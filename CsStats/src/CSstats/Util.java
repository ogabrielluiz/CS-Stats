package CSstats;

import MODEL.IEntity;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import org.postgresql.util.Base64;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
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

}
