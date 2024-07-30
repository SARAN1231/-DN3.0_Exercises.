public class Main {
    public static void main(String[] args) {
        Order[] orders = {
                new Order("O001", "Alice", 150.0),
                new Order("O002", "Bob", 200.0),
                new Order("O003", "Charlie", 50.0),
                new Order("O004", "David", 300.0),
                new Order("O005", "Eve", 100.0)
        };

        // Bubble Sort
        System.out.println("Before Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }
        SortingAlgorithms.bubbleSort(orders);
        System.out.println("After Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Quick Sort
        Order[] orders2 = {
                new Order("O001", "Alice", 150.0),
                new Order("O002", "Bob", 200.0),
                new Order("O003", "Charlie", 50.0),
                new Order("O004", "David", 300.0),
                new Order("O005", "Eve", 100.0)
        };
        System.out.println("Before Quick Sort:");
        for (Order order : orders2) {
            System.out.println(order);
        }
        SortingAlgorithms.quickSort(orders2, 0, orders2.length - 1);
        System.out.println("After Quick Sort:");
        for (Order order : orders2) {
            System.out.println(order);
        }
    }
}
