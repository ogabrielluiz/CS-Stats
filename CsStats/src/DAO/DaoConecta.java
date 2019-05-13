/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class DaoConecta {

    public static EntityManager em;
    public static EntityManagerFactory emf;

    public static void abreConexao() {
        emf = createEntityManagerFactory("CsStatsPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public static void fecharConexao() {
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
