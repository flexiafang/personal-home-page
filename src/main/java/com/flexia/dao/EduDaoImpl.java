package com.flexia.dao;

import com.flexia.bean.Edu;
import com.flexia.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EduDaoImpl implements BaseDao<Edu> {

    private static final String SQL_INSERT = "insert into kkb_edu(userid,start,end,school,study,description) values(?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_USERID = "select * from kkb_edu where userid=? order by start";

    @Override
    public int insert(Edu edu) {
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.prepareStatement(SQL_INSERT);
            statement.setInt(1, edu.getUserId());
            statement.setString(2, edu.getStart());
            statement.setString(3, edu.getEnd());
            statement.setString(4, edu.getSchool());
            statement.setString(5, edu.getStudy());
            statement.setString(6, edu.getDescription());
            int row = statement.executeUpdate();
            return row > 0 ? row : -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, statement, resultSet);
        }
        return -1;
    }

    @Override
    public Edu findByUserId(int userId) {
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Edu edu = null;
        try {
            statement = conn.prepareStatement(SQL_FIND_BY_USERID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (edu != null) {
                    Edu temp = new Edu();
                    temp.setNext(edu);
                    edu = temp;
                } else {
                    edu = new Edu();
                }
                int id = resultSet.getInt("id");
                String start = resultSet.getString("start");
                String end = resultSet.getString("end");
                String school = resultSet.getString("school");
                String study = resultSet.getString("study");
                String description = resultSet.getString("description");
                edu.setId(id);
                edu.setStart(start);
                edu.setEnd(end);
                edu.setSchool(school);
                edu.setStudy(study);
                edu.setDescription(description);
                edu.setUserId(userId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, statement, resultSet);
        }
        return edu;
    }
}
