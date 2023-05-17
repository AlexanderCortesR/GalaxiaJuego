package fes.aragon.hilos;

import fes.aragon.elementos.ColisionAJ;
import fes.aragon.elementos.ColisionBN;
import fes.aragon.interfaz.Pintar;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class Hilo_Panel extends JPanel implements Runnable, KeyListener {

    ArrayList<Pintar> componente = new ArrayList<Pintar>();
    private int fps = 15;
    private boolean pausa = true;
    private Dimension xy = null;
    private ColisionBN bn;
    private ColisionAJ aj;    

    public Hilo_Panel() {
        this.addKeyListener(this);

    }

    public ArrayList<Pintar> getComponente() {
        return componente;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gg = (Graphics2D) g;
        for (Pintar pintar : componente) {
            pintar.pintar(gg);
        }
        bn.colisiones();
        aj.colisiones();
    }

    public void setXy(Dimension xy) {
        this.xy = xy;
    }

    @Override
    public void run() {
        long inicio;
        long transcurrido;
        long espera;
        while (pausa) {
            try {
                inicio = System.nanoTime();
                repaint();
                transcurrido = System.nanoTime();
                espera = 1000 / fps - (transcurrido - inicio) / 1000000;
                espera = (espera < 0) ? 0 : espera;
                Thread.sleep(espera);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo_Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        for (Pintar teclado : componente) {
            teclado.teclado(e);
            int codigo = e.getKeyCode();

            if (codigo == KeyEvent.VK_P) {
                pausa = false;
            }
            if (codigo == KeyEvent.VK_X) {
                if (!pausa) {
                    pausa = true;
                    Thread hilo = new Thread(this);
                    hilo.start();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setBn(ColisionBN bc) {
        this.bn = bc;
    }

    public void setAj(ColisionAJ aj) {
        this.aj = aj;
    }
}
