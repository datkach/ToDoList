package code.Controller;

import code.DAOEntities.ProjectController;
import code.DAOEntities.TeamController;
import code.Model.Team;
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

import java.sql.Date;

public class ProjectViewController {

    @FXML
    private TextField name;

    @FXML
    private DatePicker deadline;

    @FXML
    private TextField team;

    @FXML
    private TextArea description;

    @FXML
    private Text error;


    @FXML
    public void saveProject(ActionEvent actionEvent) throws Exception {

        TeamController teamController = new TeamController();
        Team new_team = teamController.getTeamByName(team.getText());

        if (new_team == null) {
            error.setText("Команды с таким именем не существует!");
            return;
        }

        Project project = new Project(
                Date.valueOf(deadline.getValue()),
                name.getText(),
                false,
                new_team,
                description.getText()
        );

        ProjectController projectController = new ProjectController();
        projectController.insert(project);
        System.out.println(project.getDeadline());


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/mainProjectsAdminWindow.fxml"));
        Parent parent = loader.load();

        MainProjectAdminController controller = loader.getController();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
        controller.displayProjects(controller.project_query);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void cancelProject(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/mainProjectsAdminWindow.fxml"));
        Parent parent = loader.load();

        MainProjectAdminController controller = loader.getController();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
        controller.displayProjects(controller.project_query);
        stage.setScene(scene);
        stage.show();
    }
}
