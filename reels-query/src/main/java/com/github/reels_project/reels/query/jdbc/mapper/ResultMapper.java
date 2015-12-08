package com.github.reels_project.reels.query.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultMapper<R> {
	R map(ResultSet rs) throws SQLException;
}
