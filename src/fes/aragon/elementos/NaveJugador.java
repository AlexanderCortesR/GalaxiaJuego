package fes.aragon.elementos;

import fes.aragon.efectos.EfectosMusica;
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
public class NaveJugador implements Pintar {

    private MediaTracker tracker;
    private Dimension xy;
    private Image imagen = null;
    private Image imagenDos = null;

    private int numeroDeVidas = 3;
    private ArrayList<Balas> balas = new ArrayList<>();
    private ArrayList<NaveJugadorAux> naveJugador = new ArrayList<>();

    private int x = 0;
    private int y = 0;

    private int i = 0;
    private Random rd = new Random();

    private int tamañoNave = 40;

    public NaveJugador(Component componente, Dimension xy) {

        this.xy = xy;
        this.tracker = new MediaTracker(componente);
        this.x = (int) xy.getWidth() / 2;
        this.y = (int) xy.getHeight() - tamañoNave + 5;

        Toolkit herramienta = Toolkit.getDefaultToolkit();
        imagen = herramienta.getImage(getClass().getResource("/fes/aragon/recursos/Nave1.png"));
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

        for (NaveJugadorAux rectangle : naveJugador) {
            g.drawImage(imagen, rectangle.getRec().x, rectangle.getRec().y, tamañoNave, tamañoNave, null);
        }
        for (Balas bala : balas) {
            g.drawImage(imagenDos, bala.getBala().x, bala.getBala().y, 5, 5, null);
        }
        if (naveJugador.size() < 1 && numeroDeVidas > 0) {
            this.nuevaNave();
        }
        this.calculo();
        
    }

    @Override
    public void teclado(KeyEvent e) {

        for (NaveJugadorAux rectangle : naveJugador) {

            int codigo = e.getKeyCode();

            if (codigo == KeyEvent.VK_RIGHT) {
                if (rectangle.getRec().x <= (xy.getWidth() - tamañoNave - 5)) {
                    rectangle.getRec().x += 10;
                }
            }
            if (codigo == KeyEvent.VK_LEFT) {
                if (rectangle.getRec().x >= 5) {
                    rectangle.getRec().x -= 10;
                }
            }

            if (codigo == KeyEvent.VK_UP) {
                if (rectangle.getRec().y > ((this.xy.getHeight() / 5) * 4)) {
                    rectangle.getRec().y -= 10;
                }
            }

            if (codigo == KeyEvent.VK_DOWN) {
                if (rectangle.getRec().y < (xy.getHeight() - tamañoNave)) {
                    rectangle.getRec().y += 10;
                }
            }

            if (codigo == KeyEvent.VK_SPACE) {
                Rectangle bs = new Rectangle(rectangle.getRec().x + 18, rectangle.getRec().y, 20, 20);
                balas.add(new Balas(bs));
                Thread hilo = new Thread(new EfectosMusica("Disparo"));
                hilo.start();
            }
        }

    }

    private void calculo() {

        for (Balas bala : balas) {
            bala.getBala().y -= 10;
        }
        Iterator iter = balas.iterator();
        while (iter.hasNext()) {

            Balas objeto = (Balas) iter.next();
            if (objeto.getBala().y <= 0) {
                iter.remove();
            }
        }
    }

    public void nuevaNave() {
        Rectangle jug = new Rectangle(x, y, 20, 20);
        NaveJugadorAux nave = new NaveJugadorAux(jug);
        naveJugador.add(nave);
        numeroDeVidas -= 1;
    }

    public ArrayList<Balas> getBalas() {
        return balas;
    }

    public ArrayList<NaveJugadorAux> getNaveJugador() {
        return naveJugador;
    }

    public int getNumeroDeVidas() {
        return numeroDeVidas;
    }  
    
}