package Random_Assignments;

import java.util.Scanner;

// Base Class: Shape (Abstract)
abstract class Shape {
    private String color;
    private boolean filled;

    // Constructor
    public Shape(String color, boolean filled) {
        setColor(color);
        setFilled(filled);
    }

    // Getters
    public String getColor() {
        return color;
    }

    public boolean isFilled() {
        return filled;
    }

    // Setters with validation
    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        this.color = color;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    // Abstract methods
    public abstract double getArea();
    public abstract double getPerimeter();

    // Concrete toString method
    @Override
    public String toString() {
        return "Shape: " + this.getClass().getSimpleName() +
                "\nColor: " + color +
                "\nFilled: " + (filled ? "Yes" : "No") +
                "\nArea: " + String.format("%.2f", getArea()) +
                "\nPerimeter: " + String.format("%.2f", getPerimeter());
    }
}

// Child Class: Circle
class Circle extends Shape {
    private double radius;

    public Circle(String color, boolean filled, double radius) {
        super(color, filled);
        setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRadius: " + String.format("%.2f", radius);
    }
}

// Child Class: Rectangle
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, boolean filled, double width, double height) {
        super(color, filled);
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nWidth: " + String.format("%.2f", width) +
                "\nHeight: " + String.format("%.2f", height);
    }
}

// Child Class: Triangle
class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(String color, boolean filled, double base, double height) {
        super(color, filled);
        setBase(base);
        setHeight(height);
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public void setBase(double base) {
        if (base <= 0) {
            throw new IllegalArgumentException("Base must be positive");
        }
        this.base = base;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    @Override
    public double getPerimeter() {
        // Assuming right triangle for simplicity
        double hypotenuse = Math.sqrt(base * base + height * height);
        return base + height + hypotenuse;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nBase: " + String.format("%.2f", base) +
                "\nHeight: " + String.format("%.2f", height);
    }
}

public class shape_polymorphsim {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Create Circle
            System.out.print("Enter circle color: ");
            String circleColor = scanner.nextLine();
            System.out.print("Is circle filled? (true/false): ");
            boolean circleFilled = scanner.nextBoolean();
            System.out.print("Enter circle radius: ");
            double radius = scanner.nextDouble();
            scanner.nextLine();

            Circle circle = new Circle(circleColor, circleFilled, radius);
            System.out.println("\n" + circle);

            // Create Rectangle
            System.out.print("\nEnter rectangle color: ");
            String rectColor = scanner.nextLine();
            System.out.print("Is rectangle filled? (true/false): ");
            boolean rectFilled = scanner.nextBoolean();
            System.out.print("Enter rectangle width: ");
            double width = scanner.nextDouble();
            System.out.print("Enter rectangle height: ");
            double height = scanner.nextDouble();
            scanner.nextLine();

            Rectangle rectangle = new Rectangle(rectColor, rectFilled, width, height);
            System.out.println("\n" + rectangle);

            // Create Triangle
            System.out.print("\nEnter triangle color: ");
            String triColor = scanner.nextLine();
            System.out.print("Is triangle filled? (true/false): ");
            boolean triFilled = scanner.nextBoolean();
            System.out.print("Enter triangle base: ");
            double base = scanner.nextDouble();
            System.out.print("Enter triangle height: ");
            double triHeight = scanner.nextDouble();

            Triangle triangle = new Triangle(triColor, triFilled, base, triHeight);
            System.out.println("\n" + triangle);

            // Demonstrate Polymorphism
            System.out.println("\n=== Polymorphism Demo ===");
            Shape[] shapes = {circle, rectangle, triangle};

            double totalArea = 0;
            double totalPerimeter = 0;

            for (Shape shape : shapes) {
                System.out.println("\n" + shape.getClass().getSimpleName() + ":");
                System.out.println("Area: " + String.format("%.2f", shape.getArea()));
                System.out.println("Perimeter: " + String.format("%.2f", shape.getPerimeter()));

                totalArea += shape.getArea();
                totalPerimeter += shape.getPerimeter();
            }

            System.out.println("\n=== Summary ===");
            System.out.println("Total Area of all shapes: " + String.format("%.2f", totalArea));
            System.out.println("Total Perimeter of all shapes: " + String.format("%.2f", totalPerimeter));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}