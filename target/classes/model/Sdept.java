package model;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Sdept.java
 * @Description TODO
 * @createTime 2021-06-29 00:10:11
 */
public class Sdept {
    private String sdeptNo;
    private String sdeptName;

    public Sdept() {
    }

    public Sdept(String sdeptNo, String sdeptName) {
        this.sdeptNo = sdeptNo;
        this.sdeptName = sdeptName;
    }

    public String getSdeptNo() {
        return sdeptNo;
    }

    public void setSdeptNo(String sdeptNo) {
        this.sdeptNo = sdeptNo;
    }

    public String getSdeptName() {
        return sdeptName;
    }

    public void setSdeptName(String sdeptName) {
        this.sdeptName = sdeptName;
    }
}
