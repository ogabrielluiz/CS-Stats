package DAO;

import MODEL.TbJogadorEquipeEntity;

public class TbJogadorEquipeEntity implements IDAO {



    DaoConecta em = new DaoConecta();

    public void insert(TbJogadorEquipeEntity entidade) {
        em.abreConexao();
        em.em.persist(entidade);
        em.fecharConexao();
    }

    public void update(TbJogadorEquipeEntity entidade){
        em.abreConexao();
        em.em.merge(entidade);
        em.fecharConexao();
    }

    public TbJogadorEquipeEntity getById(int id){
        return (TbJogadorEquipeEntity) em.em.find(TbJogadorEquipeEntity.class, id);
    }

    public void remove(int id) {
        TbJogadorEquipeEntity entidade = get(id);
        if (entidade != null) {
            em.abreConexao();
            em.em.remove(entidade);
            em.fecharConexao();
        }
    }

    public List<TbJogadorEquipeEntity> list(){
        String simpleName = "tb_campeonato_equipes_status";
        return em.em.createQuery("SELECT e FROM " +
                TbJogadorEquipeEntity.class.getSimpleName()  + " e").getResultList();
    }

}
