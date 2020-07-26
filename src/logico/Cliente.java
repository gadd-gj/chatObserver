/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logico;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaddiel
 */
public class Cliente implements Runnable {

    private String mensaje;
    private int puerto;

    public Cliente(int puerto, String mensaje) {
        this.mensaje = mensaje;
        this.puerto = puerto;
    }

    @Override
    public void run() {

        final String ip = "127.0.0.1";

        DataOutputStream salida;

        try {

            Socket socket = new Socket(ip, puerto);

            salida = new DataOutputStream(socket.getOutputStream());

            salida.writeUTF(mensaje);

            socket.close();

        } catch (Exception e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
