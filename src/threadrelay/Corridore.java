/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author bianconi.yurinabil
 */
public class Corridore extends Thread {
    private JProgressBar barra;
    privae JLabel stato;
    private int velocita;
    private boolean pausa = false;
    private boolean stop = false;
    private boolean giocoAttivo;
    
    private Corridore prossimo;

    public Corridore(JProgressBar barra, privae JLabel, int velocita) {
        this.barra = barra;
        this.JLabel = JLabel;
        this.velocita = velocita;
    }
    
}
