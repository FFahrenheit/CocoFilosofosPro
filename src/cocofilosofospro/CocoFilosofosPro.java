package cocofilosofospro;

/**
 * Clase para instanciar la vista,
 * solo genera los tiempos y los envía a
 * la vista.
 * @author ivan_
 */
public class CocoFilosofosPro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        int durations[] = {1000,2000,3000,4000,5000};
        View view = new View(durations);
        view.setVisible(true);
    }
    
}
