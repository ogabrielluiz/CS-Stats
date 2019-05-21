package MODEL;

import DAO.DaoConecta;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        return idJogador == that.idJogador &&
                idEquipe == that.idEquipe &&
                Objects.equals(nome, that.nome) &&
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
        Root<TbJogadorEquipeEntity> root = cq.from(TbJogadorEquipeEntity.class);
        cq.select(cq.from(TbJogadorEquipeEntity.class)).where(builder.equal( root.get("idEquipe"), id));
        Query q = DaoConecta.em.createQuery(cq);
        List<TbJogadorEquipeEntity> result = q.getResultList();

        int i = 0;
        for (TbJogadorEquipeEntity j: result
             ) {
            if(i > 4) break;
            if(j.check_teamId( id )){
                System.out.println(j.getCodenome() +" é do time!");
                System.out.println(i);
                i++;

            }


        }
        if(i > 4) return result;
        else return null;
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

        return list_jogador.size() > 0;
    }



}
