package com.github.reels_project.reels.query.query.internal;

import com.github.reels_project.reels.query.meta.IDBColumn;

public class MySQLTypesafeQuery extends DefaultTypesafeQuery{

	public MySQLTypesafeQuery() {
		super();
	}

	public MySQLTypesafeQuery(IDBColumn<?>... columns) {
		super(columns);
	}

	@Override
	protected StringBuilder createLimitOffset(StringBuilder sb,Integer limit,Integer offset) {
		if(limit != null){
			sb.append("LIMIT " + limit + " ");
		}
		
		if(offset != null){
			sb.append("OFFSET " + offset + " ");
		}
		return sb;
	}
}
