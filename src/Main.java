import java.util.Arrays;
import java.lang.reflect.Field;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
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
    }
}