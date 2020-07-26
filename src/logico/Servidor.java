/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logico;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaddiel
 */
public class Servidor extends Observable implements Runnable {

    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {

        ServerSocket servidor = null;
        Socket socket = null;
        DataInputStream entrada;

        try {
            servidor = new ServerSocket(puerto);

            while (true) {

                socket = servidor.accept();

                entrada = new DataInputStream(socket.getInputStream());
                String mensaje = entrada.readUTF();

                setChanged();
                notifyObservers(mensaje);
                clearChanged();

                socket.close();

            }

        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
