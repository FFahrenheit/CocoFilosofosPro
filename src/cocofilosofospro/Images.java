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

    /**
     * Asigna una imagen con una clave y un alto y largo
     * @param form clave
     * @param w largo
     * @param h alto
     */
    public Images(String form, int w, int h) 
    {
        setImage(form,w,h);
    }

    /**
     * Devuelve el ícono generado
     * @return icono generado
     */
    public ImageIcon getImage() 
    {
        return this.image;
    }
    
    /**
     * Basado en una clave, obtiene una imagen 
     * redimensionada 
     * @param form clave
     * @param w largo
     * @param h alto
     */
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
