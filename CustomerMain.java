import java.util.Scanner;

public class CustomerMain {
    public static CustomerManager customerManager = new CustomerManager();
    public static String customerID;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("            *******************************************");
        System.out.println("            *********       Sharif Market     *********");
        System.out.println("            *******************************************\n");
        System.out.println("            <<<<<<<<    Customers Panel    >>>>>>>>>\n");
        System.out.println("Enter your command: ");
        System.out.println("Attention: You should sign in first.");
        String command = scanner.nextLine();
        while (!command.equals("exit")){
            detectCommand(command);
            System.out.println("\nEnter your command:");
            command = scanner.nextLine();
        }
        System.out.println("Thanks for choosing us, goodbye.");
        System.out.println("************************************************************************");
        System.out.println("******   Created and designed by Mohammad Hossein Shafizadegan    ******");
        System.out.println("************************************************************************");
    }

    private static void detectCommand(String command){
        if (command.contains("login")){
            System.out.println("Enter your password:");
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();
            String[] sentences = command.split(" ");
            if (sentences.length != 2){
                System.out.println("Invalid command!...");
                return;
            }
            customerID = sentences[1];
            if (!FileManager.isFind(customerManager.store.costumers,customerID)){
                while (!password.matches("[a-zA-Z]+\\d+")){
                    System.out.println("ERROR: For more security, your password should contains numbers and letters.");
                    password = scanner.nextLine();
                }
                FileManager.addToFile(customerManager.store.costumers,customerID+","+password);
            }
            else {
                while (!password.equals(FileManager.getUserPass(customerManager.store.costumers,customerID))){
                    System.out.println("ERROR: The password is incorrect, Please Enter your password again:");
                    password = scanner.nextLine();
                }
            }
            System.out.println("Signed in successfully.");
            System.out.println("Welcome to Sharif Market.");
        }
        else if (command.equals("ls -r")){
            customerManager.showAllProducts();
        }
        else if (command.equals("ls -i")){
            customerManager.showAvailableProducts();
        }
        else if (command.equals("ls -n")){
            customerManager.showNAProducts();
        }
        else if (command.contains("-d")){
            String[] sentences = command.split(" ");
            if (sentences.length != 3){
                System.out.println("Invalid command!...");
                return;
            }
            customerManager.cancelOrder(sentences[2]);
        }
        else if (command.contains("order")){
            String[] sentences = command.split(" ");
            if (sentences.length != 4){
                System.out.println("Invalid command!...");
                return;
            }
            customerManager.registerOrder(customerID, Integer.parseInt(sentences[1]),Integer.parseInt(sentences[3]));
        }
        else if (command.equals("logout")){
            main(null);
        }
        else
            System.out.println("Invalid command!...");
    }
}