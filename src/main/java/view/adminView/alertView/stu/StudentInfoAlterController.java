package view.adminView.alertView.stu;

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
import view.adminView.mainView.AdminController;

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
    private BorderPane mainPane;
    @FXML
    private JFXTextField studentIdField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXRadioButton male;
    @FXML
    private ToggleGroup sexRadioButton;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXDatePicker birth;
    @FXML
    private JFXDatePicker erollDate;
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXButton infoUpdateButton;
    @FXML
    private JFXButton clearButton;
    @FXML
    private JFXDialog confirmUpdateDialog;
    @FXML
    private JFXDialogLayout confirmUpdateDialogLayout;
    @FXML
    private JFXButton concelUpdateButton;
    @FXML
    private JFXButton confirmUpdateButton;
    @FXML
    private JFXDialog successDialog;
    @FXML
    private JFXDialogLayout successDialogLayout;
    @FXML
    private JFXButton successButton;
    @FXML
    private JFXDialog failDialog;
    @FXML
    private JFXDialogLayout failDialogLayout;
    @FXML
    private JFXButton failButton;
    @FXML
    private JFXDialog infoEmptyDialog;
    @FXML
    private JFXDialogLayout infoEmptyDialogLayout;
    @FXML
    private Label infoEmptyLabel;
    @FXML
    private JFXButton infoEmptyButton;
    @FXML
    private JFXDialogLayout setEmptyDialogLayout;
    @FXML
    private JFXButton cancelSetEmptyButton;
    @FXML
    private JFXButton confirmSetEmptyButton;
    @FXML
    private JFXComboBox<String> studentCollegeBox;
    @FXML
    private JFXComboBox<String> studentSdeptBox;
    @FXML
    private JFXComboBox<String> studentClassBox;
    @FXML
    private JFXDialogLayout existDialogLayout;
    @FXML
    private JFXButton existButton;


    private final AdminController controller = (AdminController) StageManager.
            CONTROLLER.get(AdminController.class.getSimpleName());
    private String studentId;
    private final StudentDao studentDao = new StudentDao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            studentId = controller.getStudentId();
        } catch (NullPointerException e) {
            System.out.println("增加学生");
        }
        controller.studentAndTeacherComboBoxSetting(studentCollegeBox, studentSdeptBox, null);
        controller.classComboBoxSetting(studentClassBox);
        studentCollegeBox.getItems().remove(0);
        studentSdeptBox.getItems().remove(0);
        studentClassBox.getItems().remove(0);
        controller.studentBoxListener(null, studentCollegeBox, studentSdeptBox, studentClassBox, null);
        if (controller.getOperation().equals("updateStudent") && studentId != null) {
            this.infoDisplay();
        } else {
            studentIdField.setEditable(true);
        }
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
        try{
            Student student = studentDao.getPartStudentInfo(studentId).get(0);
            studentIdField.setText(student.getStudentId());
            nameField.setText(student.getStudentName());
            birth.setValue(LocalDate.parse(student.getBirth()));
            erollDate.setValue(LocalDate.parse(student.getErollDate()));
            telephone.setText(student.getTelephone());
            studentCollegeBox.getSelectionModel().select(student.getCollegeName());
            studentSdeptBox.getSelectionModel().select(student.getSdeptName());
            studentClassBox.getSelectionModel().select(student.getClassNo());
            student.setSex(String.valueOf(student.getSex().charAt(0)));
            if (student.getSex().equals("男")) {
                male.setSelected(true);
            } else if (student.getSex().equals("女")) {
                female.setSelected(true);
            }
        }catch (Exception e){
            System.out.println("学生插入，无infoDisplay");
        }

        studentIdField.setFocusTraversable(false);
        nameField.setFocusTraversable(false);
        male.setFocusTraversable(false);
        female.setFocusTraversable(false);
        birth.setFocusTraversable(false);
        erollDate.setFocusTraversable(false);
        telephone.setFocusTraversable(false);


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
            infoEmptyLabel.setText("信息不能为空！");
            if (!studentDao.userIsIdOrName(studentIdField.getText()).equals("id")){
                infoEmptyLabel.setText("学号格式错误！");
                infoEmptyDialog();
                infoDisplay();
                return;
            }
            if (!isName()) {
                infoEmptyLabel.setText("姓名格式错误！");
                infoEmptyDialog();
                infoDisplay();
                return;
            }
            if (!studentDao.phoneIsCorrect(telephone.getText())) {
                infoEmptyLabel.setText("电话格式错误！");
                infoEmptyDialog();
                infoDisplay();
                return;
            }
            if (infoIsEmpty()) {
                infoEmptyDialog();
                infoDisplay();
                return;
            }

            updateDialogAlert();
        });
        clearButton.setOnAction(action -> {
            setEmptyDialog();
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
            int count = 0;
            if (controller.getOperation().equals("updateStudent")) {
                System.out.println("update");
                count = studentDao.updateStudentInfo(this.getInfo());
            } else {
                System.out.println("insert");
                count = studentDao.insertStudent(this.getInfo());
            }
            if (count == 0) {
                //update fail
                this.successOrFailDialogAlert(failDialogLayout, failButton);
            } else if (count == 1) {
                //success
                this.successOrFailDialogAlert(successDialogLayout, successButton);
            } else if (count == -1) {
                studentExistDialog();
            }

            alert.close();
        });
    }

    private void infoEmptyDialog() {
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
        return studentIdField.getText().length() == 0 || nameField.getText().length() == 0 ||
                erollDate.getValue() == null || birth.getValue() == null || studentClassBox.getSelectionModel().getSelectedItem() == null
                || telephone.getText().length() == 0;
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
            if (dialogLayout.getId().equals("successDialogLayout")) {
                AdminController adminController = (AdminController) StageManager.CONTROLLER.get(AdminController.class.getSimpleName());
                adminController.studentTableItemSetting();
                controller.setSelectedStudentId(null);
                controller.setOperation("");
                stage.close();
            }
            alert.close();
        });
    }

    private void setEmptyDialog() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(setEmptyDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();
        cancelSetEmptyButton.setOnAction(action -> {
            alert.close();
        });
        confirmSetEmptyButton.setOnAction(action -> {
            cleanField();
            alert.close();
        });
    }

    private void studentExistDialog() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(existDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();
        existButton.setOnAction(action -> {
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
        String erollDate = this.erollDate.getValue().toString();
        String phone = telephone.getText();
        String classNo = studentClassBox.getSelectionModel().getSelectedItem();

        if (male.isSelected()) {
            sex = "男";
        } else {
            sex = "女";
        }
        System.out.println(sex);
        return new Student(studentId, studentName, sex, studentBirth, 0, erollDate, phone, classNo, "", "");
    }

    private void cleanField() {
        nameField.setText("");
        birth.setValue(null);
        //erollDateField.setText("");
        telephone.setText("");
        if (controller.getOperation().equals("addStudent")){
            studentIdField.setText("");
            erollDate.setValue(null);
            studentClassBox.getSelectionModel().clearSelection();
            studentCollegeBox.getSelectionModel().clearSelection();
            studentSdeptBox.getSelectionModel().clearSelection();
        }
    }

    private boolean isName() {
        return studentDao.isIdOrName(nameField.getText()).equals("name");
    }

}
