package barbeiro.controller;

import barbeiro.dao.UsuarioDao;
import barbeiro.model.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

import barbeiro.utils.ComboBoxLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class CadastrarUsuariosController implements Initializable {
    private UsuarioDao servicoDao = new UsuarioDao();
    public static int ALTERAR = 0;

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldSenha;
    @FXML
    private ComboBox<String> comboBoxCargo;
    @FXML
    private TextField textFieldFuncao;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxCargo.setItems(ComboBoxLists.gerarTipoFunc());
        carregarDados();
    }

    private void carregarDados() {

        if (ALTERAR == 1) {
            Usuario usuario = UsuariosController.UsuarioSelecionado;
            textFieldNome.setText(usuario.getNome());
            textFieldCpf.setText(usuario.getCpf());
            textFieldEmail.setText(usuario.getEmail());
            textFieldSenha.setText(usuario.getSenha());
            switch (usuario.getCargo()) {
                case 0:
                    comboBoxCargo.getSelectionModel().select("Funcionário");
                    break;
                case 1:
                    comboBoxCargo.getSelectionModel().select("Administrador");
                    break;
            }
            textFieldFuncao.setText(usuario.getFuncao());
        }
    }

    @FXML
    private void salvar(ActionEvent event) {
        if (textFieldNome.getText().trim().isEmpty() || textFieldEmail.getText().trim().isEmpty()) {
            if (textFieldNome.getText().trim().isEmpty()) {
                textFieldNome.requestFocus();
                JOptionPane.showMessageDialog(null,"O campo nome é obrigatório");
            } else {
                textFieldEmail.requestFocus();
                JOptionPane.showMessageDialog(null,"O campo email é obrigatório");
            }

        } else {
            if (ALTERAR == 1) {
                UsuariosController.UsuarioSelecionado.setNome(textFieldNome.getText());
                UsuariosController.UsuarioSelecionado.setCpf(textFieldCpf.getText());
                UsuariosController.UsuarioSelecionado.setEmail(textFieldEmail.getText());
                UsuariosController.UsuarioSelecionado.setSenha(textFieldSenha.getText());
                switch (comboBoxCargo.getValue()) {
                    case "Funcionário":
                        UsuariosController.UsuarioSelecionado.setCargo(0);
                        break;
                    case "Administrador":
                        UsuariosController.UsuarioSelecionado.setCargo(1);
                        break;
                }
                UsuariosController.UsuarioSelecionado.setFuncao(textFieldFuncao.getText());

                servicoDao.salvar(UsuariosController.UsuarioSelecionado);

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Funcionario atualizado com sucesso!");

            } else {

                UsuariosController.novoUsuario.setNome(textFieldNome.getText());
                UsuariosController.novoUsuario.setCpf(textFieldCpf.getText());
                UsuariosController.novoUsuario.setEmail(textFieldEmail.getText());
                UsuariosController.novoUsuario.setSenha(textFieldSenha.getText());
                switch (comboBoxCargo.getValue()) {
                    case "Funcionário":
                        UsuariosController.novoUsuario.setCargo(0);
                        break;
                    case "Administrador":
                        UsuariosController.novoUsuario.setCargo(1);
                        break;
                }
                UsuariosController.novoUsuario.setFuncao(textFieldFuncao.getText());
                servicoDao.salvar(UsuariosController.novoUsuario);

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();

                JOptionPane.showMessageDialog(null,"Funcionario cadastrado com sucesso!");

            }
        }

    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage thisStage = (Stage) btnCancelar.getScene().getWindow();
        thisStage.close();
    }
}