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
public class NotLikeExp extends LikeExp {

	public NotLikeExp(IDBColumn<String> left, String right) {
		super(left, right);
	}
	
	public NotLikeExp(IDBColumn<String> left, Param right) {
		super(left, right);
	}

	public NotLikeExp(IDBColumn<String> left, TypesafeQuery right) {
		super(left, right);
	}

	@Override
	protected String getSQL(String l,String r) {
		return String.format("%s NOT LIKE %s ESCAPE '!'", l,r);
	}
}
