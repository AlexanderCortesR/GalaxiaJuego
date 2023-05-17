package fes.aragon.elementos;

import java.awt.Rectangle;

/**
 *
 * @author Alexander
 */
public class NaveEnemigoAux {

private Rectangle rec;
private boolean direccionV;
private boolean direccionH;

    public NaveEnemigoAux(Rectangle rec, boolean direccionV, boolean direccionH) {
        this.rec = rec;        
        this.direccionV = direccionV;
        this.direccionH = direccionH;
    }

    public Rectangle getRec() {
        return rec;
    }

    public void setRec(Rectangle rec) {
        this.rec = rec;
    }

    public boolean isDireccionV() {
        return direccionV;
    }

    public void setDireccionV(boolean direccionV) {
        this.direccionV = direccionV;
    }

    public boolean isDireccionH() {
        return direccionH;
    }

    public void setDireccionH(boolean direccionH) {
        this.direccionH = direccionH;
    }

    
}
