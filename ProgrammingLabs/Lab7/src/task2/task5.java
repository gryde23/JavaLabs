package task2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class task5 {
    protected JFrame frame;
    protected JPanel panel;
    protected BufferedImage image;

    public task5() {
        frame = new JFrame("Task 5");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage im;
        String imFile = "C:/Users/Денис/Downloads/5.jpg";
        try {
            im = ImageIO.read(new File(imFile));
        } catch (IOException ioe) {
            im = null;
        }
        image = im;
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                double scale = Math.min(getWidth() / (double) image.getWidth(), getHeight() / (double) image.getHeight());
                int width = (int) (image.getWidth() * scale);
                int height = (int) (image.getHeight() * scale);
                int x = (getWidth() - width) / 2;
                int y = (getHeight() - height) / 2;
                g.drawImage(image, x, y, width, height, this);
            }
        };
//        frame.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                char c = e.getKeyChar();
//                switch (c) {
//                    case 'R', 'r' -> {
//                        AffineTransform transform = new AffineTransform();
//                        transform.rotate(Math.PI, image.getWidth() / 2, image.getHeight() / 2);
//                        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
//                        image = op.filter(image, null);
//                    }
//                    case 'B', 'b' -> {
//                        image = blurImage(image);
//                    }
//                    case 'G', 'g' -> {
//                        image = convertToGrayscale(image);
//                    }
//                }
//            }
//        });
//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                panel.repaint();
//            }
//        });
        frame.add(panel);
        frame.setVisible(true);
    }

    protected void reverseImage(){
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.PI, image.getWidth() / 2, image.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        image = op.filter(image, null);
    }

    protected BufferedImage blurImage(BufferedImage image) {
        BufferedImage blurredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 3; i < image.getWidth() - 3; i++) {
            for (int j = 3; j < image.getHeight() - 3; j++) {
                Color avgColor = calculateAverageColor(image, i, j);
                blurredImage.setRGB(i, j, avgColor.getRGB());
            }
        }
        return blurredImage;
    }

    protected Color calculateAverageColor(BufferedImage image, int x, int y) {
        int sumR = 0, sumG = 0, sumB = 0;
        for (int i = x - 3; i <= x + 3; i++) {
            for (int j = y - 3; j <= y + 3; j++) {
                Color pixelColor = new Color(image.getRGB(i, j));
                sumR += pixelColor.getRed();
                sumG += pixelColor.getGreen();
                sumB += pixelColor.getBlue();
            }
        }
        return new Color(sumR / 49, sumG / 49, sumB / 49);
    }
    protected BufferedImage convertToGrayscale(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage grayscaleImage = new BufferedImage(width, height, originalImage.getType());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = originalImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                int average = (r + g + b) / 3;
                grayscaleImage.setRGB(x, y, (average << 16) | (average << 8) | average);
            }
        }

        return grayscaleImage;
    }

}
