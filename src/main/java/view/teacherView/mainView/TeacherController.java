package view.teacherView.mainView;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
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
import javafx.util.Duration;
import model.Course;
import model.Student;

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
public class TeacherController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
