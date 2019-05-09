/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoConecta {

    public static EntityManager em;
    private static EntityManagerFactory emf;

    public void abreConexao() {
        emf = Persistence.createEntityManagerFactory("CsStatsPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public void fecharConexao() {
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
