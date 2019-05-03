/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocofilosofospro;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author ivan_
 */
public class Images {

    ImageIcon image;

    Images(String form, int w, int h) {
        switch (form) {
            case "thinking":
                image = getImage("src/images/thinking.png", w, h);
                break;
            case "eating":
                image = getImage("src/images/eating.png", w, h);
                break;
            case "table":
                image = getImage("src/images/table.png",w,h);
                break;
            case "waiting":
                image = getImage("src/images/waiting.png", w, h);
                break;
            case "free":
                image = getImage("src/images/free.png", w, h);
                break;
            case "using":
                image = getImage("src/images/using.png", w, h);
                break;
            default:
                image = getImage("src/images/default.png", w, h);
                break;
        }
    }

    public ImageIcon getImage() {
        return this.image;
    }

    public ImageIcon getImage(String source, int w, int h) {
        File file = new File(source);
        System.out.println(""+file.getPath());
        try {
            Image resize = ImageIO.read(file);
            resize = resize.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(resize);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
            return null;
        }
    }

}
