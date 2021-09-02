/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro.dao;


import barbeiro.model.Servico;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class ServicoDao {
     public void salvar(Servico servico) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(servico);
            session.getTransaction().commit();
            session.close();
            System.out.println("Registro gravado/alterado com sucesso");
        } catch (Exception erro) {
            System.out.println("Ocorreu um erro:" + erro);
        }
    }
    
    public List<Servico> consultar(String nome) {
        List<Servico> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        if (nome.length() == 0) {
            lista = session.createQuery("from Servico").getResultList();
        } else {
            lista = session.createQuery("from Servico where ser_nome like" + "'" + nome + "%'").getResultList();
        }
        session.getTransaction().commit();
        session.close();
        return lista;
    }
    
    public void excluir(Servico servico){
        try (Session session = ConexaoBanco.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(servico);
            session.getTransaction().commit();
            session.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
