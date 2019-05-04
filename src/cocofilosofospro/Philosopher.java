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
public class Philosopher 
{
    public JLabel image;
    public String status;
    public int time;
    public Fork left, right;
    
    public Philosopher(JLabel reference,int time)
    {
        this.time = time;
        this.image = reference;
        this.status = "waiting";
        this.image.setIcon(new Images(status,50,50).getImage());
    }
    
    public void waiting()
    {
        this.status = "waiting";
        this.image.setIcon(new Images(status,50,50).getImage());
    }
    
    public void thinking()
    {
        this.status="thinking";
        this.image.setIcon(new Images(status,50,50).getImage());        
    }
    
    public void eating()
    {
        this.status="eating";
        this.image.setIcon(new Images(status, 50, 50).getImage());
    }
}
