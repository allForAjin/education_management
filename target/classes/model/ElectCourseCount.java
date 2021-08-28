package model;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ElectCourseCount.java
 * @Description TODO
 * @createTime 2021-06-27 00:37:02
 */
public class ElectCourseCount {
    private String studentId;
    private String studentName;
    private String semester;
    private int courseAmount;
    private int sumGrade;
    private double averageGrade;

    public ElectCourseCount() {
    }

    public ElectCourseCount(String studentId, String studentName, String semester, int courseAmount, int sumGrade, double averageGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.semester = semester;
        this.courseAmount = courseAmount;
        this.sumGrade = sumGrade;
        this.averageGrade = averageGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getCourseAmount() {
        return courseAmount;
    }

    public void setCourseAmount(int courseAmount) {
        this.courseAmount = courseAmount;
    }

    public int getSumGrade() {
        return sumGrade;
    }

    public void setSumGrade(int sumGrade) {
        this.sumGrade = sumGrade;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
