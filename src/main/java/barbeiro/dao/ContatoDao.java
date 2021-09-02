package barbeiro.dao;

import barbeiro.model.Contato;
import org.hibernate.Session;

public class ContatoDao {

    public void salvar(Contato data) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(data);
            session.getTransaction().commit();
            session.close();
            System.out.println("Registro gravado/alterado com sucesso");
        } catch (Exception erro) {
            System.out.println("Ocorreu um erro:" + erro);
        }
    }

}
