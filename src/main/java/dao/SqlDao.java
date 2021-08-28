package dao;

import java.sql.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName SqlDao.java
 * @Description TODO
 * @createTime 2021-06-22 18:20:38
 */
public class SqlDao {
    private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DBURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Teaching_system";
    private static final String USER_NAME = "sa";
    private static final String USER_PWD = "165404026lmkaz";
    private static Connection conn = null;

    public Connection connectDatabase() {
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(DBURL, USER_NAME, USER_PWD);
            System.out.println("success!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("fail!");
        }
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet execView(String sql) {
        Connection conn = this.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = conn.prepareCall(sql);
            ps.setFetchSize(500);
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public PreparedStatement execUpdate(String sql) {
        Connection conn = this.connectDatabase();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            System.out.println("fail");
            throwables.printStackTrace();
        }
        return ps;
    }

    public int execIsExistedProc(String sql, String queryFirstCondition, String querySecondCondition) {
        Connection conn = this.connectDatabase();
        CallableStatement cstmt = null;
        int flag = -1;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, queryFirstCondition);
            cstmt.setString(2, querySecondCondition);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.execute();
            flag = cstmt.getInt(3);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public int execIsExistedProc(String sql, String queryFirstCondition) {
        Connection conn = this.connectDatabase();
        CallableStatement cstmt = null;
        int flag = 0;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, queryFirstCondition);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.execute();
            flag = cstmt.getInt(2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public int execUpdateProc(String sql, String... condition) {
        Connection conn = this.connectDatabase();
        CallableStatement cstmt = null;
        int flag = -1;
        int num = condition.length;
        try {
            cstmt = conn.prepareCall(sql);
            for (int i = 0; i < num; i++) {
                cstmt.setString(i + 1, condition[i]);
            }
            cstmt.executeUpdate();
            flag = cstmt.getUpdateCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
        return flag;
    }

    public int execUpdateCourseProc(String sql, int period, int credit, int amount, String... condition) {
        Connection conn = this.connectDatabase();
        CallableStatement cstmt = null;
        int flag = -1;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, condition[0]);
            cstmt.setString(2, condition[1]);
            cstmt.setInt(3, period);
            cstmt.setInt(4, credit);
            cstmt.setString(5, condition[2]);
            cstmt.setInt(6, amount);
            cstmt.setString(7, condition[3]);
            cstmt.executeUpdate();
            flag = cstmt.getUpdateCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
        return flag;
    }


    public int execTwoConditionProc(String sql, String firstCondition, String secondCondition) {
        Connection conn = this.connectDatabase();
        CallableStatement cstmt = null;
        int count = 0;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, firstCondition);
            cstmt.setString(2, secondCondition);
            count = cstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getSQLState());
        }
        return count;
    }

    public int execDeleteProc(String sql, String id) {
        Connection conn = this.connectDatabase();
        CallableStatement cstmt = null;
        int count = 0;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, id);
            cstmt.execute();
            count = cstmt.getUpdateCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getSQLState());
        }
        this.closeConnection();
        return count;
    }


}
