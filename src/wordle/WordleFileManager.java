package wordle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @file WordleFileManager.java
 * @brief Clase para gestionar la lectura y escritura de archivos en Wordle.
 * @author laura
 * @version 1.0
 */
public class WordleFileManager {
  /**
   * @brief Lee palabras desde un archivo y las devuelve en un array.
   * @param archivo Ruta del archivo de palabras.
   * @return Array de palabras cargadas.
  */  
  public static String[] obtenerPalabras(String archivo) {
   
    String[] fileWords = new String[6];

    File listaPalabras = null;
    FileReader fr = null;
    BufferedReader br = null;

    try {
      listaPalabras = new File(archivo);
      fr = new FileReader(listaPalabras);
      br = new BufferedReader(fr);

      //Lectura del fichero
      String linea;
      int i = 0;
      while ((linea = br.readLine()) != null) {
        fileWords[i] = linea;
        i++;
      }

    } catch (IOException ioe) {
      if (fr == null) {
        System.out.println("Error al leer el archivo");
      }
    } finally {
      try {
        fr.close();
        br.close();
      } catch (IOException io) {
        System.err.println("File closing error: " + io);
      }
    }
    return fileWords;
  }
  
  /**
   * @brief Guarda una partida en un archivo.
   * @param archivoHis Ruta del archivo para guardar la partida.
   * @param palabra Palabra ingresada por usuario
   * @param secretWord Palabra secreta de la partida.
  */
  public static void mostrarHistorial(String archivoHis, String palabra, String secretWord){
      
    FileWriter fw = null; 
    PrintWriter pw = null; 

    try {      
      fw = new FileWriter(archivoHis, true);
      pw = new PrintWriter(fw);

      pw.println(palabra);  
      
    } catch (IOException ioe) {
      if (fw == null) {
        System.out.println("error");
      }
    } finally {
      try {
        pw.close();
        fw.close();
      } catch (IOException io) {
        System.err.println("Error: " + io);
      }
    }    
  }
}
