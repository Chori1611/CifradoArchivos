/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandarcifrado;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.filechooser.FileSystemView;



/**
 *
 * @author chori
 */
public class HiloServidor  implements Runnable {
    private PantallaServidor servidor = null;
    private final ServerSocket servidorSocket;
    FileSystemView filesys = FileSystemView.getFileSystemView();
    //private File archivo;
    
    public HiloServidor(ServerSocket socket,PantallaServidor gui){
        this.servidor=gui;
        this.servidorSocket = socket;
    }
    @Override
    public void run() {
        try {
            Socket socket = servidorSocket.accept();
            ObjectInputStream os = new ObjectInputStream(socket.getInputStream());
            String desktopPath = filesys.getHomeDirectory() + "";
            FileOutputStream fos = new FileOutputStream(desktopPath+"/FicheroReceptorEncriptado.pdf");
            byte contenido[] = new byte[1024*4];
            int i = os.read(contenido);
            while(i!=-1){
                fos.write(contenido,0 ,i);
                i= os.read(contenido);
                
            }
            fos.close();
            os.close();
            this.servidor.cambiaText("Mensaje Recibido");
            this.servidor.activaBoton();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
    }
    
    
}
