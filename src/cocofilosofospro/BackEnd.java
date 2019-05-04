/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocofilosofospro;

import java.util.concurrent.Semaphore;
import javax.swing.JLabel;

/**
 *
 * @author ivan_
 */
public class BackEnd
{
    protected Philosopher[] philosopher;
    protected Fork[] fork;
    protected Thread[] thread;
    public static Semaphore coach = new Semaphore(0);
    
    public void start()
    {
        for(int i=0; i<philosopher.length;i++)
        {
            thread[i] = new Thread(philosopher[i]);
            thread[i].start();
        }
        new Thread(()->
                {
                    while(true)
                    {
                        for(int i=0; i<fork.length;i++)
                        {
                            fork[i].getStatus();
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
    
    public BackEnd(JLabel[] ph, JLabel[] fork, int[] duration)
    {
        this.thread = new Thread[ph.length];
        this.fork = new Fork[fork.length];
        this.philosopher = new Philosopher[ph.length];
        for (int i = 0; i < fork.length; i++) 
        {
            this.fork[i] = new Fork(fork[i]);
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
