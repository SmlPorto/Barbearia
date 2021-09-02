package barbeiro.controller;

import barbeiro.dao.AgendamentoDao;
import barbeiro.dao.ClienteDao;
import barbeiro.dao.ServicoDao;
import barbeiro.dao.FuncionarioDao;
import barbeiro.model.Agendamento;
import barbeiro.model.Cliente;
import barbeiro.model.Servico;
import barbeiro.model.Funcionario;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import barbeiro.utils.ComboBoxLists;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import javax.swing.*;

public class CadastroAgendamento implements Initializable {
    private AgendamentoDao agendamentooDao = new AgendamentoDao();
    private FuncionarioDao funcionarioDao = new FuncionarioDao();
    private ClienteDao clienteDao = new ClienteDao();
    private ServicoDao servicoDao = new ServicoDao();
    public static int ALTERAR = 0;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;
    @FXML
    private DatePicker datePickerData;
    @FXML
    private ComboBox<Funcionario> comboBoxFuncionario;
    @FXML
    private ComboBox<Cliente> comboBoxCliente;
    @FXML
    private ComboBox<Servico> comboBoxServico;
    @FXML
    private ComboBox<String> comboBoxPago;
    @FXML
    private JFXTimePicker timePickerHora;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarUsuarios();
        carregarServico();
        carregarCliente();
        carregarDados();
        carregarPago();

    }
    private void carregarPago(){
        comboBoxPago.setItems(ComboBoxLists.gerarPago());
    }

    private void carregarUsuarios(){
        List<Funcionario> funcionarios = funcionarioDao.consultar("");
        ObservableList<Funcionario> usuariosObs = FXCollections.observableArrayList();
        for(Funcionario u: funcionarios){
            usuariosObs.add(u);
        }
        comboBoxFuncionario.setItems(usuariosObs);
    }
    private void carregarCliente(){
        List<Cliente> clientes = clienteDao.consultar("");
        ObservableList<Cliente> clientesObs = FXCollections.observableArrayList();
        for(Cliente c:clientes){
            clientesObs.add(c);
        }
        comboBoxCliente.setItems(clientesObs);
    }
    private void carregarServico(){
        List<Servico> servicos = servicoDao.consultar("");
        ObservableList<Servico> servicosObs = FXCollections.observableArrayList();
        for(Servico s:servicos){
            servicosObs.add(s);
        }
        comboBoxServico.setItems(servicosObs);
    }
    private void carregarDados() {
        if (ALTERAR == 1) {
            Agendamento agendamento = ControleAgendamento.agendamentoSelecionado;
            datePickerData.setValue(agendamento.getData());
            timePickerHora.setValue(agendamento.getHora());
            comboBoxFuncionario.getSelectionModel().select(agendamento.getUsuario());
            comboBoxCliente.getSelectionModel().select(agendamento.getCliente());
            comboBoxServico.getSelectionModel().select(agendamento.getServico());
            boolean res = agendamento.isPago();
            if (!res) {
                comboBoxPago.getSelectionModel().select("Não");
            } else {
                comboBoxPago.getSelectionModel().select("Sim");
            }

        }
    }

    @FXML
    private void salvar(ActionEvent event) {
        if ((datePickerData.getValue() == null) ||  (comboBoxFuncionario.getValue() == null) || (comboBoxCliente.getValue() == null) ||
                (comboBoxServico.getValue() == null) || (comboBoxPago.getValue() == null) || (timePickerHora.getValue() == null)){
            JOptionPane.showMessageDialog(null,"Por Favor preencha todos os campos!");
        }else {
            if (ALTERAR == 1) {
                ControleAgendamento.agendamentoSelecionado.setUsuario(comboBoxFuncionario.getValue());
                ControleAgendamento.agendamentoSelecionado.setServico(comboBoxServico.getValue());
                ControleAgendamento.agendamentoSelecionado.setCliente(comboBoxCliente.getValue());
                ControleAgendamento.agendamentoSelecionado.setData(datePickerData.getValue());
                ControleAgendamento.agendamentoSelecionado.setHora(timePickerHora.getValue());

                String res = comboBoxPago.getValue();
                switch (res){
                    case "Sim":
                        ControleAgendamento.agendamentoSelecionado.setPago(true);
                        break;
                    case "Não":
                        ControleAgendamento.agendamentoSelecionado.setPago(false);
                        break;
                }
                agendamentooDao.salvar(ControleAgendamento.agendamentoSelecionado);

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Agendamento atualizado com sucesso!");

            }else{


                ControleAgendamento.novoAgendamento.setUsuario(comboBoxFuncionario.getValue());
                ControleAgendamento.novoAgendamento.setServico(comboBoxServico.getValue());
                ControleAgendamento.novoAgendamento.setCliente(comboBoxCliente.getValue());
                ControleAgendamento.novoAgendamento.setData(datePickerData.getValue());
                ControleAgendamento.novoAgendamento.setHora(timePickerHora.getValue());
                String res = comboBoxPago.getValue();
                switch (res){
                    case "Sim":
                        ControleAgendamento.novoAgendamento.setPago(true);
                        break;
                    case "Não":
                        ControleAgendamento.novoAgendamento.setPago(false);
                        break;
                }
                ControleAgendamento.novoAgendamento.setDataCadastro(LocalDate.now());
                agendamentooDao.salvar(ControleAgendamento.novoAgendamento);

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Agendamento cadastrado com sucesso!");


            }
        }


    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage thisStage = (Stage) btnCancelar.getScene().getWindow();
        thisStage.close();
    }

}
