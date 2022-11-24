package code.Controller;

import code.DAOEntities.UserController;
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
import code.DAOEntities.ProjectController;
import code.DAOEntities.TaskController;
import code.Model.Project;
import code.Model.Task;
import code.Model.Users;

import java.sql.Date;

public class TaskViewController {

    @FXML
    private TextField name;

    @FXML
    private DatePicker deadline;

    @FXML
    private TextField responsible;

    @FXML
    private TextField project;

    @FXML
    private TextField prioritet;

    @FXML
    private TextArea description;

    @FXML
    private Text error;


    @FXML
    public void saveTask(ActionEvent actionEvent) throws Exception {

        UserController userController = new UserController();
        Users user = userController.getUserByLogin(responsible.getText());

        if (Integer.valueOf(prioritet.getText()) < 0 || Integer.valueOf(prioritet.getText()) > 10) {
            error.setText("Введите правильно приоритет!");
            return;
        }

        if (user == null) {
            error.setText("Пользователя с таким именем не существует!");
            return;
        }

        ProjectController projectController = new ProjectController();
        Project new_project = projectController.getProjectByName(project.getText());

        if (new_project == null) {
            error.setText("Такого проекта не существует!");
            return;
        }

        if (Date.valueOf(deadline.getValue()).after(new_project.getDeadline())) {
            error.setText("Проверьте дедлайн!");
            return;
        }

        Task task = new Task(
                Date.valueOf(deadline.getValue()),
                description.getText(),
                false,
                name.getText(),
                user,
                new_project,
                Integer.valueOf(prioritet.getText())
        );

        TaskController taskController = new TaskController();
        taskController.insert(task);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/mainTasksAdminWindow.fxml"));
        Parent parent = loader.load();

        MainAdminController controller = loader.getController();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
        controller.displayTasks(controller.task_query);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void cancelTask(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/mainTasksAdminWindow.fxml"));
        Parent parent = loader.load();

        MainAdminController controller = loader.getController();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
        controller.displayTasks(controller.task_query);
        stage.setScene(scene);
        stage.show();
    }

}
