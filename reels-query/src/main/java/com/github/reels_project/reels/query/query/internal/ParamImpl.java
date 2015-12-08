/**
 * 
 */
package com.github.reels_project.reels.query.query.internal;

import com.github.reels_project.reels.query.query.Param;

/**
 * @author Takahiko Sato(MOSA architect Inc.)
 *
 */
public class ParamImpl implements Param {

	@Override
	public String getParameterMarker() {
		return "?";
	}
	
}
