package dao;

import model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName TeacherDao.java
 * @Description TODO
 * @createTime 2021-06-30 19:55:51
 */
public class TeacherDao extends MainDao {
    private final List<Teacher> teacherList;

    public TeacherDao() {
        this.dao = new SqlDao();
        this.teacherList = new ArrayList<>();
    }

    public List<Teacher> getAllTeacherName() {
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "select * from teacher_all_info;";
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                teacherList.add(this.getTeacher(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return teacherList;
    }

    public List<Teacher> getAllTeacherInfo() {
        this.listClear(teacherList);
        String sql = "select * from teacher_all_info";

        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                teacherList.add(this.getTeacher(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return teacherList;
    }

    public List<String> getTeacherNameByCollege(String college) {
        List<String> teacherList = new ArrayList<>();
        String sql = "select Tname from teacher_all_info where Gname='" + college + "';";
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                teacherList.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return teacherList;
    }

    public List<String> getTeacherNameBySdept(String sdept) {
        List<String> teacherList = new ArrayList<>();
        String sql = "select Tname from teacher_all_info where Dname='" + sdept + "';";
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                teacherList.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return teacherList;
    }

    public String getTeacherIdByName(String teacherName) {
        String sql = "select Tno from Teacher where Tname='" + teacherName + "';";
        ResultSet rs = dao.execView(sql);
        String name = "";
        try {
            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }

    public List<String> getAllOfficeName() {
        List<String> officeList = new ArrayList<>();
        String sql = "select Office_name from Office";
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                officeList.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return officeList;
    }

    public List<String> getOfficeNameByCollegeOrSdept(String name, String collegeOrSdept) {
        String sql = "";
        if (collegeOrSdept.equals("sdept")) {
            sql = "select Office_name from office_sdept_college where Dname='" + name + "';";
        } else if (collegeOrSdept.equals("college")) {
            sql = "select Office_name from office_sdept_college where Gname='" + name + "';";
        }
        List<String> officeName = new ArrayList<>();
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                officeName.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return officeName;
    }

    public List<Teacher> getPartTeacherInfo(String idOrName, String sex, String title, String college, String sdept, String office) {
        this.listClear(teacherList);

        if (this.userIsIdOrName(idOrName) == "null") {
            idOrName = "";
        }
        if (sex == null) {
            sex = "";
        }
        if (title == null) {
            title = "";
        }
        if (college == null) {
            college = "";
        }
        if (sdept == null) {
            sdept = "";
        }
        if (office == null) {
            office = "";
        }
        if (idOrName == "" && sex == "" && title == "" && college == "" && sdept == "" && office == "") {
            return this.getAllTeacherInfo();
        }
        String mainSql = "select * from teacher_all_info ";
        String sqlId = mainSql + "where Tno='" + idOrName + "'";
        String sqlSex = mainSql + "where Sex='" + sex + "'";
        String sqlTitle = mainSql + "where Title='" + title + "'";
        String sqlCollege = mainSql + "where Gname='" + college + "'";
        String sqlSdept = mainSql + "where Dname='" + sdept + "'";
        String sqlOffice = mainSql + "where Office_name='" + office + "'";
        String intersect = " intersect ";


        if (this.userIsIdOrName(idOrName) == "name") {
            sqlId = mainSql + "where Tname='" + idOrName + "'";
        }
        if (idOrName.length() == 0) {
            sqlId = mainSql;
        }
        if (sex.length() == 0 || sex == "全部") {
            sqlSex = mainSql;
        }
        if (title.length() == 0 || title == "所有职称") {
            sqlTitle = mainSql;
        }
        if (college.length() == 0 || college == "所有学院") {
            sqlCollege = mainSql;
        }
        if (sdept.length() == 0 || sdept == "所有专业") {
            sqlSdept = mainSql;
        }
        if (office.length() == 0 || office == "所有办公室") {
            sqlOffice = mainSql;
        }

        String sql = sqlId + intersect + sqlSex + intersect + sqlTitle + intersect + sqlCollege + intersect + sqlSdept + intersect + sqlOffice;
        System.out.println(sql);
        ResultSet rs;
        rs = dao.execView(sql);
        try {
            while (rs.next()) {
                teacherList.add(this.getTeacher(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();

        return this.teacherList;
    }

    public Teacher getPartTeacherInfo(String teacherId) {
        ResultSet rs = dao.execView("select * from teacher_all_info where Tno='" + teacherId + "'");
        Teacher teacher = null;
        try {
            while (rs.next()) {
                teacher = this.getTeacher(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return teacher;
    }


    private Teacher getTeacher(ResultSet rs) {
        String teacherId = "";
        String teacherName = "";
        String sex = "";
        String title = "";
        String birth = "";
        String entryDate = "";
        String telephone = "";
        String office = "";
        String sdept = "";
        String college = "";
        int age = 0;

        try {
            teacherId = rs.getString(1);
            teacherName = rs.getString(2);
            sex = rs.getString(3);
            title = rs.getString(4);
            birth = rs.getDate(5).toString();
            entryDate = rs.getDate(6).toString();
            telephone = rs.getString(7);
            office = rs.getString(8);
            sdept = rs.getString(9);
            college = rs.getString(10);
            age = rs.getInt(11);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Teacher(teacherId, teacherName, sex, title, birth, entryDate, telephone, office, sdept, college, age);
    }

    public int teach(String teacherName, String courseId) {
        String teacherId = this.getTeacherIdByName(teacherName);
        String sql = "{CALL teach_insert(?,?)}";
        return dao.execTwoConditionProc(sql, courseId, teacherId);
    }

    public int cancelTeach(String teacherName, String courseId) {
        String teacherId = this.getTeacherIdByName(teacherName);
        String sql = "delete from teach where Tno=? and Cno=?";
        PreparedStatement ps = dao.execUpdate(sql);
        int count = 0;
        try {
            ps.setString(1, teacherId);
            ps.setString(2, courseId);
            count = ps.executeUpdate();
            System.out.println(count + "条");
        } catch (SQLException throwables) {
            System.out.println("更新失败");
            throwables.printStackTrace();
        }
        return count;
    }

    public int isTeached(String courseId) {
        int flag = 0;
        String sql = "{CALL course_teach_existed(?,?)}";
        flag = dao.execIsExistedProc(sql, courseId);
        return flag;
    }

    public int deleteTeacher(String teacherId) {
        String sql = "{CALL teacher_delete_p(?)}";
        return dao.execDeleteProc(sql, teacherId);
    }

    public int updateTeacher(Teacher teacher) {
        String sql = "{CALL teacher_update(?,?,?,?,?,?,?,?)}";
        teacher.setOffice(this.getOfficeNo(teacher.getOffice()));
        return dao.execUpdateProc(sql,teacher.getTeacherId(),teacher.getTeacherName(),teacher.getSex(),
                teacher.getTitle(),teacher.getBirth(),teacher.getEntryDate(),teacher.getTelephone(),teacher.getOffice());
    }

    public String getOfficeNo(String officeName){
        String sql="select Office_no from Office where Office_name='"+officeName+"';";
        ResultSet rs=dao.execView(sql);
        String no="";
        try {
            while (rs.next()){
                no=rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return no;
    }

    public int insertTeacher(Teacher teacher) {
        String flagSql = "{CALL teacher_existed(?,?)}";
        int flag = dao.execIsExistedProc(flagSql,teacher.getTeacherId());
        int count = 0;
        if (flag == 1) {
            return -2;
        } else {
            String sql = "{CALL teacher_insert(?,?,?,?,?,?,?,?)}";
            teacher.setOffice(this.getOfficeNo(teacher.getOffice()));
            count = dao.execUpdateProc(sql, teacher.getTeacherId(),teacher.getTeacherName(),teacher.getSex(),
                    teacher.getTitle(),teacher.getBirth(),teacher.getEntryDate(),teacher.getTelephone(),
                    teacher.getOffice());
        }
        return count;

    }

    public static void main(String[] args) {
        //System.out.println(new TeacherDao().teach("20090201","001"));
        // System.out.println(new TeacherDao().cancelTeach("20090201","001"));
        System.out.println(new TeacherDao().isTeached("003"));
    }
}
