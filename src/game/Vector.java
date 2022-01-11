package game;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector vector){
        this.x = vector.x;
        this.y = vector.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals (Vector other) {
        return x == other.x && y == other.y;
    }

    public Vector sub(Vector other) {
        return new Vector(x - other.getX(), y - other.getY());
    }

    public Vector add(Vector other) {
        return new Vector(x + other.getX(), y + other.getY());
    }

    public boolean colinear(Vector other) {
        return x * other.getY() == y * other.getX();
    }

    public boolean sameDirection(Vector other) {
        return ((x <= 0 && other.getX() <= 0) || (x > 0 && other.getX() > 0))
                && ((y <= 0 && other.getY() <= 0) || (y > 0 && other.getY() > 0));
    }

    public double norm() {
        return Math.sqrt(x*x + y*y);
    }
}
