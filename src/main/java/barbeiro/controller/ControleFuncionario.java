package barbeiro.controller;

import barbeiro.dao.FuncionarioDao;
import barbeiro.model.Funcionario;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

public class ControleFuncionario implements Initializable, Cadastro {
    
    private FuncionarioDao funcionarioDao = new FuncionarioDao();
    private ObservableList<Funcionario> observableList = FXCollections.observableArrayList();
    private List<Funcionario> listaFuncionarios;
    public static Funcionario funcionarioSelecionado = new Funcionario();
    public static Funcionario novoFuncionario = new Funcionario();
    @FXML
    private TableView<Funcionario> tableView;
    @FXML
    private TextField textFieldPesquisa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarColunasTabela();
        atualizarTabela();
    }    

    @Override
    public void criarColunasTabela() {
        tableView.getColumns().clear();
        TableColumn<Funcionario, Long> colunaId = new TableColumn<>("Id");
        TableColumn<Funcionario, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Funcionario, String> colunaCpf = new TableColumn<>("Cpf");
        TableColumn<Funcionario, String> colunaEmail = new TableColumn<>("Email");
        TableColumn<Funcionario, String> colunaSenha = new TableColumn<>("Senha");
        TableColumn<Funcionario, Integer> colunaCargo = new TableColumn<>("Cargo");
        TableColumn<Funcionario, Integer> colunaFuncao = new TableColumn<>("Função");

        tableView.getColumns().addAll(colunaId, colunaNome, colunaCpf, colunaEmail,colunaSenha,colunaCargo,colunaFuncao);
        colunaId.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colunaSenha.setCellValueFactory(new PropertyValueFactory("senha"));
        colunaCargo.setCellValueFactory(new PropertyValueFactory("cargo"));
        colunaFuncao.setCellValueFactory(new PropertyValueFactory("funcao"));
        
    }

    @Override
    @FXML
    public void atualizarTabela() {   
        observableList.clear();
        listaFuncionarios = funcionarioDao.consultar(textFieldPesquisa.getText());

        for (Funcionario s : listaFuncionarios) {
            observableList.add(s);
        }
        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();
        funcionarioSelecionado = tableView.getSelectionModel().getSelectedItem();
    }
    @FXML
    private void alterar(ActionEvent event) throws IOException {
        CadastroFuncionario.ALTERAR = 1;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CadastrarUsuarios.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Alterar Usuário");
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.showAndWait();
        atualizarTabela();
    }

    @FXML
    private void excluir(ActionEvent event) {
        funcionarioDao.excluir(funcionarioSelecionado);
        JOptionPane.showMessageDialog(null,"Funcionário excluido com sucesso!");
        atualizarTabela();
    }

    @FXML
    private void adicionar(ActionEvent event) throws IOException {
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome("");
        novoFuncionario.setCpf("");
        novoFuncionario.setEmail("");
        novoFuncionario.setSenha("");
        novoFuncionario.setCargo(0);
        novoFuncionario.setFuncao("");

        CadastroFuncionario.ALTERAR = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CadastrarUsuarios.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Adicionar Usuário");
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.showAndWait();
        atualizarTabela();
    }

    @FXML
    private void atualizarSelecionado(MouseEvent event) {
        funcionarioSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
    }
    @Override
    public void setCamposFormulario() {    
    }

    @Override
    public void limparCamposFormulario() {    
    }
    
}
