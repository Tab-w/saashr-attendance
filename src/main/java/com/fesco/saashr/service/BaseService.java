package com.fesco.saashr.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WangXingYu on 2017-12-31.
 */
public interface BaseService<T> {
    int insert(T entity);

    int deleteById(int id);


    int update(T entity);

    T findById(int id);

    List<T> findAll();
}
