package com.fesco.saashr.web.dao;

import com.fesco.saashr.web.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from sec_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sec_user (id, address, ",
        "age, email, enabled, ",
        "fullname, password, ",
        "plainPassword, salt, ",
        "sex, fid, type, ",
        "username, org, user_mobile, ",
        "tid, remark, create_user, ",
        "create_time, m_number, ",
        "pwdvalid, def_pwd, ",
        "start_menu_id, pwdupdatetime, ",
        "person_id)",
        "values (#{id,jdbcType=INTEGER}, #{address,jdbcType=VARCHAR}, ",
        "#{age,jdbcType=INTEGER}, #{email,jdbcType=VARCHAR}, #{enabled,jdbcType=VARCHAR}, ",
        "#{fullname,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{plainpassword,jdbcType=VARCHAR}, #{salt,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=VARCHAR}, #{fid,jdbcType=INTEGER}, #{type,jdbcType=INTEGER}, ",
        "#{username,jdbcType=VARCHAR}, #{org,jdbcType=INTEGER}, #{userMobile,jdbcType=VARCHAR}, ",
        "#{tid,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{createUser,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=VARCHAR}, #{mNumber,jdbcType=VARCHAR}, ",
        "#{pwdvalid,jdbcType=VARCHAR}, #{defPwd,jdbcType=VARCHAR}, ",
        "#{startMenuId,jdbcType=INTEGER}, #{pwdupdatetime,jdbcType=VARCHAR}, ",
        "#{personId,jdbcType=INTEGER})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "id, address, age, email, enabled, fullname, password, plainPassword, salt, sex, ",
        "fid, type, username, org, user_mobile, tid, remark, create_user, create_time, ",
        "m_number, pwdvalid, def_pwd, start_menu_id, pwdupdatetime, person_id",
        "from sec_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.VARCHAR),
        @Result(column="fullname", property="fullname", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="plainPassword", property="plainpassword", jdbcType=JdbcType.VARCHAR),
        @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="fid", property="fid", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="org", property="org", jdbcType=JdbcType.INTEGER),
        @Result(column="user_mobile", property="userMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="tid", property="tid", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="m_number", property="mNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="pwdvalid", property="pwdvalid", jdbcType=JdbcType.VARCHAR),
        @Result(column="def_pwd", property="defPwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_menu_id", property="startMenuId", jdbcType=JdbcType.INTEGER),
        @Result(column="pwdupdatetime", property="pwdupdatetime", jdbcType=JdbcType.VARCHAR),
        @Result(column="person_id", property="personId", jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update sec_user",
        "set address = #{address,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "email = #{email,jdbcType=VARCHAR},",
          "enabled = #{enabled,jdbcType=VARCHAR},",
          "fullname = #{fullname,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "plainPassword = #{plainpassword,jdbcType=VARCHAR},",
          "salt = #{salt,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "fid = #{fid,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER},",
          "username = #{username,jdbcType=VARCHAR},",
          "org = #{org,jdbcType=INTEGER},",
          "user_mobile = #{userMobile,jdbcType=VARCHAR},",
          "tid = #{tid,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "create_user = #{createUser,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=VARCHAR},",
          "m_number = #{mNumber,jdbcType=VARCHAR},",
          "pwdvalid = #{pwdvalid,jdbcType=VARCHAR},",
          "def_pwd = #{defPwd,jdbcType=VARCHAR},",
          "start_menu_id = #{startMenuId,jdbcType=INTEGER},",
          "pwdupdatetime = #{pwdupdatetime,jdbcType=VARCHAR},",
          "person_id = #{personId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}