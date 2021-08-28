package model;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ElectCourseDao.java
 * @Description TODO
 * @createTime 2021-06-24 23:39:17
 */
public class ElectCourseInfo {
    private String studentId;
    private String courseId;
    private String studentName;
    private String courseName;
    private int period;
    private int credit;
    private String semester;
    private String collegeName;
    private String classNo;
    private int grade;


    public ElectCourseInfo() {
    }

    public ElectCourseInfo(String studentId, String courseId, String studentName, String courseName, int credit, String semester, String collegeName, int grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.credit = credit;
        this.semester = semester;
        this.collegeName = collegeName;
        this.grade = grade;
    }

    public ElectCourseInfo(String studentId, String courseId, String studentName, String courseName, int period, int credit, String semester, String collegeName, String classNo, int grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.period = period;
        this.credit = credit;
        this.semester = semester;
        this.collegeName = collegeName;
        this.classNo = classNo;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
}
