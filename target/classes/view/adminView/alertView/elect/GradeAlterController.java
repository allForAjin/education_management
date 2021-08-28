package view.adminView.alertView.elect;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import dao.ElectCourseDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.StageManager;
import model.Course;
import model.ElectCourseInfo;
import model.Student;
import view.adminView.mainView.AdminController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName GradeAlterController.java
 * @Description TODO
 * @createTime 2021-06-30 12:49:10
 */
public class GradeAlterController implements Initializable {

    @FXML
    public JFXTextField courseIdField;
    @FXML
    public JFXTextField courseNameField;
    @FXML
    public JFXTextField studentIdField;
    @FXML
    public JFXTextField studentNameField;
    @FXML
    public JFXTextField gradeField;
    @FXML
    public JFXButton infoUpdateButton;
    @FXML
    public JFXButton clearButton;
    @FXML
    public JFXButton confirmUpdateButton;
    @FXML
    public JFXButton cancelUpdateButton;
    @FXML
    public JFXDialogLayout confirmUpdateDialogLayout;
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


    private String courseId;
    private String studentId;


    private final AdminController controller = (AdminController) StageManager.
            CONTROLLER.get(AdminController.class.getSimpleName());
    private final ElectCourseDao electCourseDao = new ElectCourseDao();
    private Course course;
    private Student student;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentId = controller.getStudentId();
        courseId = controller.getSelectedCourseId();
        this.electCourseInfoDisplay();
        this.updateCourseInfo();

    }

    /**
     * @author lmk
     * @Description //TODO 读取并显示课程信息
     * @Date 2021/6/30 23:41
     * @Param []
     * @Return void
     */
    private void electCourseInfoDisplay() {
        ElectCourseInfo electCourseInfo = electCourseDao.getPartScoreInfo(studentId, courseId);
        courseIdField.setText(electCourseInfo.getCourseId());
        courseNameField.setText(electCourseInfo.getCourseName());
        studentIdField.setText(electCourseInfo.getStudentId());
        studentNameField.setText(electCourseInfo.getStudentName());
        gradeField.setText(electCourseInfo.getGrade() + "");

        courseIdField.setFocusTraversable(false);
        courseNameField.setFocusTraversable(false);
        studentIdField.setFocusTraversable(false);
        studentNameField.setFocusTraversable(false);
        gradeField.setFocusTraversable(false);
    }


    private void updateCourseInfo() {
        infoUpdateButton.setOnAction(action -> {
            if (infoIsEmpty()) {
                infoEmptyLabel.setText("信息不能为空！");
                infoEmptyDialog();
                electCourseInfoDisplay();
                return;
            }
            if (!gradeIsOk()){
                infoEmptyLabel.setText("成绩需在0~100间！");
                infoEmptyDialog();
                electCourseInfoDisplay();
                return;
            }
            updateDialogAlert();
        });

        clearButton.setOnAction(action->{
            cleanField();
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
        confirmUpdateButton.setOnMouseClicked(action -> {
            int count=electCourseDao.updateScore(studentIdField.getText(),courseIdField.getText(),this.getInfo());
            if (count == 0) {
                //update fail
                this.successOrFailDialogAlert(failDialogLayout, failButton);
            } else if (count == 1) {
                //success
                this.successOrFailDialogAlert(successDialogLayout, successButton);
            }
            alert.close();
        });
        cancelUpdateButton.setOnMouseClicked(action -> {
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
        return gradeField.getText().length() == 0;
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
                adminController.electCourseTableItemSetting();
                controller.setSelectedCourseId(null);
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

    private int getInfo() {
        return Integer.parseInt(gradeField.getText());
    }

    private boolean gradeIsOk() {
        return Integer.parseInt(gradeField.getText()) >= 0 && Integer.parseInt(gradeField.getText()) <= 100;
    }

    private void cleanField() {
        gradeField.setText("");
    }


}
