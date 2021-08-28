package dao;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Sheet;
import jxl.Workbook;
import model.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName FileDao.java
 * @Description TODO
 * @createTime 2021-07-07 22:19:17
 */
public class FileDao {
    private static final StudentDao studentDao = new StudentDao();


    public static File openFile() {
        Stage mainStage = null;
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        fileChooser.setTitle("学生信息文件选择");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("xls Files", "*.xls"));
        System.out.println(selectedFile.getPath());
        return selectedFile;
    }

    public static List readSpecifyRows(File file) throws Exception {
        List<Student> columnList = new ArrayList<Student>();
        List<Integer> errorList = new ArrayList<Integer>();
        Workbook readwb = null;
        InputStream io = new FileInputStream(file.getAbsoluteFile());
        readwb = Workbook.getWorkbook(io);
        Sheet readsheet = readwb.getSheet(0);
        int rsColumns = readsheet.getColumns();  //获取表格列数
        int rsRows = readsheet.getRows();  //获取表格行数
        for (int i = 0; i < rsRows; i++) {
            Student student = new Student(readsheet.getCell(0, i).getContents(), readsheet.getCell(1, i).getContents(),
                    readsheet.getCell(2, i).getContents(), readsheet.getCell(3, i).getContents(), 0,
                    readsheet.getCell(4, i).getContents(), readsheet.getCell(5, i).getContents(), readsheet.getCell(6, i).getContents()
                    , "", "");
            student.setBirth(getDate(student.getBirth()));
            student.setErollDate(getDate(student.getErollDate()));
            System.out.println(student.getBirth());
            if (studentInfoIsLegal(student)) {
                columnList.add(student);
            } else {
                errorList.add(i);
            }
        }
        for (Student student : columnList) {
            System.out.println(student.getStudentId() + " " + student.getStudentName() + " " + student.getSex() +
                    " " + student.getBirth() + " " + student.getErollDate() + " " + student.getTelephone() + " " + student.getClassNo());
        }
        StringBuffer stringBuffer = new StringBuffer("信息总数：共" + (errorList.size() + columnList.size()) + "条," +
                "可插入数量:" + columnList.size() + ",不可插入数量:" + errorList.size() + ",错误行：");
        for (Integer error : errorList) {
            stringBuffer.append((error + 1) + ",");
        }
        if (errorList.size() != 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }

        List result = new ArrayList();
        result.add(stringBuffer.toString());
        result.add(columnList);
        return result;

    }

    private static boolean studentInfoIsLegal(Student student) {
        return studentDao.userIsIdOrName(student.getStudentId()).equals("id") &&
                studentDao.userIsIdOrName(student.getStudentName()).equals("name") &&
                (student.getSex().equals("男") || student.getSex().equals("女")) &&
                studentDao.phoneIsCorrect(student.getTelephone()) &&
                studentDao.classNoIsCorrect(student.getClassNo()).equals("id") &&
                studentDao.isLegalDate(student.getBirth()) && studentDao.isLegalDate(student.getErollDate()) && !studentDao.isExist(student);
    }

    private static String getDate(String date) {
        if (date.length() == 0 || date == null) {
            return "";
        }
        SimpleDateFormat inFormatter = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat outFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -100);
            inFormatter.set2DigitYearStart(calendar.getTime());
            result = inFormatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outFormatter.format(result);
    }

    public static boolean insertStudent(List<Student> studentList) {
        int count = 0;
        for (Student student : studentList) {
            int flag = studentDao.insertStudent(student);
            if (flag != -1 || flag != 0) {
                count++;
            }
        }
        return (count == studentList.size()) && count != 0;
    }

}
