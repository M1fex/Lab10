import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.lang.reflect.Field;
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

            while (true) {
                IO.println("Вибір мови");
                IO.println("1. Українська");
                IO.println("2. English");
                String language = scanner.nextLine();
                if (language.isEmpty()) {
                    break;
                }

                switch (language) {
                    case "1":
                        IO.println("Ви обрали українську мову");
                        break;
                    case "2":
                        IO.println("You have chosen English language");
                        break;
                    default:
                        IO.println("Невірний вибір");
                        break;
                }
            }
            fileHandler.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}