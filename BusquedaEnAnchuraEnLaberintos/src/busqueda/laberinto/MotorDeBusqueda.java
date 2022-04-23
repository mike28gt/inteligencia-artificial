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
    protected Ubicacion[] caminoDeBusqueda = null;
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
    
    protected void inicializarBusqueda() {
        if (caminoDeBusqueda == null) {
            caminoDeBusqueda = new Ubicacion[1000];
            for (int i=0; i<1000; i++) {
                caminoDeBusqueda[i] = new Ubicacion();
            }
        }
        
        longitudCamino = 0;
        ubicacionInicio = laberinto.ubicacionInicio;
        ubicacionActual = ubicacionInicio;
        ubicacionObjetivo = laberinto.ubicacionObjetivo;
        caminoDeBusqueda[longitudCamino++] = new Ubicacion();
    }
    
    protected boolean igual(Ubicacion u1, Ubicacion u2) {
        return u1.x == u2.x && u1.y == u2.y;
    }
    
    protected Ubicacion[] getCamino() {
        Ubicacion[] ret = new Ubicacion[profundidadMaxima];
        
        for (int i=0; i<profundidadMaxima; i++) {
            ret[i] = caminoDeBusqueda[i];
        }
        
        return ret;
    }
    
     protected Ubicacion[] obtenerMovimientosPosibles(Ubicacion ubicacion) {
         Ubicacion movimientosTemp[] = new Ubicacion[4];
         movimientosTemp[0] = movimientosTemp[1] = movimientosTemp[2] = movimientosTemp[3] = null;
         int x = ubicacion.x;
         int y = ubicacion.y;
         int num = 0;
         
         if (laberinto.getValor(x-1, y) == 0 || 
             laberinto.getValor(x-1, y) == laberinto.VALOR_UBICACION_OBJETIVO) {
             movimientosTemp[num++] = new Ubicacion(x-1, y);
         }
         
         if (laberinto.getValor(x+1, y) == 0 || 
             laberinto.getValor(x+1, y) == laberinto.VALOR_UBICACION_OBJETIVO) {
             movimientosTemp[num++] = new Ubicacion(x+1, y);
         }
         
         if (laberinto.getValor(x, y-1) == 0 || 
             laberinto.getValor(x, y-1) == laberinto.VALOR_UBICACION_OBJETIVO) {
             movimientosTemp[num++] = new Ubicacion(x, y-1);
         }
         
         if (laberinto.getValor(x, y+1) == 0 || 
             laberinto.getValor(x, y+1) == laberinto.VALOR_UBICACION_OBJETIVO) {
             movimientosTemp[num++] = new Ubicacion(x, y+1);
         }
         
         return movimientosTemp;
     }
}
