package CSstats;


import MODEL.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static CSstats.TableViewEquipe.getIntegrantesbyId;
import static CSstats.Util.*;
import static DAO.DaoCRUD.*;
import static DAO.DaoConecta.*;
import static DAO.DaoConecta.fecharConexao;
import static MODEL.TbJogadorEquipeEntity.getByTeamId;


public class Controller implements Initializable {


    public String getEquipe_selecionada() {
        return equipe_selecionada;
    }

    public void setEquipe_selecionada(String equipe_selecionada) {
        this.equipe_selecionada = equipe_selecionada;
    }

    public Integer getPosicao_selecionada() {
        return posicao_selecionada;
    }

    public void setPosicao_selecionada(Integer posicao_selecionada) {
        this.posicao_selecionada = posicao_selecionada;
    }

    private String equipe_selecionada;
    private Integer posicao_selecionada;

    @FXML TabPane superior_tabPane;
    @FXML TabPane inferior_tabPane;
    @FXML
    AnchorPane visualizacao_aPane;

    @FXML Tab visualizacao_tab;
        @FXML Tab tab_lista_campeonatos;
        @FXML Tab tab_lista_equipes;

    @FXML Tab tab_vis_campeonato;
        @FXML Label nm_campeonato_lb;
        @FXML Label valor_lb;
        @FXML Label data_inicio_lb;
        @FXML Label data_termino_lb;
        @FXML Label localizacao_lb;
        @FXML ImageView vis_imageView_camp;
        @FXML TableView<TableViewCamp> vis_tb_info_equipe;
        @FXML TableColumn<TableViewCamp, String> vis_coluna_equipe;
        @FXML TableColumn<TableViewCamp, Number> vis_coluna_classificacao;
        @FXML TableColumn<TableViewCamp, Number> vis_coluna_vitorias;
        @FXML TableColumn<TableViewCamp, Number> vis_coluna_empates;
        @FXML TableColumn<TableViewCamp, Number> vis_coluna_derrotas;

    @FXML Tab tab_vis_equipe;
        @FXML Label vis_nm_equipe;
        @FXML Label vis_origem;
        @FXML Label vis_except_equipe_image;
        @FXML ImageView vis_imageView_equipe;
        @FXML TableView<TableViewEquipe> tableView_lista_equipes;
            @FXML TableColumn<TableViewEquipe, String> vis_coluna_nm_equipe;
            @FXML TableColumn<TableViewEquipe, String> vis_coluna_origem_equipe;
            @FXML TableColumn<TableViewEquipe, String> vis_coluna_integrantes;
        @FXML TableView<TbJogadorEquipeEntity> vis_tb_info_integrantes;
            @FXML TableColumn<TbJogadorEquipeEntity, String> vis_coluna_nm_jogador;
            @FXML TableColumn<TbJogadorEquipeEntity, String> vis_coluna_codenome;

    @FXML Tab edicao_tab;
    @FXML TabPane edicao_tabPane;
        @FXML Tab edicao_campeonato_tab;

    @FXML TableView<TbJogadorEquipeEntity> tb_equipe_jogador;
    @FXML TableColumn<TbJogadorEquipeEntity, String> coluna_nm_jogador;
    @FXML TableColumn<TbJogadorEquipeEntity, String> coluna_codenome;



    @FXML
    private Tab tab_edicao_equipe;

    @FXML
    private ImageView imageView_equipe;

    @FXML
    private ImageView imageView_camp;

    @FXML
    private Button btn_inserir_imagem_equipe;
    @FXML
    private Button btn_inserir_imagem_camp;
    @FXML
    private Button btn_inserir_camp;
    @FXML
    private Button btn_inserir_equipe;

    @FXML
    private TextField textField_vitorias;

    @FXML
    private TextField textField_empates;

    @FXML
    private TextField textField_derrotas;

    @FXML TextField textField_codenome_jogador;
    @FXML TextField textField_nome_jogador;

    @FXML
    private TextField nm_campeonato;

    @FXML
    private TextField valor;

    @FXML
    public DatePicker data_inicio;

    @FXML
    public DatePicker data_termino;

    @FXML
    public TextField localizacao;


    @FXML
    private ComboBox<String> comboBox_equipes;

    @FXML
    private ChoiceBox<Integer> choiceBox_posicao;
    @FXML
    private ChoiceBox<Integer> vitorias_ChB;
    @FXML
    private ChoiceBox<Integer> empates_ChB;
    @FXML
    private ChoiceBox<Integer> derrotas_ChB;

    @FXML
    private TableView<TableViewCamp> tb_info_equipe;

    @FXML
    private TableColumn<TableViewCamp, String> coluna_equipe;

    @FXML
    private TableColumn<TableViewCamp, Number> coluna_classificacao;

    @FXML
    private TableColumn<TableViewCamp, Number> coluna_vitorias;

    @FXML
    private TableColumn<TableViewCamp, Number> coluna_empates;

    @FXML
    private TableColumn<TableViewCamp, Number> coluna_derrotas;



    @FXML TextField origem_equipe;
    @FXML TextField nm_equipe;

    @FXML Label except_equipe_image;
    @FXML Label except_camp_image;

    private File imagefile_camp;
    private File imagefile_equipe;


    private byte[] imagem_e;

    //Popula a lista de equipes
    @FXML
    private void btn_adicionar_equipe_em_tableView(){
        String nome = comboBox_equipes.getSelectionModel().getSelectedItem();
        String nome_camp = nm_campeonato.getText();
        Integer classificacao = choiceBox_posicao.getValue();
        Integer vitorias = vitorias_ChB.getValue();
        Integer empates = empates_ChB.getValue();
        Integer derrotas = derrotas_ChB.getValue();
        TbCampeonatoEquipesStatusEntity dados_camp = new TbCampeonatoEquipesStatusEntity(nome_camp,nome,classificacao,
                vitorias,empates,derrotas);

        if (dados_camp.exists()) {
            alertaErro("Equipe já inserida.").showAndWait();
            comboBox_equipes.getSelectionModel().clearSelection();
            choiceBox_posicao.getSelectionModel().clearSelection();


        } else {
            try {
                insert( dados_camp );
                comboBox_equipes.getItems().remove( comboBox_equipes.getSelectionModel().getSelectedItem() );
            }catch (RollbackException e){
                alertaErro( "Equipe já inserida" ).showAndWait();
            }

            List<TbCampeonatoEquipesStatusEntity> list_info_equipe = new ArrayList<>();

            try{
                abreConexao();
                CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
                cq.select(cq.from(TbCampeonatoEquipesStatusEntity.class));
                Query q = em.createQuery(cq);

                list_info_equipe = q.getResultList();

                fecharConexao();

            } catch(NullPointerException err){
                System.out.println("TableView Camp: Retorno nulo.");
            }
            ObservableList<TableViewCamp> tbCampeonatoEquipesStatusEntities = FXCollections.observableArrayList();
            tb_info_equipe.getItems().clear();
            for (TbCampeonatoEquipesStatusEntity e: list_info_equipe
            ) {

                TbEquipesEntity equipe = TbEquipesEntity.getById(e.getIdEquipe());
                TableViewCamp info = new TableViewCamp(equipe.getNome(), e.getClassificacao(),
                        e.getQtdVitorias(), e.getQtdEmpates(), e.getQtdDerrotas());
                tbCampeonatoEquipesStatusEntities.add(info);

                if (tb_info_equipe.getItems() == null) {
                    tb_info_equipe.setItems(tbCampeonatoEquipesStatusEntities);

                    comboBox_equipes.getSelectionModel().clearSelection();
                    choiceBox_posicao.getSelectionModel().clearSelection();
                    vitorias_ChB.getSelectionModel().clearSelection();
                    empates_ChB.getSelectionModel().clearSelection();
                    derrotas_ChB.getSelectionModel().clearSelection();
                } else {

                    tb_info_equipe.getItems().add(info);

                    comboBox_equipes.getSelectionModel().clearSelection();
                    choiceBox_posicao.getSelectionModel().clearSelection();
                    vitorias_ChB.getSelectionModel().clearSelection();
                    empates_ChB.getSelectionModel().clearSelection();
                    derrotas_ChB.getSelectionModel().clearSelection();

                }
            }


        }



    }

    @FXML
    private void btn_remover_de_tb_camp(){
        ObservableList<TableViewCamp> equipeSelecionada, todasEquipes;


        todasEquipes = tb_info_equipe.getItems();

        equipeSelecionada = tb_info_equipe.getSelectionModel().getSelectedItems();
        Integer idcamp = TbCampeonatoEntity.getByNome(nm_campeonato.getText()).getIdCampeonato();
        Integer idequipe;


        for (TableViewCamp j: equipeSelecionada
        ) {
            idequipe = TbEquipesEntity.getByNome(j.getNome()).getIdEquipe();
            TbCampeonatoEquipesStatusEntity info = TbCampeonatoEquipesStatusEntity.getByIdEqCamp(idcamp,idequipe);


            delete(info);
            todasEquipes.remove(j);

            alertaAviso( "Equipe excluída" ).showAndWait();

            }


    }

    @FXML
    private void btn_remover_de_tb_equipe(){
        ObservableList<TbJogadorEquipeEntity> jogadorSelecionado, todosJogadores;

        todosJogadores = tb_equipe_jogador.getItems();
        jogadorSelecionado = tb_equipe_jogador.getSelectionModel().getSelectedItems();

        jogadorSelecionado.forEach(todosJogadores::remove);
        for (TbJogadorEquipeEntity j: jogadorSelecionado
             ) {
            j.setAtivo(false);
            update(j);
            alertaAviso( j.getCodenome() + " inativado." );

        }
    }

    @FXML
    private void btn_adicionar_jogador_tableView(){
        TbJogadorEquipeEntity jogadorEquipeEntity = new TbJogadorEquipeEntity();
        jogadorEquipeEntity.setNome( textField_nome_jogador.getText() );
        jogadorEquipeEntity.setCodenome( textField_codenome_jogador.getText() );


        TbEquipesEntity equipe = new TbEquipesEntity();
        jogadorEquipeEntity.setIdEquipe(equipe.getByNome(nm_equipe.getText()).getIdEquipe());

        if(!jogadorEquipeEntity.exists()){
            insert(jogadorEquipeEntity);

            List<TbJogadorEquipeEntity> list_integrantes = new ArrayList<>();

            try{
                abreConexao();
                CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
                cq.select(cq.from(TbJogadorEquipeEntity.class));
                Query q = em.createQuery(cq);

                list_integrantes = q.getResultList();

                fecharConexao();

            } catch(NullPointerException err){
                System.out.println("TableView Camp: Retorno nulo.");
            }
            ObservableList<TbJogadorEquipeEntity> jogadorEquipeEntities = FXCollections.observableArrayList();

            for (TbJogadorEquipeEntity e: list_integrantes
            ) {
                jogadorEquipeEntities.add(e);

            }
            //tb_equipe_jogador.setItems(jogadorEquipeEntities);

            if(tb_equipe_jogador.getItems() == null){
                tb_equipe_jogador.setItems(jogadorEquipeEntities);
                textField_nome_jogador.clear();
                textField_codenome_jogador.clear();
            } else{
                tb_equipe_jogador.getItems().add(jogadorEquipeEntity);
                textField_nome_jogador.clear();
                textField_codenome_jogador.clear();

            }



        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Jogador " + jogadorEquipeEntity.getCodenome() +
                    " já existe no banco de dados.", ButtonType.OK);
            alert.showAndWait();
            textField_nome_jogador.clear();
            textField_codenome_jogador.clear();

        }



    }

    @FXML
    public void handle_inserir_camp() throws NullPointerException {

        String nome = nm_campeonato.getText();
        String premiacao = valor.getText();

        Date data_i = Util.localDate_to_SQLdate(data_inicio.getValue());
        Date data_t = Util.localDate_to_SQLdate(data_termino.getValue());
        String local = localizacao.getText() ;
        byte[] imagem = new byte[0];
        try {
            imagem = Util.image_to_bytea(imagefile_camp);
            except_camp_image.setText("");
        } catch (IOException e) {
            except_camp_image.setText("Escolha um arquivo de imagem.");
        }

        TbCampeonatoEntity camp = new TbCampeonatoEntity();

        camp.setNome(nome);
        camp.setValor(BigInteger.valueOf(Long.parseLong(premiacao)));
        camp.setDtInicio(data_i);
        camp.setDtFim(data_t);
        camp.setImagem(imagem);
        camp.setLocalizacao(local);

        insert(camp);


    }

    @FXML
    public void handle_inserir_equipe() throws NullPointerException{

        String nome = nm_equipe.getText();
        String origem = origem_equipe.getText();
        byte[] imagem = new byte[0];
        try {
            imagem = Util.image_to_bytea(imagefile_equipe);
            except_equipe_image.setText("");
        } catch (IOException e) {
            except_equipe_image.setText("Nenhuma imagem selecionada.");
        }


            TbEquipesEntity equipe = new TbEquipesEntity();
            equipe.setNome(nome);
            equipe.setOrigem(origem);
            equipe.setImagem(imagem);
        if(is_not_empty(nome) && is_not_empty(origem)) {
            insert(equipe);
        }
    }


    @FXML
    private Label fileSselected;

        @FXML File btn_selecionar_imagem_camp() throws MalformedURLException, FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files",
                "*.bmp", "*.png", "*.jpg");
        File selectedFile;
        selectedFile = fileChooser.showOpenDialog(btn_inserir_imagem_camp.getScene().getWindow());

        if (selectedFile != null) {
            except_camp_image.setText("");
        }

        try {
            Image image_camp = new Image(selectedFile.getAbsoluteFile().toURI().toString(),
                    imageView_camp.getFitWidth(), imageView_camp.getFitHeight(), true, true);
            imageView_camp.setImage(image_camp);
            imageView_camp.setCache(true);
            imageView_camp.setPreserveRatio(true);
            FileInputStream fis = new FileInputStream(selectedFile);
            imagefile_camp = selectedFile;

        }catch (NullPointerException npe){
            except_camp_image.setText("Nenhuma imagem selecionada");
        }
            return imagefile_camp;
        }



    @FXML File btn_selecionar_imagem_equipe() throws MalformedURLException, FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files",
                "*.bmp", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile;
        selectedFile = fileChooser.showOpenDialog(btn_inserir_imagem_camp.getScene().getWindow());

        if (selectedFile == null) {
            except_equipe_image.setText("Escolha um arquivo de imagem.");
        } else
            except_equipe_image.setText("");



            Image image_equipe = new Image(selectedFile.getAbsoluteFile().toURI().toString(),imageView_camp.getFitWidth(),imageView_camp   .getFitHeight(),true,true);
        imageView_equipe.setImage(image_equipe);
        imageView_equipe.setCache(true);
        imageView_equipe.setPreserveRatio(true);
        FileInputStream fis = new FileInputStream(selectedFile);

        imagefile_equipe = selectedFile;
        return imagefile_equipe;

    }


    @FXML
    private String handleComboBoxAction(){
        //equipe_selecionada = comboBox_equipes.getSelectionModel().getSelectedItem();
        return equipe_selecionada;
    }
    @FXML
    private Integer handleChoiceBoxAction(){
            return 0;
    }

    public void insert_campeonato_equipes_status(){
    }

    @FXML
    public void popula_box_edicao_camp(){

        try{
            comboBox_equipes.getItems().clear();
            abreConexao();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbEquipesEntity.class));
            Query q = em.createQuery(cq);

            List<TbEquipesEntity> list_equipes = q.getResultList();

            for(TbEquipesEntity t : list_equipes) {
                comboBox_equipes.getItems().addAll(t.getNome());
            }

            fecharConexao();

        } catch(NullPointerException err){
            System.out.println("ComboBox Camp: Nenhuma equipe na lista");
        }

    }

    public void popula_tb_equipe_jogador(){
        List<TbJogadorEquipeEntity> list_integrantes = new ArrayList<>();

        try{
            abreConexao();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbJogadorEquipeEntity.class));
            Query q = em.createQuery(cq);

            list_integrantes = q.getResultList();

            fecharConexao();

        } catch(NullPointerException err){
            System.out.println("TableView Camp: Retorno nulo.");
        }
        ObservableList<TbJogadorEquipeEntity> jogadorEquipeEntities = FXCollections.observableArrayList();
        TbEquipesEntity byNome = TbEquipesEntity.getByNome( vis_nm_equipe.getText() );

            List<TbJogadorEquipeEntity> results = getByTeamId( byNome.getIdEquipe() );
        for (TbJogadorEquipeEntity e: list_integrantes
        ) {

                if(e.getIdEquipe() == byNome.getIdEquipe() && e.getAtivo() == true) {
                    jogadorEquipeEntities.add( e );
                }

        }
       tb_equipe_jogador.setItems(jogadorEquipeEntities);

    }


    @FXML void salvar_tudo_equipe(){
            TbEquipesEntity equipe = TbEquipesEntity.getByNome(nm_equipe.getText());

            TbJogadorEquipeEntity jogador = new TbJogadorEquipeEntity();

            ObservableList<TbJogadorEquipeEntity> integrantes = tb_equipe_jogador.getItems();

        for (TbJogadorEquipeEntity j: integrantes
             ) {
            j.setIdEquipe(equipe.getIdEquipe());

             insert(j);
        }




    }
    // VISUALIZAÇÃO

    @FXML TableView<TbCampeonatoEntity> tableView_lista_campeonatos = new TableView<>();
    @FXML TableColumn<MODEL.TbCampeonatoEquipesStatusEntity, String> vis_nome_camp_coluna;
    @FXML TableColumn<MODEL.TbCampeonatoEquipesStatusEntity, Number> vis_premio_campeonatos_coluna;
    @FXML TableColumn<MODEL.TbCampeonatoEquipesStatusEntity, LocalDate> vis_data_inicio_coluna;
    @FXML TableColumn<MODEL.TbCampeonatoEquipesStatusEntity, LocalDate> vis_data_termino_coluna;
    @FXML TableColumn<MODEL.TbCampeonatoEquipesStatusEntity, String> vis_local_coluna;

    @FXML public void popula_tableview_lista_campeonatos(){

        List<TbCampeonatoEntity> list_campeonatos = new ArrayList<>();

        try{
            abreConexao();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbCampeonatoEntity.class));
            Query q = em.createQuery(cq);

            list_campeonatos = q.getResultList();

            fecharConexao();

        } catch(NullPointerException err){
            System.out.println("TableView Camp: Retorno nulo.");
        }
        ObservableList<TbCampeonatoEntity> campeonatoEntities = FXCollections.observableArrayList();

        for (TbCampeonatoEntity e: list_campeonatos
             ) {
            campeonatoEntities.add(e);
        }
        tableView_lista_campeonatos.setItems(campeonatoEntities);


    }

    @FXML public void popula_tableview_lista_equipes(){

        List<TbEquipesEntity> list_equipes = new ArrayList<>();
        ObservableList<TableViewEquipe> equipeEntities = FXCollections.observableArrayList();

        try{

            abreConexao();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery cq = builder.createQuery();
            Root<IEntity> root = cq.from(TbEquipesEntity.class);
            cq.select(cq.from(TbEquipesEntity.class));
            Query q = em.createQuery(cq);
            list_equipes = q.getResultList();
            fecharConexao();

            String integrantes;
            for (TbEquipesEntity e : list_equipes) {
                integrantes = getIntegrantesbyId( e.getIdEquipe() ) ;
                equipeEntities.add(
                        new TableViewEquipe(
                                e.getNome(),
                                e.getOrigem(),
                                integrantes
                                ));
            }



        } catch(NullPointerException err){
            System.out.println("TableView Equipe: Retorno nulo.");
        }
        tableView_lista_equipes.getItems().clear();
        //tableView_lista_equipes.setItems(equipeEntities);
        tableView_lista_equipes.getItems().addAll( equipeEntities );
    }


    @FXML public void handle_btn_editar_camp(){

        TbCampeonatoEntity campeonatoEntity;
        btn_inserir_camp.setDisable( true );

        campeonatoEntity = TbCampeonatoEntity.getByNome( nm_campeonato_lb.getText() );

        nm_campeonato.setText(nm_campeonato_lb.getText());
        valor.setText( valor_lb.getText() );
        LocalDate data_inicio_string = LocalDate.parse( data_inicio_lb.getText() ) ;
        LocalDate data_termino_string = LocalDate.parse( data_termino_lb.getText() ) ;
        data_inicio.setValue(data_inicio_string );
        data_termino.setValue( data_termino_string );
        imageView_camp.setImage( vis_imageView_camp.getImage() );
        List<TbCampeonatoEquipesStatusEntity> results = TbCampeonatoEquipesStatusEntity
                .getById( campeonatoEntity.getIdCampeonato() );
        ObservableList<TableViewCamp> info_camp = FXCollections.observableArrayList();

        for (TbCampeonatoEquipesStatusEntity e: results
        ) {
            TbEquipesEntity equipe = TbEquipesEntity.getById(e.getIdEquipe());
            TableViewCamp info = new TableViewCamp(equipe.getNome(), e.getClassificacao(),
                    e.getQtdVitorias(), e.getQtdEmpates(), e.getQtdDerrotas());


            if(!containsId(info_camp,info)){
                info_camp.add(info);
            }
        }

        tb_info_equipe.setItems(info_camp);

        localizacao.setText( campeonatoEntity.getLocalizacao() );
        btn_inserir_camp.setDisable(true);
        superior_tabPane.getSelectionModel().select( edicao_tab );
        edicao_tabPane.getSelectionModel().select(edicao_campeonato_tab);
    }

    @FXML public void handle_btn_editar_equipe(){

        TbEquipesEntity equipeEntity;

        equipeEntity = TbEquipesEntity.getByNome( vis_nm_equipe.getText() );

        nm_equipe.setText(vis_nm_equipe.getText());
        origem_equipe.setText( vis_origem.getText() );

        imageView_equipe.setImage( vis_imageView_equipe.getImage() );

        Integer id_equipe = equipeEntity.getIdEquipe();
        List<TbJogadorEquipeEntity> results = getByTeamId( id_equipe );
        ObservableList<TbJogadorEquipeEntity> info_equipe = FXCollections.observableArrayList();
        try {
            for (TbJogadorEquipeEntity e : results
            ) {
                info_equipe.add( e );
                if (info_equipe.size() == 5) break;
            }

            tb_equipe_jogador.getItems().addAll( info_equipe );
        } catch (NullPointerException e) {
            System.out.println("NPE na tb_equipe_jogador.");
        }
        popula_tb_equipe_jogador();
        btn_inserir_equipe.setDisable(true);
        superior_tabPane.getSelectionModel().select( edicao_tab );
        edicao_tabPane.getSelectionModel().select(tab_edicao_equipe);
    }

    public void clear_edicao_camp(){
        nm_campeonato.clear();
        data_termino.setValue(null );
        data_inicio.setValue( null );
        localizacao.clear();
        valor.clear();
        imageView_camp.setImage( null );
        tb_info_equipe.setItems( null );

    }

    public void clear_edicao_equipe(){
        nm_equipe.clear();
        origem_equipe.clear();
        imageView_equipe.setImage( null );
        tb_equipe_jogador.setItems( null );

    }

    @FXML public void handle_btn_atualizar_camp() throws IOException {
        TbCampeonatoEntity campeonatoAtualizado = new TbCampeonatoEntity();

        campeonatoAtualizado.setNome( nm_campeonato.getText() );
        campeonatoAtualizado.setIdCampeonato( TbCampeonatoEntity.getByNome( campeonatoAtualizado.getNome() ).getIdCampeonato() );
        campeonatoAtualizado.setValor(BigInteger.valueOf(Long.parseLong( valor.getText() )));
        campeonatoAtualizado.setDtInicio( localDate_to_SQLdate( data_inicio.getValue() ) );
        campeonatoAtualizado.setDtFim( localDate_to_SQLdate( data_termino.getValue() ) );
        campeonatoAtualizado.setLocalizacao( localizacao.getText() );



        // Cria um arquivo sem espaços no nome
        File file = new File( "src/ImagensCampeonatos/" +
                campeonatoAtualizado.getNome().replaceAll( "\\s+","" ) ) ;
        try {
            ImageIO.write( SwingFXUtils.fromFXImage(imageView_camp.getImage(), null), "png", file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        campeonatoAtualizado.setImagem( image_to_bytea(file) );

        update(campeonatoAtualizado);
        superior_tabPane.getSelectionModel().select( visualizacao_tab );
//        ObservableList<TbCampeonatoEntity > camp_list = add_camp_into_obsList( campeonatoAtualizado );
//        tableView_lista_campeonatos.setItems(camp_list );
        popula_tableview_lista_campeonatos();
        inferior_tabPane.getSelectionModel().select( tab_lista_campeonatos );
        clear_edicao_camp();
        btn_inserir_camp.setDisable(false);
    }

    @FXML public void handle_btn_atualizar_equipe() throws IOException {
        TbEquipesEntity equipeAtualizada = TbEquipesEntity.getByNome( nm_equipe.getText() );
        equipeAtualizada.setNome( nm_equipe.getText() );
        equipeAtualizada.setOrigem( origem_equipe.getText() );




        // Cria um arquivo sem espaços no nome
        File file = new File( "src/ImagensEquipes/" +
                equipeAtualizada.getNome().replaceAll( "\\s+","" ) + ".png" ) ;
        try {
            ImageIO.write( SwingFXUtils.fromFXImage(imageView_equipe.getImage(), null), "png", file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        equipeAtualizada.setImagem( image_to_bytea(file) );

        update(equipeAtualizada);
        superior_tabPane.getSelectionModel().select( visualizacao_tab );
//        ObservableList<equipeAtualizada > equipe_list = add_equipe_into_obsList( equipeAtualizada );
//        tableView_lista_equipes.setItems(equipe_list );

        popula_tableview_lista_equipes();
        inferior_tabPane.getSelectionModel().select( tab_lista_equipes );
        clear_edicao_equipe();
        btn_inserir_equipe.setDisable(false);
    }

    public void initialize(URL location, ResourceBundle resources) {
        tableView_lista_campeonatos.setRowFactory(tv -> {
                    TableRow<TbCampeonatoEntity> row = new TableRow<>();
                    row.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2 && (!row.isEmpty())) {
                            tab_vis_campeonato.setDisable(false);

                            inferior_tabPane.getSelectionModel().select(tab_vis_campeonato);

                            TbCampeonatoEntity rowData = row.getItem();
                            nm_campeonato_lb.setText(rowData.getNome());
                            valor_lb.setText(rowData.getValor().toString());
                            data_inicio_lb.setText(rowData.getDtInicio().toString());
                            data_termino_lb.setText(rowData.getDtFim().toString());
                            try {
                                vis_imageView_camp.setImage(bytea_to_image(rowData.getImagem()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            tab_vis_campeonato.setText( rowData.getNome() );
                            ObservableList<TableViewCamp> info_camp = FXCollections.observableArrayList();

                            List<TbCampeonatoEquipesStatusEntity> results = MODEL.TbCampeonatoEquipesStatusEntity
                                    .getById( rowData.getIdCampeonato() );

                            for (TbCampeonatoEquipesStatusEntity e: results
                            ) {
                                TbEquipesEntity equipe = TbEquipesEntity.getById(e.getIdEquipe());
                                TableViewCamp info = new TableViewCamp(equipe.getNome(), e.getClassificacao(),
                                        e.getQtdVitorias(), e.getQtdEmpates(), e.getQtdDerrotas());


                                if(!containsId(info_camp,info)){
                                    info_camp.add(info);
                                }
                            }

                            vis_tb_info_equipe.setItems( info_camp );

                            localizacao_lb.setText(rowData.getLocalizacao());



                        }
                    }
                    );
                    return row;
        }
        );

        tableView_lista_equipes.setRowFactory(tv -> {
                    TableRow<TableViewEquipe> row = new TableRow<>();
                    row.setOnMouseClicked(event -> {
                                if (event.getClickCount() == 2 && (!row.isEmpty())) {




                                    TableViewEquipe rowData = row.getItem();
                                    vis_nm_equipe.setText(rowData.getNome());
                                    vis_origem.setText(rowData.getOrigem());

                                    TbEquipesEntity newData = new TbEquipesEntity();

                                    TbEquipesEntity byNome = newData.getByNome( rowData.getNome() );


                                    try {
                                        vis_imageView_equipe.setImage(bytea_to_image(byNome.getImagem()));
                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    tab_vis_equipe.setText( byNome.getNome() );
                                    ObservableList<TbJogadorEquipeEntity> info_equipe = FXCollections.observableArrayList();

                                    try {
                                        List<TbJogadorEquipeEntity> results = getByTeamId( byNome.getIdEquipe() );
                                        for (TbJogadorEquipeEntity e : results
                                        ) {
                                            if(e.getIdEquipe() == byNome.getIdEquipe()){
                                                info_equipe.add( e );

                                            }


                                        }
                                    } catch (NullPointerException e){
                                        System.out.println("NPE na vis_tb_info_integrantes");

                                    }



                                    tab_vis_equipe.setDisable( false );
                                    vis_tb_info_integrantes.setItems(info_equipe);
                                    inferior_tabPane.getSelectionModel().select(tab_vis_equipe);

                                    //popula_tb_equipe_jogador();



                                }
                            }
                    );
                    return row;
                }
        );


        declara_colunas(coluna_equipe, coluna_classificacao, coluna_vitorias, coluna_empates, coluna_derrotas);

        declara_colunas(vis_coluna_equipe, vis_coluna_classificacao, vis_coluna_vitorias, vis_coluna_empates, vis_coluna_derrotas);

        vis_nome_camp_coluna.setCellValueFactory(new PropertyValueFactory<>("nome"));
        vis_premio_campeonatos_coluna.setCellValueFactory(new PropertyValueFactory<>("valor"));
        vis_data_inicio_coluna.setCellValueFactory(new PropertyValueFactory<>("dtInicio"));
        vis_data_termino_coluna.setCellValueFactory(new PropertyValueFactory<>("dtFim"));
        vis_local_coluna.setCellValueFactory(new PropertyValueFactory<>("localizacao"));


        vis_coluna_nm_equipe.setCellValueFactory(new PropertyValueFactory<>("nome"));
        vis_coluna_origem_equipe.setCellValueFactory(new PropertyValueFactory<>("origem"));
        vis_coluna_integrantes.setCellValueFactory(new PropertyValueFactory<>("integrantes"));

        vis_coluna_nm_jogador.setCellValueFactory(new PropertyValueFactory<>("nome"));
        vis_coluna_codenome.setCellValueFactory(new PropertyValueFactory<>("codenome"));

        coluna_nm_jogador.setCellValueFactory(new PropertyValueFactory<>("nome"));
        coluna_codenome.setCellValueFactory(new PropertyValueFactory<>("codenome"));

        popula_tableview_lista_equipes();
        popula_tableview_lista_campeonatos();
        // Popula a lista de números
        choiceBox_posicao.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        vitorias_ChB.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        empates_ChB.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        derrotas_ChB.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    }

    private void declara_colunas(TableColumn<TableViewCamp, String> coluna_equipe,
                                 TableColumn<TableViewCamp, Number> coluna_classificacao,
                                 TableColumn<TableViewCamp, Number> coluna_vitorias,
                                 TableColumn<TableViewCamp, Number> coluna_empates,
                                 TableColumn<TableViewCamp, Number> coluna_derrotas) {

        coluna_equipe.setCellValueFactory(new PropertyValueFactory<>("nome"));
        coluna_classificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        coluna_vitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias"));
        coluna_empates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        coluna_derrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas"));
    }
}
