package com.github.reels_project.reels.query.helper;

import java.util.Objects;
import java.util.function.Consumer;

import com.github.reels_project.reels.query.query.internal.ReusableQueryExecutor;

public class ReusableQueryExecutorHelper {
	
	private final ReusableQueryExecutor executor;
	
	public ReusableQueryExecutorHelper(ReusableQueryExecutor executor) {
		this.executor = Objects.requireNonNull(executor);
	}
	
	public void execute(Consumer<ReusableQueryExecutor> consumer){
		Objects.requireNonNull(consumer).accept(executor);
	}
}
