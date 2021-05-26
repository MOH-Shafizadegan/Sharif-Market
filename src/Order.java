public class Order {
    String costumerID;
    int productCode;
    int productInventory;
    String orderID;

    public Order(String costumerID, int productCode, int productInventory,int orderNum) {
        this.costumerID = costumerID;
        this.productCode = productCode;
        this.productInventory = productInventory;
        this.orderID = costumerID;
        for (int i = 0; i < 4-String.valueOf(orderNum).length(); i++) {
            orderID += "0";
        }
        this.orderID += String.valueOf(orderNum);
    }
}