package fes.aragon.elementos;

import fes.aragon.interfaz.Pintar;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author Alexander
 */
public class FondoImagen implements Pintar {

    private Image imagen = null;
    private MediaTracker tracker;
    private Dimension xy;    
    
    public FondoImagen(Component componente, Dimension dim) {

        this.xy = dim;
        this.tracker = new MediaTracker(componente);
        Toolkit herramienta = Toolkit.getDefaultToolkit();
        imagen = herramienta.getImage(getClass().getResource("/fes/aragon/recursos/Galaxia4.gif"));
        tracker.addImage(imagen, 0);

        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void pintar(Graphics2D g) {
        g.drawImage(imagen, 0, 0, this.xy.width + 11, this.xy.height + 40, null);        
    }

    @Override
    public void teclado(KeyEvent e) {
    }

}