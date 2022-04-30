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
        
        bucleExterior:
        while(cola.estaVacia() == false) {
            
            Ubicacion siguienteUbicacion = cola.front();
            
            if (siguienteUbicacion == null) {
                break;
            }
            
            Ubicacion[] estadosSiguientes = obtenerMovimientosPosibles(siguienteUbicacion);
            
            for (int i=0; i<4; i++) {
                
                if (estadosSiguientes[i] == null) {
                    break;
                }
                
                int coordenadaX = estadosSiguientes[i].x;
                int coordenadaY = estadosSiguientes[i].y;
                
                if (matrizEstadosVisitados[coordenadaX][coordenadaY] == false) {
                    matrizEstadosVisitados[coordenadaX][coordenadaY] = true;
                    matrizPredecesores[coordenadaX][coordenadaY] = siguienteUbicacion;
                    cola.enqueue(estadosSiguientes[i]);
                    
                    if (igual(estadosSiguientes[i], ubicacionObjetivo)) {
                        exito = true;
                        break bucleExterior;
                    }
                }
            }
            cola.dequeue();
        }

        
        String matriz = "";
        for (int i=0; i<ancho; i++) {
            for (int j=0; j<alto; j++) {
                matriz += matrizEstadosVisitados[i][j] + "\t";
            }
            matriz += "\n";
        }
        System.out.println("--");
        System.out.println("Matriz Estados Visitados");
        System.out.println(matriz);
        
        matriz = "";
        for (int i=0; i<ancho; i++) {
            for (int j=0; j<alto; j++) {
                if (matrizPredecesores[i][j] != null)
                    matriz += "(" + matrizPredecesores[i][j].x + "," + matrizPredecesores[i][j].y + ")" + "\t";
                else
                    matriz += "(-,-)\t";
            }
            matriz += "\n";
        }
        System.out.println("--");
        System.out.println("Matriz de Predecesores");
        System.out.println(matriz);
       
    }
}
