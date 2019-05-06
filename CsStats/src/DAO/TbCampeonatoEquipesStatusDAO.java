/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import MODEL.TbCampeonatoEquipesStatusEntity;

import java.util.List;

public class TbCampeonatoEquipesStatusDAO {

    DaoConecta em = new DaoConecta();

    public void insert(TbCampeonatoEquipesStatusEntity entidade) {
        em.abreConexao();
        em.em.persist(entidade);
        em.fecharConexao();
    }

    public void update(TbCampeonatoEquipesStatusEntity entidade){
        em.abreConexao();
        em.em.merge(entidade);
        em.fecharConexao();
    }

    public TbCampeonatoEquipesStatusEntity get(int id){
        return (TbCampeonatoEquipesStatusEntity) em.em.find(TbCampeonatoEquipesStatusEntity.class, id);
    }

    public void remove(int id) {
        TbCampeonatoEquipesStatusEntity entidade = get(id);
        if (entidade != null) {
            em.abreConexao();
            em.em.remove(entidade);
            em.fecharConexao();
        }
    }

    public List<TbCampeonatoEquipesStatusEntity> list(){
        String simpleName = "tb_campeonato_equipes_status";
        return em.em.createQuery("SELECT e FROM " +
               TbCampeonatoEquipesStatusEntity.class.getSimpleName()  + " e").getResultList();
    }



}

