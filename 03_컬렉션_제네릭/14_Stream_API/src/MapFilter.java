import java.util.List;
import java.util.stream.Collectors;

public class MapFilter {
    record Product(String name, int price) {}

    public static void main(String[] args) {
        List<Product> products = List.of(
            new Product("Pen", 1000),
            new Product("Notebook", 3000),
            new Product("Bag", 25000),
            new Product("Cup", 5000)
        );

        List<String> expensiveNames = products.stream()
            .filter(p -> p.price() >= 3000)
            .map(Product::name)
            .collect(Collectors.toList());

        System.out.println(expensiveNames);
    }
}
