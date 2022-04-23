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
    
    public ColaUbicacion(int num) {
        this.cola = new Ubicacion[num];
        this.ultimo = this.primero = 0;
        this.longitud = num;
    }
    
    public ColaUbicacion() {
        this(400);
    }
    
    public void agregarALaCola(Ubicacion n) {
        cola[ultimo] = n;
        if (ultimo >= (longitud - 1)) {
            ultimo = 0;
        } else {
            ultimo++;
        }
    }
    
    public Ubicacion extraerDeLaCola() {
        Ubicacion ret = cola[primero];
        
        if (primero >= (longitud - 1)) {
            primero = 0;
        } else {
            primero++;
        }
        return ret;
    }
    
    public boolean estaVacio() {
        return primero == (ultimo + 1);
    }
    
    public Ubicacion obtenerDelInicioDeLaCola() {
        return cola[primero];
    }
}
