package com.github.reels_project.reels.query.query.internal;

import com.github.reels_project.reels.query.meta.IDBColumn;

public class DB2TypesafeQuery extends DefaultTypesafeQuery{

	public DB2TypesafeQuery() {
		super();
	}

	public DB2TypesafeQuery(IDBColumn<?>... columns) {
		super(columns);
	}

	@Override
	protected String createForUpdate() {
		return "FOR UPDATE WITH RS";
	}
}
