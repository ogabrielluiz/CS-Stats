package CSstats;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Util {

    public static Date localDate_to_SQLdate(LocalDate local_date){
        Instant instant = Instant.from(local_date.atStartOfDay(ZoneId.systemDefault()));
        Date date = (Date) Date.from(instant);
        return date;
    }

    public static byte[] image_to_bytea(File imagefile) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new File(String.valueOf(imagefile)));
        ByteArrayOutputStream byteOutpout = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteOutpout );
        byte [] bytes = byteOutpout.toByteArray();


        return bytes;

    }
}
