package view;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import dao.AdminDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;
import view.adminView.mainView.AdminMain;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description TODO
 * @createTime 2021-07-06 13:33:14
 */
public class LoginController implements Initializable {
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private Label nameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label title;
    @FXML
    private JFXDialogLayout errorDialogLayout;
    @FXML
    private Label errorLabel;
    @FXML
    private JFXButton errorButton;
    @FXML
    private BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fieldSetting();
        this.labelSetting();
        this.login();

    }

    private void fieldSetting(){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Password Can't be empty");
        FontIcon warnIcon = new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE);
        warnIcon.getStyleClass().add("error");
        validator.setIcon(warnIcon);
        password.getValidators().add(validator);
        password.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                password.validate();
            }
        });

        validator = new RequiredFieldValidator();
        validator.setMessage("UserName Can't be empty");
        userName.getValidators().add(validator);
        userName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                userName.validate();
            }
        });
    }

    private void labelSetting(){
        Image image;
        image=new Image(getClass().getResourceAsStream("../img/标题栏.png"),40,40,false,false);
        title.setGraphic(new ImageView(image));
        image=new Image(getClass().getResourceAsStream("../img/用户名.png"),20,20,false,false);
        nameLabel.setGraphic(new ImageView(image));
        image=new Image(getClass().getResourceAsStream("../img/密码.png"),20,20,false,false);
        passwordLabel.setGraphic(new ImageView(image));
    }

    private void login(){
        loginBtn.setOnAction(action->{
            if (userName.getText().length()==0||password.getText().length()==0){
                errorLabel.setText("用户名或密码不能为空！");
                errorDialog();
                return;
            }
            if (!new AdminDao().isExisted(userName.getText())){
                errorLabel.setText("用户不存在！");
                errorDialog();
                return;
            }
            if (new AdminDao().isPassed(userName.getText(),password.getText())){
                try {
                    new AdminMain().start(new Stage());
                    Stage stage = (Stage) mainPane.getScene().getWindow();
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                errorLabel.setText("用户名或密码错误！");
                errorDialog();
            }
        });


    }

    private void errorDialog() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(errorDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();
        errorButton.setOnMouseClicked(action -> {
            alert.close();
        });


    }
}
