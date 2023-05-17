package fes.aragon.elementos;

import fes.aragon.interfaz.Pintar;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Alexander
 */
public class Asteroide implements Pintar {

    private MediaTracker tracker;
    private Dimension xy;
    private Image imagen;
    private Random rd = new Random();

    private ArrayList<AsteroideAux> asteroides = new ArrayList<>();

    private int x = 0;
    private int y = 0;

    private int tamañoAsteroide = 20;
    private int numeroAsteroides = 10;

    public Asteroide(Component componente, Dimension xy) {
        this.tracker = new MediaTracker(componente);
        this.xy = xy;
        this.y = 0;       

        Toolkit herramienta = Toolkit.getDefaultToolkit();
        imagen = herramienta.getImage(getClass().getResource("/fes/aragon/recursos/Asteroide.png"));
        tracker.addImage(imagen, 0);

        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void pintar(Graphics2D g) {
        for (AsteroideAux ast : asteroides) {
            g.drawImage(imagen, ast.getRec().x, ast.getRec().y, tamañoAsteroide, tamañoAsteroide, null);
        }
        if(asteroides.size() < numeroAsteroides){
            this.nuevoAsteroide();
        }
        this.calculos();
    }

    @Override
    public void teclado(KeyEvent e) {
    }

    public void calculos() {

        for (AsteroideAux asteroide : asteroides) {
            if (asteroide.getRec().y <= xy.height) {
                asteroide.getRec().y += 8;
            }
            else{
                asteroide.getRec().y = 0;
                asteroide.getRec().x = rd.nextInt(xy.width);
            }
        }        
    }
    
    public void nuevoAsteroide(){
        
        this.x = (int) rd.nextInt(xy.width);
        Rectangle ast = new Rectangle(x, y, tamañoAsteroide, tamañoAsteroide);
        AsteroideAux asteroide = new AsteroideAux(ast, true);
        asteroides.add(asteroide);
        
    }

    public ArrayList<AsteroideAux> getAsteroides() {
        return asteroides;
    }
}