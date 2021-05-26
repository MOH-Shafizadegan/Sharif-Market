import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdminManager {
    Store store = new Store();

    public void showAllProducts(){
        store.allProducts();
    }

    public void showAvailableProducts() {
        store.availableProducts();
    }

    public void showNAProducts(){
        store.notAvailableProducts();
    }

    public void showAllOrders() {
        store.allOrders();
    }

    public void checkOutOrder(String orderID){
        try {
            String order = "";
            Scanner scanner = new Scanner(store.orders);
            while (scanner.hasNext()){
                String temp = scanner.nextLine();
                String[] sentences = temp.split(",");
                if (sentences[3].equals(orderID)){
                    order = temp;
                    FileManager.addToFile(store.checkedOutOrders,order);
                }
            }
            FileManager.remove(store.orders,order);
            System.out.println("Order with order ID '"+orderID+"' checked out successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showCheckedOutOrders() {
        store.checkedOutOrder();
    }

    public void addProduct(String productName, int count, long buyPrice, long sellPrice){
        if (FileManager.isFind(store.products,productName)){
            System.out.println("ERROR: This product was added before!...");
            return;
        }
        Product product = new Product(productName,buyPrice,sellPrice,count);
        String storeProduct = productName+",";
        storeProduct += product.code+",";
        storeProduct += buyPrice+",";
        storeProduct += sellPrice+",";
        storeProduct += count;
        FileManager.addToFile(store.products,storeProduct);
        System.out.println("add was successful -> good_id = "+product.code);
    }

    public void removeProduct(int productID){
        if (!FileManager.isFind(store.products, String.valueOf(productID))){
            System.out.println("ERROR: Product with product ID '"+productID+"' not found!...");
            return;
        }
        FileManager.remove(store.products, String.valueOf(productID));
        System.out.println("Product removed successfully.");
    }

    public void editProduct(int productID, String newName){
        if (!FileManager.isFind(store.products, String.valueOf(productID))){
            System.out.println("ERROR: Product with product ID '"+productID+"' not found!...");
            return;
        }
        try {
            Scanner scanner = new Scanner(store.products);
            String[] product = null;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.contains(String.valueOf(productID))){
                    product = line.split(",");
                }
            }
            product[0] = newName;
            String result = "";
            for (int i = 0; i < product.length; i++) {
                result += product[i]+",";
            }
            FileManager.remove(store.products,String.valueOf(productID));
            FileManager.addToFile(store.products,result);
            System.out.println("Product edited successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editProduct(int productID, String newName, int newCount){
        if (!FileManager.isFind(store.products, String.valueOf(productID))){
            System.out.println("ERROR: Product with product ID '"+productID+"' not found!...");
            return;
        }
        try {
            Scanner scanner = new Scanner(store.products);
            String[] product = null;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.contains(String.valueOf(productID))){
                    product = line.split(",");
                }
            }
            product[0] = newName;
            product[4] = String.valueOf(newCount);
            String result = "";
            for (int i = 0; i < product.length; i++) {
                result += product[i]+",";
            }
            FileManager.remove(store.products,String.valueOf(productID));
            FileManager.addToFile(store.products,result);
            System.out.println("Product edited successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editProduct(int productID, int newCount){
        try {
            Scanner scanner = new Scanner(store.products);
            String[] product = null;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.contains(String.valueOf(productID))){
                    product = line.split(",");
                }
            }
            product[4] = String.valueOf(Integer.parseInt(product[4])-newCount);
            String result = "";
            for (int i = 0; i < product.length; i++) {
                result += product[i]+",";
            }
            FileManager.remove(store.products,String.valueOf(productID));
            FileManager.addToFile(store.products,result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editProduct(int productID, long newSellPrice, long newBuyPrice, int newCount){
        if (!FileManager.isFind(store.products, String.valueOf(productID))){
            System.out.println("ERROR: Product with product ID '"+productID+"' not found!...");
            return;
        }
        if (newBuyPrice > newSellPrice){
            System.out.println("ERROR: new sales price should be more than new buy price.");
            return;
        }
        try {
            Scanner scanner = new Scanner(store.products);
            String[] product = null;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.contains(String.valueOf(productID))){
                    product = line.split(",");
                }
            }
            product[3] = String.valueOf(newSellPrice);
            product[2] = String.valueOf(newBuyPrice);
            product[4] = String.valueOf(newCount);
            String result = "";
            for (int i = 0; i < product.length; i++) {
                result += product[i]+",";
            }
            FileManager.remove(store.products,String.valueOf(productID));
            FileManager.addToFile(store.products,result);
            System.out.println("Product edited successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void calculateBenefit(){
        try {
            Scanner scanner = new Scanner(store.checkedOutOrders);
            long totalBenefit = 0;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] sentences = line.split(",");
                int num = Integer.parseInt(sentences[2]);
                long benefit = 0;
                Scanner scanner1 = new Scanner(store.products);
                while (scanner1.hasNext()){
                    String line2 = scanner1.nextLine();
                    if (line2.contains(sentences[1])){
                        String[] text = line2.split(",");
                        benefit = Long.parseLong(text[3])-Long.parseLong(text[2]);
                    }
                }
                totalBenefit += benefit*num;
            }
            System.out.println("Total benefit is '"+totalBenefit+"' IRR.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void calculateSPB(int productID){
        try {
            Scanner scanner = new Scanner(store.checkedOutOrders);
            long benefit = 0;
            int num = 0;
            String productName ="";
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.contains(String.valueOf(productID))){
                    String[] sentences = line.split(",");
                    num = Integer.parseInt(sentences[2]);
                    Scanner scanner1 = new Scanner(store.products);
                    while (scanner1.hasNext()){
                        String line2 = scanner1.nextLine();
                        if (line2.contains(sentences[1])){
                            String[] text = line2.split(",");
                            productName = text[0];
                            benefit = Long.parseLong(text[3])-Long.parseLong(text[2]);
                        }
                    }
                }
            }
            if (productName.equals("")){
                System.out.println("You haven't sell any of this product yet.");
                return;
            }
            System.out.println("Total benefit of selling "+productName+" is: "+benefit*num);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void calculateTotalSales(){
        try {
            Scanner scanner = new Scanner(store.checkedOutOrders);
            long totalSale = 0;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] sentences = line.split(",");
                int num = Integer.parseInt(sentences[2]);
                long salePrice = 0;
                Scanner scanner1 = new Scanner(store.products);
                while (scanner1.hasNext()){
                    String line2 = scanner1.nextLine();
                    if (line2.contains(sentences[1])){
                        String[] text = line2.split(",");
                        salePrice = Long.parseLong(text[3]);
                    }
                }
                totalSale += salePrice*num;
            }
            System.out.println("Total Sale price is '"+totalSale+"' IRR.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void calculateSPS(int productID){
        try {
            Scanner scanner = new Scanner(store.checkedOutOrders);
            long sale = 0;
            int num = 0;
            String productName ="";
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.contains(String.valueOf(productID))){
                    String[] sentences = line.split(",");
                    num = Integer.parseInt(sentences[2]);
                    Scanner scanner1 = new Scanner(store.products);
                    while (scanner1.hasNext()){
                        String line2 = scanner1.nextLine();
                        if (line2.contains(sentences[1])){
                            String[] text = line2.split(",");
                            productName = text[0];
                            sale = Long.parseLong(text[3]);
                        }
                    }
                }
            }
            if (productName.equals("")){
                System.out.println("You haven't sell any of this product yet.");
                return;
            }
            System.out.println("Total amount of selling "+productName+" is: "+sale*num);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}