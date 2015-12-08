package com.sample.model;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import com.github.reels_project.reels.query.Bulk;
import com.github.reels_project.reels.query.DefaultFinder;
import com.github.reels_project.reels.query.Finder;
import com.github.reels_project.reels.query.ModelDescription;
import com.github.reels_project.reels.query.ModelHandler;
import com.github.reels_project.reels.query.ReusableModelHandler;
import com.github.reels_project.reels.query.meta.IDBTable;
import com.github.reels_project.reels.query.meta.IStringDBColumn;
import com.github.reels_project.reels.query.meta.impl.DBTableImpl;
import com.github.reels_project.reels.query.meta.impl.StringDBColumnImpl;

@Generated("Meta Generator")
public final class Unit_{

	public static final IDBTable TABLE = new DBTableImpl("unit");
	public static final IStringDBColumn UNIT_ID = new StringDBColumnImpl(TABLE,"unit_id");
	public static final IStringDBColumn NAME = new StringDBColumnImpl(TABLE,"name");
	private static final List<String> _FIELDS = Arrays.asList("unitId","name");
	private static final ModelDescription _DESC = new ModelDescription(Unit.class, _FIELDS);
	private static final ModelHandler<Unit> model = new ModelHandler<Unit>(Unit.class,TABLE,_DESC);
	private static final Finder<String,Unit> find = new DefaultFinder<java.lang.String,Unit>(Unit.class,TABLE,_DESC);
	private static final Bulk bulk = new Bulk(TABLE);

	private Unit_(){
	}

	public static ModelHandler<Unit> model(){
		return model;
	}

	public static ReusableModelHandler<Unit> modelForReuse(){
		return new ReusableModelHandler<>(Unit.class,TABLE,_DESC);
	}

	public static Finder<String,Unit> find(){
		return find;
	}

	public static Bulk bulk(){
		return bulk;
	}


}