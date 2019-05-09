package cocofilosofospro;

import javax.swing.JLabel;

/**
 * En esta clase se manipula el 
 * control total de los hilos existentes
 * y se crean las relaciones entre la 
 * vista y objetos
 * @author ivan_
 */
public class BackEnd
{
    protected Philosopher[] philosopher;
    protected Fork[] fork;
    protected Thread[] thread;
    
    /***
     * Se crean los hilos que manejará
     * cada filósofo
     */
    public void start()
    {
        for(int i=0; i<philosopher.length;i++)
        {
            thread[i] = new Thread(philosopher[i]);
            thread[i].start();
        }
    }
    
    /***
     * Se inicializan los componentes controladores
     * con una vista definda, se crean filósofos con cierto
     * tiempo de duración y tenedores.
     * @param ph Vistas de filósofos
     * @param fork Vistas de tenedores
     * @param duration Arreglo de duraciones correspondientes
     */
    public BackEnd(JLabel[] ph, JLabel[] fork, int[] duration, Images source)
    {
        this.thread = new Thread[ph.length];
        this.fork = new Fork[fork.length];
        this.philosopher = new Philosopher[ph.length];
        for (int i = 0; i < fork.length; i++) 
        {
            this.fork[i] = new Fork(fork[i],source);
        }
        for (int i = 0; i < ph.length; i++) 
        {
            this.philosopher[i] = new Philosopher(ph[i],duration[i],i,source);
            if(i==0)
            {
                this.philosopher[i].setForks(this.fork[fork.length-1], this.fork[i]);
            }
            else 
            {
                this.philosopher[i].setForks(this.fork[i-1], this.fork[i]);
            }
        }
        System.out.println("Ya quedó");
    }
}
