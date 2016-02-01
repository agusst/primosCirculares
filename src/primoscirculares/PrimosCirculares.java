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
        listarPrimos(200);
    }
    
    public static void listarPrimos(int cota) {
        
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
        
        System.out.println(Arrays.asList(primos));
        
        preprocesadoPrimos(primos);
        
    }
    
    public static void preprocesadoPrimos(List<Integer> primos){
        
        //Creo una listo con los dígitos que por propiedad no puede tener un 
        //nro primo circular
        List<Integer> excluidos = Arrays.asList(0, 2, 4, 5, 6, 8);
        
        //Creo lista con los primos que deberán ser comprobados si son o no 
        //circulares
        List<Integer> preprocesados = new ArrayList<>();
        
        //Recorro la lista de nros primos
        for(int primo: primos){
        
            //Creo una lista para almacenar los dìgitos del nro
            LinkedList<Integer> stack = new LinkedList<>();
            
            //Creo un entero donde almacenar temporalmente los dígitos
            int nro = primo;
            
            //Separo los dígitos del nro
            while(nro > 0){

                //Coloco el último dìgito en la pila
                stack.push(nro % 10);

                //Quito el último dígito del nro
                nro = nro / 10;

            }
            
            //Compruebo si el primo tiene más de un dígito
            if(stack.size() > 1){
                
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
                    //el primo a la lista de preprocesados
                    }else if (stack.isEmpty()){
                        
                        preprocesados.add(primo);
                        
                    }
                }
                
            } else {
                
                //Añado el primo a la lista de preprocesados
                preprocesados.add(primo);
            
            }
            
        }
            
        System.out.println(Arrays.asList(preprocesados));
        
    }
}
