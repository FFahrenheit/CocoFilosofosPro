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
    private int id;
    private Images source;
    
    public Fork(JLabel reference, int id, Images source)
    {
        this.source = source;
        this.id = id;
        this.inUse = new AtomicBoolean(false);
        this.image = reference;
        this.status = "free";
        this.image.setIcon(source.getImage(status));
        this.used = new ReentrantLock();
    }
    
    public void setImage(String st)
    {
        this.image.setIcon(source.getImage(st));
        this.status = st;
    }
    
    public boolean tryHold()
    {
        return used.tryLock();
    }
    
    /*public void getStatus()
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
    }*/
    
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
