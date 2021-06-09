package es.curso;

import java.util.function.*;

public class Funcional{
    
    public static void main(String[] args){
        
        System.out.println("HOLA");
        /****************/
        Consumer<String> variable=System.out::println;
        variable.accept("HOLA AMIGO !");
        
        personaliza(variable);
        
        // Función Lambda
            // Funciones anónimas
        
        Function<String,String> mifuncion=(value) -> valor.toUpperCase(); 
    }
    public static void personaliza(Consumer<String> funcionSuministrada){
        funcionSuministrada.accept("IVAN");
    }
    
}

/*

Function: Una función que toman argumento y devuelven algo
Consumer: Una función que toma argumentos pero no devuelve nada
Supplier: Una función que no toma arguments pero devuelve un valor


*/