package dao;

import model.College;
import model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName CollegeDao.java
 * @Description TODO
 * @createTime 2021-06-24 17:56:58
 */
public class CollegeDao extends MainDao{
    private List<College> collegeList;

    public CollegeDao() {
        collegeList=new ArrayList<>();
        dao=new SqlDao();
    }

    public List<College> getAllCollegeInfo() {
        this.listClear();
        ResultSet rs = dao.execView("select * from College_info");
        try {
            while (rs.next()) {
                collegeList.add(this.getCollege(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return collegeList;
    }

    private College getCollege(ResultSet rs) {
        String collegeId = "";
        String collgegName = "";

        try {
            collegeId = rs.getString(1);
            collgegName = rs.getString(2);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new College(collegeId,collgegName);
    }

    public String getCollegeId(String collegeName){
        String sql="select Gno from College where Gname='"+collegeName+"'";
        ResultSet rs=dao.execView(sql);
        String id="";
        try {
            while (rs!=null&&rs.next()){
                id=rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    private void listClear() {
        if (!collegeList.isEmpty()) {
            collegeList.clear();
        }
    }
}
