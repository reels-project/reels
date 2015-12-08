package com.github.reels_project.reels.query;

import static com.github.reels_project.reels.query.Q.*;
import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.query.BatchQueryExecutor;
import com.github.reels_project.reels.query.query.QueryExecutor;

public class BatchModelHandler<T> extends ReusableModelHandler<T>{

	public BatchModelHandler(Class<T> modelClass, IDBTable table, ModelDescription description) {
		super(modelClass, table, description);
	}

	@Override
	protected QueryExecutor newExecutor(String sql) {
		return stringQuery(sql).forBatch();
	}
	
	public void executeBatch(){
		getCache().forEach((sql,executor) -> ((BatchQueryExecutor)executor).executeBatch());
	}
}
