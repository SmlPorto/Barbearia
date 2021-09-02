package barbeiro.controller;

import static barbeiro.controller.CadastroClientes.ALTERAR;

import barbeiro.dao.EnderecoDao;
import barbeiro.model.Cliente;
import barbeiro.utils.ComboBoxLists;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class EnderecoCliente implements Initializable {

    private EnderecoDao enderecoDao = new EnderecoDao();
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField TextFieldCidade;
    @FXML
    private ComboBox<String> comboBoxEstado;
    @FXML
    private TextField textFieldCodigoPostal;
    @FXML
    private TextField textFIeldRua;
    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldBairro;
    @FXML
    private TextField textFieldComplemento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxEstado.setItems(ComboBoxLists.gerarEstados());
        carregarDados();
    }

    private void carregarDados() {
        if (ALTERAR == 1) {
            Cliente cliente = ControleClientes.clienteSelecionado;
            TextFieldCidade.setText(cliente.getEndereco().getCidade());
            comboBoxEstado.getSelectionModel().select(cliente.getEndereco().getEstado());
            textFieldCodigoPostal.setText(String.valueOf(cliente.getEndereco().getCodigoPostal()));
            textFIeldRua.setText(cliente.getEndereco().getRua());
            textFieldNumero.setText(String.valueOf(cliente.getEndereco().getNumero()));
            textFieldBairro.setText(cliente.getEndereco().getBairro());
            textFieldComplemento.setText(cliente.getEndereco().getComplemento());
        }
    }

    @FXML
    private void salvar(ActionEvent event) {
        if ((TextFieldCidade.getText().trim().isEmpty()) || (comboBoxEstado.getValue() == null) ||
                (textFieldCodigoPostal.getText().trim().isEmpty()) || (textFIeldRua.getText().trim().isEmpty()) ||
                (textFieldNumero.getText().trim().isEmpty()) || (textFieldBairro.getText().trim().isEmpty())){
            JOptionPane.showMessageDialog(null,"Por favor preencha todos os campos!");

        }else {

            if (CadastroClientes.ALTERAR == 1) {

                ControleClientes.clienteSelecionado.getEndereco().setCidade(TextFieldCidade.getText());
                ControleClientes.clienteSelecionado.getEndereco().setEstado(comboBoxEstado.getValue());
                ControleClientes.clienteSelecionado.getEndereco().setCodigoPostal(Integer.parseInt(textFieldCodigoPostal.getText()));
                ControleClientes.clienteSelecionado.getEndereco().setRua(textFIeldRua.getText());
                ControleClientes.clienteSelecionado.getEndereco().setNumero(Integer.parseInt(textFieldNumero.getText()));
                ControleClientes.clienteSelecionado.getEndereco().setBairro(textFieldBairro.getText());
                ControleClientes.clienteSelecionado.getEndereco().setComplemento(textFieldComplemento.getText());

                enderecoDao.salvar(ControleClientes.clienteSelecionado.getEndereco());

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Endereço atualizado com sucesso!");

            } else {
                ControleClientes.novoCliente.getEndereco().setCidade(TextFieldCidade.getText());
                ControleClientes.novoCliente.getEndereco().setEstado(comboBoxEstado.getValue());
                ControleClientes.novoCliente.getEndereco().setCodigoPostal(Integer.parseInt(textFieldCodigoPostal.getText()));
                ControleClientes.novoCliente.getEndereco().setRua(textFIeldRua.getText());
                ControleClientes.novoCliente.getEndereco().setNumero(Integer.parseInt(textFieldNumero.getText()));
                ControleClientes.novoCliente.getEndereco().setBairro(textFieldBairro.getText());
                ControleClientes.novoCliente.getEndereco().setComplemento(textFieldComplemento.getText());

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Salve o cliente para que as alterações do contato tenham efeito!");
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage thisStage = (Stage) btnCancelar.getScene().getWindow();
        thisStage.close();
    }

}
