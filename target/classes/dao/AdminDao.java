package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminDao.java
 * @Description TODO
 * @createTime 2021-07-06 15:34:38
 */
public class AdminDao extends MainDao {

    public boolean isExisted(String userName) {
        String sql = "select UserName from AdminUser where UserName='" + userName + "';";
        ResultSet rs = dao.execView(sql);
        if (rs != null) {
            return true;
        }
        return false;
    }

    public boolean isPassed(String userName, String password) {
        String sql = "select UserName,Password from AdminUser where UserName='" + userName + "';";
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                if (userName.equals(rs.getString(1))&&
                        password.equals(rs.getString(2))) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return false;
    }
}
