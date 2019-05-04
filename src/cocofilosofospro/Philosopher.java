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
public class Philosopher implements Runnable {

    public JLabel image;
    public String status;
    public int time;
    public Fork leftFork, rightFork;
    public int id;
    public boolean full = false;

    public void setForks(Fork left, Fork right) {
        this.leftFork = left;
        this.rightFork = right;
    }

    public Philosopher(JLabel reference, int time, int id) {
        this.id = id;
        this.time = time;
        this.image = reference;
        this.status = "thinking";
        this.image.setIcon(new Images(status, 50, 50).getImage());
    }

    public void getStatus() {
        updateActivity(status);
    }

    public void waiting() {
        updateActivity("waiting");
    }

    public void thinking() throws InterruptedException {
        updateActivity("thinking");
        Thread.sleep(this.time);
    }

    void updateActivity(String state) {
        System.out.println("Philosopher " + id + " is " + state);
        this.status = state;
        this.image.setIcon(new Images(status, 50, 50).getImage());
    }

    public void eating() throws InterruptedException {
        updateActivity("eating");
        Thread.sleep(time);
        this.full = true;
        waiting();
    }

    @Override
    public void run() {
        while (true) {
            try {
                thinking();
                BackEnd.coach.release();
                tryEat();
                BackEnd.coach.release();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void tryEat() {
        boolean first = true;
        while (true) {
            while (leftFork.isUsing().get() || rightFork.isUsing().get()) {
                if (first) {
                    waiting();
                    BackEnd.coach.release();
                    first = false;
                }
            }
            if (leftFork.tryHold()) {
                try {
                    if (rightFork.tryHold()) {
                        try {
                            BackEnd.coach.release();
                            eating();
                            this.leftFork.isUsing().getAndSet(true);
                            this.rightFork.isUsing().getAndSet(true);
                            return;
                        } catch (InterruptedException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        } finally {
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
