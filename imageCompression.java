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
    File inputFile = new File("test2.txt");
    Scanner sc = new Scanner(inputFile);
    System.out.println("Testing convertTo2DUsingGetRGB:");
    int[][] result = convertTo2DUsingGetRGB(image, output);
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Color rgb = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
        image2.setRGB(x, y, rgb.getRGB());
      }
    }
    File outputFile = new File("output.jpg");
    ImageIO.write(image2, "jpg", outputFile);
    PrintStream outputCompressed = new PrintStream(new File("compressed.txt"));
    compress(inputFile, outputCompressed, inputFile);
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

  public static void compress(File inputFile, PrintStream outputCompressed, File inputFile2)
      throws FileNotFoundException {
    Scanner outerSc = new Scanner(inputFile);
    Scanner firstScanner = new Scanner(inputFile2);
    while (outerSc.hasNextLine()) {
      Scanner innerSc = new Scanner(outerSc.nextLine());
      int first = firstScanner.nextInt();
      firstScanner.nextLine();
      outputCompressed.print(first + " ");
      while (innerSc.hasNextInt()) {
        int val = 0;
        if (innerSc.hasNextInt()) {
          val = innerSc.nextInt();
        }
        if (innerSc.hasNextInt()) {
          val += innerSc.nextInt();
        }
        outputCompressed.print(val + " ");
      }
      outputCompressed.println();
    }
  }
}
