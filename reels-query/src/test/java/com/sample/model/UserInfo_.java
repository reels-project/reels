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
public final class UserInfo_{

	public static final IDBTable TABLE = new DBTableImpl("user_info");
	public static final IStringDBColumn USER_ID = new StringDBColumnImpl(TABLE,"user_id");
	private static final List<String> _FIELDS = Arrays.asList("userId");
	private static final ModelDescription _DESC = new ModelDescription(UserInfo.class, _FIELDS);
	private static final ModelHandler<UserInfo> model = new ModelHandler<UserInfo>(UserInfo.class,TABLE,_DESC);
	private static final Finder<String,UserInfo> find = new DefaultFinder<java.lang.String,UserInfo>(UserInfo.class,TABLE,_DESC);
	private static final Bulk bulk = new Bulk(TABLE);

	private UserInfo_(){
	}

	public static ModelHandler<UserInfo> model(){
		return model;
	}

	public static ReusableModelHandler<UserInfo> modelForReuse(){
		return new ReusableModelHandler<>(UserInfo.class,TABLE,_DESC);
	}

	public static Finder<String,UserInfo> find(){
		return find;
	}

	public static Bulk bulk(){
		return bulk;
	}


}