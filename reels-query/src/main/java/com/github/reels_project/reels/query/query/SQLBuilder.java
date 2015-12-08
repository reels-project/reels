package com.github.reels_project.reels.query.query;

import com.github.reels_project.reels.query.meta.IDBTable;

public interface SQLBuilder {
	String createDeleteSQL(IDBTable root,Exp exp);
}
