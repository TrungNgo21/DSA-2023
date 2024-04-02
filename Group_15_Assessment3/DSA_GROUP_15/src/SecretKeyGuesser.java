public class SecretKeyGuesser {

  // Sample implementation to get the order of the characters
  static int order(char c) {
    if (c == 'M') {
      return 0;
    } else if (c == 'O') {
      return 1;
    } else if (c == 'C') {
      return 2;
    } else if (c == 'H') {
      return 3;
    }git s
    return 4;
  }

  // Sample implementation to get the characters from the orders
  static char charOf(int order) {
    if (order == 0) {
      return 'M';
    } else if (order == 1) {
      return 'O';
    } else if (order == 2) {
      return 'C';
    } else if (order == 3) {
      return 'H';
    }
    return 'A';
  }

  // Group implementation to change the current character in the string

  public static String moveForward(String currentStr, int charIdx) {
    char[] curr = currentStr.toCharArray();

    if (order(curr[charIdx]) < 4) {
      curr[charIdx] = charOf(order(curr[charIdx]) + 1);
      return String.valueOf(curr);
    }
    curr[charIdx] = 'M';
    return String.valueOf(curr);
  }

  //Group implementation to revert the current character in the string
  public static String trackBack(String currentStr, int charIdx) {
    char[] curr = currentStr.toCharArray();
    if (order(curr[charIdx]) < 4) {
      curr[charIdx] = charOf(order(curr[charIdx]) - 1);
      return String.valueOf(curr);
    }
    curr[charIdx] = 'M';
    return String.valueOf(curr);
  }

  //Group implementation to check and process other steps (recursive)
  public static String solve(String guessedString, SecretKey secretKey, int idx, int score) {

    if (score < 0) {
      System.out.println("Your key is not valid");
      return guessedString;
    }

    if (score == 12) {
      System.out.println("The correct key is: " + guessedString);
      return guessedString;
    }

    System.out.println("Guessing... " + guessedString);
    guessedString = moveForward(guessedString, idx);
    int newScore = secretKey.guess(guessedString);

    if (newScore < score) {
      // Revert the current character in string
      guessedString = trackBack(guessedString, idx);
      // Call the method after changing
      return solve(guessedString, secretKey, idx + 1, score);
    }
    //Processing if number of matched is greater than the newestMatched
    if (newScore > score) {
      score = newScore;
      // Call the method after changing
      return solve(guessedString, secretKey, idx + 1, score);
    }
    return solve(guessedString, secretKey, idx, score);
  }

  public void start() {
    SecretKey key = new SecretKey();
    //Test case when the guessed key is not valid (key contained another letter)
    String guessedKey = "MMMMMMMMMMMM";
    int score = key.guess(guessedKey);
    solve(guessedKey, key, 0, score);
  }
}


