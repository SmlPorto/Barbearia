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

public class ContatoClienteController implements Initializable {

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
            if (CadastroClientesController.ALTERAR == 1) {

                ClientesController.clienteSelecionado.getContato().setEmail(TextFieldEmail.getText());
                ClientesController.clienteSelecionado.getContato().setTelefoneCelular(TextFieldTelefoneC.getText());
                ClientesController.clienteSelecionado.getContato().setTelefoneFixo(TextFieldTelefoneF.getText());
                contatoDao.salvar(ClientesController.clienteSelecionado.getContato());

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Contato atualizado com sucesso!");

            } else {
                ClientesController.novoCliente.getContato().setEmail(TextFieldEmail.getText());
                ClientesController.novoCliente.getContato().setTelefoneCelular(TextFieldTelefoneC.getText());
                ClientesController.novoCliente.getContato().setTelefoneFixo(TextFieldTelefoneF.getText());

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Salve o cliente para que as alterações tenham efeito");
            }

        }
    }

    private void carregarDados() {
        if (CadastroClientesController.ALTERAR == 1) {
            Cliente cliente = ClientesController.clienteSelecionado;
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
