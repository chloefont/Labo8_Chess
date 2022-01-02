package game;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
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
}
