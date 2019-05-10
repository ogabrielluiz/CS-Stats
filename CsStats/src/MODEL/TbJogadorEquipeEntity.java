package MODEL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_jogador_equipe", schema = "public", catalog = "aps")
public class TbJogadorEquipeEntity implements IEntity  {
    private int idJogador;
    private int idEquipe;
    private String nome;
    private String condenome;
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
    @Column(name = "condenome", nullable = true, length = 60)
    public String getCondenome() {
        return condenome;
    }

    public void setCondenome(String condenome) {
        this.condenome = condenome;
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
                Objects.equals(condenome, that.condenome) &&
                Objects.equals(ativo, that.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogador, idEquipe, nome, condenome, ativo);
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
