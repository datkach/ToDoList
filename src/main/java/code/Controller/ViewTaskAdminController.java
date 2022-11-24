package code.Controller;

import code.DAOEntities.ProjectController;
import code.DAOEntities.TaskController;
import code.DAOEntities.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import code.Model.Project;
import code.Model.Users;

import java.sql.Date;

public class ViewTaskAdminController extends ViewTaskController {

    public void init() {
        name.setEditable(true);
        deadline.setEditable(true);
        responsible.setEditable(true);
        project.setEditable(true);
        prioritet.setEditable(true);
        description.setEditable(true);
    }

    @FXML
    public void updateTask(ActionEvent actionEvent) throws Exception {

        UserController userController = new UserController();
        Users user = userController.getUserByLogin(responsible.getText());
        ProjectController projectController = new ProjectController();
        Project new_proj = projectController.getProjectByName(project.getText());

        if (new_proj == null) {
            error.setText("Такого проекта нет!");
            return;
        }

        if (Date.valueOf(deadline.getValue()).after(new_proj.getDeadline())) {
            error.setText("Проверьте дедлайн!");
            return;
        }

        if (Integer.valueOf(prioritet.getText()) < 0 || Integer.valueOf(prioritet.getText()) > 10) {
            error.setText("Введите правильно приоритет!");
            return;
        }

        if (user == null) {
            error.setText("Пользователя с таким именем не существует!");
            return;
        }

        new_task.setDeadline(Date.valueOf(deadline.getValue()));
        new_task.setDescription(description.getText());
        new_task.setIs_finished(is_finished.isSelected());
        new_task.setPrioritet(Integer.valueOf(prioritet.getText()));
        new_task.getResponsible().setLogin(responsible.getText());
        new_task.getProject().setProjectName(project.getText());
        new_task.setTaskName(name.getText());

        TaskController taskController = new TaskController();
        taskController.update(new_task);
        error.setText("Изменения успешно внесены!");


        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
    }


    @FXML
    public void deleteTask(ActionEvent actionEvent) throws Exception {
        if (!is_finished.isSelected()) {
            error.setText("Задание еще не выполнено");
            return;
        }
        TaskController taskController = new TaskController();
        taskController.delete(new_task);
        error.setText("Задание успешно удалено!");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
    }


}
