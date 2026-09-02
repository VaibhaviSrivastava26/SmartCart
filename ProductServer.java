import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProductServer {

    static class Product {
        String name;
        String category;
        double price;
        double rating;
        int popularity;
        int discount;
        boolean newArrival;
        String image;

        Product(String name, String category, double price,
                double rating, int popularity, int discount,
                boolean newArrival, String image) {

            this.name = name;
            this.category = category;
            this.price = price;
            this.rating = rating;
            this.popularity = popularity;
            this.discount = discount;
            this.newArrival = newArrival;
            this.image = image;
        }
    }

    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        addProducts();

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);

        server.createContext("/", ProductServer::serveHome);
        server.createContext("/recommend", ProductServer::recommend);

        server.setExecutor(null);

        System.out.println();
        System.out.println("========================================");
        System.out.println("           SMARTCART SERVER");
        System.out.println("========================================");
        System.out.println("Server started successfully!");
        System.out.println("Website: http://localhost:8080");
        System.out.println("========================================");

        server.start();
    }

    static void addProducts() {

        products.add(new Product(
                "Classic Laptop",
                "Electronics",
                55000,
                4.5,
                95,
                10,
                true,
                "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Premium Smartphone",
                "Electronics",
                25000,
                4.3,
                98,
                15,
                true,
                "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Studio Headphones",
                "Electronics",
                2000,
                4.2,
                90,
                20,
                false,
                "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Luxury Smart Watch",
                "Electronics",
                3500,
                4.6,
                88,
                25,
                true,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Wireless Mouse",
                "Electronics",
                899,
                4.4,
                80,
                30,
                false,
                "https://images.unsplash.com/photo-1527814050087-3793815479db?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Essential T-Shirt",
                "Clothing",
                799,
                4.1,
                75,
                20,
                true,
                "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Classic Denim Jeans",
                "Clothing",
                1499,
                4.4,
                85,
                15,
                false,
                "https://images.unsplash.com/photo-1542272604-787c3835535d?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Signature Jacket",
                "Clothing",
                2499,
                4.6,
                92,
                30,
                true,
                "https://images.unsplash.com/photo-1551028719-00167b16eac5?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Premium Sneakers",
                "Clothing",
                2999,
                4.7,
                97,
                35,
                true,
                "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Java Programming",
                "Books",
                599,
                4.7,
                70,
                10,
                false,
                "https://images.unsplash.com/photo-1515879218367-8466d910aaa4?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Python Basics",
                "Books",
                499,
                4.5,
                82,
                20,
                true,
                "https://images.unsplash.com/photo-1526379095098-d400fd0bf935?auto=format&fit=crop&w=1200&q=90"
        ));

        products.add(new Product(
                "Data Structures",
                "Books",
                699,
                4.8,
                91,
                15,
                true,
                "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?auto=format&fit=crop&w=1200&q=90"
        ));
    }

    static void serveHome(HttpExchange exchange) throws IOException {

        String path = exchange.getRequestURI().getPath();

        if (path.equals("/") || path.equals("/index.html")) {

            File file = new File("index.html");

            if (!file.exists()) {

                String message =
                        "index.html not found. Please keep index.html "
                        + "in the same folder as ProductServer.java.";

                sendResponse(
                        exchange,
                        message,
                        "text/plain",
                        404
                );

                return;
            }

            byte[] data =
                    Files.readAllBytes(
                            Paths.get("index.html")
                    );

            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/html; charset=UTF-8"
            );

            exchange.sendResponseHeaders(
                    200,
                    data.length
            );

            OutputStream output =
                    exchange.getResponseBody();

            output.write(data);
            output.close();

        } else {

            sendResponse(
                    exchange,
                    "404 - Page Not Found",
                    "text/plain",
                    404
            );
        }
    }

    static void recommend(HttpExchange exchange)
            throws IOException {

        String query =
                exchange.getRequestURI().getQuery();

        String type = "all";
        String category = "";
        String search = "";

        double budget = 100000;
        double minRating = 0;

        if (query != null) {

            String[] parameters =
                    query.split("&");

            for (String parameter : parameters) {

                String[] pair =
                        parameter.split("=", 2);

                if (pair.length == 2) {

                    String key = pair[0];

                    String value =
                            URLDecoder.decode(
                                    pair[1],
                                    StandardCharsets.UTF_8
                            );

                    if (key.equals("type")) {
                        type = value;
                    }

                    if (key.equals("category")) {
                        category = value;
                    }

                    if (key.equals("search")) {
                        search =
                                value.toLowerCase();
                    }

                    if (key.equals("budget")) {

                        try {
                            budget =
                                    Double.parseDouble(value);
                        }

                        catch (Exception e) {
                            budget = 100000;
                        }
                    }

                    if (key.equals("rating")) {

                        try {
                            minRating =
                                    Double.parseDouble(value);
                        }

                        catch (Exception e) {
                            minRating = 0;
                        }
                    }
                }
            }
        }

        ArrayList<Product> result =
                new ArrayList<>();

        for (Product product : products) {

            if (!category.isEmpty()
                    && !product.category
                    .equalsIgnoreCase(category)) {

                continue;
            }

            if (product.price > budget) {
                continue;
            }

            if (product.rating < minRating) {
                continue;
            }

            if (!search.isEmpty()) {

                boolean nameMatch =
                        product.name
                        .toLowerCase()
                        .contains(search);

                boolean categoryMatch =
                        product.category
                        .toLowerCase()
                        .contains(search);

                if (!nameMatch && !categoryMatch) {
                    continue;
                }
            }

            result.add(product);
        }

        switch (type.toLowerCase()) {

            case "top":

                result.sort(
                        Comparator.comparingDouble(
                                p -> -p.rating
                        )
                );

                break;

            case "budget":

                result.sort(
                        Comparator.comparingDouble(
                                p -> p.price
                        )
                );

                break;

            case "trending":

                result.sort(
                        Comparator.comparingInt(
                                p -> -p.popularity
                        )
                );

                break;

            case "new":

                result.removeIf(
                        p -> !p.newArrival
                );

                result.sort(
                        Comparator.comparingInt(
                                p -> -p.popularity
                        )
                );

                break;

            case "premium":

                result.sort(
                        Comparator.comparingDouble(
                                p -> -p.price
                        )
                );

                break;

            case "deals":

                result.sort(
                        Comparator.comparingInt(
                                p -> -p.discount
                        )
                );

                break;

            case "student":

                result.removeIf(
                        p -> p.price > 3000
                );

                result.sort(
                        Comparator.comparingDouble(
                                p -> -p.rating
                        )
                );

                break;

            case "value":

                result.sort(
                        Comparator.comparingDouble(
                                p -> -(p.rating /
                                (p.price / 1000))
                        )
                );

                break;

            default:

                result.sort(
                        Comparator.comparingDouble(
                                p -> -p.rating
                        )
                );
        }

        String json =
                convertToJSON(result);

        sendResponse(
                exchange,
                json,
                "application/json",
                200
        );
    }

    static String convertToJSON(
            ArrayList<Product> list) {

        StringBuilder json =
                new StringBuilder();

        json.append("[");

        boolean first = true;

        for (Product p : list) {

            if (!first) {
                json.append(",");
            }

            json.append("{");

            json.append("\"name\":\"")
                    .append(escape(p.name))
                    .append("\",");

            json.append("\"category\":\"")
                    .append(escape(p.category))
                    .append("\",");

            json.append("\"price\":")
                    .append(p.price)
                    .append(",");

            json.append("\"rating\":")
                    .append(p.rating)
                    .append(",");

            json.append("\"popularity\":")
                    .append(p.popularity)
                    .append(",");

            json.append("\"discount\":")
                    .append(p.discount)
                    .append(",");

            json.append("\"newArrival\":")
                    .append(p.newArrival)
                    .append(",");

            json.append("\"image\":\"")
                    .append(escape(p.image))
                    .append("\"");

            json.append("}");

            first = false;
        }

        json.append("]");

        return json.toString();
    }

    static String escape(String text) {

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    static void sendResponse(
            HttpExchange exchange,
            String response,
            String contentType,
            int status) throws IOException {

        byte[] data =
                response.getBytes(
                        StandardCharsets.UTF_8
                );

        exchange.getResponseHeaders().set(
                "Content-Type",
                contentType + "; charset=UTF-8"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Origin",
                "*"
        );

        exchange.sendResponseHeaders(
                status,
                data.length
        );

        OutputStream output =
                exchange.getResponseBody();

        output.write(data);
        output.close();
    }
}