package util;

import java.util.Optional;

public final class EntityLookup {

	private EntityLookup() {
	}

	public static <T> T orThrow(Optional<T> found, String entityName, Object id) {
		return found.orElseThrow(() -> new IllegalArgumentException(entityName + " not found: " + id));
	}
}
