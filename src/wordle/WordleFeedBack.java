package wordle;

/**
 * @file WordleFeedBack.java
 * @brief Clase para generar retroalimentación de color en Wordle.
 * @author laura
 * @version 1.0
 */
public class WordleFeedBack {

  private static final int WORD_LENGTH = 5;
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_WHITE = "\u001B[37m";

// Método auxiliar para aplicar color
  /**
   * @brief Aplica color a una letra.
   * @param letter Letra a colorear.
   * @param color Código de color ANSI.
   * @return Letra con color aplicado.
   */
  private static String applyColor(String letter, String color) {
    return color + letter + ANSI_RESET;
  }

  /**
   * @brief Genera una cadena de retroalimentación de color para la palabra ingresada.
   * @param guess Palabra ingresada por el usuario.
   * @param secretWord Palabra secreta a adivinar.
   * @return Cadena de retroalimentación de color.
   */
  public static String feedBackString(String guess, String secretWord) {
    StringBuilder feedback = new StringBuilder();
    String color;

    for (int i = 0; i < guess.length(); i++) {

      //COMPARO PRIMERA POSICION CON CADENA. SI ENCUENTRA PRIMER CARACTER EN SEGUNDA PALABRA, PINTO VERDE
      if (guess.charAt(i) == secretWord.charAt(i)) {

        String caracter = String.valueOf(guess.charAt(i)); //CASTING DE CHAR A STRING        
        color = applyColor(caracter, ANSI_GREEN);
        feedback.append(color);

        //BUSCAR SI EL CARACTER DE SECRETWORD EXISTE EN PALABRA
      } else {

        int posicion = secretWord.indexOf(guess.charAt(i));
        if (posicion >= 0) { //SI POSICION >=0 EXISTE, PINTO EN AMARILLO,

          String caracter = String.valueOf(guess.charAt(i)); //CASTING DE CHAR A STRING          
          color = applyColor(caracter, ANSI_YELLOW);
          feedback.append(color);

        } else { //SI POSICION <0 NO EXISTE

          String caracter = String.valueOf(guess.charAt(i)); //CASTING DE CHAR A STRING          
          color = applyColor(caracter, ANSI_WHITE);
          feedback.append(color);
        }
      }
    }
    return feedback.toString();
  }
}
