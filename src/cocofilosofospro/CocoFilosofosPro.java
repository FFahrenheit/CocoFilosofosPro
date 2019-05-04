/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocofilosofospro;

/**
 *
 * @author ivan_
 */
public class CocoFilosofosPro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        int durations[] = {1000,1000,1000,1000,1000};
        View view = new View(durations);
        view.setVisible(true);
    }
    
}
