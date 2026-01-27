
@configuration
@EnableAsync
public class AsynchConfig{
	
	@Bean(name = "llmExecutor")
	public Executor llmExecutor() {
		ThreadpoolTaskExecutor executor = new ThreadpoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("llm-async-");
		executor.initialize();
		
		return executor;
	}
	
}