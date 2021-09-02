package barbeiro.dao;

import barbeiro.model.Agendamento;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDao {
    public void salvar(Agendamento agendamento) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(agendamento);
            session.getTransaction().commit();
            session.close();
            System.out.println("Registro gravado/alterado com sucesso");
        } catch (Exception erro) {
            System.out.println("Ocorreu um erro:" + erro);
        }
    }
    public List<Agendamento> consultar() {
        List<Agendamento> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        lista = session.createQuery("from Agendamento").getResultList();
        session.getTransaction().commit();
        session.close();
        return lista;
    }
    public List<Agendamento> consultarData(LocalDate data) {
        List<Agendamento> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        lista = session.createQuery("from Agendamento WHERE age_data = '"+data+"'").getResultList();
        session.getTransaction().commit();
        session.close();
        return lista;
    }
    public List<Agendamento> consultarIntervalosData(LocalDate dataInicio,LocalDate  dataFim) {
        List<Agendamento> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        lista = session.createQuery("from Agendamento WHERE age_data BETWEEN '"+dataInicio+"' AND '"+dataFim+"'").getResultList();
        session.getTransaction().commit();
        session.close();
        return lista;
    }

    public void excluir(Agendamento agendamento){
        try (Session session = ConexaoBanco.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(agendamento);
            session.getTransaction().commit();
            session.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public long consultarNRegistros(int mes, int ano) {
        int mesfim = mes+1;
        long qtd = 0;
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        if(mes == 12){
            qtd = (long) session.createQuery("select count(id) from Agendamento WHERE age_data_cadastro BETWEEN '" + ano + "-" + mes + "-01' and '" + ano + "-" + mes + "31' ").uniqueResult();
        }else{
            qtd = (long) session.createQuery("select count(id) from Agendamento WHERE age_data_cadastro BETWEEN '" + ano + "-" + mes + "-01' and '" + ano + "-" + mesfim + "-01' ").uniqueResult();
        }

        session.getTransaction().commit();
        session.close();
        return qtd;
    }
    public long consultarTodosNRegistros() {
        long qtd = 0;
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();

        qtd = (long) session.createQuery("select count(id) from Agendamento").uniqueResult();


        session.getTransaction().commit();
        session.close();
        return qtd;
    }
    public long consultarNPagamentosPendentes() {
        long qtd = 0;
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();

        qtd = (long) session.createQuery("select count(id) from Agendamento WHERE age_pago = 0").uniqueResult();


        session.getTransaction().commit();
        session.close();
        return qtd;
    }
}
