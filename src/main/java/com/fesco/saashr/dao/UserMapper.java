package com.fesco.saashr.dao;


import com.fesco.saashr.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author WangXingYu
 * @date 2018-01-02
 */
public interface UserMapper {

    int insert(User user);

    @Delete("DELETE FROM sec_user WHERE id = #{id}")
    int deleteById(int id);

    @Update("UPDATE sec_user SET username = #{username}, password = #{password}, person_id = #{person_id} WHERE id = #{id}")
    int update(User user);

    @Select("SELECT id, username, password, person_id FROM sec_user WHERE id = #{id}")
    User findById(int id);

    @Select("SELECT id, username, password, person_id FROM sec_user")
    List<User> findAll();
}
