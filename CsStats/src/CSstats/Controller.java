package CSstats;


import DAO.DaoCRUD;
import DAO.DaoConecta;
import MODEL.IEntity;
import MODEL.TbCampeonatoEntity;
import MODEL.TbEquipesEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javafx.scene.image.ImageView;

import javax.persistence.TypedQuery;
import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import static DAO.DaoCRUD.*;


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
    private TableView<TbEquipesEntity> tb_info_equipe;

    @FXML
    private TableColumn<TbEquipesEntity, String> coluna_equipe;

    @FXML
    private TableColumn<TbEquipesEntity, Integer> coluna_classificacao;

    @FXML
    private TableColumn<TbEquipesEntity, Integer> coluna_vitorias;

    @FXML
    private TableColumn<TbEquipesEntity, Integer> coluna_empates;

    @FXML
    private TableColumn<TbEquipesEntity, Integer> coluna_derrotas;

    @FXML TextField origem_equipe;
    @FXML TextField nm_equipe;

    @FXML Label except_equipe_image;
    @FXML Label except_camp_image;

    private File imagefile_camp;
    private File imagefile_equipe;

    public  ObservableList<TbEquipesEntity> table_equipes(){
        ObservableList<TbEquipesEntity> equipe = FXCollections.observableArrayList();
        equipe.add(new TbEquipesEntity(nm_equipe.getText(), imagem_e, origem_equipe.getText()));

        return equipe;
        
    }

    private byte[] imagem_e;

    //Popula a lista de equipes
    @FXML
    private void btn_adicionar_equipe_em_tableView(){


        ObservableList<TbEquipesEntity> data = table_equipes();
        System.out.println(table_equipes());
        System.out.println(choiceBox_posicao.getValue());
        System.out.println(textField_derrotas.getText());
        System.out.println(textField_empates.getText());
        System.out.println(textField_vitorias.getText());

        tb_info_equipe.setItems(data);




    }

    @FXML
    private void btn_remover_de_tableView(){}

    @FXML
    private void btn_adicionar_jogador_tableView(){}

    @FXML
    public void handle_inserir_camp() {
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
    public void handle_inserir_equipe(){
        String nome = nm_equipe.getText();
        String origem = origem_equipe.getText();
        byte[] imagem = new byte[0];
        try {
            imagem = Util.image_to_bytea(imagefile_equipe);
            except_equipe_image.setText("");
        } catch (IOException e) {
            except_equipe_image.setText("Escolha um arquivo de imagem.");
        }

        TbEquipesEntity equipe = new TbEquipesEntity();
        equipe.setNome(nome);
        equipe.setOrigem(origem);
        equipe.setImagem(imagem);
        insert(equipe);
    }


    @FXML
    private Label fileSselected;

        @FXML File btn_selecionar_imagem_camp() throws MalformedURLException, FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files",
                "*.bmp", "*.png", "*.jpg");
        File selectedFile;
        selectedFile = fileChooser.showOpenDialog(btn_inserir_imagem_camp.getScene().getWindow());

        if (selectedFile == null) {
            selectedFile = new File("path/to/default/file");
        }


        Image image_camp = new Image(selectedFile.getAbsoluteFile().toURI().toString(),
                imageView_camp.getFitWidth(),imageView_camp   .getFitHeight(),true,true);
        imageView_camp.setImage(image_camp);
        imageView_camp.setCache(true);
        imageView_camp.setPreserveRatio(true);
        FileInputStream fis = new FileInputStream(selectedFile);
        imagefile_camp = selectedFile;
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
            selectedFile = new File("path/to/default/file");
        }


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
        equipe_selecionada = comboBox_equipes.getSelectionModel().getSelectedItem();
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
            TypedQuery<TbEquipesEntity> query =
                    DaoConecta.em.createQuery("SELECT c.nome FROM TbEquipesEntity as c", TbEquipesEntity.class);
            List<TbEquipesEntity> resultado = query.getResultList();

            for(TbEquipesEntity e: resultado){
                if(e != null){
                comboBox_equipes.getItems().addAll(e.getNome());
                }
            }

        } catch(NullPointerException e){
            comboBox_equipes.getItems().clear();
            System.out.println("Não consegui nada.");
        }


    }


    public void initialize(URL location, ResourceBundle resources) {
        popula_box_edicao_camp();
        // Popula a lista de números
        choiceBox_posicao.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));

        coluna_equipe.setCellValueFactory(new PropertyValueFactory<>("nome"));
        coluna_classificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        coluna_vitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias"));
        coluna_empates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        coluna_derrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas"));






    }
}
