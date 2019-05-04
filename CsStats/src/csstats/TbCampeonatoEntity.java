package csstats;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "tb_campeonato", schema = "public", catalog = "APS")
public class TbCampeonatoEntity {
    private int idCampeonato;
    private String nome;
    private Date dtInicio;
    private Date dtFim;
    private byte[] imagem;
    private BigInteger valor;
    private String localizacao;
    private Boolean ativo;
    private Collection<TbCampeonatoEquipesStatusEntity> tbCampeonatoEquipesStatusesByIdCampeonato;

    @Id
    @Column(name = "id_campeonato", nullable = false)
    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    @Basic
    @Column(name = "nome", nullable = true, length = 100)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name = "dt_inicio", nullable = true)
    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    @Basic
    @Column(name = "dt_fim", nullable = true)
    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
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
    @Column(name = "valor", nullable = true, precision = 0)
    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    @Basic
    @Column(name = "localizacao", nullable = true, length = 60)
    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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
        TbCampeonatoEntity that = (TbCampeonatoEntity) o;
        return idCampeonato == that.idCampeonato &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(dtInicio, that.dtInicio) &&
                Objects.equals(dtFim, that.dtFim) &&
                Arrays.equals(imagem, that.imagem) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(localizacao, that.localizacao) &&
                Objects.equals(ativo, that.ativo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idCampeonato, nome, dtInicio, dtFim, valor, localizacao, ativo);
        result = 31 * result + Arrays.hashCode(imagem);
        return result;
    }

    @OneToMany(mappedBy = "tbCampeonatoByIdCampeonato")
    public Collection<TbCampeonatoEquipesStatusEntity> getTbCampeonatoEquipesStatusesByIdCampeonato() {
        return tbCampeonatoEquipesStatusesByIdCampeonato;
    }

    public void setTbCampeonatoEquipesStatusesByIdCampeonato(Collection<TbCampeonatoEquipesStatusEntity> tbCampeonatoEquipesStatusesByIdCampeonato) {
        this.tbCampeonatoEquipesStatusesByIdCampeonato = tbCampeonatoEquipesStatusesByIdCampeonato;
    }
}
