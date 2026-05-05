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
    
    public void removeObserver(int valore, String stato){
        for (Observer o : observers) {
            o.update(id, valore , stato);
        }
    }
    
    public void riprendi() {
        pausa = false;
    }
    }

