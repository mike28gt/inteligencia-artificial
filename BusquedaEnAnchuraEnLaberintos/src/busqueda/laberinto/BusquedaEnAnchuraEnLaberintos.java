/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busqueda.laberinto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.DebugGraphics;
import javax.swing.JPanel;

/**
 *
 * @author miguelcatalan
 */
public class BusquedaEnAnchuraEnLaberintos extends javax.swing.JFrame {

    JPanel jPanel1 = new JPanel();
    MotorDeBusquedaEnAnchura motorDeBusqueda = null;
    
    public BusquedaEnAnchuraEnLaberintos() {
        try {
            jbInit();
        } catch (Exception e) {
            System.out.println("Error al inicializar GUI: " + e);
        }
        motorDeBusqueda = new MotorDeBusquedaEnAnchura(10, 10);
        repaint();
    }
    
    @Override
    public void paint(Graphics g_unused) {
        if (motorDeBusqueda == null) {
            return;
        }
        
        Laberinto laberinto = motorDeBusqueda.getLaberinto();
        int ancho = laberinto.getAncho();
        int alto = laberinto.getAlto();
        System.out.println("Tamaño del laberinto: " + ancho + " por " + alto);
        
        Graphics g = jPanel1.getGraphics();
        BufferedImage image = new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = image.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, 320, 320);
        g2.setColor(Color.black);
        laberinto.setValor(0, 0, Laberinto.VALOR_UBICACION_INICIO);
        
        for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++) {
                short val = laberinto.getValor(x,y);
                
                if ( val == Laberinto.OBSTACULO) {
                    g2.setColor(Color.lightGray);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Laberinto.VALOR_UBICACION_INICIO) {
                    g2.setColor(Color.blue);
                    g2.drawString("S", 16 + x * 29, 19 + y * 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Laberinto.VALOR_UBICACION_OBJETIVO) {
                    g2.setColor(Color.red);
                    g2.drawString("G", 16 + x * 29, 19 + y * 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else {
                	g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                }
            }
        }
        
        //Dibuja el camino en color negro
        g2.setColor(Color.black);
        Ubicacion [] path = motorDeBusqueda.getCamino();
        
        for (int i=1; i< (path.length-1); i++) {
          int x = path[i].x;
          int y = path[i].y;
          short val = laberinto.getValor(x,y);
          g2.drawString("" + (path.length - i), 16 + x * 29, 19 + y * 29);
        }
        g.drawImage(image, 30, 40, 320, 320, null);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new BusquedaEnAnchuraEnLaberintos();
    }
    
    private void jbInit() throws Exception {
        this.setContentPane(jPanel1);
        this.setCursor(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Búsqueda en Anchura en Laberintos");
        this.getContentPane().setLayout(null);
        jPanel1.setBackground(Color.white);
        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        jPanel1.setDoubleBuffered(false);
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);
        this.setSize(370, 420);
        this.setVisible(true);
    }
}
