package wordle;

import java.util.Random;
import java.util.Scanner;

/**
 * @file WordleGame.java
 * @brief Clase que gestiona la lógica del juego Wordle.
 * @author laura
 * @version 1.0
 */
public class WordleGame {

  Scanner scanner = new Scanner(System.in);
  String palabra;
  boolean encontrado = false;

  //----Atributos de la clase WordleGame
  private final int MAX_TRIES = 6;
  private final int WORD_LENGTH = 5;
  private String[] fileWords;
  private String secretWord;
  private int remainingAttempts;
  private String[] triesHistory; 


  //----Métodos de la clase WordleGame
  //1. Constructor WordleGame(String[] fileWords) 
  /**
   * @brief Constructor de la clase WordleGame.
   * @param fileWords Array de palabras cargadas desde un archivo.
   */
  public WordleGame(String[] fileWords) {
    this.fileWords = fileWords;
    this.secretWord = selectRandomWord(fileWords);
    this.remainingAttempts = MAX_TRIES;
    this.triesHistory = new String[MAX_TRIES];
  }

  //2. Método start(). 
  /**
   * @brief Inicia el ciclo principal del juego.
   */
  public void start() {

    System.out.println("Bienvenido a Wordle!");
    System.out.println(" ");
    System.out.println("Tienes " + MAX_TRIES + " intentos restantes.");

    WordleFileManager.mostrarHistorial("Files/triesHistory.txt", "", palabra, true);
    //Bucle for que permite al usuario realizar intentos hasta que adivine  palabra o se quede sin intentos.
    for (int i = 0; i < MAX_TRIES && encontrado == false; i++) {

      palabra = getUserInput(scanner);

      //Comprobar si palabra ingresada es la palabara secreta. Si es igual, se termina juego, indicando palabras intentadas y escribiendo en archivo.
      if (palabra.equalsIgnoreCase(secretWord)) {
        System.out.println("¡Felicidades! Has adivinado la palabra correcta: " + palabra);
        encontrado = true;
        
        triesHistory[i] = palabra;
        WordleFileManager.mostrarHistorial("Files/triesHistory.txt", palabra, secretWord, false);
        
        triesHistory[i] = WordleFeedBack.feedBackString(palabra, secretWord);
        showTriesHistory();
        
      //Si usuario falla se reduce nº intentos y se muestra el feedback con colores en las letras.
      } else {
        remainingAttempts--;
        System.out.println("");
        System.out.println("No es correcta. Tienes " + remainingAttempts + " intentos restantes.");
        
        triesHistory[i] = palabra;
        WordleFileManager.mostrarHistorial("Files/triesHistory.txt",palabra, secretWord, false);
        
        triesHistory[i] = WordleFeedBack.feedBackString(palabra, secretWord);
        showTriesHistory();
      }
    }
    //Si el nº intentos es <=0 se termina el juego.
    if (remainingAttempts <= 0) {
      System.out.println("Lo sentimos, tu partida ha terminado. La palabra secreta era: " + secretWord);
    }
  }

  /*3. Método showTriesHistory(). Muestra al jugador el historial de intentos*/
  /**
   * @brief Muestra el historial de intentos del jugador.
   */
  public void showTriesHistory() {    
    for (int i = 0; i < triesHistory.length; i++) {
      if (triesHistory[i] != null) {
        System.out.println(triesHistory[i]);
      }
    }
  }

  /*4. Método selectRandomWord(String[] words). Seleccionar una palabra secreta al azar del array proporcionado.*/
  /**
   * @brief Selecciona una palabra secreta al azar del array proporcionado.
   * @param words Array de palabras disponibles.
   * @return Palabra secreta seleccionada.
   */
  public String selectRandomWord(String[] words) {

    if (words == null || words.length == 0) {
      return null; 
    }

    Random random = new Random();
    int randomI = random.nextInt(words.length);
    return words[randomI];
  }

  /*5. Método getUserInput(Scanner scanner). Leer la palabra ingresada por el usuario, asegurarse de que tenga exactamente 5 letras y devolverla*/
  /**
   * @brief Lee la palabra ingresada por el usuario, valida la longitud y la devuelve.
   * @param scanner Objeto Scanner para capturar la entrada del usuario.
   * @return Palabra ingresada por el usuario.
   */
  public String getUserInput(Scanner scanner) {

    do {
      System.out.print("Introduce una palabra de 5 letras: ");
      palabra = scanner.nextLine().toUpperCase();

      if (!palabra.matches("[a-zA-Z]{5}")) {
        System.out.println("Error, la palabra debe ser de 5 letras y contener solo letras.");
        WordleFileManager.mostrarHistorial("Files/triesHistory.txt",palabra, secretWord, false);
      }
    } while (!palabra.matches("[a-zA-Z]{5}"));

    return palabra;
  }
}
