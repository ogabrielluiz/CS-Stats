package CSstats;

import java.util.Objects;

public class TableViewCamp {

    private String nome;
    private Integer classificao;
    private Integer vitorias;
    private Integer empates;
    private Integer derrotas;

    public TableViewCamp(String nome, Integer classificao, Integer vitorias, Integer empates, Integer derrotas) {
        this.nome = nome;
        this.classificao = classificao;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getClassificao() {
        return classificao;
    }

    public void setClassificao(Integer classificao) {
        this.classificao = classificao;
    }

    public Integer getVitorias() {
        return vitorias;
    }

    public void setVitorias(Integer vitorias) {
        this.vitorias = vitorias;
    }

    public Integer getEmpates() {
        return empates;
    }

    public void setEmpates(Integer empates) {
        this.empates = empates;
    }

    public Integer getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(Integer derrotas) {
        this.derrotas = derrotas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableViewCamp that = (TableViewCamp) o;
        return getNome().equals(that.getNome()) &&
                getClassificao().equals(that.getClassificao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getClassificao());
    }
}

