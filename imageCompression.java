import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class imageCompression {

  public static void main(String[] args) throws IOException {

    BufferedImage image = ImageIO.read(imageCompression.class.getResource("test.jpg"));
    PrintStream output = new PrintStream(new File("test2.txt"));

    System.out.println("Testing convertTo2DUsingGetRGB:");

    int[][] result = convertTo2DUsingGetRGB(image, output);
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    File inputFile = new File("test2.txt");
    Scanner sc = new Scanner(inputFile);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Color rgb = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
        image2.setRGB(x, y, rgb.getRGB());
      }
    }

    File outputFile = new File("output.jpg");
    ImageIO.write(image2, "jpg", outputFile);
  }

  private static int[][] convertTo2DUsingGetRGB(BufferedImage image, PrintStream output) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][] result = new int[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        result[row][col] = image.getRGB(col, row);
        output.print((result[row][col] & 0xff0000) >> 16);
        output.print(" ");
        output.print((result[row][col] & 0xff00) >> 8);
        output.print(" ");
        output.print(result[row][col] & 0xff);
        output.print(" ");
      }
      output.println();
    }

    return result;
  }
}
