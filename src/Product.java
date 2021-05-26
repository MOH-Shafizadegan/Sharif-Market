import java.util.Random;

public class Product {
    String name;
    int code;
    long buyingPrice;
    long sellingPrice;
    int inventory;

    public Product(String name, long buyingPrice, long sellingPrice, int inventory) {
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.inventory = inventory;
        Random random = new Random();
        this.code = random.nextInt(9000)+1000;
    }
}