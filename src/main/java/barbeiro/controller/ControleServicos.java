package barbeiro.controller;

import barbeiro.dao.ServicoDao;
import barbeiro.model.Servico;
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

public class ControleServicos implements Initializable, Cadastro {

    private ServicoDao servicoDao = new ServicoDao();
    private ObservableList<Servico> observableList = FXCollections.observableArrayList();
    private List<Servico> listaServicos;
    public static Servico servicoSelecionado = new Servico();
    public static Servico novoServico = new Servico();
    @FXML
    private TableView<Servico> tableView;
    @FXML
    private TextField textFieldPesquisa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarColunasTabela();
        atualizarTabela();
    }


    @FXML
    private void adicionar(ActionEvent event) throws IOException {
        Servico novoServico = new Servico();
        novoServico.setNome("");
        novoServico.setPreco(0.0);

        CadastroServicos.ALTERAR = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CadastroServicos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Adicionar Servico");
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.showAndWait();
        atualizarTabela();
    }

    @FXML
    private void alterar(ActionEvent event) throws IOException {
        CadastroServicos.ALTERAR = 1;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CadastroServicos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Alterar Servico");
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.showAndWait();
        atualizarTabela();
    }

    @FXML
    private void excluir(ActionEvent event) {
        servicoDao.excluir(servicoSelecionado);
        JOptionPane.showMessageDialog(null,"Serviço excluído com sucesso!");
        atualizarTabela();
    }

    @Override
    public void criarColunasTabela() {
        tableView.getColumns().clear();
        TableColumn<Servico, Long> colunaId = new TableColumn<>("Id");
        TableColumn<Servico, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Servico, Double> colunaPreco = new TableColumn<>("Preço");

        tableView.getColumns().addAll(colunaId, colunaNome, colunaPreco);
        colunaId.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory("preco"));

    }

    @Override
    @FXML
    public void atualizarTabela() {
        observableList.clear();
        listaServicos = servicoDao.consultar(textFieldPesquisa.getText());

        for (Servico s : listaServicos) {
            observableList.add(s);
        }
        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();
        servicoSelecionado = tableView.getSelectionModel().getSelectedItem();
    }

    @Override
    public void setCamposFormulario() {
       
    }

    @Override
    public void limparCamposFormulario() {

    }

    @FXML
    private void atualizarSelecionado(MouseEvent event) {
        servicoSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
    }

}
