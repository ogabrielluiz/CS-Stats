/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import csstats.*;

public class TbCampeonatoDao {

    DaoConecta dao = new DaoConecta();

    public void salvar(CSstats.TbCampeonatoEntity c) {
        dao.abreConexao();
        dao.em.persist(c);
        dao.fecharConexao();
    }

}
