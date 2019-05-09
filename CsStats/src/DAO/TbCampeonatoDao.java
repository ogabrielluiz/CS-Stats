/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import MODEL.TbCampeonatoEntity;

public class TbCampeonatoDao implements IDAO {

    DaoConecta dao = new DaoConecta();

    public void salvar(TbCampeonatoEntity c) {
        dao.abreConexao();
        dao.em.persist(c);
        dao.fecharConexao();
    }

}
