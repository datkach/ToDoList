package code.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import code.DAOEntities.ProjectController;

import java.sql.Date;

public class ViewProjectAdminController extends ViewProjectController {

    @FXML
    protected CheckBox is_finished;

    public void init() {
        name.setEditable(true);
        deadline.setEditable(true);
        team.setEditable(true);
        description.setEditable(true);
    }

    @FXML
    public void updateProject(ActionEvent actionEvent) throws Exception {

        new_proj.setDeadline(Date.valueOf(deadline.getValue()));
        new_proj.setDescription(description.getText());
        new_proj.setProjectName(name.getText());
        new_proj.getTeam().setTeamName(team.getText());


        ProjectController projectController = new ProjectController();
        projectController.update(new_proj);

        error.setText("Изменения успешно внесены!");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();

    }

    @FXML
    public void deleteProject(ActionEvent actionEvent) throws Exception {

        ProjectController projectController = new ProjectController();

        try {
            if (!is_finished.isSelected()) {
                error.setText("Проект еще не выполнен");
                return;
            }
            projectController.delete(new_proj);
            error.setText("Удаление прошло успешно!");
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.hide();
        } catch (Exception excep) {
            error.setText("В проекте есть еще невыполненные задачи!");
        }
        ;


    }

}
