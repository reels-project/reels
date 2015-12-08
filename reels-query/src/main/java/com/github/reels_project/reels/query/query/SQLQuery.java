package com.github.reels_project.reels.query.query;


public interface SQLQuery {
	String getSQL(QueryContext context);
	QueryExecutor forOnce();
	QueryExecutor forReuse();
	BatchQueryExecutor forBatch();
}
