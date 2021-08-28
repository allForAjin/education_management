package view.studentView.alertView;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import dao.StudentDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.StageManager;
import model.Student;
import view.studentView.mainView.StudentController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName StudentInfoAlterController.java
 * @Description TODO
 * @createTime 2021-06-27 19:17:16
 */
public class StudentInfoAlterController implements Initializable {
    @FXML
    public BorderPane mainPane;
    @FXML
    public JFXTextField studentIdField;
    @FXML
    public JFXTextField nameField;
    @FXML
    public JFXRadioButton male;
    @FXML
    public ToggleGroup sexRadioButton;
    @FXML
    public JFXRadioButton female;
    @FXML
    public JFXDatePicker birth;
    @FXML
    public JFXTextField erollDateField;
    @FXML
    public JFXTextField telephone;
    @FXML
    public JFXButton infoUpdateButton;
    @FXML
    public JFXButton clearButton;
    @FXML
    public JFXDialog confirmUpdateDialog;
    @FXML
    public JFXDialogLayout confirmUpdateDialogLayout;
    @FXML
    public JFXButton concelUpdateButton;
    @FXML
    public JFXButton confirmUpdateButton;
    @FXML
    public JFXDialog successDialog;
    @FXML
    public JFXDialogLayout successDialogLayout;
    @FXML
    public JFXButton successButton;
    @FXML
    public JFXDialog failDialog;
    @FXML
    public JFXDialogLayout failDialogLayout;
    @FXML
    public JFXButton failButton;
    @FXML
    public JFXDialog infoEmptyDialog;
    @FXML
    public JFXDialogLayout infoEmptyDialogLayout;
    @FXML
    public Label infoEmptyLabel;
    @FXML
    public JFXButton infoEmptyButton;

    private static final String studentId = StudentController.getStudentId();
    private final StudentDao studentDao = new StudentDao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(studentId);
        this.infoDisplay();
        this.updateStudentInfo();
    }

    /**
     * @author lmk
     * @Description //TODO 显示学生信息
     * @Date 2021/6/28 17:01
     * @Param []
     * @Return void
     */
    private void infoDisplay() {
        Student student = studentDao.getPartStudentInfo(studentId).get(0);
        studentIdField.setText(student.getStudentId());
        nameField.setText(student.getStudentName());

        birth.setValue(LocalDate.parse(student.getBirth()));
        erollDateField.setText(student.getErollDate());
        telephone.setText(student.getTelephone());

        studentIdField.setFocusTraversable(false);
        nameField.setFocusTraversable(false);
        male.setFocusTraversable(false);
        female.setFocusTraversable(false);
        birth.setFocusTraversable(false);
        erollDateField.setFocusTraversable(false);
        telephone.setFocusTraversable(false);

        student.setSex(String.valueOf(student.getSex().charAt(0)));
        System.out.println(student.getSex().equals("女"));

        if (student.getSex().equals("男")) {
            male.setSelected(true);
        } else if (student.getSex().equals("女")) {
            female.setSelected(true);
        }
    }

    /**
     * @author lmk
     * @Description //TODO 确认修改按钮
     * @Date 2021/6/28 17:02
     * @Param []
     * @Return void
     */
    public void updateStudentInfo() {
        infoUpdateButton.setOnAction(action -> {
            if (infoIsEmpty()) {
                infoEmptyEmptyDialog();
                infoDisplay();
                return;
            }
            updateDialogAlert();
        });
        clearButton.setOnAction(action -> {
            cleanField();
        });
    }

    /**
     * @author lmk
     * @Description //TODO 确认修改提示框
     * @Date 2021/6/28 17:02
     * @Param []
     * @Return void
     */
    private void updateDialogAlert() {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(confirmUpdateDialogLayout);

        alert.initModality(Modality.NONE);
        alert.show();
        concelUpdateButton.setOnMouseClicked(action -> {
            alert.close();
        });
        confirmUpdateButton.setOnMouseClicked(action -> {

            int count = studentDao.updateStudentInfo(this.getInfo());
            if (count == 0) {
                //update success
                this.successOrFailDialogAlert(failDialogLayout, failButton);
            } else {
                //fail
                this.successOrFailDialogAlert(successDialogLayout, successButton);
            }

            alert.close();
        });
    }

    private void infoEmptyEmptyDialog() {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(infoEmptyDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();
        infoEmptyButton.setOnAction(action -> {
            alert.close();
        });
    }

    private boolean infoIsEmpty() {
        return nameField.getText() == null || birth.getValue() == null || telephone.getText() == null;
    }


    /**
     * @author lmk
     * @Description //TODO 修改成功或失败对话框
     * @Date 2021/6/28 17:04
     * @Param [dialogLayout, button]
     * @Return void
     */
    private void successOrFailDialogAlert(JFXDialogLayout dialogLayout, JFXButton button) {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(dialogLayout);

        alert.initModality(Modality.NONE);
        alert.show();
        button.setOnMouseClicked(action -> {
            if (dialogLayout.getId().equals("failDialogLayout")) {

            } else {
                StudentController studentController=(StudentController) StageManager.CONTROLLER.get(StudentController.class.getSimpleName());
                studentController.studentInfoSetting();
                stage.close();

            }
            alert.close();
        });
    }

    /**
     * @author lmk
     * @Description //TODO 获取确认修改后的信息
     * @Date 2021/6/28 17:02
     * @Param []
     * @Return model.Student
     */
    private Student getInfo() {
        String studentId = studentIdField.getText();
        String studentName = nameField.getText();
        String sex = "";
        String studentBirth = birth.getValue().toString();
        String erollDate = erollDateField.getText();
        String phone = telephone.getText();

        if (male.isSelected()) {
            sex = "男";
        } else {
            sex = "女";
        }
        System.out.println(sex);
        return new Student(studentId, studentName, sex, studentBirth, 0, erollDate, phone, "", "", "");
    }

    private void cleanField() {
        nameField.setText("");
        birth.setValue(null);
        //erollDateField.setText("");
        telephone.setText("");
    }


}
