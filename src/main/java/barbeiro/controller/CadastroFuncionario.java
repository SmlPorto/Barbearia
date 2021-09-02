package barbeiro.controller;

import barbeiro.dao.FuncionarioDao;
import barbeiro.model.Funcionario;

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

public class CadastroFuncionario implements Initializable {
    private FuncionarioDao servicoDao = new FuncionarioDao();
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
            Funcionario funcionario = ControleFuncionario.funcionarioSelecionado;
            textFieldNome.setText(funcionario.getNome());
            textFieldCpf.setText(funcionario.getCpf());
            textFieldEmail.setText(funcionario.getEmail());
            textFieldSenha.setText(funcionario.getSenha());
            switch (funcionario.getCargo()) {
                case 0:
                    comboBoxCargo.getSelectionModel().select("Funcionário");
                    break;
                case 1:
                    comboBoxCargo.getSelectionModel().select("Administrador");
                    break;
            }
            textFieldFuncao.setText(funcionario.getFuncao());
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
                ControleFuncionario.funcionarioSelecionado.setNome(textFieldNome.getText());
                ControleFuncionario.funcionarioSelecionado.setCpf(textFieldCpf.getText());
                ControleFuncionario.funcionarioSelecionado.setEmail(textFieldEmail.getText());
                ControleFuncionario.funcionarioSelecionado.setSenha(textFieldSenha.getText());
                switch (comboBoxCargo.getValue()) {
                    case "Funcionário":
                        ControleFuncionario.funcionarioSelecionado.setCargo(0);
                        break;
                    case "Administrador":
                        ControleFuncionario.funcionarioSelecionado.setCargo(1);
                        break;
                }
                ControleFuncionario.funcionarioSelecionado.setFuncao(textFieldFuncao.getText());

                servicoDao.salvar(ControleFuncionario.funcionarioSelecionado);

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Funcionario atualizado com sucesso!");

            } else {

                ControleFuncionario.novoFuncionario.setNome(textFieldNome.getText());
                ControleFuncionario.novoFuncionario.setCpf(textFieldCpf.getText());
                ControleFuncionario.novoFuncionario.setEmail(textFieldEmail.getText());
                ControleFuncionario.novoFuncionario.setSenha(textFieldSenha.getText());
                switch (comboBoxCargo.getValue()) {
                    case "Funcionário":
                        ControleFuncionario.novoFuncionario.setCargo(0);
                        break;
                    case "Administrador":
                        ControleFuncionario.novoFuncionario.setCargo(1);
                        break;
                }
                ControleFuncionario.novoFuncionario.setFuncao(textFieldFuncao.getText());
                servicoDao.salvar(ControleFuncionario.novoFuncionario);

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