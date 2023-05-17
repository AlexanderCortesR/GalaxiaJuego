package fes.aragon.elementos;

import java.awt.Rectangle;

public class AsteroideAux {
    
    private Rectangle rec;
    private boolean direccion;    
    
    public AsteroideAux(Rectangle asteroide, boolean direccion) {
        this.rec = asteroide;
        this.direccion = direccion;        
    }

    public Rectangle getRec() {
        return rec;
    }

    public void setRec(Rectangle asteroide) {
        this.rec = asteroide;
    }

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }        
}
