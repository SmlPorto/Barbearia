/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComboBoxLists {
    private static ObservableList<String> obsList;
    private static ObservableList<String> obsEstados;
    private static ObservableList<Integer> obsListAnos;
    private static ObservableList<String> obsListTiposFunc;
    public static ObservableList gerarExibicaoCliente(){
        obsList = FXCollections.observableArrayList(
                "Todos os dados",
                "Somente o Cliente",
                "Somente o Endereço",
                "Somente o Contato"
        );
        return obsList;
    }
      public static ObservableList gerarPesquisaCliente(){
        obsList = FXCollections.observableArrayList(
                "Nome",
                "CPF"
        );
        return obsList;
    }
     public static ObservableList gerarEstados(){
        obsEstados = FXCollections.observableArrayList(
                "AC",
                "AL",
                "AP",
                "AM",
                "BA",
                "CE",
                "DF",
                "ES",
                "GO",
                "MA",
                "MT",
                "MS",
                "MG",
                "PA",
                "PB",
                "PR",
                "PE",
                "PI",
                "RJ",
                "RN",
                "RS",
                "RO",
                "RR",
                "SC",
                "SP",
                "SE",
                "TO"
        );
        return obsEstados;
    }
    public static ObservableList gerarPorte(){
        obsList = FXCollections.observableArrayList(
                "Pequeno",
                "Médio",
                "Grande"
        );
        return obsList;
    }
    public static ObservableList gerarPago(){
        obsList = FXCollections.observableArrayList(
                "Não",
                "Sim"
        );
        return obsList;
    }
    public static ObservableList gerarPesquisaAgendamento(){
        obsList = FXCollections.observableArrayList(
                "Data Específica"
        );
        return obsList;
    }
    public static ObservableList gerarAnos(){
        obsListAnos= FXCollections.observableArrayList(
                2021,
                2022,
                2023,
                2024,
                2025
        );
        return obsListAnos;
    }
    public static ObservableList gerarTipoFunc(){
        obsListTiposFunc= FXCollections.observableArrayList(
                "Funcionário",
                "Administrador"
        );
        return obsListTiposFunc;
    }
}
