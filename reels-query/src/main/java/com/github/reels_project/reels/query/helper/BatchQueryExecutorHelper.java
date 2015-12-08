package com.github.reels_project.reels.query.helper;

import java.util.Objects;
import java.util.function.Consumer;

import com.github.reels_project.reels.query.query.BatchQueryExecutor;

public class BatchQueryExecutorHelper {
	private final BatchQueryExecutor executor;
	
	public BatchQueryExecutorHelper(BatchQueryExecutor executor) {
		this.executor = Objects.requireNonNull(executor);
	}
	
	public void execute(Consumer<BatchQueryExecutor> consumer){
		Objects.requireNonNull(consumer).accept(executor);
		executor.executeBatch();
	}
}
