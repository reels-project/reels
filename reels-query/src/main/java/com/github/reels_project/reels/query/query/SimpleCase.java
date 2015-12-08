package com.github.reels_project.reels.query.query;

public interface SimpleCase<T> extends Case{
	SimpleCase<T> when(T t);
	SimpleCase<T> then(Object o);
	SimpleCase<T> else_(Object o);
}
