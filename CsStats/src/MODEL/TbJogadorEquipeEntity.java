package MODEL;

import DAO.DaoConecta;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    private Boolean ativo = true;

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
    @Column(name = "ativo", nullable = false)
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
        return Objects.equals(nome, that.nome) &&
                Objects.equals( codenome, that.codenome );

    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogador, idEquipe, nome, codenome, ativo);
    }

    @Override
    public String toString(){
        return this.nome;
    }



    public static List<TbJogadorEquipeEntity> getByTeamId(int id){
        abreConexao();
        CriteriaBuilder builder = DaoConecta.em.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(TbJogadorEquipeEntity.class);
        Root<TbJogadorEquipeEntity> root = cq.from(TbJogadorEquipeEntity.class);
        ParameterExpression<Integer> idE = builder.parameter(Integer.class);

        cq.select(root).where(builder.equal( root.get("idEquipe"), idE),
                builder.isTrue(root.get("ativo")));
        Query q = DaoConecta.em.createQuery(cq);
        q.setParameter(idE,id);


        List<TbJogadorEquipeEntity> result = q.getResultList();

        return result;
    }

    public boolean check_teamId(int id){
        if(this.idEquipe == id){
            return true;
        } else {
            return false;
        }
    }

    public boolean exists() {
        abreConexao();
        CriteriaBuilder builder = DaoConecta.em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = builder.createTupleQuery();
        Root<TbJogadorEquipeEntity> root = cq.from( TbJogadorEquipeEntity.class );
        cq.multiselect( root.get( "codenome" ), root.get( "ativo" ) ).where(
                builder.equal( root.get( "nome" ), this.getNome() ),
                builder.equal( root.get( "codenome" ),this.getCodenome() ) );
        Query q = DaoConecta.em.createQuery( cq );
        List<TbJogadorEquipeEntity> list_jogador = q.getResultList();
        if(list_jogador.size() > 0){

            return true;
        } else{
            return false;
        }
    }

    public static TbJogadorEquipeEntity getByCodenome(String codenome){
        abreConexao();
        CriteriaBuilder builder = DaoConecta.em.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(TbJogadorEquipeEntity.class);
        Root<TbJogadorEquipeEntity> root = cq.from(TbJogadorEquipeEntity.class);
        ParameterExpression<String> cdn = builder.parameter(String.class);

        cq.select(root).where(builder.equal( root.get("codenome"), cdn),
                builder.isFalse(root.get("ativo")));
        Query q = DaoConecta.em.createQuery(cq);
        q.setParameter(cdn,codenome);


        List<TbJogadorEquipeEntity> result = q.getResultList();

        TbJogadorEquipeEntity entidade = result.get(0);

        return entidade;
    }



}
