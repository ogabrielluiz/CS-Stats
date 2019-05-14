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
@Table(name = "tb_campeonato_equipes_status", schema = "public", catalog = "aps")
@IdClass(value = TbCampeonatoEquipesStatusEntityPK.class)
public class TbCampeonatoEquipesStatusEntity implements IEntity {
    private int idCampeonato;
    private int idEquipe;
    private Integer classificacao;
    private Integer qtdVitorias;
    private Integer qtdEmpates;
    private Integer qtdDerrotas;

    public TbCampeonatoEquipesStatusEntity(){};

    public TbCampeonatoEquipesStatusEntity(Integer id_camp, Integer id_equipe, Integer classificacao, Integer vitorias, Integer empates, Integer derrotas) {
        this.idCampeonato = idCampeonato;
        this.idEquipe = idEquipe;
        this.classificacao = this.classificacao;
        this.qtdVitorias = qtdVitorias;
        this.qtdEmpates = qtdEmpates;
        this.qtdDerrotas = qtdDerrotas;
    }

    @Id
    @Column(name = "id_campeonato", nullable = false)
    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    @Id
    @Column(name = "id_equipe", nullable = false)
    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    @Basic
    @Column(name = "classificacao", nullable = true)
    public Integer getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Integer classificacao) {
        this.classificacao = classificacao;
    }

    @Basic
    @Column(name = "qtd_vitorias", nullable = true)
    public Integer getQtdVitorias() {
        return qtdVitorias;
    }

    public void setQtdVitorias(Integer qtdVitorias) {
        this.qtdVitorias = qtdVitorias;
    }

    @Basic
    @Column(name = "qtd_empates", nullable = true)
    public Integer getQtdEmpates() {
        return qtdEmpates;
    }

    public void setQtdEmpates(Integer qtdEmpates) {
        this.qtdEmpates = qtdEmpates;
    }

    @Basic
    @Column(name = "qtd_derrotas", nullable = true)
    public Integer getQtdDerrotas() {
        return qtdDerrotas;
    }

    public void setQtdDerrotas(Integer qtdDerrotas) {
        this.qtdDerrotas = qtdDerrotas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbCampeonatoEquipesStatusEntity that = (TbCampeonatoEquipesStatusEntity) o;
        return idCampeonato == that.idCampeonato &&
                idEquipe == that.idEquipe &&
                Objects.equals(classificacao, that.classificacao) &&
                Objects.equals(qtdVitorias, that.qtdVitorias) &&
                Objects.equals(qtdEmpates, that.qtdEmpates) &&
                Objects.equals(qtdDerrotas, that.qtdDerrotas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampeonato, idEquipe, classificacao, qtdVitorias, qtdEmpates, qtdDerrotas);
    }

    public static List<TbCampeonatoEquipesStatusEntity> getById(int id){
        abreConexao();
        CriteriaBuilder builder = DaoConecta.em.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(TbCampeonatoEquipesStatusEntity.class);
        Root<IEntity> root = cq.from(TbCampeonatoEquipesStatusEntity.class);
        cq.select(cq.from(TbCampeonatoEquipesStatusEntity.class)).where(builder.equal( root.get("idCampeonato"), id));
        Query q = DaoConecta.em.createQuery(cq);
        List<TbCampeonatoEquipesStatusEntity> result = q.getResultList();
        fecharConexao();
        return (List<TbCampeonatoEquipesStatusEntity>) result;
    }
}
