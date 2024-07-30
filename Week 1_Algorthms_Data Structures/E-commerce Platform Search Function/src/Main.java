public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new Product("P001", "Laptop", "Electronics"),
                new Product("P002", "Smartphone", "Electronics"),
                new Product("P003", "Tablet", "Electronics"),
                new Product("P004", "Headphones", "Accessories"),
                new Product("P005", "Charger", "Accessories")
        };

        // Linear Search
        Product foundProductLinear = LinearSearch.linearSearch(products, "Charger");
        System.out.println("Linear Search Result: " + foundProductLinear);

        // Binary Search
        Product foundProductBinary = BinarySearch.binarySearch(products, "Tablet");
        System.out.println("Binary Search Result: " + foundProductBinary);
    }
}
