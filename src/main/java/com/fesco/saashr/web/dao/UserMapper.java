package com.fesco.saashr.web.dao;

import com.fesco.saashr.web.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {

    @Delete({
            "delete from user",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);


    @Insert({
            "insert into user (id, username, ",
            "password, remark)",
            "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=CHAR}, ",
            "#{password,jdbcType=CHAR}, #{remark,jdbcType=CHAR})"
    })
    int insert(User record);


    @InsertProvider(type = UserSqlProvider.class, method = "insertSelective")
    int insertSelective(User record);

    @Select({
            "select",
            "id, username, password, remark",
            "from user",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.CHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.CHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.CHAR)
    })
    User selectByPrimaryKey(Integer id);

    @UpdateProvider(type = UserSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
            "update user",
            "set username = #{username,jdbcType=CHAR},",
            "password = #{password,jdbcType=CHAR},",
            "remark = #{remark,jdbcType=CHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}