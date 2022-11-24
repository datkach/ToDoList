package code.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAdminController extends MainController {

    @FXML
    public void addTask(ActionEvent actionEvent) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("/View/createTask.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void approveTasks(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/viewAllTasks.fxml"));
        Parent parent = loader.load();

        viewAllTasksController controller = loader.getController();
        controller.loadTasks("SELECT t FROM Task t INNER JOIN Done d ON t = d.task ");

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


}
