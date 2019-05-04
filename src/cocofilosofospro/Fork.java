/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocofilosofospro;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JLabel;

/**
 * Clase manipualadora de los
 * tenedores
 * @author ivan_
 */
public class Fork 
{
    private JLabel image;
    private String status;
    private ReentrantLock used;
    private AtomicBoolean inUse;
    
    public Fork(JLabel reference)
    {
        this.inUse = new AtomicBoolean(false);
        this.image = reference;
        this.status = "free";
        this.image.setIcon(new Images(status,40,40).getImage());
        this.used = new ReentrantLock();
    }
    
    public boolean tryHold()
    {
        return used.tryLock();
    }
    
    public void getStatus()
    {
        if(inUse.get())
        {
            this.status = "free";
            this.image.setIcon(new Images(status,40,40).getImage());
        }
        else 
        {
            this.status = "using";
            this.image.setIcon(new Images(status,40,40).getImage());
        }
    }
    
    public AtomicBoolean isUsing()
    {
        return this.inUse;
    }
    
    public boolean isUsedForMe()
    {
        return used.isHeldByCurrentThread();
    }
    
    public void free()
    {
        if (used.isHeldByCurrentThread()) 
        {
            used.unlock();
            this.status = "free";
            this.image.setIcon(new Images(status,40,40).getImage());
        }
    }
}
