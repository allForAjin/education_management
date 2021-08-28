package dao;

import model.Sdept;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName SdeptDao.java
 * @Description TODO
 * @createTime 2021-06-29 00:08:21
 */
public class SdeptDao extends MainDao {
    private List<Sdept> sdeptList;

    public SdeptDao() {
        this.sdeptList = new ArrayList<>();
        this.dao = new SqlDao();
    }

    public List<Sdept> getAllSdeptInfo() {
        this.listClear(sdeptList);

        ResultSet rs = dao.execView("select * from Sdept");
        try {
            while (rs.next()) {
                sdeptList.add(this.getSdept(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();


        return sdeptList;
    }

    private Sdept getSdept(ResultSet rs) {
        String sdeptNo = "";
        String sdeptName = "";

        try {
            sdeptNo = rs.getString(1);
            sdeptName = rs.getString(2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new Sdept(sdeptNo, sdeptName);
    }

    public List<Sdept> getPartSdept(String college) {
        this.listClear(sdeptList);

        ResultSet rs = dao.execView("select * from sdept_info where Gno in (select Gno from College where Gname='" + college + "');");
        try {
            while (rs.next()) {
                sdeptList.add(this.getSdept(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();

        return sdeptList;
    }
}
