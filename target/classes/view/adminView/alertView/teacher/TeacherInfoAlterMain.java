package view.adminView.alertView.teacher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName TeacherInfoAlterMain.java
 * @Description TODO
 * @createTime 2021-06-27 19:18:09
 */
public class TeacherInfoAlterMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("teacher-alter.fxml"));
        primaryStage.setTitle("教师信息增加/修改");
        Scene scene=new Scene(root, 450, 750);
        scene.getStylesheets().add(getClass().
                getResource("../../../../css/teacher-info-alert.css").
                toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
