package code.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import code.DAOEntities.DoneController;
import code.Model.Done;
import code.Model.Task;

public class ViewTaskController {

    @FXML
    protected TextField name;

    @FXML
    protected DatePicker deadline;

    @FXML
    protected TextField responsible;

    @FXML
    protected TextField project;

    @FXML
    protected TextField prioritet;

    @FXML
    protected TextArea description;

    @FXML
    protected CheckBox is_finished;

    @FXML
    protected Text error;

    public Task new_task;


    public void initData(Task task) {
        name.setText(task.getTaskName());
        name.setEditable(false);
        name.setFocusTraversable(false);
        deadline.setValue(task.getDeadline().toLocalDate());
        deadline.setEditable(false);
        responsible.setText(StartController.user.getLogin());
        responsible.setEditable(false);
        project.setText(task.getProject().getProjectName());
        project.setEditable(false);
        prioritet.setText(task.getPrioritet().toString());
        prioritet.setEditable(false);
        description.setText(task.getDescription());
        description.setEditable(false);
        is_finished.setSelected(task.getIs_finished());

        new_task = task;

        task.getProject().getTeam().getAdmin();

    }

    @FXML
    public void cancelTask(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
    }

    @FXML
    public void sendToAdminTask(ActionEvent actionEvent) throws Exception {
        Boolean done = is_finished.isSelected();

        DoneController doneController = new DoneController();
        if (done && doneController.findDone(new_task) == null) {
            Done finish = new Done(new_task);
            System.out.println(finish.getTask().getTaskName());
            doneController.insert(finish);
            error.setText("Задание отправлено администратору на проверку");
        } else {
            error.setText("Задание уже отправлено администратору на проверку");
        }
    }

}
