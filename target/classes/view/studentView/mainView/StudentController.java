package view.studentView.mainView;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import dao.CollegeDao;
import dao.CourseDao;
import dao.ElectCourseDao;
import dao.StudentDao;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import manager.StageManager;
import model.*;
import view.studentView.alertView.StudentInfoAlterMain;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lmk
 * @Description //TODO
 * @Date 2021/6/26 16:29
 * @Param
 * @Return
 */
public class StudentController implements Initializable {
    @FXML
    private Label idLabel;
    @FXML
    private Label sexLabel;
    @FXML
    private Label collegeLabel;
    @FXML
    private Label classLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label sdeptLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXTextField courseQueryField;
    @FXML
    private JFXComboBox<String> collegeQueryBox;
    @FXML
    private JFXComboBox<String> semesterQueryBox;
    @FXML
    private TableView courseQueryTable;
    @FXML
    private JFXTextField courseSelectField;
    @FXML
    private JFXComboBox<String> collegeSelectBox;
    @FXML
    private JFXComboBox<String> semesterSelectBox;
    @FXML
    private TableColumn courseSelectAction;
    @FXML
    private JFXButton courseSelectQueryBtn;
    @FXML
    private TableView courseSelectTable;
    @FXML
    private JFXButton courseQueryBtn;
    @FXML
    private VBox courseSelectBox;
    @FXML
    private VBox courseQueryBox;
    @FXML
    private JFXDialog fieldErrorDialog;
    @FXML
    private JFXButton dialogAcceptButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private JFXDialogLayout courseErrorDialogLayout;
    @FXML
    private JFXTextField scoreCourseField;
    @FXML
    private JFXComboBox<String> scoreCollegeBox;
    @FXML
    private JFXComboBox<String> scoreSemesterBox;
    @FXML
    private JFXButton courseScoreQueryBtn;
    @FXML
    private TableView scoreCountTable;
    @FXML
    private JFXDialog confirmSelectDialog;
    @FXML
    private JFXDialogLayout confirmSelectDialogLayout;
    @FXML
    private JFXButton confirmSelectButton;
    @FXML
    private JFXDialog selectSuccessDialog;
    @FXML
    private JFXDialogLayout selectSuccessDialogLayout;
    @FXML
    private JFXButton selectSuccessButton;
    @FXML
    private JFXButton cancelSelectButton;
    @FXML
    private TableView scoreInfoTable;
    @FXML
    private JFXDialog courseSelectedFullDialog;
    @FXML
    private JFXDialogLayout courseSelectedFullDialogLayout;
    @FXML
    private JFXButton courseSelectedFullButton;
    @FXML
    private Label clock;
    @FXML
    private TabPane tabPane;

    private final CourseDao courseDao = new CourseDao();
    private final ElectCourseDao electCourseDao = new ElectCourseDao();
    private final StudentDao studentDao=new StudentDao();
    private final static String studentId = "20161432";

    private ObservableList<Course> courseList = FXCollections.observableArrayList();
    private ObservableList<ElectCourseInfo> electCourseInfoList = FXCollections.observableArrayList();
    private ObservableList<ElectCourseCount> electCourseCountList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageManager.CONTROLLER.put(this.getClass().getSimpleName(),this);
        this.timeClock();
        this.tabPaneSetting();
        this.studentInfoSetting();
        this.setCourseTableButton();
        this.courseSelectTableSetting();
        this.courseQueryTableSetting();
        this.scoreInfoTableSetting();
        this.openStudentInfoUpdate();
    }
/**
 * @author lmk
 * @Description //TODO
 * @Date 2021/6/28 21:08
 * @Param []
 * @Return void
 */
    public void studentInfoSetting(){
        Student student=studentDao.getPartStudentInfo(studentId).get(0);
        idLabel.setText(student.getStudentId());
        nameLabel.setText(student.getStudentName());
        sexLabel.setText(student.getSex());
        ageLabel.setText(student.getAge()+"");
        classLabel.setText(student.getClassNo());
        phoneLabel.setText(student.getTelephone());
        sdeptLabel.setText(student.getSdeptName());
        collegeLabel.setText(student.getCollegeName());
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/22 13:47
     * @Param []
     * @Return void
     */
    private void tabPaneSetting() {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                System.out.println("这是 = " + newValue.getId());
                switch (newValue.getId()) {
                    case "info":
                        studentInfoSetting();
                        break;
                    case "courseQuery":
                        courseQueryTableSetting();
                        courseQueryField.setText("");
                        collegeQueryBox.setPromptText("选择学院");
                        semesterQueryBox.setPromptText("选择学期");
                        break;
                    case "courseSelect":
                        courseSelectTableSetting();
                        courseSelectField.setText("");
                        collegeSelectBox.setPromptText("选择学院");
                        semesterSelectBox.setPromptText("选择学期");
                        break;
                    case "score":
                        scoreInfoTableSetting();
                        scoreCourseField.setText("");
                        scoreCollegeBox.setPromptText("选择学院");
                        scoreSemesterBox.setPromptText("选择学期");
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + newValue.getId());
                }
            }
        });

    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/22 13:48
     * @Param []
     * @Return void
     */
    private void comboBoxSetting(JFXComboBox<String> collegeBox, JFXComboBox<String> semesterBox) {
        collegeBox.getItems().clear();
        semesterBox.getItems().clear();
        collegeBox.setPromptText("选择学院");
        semesterBox.setPromptText("选择学期");
        ArrayList<String> list = new ArrayList<>();

        collegeBox.getItems().add("所有学院");
        CollegeDao collegeDao = new CollegeDao();
        List<College> collegeList = collegeDao.getAllCollegeInfo();
        for (College college : collegeList) {
            list.add(college.getCollegeName());
        }
        collegeBox.getItems().addAll(list);
        collegeBox.setEditable(false);
        semesterBox.getItems().add("所有学期");
        for (int i = 2017; i < 2025; i++) {
            semesterBox.getItems().add(i + "学年第一学期");
            semesterBox.getItems().add(i + "学年第二学期");
        }

    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/24 18:22
     * @Param []
     * @Return void
     */
    private void courseSelectTableSetting() {
        this.courseTableSetting(courseSelectTable);
        this.queryCourseOrScore(courseSelectTable, courseSelectQueryBtn, courseSelectField, collegeSelectBox, semesterSelectBox);
        this.comboBoxSetting(collegeSelectBox, semesterSelectBox);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/24 18:22
     * @Param []
     * @Return void
     */
    private void courseQueryTableSetting() {
        this.courseTableSetting(courseQueryTable);
        this.queryCourseOrScore(courseQueryTable, courseQueryBtn, courseQueryField, collegeQueryBox, semesterQueryBox);
        this.comboBoxSetting(collegeQueryBox, semesterQueryBox);
    }

    private void scoreInfoTableSetting(){
        List<ElectCourseInfo> infoList=electCourseDao.getAllScoreInfo(studentId);
        List<ElectCourseCount> countList=electCourseDao.getAllScoreCount(studentId);
        this.updateScoreTableItem(infoList,countList);
        this.comboBoxSetting(scoreCollegeBox,scoreSemesterBox);
        this.tableSetting(scoreCountTable);
        this.tableSetting(scoreInfoTable);
        this.queryCourseOrScore(scoreInfoTable,courseScoreQueryBtn,scoreCourseField,scoreCollegeBox,scoreSemesterBox);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/24 18:23
     * @Param [list, courseTable]
     * @Return void
     */
    private void updateCourseTableItem(List<Course> list, TableView courseTable) {
        if (courseList != null) {
            courseList.clear();
        }
        for (Course course : list) {
            if (course.getCourseTeacher() == null) {
                course.setCourseTeacher("暂无");
            }
            courseList.add(course);
        }
        courseTable.setItems(courseList);
    }

    private void updateScoreTableItem(List<ElectCourseInfo> infoList,List<ElectCourseCount> countList){
        if (electCourseInfoList!=null){
            electCourseInfoList.clear();
        }
        if (electCourseCountList!=null){
            electCourseCountList.clear();
        }
        for (ElectCourseInfo info:infoList){
//            if (info.getGrade()==-1){
//                continue;
//            }
            electCourseInfoList.add(info);
        }

        for (ElectCourseCount count:countList){
            electCourseCountList.add(count);
        }

        scoreInfoTable.setItems(electCourseInfoList);
        scoreCountTable.setItems(electCourseCountList);

    }

    private void openStudentInfoUpdate(){
        btnEdit.setOnAction(action->{
            Stage stage=new Stage();
            try {
                new StudentInfoAlterMain().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/22 13:47
     * @Param []
     * @Return void
     */
    private void courseTableSetting(TableView courseTable) {
        List<Course> list=new ArrayList<>();
        if (courseTable.getId().equals("courseQueryTable")) {
            list = courseDao.getAllSelectedCourseInfo(studentId);
        } else if (courseTable.getId().equals("courseSelectTable")){
            list = courseDao.getAllNoSelectedCourseInfo(studentId);
        }
        this.updateCourseTableItem(list, courseTable);
        this.tableSetting(courseTable);
    }




    private void tableSetting(TableView courseTable) {
        //设置表格列不可拖动
        courseTable.getColumns().addListener(new ListChangeListener() {
            boolean isturnback = false;

            @Override
            public void onChanged(Change c) {
                if (!isturnback) {
                    while (c.next()) {
                        if (!c.wasPermutated() && !c.wasUpdated()) {
                            isturnback = true;
                            courseTable.getColumns().setAll(c.getRemoved());
                        }
                    }
                } else {
                    isturnback = false;
                }
            }
        });
    }
/**
 * @author lmk
 * @Description //TODO
 * @Date 2021/6/27 22:08
 * @Param []
 * @Return void
 */
    private void setCourseTableButton() {
        courseSelectAction.setCellFactory((col) -> {
            TableCell<Course, String> cell = new TableCell<Course, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    JFXButton btnSelect = new JFXButton("选课");
                    ;
                    btnSelect.setStyle("-fx-background-color: #00bcff;" +
                            "-fx-text-fill: #ffffff;" +
                            "-fx-pref-height: 15px;" +
                            "-fx-pref-width: 40px;-fx-font-size: 12px;");

                    // setCourseTableButtonDisabled();
                    //System.out.println(item.length());


                    btnSelect.setOnMouseClicked((click) -> {
                        Course selectedCourse = courseList.get(getIndex());
                        int flag = electCourseDao.studentIsElectCourse(studentId, selectedCourse.getCourseId());
                        if (flag == 0) {
                            confirmSelectCourseAlert(selectedCourse.getCourseId());

                        } else {
                            System.out.println(selectedCourse.getCourseId());
                        }

                    });

                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        this.setGraphic(btnSelect);
                    }
                }
            };
            return cell;
        });
    }


    private static String college = null;
    private static String semester = null;
/**
 * @author lmk
 * @Description //TODO 课程信息查询
 * @Date 2021/6/26 20:46
 * @Param [courseTable, btn, textField, collegeB, semesterB]
 * @Return void
 */
    private void queryCourseOrScore(TableView courseTable, JFXButton btn, JFXTextField textField, JFXComboBox<String> collegeB, JFXComboBox<String> semesterB) {
        college = null;
        semester = null;
        //学院下拉框事件
        collegeB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                college = (String) newValue;
                System.out.println("成功选中" + newValue);
            }
        });
        //学期下拉框设置
        semesterB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                semester = (String) newValue;
                System.out.println("成功选中" + newValue);
            }
        });
        //选课按钮事件设置
        btn.setOnMouseClicked((click) -> {
            courseList.clear();
            String field = textField.getText();
            List<Course> course=new ArrayList<>();
            System.out.println(courseDao.isIdOrName(field));
            if (courseDao.isIdOrName(field).equals("error")) {
                this.courseDialogAlert();
                if (courseTable.getId().equals("courseQueryTable")) {
                    course = courseDao.getAllSelectedCourseInfo(studentId);
                } else if (courseTable.getId().equals("courseSelectTable")){
                    course = courseDao.getAllNoSelectedCourseInfo(studentId);
                }else if (courseTable.getId().equals("scoreInfoTable")){
                    List<ElectCourseInfo> infoList=new ArrayList<>();
                    List<ElectCourseCount> countList=new ArrayList<>();
                    infoList=electCourseDao.getAllScoreInfo(studentId);
                    countList=electCourseDao.getAllScoreCount(studentId);
                    this.updateScoreTableItem(infoList,countList);
                    return;
                }
            } else {
                if (courseTable.getId().equals("courseQueryTable")) {
                    course = courseDao.getPartSelectedCourseInfo(studentId, field, college, semester,"");
                    for (Course c:course){
                        System.out.println("eee"+c.getCourseId());
                    }

                } else if (courseTable.getId().equals("courseSelectTable")){
                    course = courseDao.getPartNoSelectedCourseInfo(studentId,field, college, semester,"");
                }else if (courseTable.getId().equals("scoreInfoTable")){
                    List<ElectCourseInfo> infoList=new ArrayList<>();
                    List<ElectCourseCount> countList=new ArrayList<>();

                    infoList=electCourseDao.getPartScoreInfo(studentId, field, college, semester);

                    System.out.println(semester==null);
                    countList=electCourseDao.getPartScoreCount(studentId,semester);
                    this.updateScoreTableItem(infoList,countList);
                    return;
                }
            }
            this.updateCourseTableItem(course, courseTable);
        });

    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 20:28
     * @Param []
     * @Return void
     */

    private void timeClock() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date time = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //创建一个handler
        EventHandler<ActionEvent> eventHandler = e -> {
            time.setTime(time.getTime() + 1000);

            clock.setText("当前时间是：" + df.format(new Date()));
        };

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 20:28
     * @Param []
     * @Return void
     */
    private void courseDialogAlert() {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(courseErrorDialogLayout);

        alert.initModality(Modality.NONE);
        alert.show();
        dialogAcceptButton.setOnMouseClicked(action -> {
            alert.close();
            courseQueryField.setText("");
            courseSelectField.setText("");
        });
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 20:28
     * @Param []
     * @Return void
     */
    private void confirmSelectCourseAlert(String courseId) {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(confirmSelectDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();

        confirmSelectButton.setOnMouseClicked(action -> {
            if (courseDao.courseSelectedAmountIsFull(courseId)){
                alert.close();
                this.courseSelectedFullDialog();
                return;
            }

            int count = electCourseDao.selectCourse(studentId, courseId);
            if (count > 0) {
                this.selectSuccessDialog();
                this.courseTableSetting(courseSelectTable);
            }
            alert.close();
        });

        cancelSelectButton.setOnMouseClicked(action->{
            alert.close();
        });
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/26 20:46
     * @Param []
     * @Return void
     */
    private void selectSuccessDialog() {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(selectSuccessDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();

        selectSuccessButton.setOnMouseClicked(action -> {
            alert.close();
        });

    }

    private void courseSelectedFullDialog() {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(courseSelectedFullDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();

        courseSelectedFullButton.setOnMouseClicked(action -> {
            alert.close();
        });
    }



    public static String getStudentId() {
        return studentId;
    }
}
