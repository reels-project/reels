package com.github.reels_project.reels.query.helper;

import java.util.function.Consumer;

import com.github.reels_project.reels.query.BatchModelHandler;

public class BatchMetaHelper<T> {
	
	private BatchModelHandler<T> handler;
	
	public BatchMetaHelper(BatchModelHandler<T> handler){
		this.handler = handler;
	}
	
	public void execute(Consumer<BatchModelHandler<T>> consumer){
		try {
			consumer.accept(handler);
			handler.executeBatch();
		} finally {
			handler.close();
		}
	}
}
