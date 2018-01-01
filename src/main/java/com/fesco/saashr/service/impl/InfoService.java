package com.fesco.saashr.service.impl;

import com.fesco.saashr.dao.InfoMapper;
import com.fesco.saashr.model.Info;
import com.fesco.saashr.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: WangXingYu
 * @date: 2017-12-31 10:06
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class InfoService implements BaseService<Info> {
    @Autowired
    private InfoMapper mapper;

    @Override
    public int insert(Info entity) {
        for (int i = 0; i < 100; i++) {
            mapper.insert(entity);
            if (i == 50) {
                System.out.println(1 / 0);
            }
        }
        return 1;
    }

    @Override
    public int deleteById(int id) {
        return mapper.deleteById(id);
    }

    @Override
    public int update(Info entity) {
        return mapper.update(entity);
    }

    @Override
    public Info findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public List<Info> findAll() {
        return mapper.findAll();
    }
}
