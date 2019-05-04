/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocofilosofospro;

import javax.swing.JLabel;

/**
 *
 * @author ivan_
 */
public class BackEnd 
{
    public Philosopher[] philosopher;
    public Fork[] fork;
    
    public BackEnd(JLabel[] ph, JLabel[] fork, int[] duration)
    {
        this.fork = new Fork[fork.length];
        for (int i = 0; i < fork.length; i++) 
        {
            this.fork[i] = new Fork(fork[i]);
        }
        
        this.philosopher = new Philosopher[ph.length];
        for (int i = 0; i < ph.length; i++) 
        {
            this.philosopher[i] = new Philosopher(ph[i],duration[i]);
        }
    }
}
