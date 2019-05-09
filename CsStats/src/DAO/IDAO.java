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

    default IEntity getById(int id){
        return DaoConecta.em.find(IEntity.class, id);
    }

    default IEntity getByNome(String nome){
        return DaoConecta.em.find(IEntity.class, nome);
    }

    default void remove(int id) {
        Object entidade = getById(id);
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

