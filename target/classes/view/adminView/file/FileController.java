package view.adminView.file;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import dao.FileDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.StageManager;
import model.Student;
import view.adminView.mainView.AdminController;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName FileController.java
 * @Description TODO
 * @createTime 2021-07-07 23:50:41
 */
public class FileController implements Initializable {
    @FXML
    private JFXTextField filePath;
    @FXML
    private JFXTextField infoCount;
    @FXML
    private JFXButton chooseButton;
    @FXML
    private JFXButton confirmButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private JFXDialogLayout confirmUpdateDialogLayout;
    @FXML
    private JFXButton confirmUpdateButton;
    @FXML
    private JFXButton concelUpdateButton;
    @FXML
    private JFXDialogLayout successDialogLayout;
    @FXML
    private JFXButton successButton;
    @FXML
    private JFXDialogLayout failDialogLayout;
    @FXML
    private JFXButton failButton;
    @FXML
    private JFXDialogLayout infoEmptyDialogLayout;
    @FXML
    private Label infoEmptyLabel;
    @FXML
    private JFXButton infoEmptyButton;

    private List<Student> studentList=new ArrayList<>();
    private final AdminController controller = (AdminController) StageManager.
            CONTROLLER.get(AdminController.class.getSimpleName());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.buttonAction();
    }

    private void buttonAction(){
        chooseButton.setOnAction(action->{
            File file=FileDao.openFile();
            filePath.setText(file.getPath());
            try {
                List list=FileDao.readSpecifyRows(file);
                infoCount.setText(list.get(0).toString());
                studentList=(List<Student>) list.get(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        confirmButton.setOnAction(action->{
            if (filePath.getText().length()==0){
                infoEmptyDialog();
                return;
            }
            updateDialogAlert();
        });
    }

    private void updateDialogAlert() {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(confirmUpdateDialogLayout);

        alert.initModality(Modality.NONE);
        alert.show();
        concelUpdateButton.setOnMouseClicked(action -> {
            alert.close();
        });
        confirmUpdateButton.setOnMouseClicked(action -> {
            boolean flag=FileDao.insertStudent(studentList);
            if (flag){
                successOrFailDialogAlert(successDialogLayout,successButton);
            }else{
                successOrFailDialogAlert(failDialogLayout,failButton);
            }
            alert.close();
        });
    }

    private void infoEmptyDialog() {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(infoEmptyDialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();
        infoEmptyButton.setOnAction(action -> {
            alert.close();
        });
    }

    private void successOrFailDialogAlert(JFXDialogLayout dialogLayout, JFXButton button) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(dialogLayout);
        alert.initModality(Modality.NONE);
        alert.show();
        button.setOnMouseClicked(action -> {
            if (button.getId().equals("failButton")){
                filePath.setText("");
                infoCount.setText("");
            }else {
                AdminController adminController = (AdminController) StageManager.CONTROLLER.get(AdminController.class.getSimpleName());
                adminController.studentTableItemSetting();
                stage.close();
            }
            alert.close();
        });
    }

}
