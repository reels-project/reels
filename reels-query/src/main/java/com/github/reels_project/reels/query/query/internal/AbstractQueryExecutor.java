package com.github.reels_project.reels.query.query.internal;

import com.github.reels_project.reels.query.jdbc.SQLRunner;
import com.github.reels_project.reels.query.jdbc.SQLRunnerFactory;

public abstract class AbstractQueryExecutor {
	
	private final String sql;
	private final SQLRunner sqlRunner;
	
	AbstractQueryExecutor(String sql) {
		this.sql = sql;
		this.sqlRunner = SQLRunnerFactory.get().newSQLRunner(sql);
	}
	
	protected SQLRunner getSQLRunner(){
		return sqlRunner;
	}
	
	public void close(){
		sqlRunner.close();
	}

	@Override
	public String toString() {
		return this.sql;
	}
}
