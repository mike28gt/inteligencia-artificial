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
public class MotorDeBusquedaEnAnchura extends MotorDeBusqueda {
    
    public MotorDeBusquedaEnAnchura(int ancho, int alto) {
        super(ancho, alto);
        realizarBusquedaEnLaberinto();
    }
    
    private void realizarBusquedaEnLaberinto() {
        int ancho = laberinto.getAncho();
        int alto = laberinto.getAlto();
        boolean matrizEstadosVisitados[][] = new boolean[ancho][alto];
        Ubicacion matrizPredecesores[][] = new Ubicacion[ancho][alto];
        ColaUbicacion cola = new ColaUbicacion();
        boolean exito = false;
        
        for (int i=0; i<ancho; i++) {
            for (int j=0; j<alto; j++) {
                matrizEstadosVisitados[i][j] = false;
                matrizPredecesores[i][j] = null;
            }
        }
        
        matrizEstadosVisitados[ubicacionInicio.x][ubicacionInicio.y] = true;
        cola.enqueue(ubicacionInicio);
        
        
        
    }
}
