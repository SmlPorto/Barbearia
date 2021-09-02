package barbeiro.controller;

import barbeiro.dao.ClienteDao;
import barbeiro.dao.ContatoDao;
import barbeiro.dao.EnderecoDao;
import barbeiro.model.Cliente;
import barbeiro.model.Contato;
import barbeiro.model.Endereco;
import barbeiro.utils.ColumnFormatter;
import barbeiro.utils.ComboBoxLists;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

public class ControleClientes implements Initializable, Cadastro {

    private ClienteDao clienteDao = new ClienteDao();
    private ContatoDao contatoDao = new ContatoDao();
    private EnderecoDao enderecoDao = new EnderecoDao();
    private ObservableList<Cliente> observableList = FXCollections.observableArrayList();
    private List<Cliente> listaClientes;
    public static Cliente clienteSelecionado = new Cliente();
    public static Cliente novoCliente = new Cliente();
    private int pesquisaSelecionada = 0;
    @FXML
    private TableView<Cliente> tableView;
    @FXML
    private TextField textFieldPesquisa;
    @FXML
    private ComboBox<String> cbExibicao;
    @FXML
    private ComboBox<?> cbPesquisa;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbPesquisa.setItems(ComboBoxLists.gerarPesquisaCliente());
        cbPesquisa.getSelectionModel().selectFirst();
        criarColunasTabela();
        atualizarTabela();
    }

    @Override
    public void criarColunasTabela() {
        tableView.getColumns().clear();
        TableColumn<Cliente, Long> colunaId = new TableColumn<>("Id");
        TableColumn<Cliente, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Cliente, String> colunaCpf = new TableColumn<>("Cpf");
        TableColumn colunaDataNascimento = new TableColumn<>("Data de Nascimento");

        TableColumn<Cliente, String> colunaCidade = new TableColumn<>("Cidade");
        TableColumn<Cliente, String> colunaEstado = new TableColumn<>("Estado");
        TableColumn<Cliente, String> colunaCodigoPostal = new TableColumn<>("Código Postal");
        TableColumn<Cliente, String> colunaRua = new TableColumn<>("Rua");
        TableColumn<Cliente, String> colunaNumero = new TableColumn<>("Número");
        TableColumn<Cliente, String> colunaBairro = new TableColumn<>("Bairro");
        TableColumn<Cliente, String> colunaComplemento = new TableColumn<>("Complemento");

        TableColumn<Cliente, String> colunaEmail = new TableColumn<>("Email");
        TableColumn<Cliente, String> colunaTelefoneCelular = new TableColumn<>("Telefone celular");
        TableColumn<Cliente, String> colunaTelefoneFixo = new TableColumn<>("Telefone fixo");

            tableView.getColumns().addAll(colunaId, colunaNome, colunaCpf, colunaDataNascimento, colunaCidade, colunaEstado, colunaCodigoPostal, colunaRua,
                    colunaNumero, colunaBairro, colunaComplemento, colunaEmail, colunaTelefoneCelular, colunaTelefoneFixo);

            colunaId.setCellValueFactory(new PropertyValueFactory("id"));
            colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
            colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
            colunaDataNascimento.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
            colunaDataNascimento.setCellFactory(new ColumnFormatter<>(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            colunaCidade.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndereco().getCidade()));
            colunaEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndereco().getEstado()));
            colunaCodigoPostal.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEndereco().getCodigoPostal()).asString());
            colunaRua.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndereco().getRua()));
            colunaNumero.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEndereco().getNumero()).asString());
            colunaBairro.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndereco().getBairro()));
            colunaComplemento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndereco().getComplemento()));

            colunaEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContato().getEmail()));
            colunaTelefoneCelular.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContato().getTelefoneCelular()));
            colunaTelefoneFixo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContato().getTelefoneFixo()));

    }

    @FXML
    private void mudarPesquisa(){
        String res = cbPesquisa.getSelectionModel().getSelectedItem().toString();
        switch (res){
            case "Nome":
                pesquisaSelecionada = 0;
                break;
            case "CPF":
                pesquisaSelecionada = 1;
                break;
        }
    }

    @Override
    @FXML
    public void atualizarTabela() {
        if (pesquisaSelecionada == 0){
            observableList.clear();
            listaClientes = clienteDao.consultar(textFieldPesquisa.getText());

            for (Cliente c : listaClientes) {
                observableList.add(c);
            }
            tableView.getItems().setAll(observableList);
            tableView.getSelectionModel().selectFirst();
            clienteSelecionado = tableView.getSelectionModel().getSelectedItem();
        }else if(pesquisaSelecionada == 1){
            observableList.clear();
            listaClientes = clienteDao.consultarCpf(textFieldPesquisa.getText());

            for (Cliente c : listaClientes) {
                observableList.add(c);
            }
            tableView.getItems().setAll(observableList);
            tableView.getSelectionModel().selectFirst();
            clienteSelecionado = tableView.getSelectionModel().getSelectedItem();
        }


    }

    @Override
    @FXML
    public void setCamposFormulario() {
        clienteSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
    }

    @Override
    public void limparCamposFormulario() {
    }

    @FXML
    private void adicionar(ActionEvent event) throws IOException {

        Endereco end = new Endereco();
        end.setCidade("");
        end.setEstado("");
        end.setCodigoPostal(0);
        end.setRua("");
        end.setNumero(0);
        end.setBairro("");
        end.setComplemento("");

        Contato con = new Contato();
        con.setEmail("");
        con.setTelefoneCelular("");
        con.setTelefoneFixo("");

        novoCliente.setNome("");
        novoCliente.setCpf("");
        LocalDate date = LocalDate.of(1900, Month.JANUARY, 1);
        novoCliente.setDataNascimento(date);
        novoCliente.setEndereco(end);
        novoCliente.setContato(con);

        CadastroClientes.ALTERAR = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CadastroClientes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Adicionar Cliente");
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.showAndWait();
        atualizarTabela();

    }

    @FXML
    private void alterar(ActionEvent event) throws IOException {

        CadastroClientes.ALTERAR = 1;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CadastroClientes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Alterar Cliente");
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.showAndWait();
        atualizarTabela();
    }

    @FXML
    private void excluir(ActionEvent event) {
        clienteDao.excluir(clienteSelecionado);
        JOptionPane.showMessageDialog(null,"Cliente excluido com sucesso");
        atualizarTabela();
    }

}
