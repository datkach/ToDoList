package code.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminController extends Controller {
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
