package es.curso;

import java.io.*;

public class Programa {
    
    public static void main( String[] args ) throws Exception{
        MiProducer mp=MiProducer.getProducer();
        mp.send(args[0],"1","INFO - Soy el mensaje 1");
        mp.send(args[0],"2","INFO - Soy el mensaje 2");
        mp.send(args[0],"3","ERROR - Soy el mensaje 3");
        mp.send(args[0],"4","WARNING - Soy el mensaje 4");
        
        System.out.println("PULSA ENTER PARA SALIR");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        
        mp.dispose();
    }
    
}
