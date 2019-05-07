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
public class Images 
{
    private ImageIcon free;
    private ImageIcon using;
    private ImageIcon thinking;
    private ImageIcon waiting;
    private ImageIcon eating;
    private ImageIcon table;

    /**
     * Instancia todas las imágenes
     * posibles
     */
    public Images() 
    {
        free = setImage("free",40,40);
        using = setImage("using",40,40);
        thinking = setImage("thinking",50,50);
        waiting = setImage("waiting",50,50);
        eating = setImage("eating",50,50);
        table = setImage("table",150,150);
    }

    /**
     * Devuelve el ícono generado
     * @return icono generado
     */
    public ImageIcon getImage(String form) 
    {
        switch(form)
        {
            case "free":
                return this.free;
            case "using":
                return this.using;
            case "thinking":
                return this.thinking;
            case "waiting":
                return this.waiting;
            case "eating":
                return this.eating;
            case "table":
                return this.table;
            default:
                return null;
        }
    }
    
    /**
     * Basado en una clave, obtiene una imagen 
     * redimensionada 
     * @param form clave
     * @param w largo
     * @param h alto
     */
    public ImageIcon setImage(String form, int w, int h) 
    {
        String source = "src/images/"+form+".png";
        File file = new File(source);
        try 
        {
            Image resize = ImageIO.read(file);
            resize = resize.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(resize);
        } 
        catch (IOException ex) 
        {
            System.out.println("Error: " + ex.toString());
            if(source.equals("default"))
            {
                return null; //Avoid infinite loop
            }
            else 
            {
                return setImage("default",w,h);
            }
        }
    }

}
