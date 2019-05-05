package cocofilosofospro;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Parte de la vista del programa,
 * maneja todo lo visible y envía 
 * los componentes al "back end"
 * que lo manipulará.
 * @author ivan_
 */
public class View extends JFrame 
{
    protected int width;
    protected int height;
    protected JButton begin;
    protected JLabel table;
    protected JLabel[] philosophers;
    protected JLabel[] forks;
    protected int[] durations;

    /**
     * Aquí se crea la ventana y se llaman a las
     * funciones auxiliares para crear la vista
     * @param time Los tiempos para sincronizar 
     * el programa
     */
    View(int[] time) {
        durations = time;
        width = 500;
        height = 500;

        philosophers = new JLabel[5];
        forks = new JLabel[5];

        initWindow();
        initComponents();

        this.setLayout(null);
    }

    /**
     * Se inicializan los filósofos, la mesa
     * y los tenedores a la vista
     */
    protected void initComponents() 
    {
        int posPh[][] = { 
        {182,10}, //Up
        {340,150}, //Right middle
        {300,300}, //Right bottom
        {100,300}, //Left bottom
        {40,150} //Left midle
        };
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new JLabel(new Images("waiting",50,50).getImage());
            philosophers[i].setBounds(posPh[i][0] , posPh[i][1], 50, 50);
            this.add(philosophers[i]);
        }
        int posFork[][] = {
            {(posPh[0][0]+posPh[1][0])/2 ,(posPh[0][1]+posPh[1][1])/2}, //Right top
            {(posPh[1][0]+posPh[2][0])/2,(posPh[1][1]+posPh[2][1])/2}, //Middle 
            {(posPh[2][0]+posPh[3][0])/2,(posPh[2][1]+posPh[3][1])/2}, //
            {(posPh[3][0]+posPh[4][0])/2,(posPh[3][1]+posPh[4][1])/2}, //
            {(posPh[4][0]+posPh[0][0])/2,(posPh[4][1]+posPh[0][1])/2} //
        };
        for(int i=0; i<forks.length; i++)
        {
            forks[i] = new JLabel(new Images("free",40,40).getImage());
            forks[i].setBounds(posFork[i][0] , posFork[i][1], 40, 40);
            this.add(forks[i]);
        }
        
        table = new JLabel(new Images("table",150,150).getImage());
        table.setBounds(140,110,150,150);
        this.add(table);
    }
    
    /**
     * Se inicializa la ventana como tal
     * con su menú principal
     */
    protected void initWindow() 
    {
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

        begin.addActionListener(e  -> {
            System.out.println("Empezando...");
            BackEnd backEnd = new BackEnd(philosophers, forks, durations);
            backEnd.start();
            begin.setEnabled(false);        
        });
        this.add(begin);
    }
    
    
    /**
     * Aquí se instancia a la parte
     * lógica del programa para empezar a que
     * corran los hilos
     */

}
