package com.github.reels_project.reels.query;

import static com.github.reels_project.reels.query.Q.*;
import java.util.HashMap;
import java.util.Map;

import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.query.QueryExecutor;

public class ReusableModelHandler<T> extends ModelHandler<T> implements AutoCloseable{

	private Map<String, QueryExecutor> cache;
	
	public ReusableModelHandler(Class<T> modelClass, IDBTable table, ModelDescription description) {
		super(modelClass, table, description);
		cache = new HashMap<>();
	}

	@Override
	protected QueryExecutor createExecutor(String sql) {
		QueryExecutor ex = cache.get(sql);
		if(ex == null){
			ex = newExecutor(sql);
			cache.put(sql, ex);
		}
		return ex;
	}
	
	protected QueryExecutor newExecutor(String sql){
		return stringQuery(sql).forReuse();
	}
	
	protected Map<String, QueryExecutor> getCache(){
		return cache;
	}

	@Override
	public void close() {
		cache.forEach((sql,executor) -> executor.close());
	}
}
