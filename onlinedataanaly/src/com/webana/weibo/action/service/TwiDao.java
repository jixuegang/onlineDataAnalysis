package com.webana.weibo.action.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.webana.weibo.action.model.Tweet;
import com.webana.weibo.action.model.TwiUsers;

/**
 * client for db.
 *
 * @author 28851470
 */
public class TwiDao {

  //CS IGNORE check FOR NEXT 50 LINES. REASON: No need to add comments.
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/onlinedataanaly";
    private static final String USER = "root";
    private static final String PWD = "jixuegang";

    private static TwiDao dao;

    public static TwiDao getInstance() {
    	if(dao == null) {
    		dao = new TwiDao();
    	}
    	return dao;
    }

    private TwiDao() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public List<String> getModelByPrototype(String prototype) {
        String params[] = {prototype.trim()};
        return getDistinctValues(
                "select distinct t2.modelname from (select min(b.modelid) as aa from cdf_sacco as b where b.modelprojectname=? group by b.commercialmodelid) t1 inner join cdf_sacco t2 " +
                "on t1.aa = t2.modelid", params);
    }

    private List<String> getDistinctValues(String sql, String[] param) {
        Connection conn = getConnection();
        List<String> models = new ArrayList<String>();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= param.length; i++) {
                ps.setString(i, param[i - 1]);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String modelname = rs.getString(1);
                models.add(modelname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return models;
    }

    public void addTwi(Tweet twi) {
    	String insertsql = "insert into twi(twiMid, text, repost_number, comment_number, query_date) values(?, ?, ?, ?, ?)";
    	PreparedStatement ps = null;
    	Connection conn = getConnection();
        try {
            ps = conn.prepareStatement(insertsql);
            ps.setString(1, twi.getTwiMid());
            ps.setString(2, twi.getText());
            ps.setInt(3, twi.getRepostCount());
            ps.setInt(4, twi.getCommentCount());
            ps.setString(5, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            ps.execute();
            // insert user info
            String insertUserSql = "insert into twi_users(twiMid, screenName, gender, userType, location, followersCount," +
            		"repostCount, commentCount) values(?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(insertUserSql);
            for (TwiUsers user: twi.getUsers()) {
                ps.setString(1, twi.getTwiMid());
                ps.setString(2, user.getScreenName());
                ps.setString(3, user.getGender());
                ps.setString(4, user.getUserType());
                ps.setString(5, user.getLocation());
                ps.setInt(6, user.getFollowers());
                ps.setInt(7, user.getRepostCount());
                ps.setInt(8, user.getCommentCount());
                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
