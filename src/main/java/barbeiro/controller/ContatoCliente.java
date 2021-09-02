package barbeiro.controller;

import barbeiro.dao.ContatoDao;
import barbeiro.model.Cliente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class ContatoCliente implements Initializable {

    private ContatoDao contatoDao = new ContatoDao();
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField TextFieldEmail;
    @FXML
    private TextField TextFieldTelefoneC;
    @FXML
    private TextField TextFieldTelefoneF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarDados();
    }

    @FXML
    private void salvar(ActionEvent event) throws IOException {
        if(TextFieldEmail.getText().trim().isEmpty() && TextFieldTelefoneC.getText().trim().isEmpty() && TextFieldTelefoneF.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Por favor preencha ao menos um contato!");

        }else {
            if (CadastroClientes.ALTERAR == 1) {

                ControleClientes.clienteSelecionado.getContato().setEmail(TextFieldEmail.getText());
                ControleClientes.clienteSelecionado.getContato().setTelefoneCelular(TextFieldTelefoneC.getText());
                ControleClientes.clienteSelecionado.getContato().setTelefoneFixo(TextFieldTelefoneF.getText());
                contatoDao.salvar(ControleClientes.clienteSelecionado.getContato());

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Contato atualizado com sucesso!");

            } else {
                ControleClientes.novoCliente.getContato().setEmail(TextFieldEmail.getText());
                ControleClientes.novoCliente.getContato().setTelefoneCelular(TextFieldTelefoneC.getText());
                ControleClientes.novoCliente.getContato().setTelefoneFixo(TextFieldTelefoneF.getText());

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Salve o cliente para que as alterações tenham efeito");
            }

        }
    }

    private void carregarDados() {
        if (CadastroClientes.ALTERAR == 1) {
            Cliente cliente = ControleClientes.clienteSelecionado;
            TextFieldEmail.setText(cliente.getContato().getEmail());
            TextFieldTelefoneC.setText(cliente.getContato().getTelefoneCelular());
            TextFieldTelefoneF.setText(cliente.getContato().getTelefoneFixo());
        }
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        Stage thisStage = (Stage) btnCancelar.getScene().getWindow();
        thisStage.close();
    }

}
