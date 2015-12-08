/**
 * 
 */
package com.github.reels_project.reels.query.query.internal.expression;

import com.github.reels_project.reels.query.query.Exp;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class OrExp extends AndExp {

	public OrExp(Exp[] exps) {
		super(exps);
	}

	@Override
	protected String getJoinString() {
		return " OR ";
	}
}
