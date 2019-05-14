package MODEL;

import DAO.DaoConecta;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static DAO.DaoConecta.*;

@Entity
@NamedQuery(name="Equipe.getNomes", query="SELECT e.nome FROM TbEquipesEntity AS e")
@Table(name = "tb_equipes", schema = "public", catalog = "aps")
public class TbEquipesEntity implements IEntity  {
    private int idEquipe;
    private String nome;
    private byte[] imagem;
    private String origem;
    private Boolean ativo;




    public TbEquipesEntity(){}

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Column(name = "id_equipe", nullable = false)
    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    @Basic
    @Column(name = "nome", nullable = false, length = 60)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name = "imagem", nullable = true)
    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @Basic
    @Column(name = "origem", nullable = false, length = 60)
    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
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
        TbEquipesEntity that = (TbEquipesEntity) o;
        return idEquipe == that.idEquipe &&
                Objects.equals(nome, that.nome) &&
                Arrays.equals(imagem, that.imagem) &&
                Objects.equals(origem, that.origem) &&
                Objects.equals(ativo, that.ativo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idEquipe, nome, origem, ativo);
        result = 31 * result + Arrays.hashCode(imagem);
        return result;
    }

    public TbEquipesEntity(String nome,byte[] imagem, String origem){
        this.nome = nome;
        this.imagem = imagem;
        this.origem = origem;
    }

    @Override
    public String toString(){
        return this.nome;
    }


    public static List<TbEquipesEntity> getAll(){
        TypedQuery<TbEquipesEntity> query = DaoConecta.em.createQuery(
                "SELECT c FROM " + IEntity.class.getSimpleName() + "c", TbEquipesEntity.class
        );

        return query.getResultList();
    }

    public TbEquipesEntity getByNome(String text){
        abreConexao();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(TbEquipesEntity.class);

        Root<TbEquipesEntity> c = q.from(TbEquipesEntity.class);
        ParameterExpression<String> p = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("nome"), p));

        TypedQuery<TbEquipesEntity> query = DaoConecta.em.createQuery(q);
        query.setParameter(p, text);

        return query.getSingleResult();
    }
}

