public class CustomerManager {
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

    public void registerOrder (String costumerID, int productID, int num){
        if (!FileManager.isFind(store.products, String.valueOf(productID))){
            System.out.println("ERROR: order not successful");
            System.out.println("product with product ID '"+String.valueOf(productID)+"' not found!...");
            return;
        }
        if (num > FileManager.getProductNum(store.products,productID)){
            System.out.println("Sorry Inventory is not enough.");
            System.out.println("ERROR: order not successful");
            return;
        }
        int orderNum = 1+FileManager.getFileItems(store.orders)+FileManager.getFileItems(store.checkedOutOrders);
        Order order = new Order(costumerID,productID,num,orderNum);
        String customerOrder = costumerID+",";
        customerOrder += String.valueOf(productID)+",";
        customerOrder += String.valueOf(num)+",";
        customerOrder += order.orderID;
        FileManager.addToFile(store.orders,customerOrder);
        System.out.println("Your order id is = "+order.orderID);
        update(productID,num);
    }

    public void cancelOrder(String orderID){
        if (!FileManager.isFind(store.orders, orderID)){
            System.out.println("Error deleting order "+orderID+" !");
            return;
        }
        FileManager.remove(store.orders,orderID);
        System.out.println("order "+orderID+" was deleted successfully!");
    }

    public void update(int productID, int count){
        AdminManager adminManager = new AdminManager();
        adminManager.editProduct(productID,count);
    }
}