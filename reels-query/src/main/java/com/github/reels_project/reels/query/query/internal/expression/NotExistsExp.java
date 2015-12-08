/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.expression;

import com.github.reels_project.reels.query.query.TypesafeQuery;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class NotExistsExp extends ExistsExp {

	public NotExistsExp(TypesafeQuery subQuery) {
		super(subQuery);
	}

	@Override
	protected String getSQL(String subquery) {
		return String.format("NOT EXISTS(%s)",subquery);
	}
}
