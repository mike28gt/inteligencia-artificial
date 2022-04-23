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
public abstract class MotorDeBusqueda {
    protected Laberinto laberinto;
    protected Ubicacion[] caminoBusqueda = null;
    protected int longitudCamino;
    protected int profundidadMaxima;
    protected Ubicacion ubicacionInicio;
    protected Ubicacion ubicacionObjetivo;
    protected Ubicacion ubicacionActual;
    protected boolean estaBuscando;
    
    public MotorDeBusqueda(int ancho, int alto) {
        laberinto = new Laberinto(ancho, alto);
        inicializarBusqueda();
    }
    
    public Laberinto getLaberinto() {
        return laberinto;
    }
    
    public void inicializarBusqueda() {
        if (caminoBusqueda == null) {
            caminoBusqueda = new Ubicacion[1000];
            for (int i = 0; i < 1000; i++) {
                caminoBusqueda[i] = new Ubicacion();
            }
        }
        
        longitudCamino = 0;
        ubicacionInicio = laberinto.ubicacionInicio;
        ubicacionObjetivo = laberinto.ubicacionObjetivo;
        ubicacionActual = laberinto.ubicacionInicio;
        
        caminoBusqueda[longitudCamino++] = new Ubicacion();
    }
    
    public boolean igual(Ubicacion u1, Ubicacion u2) {
        return u1.x == u2.x && u1.y == u2.y;
    }
    
    public Ubicacion[] getCamino() {
        Ubicacion[] retorno = new Ubicacion[profundidadMaxima];
        
        for (int i = 0; i<profundidadMaxima; i++) {
            retorno[i] = caminoBusqueda[i];
        }
        
        return retorno;
    }
    
    public Ubicacion[] obtenerMovimientosPosibles(Ubicacion ubicacion) {
        Ubicacion movimientosPosibles[] = new Ubicacion[4];
        movimientosPosibles[0] = movimientosPosibles[1] = movimientosPosibles[2] = movimientosPosibles[3] = null;
        int x = ubicacion.x;
        int y = ubicacion.y;
        int num = 0;
        
        if (laberinto.getValor(x-1, y) == 0 ||
            laberinto.getValor(x-1, y) == laberinto.UBICACION_OBJETIVO) {
            movimientosPosibles[num++] = new Ubicacion(x-1, y);
        }
        
        if (laberinto.getValor(x+1, y) == 0 ||
            laberinto.getValor(x+1, y) == laberinto.UBICACION_OBJETIVO) {
            movimientosPosibles[num++] = new Ubicacion(x+1, y);
        }
        
        if (laberinto.getValor(x, y-1) == 0 ||
            laberinto.getValor(x, y-1) == laberinto.UBICACION_OBJETIVO) {
            movimientosPosibles[num++] = new Ubicacion(x, y-1);
        } 
        
        if (laberinto.getValor(x, y+1) == 0 ||
            laberinto.getValor(x, y+1) == laberinto.UBICACION_OBJETIVO) {
            movimientosPosibles[num++] = new Ubicacion(x, y+1);
        }
        
        return movimientosPosibles;
    }
}
