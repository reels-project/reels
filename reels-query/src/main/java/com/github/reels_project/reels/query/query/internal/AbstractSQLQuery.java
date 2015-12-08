package com.github.reels_project.reels.query.query.internal;

import com.github.reels_project.reels.query.query.BatchQueryExecutor;
import com.github.reels_project.reels.query.query.QueryExecutor;
import com.github.reels_project.reels.query.query.SQLQuery;

public abstract class AbstractSQLQuery implements SQLQuery{

	@Override
	public QueryExecutor forOnce() {
		QueryExecutor executableQuery = new SimpleQueryExecutor(getSQL(null));
		return executableQuery;
	}

	@Override
	public QueryExecutor forReuse() {
		QueryExecutor executableReusableQuery = new ReusableQueryExecutor(getSQL(null));
		return executableReusableQuery;
	}

	@Override
	public BatchQueryExecutor forBatch() {
		BatchQueryExecutor batchQuery = new DefaultBatchQueryExecutor(getSQL(null));
		return batchQuery;
	}
}
