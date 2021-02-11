package clienteservidor;
import java.net.*;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.io.*;
import java.util.ArrayList;

public class manejadorHilosServ implements Runnable{
    private Socket s1;
    private int numCliente;
    boolean banderaOtro = true;
    
    public manejadorHilosServ(Socket i, int c){
        s1 = i;
        numCliente = c;
    }
    public void run() {
    	try {
    		 OutputStream s1out = s1.getOutputStream();
 	        DataOutputStream dos = new DataOutputStream (s1out);

 	        InputStream s1In = s1.getInputStream();
 	        DataInputStream dis = new DataInputStream(s1In);
 	          
 	       dos.writeUTF("Conexión con el servidor establecida");
 		    Scanner sc = new Scanner(System.in);
 	        
 	        while(banderaOtro == true) 
 	        {
 		        dos.writeUTF("\nIntroduzca un número entero para el tamaño de la matriz");
 			    int tamanioMat = (dis.readInt());
 			    try 
 			    {
 		        	if(tamanioMat != (int)tamanioMat)
 			        {
 		        		throw new DataFormatException();		
 			        }else
 			        {
 			        	System.out.println("Numero recibido: " + tamanioMat);
 				        //Creando Matriz
 				        int matriz[][] = new int[tamanioMat][tamanioMat];
 				        ArrayList lista = new ArrayList();
 				        
 				        for (int i = 0; i < matriz.length; i++) 
 				        { 
 				            for (int j = 0; j < matriz.length; j++) 
 				            {
 				                int elemento = (int) (Math.random() * tamanioMat + 1);  
 				                matriz[i][j] = elemento;
 				                lista.add(elemento);
 				            } 
 				        }
 				        
 				        System.out.println("Matriz creada: ");
 				        //Impre matriz
 				        for (int i = 0; i < matriz.length; i++) 
 				        {
 				            for (int j = 0; j < matriz.length; j++) 
 				            { 
 				                System.out.print(matriz[i][j] + " ");
 				            }
 				            System.out.println(" ");
 				        }
 				        
 				        ObjectOutputStream op = new ObjectOutputStream(s1out);
 				        op.writeObject(lista);
 				        
 				        
 				        
 					    dos.writeUTF("Desea crear otra matriz? SI/NO ");
 					    String resp = dis.readUTF();
 					    System.out.println(resp);
 					    
 				        if(!(resp.equals("SI"))) 
 				        {
 				        	banderaOtro = false;
 					        dos.writeUTF("Sesión Finalizada. Hasta luego!");
 				        }
 			        }
 		        }catch(DataFormatException exception) 
 			    {
 			        dos.writeUTF("El dato proporcionado no es de tipo entero.\n Introduzca otro dato");
 		        }
 	        }
 	        sc.close();
		    
	        //Cierra la conexión, pero no el socket del servidor
		    dis.close();
		    s1In.close();
		    dos.close();
		    s1out.close();         
		    s1.close();
            System.out.println("Cliente " + numCliente + " desconectado.");

 		   
    	}catch (IOException e) {
            e.printStackTrace();
    	}
    }
}
