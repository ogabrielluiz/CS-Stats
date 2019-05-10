package DAO;

import MODEL.IEntity;
import MODEL.TbEquipesEntity;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

public class DaoCRUD {

    private static DaoConecta em = new DaoConecta();

    public static void insert(IEntity entidade) {
        em.abreConexao();
        DaoConecta.em.persist(entidade);
        em.fecharConexao();
    }

    public void update(IEntity entidade){
        em.abreConexao();
        DaoConecta.em.merge(entidade);
        em.fecharConexao();
    }

    public IEntity getById(int id){
        return DaoConecta.em.find(IEntity.class, id);
    }

    public IEntity getByNome(String nome){
        return DaoConecta.em.find(IEntity.class, nome);
    }

    public void remove(int id) {
        IEntity entidade = getById(id);
        if (entidade != null) {
            em.abreConexao();
            DaoConecta.em.remove(entidade);
            em.fecharConexao();
        }
    }

    public static List list_names_from(String nome_tabela){
        Query query = DaoConecta.em.createQuery(
                "SELECT" + "a" + "FROM " + nome_tabela + "a", IEntity.class
        );

        return query.getResultList();
    }

}
