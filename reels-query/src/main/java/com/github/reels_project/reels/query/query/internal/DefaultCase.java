package com.github.reels_project.reels.query.query.internal;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.github.reels_project.reels.query.meta.IBooleanDBColumn;
import com.github.reels_project.reels.query.meta.IDateDBColumn;
import com.github.reels_project.reels.query.meta.INumberDBColumn;
import com.github.reels_project.reels.query.meta.IStringDBColumn;
import com.github.reels_project.reels.query.meta.impl.BooleanDBColumnImpl;
import com.github.reels_project.reels.query.meta.impl.DateDBColumnImpl;
import com.github.reels_project.reels.query.meta.impl.NumberDBColumnImpl;
import com.github.reels_project.reels.query.meta.impl.StringDBColumnImpl;
import com.github.reels_project.reels.query.query.Case;

public abstract class DefaultCase implements Case{

	@Override
	public IStringDBColumn endAsStringColumn() {
		return new StringDBColumnImpl(this);
	}

	@Override
	public IDateDBColumn<Date> endAsDateColumn() {
		return new DateDBColumnImpl<Date>(this);
	}

	@Override
	public IDateDBColumn<Timestamp> endAsTimestampColumn() {
		return new DateDBColumnImpl<Timestamp>(this);
	}

	@Override
	public IDateDBColumn<Time> endAsTimeColumn() {
		return new DateDBColumnImpl<Time>(this);
	}

	@Override
	public IBooleanDBColumn endAsBooleanColumn() {
		return new BooleanDBColumnImpl(this);
	}

	@Override
	public <N extends Number & Comparable<? super N>> INumberDBColumn<N> endAsNumberColumn() {
		return new NumberDBColumnImpl<N>(this);
	}
}
