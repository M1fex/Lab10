import java.io.File;
import java.util.Arrays;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
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

        Reader read = new Reader();

        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.WARNING);
            logger.addHandler(fileHandler);

            System.out.println("\n=== Task 3: Шифрування та дешифрування ===");
            char key = 'A'; // ключовий символ для зсуву
            read.encryptFile("unnamed.txt", "encrypted.txt", key);
            // read.decryptFile("encrypted.txt", "decrypted.txt", key);

            fileHandler.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}