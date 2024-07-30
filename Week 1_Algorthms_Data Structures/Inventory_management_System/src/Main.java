public class Main {
    public static void main(String[] args) {

        Inventory inv = new Inventory();
        // Adding products
        Product product1 = new Product(1, "Product 1", 10, 99.99);
        Product product2 = new Product(2, "Product 2", 5, 49.99);
        inv.addProduct(product1);
        inv.addProduct(product2);

        //display
        System.out.println("\nAll Products in Inventory: ");
        System.out.println(inv.getProduct(1));
        System.out.println(inv.getProduct(2));

        // Updating a product
        product1.setPrice(89.99);
        inv.updateProduct(product1);

        // Displaying updated product
        System.out.print("\nUpdated product1: \n");
        System.out.println(inv.getProduct(1));

        System.out.println("\nAll Products in Inventory after updating: ");
        System.out.println(inv.getProduct(1));
        System.out.println(inv.getProduct(2));
        // Deleting a product
        inv.deleteProduct(2);
        System.out.println("\nDeleted product2 \n ");

        // Trying to display deleted product
        System.out.print("\nRemaining products: \n");
        System.out.println(inv.getProduct(1));
    }
}