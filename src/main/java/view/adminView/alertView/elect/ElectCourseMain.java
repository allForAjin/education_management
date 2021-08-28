package view.adminView.alertView.elect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ElectCourseMain.java
 * @Description TODO
 * @createTime 2021-07-08 10:30:12
 */
public class ElectCourseMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("elect.fxml"));
        primaryStage.setTitle("选课");
        Scene scene=new Scene(root, 500, 600);
        scene.getStylesheets().add(getClass().
                getResource("../../../../css/course-info-alert.css").
                toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
