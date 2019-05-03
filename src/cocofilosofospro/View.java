/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocofilosofospro;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author ivan_
 */
public class View extends JFrame {

    protected int width;
    protected int height;
    protected JButton begin;

    View() 
    {
        width = 400;
        height = 400;
        initWindow();
        this.setLayout(null);
    }

    protected void initWindow() 
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setTitle("Cena de filósofos");

        Color color = new Color(102, 153, 153, 255);
        this.getContentPane().setBackground(color);

        begin = new JButton();
        begin.setBounds(340, 360, 50, 20);
        Font font = new Font("Arial", Font.PLAIN, 15);
        begin.setText("Bienvenido");
        begin.setFont(font);
        
        begin.addActionListener(e->
        {
            startEating();
        });

        this.add(begin);
    }
    
    protected void startEating()
    {
        System.out.println("Empezando...");
    }

}
