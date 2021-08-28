package view.adminView.alertView.course;

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
public class CourseAlterMain extends Application {
    private String fileName;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(fileName));
        primaryStage.setTitle("课程信息增加/修改");
        Scene scene=new Scene(root, 500, 650);
        scene.getStylesheets().add(getClass().
                getResource("../../../../css/course-info-alert.css").
                toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
