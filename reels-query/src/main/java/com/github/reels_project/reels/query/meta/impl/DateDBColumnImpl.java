/**
 * 
 */
package com.github.reels_project.reels.query.meta.impl;

import com.github.reels_project.reels.query.meta.IComparableDBColumn;
import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.meta.IDateDBColumn;
import com.github.reels_project.reels.query.query.Case;
import com.github.reels_project.reels.query.query.Func;
import com.github.reels_project.reels.query.query.TypesafeQuery;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class DateDBColumnImpl<T extends Comparable<? super T>> extends ComparableDBColumnImpl<T> implements
		IDateDBColumn<T> {

	public DateDBColumnImpl(IDBTable table, String name) {
		super(table, name);
	}
	
	public DateDBColumnImpl(TypesafeQuery query) {
		super(query);
	}

	public DateDBColumnImpl(Case case_) {
		super(case_);
	}

	protected DateDBColumnImpl(IDBColumn<?> wrap) {
		super(wrap);
	}
	
	protected DateDBColumnImpl(IDBColumn<?> wrap, String otherName) {
		super(wrap, otherName);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <C extends IComparableDBColumn<T>> C addFunc(Func func) {
		DateDBColumnImpl<T> wrap = new DateDBColumnImpl<T>(this);
		wrap.add(func);
		return (C)func;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V extends IDBColumn<T>> V createFromTableAlias(String tableAlias) {
		return (V)new DateDBColumnImpl<T>(new DBTableImpl(getTable().getName(), tableAlias), getName());
	}

	@Override
	public IDBColumn<T> as(String otherName) {
		return new DateDBColumnImpl<T>(this, otherName);
	}
}
