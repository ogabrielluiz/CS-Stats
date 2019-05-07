package DAO;

import java.util.List;
import MODEL.IEntity;

public interface IDAO {

    DaoConecta em = new DaoConecta();


    default void insert(IEntity entidade) {
        em.abreConexao();
        DaoConecta.em.persist(entidade);
        em.fecharConexao();
    }

    default void update(IEntity entidade){
        em.abreConexao();
        em.em.merge(entidade);
        em.fecharConexao();
    }

    default IEntity get(int id){
        return DaoConecta.em.find(IEntity.class, id);
    }

    default void remove(int id) {
        Object entidade = get(id);
        if (entidade != null) {
            em.abreConexao();
            em.em.remove(entidade);
            em.fecharConexao();

        }
    }

    default List<IEntity> getAll(){
            return DaoConecta.em.createQuery("SELECT e FROM " +
                    IEntity.class.getSimpleName()  + " e").getResultList();
        }

    }

