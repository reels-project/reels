package com.github.reels_project.reels.query.query;

public interface SearchedCase extends Case{
	SearchedCase when(Exp exp);
	SearchedCase then(Object o);
	SearchedCase else_(Object o);
}
