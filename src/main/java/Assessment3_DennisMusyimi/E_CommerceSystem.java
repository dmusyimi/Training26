package Assessment3_DennisMusyimi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

// Custom Exceptions for E-Commerce System

class OutOfStockException extends Exception {
    private String productId;
    private int requestedQuantity;
    private int availableQuantity;

    public OutOfStockException(String message, String productId, int requestedQuantity, int availableQuantity) {
        super(message);
        this.productId = productId;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    public String getProductId() { return productId; }
    public int getRequestedQuantity() { return requestedQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }

    @Override
    public String toString() {
        return super.toString() + 
               String.format(" [Product: %s, Requested: %d, Available: %d]", 
                           productId, requestedQuantity, availableQuantity);
    }
}

class InvalidProductDataException extends RuntimeException {
    private String fieldName;
    private String invalidValue;

    public InvalidProductDataException(String message, String fieldName, String invalidValue) {
        super(message);
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
    }

    public String getFieldName() { return fieldName; }
    public String getInvalidValue() { return invalidValue; }

    @Override
    public String toString() {
        return super.toString() + 
               String.format(" [Field: %s, Value: %s]", fieldName, invalidValue);
    }
}

class ProductNotFoundException extends Exception {
    private String productId;

    public ProductNotFoundException(String message, String productId) {
        super(message);
        this.productId = productId;
    }

    public String getProductId() { return productId; }

    @Override
    public String toString() {
        return super.toString() + " [Product ID: " + productId + "]";
    }
}

// Abstract Base Class: Product
abstract class Product {
    protected String productId;
    protected String name;
    protected double basePrice;
    protected String category;
    protected static final double TAX_RATE = 0.08; // 8% tax rate

    public Product(String productId, String name, double basePrice, String category) {
        validateProductId(productId);
        validateName(name);
        validatePrice(basePrice);
        validateCategory(category);

        this.productId = productId.trim();
        this.name = name.trim();
        this.basePrice = roundAmount(basePrice);
        this.category = category.trim();
    }

    // Abstract methods to be implemented by concrete classes
    public abstract double calculateDiscount();
    public abstract String getProductType();

    // Concrete methods
    public double applyTax() {
        double discountedPrice = basePrice - calculateDiscount();
        return roundAmount(discountedPrice * (1 + TAX_RATE));
    }

    public void displayProductInfo() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.printf("║ %-20s: %-20s ║%n", "Product ID", productId);
        System.out.printf("║ %-20s: %-20s ║%n", "Name", name);
        System.out.printf("║ %-20s: %-20s ║%n", "Type", getProductType());
        System.out.printf("║ %-20s: %-20s ║%n", "Category", category);
        System.out.printf("║ %-20s: $%-19.2f ║%n", "Base Price", basePrice);
        System.out.printf("║ %-20s: $%-19.2f ║%n", "Discount", calculateDiscount());
        System.out.printf("║ %-20s: $%-19.2f ║%n", "Final Price (with tax)", applyTax());
        displaySpecificInfo();
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    // Template method for subclasses to add specific information
    protected abstract void displaySpecificInfo();

    // Validation methods
    private void validateProductId(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new InvalidProductDataException("Product ID cannot be null or empty", "productId", productId);
        }
        if (productId.trim().length() < 3) {
            throw new InvalidProductDataException("Product ID must be at least 3 characters", "productId", productId);
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductDataException("Product name cannot be null or empty", "name", name);
        }
        if (name.trim().length() < 2) {
            throw new InvalidProductDataException("Product name must be at least 2 characters", "name", name);
        }
    }

    private void validatePrice(double price) {
        if (price < 0) {
            throw new InvalidProductDataException("Price cannot be negative", "price", String.valueOf(price));
        }
        if (Double.isNaN(price) || Double.isInfinite(price)) {
            throw new InvalidProductDataException("Price must be a valid number", "price", String.valueOf(price));
        }
    }

    private void validateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidProductDataException("Category cannot be null or empty", "category", category);
        }
    }

    protected double roundAmount(double amount) {
        return BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public String getCategory() { return category; }
}

// Electronics Class
class Electronics extends Product {
    private int warrantyPeriod; // in months
    private double powerConsumption; // in watts

    public Electronics(String productId, String name, double basePrice, String category, 
                      int warrantyPeriod, double powerConsumption) {
        super(productId, name, basePrice, category);
        validateWarrantyPeriod(warrantyPeriod);
        validatePowerConsumption(powerConsumption);

        this.warrantyPeriod = warrantyPeriod;
        this.powerConsumption = powerConsumption;
    }

    @Override
    public double calculateDiscount() {
        // Electronics get 10% discount for items over $500, 5% for items over $200
        if (basePrice > 500) {
            return roundAmount(basePrice * 0.10);
        } else if (basePrice > 200) {
            return roundAmount(basePrice * 0.05);
        }
        return 0.0;
    }

    @Override
    public String getProductType() {
        return "Electronics";
    }

    @Override
    protected void displaySpecificInfo() {
        System.out.printf("║ %-20s: %-20d ║%n", "Warranty (months)", warrantyPeriod);
        System.out.printf("║ %-20s: %-20.1f ║%n", "Power (watts)", powerConsumption);
    }

    private void validateWarrantyPeriod(int warranty) {
        if (warranty < 0) {
            throw new InvalidProductDataException("Warranty period cannot be negative", "warrantyPeriod", String.valueOf(warranty));
        }
        if (warranty > 120) { // Max 10 years
            throw new InvalidProductDataException("Warranty period cannot exceed 120 months", "warrantyPeriod", String.valueOf(warranty));
        }
    }

    private void validatePowerConsumption(double power) {
        if (power < 0) {
            throw new InvalidProductDataException("Power consumption cannot be negative", "powerConsumption", String.valueOf(power));
        }
        if (power > 10000) { // Reasonable upper limit
            throw new InvalidProductDataException("Power consumption seems unrealistic", "powerConsumption", String.valueOf(power));
        }
    }

    public int getWarrantyPeriod() { return warrantyPeriod; }
    public double getPowerConsumption() { return powerConsumption; }
}

// Clothing Class
class Clothing extends Product {
    private String size;
    private String material;
    private String color;

    public Clothing(String productId, String name, double basePrice, String category,
                   String size, String material, String color) {
        super(productId, name, basePrice, category);
        validateSize(size);
        validateMaterial(material);
        validateColor(color);

        this.size = size.trim().toUpperCase();
        this.material = material.trim();
        this.color = color.trim();
    }

    @Override
    public double calculateDiscount() {
        // Clothing gets 15% discount for items over $100, 8% for items over $50
        if (basePrice > 100) {
            return roundAmount(basePrice * 0.15);
        } else if (basePrice > 50) {
            return roundAmount(basePrice * 0.08);
        }
        return 0.0;
    }

    @Override
    public String getProductType() {
        return "Clothing";
    }

    @Override
    protected void displaySpecificInfo() {
        System.out.printf("║ %-20s: %-20s ║%n", "Size", size);
        System.out.printf("║ %-20s: %-20s ║%n", "Material", material);
        System.out.printf("║ %-20s: %-20s ║%n", "Color", color);
    }

    private void validateSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new InvalidProductDataException("Size cannot be null or empty", "size", size);
        }
        String[] validSizes = {"XS", "S", "M", "L", "XL", "XXL", "XXXL"};
        boolean validSize = false;
        for (String validSz : validSizes) {
            if (validSz.equalsIgnoreCase(size.trim())) {
                validSize = true;
                break;
            }
        }
        if (!validSize) {
            throw new InvalidProductDataException("Invalid size. Must be XS, S, M, L, XL, XXL, or XXXL", "size", size);
        }
    }

    private void validateMaterial(String material) {
        if (material == null || material.trim().isEmpty()) {
            throw new InvalidProductDataException("Material cannot be null or empty", "material", material);
        }
    }

    private void validateColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new InvalidProductDataException("Color cannot be null or empty", "color", color);
        }
    }

    public String getSize() { return size; }
    public String getMaterial() { return material; }
    public String getColor() { return color; }
}

// Books Class
class Books extends Product {
    private String author;
    private String publisher;
    private String isbn;
    private static final Pattern ISBN_PATTERN = Pattern.compile("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$");

    public Books(String productId, String name, double basePrice, String category,
                String author, String publisher, String isbn) {
        super(productId, name, basePrice, category);
        validateAuthor(author);
        validatePublisher(publisher);
        validateISBN(isbn);

        this.author = author.trim();
        this.publisher = publisher.trim();
        this.isbn = isbn.trim();
    }

    @Override
    public double calculateDiscount() {
        // Books get 12% discount for items over $30, 6% for items over $15
        if (basePrice > 30) {
            return roundAmount(basePrice * 0.12);
        } else if (basePrice > 15) {
            return roundAmount(basePrice * 0.06);
        }
        return 0.0;
    }

    @Override
    public String getProductType() {
        return "Books";
    }

    @Override
    protected void displaySpecificInfo() {
        System.out.printf("║ %-20s: %-20s ║%n", "Author", author);
        System.out.printf("║ %-20s: %-20s ║%n", "Publisher", publisher);
        System.out.printf("║ %-20s: %-20s ║%n", "ISBN", isbn);
    }

    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new InvalidProductDataException("Author cannot be null or empty", "author", author);
        }
    }

    private void validatePublisher(String publisher) {
        if (publisher == null || publisher.trim().isEmpty()) {
            throw new InvalidProductDataException("Publisher cannot be null or empty", "publisher", publisher);
        }
    }

    private void validateISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new InvalidProductDataException("ISBN cannot be null or empty", "isbn", isbn);
        }
        if (!ISBN_PATTERN.matcher(isbn.trim()).matches()) {
            throw new InvalidProductDataException("Invalid ISBN format", "isbn", isbn);
        }
    }

    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public String getISBN() { return isbn; }
}

// Inventory Management System
class InventoryManager {
    private Map<String, Product> products;
    private Map<String, Integer> stockLevels;

    public InventoryManager() {
        this.products = new HashMap<>();
        this.stockLevels = new HashMap<>();
    }

    public void addProduct(Product product, int initialStock) throws InvalidProductDataException {
        if (product == null) {
            throw new InvalidProductDataException("Product cannot be null", "product", "null");
        }
        if (initialStock < 0) {
            throw new InvalidProductDataException("Initial stock cannot be negative", "initialStock", String.valueOf(initialStock));
        }

        products.put(product.getProductId(), product);
        stockLevels.put(product.getProductId(), initialStock);

        System.out.printf("✓ Added product %s with stock level %d%n", product.getProductId(), initialStock);
    }

    public Product getProduct(String productId) throws ProductNotFoundException {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found", productId);
        }
        return product;
    }

    public int getStockLevel(String productId) throws ProductNotFoundException {
        if (!stockLevels.containsKey(productId)) {
            throw new ProductNotFoundException("Product not found in inventory", productId);
        }
        return stockLevels.get(productId);
    }

    public void updateStock(String productId, int quantity) throws ProductNotFoundException, InvalidProductDataException {
        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product not found", productId);
        }

        int currentStock = stockLevels.get(productId);
        int newStock = currentStock + quantity;

        if (newStock < 0) {
            throw new InvalidProductDataException("Stock cannot go below zero", "quantity", String.valueOf(quantity));
        }

        stockLevels.put(productId, newStock);
        System.out.printf("✓ Updated stock for %s: %d -> %d%n", productId, currentStock, newStock);
    }

    public void purchaseProduct(String productId, int quantity) 
            throws ProductNotFoundException, OutOfStockException, InvalidProductDataException {

        if (quantity <= 0) {
            throw new InvalidProductDataException("Purchase quantity must be positive", "quantity", String.valueOf(quantity));
        }

        Product product = getProduct(productId);
        int currentStock = getStockLevel(productId);

        if (currentStock < quantity) {
            throw new OutOfStockException("Insufficient stock for purchase", productId, quantity, currentStock);
        }

        stockLevels.put(productId, currentStock - quantity);

        double totalPrice = product.applyTax() * quantity;
        System.out.printf("✓ Purchase successful: %d x %s = $%.2f%n", 
                         quantity, product.getName(), totalPrice);
        System.out.printf("  Remaining stock: %d%n", stockLevels.get(productId));
    }

    public void displayInventory() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    INVENTORY SUMMARY                    ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-15s ║ %-20s ║ %-10s ║ %-8s ║%n", "Product ID", "Name", "Type", "Stock");
        System.out.println("╠═════════════════╬══════════════════════╬════════════╬══════════╣");

        if (products.isEmpty()) {
            System.out.println("║                     No products in inventory                      ║");
        } else {
            for (Product product : products.values()) {
                String productId = product.getProductId();
                int stock = stockLevels.get(productId);
                System.out.printf("║ %-15s ║ %-20s ║ %-10s ║ %8d ║%n", 
                                productId, product.getName(), product.getProductType(), stock);
            }
        }

        System.out.println("╚═════════════════╩══════════════════════╩════════════╩══════════╝");
    }
}

public class E_CommerceSystem {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("           E-COMMERCE PRODUCT MANAGEMENT SYSTEM");
        System.out.println("        Comprehensive OOP & Exception Handling Demo");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        InventoryManager inventory = new InventoryManager();

        // Test 1: Successful Product Creation and Inventory Management
        testSuccessfulOperations(inventory);

        // Test 2: Product Data Validation Exceptions
        testProductValidationExceptions();

        // Test 3: Inventory Management Exceptions
        testInventoryExceptions(inventory);

        // Test 4: Out of Stock Scenarios
        testOutOfStockScenarios(inventory);

        // Test 5: Product Information Display
        testProductDisplays(inventory);

        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("                    DEMONSTRATION COMPLETE");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }

    private static void testSuccessfulOperations(InventoryManager inventory) {
        System.out.println("1. SUCCESSFUL OPERATIONS TEST");
        System.out.println("─────────────────────────────────────────────────────────────");

        try {
            // Create products
            Electronics laptop = new Electronics("ELC001", "Gaming Laptop", 1299.99, "Computers", 24, 150.0);
            Clothing shirt = new Clothing("CLT001", "Cotton T-Shirt", 29.99, "Apparel", "M", "100% Cotton", "Blue");
            Books book = new Books("BKS001", "Java Programming", 45.99, "Education", "John Doe", "TechBooks Inc", "978-0123456789");

            // Add to inventory
            inventory.addProduct(laptop, 10);
            inventory.addProduct(shirt, 50);
            inventory.addProduct(book, 25);

            System.out.println("\n✓ All products added successfully!");

        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
        }

        System.out.println("─────────────────────────────────────────────────────────────\n");
    }

    private static void testProductValidationExceptions() {
        System.out.println("2. PRODUCT VALIDATION EXCEPTIONS TEST");
        System.out.println("─────────────────────────────────────────────────────────────");

        // Test invalid product ID
        System.out.println("Testing empty product ID...");
        try {
            new Electronics("", "Invalid Product", 100.0, "Electronics", 12, 50.0);
        } catch (InvalidProductDataException e) {
            System.err.println("❌ Caught: " + e.getMessage() + "\n");
        }

        // Test invalid price
        System.out.println("Testing negative price...");
        try {
            new Clothing("CLT999", "Invalid Shirt", -25.0, "Apparel", "L", "Cotton", "Red");
        } catch (InvalidProductDataException e) {
            System.err.println("❌ Caught: " + e.toString() + "\n");
        }

        // Test invalid clothing size
        System.out.println("Testing invalid clothing size...");
        try {
            new Clothing("CLT998", "Invalid Size Shirt", 30.0, "Apparel", "HUGE", "Cotton", "Green");
        } catch (InvalidProductDataException e) {
            System.err.println("❌ Caught: " + e.getMessage() + "\n");
        }

        // Test invalid ISBN
        System.out.println("Testing invalid ISBN...");
        try {
            new Books("BKS999", "Invalid Book", 25.0, "Education", "Author", "Publisher", "invalid-isbn");
        } catch (InvalidProductDataException e) {
            System.err.println("❌ Caught: " + e.toString() + "\n");
        }

        System.out.println("─────────────────────────────────────────────────────────────\n");
    }

    private static void testInventoryExceptions(InventoryManager inventory) {
        System.out.println("3. INVENTORY MANAGEMENT EXCEPTIONS TEST");
        System.out.println("─────────────────────────────────────────────────────────────");

        // Test adding null product
        System.out.println("Testing null product addition...");
        try {
            inventory.addProduct(null, 10);
        } catch (InvalidProductDataException e) {
            System.err.println("❌ Caught: " + e.getMessage() + "\n");
        }

        // Test negative initial stock
        System.out.println("Testing negative initial stock...");
        try {
            Electronics testProduct = new Electronics("ELC999", "Test Product", 100.0, "Electronics", 12, 50.0);
            inventory.addProduct(testProduct, -5);
        } catch (InvalidProductDataException e) {
            System.err.println("❌ Caught: " + e.toString() + "\n");
        } catch (Exception e) {
            System.err.println("❌ Other error: " + e.getMessage() + "\n");
        }

        // Test getting non-existent product
        System.out.println("Testing non-existent product retrieval...");
        try {
            inventory.getProduct("NONEXISTENT");
        } catch (ProductNotFoundException e) {
            System.err.println("❌ Caught: " + e.toString() + "\n");
        }

        System.out.println("─────────────────────────────────────────────────────────────\n");
    }

    private static void testOutOfStockScenarios(InventoryManager inventory) {
        System.out.println("4. OUT OF STOCK SCENARIOS TEST");
        System.out.println("─────────────────────────────────────────────────────────────");

        try {
            // Try to purchase more than available stock
            System.out.println("Testing purchase of 15 laptops (only 10 available)...");
            try {
                inventory.purchaseProduct("ELC001", 15);
            } catch (OutOfStockException e) {
                System.err.println("❌ Caught OutOfStockException: " + e.getMessage());
                System.err.printf("   Product: %s, Requested: %d, Available: %d%n%n", 
                                e.getProductId(), e.getRequestedQuantity(), e.getAvailableQuantity());
            }

            // Test successful purchase
            System.out.println("Testing successful purchase of 3 laptops...");
            inventory.purchaseProduct("ELC001", 3);
            System.out.println();

            // Test invalid purchase quantity
            System.out.println("Testing invalid purchase quantity...");
            try {
                inventory.purchaseProduct("ELC001", -2);
            } catch (InvalidProductDataException e) {
                System.err.println("❌ Caught: " + e.getMessage() + "\n");
            }

        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage() + "\n");
        }

        System.out.println("─────────────────────────────────────────────────────────────\n");
    }

    private static void testProductDisplays(InventoryManager inventory) {
        System.out.println("5. PRODUCT INFORMATION DISPLAY TEST");
        System.out.println("─────────────────────────────────────────────────────────────");

        try {
            // Display individual product information
            System.out.println("Electronics Product Information:");
            Product laptop = inventory.getProduct("ELC001");
            laptop.displayProductInfo();
            System.out.println();

            System.out.println("Clothing Product Information:");
            Product shirt = inventory.getProduct("CLT001");
            shirt.displayProductInfo();
            System.out.println();

            System.out.println("Books Product Information:");
            Product book = inventory.getProduct("BKS001");
            book.displayProductInfo();
            System.out.println();

            // Display full inventory
            inventory.displayInventory();

        } catch (Exception e) {
            System.err.println("❌ Error displaying products: " + e.getMessage());
        }

        System.out.println("\n─────────────────────────────────────────────────────────────\n");
    }
}
