package dao;

import model.ElectCourseCount;
import model.ElectCourseInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ElectCourseDao.java
 * @Description TODO
 * @createTime 2021-06-26 00:25:24
 */
public class ElectCourseDao extends MainDao {
    private List<ElectCourseInfo> electCourseInfoList;
    private List<ElectCourseCount> electCourseCountList;
//    private final SqlDao dao;

    public ElectCourseDao() {
        electCourseInfoList = new ArrayList<>();
        electCourseCountList = new ArrayList<>();
        dao = new SqlDao();
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:53
     * @Param [studentId, courseId]
     * @Return int
     */
    public int studentIsElectCourse(String studentId, String courseId) {
        String sql = "{CALL student_select_course(?,?,?)}";
        return dao.execIsExistedProc(sql, studentId, courseId);

    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:53
     * @Param [courseId]
     * @Return int
     */
    public int courseIsSelected(String courseId) {
        String sql = "{CALL course_SC_existed(?,?)}";
        return dao.execIsExistedProc(sql, courseId);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:53
     * @Param [studentId, courseId]
     * @Return int
     */
    public int selectCourse(String studentId, String courseId) {
        String sql = "{CALL sc_insert(?,?)}";
        return dao.execTwoConditionProc(sql, studentId, courseId);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:53
     * @Param [studentId]
     * @Return java.util.List<model.ElectCourseInfo>
     */
    public List<ElectCourseInfo> getAllScoreInfo(String studentId) {
        this.listClear(electCourseInfoList);
        ResultSet rsInfo = dao.execView("select Sno,Cno,Sname,Cname,Credit,Semester,Gname,Grade,Period from Student_score_info where Sno='" + studentId + "';");
        try {
            while (rsInfo.next()) {
                electCourseInfoList.add(this.getElectCourse(rsInfo));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return electCourseInfoList;
    }

    public List<ElectCourseInfo> getAllScoreInfo() {
        this.listClear(electCourseInfoList);
        ResultSet rsInfo = dao.execView("select * from Student_score_info");
        try {
            while (rsInfo.next()) {
                electCourseInfoList.add(this.getElectCourse(rsInfo));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return electCourseInfoList;
    }

    public ElectCourseInfo getPartScoreInfo(String studentId, String courseId) {
        String sql = "select * from Student_score_info where Sno='" + studentId + "' and Cno='" + courseId + "';";
        System.out.println(sql);
        ResultSet rs = dao.execView(sql);
        ElectCourseInfo electCourseInfo = null;
        try {
            while (rs.next()) {
                electCourseInfo = this.getElectCourse(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dao.closeConnection();
        return electCourseInfo;
    }

    public String socreCount(String student, String course, String college, String sdept, String classNo, String semester) {
        String tableSql = this.getSql(student, course, college, sdept, classNo, semester);
        String mainSql = "select count(*),cast(cast(sum(case when Grade between 90 and 100 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as '优'," +
                "cast(cast(sum(case when Grade between 80 and 89 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as'良'," +
                "cast(cast(sum(case when Grade between 70 and 79 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as'中等'," +
                "cast(cast(sum(case when Grade between 60 and 69 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as '及格'," +
                "cast(cast(sum(case when Grade <60 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as'不及格'," +
                "sum(case when Grade between 90 and 100 then 1 else 0 end) as '优人数'," +
                "sum(case when Grade between 80 and 89 then 1 else 0 end) as '良人数'," +
                "sum(case when Grade between 70 and 79 then 1 else 0 end) as '中等人数'," +
                "sum(case when Grade between 60 and 69 then 1 else 0 end) as '及格人数'," +
                "sum(case when Grade <60 then 1 else 0 end) as '不及格人数' from (" + tableSql + ") as a;";
        ResultSet rs = dao.execView(mainSql);
        String result="  当前页面共";

        try {
            while (rs.next() && rs != null) {
                result=result+rs.getString(1)+"条选课记录\n  优秀比例"+rs.getString(2)+"，良比例"+rs.getString(3)+
                        ",中比例"+rs.getString(4)+",及格比例"+rs.getString(5)+",不及格比例"+rs.getString(6)+
                        "\n  优秀人数"+rs.getString(7)+ "人,良人数"+rs.getString(8)+"人,中人数"+rs.getString(9)+
                        "人,及格人数"+rs.getString(10)+ "人,不及格人数"+rs.getString(11)+"人";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;

    }

    private String getSql(String student, String course, String college, String sdept, String classNo, String semester) {
        if (this.userIsIdOrName(student) == "null") {
            student = "";
        }
        if (this.isIdOrName(course) == "null") {
            course = "";
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
        if (semester == null) {
            semester = "";
        }

        String mainSql = "select * from Student_score_info ";
        String sqlSno = mainSql + "where Sno='" + student + "'";
        String sqlCno = mainSql + "where Cno='" + course + "'";
        String sqlCollege = mainSql + "where Class_no in(select Class_no from class_sdept_college" +
                " where Gname='" + college + "')";
        String sqlSdept = mainSql + "where Class_no in (select Class_no from class_sdept_college" +
                " where Dname='" + sdept + "')";
        String sqlClassNo = mainSql + "where Class_no='" + classNo + "'";
        String sqlSemester = mainSql + "where Semester='" + semester + "'";
        String intersect = " intersect ";
        if (student == "" && course == "" && college == "" && sdept == "" && classNo == "" && semester == "") {
            return mainSql;
        }
        if (this.isIdOrName(course) == "name") {
            sqlCno = mainSql + "where Cname='" + course + "'";
        }
        if (this.userIsIdOrName(student) == "name") {
            sqlSno = mainSql + "where Sname='" + student + "'";
        }
        if (student.length() == 0) {
            sqlSno = mainSql;
        }
        if (course.length() == 0) {
            sqlCno = mainSql;
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
        if (semester.length() == 0 || semester == "所有学期") {
            sqlSemester = mainSql;
        }
        String sql = sqlSno + intersect + sqlCno + intersect + sqlCollege + intersect + sqlSdept + intersect + sqlClassNo + intersect + sqlSemester;
        System.out.println(sql);
        return sql;
    }


    public List<ElectCourseInfo> getPartScoreInfo(String student, String course, String college, String sdept, String classNo, String semester) {
        this.listClear(electCourseInfoList);
        String sql = this.getSql(student, course, college, sdept, classNo, semester);
        ResultSet rs = dao.execView(sql);
        try {
            while (rs.next()) {
                electCourseInfoList.add(this.getElectCourse(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return electCourseInfoList;
    }


    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:53
     * @Param [studentId, ID_OR_NAME, college, semester]
     * @Return java.util.List<model.ElectCourseInfo>
     */
    public List<ElectCourseInfo> getPartScoreInfo(String studentId, String ID_OR_NAME, String college, String semester) {
        this.listClear(electCourseInfoList);

        if (this.isIdOrName(ID_OR_NAME).equals("null")) {
            ID_OR_NAME = "";
        }
        if (college == null) {
            college = "";
        }
        if (semester == null) {
            semester = "";
        }

        if (ID_OR_NAME == "" && college == "" && semester == "") {
            return this.getAllScoreInfo(studentId);
        }

        String sqlCno = "select * from Student_score_info where Cno='" + ID_OR_NAME + "' and Sno='" + studentId + "';";
        String sqlCollege = "select * from Student_score_info where Gname='" + college + "' and Sno='" + studentId + "';";
        String sqlSemester = "select * from Student_score_info where Semester='" + semester + "' and Sno='" + studentId + "';";

        if (this.isIdOrName(ID_OR_NAME) == "name") {
            sqlCno = "select * from Student_score_info where Cname='" + ID_OR_NAME + "';";
        }

        if (ID_OR_NAME == "") {
            sqlCno = "select * from Student_score_info where Sno='" + studentId + "';";
        }
        if (college == "" || college == "所有学院") {
            sqlCollege = "select * from Student_score_info where Sno='" + studentId + "';";
        }
        if (semester == "" || semester == "所有学期") {
            sqlSemester = "select * from Student_score_info where Sno='" + studentId + "';";
        }

        List<ElectCourseInfo> cnoSet = new ArrayList<>();
        List<ElectCourseInfo> collegeSet = new ArrayList<>();
        List<ElectCourseInfo> semesterSet = new ArrayList<>();

        try {
            System.out.println(sqlCno);
            ResultSet rs = dao.execView(sqlCno);
            while (rs.next()) {
                cnoSet.add(this.getElectCourse(rs));
            }
            System.out.println(sqlCollege);
            rs = dao.execView(sqlCollege);
            while (rs.next()) {
                collegeSet.add(this.getElectCourse(rs));
            }
            System.out.println(sqlSemester);
            rs = dao.execView(sqlSemester);
            while (rs.next()) {
                semesterSet.add(this.getElectCourse(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return this.intersectScoreInfo(this.intersectScoreInfo(cnoSet, collegeSet), semesterSet);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:53
     * @Param [rs]
     * @Return model.ElectCourseInfo
     */
    private ElectCourseInfo getElectCourse(ResultSet rs) {
        String studentId = "";
        String courseId = "";
        String studentName = "";
        String courseName = "";
        int credit = 0;
        String semester = "";
        String collegeName = "";
        int grade = 0;
        int period = 0;
        String classNo = "";
        try {
            studentId = rs.getString(1);
            courseId = rs.getString(2);
            studentName = rs.getString(3);
            courseName = rs.getString(4);
            credit = rs.getInt(5);
            semester = rs.getString(6);
            collegeName = rs.getString(7);
            grade = rs.getInt(8);
            period = rs.getInt(9);
            classNo = rs.getString(10);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ElectCourseInfo(studentId, courseId, studentName, courseName, period, credit, semester, collegeName, classNo, grade);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:53
     * @Param [studentId]
     * @Return java.util.List<model.ElectCourseCount>
     */
    public List<ElectCourseCount> getAllScoreCount(String studentId) {
        this.listClear(electCourseCountList);
        ResultSet rsCountScore = dao.execView("select Sno,Sname,count(Sno),sum(Grade),avg(Grade) " +
                "from Student_score_info where Sno='" + studentId + "' group by Sno,Sname;");
        try {
            while (rsCountScore.next()) {
                electCourseCountList.add(this.getScoreCount(rsCountScore));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return electCourseCountList;
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:53
     * @Param [studentId, semester]
     * @Return java.util.List<model.ElectCourseCount>
     */
    public List<ElectCourseCount> getPartScoreCount(String studentId, String semester) {
        this.listClear(electCourseCountList);
        if (semester == "所有学期" || semester == null) {
            return this.getAllScoreCount(studentId);
        }
        ResultSet rsCountScore = dao.execView("select Sno,Sname,count(Sno),sum(Grade),avg(Grade),Semester " +
                "from Student_score_info where Sno='" + studentId + "' and Semester='" + semester + "' group by Sno,Sname,Semester;");
        try {
            while (rsCountScore.next()) {
                electCourseCountList.add(this.getScoreCount(rsCountScore));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return electCourseCountList;
    }


    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 11:53
     * @Param [rs]
     * @Return model.ElectCourseCount
     */
    private ElectCourseCount getScoreCount(ResultSet rs) {
        int columnCount = 0;
        String studentId = "";
        String studentName = "";
        String semester = "";
        int courseAmount = 0;
        int sumGrade = 0;
        double averageGrade = 0;

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();

            studentId = rs.getString(1);
            studentName = rs.getString(2);
            courseAmount = rs.getInt(3);
            sumGrade = rs.getInt(4);
            averageGrade = rs.getInt(5);
            if (columnCount == 5) {
                semester = "所有学期";
            } else if (columnCount == 6) {
                semester = rs.getString(6);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ElectCourseCount(studentId, studentName, semester, courseAmount, sumGrade, averageGrade);
    }

    public List<String> getAllClass() {
        String sql = "select Class_no from class_sdept_college";
        ResultSet rs = dao.execView(sql);
        List<String> classList = new ArrayList<>();
        try {
            while (rs.next()) {
                classList.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return classList;
    }


    public List<String> getClassByCollege(String collegeName) {
        String sql = "select Class_no from class_sdept_college where Gname='" + collegeName + "';";
        ResultSet rs = dao.execView(sql);
        List<String> classList = new ArrayList<>();
        try {
            while (rs.next()) {
                classList.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return classList;
    }

    public List<String> getClassBySdept(String sdept) {
        String sql = "select Class_no from class_sdept_college where Dname='" + sdept + "';";
        ResultSet rs = dao.execView(sql);
        List<String> classList = new ArrayList<>();
        try {
            while (rs.next()) {
                classList.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return classList;
    }

    public int cancelElect(String studentId, String courseId) {
        String sql = "delete from SC where Sno=? and Cno=?;";
        PreparedStatement ps = dao.execUpdate(sql);
        int count = 0;
        try {
            ps.setString(1, studentId);
            ps.setString(2, courseId);
            count = ps.executeUpdate();
            System.out.println(count + "条");
        } catch (SQLException throwables) {
            System.out.println("更新失败");
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return count;
    }

    public int updateScore(String studentId, String courseId, int score) {
        String sql = "update SC set Grade=? where Sno=? and Cno=?";
        PreparedStatement ps = dao.execUpdate(sql);
        int count = 0;
        try {
            ps.setInt(1, score);
            ps.setString(2, studentId);
            ps.setString(3, courseId);
            count = ps.executeUpdate();
            System.out.println(count + "条");
        } catch (SQLException throwables) {
            System.out.println("更新失败");
            throwables.printStackTrace();
        }
        dao.closeConnection();
        return count;
    }


    public static void main(String[] args) {
//        System.out.println(new ElectCourseDao().getPartScoreInfo("20161432", "", "计算机学院", "").get(0).getCourseId());
//        System.out.println(new ElectCourseDao().courseIsSelected("001"));
        //System.out.println("qqq"+new ElectCourseDao().electedAmount("001"));
        System.out.println(new ElectCourseDao().cancelElect("20161432", "003"));
    }
}
