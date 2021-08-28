package view.adminView.mainView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName LoginMain.java
 * @Description TODO
 * @createTime 2021-06-28 20:33:59
 */
public class AdminMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        primaryStage.setTitle("教学管理系统");
        Scene scene=new Scene(root, 1200, 750);
        primaryStage.initStyle(StageStyle.DECORATED);
        scene.getStylesheets().add(getClass().getResource("../../../css/admin.css").toExternalForm());
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../../../img/窗口栏.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
