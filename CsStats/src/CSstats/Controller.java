package CSstats;


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

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.*;
import java.util.ResourceBundle;



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
    private TextField textField_vitorias;

    @FXML
    private TextField textField_empates;

    @FXML
    private TextField textField_derrotas;


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
    private Label fileSselected;

        @FXML void btn_selecionar_imagem_camp() throws MalformedURLException, FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files",
                "*.bmp", "*.png", "*.jpg");
        File selectedFile;
        selectedFile = fileChooser.showOpenDialog(btn_inserir_imagem_camp.getScene().getWindow());

        if (selectedFile == null) {
            selectedFile = new File("path/to/default/file");
        }


        Image image = new Image(selectedFile.getAbsoluteFile().toURI().toString(),imageView_camp.getFitWidth(),imageView_camp   .getFitHeight(),true,true);
        imageView_camp.setImage(image);
        imageView_camp.setCache(true);
        imageView_camp.setPreserveRatio(true);
        FileInputStream fis = new FileInputStream(selectedFile);
        }



    @FXML void btn_selecionar_imagem_equipe() throws MalformedURLException, FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files",
                "*.bmp", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile;
        selectedFile = fileChooser.showOpenDialog(btn_inserir_imagem_camp.getScene().getWindow());
        if (selectedFile == null) {
            selectedFile = new File("path/to/default/file");
        }


        Image image = new Image(selectedFile.getAbsoluteFile().toURI().toString(),
                imageView_equipe.getFitWidth(),imageView_equipe.getFitHeight(),true,true);
        imageView_equipe.setImage(image);
        imageView_equipe.setCache(true);
        imageView_equipe.setPreserveRatio(true);
        FileInputStream fis = new FileInputStream(selectedFile);
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

    private final String url = "jdbc:postgresql://localhost/APS";
    private final String user = "postgres";
    private final String password = "postgres";


    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void insert_campeonato_equipes_status(){
    }

    public void popula_box_edicao_camp(){
        String query = "SELECT id_equipe, nome FROM tb_equipes";

        try (Connection conn = connect();
             Statement statement = conn.createStatement();
             ResultSet lista_de_equipes = statement.executeQuery(query)) {

            while (lista_de_equipes.next()) {
                comboBox_equipes.getItems().addAll(lista_de_equipes.getString("nome"));
            }

            statement.close();
            lista_de_equipes.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
