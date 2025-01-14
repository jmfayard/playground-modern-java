package modernjava;

sealed interface Shape permits Circle, Rectangle, Triangle {
    double area();
}

// Sous-classe autorisée en tant que record
record Circle(double radius) implements Shape {
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// Sous-classe autorisée en tant que record
record Rectangle(double width, double height) implements Shape {
    @Override
    public double area() {
        return width * height;
    }
}

// Sous-classe autorisée en tant que record
record Triangle(double base, double height) implements Shape {
    @Override
    public double area() {
        return 0.5 * base * height;
    }
}