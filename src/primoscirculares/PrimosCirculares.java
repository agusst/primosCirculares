/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primoscirculares;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Agustin
 */
public class PrimosCirculares {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        isPrime(100);
    }
    
    public static void isPrime(int cota) {
        
        //Creo una lista para almacenar los números compuestos
        List<Integer> compuestos = new ArrayList<>();
        
        //Creo una lista para almacenar los números primos
        List<Integer> primos = new ArrayList<>();

        //La Criba de eratóstenes establece que al llegar a un número cuyo cuadrado
        //sea mayor que la cota superior el algoritmo se detiene.
        //Para ello calculo la raíz cuadrada de la cota superior, tomo la parte entera y le sumo 1.
        int limite = (int) Math.sqrt(cota) + 1;
        
        //Recorro en órden los números hasta el valor límite
        for (int i = 2; i <= limite; i++) {
            
            //Compruebo que el número no se encuentre en la lista de nros compuestos
            if (!compuestos.contains(i)) {
                
                //Determino el lìmiteMultiplo en función de la cota superior para detener el
                //cálculo de los múltiplos del nro. primo, cuando el múltiplo supere
                //la cota.
                int limiteMultiplo = (cota / i) + 1;
                
                //Comienzo desde el múltiplo cuadrado
                for (int j = i; j <= limiteMultiplo; j++){ 
                    
                    //Añado los múltiplos del nro. primo a la lista de compuestos
                    compuestos.add(i * j);
                
                }
                
            }
            
        }

        for (int index = 2; index < cota; index++) {
            
            if (!compuestos.contains(index)) primos.add(index);
        
        }
        
    }
}
