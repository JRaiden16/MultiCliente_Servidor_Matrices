package clienteservidor;

import java.net.*;
import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class simpleServer {
	class ServidorEcoConHilos {

	}

	public static void main(String args[]) throws IOException {

		try {
			int i = 1;
			ServerSocket s = new ServerSocket(8000);

			while (true) {
				Socket s1 = s.accept();
				System.out.println("Creando Cliente " + i + ".");
				Runnable r = new clienteservidor.manejadorHilosServ(s1, i);
				Thread t = new Thread(r);
				t.start();
				i++;
			}
		} catch (IOException ex) {
			Logger.getLogger(simpleServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}