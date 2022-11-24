package code.Controller;

import code.DAOEntities.ProfileController;
import code.Model.Profile;
import code.Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import code.Model.Users;

public class ViewProfileController {

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

    private Profile profile;

    @FXML
    private PasswordField password;

    public void initData(Users user) {

        ProfileController profileController = new ProfileController();
        profile = profileController.getProfileByUserId(user);
        System.out.println(user.getLogin());
        Team new_team = profile.getTeam();

        name.setText(profile.getName());
        surname.setText(profile.getSurname());
        age.setText(String.valueOf(profile.getAge()));
        team.setText(new_team.getTeamName());
        job.setText(profile.getJob());
        login.setText(user.getLogin());
        password.setText(user.getPassword());
        hobby.setText(profile.getHobby());
    }

    @FXML
    public void save(ActionEvent actionEvent) throws Exception {

        profile.setAge(Integer.valueOf(age.getText()));
        profile.setHobby(hobby.getText());
        profile.setJob(job.getText());
        profile.getUser().setLogin(login.getText());
        profile.getUser().setPassword(password.getText());
        profile.getTeam().setTeamName(team.getText());
        profile.setName(name.getText());
        profile.setSurname(surname.getText());


        ProfileController profileController = new ProfileController();
        System.out.println(profile.getAge());
        profileController.update(profile);

        error.setText("Изменения успешно внесены!");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();

    }

    @FXML
    public void cancel(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
    }


}
