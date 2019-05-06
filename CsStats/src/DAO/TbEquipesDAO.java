/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import MODEL.TbEquipesEntity;

import java.util.List;

public class TbEquipesDAO {

    DaoConecta em = new DaoConecta();

    public void insert(TbEquipesEntity entidade) {
        em.abreConexao();
        em.em.persist(entidade);
        em.fecharConexao();
    }

    public void update(TbEquipesEntity entidade){
        em.abreConexao();
        em.em.merge(entidade);
        em.fecharConexao();
    }

    public TbEquipesEntity get(int id){
        return (TbEquipesEntity) em.em.find(TbEquipesEntity.class, id);
    }

    public void remove(int id) {
        TbEquipesEntity entidade = get(id);
        if (entidade != null) {
            em.abreConexao();
            em.em.remove(entidade);
            em.fecharConexao();
        }
    }

    public List<TbEquipesEntity> list(){
        String simpleName = "tb_campeonato_equipes_status";
        return em.em.createQuery("SELECT e FROM " +
                TbEquipesEntity.class.getSimpleName()  + " e").getResultList();
    }



}

