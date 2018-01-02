package com.fesco.saashr.service;

import java.util.List;

/**
 * @author WangXingYu
 * @date 2018-01-01
 */
public interface BaseService<T> {

    int insert(T entity);

    int deleteById(int id);


    int update(T entity);

    T findById(int id);

    List<T> findAll();
}