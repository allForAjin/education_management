package dao;

import model.Course;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName CourseDao.java
 * @Description TODO
 * @createTime 2021-06-22 18:28:09
 */
public class CourseDao extends MainDao {
    private List<Course> courseList;
    private ElectCourseDao electCourseDao;

    public CourseDao() {
        super();
        courseList = new ArrayList<>();
        electCourseDao = new ElectCourseDao();
        dao = new SqlDao();
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 、
     * @Param []
     * @Return java.util.List<model.Course>
     */
    public List<Course> getAllCourseInfo() {
        this.listClear(courseList);
        ResultSet rs = dao.execView("select Cno,Cname,Period,Credit,Semester,Gname,Tname,Amount,elect_amount from Course_info");
        try {
            while (rs.next()) {
                courseList.add(this.getCourse(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();

        return courseList;
    }

    public List<Course> getPartCourseInfo(String courseId) {
        this.listClear(courseList);
        ResultSet rs = dao.execView("select Cno,Cname,Period,Credit,Semester,Gname,Tname,Amount,elect_amount from Course_info where Cno='" + courseId + "';");
        try {
            while (rs.next()) {
                courseList.add(this.getCourse(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return courseList;
    }


    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 22:05
     * @Param [rs]
     * @Return model.Course
     */
    private Course getCourse(ResultSet rs) {
        String courseId = "";
        String courseName = "";
        int period = 0;
        int credit = 0;
        String semester1 = "";
        String courseCollege = "";
        String courseTeacher = "";
        int amount = 0;
        int elect = 0;
        try {
            courseId = rs.getString(1);
            courseName = rs.getString(2);
            period = rs.getInt(3);
            credit = rs.getInt(4);
            semester1 = rs.getString(5);
            courseCollege = rs.getString(6);
            courseTeacher = rs.getString(7);
            amount = rs.getInt(8);
            elect = rs.getInt(9);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (courseTeacher == null) {
            courseTeacher = "暂无授课老师";
        }
        return new Course(courseId, courseName, period, credit, semester1, courseCollege, courseTeacher, amount, elect);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 22:04
     * @Param [ID_OR_NAME, college, semester]
     * @Return java.util.List<model.Course>
     */
    public List<Course> getPartCourseInfo(String ID_OR_NAME, String college, String semester, String teacher) {
        if (ID_OR_NAME == "" && college == "" && semester == "" && teacher == "") {
            return this.getAllCourseInfo();
        }
        String mainSql = "select Cno,Cname,Period,Credit,Semester,Gname,Tname,amount,elect_amount from Course_info ";
        String sql = this.getSql(mainSql, ID_OR_NAME, college, semester, teacher);
        List<Course> list = new ArrayList<>();
        try {
            ResultSet rs;
            rs = dao.execView(sql);
            while (rs.next()) {
                list.add(this.getCourse(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return list;
    }



    private String getSql(String mainSql, String ID_OR_NAME, String college, String semester, String teacher) {
        if (this.isIdOrName(ID_OR_NAME) == "null") {
            ID_OR_NAME = "";
        }
        if (college == null) {
            college = "";
        }
        if (semester == null) {
            semester = "";
        }
        if (teacher == null) {
            teacher = "";
        }

        String sqlCno = mainSql + "where Cno='" + ID_OR_NAME + "'";
        String sqlCollege = mainSql + "where Gname='" + college + "'";
        String sqlSemester = mainSql + "where Semester='" + semester + "'";
        String sqlTeacher = mainSql + "where Tname='" + teacher + "';";
        String intersect = " intersect ";

        if (this.isIdOrName(ID_OR_NAME) == "name") {
            sqlCno = mainSql + "where Cname='" + ID_OR_NAME + "'";
        }
        if (ID_OR_NAME.length() == 0) {
            sqlCno = mainSql;
        }
        if (college.length() == 0 || college == "所有学院") {
            sqlCollege = mainSql;
        }
        if (semester.length() == 0 || semester == "所有学期") {
            sqlSemester = mainSql;
        }
        if (teacher.length() == 0 || teacher == "所有教师") {
            sqlTeacher = mainSql;
        }

        String sql = sqlCno + intersect + sqlCollege + intersect + sqlSemester + intersect + sqlTeacher;
        System.out.println(sql);
        return sql;
    }


    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 22:07
     * @Param [studentId]
     * @Return java.util.List<model.Course>
     */
    public List<Course> getAllSelectedCourseInfo(String studentId) {
        this.listClear(courseList);
        String sql = "select * from student_course_selected where Sno='" + studentId + "';";
        System.out.println(sql);
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                courseList.add(this.getCourse(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return courseList;
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 22:08
     * @Param [studentId, ID_OR_NAME, college, semester]
     * @Return java.util.List<model.Course>
     */
    public List<Course> getPartSelectedCourseInfo(String studentId, String ID_OR_NAME, String college, String semester, String teacher) {
        final List<Course> allCourse = this.getPartCourseInfo(ID_OR_NAME, college, semester, teacher);
        final List<Course> selectedCourse = this.getAllSelectedCourseInfo(studentId);
        return this.intersectCourse(allCourse, selectedCourse);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 22:08
     * @Param [studentId]
     * @Return java.util.List<model.Course>
     */
    public List<Course> getAllNoSelectedCourseInfo(String studentId) {
        this.listClear(courseList);
        String sql = "select * from Course_info where Cno not in(select Cno from student_course_selected where Sno='" + studentId + "') ";
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                courseList.add(this.getCourse(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return courseList;
    }

    public List<Course> getPartNoSelectedCourseInfo(String studentId, String ID_OR_NAME, String college, String semester, String teacher) {
        List<Course> allCourse = this.getPartCourseInfo(ID_OR_NAME, college, semester, teacher);
        List<Course> selectedCourse = this.getAllNoSelectedCourseInfo(studentId);
        return this.intersectCourse(allCourse, selectedCourse);
    }

    public boolean courseSelectedAmountIsFull(String courseId) {
        String sql = "select count(Sno) from SC where Cno='" + courseId + "';";
        ResultSet rs = dao.execView(sql);
        int selectedAmount = 0;
        int courseAmount = 0;
        try {
            while (rs.next()) {
                selectedAmount = rs.getInt(1);
            }
            sql = "select Amount from Course where Cno='" + courseId + "';";
            rs = dao.execView(sql);
            while (rs.next()) {
                courseAmount = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();

        if (selectedAmount == courseAmount) {
            return true;
        }
        return false;

    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:55
     * @Param [courseId]
     * @Return int
     */
    public int deleteCourse(String courseId) {
        String sql = "{CALL course_delete_p(?)}";
        return dao.execDeleteProc(sql, courseId);
    }

    public int updateCourseInfo(Course course) {
        String sql = "update Course set Cno=?,Cname=?,Period=?,Credit=?,Semester=?,Amount=? where Cno=?";
        PreparedStatement ps = dao.execUpdate(sql);
        int count = 0;
        try {
            ps.setString(1, course.getCourseId());
            ps.setString(2, course.getCourseName());
            ps.setInt(3, course.getPeriod());
            ps.setInt(4, course.getCredit());
            ps.setString(5, course.getSemester());
            ps.setInt(6, course.getAmount());
            ps.setString(7, course.getCourseId());
            count = ps.executeUpdate();
            System.out.println(count + "条");
        } catch (SQLException throwables) {
            System.out.println("更新失败");
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return count;
    }

    public int insertCourse(Course course) {
        String flagSql = "{CALL course_existed(?,?)}";
        int flag = dao.execIsExistedProc(flagSql, course.getCourseId());
        int count = 0;
        if (flag == 1) {
            return -1;
        } else {
            course.setCourseCollege(new CollegeDao().getCollegeId(course.getCourseCollege()));
            String sql = "{CALL course_insert(?,?,?,?,?,?,?)}";
            count = dao.execUpdateCourseProc(sql,course.getPeriod(),course.getCredit()
                    ,course.getAmount(),course.getCourseId(),course.getCourseName(),course.getSemester(),
                    course.getCourseCollege());
        }
        return count;
    }


    public static void main(String[] args) {
    }
}
