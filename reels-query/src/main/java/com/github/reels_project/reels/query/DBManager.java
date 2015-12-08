package com.github.reels_project.reels.query;

import com.github.reels_project.reels.query.jdbc.converter.DefaultJdbcValueConverter;
import com.github.reels_project.reels.query.jdbc.converter.JdbcValueConverter;
import com.github.reels_project.reels.query.query.SQLBuilder;
import com.github.reels_project.reels.query.query.internal.sql.DefaultSQLBuilder;

public final class DBManager {
	private static final JdbcValueConverter DEFAULT = new DefaultJdbcValueConverter();

	public static JdbcValueConverter getJdbcValueConverter(){
		return DEFAULT;
	}
	
	public static SQLBuilder getSQLBuilder(){
		return new DefaultSQLBuilder();
	}
}
