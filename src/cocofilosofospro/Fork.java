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
    private ReentrantLock used;
    private Images source;
    
    public Fork(JLabel reference, Images source)
    {
        this.source = source;
        this.image = reference;
        this.setImage("free");
        this.used = new ReentrantLock();
    }
    
    public void setImage(String st)
    {
        this.image.setIcon(source.getImage(st));
    }
    
    public boolean tryHold()
    {
        return used.tryLock();
    }
    
    public void free()
    {
        if (used.isHeldByCurrentThread()) 
        {
            used.unlock();
        }
    }
}
