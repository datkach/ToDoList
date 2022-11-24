package code.Controller;

import code.DAOEntities.ProfileController;
import code.DAOEntities.TeamController;
import code.DAOEntities.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import code.Model.Profile;
import code.Model.Team;
import code.Model.Users;


public class RegistrationController {

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField age;

    @FXML
    private TextField team;

    @FXML
    private TextField job;

    @FXML
    private TextField login;

    @FXML
    private TextArea hobby;

    @FXML
    private Text error;

    @FXML
    private CheckBox is_admin;

    @FXML
    private PasswordField password;


    @FXML
    public void userSignUp(ActionEvent actionEvent) throws Exception {

        Boolean admin = is_admin.isSelected();

        error.setText("");

        if (login.getText().isEmpty() || password.getText().isEmpty()
                || team.getText().isEmpty() || name.getText().isEmpty() || surname.getText().isEmpty()) {
            error.setText("Введите все необходимые поля!");
            return;
        }

        System.out.println("Hello");

        UserController userController = new UserController();
        Users user = userController.getUserByLogin(login.getText());

        if (user != null) {
            error.setText("Пользователь с таким именем уже существует!");
            return;
        } else {
            user = new Users(login.getText(), password.getText());
            if (admin) user.setRole(2);
            System.out.println(user.getLogin());
            userController.insert(user);
        }

        TeamController teamController = new TeamController();
        Team new_team = teamController.getTeamByName(team.getText());
        if (new_team == null) {
            if (admin) {
                new_team = new Team(team.getText(), user);
                System.out.println(new_team.getTeamName());
                teamController.insert(new_team);
            } else {
                error.setText("Такой команды не существует!");
                return;
            }
        }

        Profile userProfile = new Profile(
                Integer.parseInt(age.getText()),
                hobby.getText(),
                job.getText(),
                name.getText(),
                surname.getText(),
                user,
                new_team
        );

        ProfileController profileController = new ProfileController();
        profileController.insert(userProfile);
        error.setText("Успешная регистрация!");

        Parent parent = FXMLLoader.load(getClass().getResource("/View/startWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();

        stage.setScene(scene);
        stage.show();

    }

}

