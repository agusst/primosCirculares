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
public class PrimosCirculares {

    private static final List<Integer> primos = new ArrayList<>();
    private static final List<Integer> primosCirculares = new ArrayList<>();
    private static final List<Integer> preprocesados = new ArrayList<>();
    private static int cota;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        cota = 200;
        listarPrimos();
        for(int i = 0; i < preprocesados.size(); i++){
            orbita(preprocesados.get(i));
        }
        System.out.println(Arrays.asList(primosCirculares));
        
    }
    
    public static void listarPrimos() {
        
        //Creo una lista para almacenar los números compuestos
        List<Integer> compuestos = new ArrayList<>();

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
        
        System.out.println("Numeros primos: " + Arrays.asList(primos));
        
        preprocesadoPrimos(primos);
        
    }
    
    public static void preprocesadoPrimos(List<Integer> primos){
        
        //Creo una listo con los dígitos que por propiedad no puede tener un 
        //nro primo circular
        List<Integer> excluidos = Arrays.asList(0, 2, 4, 5, 6, 8);
        
        //Creo lista con los primos que deberán ser comprobados si son o no 
        //circulares
//        List<Integer> preprocesados = new ArrayList<>();
        
        //Recorro la lista de nros primos
        for(int primo: primos){
        
            //Creo una lista para almacenar los dìgitos del nro
            LinkedList<Integer> stack = new LinkedList<>();
            
            //Creo un entero donde almacenar temporalmente los dígitos
            int nro = primo;
            
            //Separo los dígitos del nro
            while(nro > 0){

                //Coloco el último dígito en la pila
                stack.push(nro % 10);

                //Quito el último dígito del nro
                nro = nro / 10;

            }
            
            int digitosIguales = 0;
            
            //Compruebo si los dígitos del primo son todos iguales
            for(int i = 0; i < stack.size(); i++){
                
                if(Objects.equals(stack.getFirst(), stack.get(i))){
                    
                    digitosIguales++;
                    
                }
                
            }
            
            //Si son iguales entonces podemos garantizar que el primo es circular
            if(digitosIguales != stack.size()){
                
                while (!stack.isEmpty()) {
                    
                    //Verifico que ningún dígito del primo coincida con alguno 
                    //de la lista de excluidos ya que si contiene uno de estos  
                    //dígitos, por propiedad el nro primo no puede ser circular
                    if(excluidos.contains(stack.pop())){
                        
                        //Si un dígito coincide, borro la pila y dejo de comprobar
                        stack.clear();
                        
                        break;
                        
                    //Si la pila esta vacía y el último dígito no se corresponde
                    //con ninguno de los dígitos de la lista de excluidos, añado
                    //el primo a la lista de preprocesados (por comprobar)
                    }else if (stack.isEmpty()){
                        
                        preprocesados.add(primo);
                        
                    }
                }
                
            } else {
                
                //Añado el primo a la lista de circulares
                primosCirculares.add(primo);
            
            }
            
        }
            
        System.out.println("Lista para comprobar: " + Arrays.asList(preprocesados));
        
    }
    
    public static void orbita(int primo){
        
        //Convierto el nro primo en un String
        String nroOriginal = Integer.toString(primo);
        
        //Determino la cantidad de dígitos del nro
        int cantidad = nroOriginal.length();
        
        //Creo un arreglo para almacenar cada uno de los dígitos por separado
        int[] digitos = new int[cantidad];
        
        //Separo los dígitos del nro primo
        for(int i = cantidad-1; primo > 0; i--) {
            
            digitos[i] = primo % 10;
            
            primo = primo / 10;
            
        }
                
        //Compruebo si las combinaciones del primo son también nros primos.
        //La última combinación es la original, ya que sabemos que es primo
        //la omitimos porque no es necesario comprobarlo
        for(int i = 0; i < cantidad-1; i++){
            
            //Creo un string para contener el nro rotado
            StringBuilder nroRotado = new StringBuilder();
            
            int primero = digitos[0];

            int j;
            
            //Roto la posición de los dígitos del nro en el arreglo y construyo
            //el nuevo nro, utilizando un string
            for(j = 0; j < cantidad-1; j++){

                digitos[j] = digitos[j+1];
                
                nroRotado.append(digitos[j]);
                
            }
            
            digitos[j] = primero;
            
            nroRotado.append(primero);
            
            //Compruebo si no es primo, dejo de iterar en la órbita del nro, 
            //ya que si una combinación no es prima, el primo no es circular
            if(!esPrimo(Integer.parseInt(nroRotado.toString()))){
                
                break;
            
            //Si es la última combinación y el nro es primo, entonces agrego
            //el primo original a la lista de primos circulares
            }else if(i == cantidad-2){
                
                primosCirculares.add(Integer.parseInt(nroOriginal));
            
            }                   
            
//                if((!(primos.contains(Integer.parseInt(intt.toString())))) && Integer.parseInt(intt.toString()) < cota){
//
//                    break;
//
//                }else if ((Integer.parseInt(intt.toString()) > cota && esPrimo(Integer.parseInt(intt.toString())) && i == cantidad-2)){
//                    primosCirculares.add(Integer.parseInt(numero));
//                    break;
//                    
//                }else if(i == cantidad-2){
//                        primosCirculares.add(Integer.parseInt(numero));
//                }
            
        }
            
    }
    
    public static boolean esPrimo(int nro){
        
        //Compruebo si el nro es 2 o 3, si es así, es primo
        if (nro == 2 || nro == 3) return true;
        
        //Compruebo si el nro es 1 o es divisible por 2 o 3, si es así, no es primo
        if (nro == 1 || (nro%2) == 0 || (nro%3) == 0) return false;
        
        //Establezco el límite para i de forma que el denominador no sea mayor 
        //al numerador (nro a evaluar)
        int lim = (int) (Math.sqrt(nro)+1)/6;
        
        //Por propiedad a partir del nro 3 todo nro primo (p) cumple que 
        //p = 6*n +- 1 para todo n perteneciente a los naturales, con lo cual
        //si el resto de p/(6*n + 1) o p/(6*n-1) es 0 el nro no es primo
        for(int i = 1; i <= lim; i++){
            
            if((nro%(6*i-1) == 0) || (nro%(6*i+1) == 0)) return false;
        
        }

        return true;
        
    }
    
}
