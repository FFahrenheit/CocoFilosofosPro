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
    public String status;
    private ReentrantLock used;
    private AtomicBoolean inUse;
    public int id;
    
    public Fork(JLabel reference, int id)
    {
        this.id = id;
        this.inUse = new AtomicBoolean(false);
        this.image = reference;
        this.status = "free";
        this.image.setIcon(new Images(status,40,40).getImage());
        this.used = new ReentrantLock();
    }
    
    public void setImage(String st)
    {
        this.image.setIcon(new Images(st,40,40).getImage());
        this.status = st;
    }
    
    public boolean tryHold()
    {
        return used.tryLock();
    }
    
    public void getStatus()
    {
        if(inUse.get())
        {
            this.status = "using";
        }
        else 
        {
            this.status = "free";
        }
        this.image.setIcon(new Images(this.status,40,40).getImage());
    }
    
    public AtomicBoolean isUsing()
    {
        return this.inUse;
    }
    
    public void free()
    {
        if (used.isHeldByCurrentThread()) 
        {
            used.unlock();
        }
    }
}
