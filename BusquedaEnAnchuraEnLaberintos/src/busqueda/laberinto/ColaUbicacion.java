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
public class ColaUbicacion {
    private Ubicacion[] cola;
    private int ultimo, primero, longitud;
    
    public ColaUbicacion(int longitudCola) {
        this.cola = new Ubicacion[longitudCola];
        this.ultimo = this.primero = 0;
        this.longitud = longitudCola;
    }
    
    public ColaUbicacion() {
        this(4000);
    }
    
    public void enqueue(Ubicacion nodo) {
        cola[ultimo] = nodo;
        
        if (ultimo >= (longitud - 1)) {
            ultimo = 0;
        } else {
            ultimo++;
        }
    }
    
    public Ubicacion dequeue() {
        Ubicacion retorno = cola[primero];
        
        if (primero >= (longitud - 1)) {
            primero = 0;
        } else {
            primero++;
        }
        
        return retorno;
    }
    
    public boolean estaVacia() {
        return primero == (ultimo + 1);
    }
    
    public Ubicacion front() {
        return cola[primero];
    }
}
