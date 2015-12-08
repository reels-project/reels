package com.github.reels_project.reels.query.query;

import com.github.reels_project.reels.query.Settings;
import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.query.internal.DB2TypesafeQuery;
import com.github.reels_project.reels.query.query.internal.DefaultTypesafeQuery;
import com.github.reels_project.reels.query.query.internal.MySQLTypesafeQuery;

public final class TypesafeQueryFactory {
	private TypesafeQueryFactory() {}
	
	public static TypesafeQuery get(){
		Settings settings = Settings.get();
		String dbType = settings.getDbType();
		if(dbType == null || dbType.isEmpty()){
			return new DefaultTypesafeQuery();
		}
		
		if("db2".equalsIgnoreCase(dbType)){
			return new DB2TypesafeQuery();
		}
		
		if("mysql".equalsIgnoreCase(dbType)){
			return new MySQLTypesafeQuery();
		}
		
		throw new RuntimeException("不正なDBタイプが指定されました " + dbType);
	}
	
	public static TypesafeQuery get(IDBColumn<?>...columns){
		Settings settings = Settings.get();
		String dbType = settings.getDbType();
		if(dbType == null || dbType.isEmpty()){
			return new DefaultTypesafeQuery(columns);
		}
		
		if("db2".equalsIgnoreCase(dbType)){
			return new DB2TypesafeQuery(columns);
		}
		
		if("mysql".equalsIgnoreCase(dbType)){
			return new MySQLTypesafeQuery(columns);
		}

		throw new RuntimeException("不正なDBタイプが指定されました " + dbType);
	}
}
