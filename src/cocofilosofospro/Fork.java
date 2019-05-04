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
public class Fork 
{
    public JLabel image;
    public String status;
    
    public Fork(JLabel reference)
    {
        this.image = reference;
        this.status = "free";
        this.image.setIcon(new Images(status,40,40).getImage());
    }
    
    public void using()
    {
        this.status = "using";
        this.image.setIcon(new Images(status,40,40).getImage());
    }
    
    public void free()
    {
        this.status = "free";           
        this.image.setIcon(new Images(status,40,40).getImage());
    }
}
