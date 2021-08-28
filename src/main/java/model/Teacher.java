package model;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Teacher.java
 * @Description TODO
 * @createTime 2021-06-30 18:41:47
 */
public class Teacher {
    private String teacherId;
    private String teacherName;
    private String sex;
    private String title;
    private String birth;
    private String entryDate;
    private String telephone;
    private String office;
    private String sdeptName;
    private String collegeName;
    private int age;

    public Teacher() {
    }

    public Teacher(String teacherId, String teacherName, String sex, String title,
                   String birth, String entryDate, String telephone, String office,
                   String sdeptName, String collegeName,int age) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.sex = sex;
        this.title = title;
        this.birth = birth;
        this.entryDate = entryDate;
        this.telephone = telephone;
        this.office = office;
        this.sdeptName = sdeptName;
        this.collegeName = collegeName;
        this.age=age;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getSdeptName() {
        return sdeptName;
    }

    public void setSdeptName(String sdeptName) {
        this.sdeptName = sdeptName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
