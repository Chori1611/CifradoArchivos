/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandarcifrado;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author gabriel
 */
public class GeneradorDeClaves {
    private static SecretKey clave;
    
    public static void main(String args[]){
        generaClave();
        almacenaClave();
        
    }
    
    public static void generaClave(){
        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            clave=kg.generateKey();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public static void almacenaClave(){
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("clave.secreta"));
            out.writeObject(clave);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(PantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
