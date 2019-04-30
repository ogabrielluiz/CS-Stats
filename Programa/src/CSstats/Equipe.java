package CSstats;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Equipe {

    private SimpleStringProperty nome;
    private SimpleStringProperty imagem;
    private SimpleStringProperty origem;
    private SimpleIntegerProperty classificacao;
    private SimpleIntegerProperty vitorias;
    private SimpleIntegerProperty empates;
    private SimpleIntegerProperty derrotas;
    private SimpleIntegerProperty id_campeonato;

    public Equipe(String nome, Integer classificacao, Integer vitorias, Integer empates, Integer derrotas){
        this.nome = new SimpleStringProperty(nome);
        this.classificacao = new SimpleIntegerProperty(classificacao);
        this.vitorias = new SimpleIntegerProperty(vitorias);
        this.empates = new SimpleIntegerProperty(empates);
        this.derrotas = new SimpleIntegerProperty(derrotas);
    }

}
