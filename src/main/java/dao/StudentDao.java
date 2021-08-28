package dao;

import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName StudentDao.java
 * @Description TODO
 * @createTime 2021-06-27 23:05:18
 */
public class StudentDao extends MainDao {
    private List<Student> studentList;

    public StudentDao() {
        this.studentList = new ArrayList<>();
    }

    public List<Student> getAllStudentInfo() {
        this.listClear(studentList);
        ResultSet rs = dao.execView("select Sno,Sname,Sex,age,Telephone,Class_no,Dname,Gname,Birth,Eroll_Date from student_info");
        try {
            while (rs.next()) {
                studentList.add(this.getStudentInfo(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return studentList;
    }

    public Student getStudentInfo(ResultSet rs) {
        String studentId = "";
        String studentName = "";
        String sex = "";
        String birth = "";
        int age = 0;
        String erollDate = "";
        String telephone = "";
        String classNo = "";
        String sdeptName = "";
        String collegeName = "";

        try {
            studentId = rs.getString(1);
            studentName = rs.getString(2);
            sex = rs.getString(3);
            age = rs.getInt(4);
            telephone = rs.getString(5);
            classNo = rs.getString(6);
            sdeptName = rs.getString(7);
            collegeName = rs.getString(8);
            birth = rs.getDate(9).toString();
            erollDate = rs.getDate(10).toString();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Student(studentId, studentName, sex, birth, age, erollDate, telephone, classNo, sdeptName, collegeName);
    }

    public List<Student> getPartStudentInfo(String studentId) {
        this.listClear(studentList);
        String sql = "select * from student_info where Sno='" + studentId + "';";
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                studentList.add(this.getStudentInfo(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return studentList;
    }

    public List<Student> getPartStudentInfo(String ID_OR_NAME, String sex, String college, String sdept, String classNo) {
        this.listClear(studentList);

        if (this.userIsIdOrName(ID_OR_NAME) == "null") {
            ID_OR_NAME = "";
        }
        if (sex == null) {
            sex = "";
        }
        if (college == null) {
            college = "";
        }
        if (sdept == null) {
            sdept = "";
        }
        if (classNo == null) {
            classNo = "";
        }

        if (ID_OR_NAME == "" && sex == "" && college == "" && sdept == "" && classNo == "") {
            return this.getAllStudentInfo();
        }

        String mainSql = "select Sno,Sname,Sex,age,Telephone,Class_no,Dname,Gname,Birth,Eroll_Date from student_info ";
        String sqlSno = mainSql + "where Sno='" + ID_OR_NAME + "'";
        String sqlSex = mainSql + "where Sex='" + sex + "'";
        String sqlCollege = mainSql + "where Gname='" + college + "'";
        String sqlSdept = mainSql + "where Dname='" + sdept + "'";
        String sqlClassNo = mainSql + "where Class_no='" + classNo + "'";
        String intersect = " intersect ";

        if (this.userIsIdOrName(ID_OR_NAME) == "name") {
            sqlSno = mainSql + "where Sname='" + ID_OR_NAME + "'";
        }
        if (ID_OR_NAME.length() == 0) {
            sqlSno = mainSql;
        }
        if (sex.length() == 0 || sex == "全部") {
            sqlSex = mainSql;
        }
        if (college.length() == 0 || college == "所有学院") {
            sqlCollege = mainSql;
        }
        if (sdept.length() == 0 || sdept == "所有专业") {
            sqlSdept = mainSql;
        }
        if (classNo.length() == 0 || classNo == "所有班级") {
            sqlClassNo = mainSql;
        }
        String sql = sqlSno + intersect + sqlSex + intersect + sqlCollege + intersect + sqlSdept + intersect + sqlClassNo;
        System.out.println(sql);
        ResultSet rs;
        rs = dao.execView(sql);
        try {
            while (rs.next()) {
                studentList.add(this.getStudentInfo(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return studentList;
    }

    public int updateStudentInfo(Student student) {
        String sql = "{CALL student_update(?,?,?,?,?,?,?)}";
        return dao.execUpdateProc(sql, student.getStudentId(), student.getStudentName(), student.getSex(), student.getBirth(),
                student.getErollDate(), student.getTelephone(), student.getClassNo());
    }

    public int deleteStudent(String studentId) {
        String sql = "{CALL stu_delete_p(?)}";
        return dao.execDeleteProc(sql, studentId);
    }

    public boolean isExist(Student student){
        String flagSql = "{CALL student_existed(?,?)}";
        int flag = dao.execIsExistedProc(flagSql, student.getStudentId());
        if (flag==1){
            return true;
        }
        return false;
    }

    public int insertStudent(Student student) {
        String flagSql = "{CALL student_existed(?,?)}";
        int flag = dao.execIsExistedProc(flagSql, student.getStudentId());
        int count = 0;
        if (flag == 1) {
            return -1;
        } else {
            String sql = "{CALL stu_insert(?,?,?,?,?,?,?)}";
            System.out.println(student.getStudentId() + student.getStudentName() + student.getSex() + student.getBirth() + student.getErollDate() + student.getTelephone() + student.getClassNo());
            count = dao.execUpdateProc(sql, student.getStudentId(), student.getStudentName(), student.getSex(), student.getBirth(),
                    student.getErollDate(), student.getTelephone(), student.getClassNo());
        }
        return count;
    }

    public static void main(String[] args) {

        List<Student> studentList = new StudentDao().getPartStudentInfo("何光宗", "女", "计算机学院", "", "");
        for (Student student : studentList) {
            System.out.println(student.getStudentId());
        }
    }
}
