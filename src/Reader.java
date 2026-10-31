import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader {

    private static final Logger logger = Logger.getLogger(Reader.class.getName());

    // Задание 1: поиск строки с максимальным количеством слов
    public String findLineWithMaxWords(String filePath) {
        int maxWordsCount = 0;
        String maxLine = "";


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] words = line.trim().split("\\s+");
                int wordsCount = words.length;
                if (wordsCount > maxWordsCount) {
                    maxWordsCount = wordsCount;
                    maxLine = line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return maxLine;
    }

    // Задание 3: метод шифрования
    public void encryptFile(String sourcePath, String destPath, char key) {
        logger.info("Начало шифрования файла: " + sourcePath + "-> " + destPath);

        try (FileReader in = new FileReader(sourcePath);
             EncryptWriter out = new EncryptWriter(new FileWriter(destPath), key)) {

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            logger.info("Файл успешно зашифрован в: " + destPath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при шифровании файла" + sourcePath, e);
        }
    }

    // Задание 3: метод дешифрования
    public void decryptFile(String sourcePath, String destPath, char key) {
        logger.info("Начало дешифрования файла: " + sourcePath + "-> " + destPath);
        try (DecryptReader in = new DecryptReader(new FileReader(sourcePath), key);
             FileWriter out = new FileWriter(destPath)) {

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            logger.info("Файл успешно расшифрован в: " + destPath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при дешифровании файла" + sourcePath, e);
        }
    }
}