/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro.dao;

import barbeiro.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexaoBanco {

    private static SessionFactory conexao = null;
    private static Configuration configuracao;

    private static SessionFactory buildSessionFactory() {
        configuracao = new Configuration().configure();

        configuracao.setProperty("hibernate.connection.username", "root");
        configuracao.setProperty("hibernate.connection.password", "");

        configuracao.addPackage("barbeiro.model").addAnnotatedClass(Cliente.class);
        configuracao.addPackage("barbeiro.model").addAnnotatedClass(Endereco.class);
        configuracao.addPackage("barbeiro.model").addAnnotatedClass(Contato.class);
        configuracao.addPackage("barbeiro.model").addAnnotatedClass(Servico.class);
        configuracao.addPackage("barbeiro.model").addAnnotatedClass(Funcionario.class);
        configuracao.addPackage("barbeiro.model").addAnnotatedClass(Agendamento.class);

        conexao = configuracao.buildSessionFactory();

        return conexao;
    }

    public static SessionFactory getSessionFactory() {
        if (conexao == null) {
            conexao = buildSessionFactory();
        }
        return conexao;
    }
}
