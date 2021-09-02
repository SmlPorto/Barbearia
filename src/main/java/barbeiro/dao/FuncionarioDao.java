/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro.dao;

import barbeiro.model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class FuncionarioDao {
    public void salvar(Funcionario funcionario) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(funcionario);
            session.getTransaction().commit();
            session.close();
            System.out.println("Registro gravado/alterado com sucesso");
        } catch (Exception erro) {
            System.out.println("Ocorreu um erro:" + erro);
        }
    }
    
    public List<Funcionario> consultar(String nome) {
        List<Funcionario> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        if (nome.length() == 0) {
            lista = session.createQuery("from Funcionario").getResultList();
        } else {
            lista = session.createQuery("from Funcionario where usu_nome like" + "'" + nome + "%'").getResultList();
        }
        session.getTransaction().commit();
        session.close();
        return lista;
    }

    public Funcionario consultarLogin (String email, String senha) {
        Funcionario user = new Funcionario();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        user = ((Funcionario) session.createQuery("from Funcionario where usu_email = " + "'" + email + "'" + "and senha =" + "'" + senha + "'").uniqueResult());

        return user;
    }
    
    public void excluir(Funcionario funcionario){
        try (Session session = ConexaoBanco.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(funcionario);
            session.getTransaction().commit();
            session.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

}
