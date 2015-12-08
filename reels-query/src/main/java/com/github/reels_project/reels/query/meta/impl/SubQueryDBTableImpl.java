package com.github.reels_project.reels.query.meta.impl;

import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.query.QueryContext;
import com.github.reels_project.reels.query.query.TypesafeQuery;


public class SubQueryDBTableImpl extends DBTableImpl{
	
	private TypesafeQuery query;
	
	public SubQueryDBTableImpl(TypesafeQuery query,IDBTable table) {
		super(table.getName());
		this.query = query;
	}

	@Override
	public String getQuery(QueryContext context) {
		return String.format("(%s)", query.getSQL(context).trim());
	}
}
