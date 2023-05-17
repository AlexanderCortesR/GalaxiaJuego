package fes.aragon.elementos;

import fes.aragon.efectos.EfectosMusica;
import fes.aragon.interfaz.Pintar;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alexander
 */
public class ColisionAJ implements Pintar{

    private NaveJugador naveJugador;

    private Asteroide asteroide;
    
    private ArrayList<Rectangle> choques = new ArrayList<>();
    private Image imagen = null;
    private MediaTracker tracker;

    public ColisionAJ(Component componente, NaveJugador naveJugador, Asteroide asteroide) {
        this.naveJugador = naveJugador;
        this.asteroide = asteroide;
        this.tracker = new MediaTracker(componente);

        Toolkit herramienta = Toolkit.getDefaultToolkit();
        imagen = herramienta.getImage(getClass().getResource("/fes/aragon/recursos/Explosion.gif"));
        tracker.addImage(imagen, 0);

    }

    public void colisiones() {
        
        if (asteroide.getAsteroides().size() > 0) {
            Iterator iter0 = asteroide.getAsteroides().iterator();

            while (iter0.hasNext()) {
                Rectangle rectanguloUno = ((AsteroideAux) iter0.next()).getRec();
                Iterator iter = naveJugador.getNaveJugador().iterator();

                while (iter.hasNext()) {
                    Rectangle rectanguloDos = ((NaveJugadorAux) iter.next()).getRec();
                    if (rectanguloDos.intersects(rectanguloUno)) {
                                                
                        this.choques.add(new Rectangle(rectanguloDos.intersection(rectanguloUno)));
                        iter.remove();
                        iter0.remove();
                        Thread hilo = new Thread(new EfectosMusica("Explosion"));
                        hilo.start();
                        break;
                        
                    }
                }
            }
        }

        if (naveJugador.getBalas().size() > 0) {
            Iterator iter0 = naveJugador.getBalas().iterator();

            while (iter0.hasNext()) {
                Rectangle rectanguloUno = ((Balas) iter0.next()).getBala();
                Iterator iter = asteroide.getAsteroides().iterator();

                while (iter.hasNext()) {
                    Rectangle rectanguloDos = ((AsteroideAux) iter.next()).getRec();
                    if (rectanguloDos.intersects(rectanguloUno)) {
                        
                        this.choques.add(new Rectangle(rectanguloDos.intersection(rectanguloUno)));
                        iter.remove();
                        iter0.remove();
                        Thread hilo = new Thread(new EfectosMusica("AsteroideChoque"));
                        hilo.start();
                        break;
                        
                    }
                }
            }
        }

    }

    @Override
    public void pintar(Graphics2D g) {
        this.colisiones();
        while(choques.size() > 0){
            g.drawImage(imagen, choques.get(0).x, choques.get(0).y, 40, 40, null);            
            this.choques.remove(0);            
        }
    }

    @Override
    public void teclado(KeyEvent e) {
        
    }
}