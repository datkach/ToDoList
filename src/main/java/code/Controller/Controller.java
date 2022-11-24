package code.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

    @FXML
    public void openProfile(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/viewProfile.fxml"));
        Parent parent = loader.load();

        System.out.println(StartController.user.getLogin());

        ViewProfileController controller = loader.getController();
        controller.initData(StartController.user);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void loadProjects(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/View/mainProjectsWindow.fxml"));
        if (StartController.user.getRole() == 2) {
            loader.setLocation(getClass().getResource("/View/mainProjectsAdminWindow.fxml"));
        }
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
        MainProjectController controller = loader.getController();
        controller.displayProjects(controller.project_query);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadTasks(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/View/mainTasksWindow.fxml"));
        if (StartController.user.getRole() == 2) {
            loader.setLocation(getClass().getResource("/View/mainTasksAdminWindow.fxml"));
        }
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
        MainController controller = loader.getController();
        controller.displayTasks(controller.task_query);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void logOut(ActionEvent actionEvent) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/startWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();

        stage.setScene(scene);
        stage.show();
    }


}
