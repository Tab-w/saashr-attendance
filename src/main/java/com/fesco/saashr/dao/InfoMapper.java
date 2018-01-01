package com.fesco.saashr.dao;

import com.fesco.saashr.model.Info;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by WangXingYu on 2017-12-31.
 */
@Mapper
public interface InfoMapper {

    int insert(Info info);

    @Delete("DELETE FROM info WHERE id = #{id}")
    int deleteById(int id);

    @Update("UPDATE info SET name = #{name}, age = #{age}, score = #{score} WHERE id = #{id}")
    int update(Info info);

    @Select("SELECT id, name, age, score FROM info WHERE id = #{id}")
    Info findById(int id);

    @Select("SELECT id, name, age, score FROM info")
    List<Info> findAll();
}
