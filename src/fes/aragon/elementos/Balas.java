package fes.aragon.elementos;

import java.awt.Rectangle;

/**
 *
 * @author Alexander
 */
public class Balas {
    
    private Rectangle bala;
    private boolean pintar = true;

    public Balas(Rectangle bala) {
        this.bala = bala;
    }

    public Rectangle getBala() {
        return bala;
    }

    public void setBala(Rectangle bala) {
        this.bala = bala;
    }

}
