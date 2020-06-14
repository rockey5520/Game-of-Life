import java.util.Objects;
import java.util.Scanner;

class StringProcessor extends Thread {

  final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

  @Override
  public void run() {

    while (scanner.hasNext()) {
      String input = scanner.nextLine();
      if (Objects.equals(input, input.toUpperCase())) {
        System.out.println("FINISHED");
      } else {
        System.out.println(input.toUpperCase());
      }
    }

  }
}