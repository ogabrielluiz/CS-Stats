package CSstats;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "tb_equipes", schema = "public", catalog = "APS")
public class TbEquipesEntity {
    private int idEquipe;
    private String nome;
    private byte[] imagem;
    private String origem;
    private Boolean ativo;

    @Id
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
    @Column(name = "imagem", nullable = true)
    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @Basic
    @Column(name = "origem", nullable = true, length = 60)
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
}
