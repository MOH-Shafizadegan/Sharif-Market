import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    File products = new File("products.txt");
    File orders = new File("orders.txt");
    File checkedOutOrders = new File("checkedOutOrders.txt");
    File costumers = new File("costumers.txt");

    public void allProducts(){
        try {
            Scanner scanner = new Scanner(products);
            System.out.println("+-----------------+---------------+------------+------------+\n" +
                    "| Good name       | Product Code  | Inventory  | Price(IRR) |\n" +
                    "+-----------------+---------------+------------+------------+");
            while (scanner.hasNext()){
                String[] sentences = scanner.nextLine().split(",");
                System.out.print("| "+sentences[0]);
                for (int i = 0; i < 16-sentences[0].length(); i++) {
                    System.out.print(" ");
                }
                System.out.print("| "+sentences[1]);
                for (int i = 0; i < 14-sentences[1].length(); i++) {
                    System.out.print(" ");
                }
                System.out.print("| "+sentences[4]);
                for (int i = 0; i < 11-sentences[4].length(); i++) {
                    System.out.print(" ");
                }
                System.out.print("| "+sentences[3]);
                for (int i = 0; i < 11-sentences[3].length(); i++) {
                    System.out.print(" ");
                }
                System.out.println("|");
            }
            System.out.println("+-----------------+---------------+------------+------------+\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void availableProducts(){
        try {
            Scanner scanner = new Scanner(products);
            System.out.println("+-----------------+------------+------------+\n" +
                    "| Good name       | Inventory  | Price(IRR) |\n" +
                    "+-----------------+------------+------------+");
            while (scanner.hasNext()){
                String[] sentences = scanner.nextLine().split(",");
                if (!sentences[4].equals("0")){
                    System.out.print("| "+sentences[0]);
                    for (int i = 0; i < 16-sentences[0].length(); i++) {
                        System.out.print(" ");
                    }
                    System.out.print("| "+sentences[4]);
                    for (int i = 0; i < 11-sentences[4].length(); i++) {
                        System.out.print(" ");
                    }
                    System.out.print("| "+sentences[3]);
                    for (int i = 0; i < 11-sentences[3].length(); i++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                }
            }
            System.out.println("+-----------------+------------+------------+");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void notAvailableProducts(){
        try {
            Scanner scanner = new Scanner(products);
            System.out.println("+-----------------+------------+------------+\n" +
                    "| Good name       | Inventory  | Price(IRR) |\n" +
                    "+-----------------+------------+------------+");
            while (scanner.hasNext()){
                String[] sentences = scanner.nextLine().split(",");
                if (sentences[4].equals("0")){
                    System.out.print("| "+sentences[0]);
                    for (int i = 0; i < 16-sentences[0].length(); i++) {
                        System.out.print(" ");
                    }
                    System.out.print("| "+sentences[4]);
                    for (int i = 0; i < 11-sentences[4].length(); i++) {
                        System.out.print(" ");
                    }
                    System.out.print("| "+sentences[3]);
                    for (int i = 0; i < 11-sentences[3].length(); i++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                }
            }
            System.out.println("+-----------------+------------+------------+");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void allOrders(){
        try {
            int c = 1;
            Scanner scanner = new Scanner(orders);
            if (!scanner.hasNext()){
                System.out.println("No orders...");
                return;
            }
            while (scanner.hasNext()){
                System.out.println("Order number "+c+" :");
                String[] sentences = scanner.nextLine().split(",");
                System.out.println("Order ID: "+sentences[3]);
                System.out.println("Customer ID: "+sentences[0]);
                System.out.println("Product ID: "+sentences[1]+"\tCount/weight: "+sentences[2]);
                c++;
                System.out.println("----------------------------------");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkedOutOrder(){
        try {
            int c = 1;
            Scanner scanner = new Scanner(checkedOutOrders);
            if (!scanner.hasNext()){
                System.out.println("No checked out orders...");
                return;
            }
            while (scanner.hasNext()){
                System.out.println("Order number "+c+" :");
                String[] sentences = scanner.nextLine().split(",");
                System.out.println("Order ID: "+sentences[3]);
                System.out.println("Customer ID: "+sentences[0]);
                System.out.println("Product ID: "+sentences[1]+"\tCount/weight: "+sentences[2]);
                c++;
                System.out.println("----------------------------------");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}