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
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Alexander
 */
public class NaveEnemigo implements Pintar {

    private MediaTracker tracker;
    private Dimension xy;
    private Image imagen = null;
    private Image imagenDos = null;
    private Random rd = new Random();

    private ArrayList<NaveEnemigoAux> naveEnemigo = new ArrayList<>();
    private ArrayList<Balas> balas = new ArrayList<>();

    private int x = 0;
    private int y = 0;

    private boolean disparar = false;

    private int tamañoNave = 40;
    private int numeroEnemigos = 8;

    public NaveEnemigo(Component componente, Dimension xy) {

        this.xy = xy;
        this.tracker = new MediaTracker(componente);

        do {
            this.x = (int) rd.nextInt(xy.width);
            this.y = (int) rd.nextInt(xy.height / 3);
            Rectangle enemigo = new Rectangle(x, y, 20, 20);
            NaveEnemigoAux nav = new NaveEnemigoAux(enemigo, true, true);
            naveEnemigo.add(nav);
        } while (naveEnemigo.size() < numeroEnemigos);

        Toolkit herramienta = Toolkit.getDefaultToolkit();
        imagen = herramienta.getImage(getClass().getResource("/fes/aragon/recursos/NaveE1.png"));
        tracker.addImage(imagen, 0);

        Toolkit herramientaDos = Toolkit.getDefaultToolkit();
        imagenDos = herramientaDos.getImage(getClass().getResource("/fes/aragon/recursos/Disparo.png"));
        tracker.addImage(imagenDos, 1);

        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void pintar(Graphics2D g) {

        for (NaveEnemigoAux nave : naveEnemigo) {
            g.drawImage(imagen, nave.getRec().x, nave.getRec().y, tamañoNave, tamañoNave, null);
        }

        for (Balas bala : balas) {
            g.drawImage(imagenDos, bala.getBala().x, bala.getBala().y, 5, 5, null);            
        }

        this.calculo();

    }

    @Override
    public void teclado(KeyEvent e) {
    }

    public void calculo() {

        for (NaveEnemigoAux nave : naveEnemigo) {

            if (nave.isDireccionH()) {
                nave.getRec().x += 5;
            }
            if (!nave.isDireccionH()) {
                nave.getRec().x -= 5;
            }
            if (nave.isDireccionV()) {
                nave.getRec().y += 10;
            }

            if (!nave.isDireccionV()) {
                nave.getRec().y -= 10;
            }

            if (nave.getRec().x >= xy.width - 55) {
                nave.setDireccionH(false);
            }
            if (nave.getRec().x <= 0) {
                nave.setDireccionH(true);
            }

            if (nave.getRec().y >= xy.height / 3) {
                nave.setDireccionV(false);
            }
            if (nave.getRec().y <= 0) {
                nave.setDireccionV(true);
            }

            do {                
                if (rd.nextInt(15) == 5) {
                    if (disparar) {
                        Rectangle bs = new Rectangle(nave.getRec().x + 18, nave.getRec().y, 20, 20);
                        balas.add(new Balas(bs));
                    }
                    disparar = rd.nextBoolean();
                }
            } while (disparar);

            for (Balas bala : balas) {
                bala.getBala().y += 5;
            }

            Iterator iter = balas.iterator();
            while (iter.hasNext()) {

                Balas objeto = (Balas) iter.next();
                if (objeto.getBala().y >= xy.getHeight()) {
                    iter.remove();
                    
                }
            }
        }
    }

    public ArrayList<NaveEnemigoAux> getNaveEnemigo() {
        return naveEnemigo;
    }

    public ArrayList<Balas> getBalas() {
        return balas;
    }
}