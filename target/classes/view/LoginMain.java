package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName LoginMain.java
 * @Description TODO
 * @createTime 2021-06-28 20:33:59
 */
public class LoginMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("登录");
        Scene scene=new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("../css/login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../img/窗口栏.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
