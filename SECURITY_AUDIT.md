# CleverCRM Security Audit

Manual review of every `.java` file at the repository root (commit `f2992b5`).

**Important context:** the repository is a flat collection of ~70 Java source files with no
build file (no `pom.xml` / `build.gradle`), no `application.properties`, no Spring Boot
application class, and no test suite. Many files do not compile (misspelled annotations such as
`@component` / `@configuration` / `@entity`, `@JoinColumn(name =="guest_id")`, `for(int i = 0, i < n; i++)`,
unterminated methods in `LLMController`, `MessageController`, `TaskController`, `OpenAIClient.classifyMessage`).
The fixes below therefore could **not** be compiled or exercised at runtime.

## Findings

| # | Severity | Issue | Location | Status |
|---|----------|-------|----------|--------|
| 1 | Critical | No authentication or authorization anywhere. There is no Spring Security configuration, no filter, and no `@PreAuthorize`/role check. Every `/api/**` endpoint — guest PII, reservations, tasks, settings, LLM calls — is anonymously reachable, including destructive ones (`DELETE /api/guests/{id}`, `DELETE /api/reservations/{id}`). | all controllers | Fixed (baseline) |
| 2 | Critical | Hardcoded API key fallback: `System.getenv().getOrDefault("OPENAI_API_KEY","MY_API_KEY")`. The placeholder is not a live credential, but the pattern silently ships a literal key into the Authorization header instead of failing. (`"Bearer" + apiKey` also omits the separating space.) | `OpenAIClient.java` | Fixed |
| 3 | High | Wildcard CORS: `@CrossOrigin(origins = "*")` on an endpoint that spends money on the OpenAI API, allowing any site to invoke it from a visitor's browser. | `EmbeddingController.java` | Fixed |
| 4 | High | Unvalidated user input on unauthenticated, cost-incurring endpoints: `maxChars`/`overlap` accepted as arbitrary ints (a negative or zero `maxChars` breaks the `substring` chunking loop), unbounded `text` length, `topK` unbounded, uploaded files accepted with no size or content-type check. | `ChunkingController.java`, `ChunkingService.java`, `EmbeddingController.java`, `FileIngestionController.java` | Fixed |
| 5 | High | No dependency management at all: no manifest, so no version pinning and no possibility of vulnerability scanning. Nothing to audit and nothing that would fail a CVE check today — but also nothing preventing a vulnerable transitive dependency from entering the build later. | repository root | Reported only |
| 6 | Medium | Mass assignment: JPA entities are bound directly from request bodies (`@RequestBody Task`, `@RequestBody Message`), letting a caller set server-controlled fields such as `id`, `completedAt`, `status`, or `isRead`. DTOs exist for guests/reservations but not for tasks/messages. | `TaskController.java`, `MessageController.java` | Reported only |
| 7 | Medium | Information disclosure: no exception handler, so `RuntimeException("Guest not found")` and any JPA/WebClient failure surface as a Spring error payload with exception details; `ChunkingService` also logged ingestion details to stdout. | `GuestHistoryService.java`, `GuestPreferenceService.java`, `ChunkingService.java` | Fixed (handler added, stdout log removed) |
| 8 | Medium | Missing access control on object level (IDOR): guest, reservation, message, and notification lookups are keyed only by an id/`staffId` with no ownership or tenant check, so once #1 is addressed any authenticated staff account can still read any record. | `GuestController.java`, `NotificationController.java`, `MessageRepository.java` | Reported only |
| 9 | Medium | Prompt injection / untrusted LLM output: guest-supplied message text is concatenated into system prompts, and the model's suggested action is then enacted (`enactSuggestedAction`) with no allow-list validation of `type`/`module`/`payload`. | `OpenAIClient.java`, `MessageClassifierController.java` | Reported only |
| 10 | Low | `Settings` is exposed read-only via `GET /api/Settings/*` with plain (unannotated) request parameters; if operational settings ever hold credentials they would be publicly readable. | `SettingsController.java` | Reported only |

### Checked, not vulnerable

- **SQL injection:** the only native query (`CrmChunkRepository.findNearest`) uses bound
  `:queryEmbedding` / `:k` parameters, and `MessageRepository.searchMessages` uses JPQL with a
  bound `:keyword`. No string-concatenated SQL, no `createQuery`/`EntityManager` usage anywhere.
- **Exposed debug endpoints:** no actuator configuration, no `/debug`, `/dump`, or test-only
  controller. `POST /api/Response/postProcess` and `/api/chunking/chunk` are effectively developer
  utility endpoints, covered by findings #1 and #4 rather than a separate issue.
- **Committed secrets:** no `.env`, key material, or credential literal is present in the tree
  besides the placeholder in finding #2.

## Fixes applied

- `SecurityConfig.java` (new) — deny-by-default filter chain (`anyRequest().authenticated()`),
  stateless sessions, and a CORS policy restricted to origins from
  `clevercrm.cors.allowed-origins` (default `http://localhost:3000`). HTTP Basic is a placeholder
  authentication mechanism; replace it with the project's real scheme (JWT/OIDC).
- `GlobalExceptionHandler.java` (new) — `IllegalArgumentException` → `400` with the validation
  message, everything else → `500` with a generic body, details logged server-side only.
- `OpenAIClient.java` — no key fallback; fails fast when `OPENAI_API_KEY` is unset. Fixed the
  `Bearer` separator and the `HtttpHeaders` typo.
- `EmbeddingController.java` — removed `@CrossOrigin(origins = "*")`; bounds `text` (≤8000 chars,
  non-blank) and `topK` (1–50).
- `ChunkingService.java` — validates `text`, `maxChars`, `overlap`, `sourceType`, `sourceId`;
  removed the stdout log.
- `FileIngestionController.java` — rejects empty uploads, files >10MB, and content types outside
  `text/plain`, `text/csv`, `application/pdf`.
- `.gitignore` (new) — keeps `.env`, `*.pem`, `*.key`, and local property files out of the repo.

## Recommended next (not done here)

1. Add a build file (Maven/Gradle) pinning `spring-boot-starter-security`,
   `spring-boot-starter-validation`, and `spring-boot-starter-web`; the new config classes need
   Spring Security on the classpath to take effect. Wire in a dependency scanner
   (`dependency-check`, Dependabot) at the same time — finding #5.
2. Replace the HTTP Basic placeholder with the intended authentication scheme and add role checks
   on mutating endpoints; add ownership/tenant scoping for findings #1 and #8.
3. Introduce request DTOs with `@Valid` for tasks and messages instead of binding entities —
   finding #6.
4. Validate LLM-suggested actions against an allow-list before executing them, and separate
   untrusted guest text from instructions in prompts — finding #9.
5. Move the file layout into `src/main/java/com/cleverCRM/...` matching the declared packages, and
   fix the compilation errors listed above so the code (and these fixes) can actually be verified.
