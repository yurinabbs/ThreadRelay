/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author bianconi.yurinabil
 */
public class Corridore implements Runnable {
    private List<Observer> observers = new ArrayList<>();
    private int id;
    private int velocita;
    private boolean pausa = false;
    private boolean stop = false;
    
    private Corridore prossimo;

    public Corridore(int id, int velocita) {
        this.id = id;
        this.velocita = velocita;
    }


    
    public void setProssimo(Corridore prossimo) {
        this.prossimo = prossimo;
    }
    
    public void pausa() {
        pausa = true;
    }
    
    public void ferma() {
        stop = true;
    }
    
    public void riprendi(){
        pausa = false;
    }
    
    public void addObserver(Observer o) {
        observers.add(o);
    }
    
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    
    public void notifyObservers(int valore, String stato){
        for (Observer o : observers) {
            o.update(id, valore , stato);
        }
    }
    
    @Override
    public void run() {
        
        for (int i = 0; i <= 100; i++) {
            
            if (stop) {
                break;
            }
            
            while(pausa) {
                
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    
                }
            }
            
            notifyObservers(i, "CORRENDO");
            
            try {
                Thread.sleep(velocita);
            } catch (Exception e) {
                
            }
            
            //passaggio del testimone
            if (i == 90 && prossimo != null) {
                
                notifyObservers(i, "PASSAGGIO TESTIMONE");
                
                Thread t = new Thread(prossimo);
                t.start();
            }
        }
        
        notifyObservers(100, "FINITO");
    }
    
}

