import java.util.Arrays;
import java.lang.reflect.Field;

class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String str = "Hello";

        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);

        // char[] value = (char[]) field.get(str);

        byte[] value = (byte[]) field.get(str);
        value[0] = Byte.parseByte("J");

        IO.println("value = " + Arrays.toString(value));
    }
}