/*
 * EncFileStorage.java
 *
 * Created on 27/09/2007, 11:55
 *
 */

package com.gmail.renatn.jZamok.data;

import com.gmail.renatn.jZamok.model.ZamokDataModel;
import java.io.*;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;
import com.sun.crypto.provider.SunJCE;

/**
 *
 * @author Renat Nasyrov aka k0sh
 */
public class EncFileStorage extends FileStorage {
    
    private final static byte[] SALT = {(byte)0xf5, (byte)0x33, (byte)0x01, (byte)0x2a,
                                        (byte)0xb2, (byte)0xcc, (byte)0xe4, (byte)0x7f};
    private char phrase[];
       
    public EncFileStorage(char phrase[]) {
        Security.addProvider(new SunJCE());        
        this.phrase = phrase;
    }     
    
    private SecretKey generateKey(char[] phrase) throws GeneralSecurityException {

        PBEKeySpec keySpec = new PBEKeySpec(phrase);      
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        
        return keyFactory.generateSecret(keySpec);
    }

    private Cipher createCipher(int mode, SecretKey key) throws GeneralSecurityException {
        
        PBEParameterSpec paramSpec = new PBEParameterSpec(SALT, 10);
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");        
        cipher.init(mode, key, paramSpec);

        return cipher;
    } 
    
    @Override
    public void loadFromFile(File file) throws IOException {
        
        try {

            SecretKey key = generateKey(phrase);
            Cipher cipher = createCipher(Cipher.DECRYPT_MODE, key);
            InputStream in = new CipherInputStream(new FileInputStream(file), cipher);
        
            super.load(in);
            

        } catch (GeneralSecurityException ex) {           
            ex.printStackTrace();
        }
                
    }   
    
    @Override
    public void saveToFile(ZamokDataModel model, File file) throws IOException {

        try {
            
            FileOutputStream fout = new FileOutputStream(file);
            SecretKey key = generateKey(phrase);

            Cipher cipher = createCipher(Cipher.ENCRYPT_MODE, key);            
            CipherOutputStream cout = new CipherOutputStream(fout, cipher);
        
            super.save(model, cout);           
            cout.close();           
            
        } catch (GeneralSecurityException ex) {
            ex.printStackTrace();
        }
        
    }          
    
    public char[] getPhrase() {
        return phrase.clone();
    }
     
}
