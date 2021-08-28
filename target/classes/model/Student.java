package model;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Student.java
 * @Description TODO
 * @createTime 2021-06-22 00:11:10
 */
public class Student {
    private String studentId;
    private String studentName;
    private String sex;
    private String birth;
    private int age;
    private String erollDate;
    private String telephone;
    private String classNo;
    private String SdeptName;
    private String collegeName;


    public Student() {
    }

    public Student(String studentId, String studentName, String sex, String birth,
                   int age, String erollDate, String telephone, String classNo,
                   String sdeptName, String collegeName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.birth = birth;
        this.age = age;
        this.erollDate = erollDate;
        this.telephone = telephone;
        this.classNo = classNo;
        SdeptName = sdeptName;
        this.collegeName = collegeName;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getErollDate() {
        return erollDate;
    }

    public void setErollDate(String erollDate) {
        this.erollDate = erollDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getSdeptName() {
        return SdeptName;
    }

    public void setSdeptName(String sdeptName) {
        SdeptName = sdeptName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
