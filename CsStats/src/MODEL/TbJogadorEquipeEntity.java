package MODEL;

import DAO.DaoConecta;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

import static DAO.DaoConecta.abreConexao;
import static DAO.DaoConecta.fecharConexao;

@Entity

@Table(name = "tb_jogador_equipe", schema = "public", catalog = "aps")
public class TbJogadorEquipeEntity implements IEntity  {
    private int idJogador;
    private int idEquipe;
    private String nome;
    private String codenome;
    private Boolean ativo;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Column(name = "id_jogador", nullable = false)
    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    @Basic
    @Column(name = "id_equipe", nullable = false)
    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    @Basic
    @Column(name = "nome", nullable = true, length = 60)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name = "codenome", nullable = true, length = 60)
    public String getCodenome() {
        return codenome;
    }

    public void setCodenome(String condenome) {
        this.codenome = condenome;
    }

    @Basic
    @Column(name = "ativo", nullable = true)
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbJogadorEquipeEntity that = (TbJogadorEquipeEntity) o;
        return idJogador == that.idJogador &&
                idEquipe == that.idEquipe &&
                Objects.equals(nome, that.nome) &&
                Objects.equals( codenome, that.codenome ) &&
                Objects.equals(ativo, that.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogador, idEquipe, nome, codenome, ativo);
    }

    @Override
    public String toString(){
        return this.nome;
    }

//    public static List<TbJogadorEquipeEntity> getById(int id){
//        abreConexao();
//        CriteriaBuilder builder = DaoConecta.em.getCriteriaBuilder();
//        CriteriaQuery cq = builder.createQuery(TbJogadorEquipeEntity.class);
//        Root<IEntity> root = cq.from(TbJogadorEquipeEntity.class);
//        cq.select(cq.from(TbJogadorEquipeEntity.class)).where(builder.equal( root.get("idEquipe"), id));
//        Query q = DaoConecta.em.createQuery(cq);
//        List<TbJogadorEquipeEntity> result = q.getResultList();
//        fecharConexao();
//        return result;
//    }

    public static List<TbJogadorEquipeEntity> getByTeamId(int id){
        abreConexao();
        CriteriaBuilder builder = DaoConecta.em.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(TbJogadorEquipeEntity.class);
        Root<IEntity> root = cq.from(TbJogadorEquipeEntity.class);
        cq.select(cq.from(TbJogadorEquipeEntity.class)).where(builder.equal( root.get("idEquipe"), id));
        Query q = DaoConecta.em.createQuery(cq);
        List<TbJogadorEquipeEntity> result = q.getResultList();
        fecharConexao();
        return result;
    }
}
