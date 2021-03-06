package utils;

public final class Encryption {

  public static String encryptDecryptXOR(String rawString) {

    //LAG EN BEDRE NØKKEL OG GEM DEN ET BEDRE STED
    // If encryption is enabled in Config.
    //finte; en bolean setting, hvis den er false så skipper man kryptering, slik at man kan se om krypteringen virker eller ikke
    if (Config.getEncryption()) {

      // The key is predefined and hidden in code
      // TODO: Create a more complex code and store it somewhere better
      char[] key = {'C', 'B', 'S'};

      // Stringbuilder enables you to play around with strings and make useful stuff
      StringBuilder thisIsEncrypted = new StringBuilder();

      //FORKLAR XOR, hvordan den virker
      // TODO: This is where the magic of XOR is happening. Are you able to explain what is going on?
      for (int i = 0; i < rawString.length(); i++) {
        thisIsEncrypted.append((char) (rawString.charAt(i) ^ key[i % key.length]));
      }

      // We return the encrypted string
      return thisIsEncrypted.toString();

    } else {
      // We return without having done anything
      return rawString;
    }
  }

}
