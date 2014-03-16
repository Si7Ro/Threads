import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Frame{

    /** метод, который конкретно в файле закрашивает рамку */
   public static void addFrame(String path, String file) {

        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path + file));

            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            WritableRaster raster = bufferedImage.getRaster();

            for (int i = 0; i < width; i++) {                           /** вычисление координат рамки, и сипользование метод для закрашивания */
                for (int j = 0; j < 20; j++) {
                    setPixels(raster, i, j);
                }
            }

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < height; j++) {
                    setPixels(raster, i, j);
                }
            }

            for (int i = width-20; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    setPixels(raster, i, j);
                }
            }

            for (int i = 0; i < width; i++) {
                for (int j = height-20; j < height; j++) {
                    setPixels(raster, i, j);
                }
            }

            ImageIO.write(bufferedImage, "png", new File(path + "new_" + file));
        } catch (NullPointerException ex) {                                                     /** исключение если файл не является картинкой */
            System.out.println(file + " не картинка!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /** метод замены пикселей на черный цвет, по заданным координатам, на самом деле похоже на магию*/
    private static void setPixels(WritableRaster raster, int i, int j) {
        int[] pixels = raster.getPixel(i, j, (int[]) null);
        pixels[0] = 0;
        pixels[1] = 0;
        pixels[2] = 0;
        raster.setPixel(i, j, pixels);
    }


}