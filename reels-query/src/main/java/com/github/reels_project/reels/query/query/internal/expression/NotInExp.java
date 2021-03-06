/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.expression;

import java.util.List;

import com.github.reels_project.reels.query.meta.IDBColumn;
import com.github.reels_project.reels.query.query.TypesafeQuery;
import com.github.reels_project.reels.query.query.internal.QueryUtils;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class NotInExp<T extends Comparable<? super T>> extends InExp<T> {

	public NotInExp(IDBColumn<T> left, T[] objects) {
		super(left, objects);
	}

	public NotInExp(IDBColumn<T> left, TypesafeQuery query) {
		super(left, query);
	}

	@Override
	protected String getSQL(String l,List<String> expressions){
		return String.format("%s NOT IN(%s)",l, QueryUtils.joinWith(",", expressions));
	}
}
