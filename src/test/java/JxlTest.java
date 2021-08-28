import jxl.Sheet;
import jxl.Workbook;
import model.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName JxlTest.java
 * @Description TODO
 * @createTime 2021-07-07 13:22:35
 */
public class JxlTest {
    public static void readSpecifyRows(File file) throws Exception {
        ArrayList<Student> columnList = new ArrayList<Student>();
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
            columnList.add(student);
        }
        for (Student student:columnList){
            System.out.println(student.getStudentId()+" "+student.getStudentName()+" "+student.getSex()+
                    " "+student.getBirth()+" "+student.getErollDate()+" "+student.getTelephone()+" "+student.getClassNo());
        }

        //System.out.println(columnList);
    }

    public static void main(String[] args) throws Exception {
        readSpecifyRows(new File("E:\\Idea JavaWorkspace\\Edu Manage System\\src\\test\\student.xls"));
    }


}
