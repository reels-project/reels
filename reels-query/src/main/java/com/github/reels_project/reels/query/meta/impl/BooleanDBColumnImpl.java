package com.github.reels_project.reels.query.meta.impl;

import com.github.reels_project.reels.query.meta.IBooleanDBColumn;
import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.query.Case;
import com.github.reels_project.reels.query.query.TypesafeQuery;

public class BooleanDBColumnImpl extends DBColumnImpl<Boolean> implements IBooleanDBColumn{

	public BooleanDBColumnImpl(IDBTable table, String name) {
		super(table, name);
	}
	
	public BooleanDBColumnImpl(TypesafeQuery query) {
		super(query);
	}

	public BooleanDBColumnImpl(Case case_) {
		super(case_);
	}

	protected BooleanDBColumnImpl(IDBColumn<?> wrap) {
		super(wrap);
	}

	protected BooleanDBColumnImpl(IDBColumn<?> wrap, String otherName) {
		super(wrap, otherName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V extends IDBColumn<Boolean>> V createFromTableAlias(
			String tableAlias) {
		return (V)new BooleanDBColumnImpl(new DBTableImpl(getTable().getName(), tableAlias), getName());
	}

	@Override
	public IDBColumn<Boolean> as(String otherName) {
		return new BooleanDBColumnImpl(this, otherName);
	}
}
