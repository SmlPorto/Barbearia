package barbeiro.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import barbeiro.dao.FuncionarioDao;
import barbeiro.model.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class Login implements Initializable {
    public static Funcionario funcionario = new Funcionario();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    @FXML
    private Button btnLogin;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField passwordFieldSenha;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
        private void handleLogin(ActionEvent event) throws IOException {

            funcionario = funcionarioDao.consultarLogin(textFieldLogin.getText(), passwordFieldSenha.getText());

            if (funcionario != null) {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Menu");
                stage.show();
                Stage thisStage = (Stage) btnLogin.getScene().getWindow();
                thisStage.close();

            }else{
                JOptionPane.showMessageDialog(null,"Email ou senha inválidos!");
            }

        }

    }
    

