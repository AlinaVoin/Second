import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Second_Task {
    public static boolean isHexStr(String s){
        int i = 0;
        if (s.length() > 2){
            if (s.charAt(0) == '0' && (s.charAt(1) == 'x' || s.charAt(1) == 'X')){
                i = 2;
            }
        }
        for (; i < s.length(); i++){
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') continue;
            if (c >= 'A' && c <= 'F') continue;
            if (c >= 'a' && c <= 'f') continue;
            return false;
        }
        return true;
    }

    public static void main(String[] a) throws IOException {
        if (a.length != 4){
            System.out.format("Ожидается 4 параметра командной строки. \n");
            return;
        }

        if (a[0].equals("-c")){
            String key = a[1];
            if (key.charAt(0) == '0' && (key.charAt(1) == 'x' || key.charAt(1) == 'X')) {
                key = key.substring(2);
            }
            if (isHexStr(key)){
                if (key.length() % 2 == 0){
                    Path filePath = Paths.get(a[2]);
                    if (!Files.exists(filePath)){
                        System.out.format("Файл не найден! \n");
                        return;
                    }
                    Path filePath2 = Paths.get(a[3]);
                    byte[] allBytes = Files.readAllBytes(filePath);
                    byte[] hexKey = new byte[key.length() / 2];
                    for (int i = 0; i < key.length() / 2; i++){
                        String bs = "";
                        bs += key.charAt(i * 2);
                        bs += key.charAt(i * 2 + 1);
                        int x = Integer.parseInt(bs, 16);
                        hexKey[i] = (byte) x;
                    }
                    for (int i = 0; i < allBytes.length; i++){
                        int k = i % hexKey.length;
                        allBytes[i] = (byte) (Byte.toUnsignedInt(allBytes[i]) ^ Byte.toUnsignedInt(hexKey[k]));
                    }
                    Files.write(filePath2, allBytes);
                }
                else{
                    System.out.format("В качестве ключа ожидается чётное количество шестнадцетиричных цифр. \n");
                }
            }
        }
        else if (a[0].equals("-d")){
            String key = a[1];
            if (key.charAt(0) == '0' && (key.charAt(1) == 'x' || key.charAt(1) == 'X')) {
                key = key.substring(2);
            }
            if (isHexStr(key)){
                if (key.length() % 2 == 0){
                    Path filePath = Paths.get(a[2]);
                    if (!Files.exists(filePath)) {
                        System.out.format("Файл не найден! \n");
                        return;
                    }
                    Path filePath2 = Paths.get(a[3]);
                    byte[] allBytes = Files.readAllBytes(filePath);
                    byte[] hexKey = new byte[key.length() / 2];
                    for (int i = 0; i < key.length() / 2; i++){
                        String bs = "";
                        bs += key.charAt(i * 2);
                        bs += key.charAt(i * 2 + 1);
                        int x = Integer.parseInt(bs, 16);
                        hexKey[i] = (byte) x;
                    }
                    for (int i = 0; i < allBytes.length; i++){
                        int k = i % hexKey.length;
                        allBytes[i] = (byte) (Byte.toUnsignedInt(allBytes[i]) ^ Byte.toUnsignedInt(hexKey[k]));
                    }
                    Files.write(filePath2, allBytes);
                }
                else{
                    System.out.format("В качестве ключа ожидается чётное количество шестнадцетиричных цифр. \n");
                }
            }
        }
        else{
            System.out.format("Укажите параметр -с или -d \n");
        }
    }
}

