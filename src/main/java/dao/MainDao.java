package dao;

import model.Course;
import model.ElectCourseInfo;
import model.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName MainDao.java
 * @Description TODO
 * @createTime 2021-06-26 01:06:23
 */
public class MainDao {
    protected SqlDao dao;

    public MainDao() {
        this.dao = new SqlDao();
    }

    protected List<Course> intersectCourse(List<Course> list1, List<Course> list2) {
        List<Course> result = list1.stream().filter(a ->
                list2.stream().map(Course::getCourseId)
                        .anyMatch(id ->
                                Objects.equals(a.getCourseId(), id))
        ).collect(Collectors.toList());
        return result;
    }

    protected List<ElectCourseInfo> intersectScoreInfo(List<ElectCourseInfo> list1, List<ElectCourseInfo> list2) {
        List<ElectCourseInfo> result = list1.stream().filter(a ->
                list2.stream().map(ElectCourseInfo::getCourseId)
                        .anyMatch(id ->
                                Objects.equals(a.getCourseId(), id))
        ).collect(Collectors.toList());
        return result;
    }

    protected List<Student> intersectStudentInfo(List<Student> list1, List<Student> list2) {
        List<Student> result = list1.stream().filter(a ->
                list2.stream().map(Student::getStudentId)
                        .anyMatch(id ->
                                Objects.equals(a.getStudentId(), id))
        ).collect(Collectors.toList());
        return result;
    }

    protected void listClear(List list) {
        if (!list.isEmpty()) {
            list.clear();
        }
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 22:05
     * @Param [text]
     * @Return java.lang.String
     */
    public String isIdOrName(String text) {
        if (text.length() == 0) {
            return "null";
        }
        String idRule = "^\\d{3}$";
        String nameRule = "^[A-Za-z\\u4e00-\\u9fa5]+$";
        if (text.matches(idRule)) {
            return "id";
        } else if (( text.matches(nameRule)) && text.length() > 1 && text.length() < 10) {
            return "name";
        }
        return "error";
    }

    public String userIsIdOrName(String text) {
        if (text.length() == 0) {
            return "null";
        }
        String idRule = "^\\d{8}$";
        String nameRule = "^[\\u4e00-\\u9fa5]+$";
        if (text.matches(idRule)) {
            return "id";
        } else if (text.matches(nameRule)  && text.length() > 0 && text.length() < 5) {
            return "name";
        }
        return "error";
    }

    public String classNoIsCorrect(String text) {
        if (text.length() == 0) {
            return "null";
        }
        String idRule = "^\\d{3}$";
        if (text.matches(idRule)) {
            return "id";
        }
        return "error";
    }

    public boolean phoneIsCorrect(String text){
        String rule="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        return text.matches(rule);
    }

    /**
     * 判断时间格式 格式必须为“YYYY-MM-dd”
     * 2004-2-30 是无效的
     * 2003-2-29 是无效的
     * @param isDate
     * @return
     */
    public boolean isLegalDate(String isDate) {
        int legalLen = 10;
        if ((isDate == null) || (isDate.length() != legalLen)) {
            return false;
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(isDate);
            return isDate.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }


}
