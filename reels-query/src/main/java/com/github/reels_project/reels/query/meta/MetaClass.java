package com.github.reels_project.reels.query.meta;

import com.github.reels_project.reels.query.ModelDescription;

public interface MetaClass<T> {
	Class<T> modelClass();
	IDBTable table();
	ModelDescription description();
}
