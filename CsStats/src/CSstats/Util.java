package CSstats;

import DAO.DaoConecta;
import MODEL.IEntity;
import MODEL.TbCampeonatoEntity;
import MODEL.TbEquipesEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import org.postgresql.util.Base64;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;

public class Util {

    public static Date localDate_to_SQLdate(LocalDate local_date){
        Date date = Date.valueOf(local_date);
        return date;
    }

    public static byte[] image_to_bytea(File imagefile) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new File(String.valueOf(imagefile)));
        ByteArrayOutputStream byteOutpout = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteOutpout );
        byte [] bytes = byteOutpout.toByteArray();


        return bytes;

    }

    public static Image bytea_to_image(byte[] bytes) throws IOException {
        String base64String =  Base64.encodeBytes(bytes);
        byte[] bytearray = Base64.decode(base64String);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytearray));
        Image output = SwingFXUtils.toFXImage(image,null);
        return output;
    }

    public static boolean is_not_empty(String s){

        final boolean b = Pattern.compile("\\w").matcher(s).find();
        return b;
    }

    public static ObservableList<TbCampeonatoEntity> add_camp_into_obsList(TbCampeonatoEntity obj){
        ObservableList<TbCampeonatoEntity> obList = FXCollections.observableArrayList();
        obList.add(obj);
        return obList;
    }

    public static ObservableList<TbEquipesEntity> add_equipe_into_obsList(TbEquipesEntity obj){
        ObservableList<TbEquipesEntity> obList = FXCollections.observableArrayList();
        obList.add(obj);
        return obList;
    }

    public static boolean containsId(List<TableViewCamp> list, TableViewCamp obj) {
        for (TableViewCamp object : list) {
            if (object.getNome() == obj.getNome() && object.getClassificacao() == obj.getClassificacao()) {
                return true;
            }
        }
        return false;
    }

    public static Alert alertaErro(String texto) {
        Alert alert = new Alert( Alert.AlertType.ERROR, texto, ButtonType.OK );

        return alert;
    }

    public static Alert alertaAviso(String texto) {
        Alert alert = new Alert( Alert.AlertType.INFORMATION, texto, ButtonType.OK );

        return alert;
    }




}
