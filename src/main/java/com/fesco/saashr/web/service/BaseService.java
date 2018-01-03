package com.fesco.saashr.web.service;

/**
 * @author WangXingYu
 * @date 2018-01-01
 */
public interface BaseService<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);


}