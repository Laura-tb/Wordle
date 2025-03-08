package wordle;

/**
 *
 * @author laura
 * @file Main.java
 * @brief Clase principal para iniciar juego Wordle
 * @version 1.0
 */
public class Main {

  /**
   * @param args
   * @brief Método principal que carga la lista de palabras e inicia el juego.
   */
  public static void main(String[] args) {

    //Carga lista de palabras de fichero externo
    String[] fileWords = WordleFileManager.obtenerPalabras("Files/listaPalabras.txt");

    //Inicializa clase WordleGame
    WordleGame game = new WordleGame(fileWords);
    game.start();
  }
}
