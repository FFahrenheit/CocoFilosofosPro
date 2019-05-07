package cocofilosofospro;

import javax.swing.JLabel;

/**
 * Esta clase maneja a los filósofos,
 * relaciona una imagen, un estado,
 * un tiempo y sus tenedores correspondientes
 * @author ivan_
 */
public class Philosopher implements Runnable {

    private JLabel image;
    private String status;
    private int time;
    private Fork leftFork, rightFork;
    private int id;
    private Images source;

    /**
     * Asigna los tenedores. Recordar que son
     * instancias como tal, asi que es como 
     * manejar un puntero
     * @param left Tenedores izquierdo
     * @param right Tenedor derecho
     */
    public void setForks(Fork left, Fork right) {
        this.leftFork = left;
        this.rightFork = right;
    }

    /**
     * Se crea el objeto basado en una referencia, un id y
     * una duración
     * @param reference Vista
     * @param time Tiempo que durará
     * @param id Identificador único para logs
     */
    public Philosopher(JLabel reference, int time, int id, Images source) 
    {
        this.source = source;
        this.id = id;
        this.time = time;
        this.image = reference;
        this.status = "thinking";
        this.image.setIcon(source.getImage(status));
    }

    /**
     * Actualiza el estado actual con 
     * la vista
     */
    public void getStatus() {
        updateActivity(status);
    }

    /**
     * Asigna el estado a esperar
     */
    public void waiting() {
        updateActivity("waiting");
    }
    
    /**
     * Actualiza el estado a pensar y 
     * hace el delay correspondiente
     * @throws InterruptedException 
     */
    public void thinking() throws InterruptedException {
        updateActivity("thinking");
        Thread.sleep(this.time);
    }

    /**
     * Actualiza el estado y realiza el
     * cambio con la vista
     * @param state Estado nuevo
     */
    void updateActivity(String state) {
        System.out.println("Philosopher " + id + " is " + state);
        this.status = state;
        this.image.setIcon(source.getImage(status));
    }

    /**
     * Actualiza el estado a comer y 
     * espera el tiempo correspondiente
     * @throws InterruptedException 
     */
    public void eating() throws InterruptedException {
        updateActivity("eating");
        Thread.sleep(time);
        waiting();
    }

    /**
     * Es el manejo del hilo.
     * Primero piensa,
     * enseguida trata de comer y cuando acabe 
     * espera hasta que le toque pensar de nuevo
     * Se repite infinitamente
     */
    @Override
    public void run() {
        while (true) {
            try {
                thinking();
                tryEat();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Primero, verifica que los tenedores
     * estén disponibles, si es su primera iteración va a esperar 
     * primero.
     * Enseguida, se verifica si el tenedor izquierdo está siendo usado, 
     * si no lo está, verifica si el derecho está siendo usado, si no 
     * lo está, empieza a comer,
     * y avisa a los tenedores que están siendo usados, al acabar
     * libera ambos tenedores y los suelta disponibles para la
     * siguiente operacion.
     */
    public void tryEat() {
        boolean first = true;
        while (true) {
            while (leftFork.isUsing().get() || rightFork.isUsing().get()) {
                if (first) {
                    waiting();
                    first = false;
                }
            }
            if (leftFork.tryHold()) {
                try {
                    if (rightFork.tryHold()) {
                        try {
                            this.leftFork.setImage("using");
                            this.rightFork.setImage("using");
                            eating();
                            this.leftFork.isUsing().getAndSet(true);
                            this.rightFork.isUsing().getAndSet(true);
                            return;
                        } catch (InterruptedException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        } finally {
                            this.leftFork.setImage("free");
                            this.rightFork.setImage("free");
                            this.leftFork.isUsing().getAndSet(false);
                            this.rightFork.isUsing().getAndSet(false);
                            rightFork.free();
                        }
                    }
                } finally {
                    leftFork.free();
                }
            }
        }
    }
}
