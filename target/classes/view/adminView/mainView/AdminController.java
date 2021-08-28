package view.adminView.mainView;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import dao.*;
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
import view.adminView.alertView.course.CourseAlterMain;
import view.adminView.alertView.elect.ElectCourseMain;
import view.adminView.alertView.elect.GradeAlterMain;
import view.adminView.alertView.stu.StudentInfoAlterMain;
import view.adminView.alertView.teacher.TeacherInfoAlterMain;
import view.adminView.file.FileMain;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminController.java
 * @Description TODO
 * @createTime 2021-06-28 20:27:46
 */
public class AdminController implements Initializable {
    private final StudentDao studentDao = new StudentDao();
    private final TeacherDao teacherDao = new TeacherDao();
    private final CollegeDao collegeDao = new CollegeDao();
    private final SdeptDao sdeptDao = new SdeptDao();
    private final CourseDao courseDao = new CourseDao();
    private final ElectCourseDao electCourseDao = new ElectCourseDao();
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private final ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();
    private final ObservableList<ElectCourseInfo> electCourseInfoList = FXCollections.observableArrayList();
    private final static String SYTLE = "-fx-background-color: #00bcff;-fx-text-fill: #ffffff;-fx-pref-height: 15px;-fx-pref-width: 40px;-fx-font-size: 12px;";
    private final static String SYTLEBIGBTN = "-fx-background-color: #00bcff;-fx-text-fill: #ffffff;-fx-pref-height: 25px;-fx-pref-width: 70px;-fx-font-size: 12px;";


    @FXML
    private BorderPane mainPane;
    @FXML
    private Label clock;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private VBox studentInfoBox;
    @FXML
    private JFXTextField studentIdNameField;
    @FXML
    private JFXComboBox<String> studentSexBox;
    @FXML
    private JFXComboBox<String> studentCollegeBox;
    @FXML
    private JFXComboBox<String> studentSdeptBox;
    @FXML
    private JFXComboBox<String> studentClassBox;
    @FXML
    private JFXButton studentQueryBtn;
    @FXML
    private TableView studentQueryTable;
    @FXML
    private TableColumn studentAction;
    @FXML
    private TableColumn studentUpdateAction;
    @FXML
    private TableColumn studentDeleteAction;
    @FXML
    private JFXDialog classNoFieldDialog;
    @FXML
    private JFXDialogLayout classNoFieldDialogLayout;
    @FXML
    private JFXButton classNoFieldFieldButton;
    @FXML
    private JFXDialogLayout deleteDialogLayout;
    @FXML
    private JFXButton cancelDeleteButton;
    @FXML
    private JFXButton confirmDeleteButton;
    @FXML
    private JFXTextField courseQueryField;
    @FXML
    private JFXComboBox<String> courseCollegeBox;
    @FXML
    private JFXComboBox<String> courseSemesterBox;
    @FXML
    private JFXButton courseQueryBtn;
    @FXML
    private TableView courseQueryTable;
    @FXML
    private TableColumn courseUpdateAction;
    @FXML
    private TableColumn courseDeleteAction;
    @FXML
    private JFXDialogLayout idFieldDialogLayout;
    @FXML
    private JFXButton idFieldButton;
    @FXML
    private Label alertLabel;
    @FXML
    private Label deleteLabel;
    @FXML
    private TableColumn chooseTeacherAction;
    @FXML
    private JFXComboBox<String> courseTeacherBox;
    @FXML
    private VBox teacherInfoBox;
    @FXML
    private JFXTextField teacherIdNameField;
    @FXML
    private JFXComboBox<String> teacherSexBox;
    @FXML
    private JFXComboBox<String> teacherCollegeBox;
    @FXML
    private JFXComboBox<String> teacherSdeptBox;
    @FXML
    private JFXComboBox<String> teacherOfficeBox;
    @FXML
    private JFXButton teacherQueryBtn;
    @FXML
    private TableView teacherQueryTable;
    @FXML
    private TableColumn teacherAction;
    @FXML
    private TableColumn teacherUpdateAction;
    @FXML
    private TableColumn teacherDeleteAction;
    @FXML
    private Label studentCount;
    @FXML
    private Label teacherCount;
    @FXML
    private JFXComboBox<String> teacherTitleBox;
    @FXML
    private Label normal;
    @FXML
    private Label assistantPro;
    @FXML
    private Label profession;
    @FXML
    private VBox electCourseBox;
    @FXML
    private JFXTextField studentField;
    @FXML
    private JFXTextField courseField;
    @FXML
    private JFXComboBox<String> collegeBox;
    @FXML
    private JFXComboBox<String> sdeptBox;
    @FXML
    private JFXComboBox<String> classBox;
    @FXML
    private JFXComboBox<String> semesterBox;
    @FXML
    private JFXButton electCourseQueryBtn;
    @FXML
    private TableView electCourseTable;
    @FXML
    private TableColumn electCourseAction;
    @FXML
    private TableColumn gradeAction;
    @FXML
    private TableColumn cancelAction;
    @FXML
    private Label courseCountLabel;
    @FXML
    private JFXButton studentInsertButton;
    @FXML
    private JFXButton teacherInsertButton;
    @FXML
    private JFXButton courseInsertButton;
    @FXML
    private Label electCountLabel;
    @FXML
    private JFXButton studentFileChoose;
    @FXML
    private JFXButton electCourseInsertButton;
    @FXML
    private TableColumn electAction;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageManager.CONTROLLER.put(this.getClass().getSimpleName(), this);
        this.timeClock();
        this.tabPaneSetting();
        this.studentTabInitialize();
        this.teacherTabInitialize();
        this.courseTabInitialize();
        this.electCourseTabInitialize();

    }


    /**
     * @author lmk
     * @Description //TODO 时间
     * @Date 2021/6/28 20:50
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
     * @Date 2021/6/29 0:26
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
                    case "student":
                        studentIdNameField.setText("");
                        studentClassBox.getSelectionModel().clearSelection();
                        studentCollegeBox.getSelectionModel().clearSelection();
                        studentSdeptBox.getSelectionModel().clearSelection();
                        studentSexBox.getSelectionModel().clearSelection();
                        studentAndTeacherComboBoxSetting(studentCollegeBox, studentSdeptBox, studentSexBox);
                        classComboBoxSetting(studentClassBox);
                        studentTableItemSetting();
                        break;
                    case "course":
                        courseQueryField.setText("");
                        courseComboxSetting(courseCollegeBox, courseSemesterBox, courseTeacherBox);
                        courseCollegeBox.getSelectionModel().clearSelection();
                        courseSemesterBox.getSelectionModel().clearSelection();
                        courseTeacherBox.getSelectionModel().clearSelection();
                        courseTableItemSetting();
                        break;
                    case "teacher":
                        teacherIdNameField.setText("");
                        teacherSexBox.getSelectionModel().clearSelection();
                        teacherCollegeBox.getSelectionModel().clearSelection();
                        teacherOfficeBox.getSelectionModel().clearSelection();
                        teacherSdeptBox.getSelectionModel().clearSelection();
                        teacherTitleBox.getSelectionModel().clearSelection();
                        teacherTableItemSetting();
                        break;
                    case "electCourse":
                        studentField.setText("");
                        courseField.setText("");
                        collegeBox.getSelectionModel().clearSelection();
                        sdeptBox.getSelectionModel().clearSelection();
                        classBox.getSelectionModel().clearSelection();
                        semesterBox.getSelectionModel().clearSelection();
                        studentAndTeacherComboBoxSetting(collegeBox, sdeptBox, null);
                        classComboBoxSetting(classBox);
                        courseComboxSetting(null, semesterBox, null);
                        electCourseTableItemSetting();
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
     * @Date 2021/6/28 23:54
     * @Param []
     * @Return void
     */
    private void studentTabInitialize() {
        this.studentTableItemSetting();
        this.tableSetting(studentQueryTable);
        this.updateAndDeleteButton(studentQueryTable, studentUpdateAction, studentDeleteAction);
        this.studentAndTeacherComboBoxSetting(studentCollegeBox, studentSdeptBox, studentSexBox);
        this.classComboBoxSetting(studentClassBox);
        this.studentBoxListener(studentSexBox, studentCollegeBox, studentSdeptBox, studentClassBox, null);
        this.studentQuery();
        this.addStudent();

    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/7/3 1:00
     * @Param []
     * @Return void
     */
    private void teacherTabInitialize() {
        this.teacherTableItemSetting();
        this.tableSetting(teacherQueryTable);
        this.updateAndDeleteButton(teacherQueryTable, teacherUpdateAction, teacherDeleteAction);
        this.studentAndTeacherComboBoxSetting(teacherCollegeBox, teacherSdeptBox, teacherSexBox);
        this.officeBoxSetting(teacherOfficeBox);
        this.teacherBoxListener(teacherSexBox, teacherTitleBox, teacherCollegeBox, teacherSdeptBox, teacherOfficeBox, this.getClass().getSimpleName());
        this.titleBoxSetting(teacherTitleBox);
        this.teacherQuery();
        this.addTeacher();
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 17:09
     * @Param []
     * @Return void
     */
    private void courseTabInitialize() {
        this.courseTableItemSetting();
        this.tableSetting(courseQueryTable);
        this.updateAndDeleteButton(courseQueryTable, courseUpdateAction, courseDeleteAction);
        this.courseComboxSetting(courseCollegeBox, courseSemesterBox, courseTeacherBox);
        this.courseBoxListener();
        this.courseQuery();
        this.addCourse();
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/7/5 13:26
     * @Param []
     * @Return void
     */
    private void electCourseTabInitialize() {
        this.electCourseTableItemSetting();
        this.tableSetting(electCourseTable);
        this.electCourseUpdateAndDeleteButton();
        this.studentAndTeacherComboBoxSetting(collegeBox, sdeptBox, null);
        this.classComboBoxSetting(classBox);
        this.courseComboxSetting(null, semesterBox, null);
        this.studentBoxListener(null, collegeBox, sdeptBox, classBox, semesterBox);
        this.electCourseQuery();
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/29 21:50
     * @Param []
     * @Return void
     */
    public void studentAndTeacherComboBoxSetting(JFXComboBox<String> collegeBox, JFXComboBox<String> sdeptBox, JFXComboBox<String> sexBox) {
        this.collegeBoxSetting(collegeBox);
        this.sdeptBoxSetting(sdeptBox);
        try {
            sexBox.getItems().clear();
            sexBox.setPromptText("选择性别");
            sexBox.getItems().add("全部");
            sexBox.setEditable(false);
            sexBox.getItems().add("男");
            sexBox.getItems().add("女");
        } catch (NullPointerException e) {
            System.out.println("无sexComBox");
        }
    }

    public void classComboBoxSetting(JFXComboBox<String> classBox) {
        classBox.getItems().clear();
        classBox.setPromptText("选择班级");
        classBox.getItems().add("所有班级");
        List<String> classList = electCourseDao.getAllClass();
        classBox.getItems().addAll(classList);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 17:09
     * @Param []
     * @Return void
     */
    public void courseComboxSetting(JFXComboBox<String> collegeBox, JFXComboBox<String> semesterBox, JFXComboBox<String> teacherBox) {
        try {
            this.collegeBoxSetting(collegeBox);
            this.teacherBoxSetting(teacherBox);
        } catch (NullPointerException e) {
            System.out.println("无combox");
        }
        semesterBox.setEditable(false);
        semesterBox.getItems().add("所有学期");
        semesterBox.setPromptText("选择学期");
        for (int i = 2015; i < 2026; i++) {
            semesterBox.getItems().add(i + "学年" + "第一学期");
            semesterBox.getItems().add(i + "学年" + "第二学期");
        }

    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 17:09
     * @Param [comBox]
     * @Return void
     */
    private void collegeBoxSetting(JFXComboBox<String> comBox) {
        comBox.getItems().clear();
        comBox.setPromptText("选择学院");
        comBox.getItems().add("所有学院");
        comBox.setEditable(false);
        List<String> list = new ArrayList<>();
        List<College> collegeList = collegeDao.getAllCollegeInfo();
        for (College college : collegeList) {
            list.add(college.getCollegeName());
        }
        comBox.getItems().addAll(list);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 17:09
     * @Param [comBox]
     * @Return void
     */
    private void sdeptBoxSetting(JFXComboBox<String> comBox) {
        comBox.getItems().clear();
        comBox.setPrefSize(comBox.getPrefWidth(), comBox.getPrefHeight());
        comBox.setPromptText("选择专业");
        comBox.getItems().add("所有专业");
        comBox.setEditable(false);
        List<String> list = new ArrayList<>();
        List<Sdept> sdeptList = sdeptDao.getAllSdeptInfo();
        for (Sdept sdept : sdeptList) {
            list.add(sdept.getSdeptName());
        }
        comBox.getItems().addAll(list);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/7/5 13:26
     * @Param [comBox]
     * @Return void
     */
    public void officeBoxSetting(JFXComboBox<String> comBox) {
        comBox.getItems().clear();
        comBox.setPromptText("选择办公室");
        comBox.getItems().add("所有办公室");
        comBox.setEditable(false);
        List<String> list = new ArrayList<>();
        List<String> officeList = teacherDao.getAllOfficeName();
        comBox.getItems().addAll(officeList);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/7/5 13:26
     * @Param [comBox]
     * @Return void
     */
    private void teacherBoxSetting(JFXComboBox<String> comBox) {
        comBox.getItems().clear();
        comBox.setPromptText("选择教师");
        comBox.getItems().add("所有教师");
        comBox.setEditable(false);
        List<String> list = new ArrayList<>();
        List<Teacher> teacherList = teacherDao.getAllTeacherName();
        for (Teacher teacher : teacherList) {
            list.add(teacher.getTeacherName());
        }
        comBox.getItems().addAll(list);
    }

    public void titleBoxSetting(JFXComboBox<String> titleBox) {
        titleBox.getItems().clear();
        titleBox.setPromptText("选择职称");
        titleBox.getItems().addAll("所有职称", "教授", "副教授", "讲师");
        titleBox.setEditable(false);
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/28 23:25
     * @Param []
     * @Return void
     */
    public void studentTableItemSetting() {
        if (studentList != null) {
            studentList.clear();
        }
        List<Student> list = studentDao.getAllStudentInfo();
        studentList.addAll(list);
        studentQueryTable.setItems(studentList);
        studentCount.setText(studentList.size() + "");
    }

    public void teacherTableItemSetting() {
        if (teacherList != null) {
            teacherList.clear();
        }
        List<Teacher> list = teacherDao.getAllTeacherInfo();
        teacherList.addAll(list);
        teacherQueryTable.setItems(teacherList);
        teacherCount.setText(teacherList.size() + "");
        this.titleCount();
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 17:09
     * @Param []
     * @Return void
     */
    public void courseTableItemSetting() {
        if (courseList != null) {
            courseList.clear();
        }
        List<Course> list = courseDao.getAllCourseInfo();
        courseList.addAll(list);
        courseQueryTable.setItems(courseList);
        this.courseCount();
    }

    public void electCourseTableItemSetting() {
        if (electCourseInfoList != null) {
            electCourseInfoList.clear();
        }
        List<ElectCourseInfo> list = electCourseDao.getAllScoreInfo();
        electCourseInfoList.addAll(list);
        electCourseTable.setItems(electCourseInfoList);
        electCountLabel.setText(electCourseDao.socreCount("","","","","",""));
    }


    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/29 12:29
     * @Param []
     * @Return void
     */
    private String selectedStudentId;
    private String selectedCourseId;
    private String selectedTeacherId;
    private String operation = "";

    private void updateAndDeleteButton(TableView tableView, TableColumn update, TableColumn delete) {
        update.setCellFactory(col -> {
            TableCell<Course, String> cell = new TableCell<Course, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    JFXButton btnUpdate = new JFXButton("修改");
                    btnUpdate.setStyle(SYTLE);

                    btnUpdate.setOnMouseClicked((click) -> {
                        if (tableView.getId().equals("studentQueryTable")) {
                            Student selectedStudent = studentList.get(getIndex());
                            studentUpdate(selectedStudent);
                        } else if (tableView.getId().equals("courseQueryTable")) {
                            Course selectedCourse = courseList.get(getIndex());
                            operation = "courseInfoAlter";
                            courseUpdate(selectedCourse);
                        } else if (tableView.getId().equals("teacherQueryTable")) {
                            Teacher selectedTeacher = teacherList.get(getIndex());
                            teacherUpdate(selectedTeacher);
                        }

                    });

                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        this.setGraphic(btnUpdate);
                    }
                }
            };
            return cell;
        });

        electAction.setCellFactory(col -> {
            TableCell<Course, String> cell = new TableCell<Course, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    JFXButton btnElect = new JFXButton("选课");
                    btnElect.setStyle(SYTLE);

                    btnElect.setOnMouseClicked((click) -> {
                        Student selectedStudent=studentList.get(getIndex());
                        electCourse(selectedStudent);
                    });

                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        this.setGraphic(btnElect);
                    }
                }
            };
            return cell;
        });

        chooseTeacherAction.setCellFactory(col -> {
            TableCell<Course, String> cell = new TableCell<Course, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    JFXButton btnTeacher = new JFXButton("授课");
                    btnTeacher.setStyle(SYTLE);

                    btnTeacher.setOnMouseClicked((click) -> {
                        Course selectedCourse = courseList.get(getIndex());
                        operation = "teach";
                        courseUpdate(selectedCourse);
                    });

                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        this.setGraphic(btnTeacher);
                    }
                }
            };
            return cell;
        });

        delete.setCellFactory(col -> {
            TableCell<Course, String> cell = new TableCell<Course, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    JFXButton btnDelete = new JFXButton("删除");
                    btnDelete.setStyle(SYTLE);

                    btnDelete.setOnMouseClicked((click) -> {

                        if (tableView.getId().equals("studentQueryTable")) {
                            Student selectedStudent = studentList.get(getIndex());
                            confirmDeleteDialog(studentQueryTable, selectedStudent.getStudentId());
                            System.out.println(selectedStudent.getStudentId());
                        } else if (tableView.getId().equals("courseQueryTable")) {
                            Course selectedCourse = courseList.get(getIndex());
                            confirmDeleteDialog(courseQueryTable, selectedCourse.getCourseId());
                            System.out.println(selectedCourse.getCourseId());
                        } else if (tableView.getId().equals("teacherQueryTable")) {
                            Teacher selectedTeacher = teacherList.get(getIndex());
                            confirmDeleteDialog(teacherQueryTable, selectedTeacher.getTeacherId());
                        }


                    });
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        this.setGraphic(btnDelete);
                    }
                }
            };
            return cell;
        });
    }

    private void electCourseUpdateAndDeleteButton() {
        gradeAction.setCellFactory(col -> {
            TableCell<Course, String> cell = new TableCell<Course, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    JFXButton btnGrade = new JFXButton("成绩录入");
                    btnGrade.setStyle(SYTLEBIGBTN);

                    btnGrade.setOnMouseClicked((click) -> {
                        ElectCourseInfo electCourseInfo = electCourseInfoList.get(getIndex());
                        scoreUpdate(electCourseInfo);
                    });
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        this.setGraphic(btnGrade);
                    }
                }
            };
            return cell;
        });
        cancelAction.setCellFactory(col -> {
            TableCell<Course, String> cell = new TableCell<Course, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    JFXButton btnCancel = new JFXButton("取消选课");
                    btnCancel.setStyle(SYTLEBIGBTN);

                    btnCancel.setOnMouseClicked((click) -> {
                        ElectCourseInfo electCourseInfo = electCourseInfoList.get(getIndex());
                        confirmDeleteDialog(electCourseInfo.getStudentId(), electCourseInfo.getCourseId());
                    });
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        this.setGraphic(btnCancel);
                    }
                }
            };
            return cell;
        });
    }

    private void electCourse(Student selectedStudent){
        selectedStudentId = selectedStudent.getStudentId();
        try {
            new ElectCourseMain().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(selectedStudent.getStudentId());
    }


    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/29 21:39
     * @Param [selectedStudent]
     * @Return void
     */
    private void studentUpdate(Student selectedStudent) {
        this.operation = "updateStudent";
        selectedStudentId = selectedStudent.getStudentId();
        try {
            new StudentInfoAlterMain().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(selectedStudent.getStudentId());
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/30 20:31
     * @Param [selectedCourse]
     * @Return void
     */
    private void courseUpdate(Course selectedCourse) {
        selectedCourseId = selectedCourse.getCourseId();
        try {
            if (operation == "courseInfoAlter") {
                CourseAlterMain main = new CourseAlterMain();
                main.setFileName("course-alter.fxml");
                main.start(new Stage());
            } else if (operation == "teach") {
                CourseAlterMain main = new CourseAlterMain();
                main.setFileName("teach.fxml");
                main.start(new Stage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(selectedCourse.getCourseId());
    }

    private void teacherUpdate(Teacher selectedTeacher) {
        this.operation = "updateTeacher";
        selectedTeacherId = selectedTeacher.getTeacherId();
        try {
            new TeacherInfoAlterMain().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(selectedTeacher.getTeacherId());
    }

    private void scoreUpdate(ElectCourseInfo electCourseInfo) {
        selectedStudentId = electCourseInfo.getStudentId();
        selectedCourseId = electCourseInfo.getCourseId();
        try {
            new GradeAlterMain().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void electCourse(){

    }

    private void addStudent() {
        studentInsertButton.setOnAction(action -> {
            operation = "addStudent";
            try {
                new StudentInfoAlterMain().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        studentFileChoose.setOnAction(action->{
            try {
                new FileMain().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void addTeacher() {
        teacherInsertButton.setOnAction(action -> {
            operation = "addTeacher";
            try {
                new TeacherInfoAlterMain().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void addCourse() {
        courseInsertButton.setOnAction(action -> {
            operation = "addCourse";
            try {
                CourseAlterMain main = new CourseAlterMain();
                main.setFileName("course-alter.fxml");
                main.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private static String sex = "";
    private static String college = "";
    private static String sdept = "";
    private static String class_ = "";
    private static String semester = "";
    private static String teacher = "";
    private static String title = "";
    private static String office = "";

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/29 21:38
     * @Param []
     * @Return void
     */
    private void studentQuery() {
        college = "";
        sdept = "";
        class_ = "";
        sex = "";
        studentQueryBtn.setOnAction(action -> {
            String nameField = studentIdNameField.getText();
            String classField = studentClassBox.getSelectionModel().getSelectedItem();
            List<Student> student = new ArrayList<>();
            if (studentDao.userIsIdOrName(nameField) == "error") {
                idFieldDialogAlert(studentQueryTable);
            } else {
                student = studentDao.getPartStudentInfo(nameField, studentSexBox.getSelectionModel().getSelectedItem(), studentCollegeBox.getSelectionModel().getSelectedItem()
                        , studentSdeptBox.getSelectionModel().getSelectedItem(), classField);
                this.updateStudentTableItem(student);
            }

        });


    }

    public void studentBoxListener(JFXComboBox<String> sexBox, JFXComboBox<String> collegeBox, JFXComboBox<String> sdeptBox,
                                   JFXComboBox<String> classBox, JFXComboBox<String> semesterBox) {
        try {
            sexBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
                sex = (String) newValue;
                System.out.println("成功选中" + newValue);

            });

            semesterBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
                semester = (String) newValue;
                System.out.println("成功选中" + newValue);
            });
        } catch (NullPointerException e) {
            System.out.println("无Box");
        }
        collegeBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            college = (String) newValue;
            System.out.println("成功选中" + newValue);

            if (college != "所有学院") {
                List<String> list = new ArrayList<>();
                List<Sdept> sdeptList = sdeptDao.getPartSdept(college);
                for (Sdept sdept : sdeptList) {
                    list.add(sdept.getSdeptName());
                }
                sdeptBox.getItems().clear();
                if (!operation.equals("addStudent") && !operation.equals("updateStudent")) {
                    sdeptBox.getItems().add("所有专业");
                }

                sdeptBox.getItems().addAll(list);

                list.clear();
                list = electCourseDao.getClassByCollege(college);
                classBox.getItems().clear();
                if (!operation.equals("updateStudent") && !operation.equals("addStudent")) {
                    classBox.getItems().add("所有班级");
                }
                classBox.getItems().addAll(list);
            } else {
                sdeptBoxSetting(sdeptBox);
                classComboBoxSetting(classBox);
            }
        });

        sdeptBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            sdept = (String) newValue;
            System.out.println("成功选中" + newValue);

            if (sdept != "所有专业" && college != "所有学院") {
                List<String> list = electCourseDao.getClassBySdept(sdept);
                classBox.getItems().clear();
                if (!operation.equals("updateStudent") && !operation.equals("addStudent")) {
                    classBox.getItems().add("所有班级");
                }
                classBox.getItems().addAll(list);
            } else if (sdept == "所有专业" && college != "所有学院") {
                List<String> list = electCourseDao.getClassByCollege(college);
                classBox.getItems().clear();
                if (!operation.equals("updateStudent") && !operation.equals("addStudent")) {
                    classBox.getItems().add("所有班级");
                }
                classBox.getItems().addAll(list);
            } else {
                classComboBoxSetting(classBox);
            }

        });

        classBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            class_ = (String) newValue;
            System.out.println("成功选中" + newValue);
        });
    }

    private void teacherQuery() {
        teacherQueryBtn.setOnAction(action -> {
            String nameField = teacherIdNameField.getText();
            List<Teacher> teacher;
            if (teacherDao.userIsIdOrName(nameField).equals("error")) {
                idFieldDialogAlert(teacherQueryTable);
                teacherIdNameField.setText("");
            } else {
                teacher = teacherDao.getPartTeacherInfo(nameField, teacherSexBox.getSelectionModel().getSelectedItem(),
                        teacherTitleBox.getSelectionModel().getSelectedItem(), teacherCollegeBox.getSelectionModel().getSelectedItem(),
                        teacherSdeptBox.getSelectionModel().getSelectedItem(), teacherOfficeBox.getSelectionModel().getSelectedItem());
                this.updateTeacherTableItem(teacher);
            }
        });

    }

    public void teacherBoxListener(JFXComboBox<String> sexBox, JFXComboBox<String> titleBox,
                                   JFXComboBox<String> collegeBox, JFXComboBox<String> sdeptBox, JFXComboBox<String> officeBox, String viewName) {
        try {
            sexBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
                sex = (String) newValue;
                System.out.println("成功选中" + newValue);
            });
        } catch (NullPointerException e) {
            System.out.println("listener无sexBox");
        }

        titleBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            title = (String) newValue;
            System.out.println("成功选中" + newValue);
        });

        collegeBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            college = (String) newValue;
            System.out.println("成功选中" + newValue);
            if (college != "所有学院") {
                List<String> list = new ArrayList<>();
                List<Sdept> sdeptList = sdeptDao.getPartSdept(college);

                for (Sdept sdept : sdeptList) {
                    list.add(sdept.getSdeptName());
                }
                sdeptBox.getItems().clear();
                if (viewName.equals(this.getClass().getSimpleName())) {
                    sdeptBox.getItems().add("所有专业");
                }

                sdeptBox.getItems().addAll(list);

                List<String> officeName = teacherDao.getOfficeNameByCollegeOrSdept(college, "college");
                officeBox.getItems().clear();

                if (viewName.equals(this.getClass().getSimpleName())) {
                    officeBox.getItems().add("所有办公室");
                }
                officeBox.getItems().addAll(officeName);
            } else {
                sdeptBoxSetting(sdeptBox);
                officeBoxSetting(officeBox);
            }
        });

        sdeptBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            sdept = (String) newValue;
            System.out.println("成功选中" + newValue);
            if (sdept != "所有专业" && college != "所有学院") {
                List<String> officeName = teacherDao.getOfficeNameByCollegeOrSdept(sdept, "sdept");
                officeBox.getItems().clear();
                if (viewName.equals("admin")) {
                    officeBox.getItems().add("所有办公室");
                }
                officeBox.getItems().addAll(officeName);
            } else if (college != "所有学院" && sdept == "所有专业") {
                List<String> officeName = teacherDao.getOfficeNameByCollegeOrSdept(college, "college");
                officeBox.getItems().clear();
                if (viewName.equals(this.getClass().getSimpleName())) {
                    officeBox.getItems().add("所有办公室");
                }
                officeBox.getItems().addAll(officeName);
            } else {
                officeBoxSetting(officeBox);
            }
        });
        officeBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            office = (String) newValue;
            System.out.println("成功选中" + newValue);
        });

    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/29 21:46
     * @Param []
     * @Return void
     */

    private void courseQuery() {
        courseQueryBtn.setOnAction(action -> {
            String courseField = courseQueryField.getText();
            List<Course> course;
            if (courseDao.isIdOrName(courseField) == "error") {
                idFieldDialogAlert(courseQueryTable);
                courseQueryField.setText("");
            } else {
                course = courseDao.getPartCourseInfo(courseField, courseCollegeBox.getSelectionModel().getSelectedItem()
                        , courseSemesterBox.getSelectionModel().getSelectedItem(), courseTeacherBox.getSelectionModel().getSelectedItem());
                this.updateCourseTableItem(course);
            }
        });
    }

    private void courseBoxListener() {
        courseCollegeBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            college = (String) newValue;
            System.out.println("成功选中" + newValue);

            if (college != "所有学院") {
                List<String> list = new ArrayList<>();
                List<String> teacherList = teacherDao.getTeacherNameByCollege(college);
                for (String teacherName : teacherList) {
                    list.add(teacherName);
                }
                courseTeacherBox.getItems().clear();
                courseTeacherBox.getItems().add("所有教师");
                courseTeacherBox.getItems().addAll(list);
            } else {
                teacherBoxSetting(courseTeacherBox);
            }
        });

        courseSemesterBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            semester = (String) newValue;
            System.out.println("成功选中" + newValue);
        });

        courseTeacherBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            teacher = (String) newValue;
            System.out.println("成功选中" + newValue);
        });

    }

    private void electCourseQuery() {
        electCourseQueryBtn.setOnAction(action -> {
            String student = studentField.getText();
            String course = courseField.getText();
            List<ElectCourseInfo> electCourseInfo;
            if (electCourseDao.isIdOrName(course) == "error") {
                idFieldDialogAlert(electCourseTable);
                courseField.setText("");
            }
            if (electCourseDao.userIsIdOrName(student) == "error") {
                idFieldDialogAlert(electCourseTable);
                studentField.setText("");
            } else {
                electCourseInfo = electCourseDao.getPartScoreInfo(student, course,
                        collegeBox.getSelectionModel().getSelectedItem(), sdeptBox.getSelectionModel().getSelectedItem()
                        , classBox.getSelectionModel().getSelectedItem(), semesterBox.getSelectionModel().getSelectedItem());
                this.updateElectTableItem(electCourseInfo);
                electCountLabel.setText(electCourseDao.socreCount(student, course,
                        collegeBox.getSelectionModel().getSelectedItem(), sdeptBox.getSelectionModel().getSelectedItem()
                        , classBox.getSelectionModel().getSelectedItem(), semesterBox.getSelectionModel().getSelectedItem()));
            }
        });
    }


    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/29 15:19
     * @Param [student]
     * @Return void
     */
    private void updateStudentTableItem(List<Student> student) {
        if (!studentList.isEmpty()) {
            studentList.clear();
        }
        studentList.addAll(student);
        studentQueryTable.setItems(studentList);
        studentCount.setText(studentList.size() + "");
    }

    private void updateTeacherTableItem(List<Teacher> teacher) {
        if (!teacherList.isEmpty()) {
            teacherList.clear();
        }
        teacherList.addAll(teacher);
        teacherQueryTable.setItems(teacherList);
        teacherCount.setText(teacherList.size() + "");
        this.titleCount();
    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/29 21:44
     * @Param [course]
     * @Return void
     */
    private void updateCourseTableItem(List<Course> course) {
        if (!courseList.isEmpty()) {
            courseList.clear();
        }
        courseList.addAll(course);
        courseQueryTable.setItems(courseList);
        this.courseCount();
        //teacherCount.setText(teacherList.size() + "");
    }

    private void updateElectTableItem(List<ElectCourseInfo> electCourseInfo) {
        if (!electCourseInfoList.isEmpty()) {
            electCourseInfoList.clear();
        }
        electCourseInfoList.addAll(electCourseInfo);
        electCourseTable.setItems(electCourseInfoList);

    }

    /**
     * @author lmk
     * @Description //TODO
     * @Date 2021/6/28 23:52
     * @Param [courseTable]
     * @Return void
     */
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

    private void idFieldDialogAlert(TableView tableView) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        if (tableView.getId().equals("studentQueryTable")) {
            alertLabel.setText("请输入正确的学号或姓名！");
        } else if (tableView.getId().equals("courseQueryTable")) {
            alertLabel.setText("请输入正确的课程号或课程名！");
        } else if (tableView.getId().equals("teacherQueryTable")) {
            alertLabel.setText("请输入正确的教师号或姓名！");
        } else {
            alertLabel.setText("请输入正确的学生或课程信息！");
        }
        alert.setContent(idFieldDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();


        idFieldButton.setOnMouseClicked(action -> {
            studentIdNameField.setText("");
            alert.close();
        });


    }


    private void confirmDeleteDialog(TableView tableView, String id) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        int count = electCourseDao.courseIsSelected(id);
        if (count > 0 && tableView.getId().equals("courseQueryTable")) {
            deleteLabel.setText("该课程尚有选课，是否确认删除？");
        } else {
            deleteLabel.setText("是否确认删除？");
        }
        alert.setContent(deleteDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();
        confirmDeleteButton.setOnMouseClicked(action -> {
            if (tableView.getId().equals("studentQueryTable")) {
                studentDelete(id);
            } else if (tableView.getId().equals("courseQueryTable")) {
                courseDelete(id);
            } else if (tableView.getId().equals("teacherQueryTable")) {
                teacherDelete(id);
            }
            alert.close();
        });

        cancelDeleteButton.setOnMouseClicked(action -> {
            alert.close();
        });
    }

    private void confirmDeleteDialog(String studentId, String courseId) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(deleteDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();
        confirmDeleteButton.setOnMouseClicked(action -> {
            electCourseDelete(studentId, courseId);
            alert.close();
        });

        cancelDeleteButton.setOnMouseClicked(action -> {
            alert.close();
        });
    }

    private void titleCount() {
        int normalTeacher = 0, assistant = 0, pro = 0;
        for (Teacher teacher : teacherList) {
            switch (teacher.getTitle()) {
                case "讲师":
                    normalTeacher++;
                    break;
                case "副教授":
                    assistant++;
                    break;
                case "教授":
                    pro++;
                    break;
                default:
                    break;
            }
            normal.setText(normalTeacher + "人");
            assistantPro.setText(assistant + "人");
            profession.setText(pro + "人");

        }
    }

    private void courseCount() {
        int count = courseList.size();
        int period = 0;
        int credit = 0;
        for (Course course : courseList) {
            period += course.getPeriod();
            credit += course.getCredit();
        }
        courseCountLabel.setText("当前页面共" + count + "条课程记录，学时总计" + period + "，学分总计" + credit);
    }

    private void studentDelete(String id) {
        studentDao.deleteStudent(id);
        studentTableItemSetting();
    }

    private void courseDelete(String courseId) {
        courseDao.deleteCourse(courseId);
        courseTableItemSetting();
    }

    private void teacherDelete(String teacherId) {
        teacherDao.deleteTeacher(teacherId);
        teacherTableItemSetting();
    }

    private void electCourseDelete(String studentId, String courseId) {
        electCourseDao.cancelElect(studentId, courseId);
        electCourseTableItemSetting();
    }


    public String getStudentId() {
        return this.selectedStudentId;
    }

    public void setSelectedStudentId(String selectedStudentId) {
        this.selectedStudentId = selectedStudentId;
    }

    public String getSelectedCourseId() {
        return selectedCourseId;
    }

    public void setSelectedCourseId(String selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    public String getSelectedTeacherId() {
        return selectedTeacherId;
    }

    public void setSelectedTeacherId(String selectedTeacherId) {
        this.selectedTeacherId = selectedTeacherId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
