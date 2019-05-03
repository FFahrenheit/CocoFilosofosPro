/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocofilosofospro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    protected ImageIcon freeImage;
    protected ImageIcon using;
    protected ImageIcon tableImage;
    protected int width;
    protected int height;
    protected JButton begin;
    protected JLabel[] philosophers;
    protected JLabel[] forks;
    protected JLabel table;

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

        freeImage = getImage("https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/160/facebook/92/hocho_1f52a.png", 40, 40);
        using = getImage("https://www.swankyinteriors.co.uk/images/products/1439243510-73640900.jpg", 40, 40);
        
        tableImage = getImage("https://www.crossfurniture.co.uk/wp-content/uploads/2018/06/Ponderosa-Grey.png",150,150);
    }

    protected ImageIcon getImage(String source, int w, int h) {
        try {
            //ImageIcon icon = new ImageIcon(new URL(source));
            //Image resize = icon.getImage();
            Image resize = ImageIO.read(new URL(source).openStream());
            resize = resize.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(resize);
        } catch (MalformedURLException ex) {
            System.out.println("Error al obtener URL: " + ex.toString());
            return null;
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    protected void initComponents() 
    {
        Integer posPh[][] = { 
        {182,10},
        {340,150},
        {300,300},
        {100,300},
        {40,150}
        };
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new JLabel(thinking);
            philosophers[i].setBounds(posPh[i][0] , posPh[i][1], 50, 50);
            this.add(philosophers[i]);
        }
        Integer posFork[][] = {
            {(posPh[0][0]+posPh[1][0])/2 ,(posPh[0][1]+posPh[1][1])/2},
            {(posPh[1][0]+posPh[2][0])/2,(posPh[1][1]+posPh[2][1])/2},
            {(posPh[2][0]+posPh[3][0])/2,(posPh[2][1]+posPh[3][1])/2},
            {(posPh[3][0]+posPh[4][0])/2,(posPh[3][1]+posPh[4][1])/2},
            {(posPh[4][0]+posPh[0][0])/2,(posPh[4][1]+posPh[0][1])/2}
        };
        for(int i=0; i<forks.length; i++)
        {
            forks[i] = new JLabel(freeImage);
            forks[i].setBounds(posFork[i][0] , posFork[i][1], 40, 40);
            this.add(forks[i]);
        }
        
        table = new JLabel(tableImage);
        table.setBounds(140,110,150,150);
        this.add(table);
        
    }

    protected void initWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setTitle("Cena de filósofos");

        Color color = new Color(240, 240, 240, 255);
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
