package CSstats;


import DAO.DaoCRUD;
import DAO.DaoConecta;
import MODEL.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javafx.scene.image.ImageView;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static CSstats.Util.bytea_to_image;
import static CSstats.Util.is_not_empty;
import static DAO.DaoCRUD.*;
import static DAO.DaoConecta.*;
import static DAO.DaoConecta.fecharConexao;


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

    @FXML Tab tab_vis_campeonato;
        @FXML Label nm_campeonato_lb;
        @FXML Label valor_lb;
        @FXML Label data_inicio_lb;
        @FXML Label data_termino_lb;
        @FXML Label localizacao_lb;
        @FXML ImageView vis_imageView_camp;
        @FXML TableView<TbCampeonatoEquipesStatusEntity> vis_tb_info_equipe;
        @FXML TableColumn<TbCampeonatoEquipesStatusEntity, String> vis_coluna_equipe;
        @FXML TableColumn<TbCampeonatoEquipesStatusEntity, Number> vis_coluna_classificacao;
        @FXML TableColumn<TbCampeonatoEquipesStatusEntity, Number> vis_coluna_vitorias;
        @FXML TableColumn<TbCampeonatoEquipesStatusEntity, Number> vis_coluna_empates;
        @FXML TableColumn<TbCampeonatoEquipesStatusEntity, Number> vis_coluna_derrotas;

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
            @FXML TableColumn<TableViewEquipe, String> vis_coluna_nm_jogador;
            @FXML TableColumn<TableViewEquipe, String> vis_coluna_codenome;



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
    private TextField textField_vitorias;

    @FXML
    private TextField textField_empates;

    @FXML
    private TextField textField_derrotas;

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
    private TableView<TableViewCamp> tb_info_equipe = new TableView<>();

    @FXML
    private TableColumn<TableViewCamp, String> coluna_equipe = new TableColumn<>("nome");

    @FXML
    private TableColumn<TableViewCamp, Number> coluna_classificacao = new TableColumn<>("classificacao");

    @FXML
    private TableColumn<TableViewCamp, Number> coluna_vitorias = new TableColumn<>("vitorias");

    @FXML
    private TableColumn<TableViewCamp, Number> coluna_empates = new TableColumn<>("empates");

    @FXML
    private TableColumn<TableViewCamp, Number> coluna_derrotas = new TableColumn<>("derrotas");



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
        Integer classificacao = choiceBox_posicao.getValue();
        Integer vitorias = vitorias_ChB.getValue();
        Integer empates = empates_ChB.getValue();
        Integer derrotas = derrotas_ChB.getValue();



        ObservableList<TableViewCamp> data = FXCollections.observableArrayList(new TableViewCamp(nome,classificacao,
                vitorias,empates,derrotas));

        tb_info_equipe.setItems(data);
        tb_info_equipe.setVisible(true);

//        comboBox_equipes.getItems().clear();
//        choiceBox_posicao.getItems().clear();
//        vitorias_ChB.getItems().clear();
//        empates_ChB.getItems().clear();
//        derrotas_ChB.getItems().clear();

    }

    @FXML
    private void btn_remover_de_tableView(){
        ObservableList<TableViewCamp> equipeSelecionada, todasEquipes;

        todasEquipes = tb_info_equipe.getItems();
        equipeSelecionada = tb_info_equipe.getSelectionModel().getSelectedItems();

        equipeSelecionada.forEach(todasEquipes::remove);
    }

    @FXML
    private void btn_adicionar_jogador_tableView(){}

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

    // VISUALIZAÇÃO

    @FXML TableView<TbCampeonatoEntity> tableView_lista_campeonatos = new TableView<>();
    @FXML TableColumn<TbCampeonatoEquipesStatusEntity, String> vis_nome_camp_coluna;
    @FXML TableColumn<TbCampeonatoEquipesStatusEntity, Number> vis_premio_campeonatos_coluna;
    @FXML TableColumn<TbCampeonatoEquipesStatusEntity, LocalDate> vis_data_inicio_coluna;
    @FXML TableColumn<TbCampeonatoEquipesStatusEntity, LocalDate> vis_data_termino_coluna;

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
            for (TbEquipesEntity e : list_equipes) {

                equipeEntities.add(
                        new TableViewEquipe(
                                e.getNome(),
                                e.getOrigem(),
                                TableViewEquipe.getIntegrantesbyId( e.getIdEquipe() ) ));
            }



        } catch(NullPointerException err){
            System.out.println("TableView Equipe: Retorno nulo.");
        }

        tableView_lista_equipes.setItems(equipeEntities);
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
                            ObservableList<TbCampeonatoEquipesStatusEntity> info_camp = FXCollections.observableArrayList();

                            List<TbCampeonatoEquipesStatusEntity> results = TbCampeonatoEquipesStatusEntity
                                    .getById( rowData.getIdCampeonato() );
                            for (TbCampeonatoEquipesStatusEntity e: results
                                 ) {
                                info_camp.add(e);
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
                                    tab_vis_equipe.setDisable(false);

                                    inferior_tabPane.getSelectionModel().select(tab_vis_equipe);

                                    TableViewEquipe rowData = row.getItem();
                                    vis_nm_equipe.setText(rowData.getNome());
                                    vis_origem.setText(rowData.getOrigem());

                                    TbEquipesEntity newData = new TbEquipesEntity();

                                    TbEquipesEntity byNome = newData.getByNome( rowData.getNome() );


                                    try {
                                        vis_imageView_equipe.setImage(bytea_to_image(byNome.getImagem()));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    tab_vis_equipe.setText( byNome.getNome() );
                                    ObservableList<TbJogadorEquipeEntity> info_equipe = FXCollections.observableArrayList();

                                    List<TbJogadorEquipeEntity> results = TbJogadorEquipeEntity
                                            .getById( byNome.getIdEquipe() );
                                    for (TbJogadorEquipeEntity e: results
                                    ) {
                                        info_equipe.add(e);
                                    }

                                    vis_tb_info_integrantes.setItems( info_equipe );



                                }
                            }
                    );
                    return row;
                }
        );



        coluna_equipe.setCellValueFactory(new PropertyValueFactory<>("nome"));
        coluna_classificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        coluna_vitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias"));
        coluna_empates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        coluna_derrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas"));

        vis_coluna_equipe.setCellValueFactory(new PropertyValueFactory<>("nome"));
        vis_coluna_classificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        vis_coluna_vitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias"));
        vis_coluna_empates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        vis_coluna_derrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas"));

        vis_nome_camp_coluna.setCellValueFactory(new PropertyValueFactory<>("nome"));
        vis_premio_campeonatos_coluna.setCellValueFactory(new PropertyValueFactory<>("valor"));
        vis_data_inicio_coluna.setCellValueFactory(new PropertyValueFactory<>("dtInicio"));
        vis_data_termino_coluna.setCellValueFactory(new PropertyValueFactory<>("dtFim"));


        vis_coluna_nm_equipe.setCellValueFactory(new PropertyValueFactory<>("nome"));
        vis_coluna_origem_equipe.setCellValueFactory(new PropertyValueFactory<>("origem"));
        vis_coluna_integrantes.setCellValueFactory(new PropertyValueFactory<>("integrantes"));

        vis_coluna_nm_jogador.setCellValueFactory(new PropertyValueFactory<>("nome"));
        vis_coluna_codenome.setCellValueFactory(new PropertyValueFactory<>("codenome"));

        popula_tableview_lista_equipes();
        popula_tableview_lista_campeonatos();
        // Popula a lista de números
        choiceBox_posicao.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        vitorias_ChB.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        empates_ChB.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        derrotas_ChB.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    }
}
