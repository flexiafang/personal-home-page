package com.flexia.dao;

import com.flexia.bean.User;
import com.flexia.util.DBUtil;

import java.sql.*;

/**
 * @author flexia
 * @date 2021/2/26
 */
public class UserDaoImpl implements BaseDao<User> {

    private static final String SQL_INSERT = "insert into kkb_user(name,age,city,address,email,phone,weixin,qq,weibo,sex,description) values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_USERID = "select * from kkb_user where id=?";

    @Override
    public int insert(User user) {
        // 得到连接
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 得到执行环境
            pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            // 向环境中填充参数
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getCity());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getPhone());
            pstmt.setString(7, user.getWeixin());
            pstmt.setString(8, user.getQq());
            pstmt.setString(9, user.getWeibo());
            pstmt.setString(10, user.getSex());
            pstmt.setString(11, user.getDescription());
            // 执行
            pstmt.executeUpdate();
            // 将数据的插入结果返回
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 释放数据库资源
            DBUtil.close(conn, pstmt, rs);
        }
        return -1;
    }

    @Override
    public User findByUserId(int userId) {
        // 连接数据库
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 得到执行环境
            pstmt = conn.prepareStatement(SQL_FIND_BY_USERID);
            // 向环境中填充参数
            pstmt.setInt(1, userId);
            // 执行并获取结果
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int age = rs.getInt("age");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String weixin = rs.getString("weixin");
                String qq = rs.getString("qq");
                String weibo = rs.getString("weibo");
                String sex = rs.getString("sex");
                String description = rs.getString("description");
                // 将结果返回
                User user = new User(name, age, city, address, email, phone, weixin, qq, weibo, sex, description);
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 释放数据库资源
            DBUtil.close(conn, pstmt, rs);
        }
        return null;
    }
}
