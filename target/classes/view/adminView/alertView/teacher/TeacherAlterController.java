package view.adminView.alertView.teacher;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import dao.TeacherDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.StageManager;
import model.Teacher;
import view.adminView.mainView.AdminController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName TeacherAlterController.java
 * @Description TODO
 * @createTime 2021-07-03 14:33:32
 */
public class TeacherAlterController implements Initializable {
    @FXML
    public JFXTextField teacherIdField;
    @FXML
    public JFXTextField nameField;
    @FXML
    public JFXRadioButton male;
    @FXML
    public ToggleGroup sexRadioButton;
    @FXML
    public JFXRadioButton female;
    @FXML
    public JFXComboBox<String> titleBox;
    @FXML
    public JFXComboBox<String> collegeBox;
    @FXML
    public JFXComboBox<String> sdeptBox;
    @FXML
    public JFXComboBox<String> officeBox;
    @FXML
    public JFXDatePicker birth;
    @FXML
    public JFXDatePicker entryDate;
    @FXML
    public JFXTextField telephone;
    @FXML
    public JFXButton infoUpdateButton;
    @FXML
    public JFXButton clearButton;
    @FXML
    public JFXDialogLayout confirmUpdateDialogLayout;
    @FXML
    public JFXButton confirmUpdateButton;
    @FXML
    public JFXButton concelUpdateButton;
    @FXML
    public JFXDialogLayout successDialogLayout;
    @FXML
    public JFXButton successButton;
    @FXML
    public JFXDialogLayout failDialogLayout;
    @FXML
    public JFXButton failButton;
    @FXML
    public JFXDialogLayout infoEmptyDialogLayout;
    @FXML
    public Label infoEmptyLabel;
    @FXML
    public JFXButton infoEmptyButton;
    @FXML
    public JFXDialogLayout setEmptyDialogLayout;
    @FXML
    public Label setEmptyEmptyLabel;
    @FXML
    public JFXButton confirmSetEmptyButton;
    @FXML
    public JFXButton cancelSetEmptyButton;
    @FXML
    public BorderPane mainPane;
    @FXML
    public JFXDialogLayout existDialogLayout;
    @FXML
    public JFXButton existButton;

    private String teacherId;
    private final AdminController controller = (AdminController) StageManager.CONTROLLER.get(AdminController.class.getSimpleName());
    private final TeacherDao teacherDao = new TeacherDao();
    private Teacher teacher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            teacherId = controller.getSelectedTeacherId();
        } catch (NullPointerException e) {
            System.out.println("增加教师");
        }

        teacher = teacherDao.getPartTeacherInfo(teacherId);
        controller.studentAndTeacherComboBoxSetting(collegeBox, sdeptBox, null);
        controller.titleBoxSetting(titleBox);
        controller.officeBoxSetting(officeBox);
        controller.teacherBoxListener(null, titleBox, collegeBox, sdeptBox, officeBox, "");
        collegeBox.getItems().remove(0);
        sdeptBox.getItems().remove(0);
        titleBox.getItems().remove(0);
        officeBox.getItems().remove(0);
        if (controller.getOperation().equals("updateTeacher") && teacherId != null) {
            this.teacherInfoDisplay();
        } else {
            teacherIdField.setEditable(true);
        }

        this.updateTeacherInfo();

    }

    private void teacherInfoDisplay() {
        try {
            teacherIdField.setText(teacher.getTeacherId());
        } catch (NullPointerException e) {
            System.out.println("无teacherId");
        }

        nameField.setText(teacher.getTeacherName());
        titleBox.getSelectionModel().select(teacher.getTitle());
        collegeBox.getSelectionModel().select(teacher.getCollegeName());
        sdeptBox.getSelectionModel().select(teacher.getSdeptName());
        officeBox.getSelectionModel().select(teacher.getOffice());
        birth.setValue(LocalDate.parse(teacher.getBirth()));
        entryDate.setValue(LocalDate.parse(teacher.getEntryDate()));
        telephone.setText(teacher.getTelephone());

        teacher.setSex(String.valueOf(teacher.getSex().charAt(0)));
        if (teacher.getSex().equals("男")) {
            male.setSelected(true);
        } else if (teacher.getSex().equals("女")) {
            female.setSelected(true);
        }

        teacherIdField.setFocusTraversable(false);
        nameField.setFocusTraversable(false);
        titleBox.setFocusTraversable(false);
        telephone.setFocusTraversable(false);
        collegeBox.setFocusTraversable(false);
        sdeptBox.setFocusTraversable(false);
        officeBox.setFocusTraversable(false);
        birth.setFocusTraversable(false);
        entryDate.setFocusTraversable(false);
        telephone.setFocusTraversable(false);
        male.setFocusTraversable(false);
        female.setFocusTraversable(false);
    }

    private void updateTeacherInfo() {
        infoUpdateButton.setOnAction(action -> {
            infoEmptyLabel.setText("信息不能为空！");
            if (!teacherDao.userIsIdOrName(teacherIdField.getText()).equals("id")){
                infoEmptyLabel.setText("教师号格式错误！");
                infoEmptyDialogAlert();
                teacherInfoDisplay();
                return;
            }
            if (!isName() && !infoIsEmpty()) {
                infoEmptyLabel.setText("姓名格式错误！");
                infoEmptyDialogAlert();
                teacherInfoDisplay();
                return;
            }
            if (!teacherDao.phoneIsCorrect(telephone.getText()) && !infoIsEmpty()) {
                infoEmptyLabel.setText("电话格式错误！");
                infoEmptyDialogAlert();
                teacherInfoDisplay();
                return;
            }
            if (infoIsEmpty()) {
                infoEmptyDialogAlert();
                teacherInfoDisplay();
                return;
            }
            updateDialogAlert();
        });

        clearButton.setOnAction(action -> {
            setEmptyDialog();
        });
    }

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

            if (controller.getOperation().equals("updateTeacher")) {
                System.out.println("update");
                count = teacherDao.updateTeacher(this.getInfo());
            } else {
                System.out.println("insert");
                count = teacherDao.insertTeacher(this.getInfo());
            }
            if (count == 0) {
                //update fail
                this.successOrFailDialogAlert(failDialogLayout, failButton);
            } else if (count == 1) {
                //success
                this.successOrFailDialogAlert(successDialogLayout, successButton);
            } else if (count == -2) {
                teacherExistDialog();
            }

            alert.close();
        });
    }

    private Teacher getInfo() {
        String teacherId = teacherIdField.getText();
        String teacherName = nameField.getText();
        String sex = "";
        String title = titleBox.getSelectionModel().getSelectedItem();
        String teacherBirth = birth.getValue().toString();
        String college = collegeBox.getSelectionModel().getSelectedItem();
        String sdept = sdeptBox.getSelectionModel().getSelectedItem();
        String office = officeBox.getSelectionModel().getSelectedItem();
        String teacherEntryDate = entryDate.getValue().toString();
        String phone = telephone.getText();

        if (male.isSelected()) {
            sex = "男";
        } else {
            sex = "女";
        }
        System.out.println(sex);
        return new Teacher(teacherId, teacherName, sex, title, teacherBirth, teacherEntryDate, phone, office, sdept, college, 0);
    }

    private void infoEmptyDialogAlert() {
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
                adminController.teacherTableItemSetting();
                controller.setSelectedTeacherId(null);
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

    private void teacherExistDialog() {
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

    private boolean infoIsEmpty() {
        return teacherIdField.getText().length() == 0 || nameField.getText().length() == 0 || birth.getValue() == null || telephone.getText().length() == 0 ||
                titleBox.getSelectionModel().isEmpty() || collegeBox.getSelectionModel().isEmpty() || sdeptBox.getSelectionModel().isEmpty() ||
                officeBox.getSelectionModel().isEmpty() || entryDate == null;
    }

    private void cleanField() {
        nameField.setText("");
        titleBox.getSelectionModel().clearSelection();
        collegeBox.getSelectionModel().clearSelection();
        sdeptBox.getSelectionModel().clearSelection();
        officeBox.getSelectionModel().clearSelection();
        birth.setValue(null);
        entryDate.setValue(null);
        telephone.setText("");
        if (controller.getOperation().equals("addTeacher")) {
            teacherIdField.setText("");
        }
    }

    private boolean isName() {
        return teacherDao.userIsIdOrName(nameField.getText()).equals("name");
    }

    private boolean isId(){
        return teacherDao.userIsIdOrName(teacherIdField.getText()).equals("id");
    }

}
