package DAO;

import MODEL.IEntity;

import java.util.List;

public class DaoCRUD {

    private DaoConecta em = new DaoConecta();

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

    public List list(){
        return DaoConecta.em.createQuery("SELECT e FROM " +
                IEntity.class.getSimpleName()  + " e").getResultList();
    }

}
