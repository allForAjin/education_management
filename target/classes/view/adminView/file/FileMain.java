package view.adminView.file;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName FileMain.java
 * @Description TODO
 * @createTime 2021-07-07 23:50:50
 */
public class FileMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("file.fxml"));
        primaryStage.setTitle("学生信息文件选择");
        Scene scene=new Scene(root, 700, 300);
        primaryStage.initStyle(StageStyle.DECORATED);
        scene.getStylesheets().add(getClass().getResource("../../../css/file.css").toExternalForm());
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../../../img/窗口栏.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
