/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocofilosofospro;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Clase para manejar los recursos
 * gráficos como imágenes
 * @author ivan_
 */
public class Images {

    private ImageIcon image;

    public Images(String form, int w, int h) 
    {
        setImage(form,w,h);
    }

    public ImageIcon getImage() 
    {
        return this.image;
    }

    public void setImage(String form, int w, int h) {
        String source = "src/images/"+form+".png";
        File file = new File(source);
        try 
        {
            Image resize = ImageIO.read(file);
            resize = resize.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            this.image = new ImageIcon(resize);
        } 
        catch (IOException ex) 
        {
            System.out.println("Error: " + ex.toString());
            if(source.equals("default"))
            {
                this.image = null; //Avoid infinite loop
            }
            else 
            {
                setImage("default",w,h);
            }
        }
    }

}
