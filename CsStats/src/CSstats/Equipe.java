package CSstats;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

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

        //ObservableList<Equipe> equipes = Controller.table_equipes.getSelectionModel().getSelectedItem();

        
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getImagem() {
        return imagem.get();
    }

    public SimpleStringProperty imagemProperty() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem.set(imagem);
    }

    public String getOrigem() {
        return origem.get();
    }

    public SimpleStringProperty origemProperty() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem.set(origem);
    }

    public int getClassificacao() {
        return classificacao.get();
    }

    public SimpleIntegerProperty classificacaoProperty() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao.set(classificacao);
    }

    public int getVitorias() {
        return vitorias.get();
    }

    public SimpleIntegerProperty vitoriasProperty() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias.set(vitorias);
    }

    public int getEmpates() {
        return empates.get();
    }

    public SimpleIntegerProperty empatesProperty() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates.set(empates);
    }

    public int getDerrotas() {
        return derrotas.get();
    }

    public SimpleIntegerProperty derrotasProperty() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas.set(derrotas);
    }

    public int getId_campeonato() {
        return id_campeonato.get();
    }

    public SimpleIntegerProperty id_campeonatoProperty() {
        return id_campeonato;
    }

    public void setId_campeonato(int id_campeonato) {
        this.id_campeonato.set(id_campeonato);
    }
}
