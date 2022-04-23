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
        realizarBusquedaEnCuadricula();
    }
    
    private void realizarBusquedaEnCuadricula() {
        int ancho = laberinto.getAncho();
        int alto = laberinto.getAlto();
        boolean banderasEstadoVisitado[][] = new boolean[ancho][alto];
        Ubicacion predecesor[][] = new Ubicacion[ancho][alto];
        ColaUbicacion cola = new ColaUbicacion();
        
        for (int i=0; i<ancho; i++) {
            for (int j=0; j<alto; j++) {
                banderasEstadoVisitado[i][j] = false;
                predecesor[i][j] = null;
            }
        }
        
        banderasEstadoVisitado[ubicacionInicio.x][ubicacionInicio.y] = true;
        cola.agregarALaCola(ubicacionInicio);
        boolean exito = false;
        
        bucleExterior:
        while(cola.estaVacio() == false) {
            Ubicacion inicio = cola.obtenerDelInicioDeLaCola();
            if (inicio == null) {
                break;
            }
            
            Ubicacion[] nodosConectados = obtenerMovimientosPosibles(inicio);
            
            for (int i=0; i<4; i++) {
                if (nodosConectados[i] == null) {
                    break;
                }
                
                int coordenadaX = nodosConectados[i].x;
                int coordenadaY = nodosConectados[i].y;
                
                if (banderasEstadoVisitado[coordenadaX][coordenadaY] == false) {
                    banderasEstadoVisitado[coordenadaX][coordenadaY] = true;
                    predecesor[coordenadaX][coordenadaY] = inicio;
                    cola.agregarALaCola(nodosConectados[i]);
                    
                    if (igual(nodosConectados[i], ubicacionObjetivo)) {
                        exito = true;
                        break bucleExterior;
                    }
                }
            }
            cola.extraerDeLaCola();
        }
        
        /**
         * Se calcula la ruta más corta utilizando el arreglo de predecesores
         */
        profundidadMaxima = 0;
        
        if (exito) {
            caminoDeBusqueda[profundidadMaxima++] = ubicacionObjetivo;
            
            for (int i=0; i<100; i++) {
                caminoDeBusqueda[profundidadMaxima] = 
                        predecesor[caminoDeBusqueda[profundidadMaxima-1].x][caminoDeBusqueda[profundidadMaxima-1].y];
                profundidadMaxima++;
                if (igual(caminoDeBusqueda[profundidadMaxima - 1], ubicacionInicio)) {
                    break;
                }
            }
        }
    }
}
