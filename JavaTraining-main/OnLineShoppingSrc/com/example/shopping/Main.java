package com.example.shopping;

import java.io.*;
import java.util.*;

public class Main {
    Scanner scanner = new Scanner(System.in);
    Map<String, Product> k = new HashMap<>();
    String nikhil="yes";
    boolean exit = false;
    public String fileName;
    private String orderHistorys = "order_history.dat";

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//it will load the file initially if we enter coorect file name it will perform operations or else it gives error name
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        OnlineShoppingSystem onlineShoppingSystem = new OnlineShoppingSystem();
        System.out.println("=== Online Shopping System ===");
        while (true) {
           main.sample();
            if (!(main.k==null)) {
                main.operation();
            } else {
                System.out.println();
                main.exit = false;
            }
            if (main.exit) {
                break;
            }
        }
    }


    public void sample() {
        System.out.print("Enter product file name to load");
        String fileName = scanner.nextLine();
        setFileName(fileName);
        k = loadProducts(fileName);
    }

    /**
     * it will browse the products and if we want to add products to cart enter product name or else press q to quit
     * @param shoppingCart
     * @param scanner
     * @param fileName
     */
    public void browseProducts(ShoppingCart shoppingCart, Scanner scanner, String fileName) {
        ProductCatalog productCatalog = new ProductCatalog();
        System.out.println("No products available.");
        System.out.println("No products available.");
        fileName = this.fileName;

        Map<String, Product> products = loadProducts(fileName);
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.print("Enter the name of the product to add to cart (or 'q' to quit): ");
        String productName = scanner.nextLine();

        if (!productName.equalsIgnoreCase("q")) {
            Product selectedProduct = products.get(productName);
            if (selectedProduct != null) {
                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();

                if (quantity > 0 && quantity <= selectedProduct.getQuantity()) {
                    shoppingCart.addItem(selectedProduct, quantity);
                    System.out.println("Product added to cart successfully.");

                    selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);

                    try (FileOutputStream fos = new FileOutputStream(fileName);
                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                        oos.writeObject(products);
                        System.out.println("Product catalog updated and saved to file.");
                    } catch (IOException e) {
                        System.out.println("Error saving product catalog to file: " + e.getMessage());
                    }
                } else {
                    System.out.println("Invalid quantity or insufficient stock.");
                }
            } else {
                System.out.println("Product not found.");
            }
        }
    }

    /**
     * it will load the products by giving file name
     * @param fileName
     * @return
     */
    public Map<String, Product> loadProducts(String fileName) {
        Map<String, Product> products = new HashMap<>();
        File file = new File(fileName);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object obj = ois.readObject();
                if (obj instanceof Map) {
                    products = (Map<String, Product>) obj;
                    System.out.println("Product catalog loaded successfully from file.");
                    System.out.println("--- Products in File ---");
                    for (Product product : products.values()) {
                        System.out.println(product);
                    }
                } else {
                    System.out.println("Invalid product catalog format in file.");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading product catalog from file: " + e.getMessage());
            }
        } else {
            System.out.println("File not found: " + fileName);
            nikhil="Not";
            return null;
        }
        return products;
    }


    /**
     * It will perform operations based on user input
     */
    public void operation() {
        Scanner scanner = new Scanner(System.in);
        ProductCatalog productCatalog = new ProductCatalog();
        ShoppingCart shoppingCart = new ShoppingCart();
        OrderHistory orderHistory = new OrderHistory();
        OnlineShoppingSystem onlineShoppingSystem = new OnlineShoppingSystem();
        Product product = new Product();
        Main main = new Main();
        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Browse Products");
            System.out.println("2. View Shopping Cart");
            System.out.println("3. Place Order");
            System.out.println("4. Save Product Catalog");
            System.out.println("5. Save Order History");
            System.out.println("6. View Order History");
            System.out.println("7. Remove Item");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    browseProducts(shoppingCart, scanner, main.fileName);
                    break;
                case 2:
                    onlineShoppingSystem.viewShoppingCart(shoppingCart);
                    break;
                case 3:
                    onlineShoppingSystem.placeOrder(shoppingCart, orderHistory, scanner);
                    break;
                case 4:
                    System.out.print("Enter the product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the product description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter the product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter the product quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    product = new Product(name, description, price, quantity);
                    productCatalog.products.put(product.getName(), product);
                    System.out.print("Enter product file name to add data");
                    System.out.print("select product to add existing file or new file");
                    System.out.print("Enter 1 for existing file");
                    System.out.print("Enter 2 for new file");
                    int choice1=scanner.nextInt();
                   if(choice1==1) {
                       productCatalog.saveProducts(fileName);
                   } else if (choice1==2) {
                       System.out.print("Enter 2 for new file");
                       String productFileNames = scanner.next();
                       productCatalog.saveProducts(productFileNames);
                   }
                    else{
                       System.out.print("Invalid Input");
                    }
                    break;
                case 5:
                    orderHistory.saveOrderHistory(orderHistorys);
                    break;
                case 6:
                    orderHistory.loadOrderHistory(orderHistorys);
                    break;
                case 7:
                    onlineShoppingSystem.removeItemFromCart(shoppingCart, scanner);
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
