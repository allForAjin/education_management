package view.teacherView.mainView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName LoginMain.java
 * @Description TODO
 * @createTime 2021-06-28 20:33:59
 */
public class TeacherMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("teacher.fxml"));
        primaryStage.setTitle("教学管理系统");
        Scene scene=new Scene(root, 850, 600);
        scene.getStylesheets().add(getClass().getResource("../../../css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
