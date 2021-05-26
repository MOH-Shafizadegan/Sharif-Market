import java.util.Scanner;

public class AdminMain {
    public static AdminManager adminManager = new AdminManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("            *******************************************");
        System.out.println("            *********       Sharif Market     *********");
        System.out.println("            *******************************************\n");
        System.out.println("            <<<<<<<<<<<      Admin Panel     >>>>>>>>>>");
        System.out.println("Enter your command: ");
        String command = scanner.nextLine();
        while (!command.equals("exit")){
            detectCommand(command);
            System.out.println("\nEnter your command: ");
            command = scanner.nextLine();
        }
        System.out.println("Thanks for choosing us, goodbye.");
        System.out.println("****************************************************************");
        System.out.println("**   Created and designed by Mohammad Hossein Shafizadegan    **");
        System.out.println("****************************************************************");
    }

    private static void detectCommand(String command){
        if (command.equals("ls -r")){
            adminManager.showAllProducts();
        }
        else if (command.equals("ls -i")){
            adminManager.showAvailableProducts();
        }
        else if (command.equals("ls -n")){
            adminManager.showNAProducts();
        }
        else if (command.equals("ls -o")){
            adminManager.showAllOrders();
        }
        else if (command.contains("checkout")){
            String[] sentences = command.split(" ");
            if (sentences.length != 2){
                System.out.println("Invalid command!...");
                return;
            }
            adminManager.checkOutOrder(sentences[1]);
        }
        else if (command.equals("ls -ho")){
            adminManager.showCheckedOutOrders();
        }
        else if (command.contains("add")){
            String[] sentences = command.split(" ");
            if (sentences.length != 9){
                System.out.println("Invalid command!...");
                return;
            }
            adminManager.addProduct(sentences[2], Integer.parseInt(sentences[4]),Long.parseLong(sentences[8]),Long.parseLong(sentences[6]));
        }
        else if (command.contains("remove")){
            String[] sentences = command.split(" ");
            if (sentences.length != 3){
                System.out.println("Invalid command!...");
                return;
            }
            adminManager.removeProduct(Integer.parseInt(sentences[2]));
        }
        else if (command.contains("edit")){
            String[] sentences = command.split(" ");
            switch (sentences.length){
                case 4:
                    adminManager.editProduct(Integer.parseInt(sentences[1]),sentences[3]);
                    break;
                case 6:
                    adminManager.editProduct(Integer.parseInt(sentences[1]),sentences[3],Integer.parseInt(sentences[5]));
                    break;
                case 8:
                    adminManager.editProduct(Integer.parseInt(sentences[1]),Long.parseLong(sentences[3]),Long.parseLong(sentences[5]),Integer.parseInt(sentences[7]));
                    break;
                default:
                    System.out.println("Invalid command!...");
                    return;
            }
        }
        else if (command.equals("calc -p")){
            adminManager.calculateBenefit();
        }
        else if (command.contains("calc -p -c")){
            String[] sentences = command.split(" ");
            if (sentences.length != 4){
                System.out.println("Invalid command!...");
                return;
            }
            adminManager.calculateSPB(Integer.parseInt(sentences[3]));
        }
        else if (command.equals("calc -s")){
            adminManager.calculateTotalSales();
        }
        else if (command.contains("calc -s -c")){
            String[] sentences = command.split(" ");
            if (sentences.length != 4){
                System.out.println("Invalid command!...");
                return;
            }
            adminManager.calculateSPS(Integer.parseInt(sentences[3]));
        }
        else
            System.out.println("Invalid command!...");
    }
}