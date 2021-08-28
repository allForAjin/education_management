package view.adminView.alertView.elect;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.StudentDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import manager.StageManager;
import model.Student;
import view.adminView.mainView.AdminController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ElcetCourseController.java
 * @Description TODO
 * @createTime 2021-07-08 10:29:50
 */
public class ElcetCourseController implements Initializable {
    private final AdminController controller = (AdminController) StageManager.
            CONTROLLER.get(AdminController.class.getSimpleName());

    private String studentId;
    private final StudentDao studentDao = new StudentDao();

    @FXML
    private JFXTextField studentIdField;
    @FXML
    private JFXTextField studentNameField;
    @FXML
    private JFXTextField courseIdField;
    @FXML
    private JFXTextField courseNameField;
    @FXML
    private JFXButton infoUpdateButton;
    @FXML
    private JFXButton clearButton;
    @FXML
    private JFXComboBox<String> semesterBox;
    @FXML
    private JFXComboBox<String> collegeBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentId = controller.getStudentId();
        this.infoDisplay();
    }

    private void infoDisplay() {
        Student student = studentDao.getPartStudentInfo(studentId).get(0);
        studentIdField.setText(student.getStudentId());
        studentNameField.setText(student.getStudentName());

        studentIdField.setFocusTraversable(false);
        studentNameField.setFocusTraversable(false);

        controller.courseComboxSetting(collegeBox, semesterBox, null);
    }
}
