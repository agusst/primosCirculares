/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primoscirculares;

import java.util.List;

/**
 *
 * @author Agustin
 */
public class Hilo implements Runnable{
    
    private final Thread hilo;
    private final Primos grupo;
    private final List<Integer> bloque;

    //Constructor del hilo, crea el hilo, con un nombre y el bloque
    //de numeros primos sobre los cuales trabajar
    public Hilo(String name, Primos grupo, List<Integer> bloque){
        
        hilo = new Thread(this, name);
        
        this.grupo = grupo;
        
        this.bloque = bloque;
    
        hilo.start();
        
    }
    
    @Override
    public void run() {

        //Llamo al método de preprocesado para limpiar los primos que por 
        //propiedad no pueden ser circulares
        grupo.preprocesadoPrimos(bloque);
        
    }
    
           
}
