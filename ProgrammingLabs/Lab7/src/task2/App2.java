package task2;

import javax.swing.*;
import java.awt.*;

public class App2 extends task5{

    public App2(){
        super();
        JButton reverseButton = new JButton("Reverse");
        reverseButton.addActionListener(e -> {
            reverseImage();
            panel.repaint();
        });
        //panel.add(reverseButton);

        JButton blurButton = new JButton("Blur");
        blurButton.addActionListener(e ->{
            image = blurImage(image);
            panel.repaint();
        });
        //panel.add(blurButton);

        JButton toGrayButton = new JButton("Gray");
        toGrayButton.addActionListener(e -> {
            image = convertToGrayscale(image);
            panel.repaint();
        });
        //panel.add(toGrayButton);

        JPanel controlPanel = new JPanel();
        controlPanel.add(reverseButton);
        controlPanel.add(blurButton);
        controlPanel.add(toGrayButton);
        panel.add(controlPanel, BorderLayout.NORTH);
    }
}
