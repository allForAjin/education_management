package model;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName College.java
 * @Description TODO
 * @createTime 2021-06-24 17:55:20
 */
public class College {
    private String collegeId;
    private String collegeName;

    public College() {
    }

    public College(String collegeId, String collegeName) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
