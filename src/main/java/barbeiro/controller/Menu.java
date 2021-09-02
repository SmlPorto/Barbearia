package barbeiro.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;

public class Menu implements Initializable {
    private String selecionado = "";
    @FXML
    private BorderPane mainPane;
    @FXML
    private HBox btnClientes;
    @FXML
    private HBox btnAgendamentos;
    @FXML
    private HBox btnServicos;
    @FXML
    private HBox btnFuncionarios;
    @FXML
    private HBox btnSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUI("Agendamento");
    }

    @FXML
    private void handleOpenClientes(){ loadUI("Clientes"); }
    @FXML
    private void handleOpenServicos(MouseEvent event) {
         loadUI("Servicos");
    }
    @FXML
    private void handleOpenAgendamentos(){
        loadUI("Agendamento");
    }
    @FXML
    private void handleOpenFuncionarios(MouseEvent event) {
         switch (Login.funcionario.getCargo()){
            case 0:
                JOptionPane.showMessageDialog(null,"Você não possui permissão para acessar esse conteúdo!");
                break;
            case 1:
                loadUI("Funcionarios");
                break;
        }

    }
    @FXML
    private void handleOpenBackup(){
        loadUI("Backup");
    }
    private void loadUI(String ui){
        
        Parent root = null;    
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/"+ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(root);
        selecionado = ui;
        atualizarStyle();
    }
    private void atualizarStyle(){
        resetarStyle();
        switch (selecionado){
            case "Clientes":
                btnClientes.setStyle("-fx-background-color: #020915; -fx-border-width: 0px 0px 4px 0px;-fx-border-color: #fff;-fx-alignment: center");
                break;
            case "Servicos":
                btnServicos.setStyle("-fx-background-color: #020915; -fx-border-width: 0px 0px 4px 0px;-fx-border-color: #fff;-fx-alignment: center");
                break;
            case "Agendamento":
                btnAgendamentos.setStyle("-fx-background-color: #020915; -fx-border-width: 0px 0px 4px 0px;-fx-border-color: #fff;-fx-alignment: center");
                break;
            case "Funcionarios":
                btnFuncionarios.setStyle("-fx-background-color: #020915; -fx-border-width: 0px 0px 4px 0px;-fx-border-color: #fff;-fx-alignment: center");
                break;
        }
    }
    private void resetarStyle(){
        btnClientes.setStyle("-fx-alignment: center");
        btnServicos.setStyle("-fx-alignment: center");
        btnFuncionarios.setStyle("-fx-alignment: center");
        btnAgendamentos.setStyle("-fx-alignment: center");

    }

    @FXML
    private void handleSignOut() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
        Stage thisStage = (Stage) btnSair.getScene().getWindow();
        thisStage.close();
    }

    


    
}
