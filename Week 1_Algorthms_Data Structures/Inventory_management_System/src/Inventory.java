import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Integer, Product> productMap =  new HashMap<>();;


    // Add a product
    public void addProduct(Product product) {
        productMap.put(product.getProductId(), product);
    }

    // Update a product
    public void updateProduct(Product product) {
        if (productMap.containsKey(product.getProductId())) {
            productMap.put(product.getProductId(), product);
        } else {
            System.out.println("Product not found");
        }
    }

    // Delete a product
    public void deleteProduct(Integer productId) {
        if (productMap.containsKey(productId)) {
            productMap.remove(productId);
        } else {
            System.out.println("Product not found");
        }
    }

    // Get a product
    public Product getProduct(Integer productId) {
        return productMap.get(productId);
    }
}
