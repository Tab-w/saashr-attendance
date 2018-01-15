package com.fesco.saashr.web.dao;

import com.fesco.saashr.web.model.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sec_user");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getAge() != null) {
            sql.VALUES("age", "#{age,jdbcType=INTEGER}");
        }
        
        if (record.getEmail() != null) {
            sql.VALUES("email", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getEnabled() != null) {
            sql.VALUES("enabled", "#{enabled,jdbcType=VARCHAR}");
        }
        
        if (record.getFullname() != null) {
            sql.VALUES("fullname", "#{fullname,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getPlainpassword() != null) {
            sql.VALUES("plainPassword", "#{plainpassword,jdbcType=VARCHAR}");
        }
        
        if (record.getSalt() != null) {
            sql.VALUES("salt", "#{salt,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.VALUES("sex", "#{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getFid() != null) {
            sql.VALUES("fid", "#{fid,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getOrg() != null) {
            sql.VALUES("org", "#{org,jdbcType=INTEGER}");
        }
        
        if (record.getUserMobile() != null) {
            sql.VALUES("user_mobile", "#{userMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getTid() != null) {
            sql.VALUES("tid", "#{tid,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateUser() != null) {
            sql.VALUES("create_user", "#{createUser,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getmNumber() != null) {
            sql.VALUES("m_number", "#{mNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getPwdvalid() != null) {
            sql.VALUES("pwdvalid", "#{pwdvalid,jdbcType=VARCHAR}");
        }
        
        if (record.getDefPwd() != null) {
            sql.VALUES("def_pwd", "#{defPwd,jdbcType=VARCHAR}");
        }
        
        if (record.getStartMenuId() != null) {
            sql.VALUES("start_menu_id", "#{startMenuId,jdbcType=INTEGER}");
        }
        
        if (record.getPwdupdatetime() != null) {
            sql.VALUES("pwdupdatetime", "#{pwdupdatetime,jdbcType=VARCHAR}");
        }
        
        if (record.getPersonId() != null) {
            sql.VALUES("person_id", "#{personId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("sec_user");
        
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getAge() != null) {
            sql.SET("age = #{age,jdbcType=INTEGER}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("email = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getEnabled() != null) {
            sql.SET("enabled = #{enabled,jdbcType=VARCHAR}");
        }
        
        if (record.getFullname() != null) {
            sql.SET("fullname = #{fullname,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getPlainpassword() != null) {
            sql.SET("plainPassword = #{plainpassword,jdbcType=VARCHAR}");
        }
        
        if (record.getSalt() != null) {
            sql.SET("salt = #{salt,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.SET("sex = #{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getFid() != null) {
            sql.SET("fid = #{fid,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getOrg() != null) {
            sql.SET("org = #{org,jdbcType=INTEGER}");
        }
        
        if (record.getUserMobile() != null) {
            sql.SET("user_mobile = #{userMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getTid() != null) {
            sql.SET("tid = #{tid,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateUser() != null) {
            sql.SET("create_user = #{createUser,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getmNumber() != null) {
            sql.SET("m_number = #{mNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getPwdvalid() != null) {
            sql.SET("pwdvalid = #{pwdvalid,jdbcType=VARCHAR}");
        }
        
        if (record.getDefPwd() != null) {
            sql.SET("def_pwd = #{defPwd,jdbcType=VARCHAR}");
        }
        
        if (record.getStartMenuId() != null) {
            sql.SET("start_menu_id = #{startMenuId,jdbcType=INTEGER}");
        }
        
        if (record.getPwdupdatetime() != null) {
            sql.SET("pwdupdatetime = #{pwdupdatetime,jdbcType=VARCHAR}");
        }
        
        if (record.getPersonId() != null) {
            sql.SET("person_id = #{personId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}