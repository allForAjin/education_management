package view.adminView.alertView.elect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName GradeAlterMain.java
 * @Description TODO
 * @createTime 2021-06-30 12:49:32
 */
public class GradeAlterMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grade.fxml"));
        primaryStage.setTitle("成绩录入");
        Scene scene=new Scene(root, 400, 450);
        scene.getStylesheets().add(getClass().
                getResource("../../../../css/grade.css").
                toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
