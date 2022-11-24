package code.Controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import code.DAOEntities.DoneController;
import code.Model.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;

public class MainController extends Controller {

    @FXML
    public TableView<Task> table;

    @FXML
    public TableColumn<Task, String> task;

    @FXML
    public TableColumn<Task, String> responsible;

    @FXML
    public TableColumn<Task, Boolean> is_finished;

    @FXML
    public TableColumn<Task, String> project;

    @FXML
    public TableColumn<Task, Date> deadline;

    @FXML
    public TableColumn<Task, Integer> prioritet;

    public ObservableList<Task> tasks;

    Integer user_id = StartController.user.getUserId();
    public String task_query = "SELECT t FROM Task t WHERE t.responsible = " + user_id;

    @FXML
    public void loadTasks(ActionEvent actionEvent) throws Exception {
        displayTasks(task_query);
    }

    @FXML
    public void sortByDate(ActionEvent actionEvent) throws Exception {
        String date_query = task_query + " ORDER BY t.deadline NULLS LAST";
        displayTasks(date_query);
    }

    @FXML
    public void sortByPrioritet(ActionEvent actionEvent) throws Exception {
        String prior_query = task_query + " ORDER BY t.prioritet DESC";
        displayTasks(prior_query);
    }

    public void displayTasks(String query) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-app");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        tasks = FXCollections.observableList(entityManager.createQuery(query, Task.class)
                .getResultList());
        entityManager.getTransaction().commit();
        entityManagerFactory.close();


        task.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        responsible.setCellValueFactory(new PropertyValueFactory<>("resp"));
        project.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        deadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        is_finished.setCellValueFactory(new PropertyValueFactory<>("is_finished"));
        prioritet.setCellValueFactory(new PropertyValueFactory<>("prioritet"));


        is_finished.setCellFactory(column -> {
            return new TableCell<Task, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setStyle("");
                    } else {
                        setText(item.toString());
                        if (item) {
                            setStyle("-fx-background-color: #d8ffda");
                        } else {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: #ff9191");
                        }
                    }
                }
            };
        });


        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Task selectedTask = table.getSelectionModel().getSelectedItem();
                System.out.println(selectedTask.getTaskName());

                try {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View/viewTask.fxml"));
                    if (StartController.user.getRole() == 2) {
                        loader.setLocation(getClass().getResource("/View/viewAdminTask.fxml"));
                    }
                    Parent parent = loader.load();

                    System.out.println(StartController.user.getLogin());

                    ViewTaskController controller = loader.getController();
                    controller.initData(selectedTask);

                    if (StartController.user.getRole() == 2) {
                        ViewTaskAdminController contr = loader.getController();
                        contr.init();
                    }

                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException exp) {
                    System.out.println("loooool");
                }
                ;

                if (selectedTask.getIs_finished()) {
                    DoneController doneController = new DoneController();
                    doneController.delete(doneController.findDone(selectedTask));
                }

                table.refresh();

            }
        });

        table.setItems(null);
        table.setItems(tasks);
    }


}
