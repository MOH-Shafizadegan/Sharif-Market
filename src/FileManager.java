import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static void addToFile(File file, String input) {
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.append(input+"\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void remove(File file, String input) {
        try {
            ArrayList<String> text = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            FileWriter fileWriter = new FileWriter(file,true);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (!line.contains(input)){
                    text.add(line);
                }
            }
            FileWriter fileWriter1 = new FileWriter(file);
            fileWriter1.write("");
            fileWriter1.close();
            for (String str : text){
                fileWriter.append(str+"\n");
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFind(File file, String input) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.contains(input))
                    return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUserPass (File file, String userID) {
        String password = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] sentences = line.split(",");
                if (sentences[0].equals(userID)){
                    password = sentences[1];
                    return password;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static int getProductNum (File file, int productID) {
        int result = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] sentences = line.split(",");
                if (sentences[1].equals(String.valueOf(productID))){
                    result = Integer.parseInt(sentences[4]);
                    return result;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getFileItems (File file) {
        int result = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) result++;
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}