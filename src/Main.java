import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        Logger logger = Logger.getLogger(Reader.class.getName());
        logger.setLevel(Level.INFO);
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();

        if (str.isEmpty()){
            str = "Hello";
        }

        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);

        byte[] value = (byte[]) field.get(str);

        IO.println(str);

        value[0] = 'J';

        IO.println(str);

        // 2 & 3 task ==================================================================================================

        Reader read = new Reader();

        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.WARNING);
            logger.addHandler(fileHandler);

            char key = 'A'; // ключовий символ для зсуву

            Locale locale = Locale.forLanguageTag("uk");
            ResourceBundle bundle = ResourceBundle.getBundle("location.messages", locale);

            while (true) {
                IO.println(bundle.getString("menu.title"));
                IO.println("1. " + bundle.getString("menu.decrypt"));
                IO.println("2. " + bundle.getString("menu.encrypt"));
                IO.println("3. " + bundle.getString("menu.language"));
                IO.println("0. " + bundle.getString("menu.exit"));

                String choice = scanner.nextLine();
                if (choice.equals("0")) {
                    break;
                }

                switch (choice) {
                    case "1":
                        System.out.println(bundle.getString("menu.decrypt"));
                        read.decryptFile("encrypted.txt", "decrypted.txt", key);
                        break;
                    case "2":
                        System.out.println(bundle.getString("menu.encrypt"));
                        read.encryptFile("test.txt", "encrypted.txt", key);
                        break;
                    case "3":
                        IO.println("1. Українська");
                        IO.println("2. English");
                        String language = scanner.nextLine();
                        if (language.equals("1")) {
                            locale = Locale.forLanguageTag("uk");
                            bundle = ResourceBundle.getBundle("location.messages", locale);
                        } else if (language.equals("2")) {
                            locale = Locale.forLanguageTag("en");
                            bundle = ResourceBundle.getBundle("location.messages", locale);
                        } else {
                            IO.println(bundle.getString("menu.wrong"));
                        }
                        break;
                    default:
                        IO.println(bundle.getString("menu.wrong"));
                        break;
                }
            }
            fileHandler.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}