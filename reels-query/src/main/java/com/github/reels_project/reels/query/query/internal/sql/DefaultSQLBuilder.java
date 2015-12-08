package com.github.reels_project.reels.query.query.internal.sql;

import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.query.Exp;
import com.github.reels_project.reels.query.query.SQLBuilder;
import com.github.reels_project.reels.query.query.internal.DefaultQueryContext;

public class DefaultSQLBuilder implements SQLBuilder{

	@Override
	public String createDeleteSQL(IDBTable root, Exp exp) {
		StringBuilder sb = new StringBuilder();
		sb
			.append("DELETE FROM ")
			.append(root.getName())
			.append(" ")
			.append(root.getAlias());
		if(exp != null){
			DefaultQueryContext context = new DefaultQueryContext(root);
			String p = exp.getSQL(context);
			if(p != null && !p.isEmpty()){
				sb
				.append(" WHERE ")
				.append(exp.getSQL(context));
			}
		}
		return sb.toString();
	}
}
