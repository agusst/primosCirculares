/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primoscirculares;

/**
 *
 * @author Agustin
 */
public class PrimosCirculares {

    private static final int cantidadHilos = 4;
    private static final int cota = 1000000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Imprimo un mensaje en pantalla indicando que el algoritmo esta trabajando
        System.out.println("Iniciando....");
        
        System.out.println("Aguarde, en unos segundos comenzarán a imprimirse en pantalla los nros. primos circulares inferiores a 1.000.000");
        
        Primos lista = new Primos(cantidadHilos, cota);
        
    }
    
    
    
   
    
    
    
}
