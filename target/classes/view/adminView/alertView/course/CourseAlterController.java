package view.adminView.alertView.course;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import dao.CourseDao;
import dao.SdeptDao;
import dao.TeacherDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.StageManager;
import model.Course;
import model.Sdept;
import view.adminView.mainView.AdminController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName GradeAlterController.java
 * @Description TODO
 * @createTime 2021-06-30 12:49:10
 */
public class CourseAlterController implements Initializable {
    @FXML
    private JFXTextField courseIdField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField periodField;
    @FXML
    private JFXTextField creditField;
    @FXML
    private JFXComboBox<String> semesterBox;
    @FXML
    private JFXButton infoUpdateButton;
    @FXML
    private JFXButton clearButton;
    @FXML
    private JFXDialogLayout confirmUpdateDialogLayout;
    @FXML
    private JFXButton concelUpdateButton;
    @FXML
    private JFXButton confirmUpdateButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private JFXDialogLayout successDialogLayout;
    @FXML
    private JFXButton successButton;
    @FXML
    private JFXButton failButton;
    @FXML
    private JFXDialogLayout failDialogLayout;
    @FXML
    private JFXDialogLayout infoEmptyDialogLayout;
    @FXML
    private Label infoEmptyLabel;
    @FXML
    private JFXButton infoEmptyButton;
    @FXML
    private JFXDialogLayout setEmptyDialogLayout;
    @FXML
    private Label setEmptyEmptyLabel;
    @FXML
    private JFXButton cancelSetEmptyButton;
    @FXML
    private JFXButton confirmSetEmptyButton;
    @FXML
    private JFXComboBox<String> collegeBox;
    @FXML
    private JFXTextField amountField;
    @FXML
    private JFXTextField semesterField;
    @FXML
    private JFXComboBox<String> sdeptBox;
    @FXML
    private JFXComboBox<String> teacherBox;
    @FXML
    private Label confirmLabel;
    @FXML
    private JFXDialogLayout existDialogLayout;
    @FXML
    private JFXButton existButton;
    @FXML
    private JFXTextField collegeField;

    private String courseId;
    private String operation;


    private final AdminController controller = (AdminController) StageManager.
            CONTROLLER.get(AdminController.class.getSimpleName());
    private final CourseDao courseDao = new CourseDao();
    private final TeacherDao teacherDao = new TeacherDao();
    private Course course;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            courseId = controller.getSelectedCourseId();
            course = courseDao.getPartCourseInfo(courseId).get(0);
        } catch (Exception e) {
            System.out.println("课程插入");
        }

        this.operation = controller.getOperation();
        if ("courseInfoAlter".equals(operation)) {
            controller.courseComboxSetting(collegeBox, semesterBox, null);
            semesterBox.getItems().remove(0);
            collegeBox.getItems().remove(0);
            this.courseInfoDisplay();
            this.updateCourseInfo();
        } else if ("addCourse".equals(operation)) {
            controller.courseComboxSetting(collegeBox, semesterBox, null);
            semesterBox.getItems().remove(0);
            collegeBox.getItems().remove(0);
            courseIdField.setEditable(true);
            this.updateCourseInfo();
        } else if ("teach".equals(operation)) {
            try {
                this.teacherBoxSetting();
                this.teachingInfoDisplay();
                this.sdeptBoxSetting();
                this.chooseTeacher();
            } catch (Exception e) {

            }

        }

    }

    /**
     * @author lmk
     * @Description //TODO 读取并显示课程信息
     * @Date 2021/6/30 23:41
     * @Param []
     * @Return void
     */
    private void courseInfoDisplay() {
        courseIdField.setText(course.getCourseId());
        nameField.setText(course.getCourseName());
        periodField.setText(course.getPeriod() + "");
        creditField.setText(course.getCredit() + "");
        semesterBox.getSelectionModel().select(course.getSemester());
        collegeBox.getSelectionModel().select(course.getCourseCollege());
        amountField.setText(course.getAmount() + "");

        courseIdField.setFocusTraversable(false);
        nameField.setFocusTraversable(false);
        periodField.setFocusTraversable(false);
        creditField.setFocusTraversable(false);
        semesterBox.setFocusTraversable(false);
        collegeBox.setFocusTraversable(false);
        amountField.setFocusTraversable(false);
    }

    private void teachingInfoDisplay() {
        courseIdField.setText(course.getCourseId());
        nameField.setText(course.getCourseName());
        semesterField.setText(course.getSemester());
        collegeField.setText(course.getCourseCollege());


        if (course.getCourseTeacher().equals("暂无授课老师")) {
            teacherBox.getSelectionModel().select(0);
        } else {
            teacherBox.getSelectionModel().select(course.getCourseTeacher());
        }

        try {
            List<Sdept> sdeptList = null;
            sdeptList = new SdeptDao().getPartSdept(collegeField.getText());
            sdeptBox.getSelectionModel().select(sdeptList.get(0).getSdeptName());
            collegeField.setFocusTraversable(false);
            sdeptBox.setFocusTraversable(false);
        } catch (NullPointerException e) {
            System.out.println("无collegeBox，无专业");
        }
        courseIdField.setFocusTraversable(false);
        nameField.setFocusTraversable(false);
        semesterField.setFocusTraversable(false);
        teacherBox.setFocusTraversable(false);
    }

    private static String sdept = "";

    private void sdeptBoxSetting() {
        sdeptBox.getItems().clear();
        sdeptBox.setPrefSize(sdeptBox.getPrefWidth(), sdeptBox.getPrefHeight());
        sdeptBox.setPromptText("选择专业");
        sdeptBox.setEditable(false);
        List<String> list = new ArrayList<>();
        SdeptDao sdeptDao = new SdeptDao();
        List<Sdept> sdeptList = sdeptDao.getPartSdept(collegeField.getText());
        for (Sdept sdept : sdeptList) {
            list.add(sdept.getSdeptName());
        }
        sdeptBox.getItems().addAll(list);

        sdeptBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                sdept = (String) newValue;
                System.out.println("成功选中" + newValue);

                if (sdept != "所有专业") {
                    List<String> teacherList = teacherDao.getTeacherNameBySdept(sdept);
                    teacherBox.getItems().clear();
                    teacherBox.getItems().add("无");
                    teacherBox.getItems().addAll(teacherList);
                } else {
                    teacherBoxSetting();
                }
            }
        });
    }

    private void teacherBoxSetting() {
        teacherBox.getItems().clear();
        teacherBox.setPromptText("选择教师");
        teacherBox.getItems().add("无");
        TeacherDao teacherDao = new TeacherDao();
        List<String> teacherList = teacherDao.getTeacherNameByCollege(course.getCourseCollege());
        teacherBox.getItems().addAll(teacherList);
    }

    private void updateCourseInfo() {
        infoUpdateButton.setOnAction(action -> {
            if (!isName()) {
                infoEmptyLabel.setText("课程名格式错误！");
                infoEmptyDialog();
                courseInfoDisplay();
                return;
            }
            if (!periodAndCreditIsCorrect()) {
                infoEmptyLabel.setText("学时应在0-100，学分应在0-20范围内！");
                infoEmptyDialog();
                courseInfoDisplay();
                return;
            }
            try {
                if (electedAmountOverAmount()) {
                    infoEmptyLabel.setText("已选课人数超出可选课人数！");
                    infoEmptyDialog();
                    courseInfoDisplay();
                    return;
                }
            } catch (NullPointerException e) {
                System.out.println("insert，不判断");
            }

            if (infoIsEmpty()) {
                infoEmptyDialog();
                courseInfoDisplay();
                return;
            }
            updateDialogAlert();
        });
        clearButton.setOnAction(action -> {
            setEmptyDialog();
        });
    }

    private void chooseTeacher() {
        infoUpdateButton.setOnAction(action -> {
            teacherUpdateDialogAlert();
        });
        clearButton.setOnAction(action -> {
            cleanTeacherField();
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
            if (controller.getOperation().equals("courseInfoAlter")) {
                System.out.println("update");
                count = courseDao.updateCourseInfo(this.getInfo());
            } else {
                System.out.println("insert");
                count = courseDao.insertCourse(this.getInfo());
            }
            if (count == 0) {
                //fail
                this.successOrFailDialogAlert(failDialogLayout, failButton);
            } else if (count == 1) {
                //update success
                this.successOrFailDialogAlert(successDialogLayout, successButton);
            } else if (count == -1) {
                courseExistDialog();
            }

            alert.close();
        });
    }

    private void teacherUpdateDialogAlert() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        int count = teacherDao.isTeached(course.getCourseId());
        if (count == 1) {
            confirmLabel.setText("该课程已有授课教师，是否修改？\n原教师:" + course.getCourseTeacher());
        }
        alert.setContent(confirmUpdateDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();
        concelUpdateButton.setOnMouseClicked(action -> {
            alert.close();
            confirmLabel.setText("是否确定修改？");
        });
        confirmUpdateButton.setOnMouseClicked(action -> {
            alert.close();
            String teacherName = teacherBox.getSelectionModel().getSelectedItem();
            String courseId = courseIdField.getText();
            int flag = 0;
            if (teacherName != "无") {
                teacherDao.cancelTeach(course.getCourseTeacher(), courseId);
                flag = teacherDao.teach(teacherName, courseId);
            } else {
                flag = teacherDao.cancelTeach(course.getCourseTeacher(), courseId);
            }
            if (flag == 1 || (flag == 0 && teacherName == "无")) {
                successOrFailDialogAlert(successDialogLayout, successButton);
            } else {
                successOrFailDialogAlert(failDialogLayout, failButton);
            }
            confirmLabel.setText("是否确定修改？");
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
            infoEmptyLabel.setText("信息不能为空！");
        });
    }

    private void courseExistDialog() {
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
        System.out.println(nameField.getText());
        System.out.println(creditField.getText() == null);
        System.out.println(periodField.getText() == null);
        System.out.println(semesterBox.getSelectionModel().getSelectedItem().isEmpty());
        return courseIdField.getText().length() == 0 || nameField.getText().length() == 0 || creditField.getText().length() == 0 ||
                periodField.getText().length() == 0 || amountField.getText().length() == 0
                || collegeBox.getSelectionModel().getSelectedItem() == null || semesterBox.getSelectionModel().getSelectedItem() == null;
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
                adminController.courseTableItemSetting();
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

    private Course getInfo() {
        String courseId = courseIdField.getText();
        String courseName = nameField.getText();
        int period = Integer.parseInt(periodField.getText());
        int credit = Integer.parseInt(creditField.getText());
        int amount = Integer.parseInt(amountField.getText());
        String semester = semesterBox.getSelectionModel().getSelectedItem();
        String courseCollege = collegeBox.getSelectionModel().getSelectedItem();
        String courseTeacher = "";
        return new Course(courseId, courseName, period, credit, semester, courseCollege, courseTeacher, amount, 0);
    }

    private void cleanField() {
        nameField.setText("");
        periodField.setText("");
        creditField.setText("");
        amountField.setText("");
        semesterBox.setPromptText("请选择学期");
        if (controller.getOperation().equals("addCourse")){
            courseIdField.setText("");
            semesterBox.getSelectionModel().clearSelection();
            collegeBox.getSelectionModel().clearSelection();
        }
    }

    private void cleanTeacherField() {
        if (course.getCourseTeacher().equals("暂无授课老师")) {
            teacherBox.getSelectionModel().select(0);
        } else {
            teacherBox.getSelectionModel().select(course.getCourseTeacher());
        }
        List<Sdept> sdeptList = new SdeptDao().getPartSdept(collegeBox.getSelectionModel().getSelectedItem());
        sdeptBox.getSelectionModel().select(sdeptList.get(0).getSdeptName());
    }

    private boolean periodAndCreditIsCorrect() {
        return (Integer.parseInt(periodField.getText()) > 0 && Integer.parseInt(periodField.getText()) < 100)
                && (Integer.parseInt(creditField.getText()) > 0 && Integer.parseInt(creditField.getText()) < 20);
    }

    private boolean electedAmountOverAmount() {
        return Integer.parseInt(amountField.getText()) < course.getElectedAmount();
    }

    private boolean isName() {
        return courseDao.isIdOrName(nameField.getText()).equals("name");
    }

}
