/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busqueda.laberinto;

/**
 *
 * @author miguelcatalan
 */
public class Laberinto {
    public static final short OBSTACULO = -1;
    public static final short UBICACION_INICIO = -2;
    public static final short UBICACION_OBJETIVO = -3;
    private int alto = 0;
    private int ancho = 0;
    public Ubicacion ubicacionInicio = new Ubicacion();
    public Ubicacion ubicacionObjetivo = new Ubicacion();
    private short[][] laberinto;
    
    public Laberinto(int alto, int ancho) {
        System.out.println("Nuevo laberinto de " + alto + " por " + ancho);
        this.alto = alto;
        this.ancho = ancho;
        laberinto = new short[alto+2][ancho+2];
        
        for (int i=0; i<ancho+2; i++) {
            for (int j=0; j<alto+2; j++) {
                laberinto[i][j] = 0;
            }
        }
        
        for (int i=0; i<alto+2; i++) {
            laberinto[0][i] = laberinto[ancho+1][i] = OBSTACULO;
        }
        
        for (int i=0; i<ancho+2; i++) {
            laberinto[i][0] = laberinto[i][alto+1] = OBSTACULO;
        }
        
        int max_obstaculos = (ancho * ancho) / 3;
        for (int i=0; i<max_obstaculos; i++) {
            int x = (int)(Math.random() * ancho);
            int y = (int)(Math.random() * alto);
            setValor(x, y, OBSTACULO);
        }
        
        ubicacionInicio.x = 0;
        ubicacionInicio.y = 0;
        setValor(0,0, UBICACION_INICIO);
        
        ubicacionObjetivo.x = ancho - 1;
        ubicacionObjetivo.y = alto - 1;
        setValor(ancho - 1, alto - 1, UBICACION_OBJETIVO);
    }
    
    synchronized public short getValor(int x, int y) {
        return laberinto[x+1][y+1];
    }
    
    synchronized public void setValor(int x, int y, short valor) {
        laberinto[x+1][y+1] = valor;
    }
    
    public int getAncho() {
        return ancho;
    }
    
    public int getAlto() {
        return alto;
    }
    
    public String toString() {
        String matriz = "";
        
        for (int i=0; i<ancho+2; i++) {
            for (int j=0; j<alto+2; j++) {
                matriz += laberinto[i][j] + "\t";
            }
            matriz += "\n";
        }
        
        return matriz;
    }
}
