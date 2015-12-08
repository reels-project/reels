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
public abstract class ComparableExp<T extends Comparable<? super T>> extends BasicExp<T> {

	protected ComparableExp(IDBColumn<T> left, IDBColumn<T> right) {
		super(left, right);
	}

	protected ComparableExp(IDBColumn<T> left, T right) {
		super(left, right);
	}
	
	protected ComparableExp(IDBColumn<T> left, Param right) {
		super(left, right);
	}

	protected ComparableExp(IDBColumn<T> left, TypesafeQuery right) {
		super(left, right);
	}
}
