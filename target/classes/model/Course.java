package model;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName CourseDao.java
 * @Description TODO
 * @createTime 2021-06-22 00:11:51
 */
public class Course {
    private String courseId;
    private String courseName;
    private int period;
    private int credit;
    private String semester;
    private String courseCollege;
    private String courseTeacher;
    private int amount;
    private int electedAmount;

    public Course() {
    }
    public Course(String courseId){
        this.courseId=courseId;
    }

    public Course(String courseId, String courseName, int period, int credit, String semester, String courseCollege, String courseTeacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.period = period;
        this.credit = credit;
        this.semester = semester;
        this.courseCollege = courseCollege;
        this.courseTeacher = courseTeacher;
    }

    public Course(String courseId, String courseName, int period, int credit, String semester,
                  String courseCollege, String courseTeacher, int amount,int electedAmount) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.period = period;
        this.credit = credit;
        this.semester = semester;
        this.courseCollege = courseCollege;
        this.courseTeacher = courseTeacher;
        this.amount = amount;
        this.electedAmount=electedAmount;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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

    public String getCourseCollege() {
        return courseCollege;
    }

    public void setCourseCollege(String courseCollege) {
        this.courseCollege = courseCollege;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getElectedAmount() {
        return electedAmount;
    }

    public void setElectedAmount(int electedAmount) {
        this.electedAmount = electedAmount;
    }
}
