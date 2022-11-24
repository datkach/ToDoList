package code.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import code.Model.Project;

public class ViewProjectController {

    @FXML
    protected TextField name;

    @FXML
    protected DatePicker deadline;

    @FXML
    protected TextField team;

    @FXML
    protected TextArea description;

    protected Project new_proj;

    @FXML
    protected Text error;

    public void initData(Project project) {
        name.setText(project.getProjectName());
        name.setEditable(false);
        name.setFocusTraversable(false);
        deadline.setValue(project.getDeadline().toLocalDate());
        deadline.setEditable(false);
        team.setText(project.getTeam().getTeamName());
        team.setEditable(false);
        description.setText(project.getDescription());
        description.setEditable(false);
        new_proj = project;
    }


    @FXML
    public void cancelProject(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
    }

    @FXML
    public void viewAllTasks(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/viewAllTasks.fxml"));
        Parent parent = loader.load();

        viewAllTasksController controller = loader.getController();
        controller.setProject(new_proj);
        controller.loadTasks();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}