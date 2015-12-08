/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.expression;

import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.query.Param;
import com.github.reels_project.reels.query.query.TypesafeQuery;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class GeExp<T extends Comparable<? super T>> extends ComparableExp<T> {

	public GeExp(IDBColumn<T> left, IDBColumn<T> right) {
		super(left, right);
	}

	public GeExp(IDBColumn<T> left, T right) {
		super(left, right);
	}
	
	public GeExp(IDBColumn<T> left, Param right) {
		super(left, right);
	}

	public GeExp(IDBColumn<T> left, TypesafeQuery right) {
		super(left, right);
	}

	@Override
	protected String getSQL(String l,String r) {
		return String.format("%s >= %s", l,r);
	}
}
