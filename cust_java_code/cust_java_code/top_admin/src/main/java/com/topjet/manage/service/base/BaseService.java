package com.topjet.manage.service.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseService<T,K> {
	
//	public void add(T t)  throws Exception;
//
//	public void update(T t)  throws Exception;
//
//
//	public void updateBySelective(K k);
//
	public void delete(Integer... ids) throws Exception;
//
//	public int queryByCount(K model)throws Exception;
//
//	public List<T> queryByList(K model) throws Exception;
//
//	public T queryById(Integer id) throws Exception;

	int countByCriteria(K k);

	int deleteByCriteria(K k);

	int deleteByPrimaryKey(Integer id);

	int insert(T t);

	int insertSelective(T t);

	List<T> selectByCriteria(K k);

	T selectByPrimaryKey(Integer id);

	int updateByCriteriaSelective(@Param("record") T t, @Param("example") K k);

	int updateByCriteria(@Param("record") T record, @Param("example") K k);

	int updateByPrimaryKeySelective(T t);

	int updateByPrimaryKey(T t);

}
