/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primoscirculares;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Agustin
 */
public class Hilo implements Runnable{
    
    private final Thread hilo;
    private Primos grupo;
    private List<Integer> bloque;

    public Hilo(String name, Primos grupo, List<Integer> bloque){
        
        hilo = new Thread(this, name);
        
        this.grupo = grupo;
        
        this.bloque = bloque;
    
        hilo.start();
        
    }
    
    @Override
    public void run() {
//        System.out.println("Staring... " + hilo.getName());
        grupo.preprocesadoPrimos(bloque);
    }
    
           
}
