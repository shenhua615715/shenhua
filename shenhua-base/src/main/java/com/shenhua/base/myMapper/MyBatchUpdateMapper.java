package com.shenhua.base.myMapper;

import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface MyBatchUpdateMapper<T> {
	
	@UpdateProvider(type=MyBatchUpdateProvider.class, method="dynamicSQL")
	void batchUpdate(List<T> list);

}
