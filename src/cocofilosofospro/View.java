/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocofilosofospro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author ivan_
 */
public class View extends JFrame {

    protected ImageIcon eating;
    protected ImageIcon thinking;
    protected ImageIcon waiting;
    protected ImageIcon free;
    protected ImageIcon using;
    protected int width;
    protected int height;
    protected JButton begin;
    protected JLabel[] philosophers;
    protected JLabel[] forks;

    View() {
        width = 500;
        height = 500;

        philosophers = new JLabel[5];
        forks = new JLabel[5];

        initImages();
        initWindow();
        initComponents();

        this.setLayout(null);
    }

    protected void initImages() 
    {
        eating = getImage("https://www.dictionary.com/e/wp-content/uploads/2018/03/Thinking_Face_Emoji-Emoji-Island.png", 50, 50);
        thinking = getImage("https://www.dictionary.com/e/wp-content/uploads/2018/03/Thinking_Face_Emoji-Emoji-Island.png", 50, 50);
        waiting = getImage("https://www.dictionary.com/e/wp-content/uploads/2018/03/Thinking_Face_Emoji-Emoji-Island.png", 50, 50);

        free = getImage("https://i2.wp.com/www.eatlikeabeast.com/wp-content/uploads/2016/06/cropped-fork-knife-emoji.png", 50, 50);
        using = getImage("https://i2.wp.com/www.eatlikeabeast.com/wp-content/uploads/2016/06/cropped-fork-knife-emoji.png", 50, 50);
    }

    protected ImageIcon getImage(String source, int w, int h) {
        try {
            ImageIcon icon = new ImageIcon(new URL(source));
            Image resize = icon.getImage();
            resize = resize.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(resize);
        } catch (MalformedURLException ex) {
            System.out.println("Error al obtener URL: " + ex.toString());
            return null;
        }
    }

    protected void initComponents() {
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new JLabel(thinking);
            philosophers[i].setBounds(10 + i * 60, 10, 50, 50);

            //this.getContentPane().add(philosophers[i]);
            this.add(philosophers[i]);

            System.out.println("Icono agregado");
        }
    }

    protected void initWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setTitle("Cena de filósofos");

        Color color = new Color(255, 255, 255, 255);
        this.getContentPane().setBackground(color);

        begin = new JButton();
        begin.setBounds(370, 420, 100, 30);
        Font font = new Font("Arial", Font.PLAIN, 12);
        begin.setText("Empezar");
        begin.setFont(font);

        begin.addActionListener(e
                -> {
            startEating();
        });

        this.add(begin);
    }

    protected void startEating() {
        System.out.println("Empezando...");
    }

}
