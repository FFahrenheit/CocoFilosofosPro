package cocofilosofospro;

import java.util.concurrent.Semaphore;
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
    public static Semaphore coach = new Semaphore(0);
    
    /***
     * Se crean los hilos que manejará
     * cada filósofo y el hilo que lo 
     * controlará, un semáforo.
     */
    public void start()
    {
        for(int i=0; i<philosopher.length;i++)
        {
            thread[i] = new Thread(philosopher[i]);
            thread[i].start();
        }
        new Thread(()->
                {
                    for(int i=0; i<fork.length;i++)
                    {
                        fork[i].setImage("free");
                    }
                    while(true)
                    {
                        boolean isUsed[] =  {false,false,false,false,false}; 
                        for (int i = 0; i < philosopher.length; i++) 
                        {
                            if(philosopher[i].status.equals("eating"))
                            {
                                philosopher[i].leftFork.setImage("using");                           
                                philosopher[i].rightFork.setImage("using");
                                isUsed[philosopher[i].rightFork.id] = true;
                                isUsed[philosopher[i].leftFork.id] = true;
                            }
                        }
                        for (int i = 0; i < philosopher.length; i++) 
                        {
                            if(!isUsed[i])
                            {
                                fork[i].setImage("free");
                            }
                            
                        }
                        try 
                        {
                            coach.acquire();
                        } catch (InterruptedException ex) 
                        {
                            System.out.println("Error con semáforo: "+ex.toString());
                        }
                    }
                }).start();
    }
    
    /***
     * Se inicializan los componentes controladores
     * con una vista definda, se crean filósofos con cierto
     * tiempo de duración y tenedores.
     * @param ph Vistas de filósofos
     * @param fork Vistas de tenedores
     * @param duration Arreglo de duraciones correspondientes
     */
    public BackEnd(JLabel[] ph, JLabel[] fork, int[] duration)
    {
        this.thread = new Thread[ph.length];
        this.fork = new Fork[fork.length];
        this.philosopher = new Philosopher[ph.length];
        for (int i = 0; i < fork.length; i++) 
        {
            this.fork[i] = new Fork(fork[i],i);
        }
        for (int i = 0; i < ph.length; i++) 
        {
            this.philosopher[i] = new Philosopher(ph[i],duration[i],i);
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
