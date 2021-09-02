package barbeiro.controller;

import barbeiro.dao.AgendamentoDao;
import barbeiro.model.Agendamento;
import barbeiro.model.Cliente;
import barbeiro.model.Servico;
import barbeiro.model.Usuario;
import barbeiro.utils.ColumnFormatter;
import barbeiro.utils.ComboBoxLists;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AgendamentoController implements Initializable, ICadastro{
    private AgendamentoDao agendamentoDao = new AgendamentoDao();
    private ObservableList<Agendamento> observableList = FXCollections.observableArrayList();
    private List<Agendamento> listaAgendamentos;
    public static Agendamento agendamentoSelecionado = new Agendamento();
    public static Agendamento novoAgendamento = new Agendamento();
    private int pesquisaSelecionada = 0;
    @FXML
    private TableView<Agendamento> tableView;
    @FXML
    private ComboBox<?> cbPesquisa;
    @FXML
    private HBox hBoxPesquisa;
    @FXML
    private DatePicker datePickerAgendamento;
    @FXML
    private Button btnLimpar1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarColunasTabela();
        atualizarTabela();
        gerarCb();
    }
    private void gerarCb(){
        cbPesquisa.setItems(ComboBoxLists.gerarPesquisaAgendamento());
        cbPesquisa.getSelectionModel().selectFirst();
    }
    @FXML
    private void atualizarAgendamentos(){
        String res;
        res = cbPesquisa.getSelectionModel().getSelectedItem().toString();
        switch (res){
            case "Data Específica":
                hBoxPesquisa.setVisible(true);
                btnLimpar1.setVisible(true);
                pesquisaSelecionada = 0;
                break;
        }
    }

    @FXML
    private void adicionar(ActionEvent event) throws IOException {
        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setPago(false);
        LocalDate date = LocalDate.of(1900, Month.JANUARY, 1);
        novoAgendamento.setData(date);
        LocalTime hora = LocalTime.of(0,0);
        novoAgendamento.setHora(hora);
        Servico servico = new Servico();
        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();
        novoAgendamento.setUsuario(usuario);
        novoAgendamento.setCliente(cliente);
        novoAgendamento.setServico(servico);


        CadastroAgendamentoController.ALTERAR = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CadastroAgendamento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Adicionar Agendamento");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        atualizarTabela();
    }

    @FXML
    private void alterar(ActionEvent event) throws IOException {
        CadastroAgendamentoController.ALTERAR = 1;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CadastroAgendamento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Alterar Agendamento");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        atualizarTabela();
    }

    @FXML
    private void excluir(ActionEvent event) {
        agendamentoDao.excluir(agendamentoSelecionado);
        JOptionPane.showMessageDialog(null,"Serviço excluido com sucesso");
        atualizarTabela();
    }

    @Override
    public void criarColunasTabela() {
        tableView.getColumns().clear();
        TableColumn<Agendamento, Long> colunaId = new TableColumn<>("Id");
        TableColumn<Agendamento,LocalDate> colunaData = new TableColumn<>("Data");
        TableColumn<Agendamento, String> colunaCliente = new TableColumn<>("Cliente");
        TableColumn<Agendamento, String> colunaUsuario = new TableColumn<>("Funcionário");
        TableColumn<Agendamento, String> colunaServico= new TableColumn<>("Serviço");
        TableColumn<Agendamento, Boolean> colunaPago = new TableColumn<>("Pago");

        tableView.getColumns().addAll(colunaId, colunaData,colunaCliente,colunaUsuario,colunaServico,colunaPago);
        colunaId.setCellValueFactory(new PropertyValueFactory("id"));
        colunaData.setCellValueFactory(new PropertyValueFactory("data"));
        colunaData.setCellFactory(new ColumnFormatter<>(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        colunaPago.setCellValueFactory(new PropertyValueFactory("pago"));
        colunaUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsuario().getNome()));
        colunaCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNome()));
        colunaServico.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServico().getNome()));
    }

    @Override
    public void atualizarTabela() {
        observableList.clear();
        switch (pesquisaSelecionada){
            case 0:
                if(datePickerAgendamento.getValue() == null){
                    listaAgendamentos = agendamentoDao.consultar();
                }else{
                    listaAgendamentos = agendamentoDao.consultarData(datePickerAgendamento.getValue());
                }

                break;
        }

        for (Agendamento a : listaAgendamentos) {
            observableList.add(a);
        }
        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();
        agendamentoSelecionado = tableView.getSelectionModel().getSelectedItem();
    }
    @FXML
    private void limpar(){
        datePickerAgendamento.setValue(null);
    }
    @Override
    public void setCamposFormulario() {

    }

    @Override
    public void limparCamposFormulario() {

    }

    @FXML
    private void atualizarSelecionado(MouseEvent event) {
        agendamentoSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
    }
}
