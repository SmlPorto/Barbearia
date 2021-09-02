package barbeiro.dao;

import barbeiro.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class ClienteDao {

    public void salvar(Cliente cliente) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(cliente);
            session.getTransaction().commit();
            session.close();
            System.out.println("Registro gravado/alterado com sucesso");
        } catch (Exception erro) {
            System.out.println("Ocorreu um erro:" + erro);
        }
    }
    
    public List<Cliente> consultar(String descricao) {
        List<Cliente> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        if (descricao.length() == 0) {
            lista = session.createQuery("from Cliente").getResultList();
        } else {
            lista = session.createQuery("from Cliente where cli_nome like" + "'" + descricao + "%'").getResultList();
        }
        session.getTransaction().commit();
        session.close();
        return lista;
    }
    public List<Cliente> consultarCpf(String cpf) {
        List<Cliente> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        if (cpf.length() == 0) {
            lista = session.createQuery("from Cliente").getResultList();
        } else {
            lista = session.createQuery("from Cliente where cli_cpf like" + "'" + cpf + "%'").getResultList();
        }
        session.getTransaction().commit();
        session.close();
        return lista;
    }
    public long consultarNRegistros(int mes, int ano) {
        int mesfim = mes+1;
        long qtd = 0;
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        if(mes == 12){
            qtd = (long) session.createQuery("select count(id) from Cliente WHERE cli_data_cadastro BETWEEN '" + ano + "-" + mes + "-01' and '" + ano + "-" + mes + "31' ").uniqueResult();
        }else{
            qtd = (long) session.createQuery("select count(id) from Cliente WHERE cli_data_cadastro BETWEEN '" + ano + "-" + mes + "-01' and '" + ano + "-" + mesfim + "-01' ").uniqueResult();
        }

        session.getTransaction().commit();
        session.close();
        return qtd;
    }
    
    public void excluir(Cliente cliente){
        try (Session session = ConexaoBanco.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(cliente);
            session.getTransaction().commit();
            session.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public long consultarTodosNRegistros() {
        long qtd = 0;
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();

        qtd = (long) session.createQuery("select count(id) from Cliente").uniqueResult();


        session.getTransaction().commit();
        session.close();
        return qtd;
    }
}
