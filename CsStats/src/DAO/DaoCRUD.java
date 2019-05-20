package DAO;

import MODEL.IEntity;
import MODEL.TbEquipesEntity;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import static DAO.DaoConecta.abreConexao;
import static DAO.DaoConecta.fecharConexao;

public class DaoCRUD {

    private static DaoConecta em = new DaoConecta();

    public static void insert(IEntity entidade) {
        abreConexao();
        DaoConecta.em.persist(entidade);
        fecharConexao();
    }

    public static void update(IEntity entidade){
        abreConexao();
        DaoConecta.em.merge(entidade);
        fecharConexao();
    }

    public static IEntity getById(int id){
        abreConexao();
        IEntity e = DaoConecta.em.find(IEntity.class, id);
        fecharConexao();
        return e;
    }

    public static IEntity getByNome(String nome){
        abreConexao();
        IEntity e = DaoConecta.em.find(IEntity.class, nome);
        fecharConexao();
        return e;
    }


//    public static void  delete(IEntity entidade) {
//
//        if (entidade != null) {
//            abreConexao();
//            DaoConecta.em.remove(entidade);
//            fecharConexao();
//        }
//    }

    public static List<IEntity> getAll(){
        abreConexao();
        TypedQuery<IEntity> query = DaoConecta.em.createQuery(
                "SELECT c FROM " + IEntity.class.getSimpleName() + "c", IEntity.class        );
        List<IEntity> result = query.getResultList();
        fecharConexao();
        return result;
    }

}
