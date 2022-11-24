package code.Controller;

import code.DAOEntities.ProfileController;
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
import code.Model.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;

public class MainProjectController extends Controller {

    @FXML
    public TableView<Project> table;

    @FXML
    public TableColumn<Project, String> project;

    @FXML
    public TableColumn<Project, String> team;

    @FXML
    public TableColumn<Project, Boolean> is_finished;

    @FXML
    public TableColumn<Project, Date> deadline;

    @FXML
    public TableColumn<Project, String> description;


    public ObservableList<Project> projects;

    public Integer team_id = new ProfileController().getProfileByUserId(StartController.user).getTeam().getTeamId();
    public String project_query = "SELECT p FROM Project p WHERE p.team = " + team_id;

    @FXML
    public void loadProjects(ActionEvent actionEvent) throws Exception {
        displayProjects(project_query);
    }

    @FXML
    public void sortByDate(ActionEvent actionEvent) throws Exception {
        String date_query = project_query + " ORDER BY p.deadline NULLS LAST";
        displayProjects(date_query);
    }


    public void displayProjects(String query) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-app");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        projects = FXCollections.observableList(entityManager.createQuery(query, Project.class)
                .getResultList());
        entityManager.getTransaction().commit();
        entityManagerFactory.close();

        project.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        team.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        deadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        is_finished.setCellValueFactory(new PropertyValueFactory<>("is_finished"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        is_finished.setCellFactory(column -> {
            return new TableCell<Project, Boolean>() {
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
                Project selectedProject = table.getSelectionModel().getSelectedItem();
                System.out.println(selectedProject.getProjectName());

                try {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View/viewProject.fxml"));
                    if (StartController.user.getRole() == 2) {
                        loader.setLocation(getClass().getResource("/View/viewAdminProject.fxml"));
                    }
                    Parent parent = loader.load();

                    ViewProjectController controller = loader.getController();
                    controller.initData(selectedProject);

                    if (StartController.user.getRole() == 2) {
                        ViewProjectAdminController contr = loader.getController();
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

            }
        });

        table.setItems(null);
        table.setItems(projects);
    }


}

