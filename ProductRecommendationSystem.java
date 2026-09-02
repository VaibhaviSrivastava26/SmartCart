import java.util.*;

class Product {
    String name;
    String category;
    double price;
    double rating;

    Product(String name, String category, double price, double rating) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
    }

    void displayProduct() {
        System.out.println("Product: " + name);
        System.out.println("Category: " + category);
        System.out.println("Price: ₹" + price);
        System.out.println("Rating: " + rating + "/5");
        System.out.println("-------------------------");
    }
}

public class ProductRecommendationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Product database
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("Laptop", "Electronics", 55000, 4.5));
        products.add(new Product("Smartphone", "Electronics", 25000, 4.3));
        products.add(new Product("Headphones", "Electronics", 2000, 4.2));

        products.add(new Product("T-Shirt", "Clothing", 799, 4.1));
        products.add(new Product("Jeans", "Clothing", 1499, 4.4));
        products.add(new Product("Jacket", "Clothing", 2499, 4.6));

        products.add(new Product("Java Programming", "Books", 599, 4.7));
        products.add(new Product("Python Basics", "Books", 499, 4.5));

        System.out.println("===== PRODUCT RECOMMENDATION SYSTEM =====");

        System.out.print("Enter your preferred category: ");
        String category = sc.nextLine();

        System.out.println("\nRecommended Products:");

        boolean found = false;

        for (Product p : products) {

            if (p.category.equalsIgnoreCase(category)) {
                p.displayProduct();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry! No products found in this category.");
        }

        sc.close();
    }
}