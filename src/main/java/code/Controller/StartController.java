package code.Controller;

import code.DAOEntities.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import code.Model.Users;

public class StartController {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Text error;

    public static Users user;

    @FXML
    public void UserSignUp(ActionEvent actionEvent) throws Exception {

        System.out.println("Hello");

        Parent parent = FXMLLoader.load(getClass().getResource("/View/registrationWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void UserLogIn(ActionEvent actionEvent) throws Exception {

        error.setText("");

        if (login.getText().isEmpty() || password.getText().isEmpty()) {
            error.setText("Введите данные корректно");
            return;
        }

        UserController userController = new UserController();
        user = userController.getUserByLogin(login.getText());


        if (user == null || user.getPassword().compareTo(password.getText()) != 0) {
            System.out.println(user.getPassword());
            error.setText("Логин или пароль введен неверно.");
            return;
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
            if (user.getRole() == 2) {
                parent = FXMLLoader.load(getClass().getResource("/View/mainAdminWindow.fxml"));
            }
            setUser(user);
            System.out.println(user.getLogin());
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.hide();

            stage.setScene(scene);
            stage.show();
        }

    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }
}
