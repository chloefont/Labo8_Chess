package game;

import java.util.Objects;

public class Vector {
    public static final Vector LEFT = new Vector(-1, 0);
    public static final Vector RIGHT = new Vector(1, 0);
    public static final Vector UP = new Vector(0, 1);
    public static final Vector DOWN = new Vector(0, -1);

    private final int X;
    private final int Y;

    public Vector(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    //TODO peut pas vérifier si vector != null
    public Vector(Vector vector){
        this(vector.X, vector.Y);
    }

    /**
     * Permet de vérifier si deux vecteurs sont égaux.
     * @param other L'autre Vecteur.
     * @return Vrai s'ils sont égaux.
     */
    @Override
    public boolean equals(Object other) {
        return other == this || (other != null &&
                getClass() == other.getClass() && ((Vector)other).getX() == X && ((Vector)other).getY() == Y);
    }

    @Override
    public int hashCode(){
        return Objects.hash(X, Y);
    }

    /**
     * Permet de soustraire un vecteur à un autre.
     * @param other L'autre Vecteur.
     * @return Un nouveau vecteur.
     */
    public Vector sub(Vector other) {
        assert other != null;
        return new Vector(X - other.getX(), Y - other.getY());
    }

    /**
     * Permet d'additionner un vecteur à un autre.
     * @param other L'autre Vecteur.
     * @return Un nouveau vecteur.
     */
    public Vector add(Vector other) {
        assert other != null;
        return new Vector(X + other.getX(), Y + other.getY());
    }

    /**
     * Permet de multiplier un vecteur par un scalaire.
     * @param number un scalaire.
     * @return Un nouveau vecteur.
     */
    public Vector mult(int number){
        return new Vector(X * number, Y * number);
    }

    /**
     * Permet de savoir si un vecteur est colinéaire à celui-ci.
     * @param other L'autre vecteur
     * @return Vrai si colinéaire.
     */
    public boolean colinear(Vector other) {
        return other != null && X * other.getY() == Y * other.getX();
    }

    /**
     * Permet de savoir si 2 vecteurs vont dans la même direction.
     * @param other L'autre vecteur.
     * @return Vrai si même direction.
     */
    public boolean sameDirection(Vector other) {
        return other != null && ((X <= 0 && other.getX() <= 0) || (X > 0 && other.getX() > 0))
                && ((Y <= 0 && other.getY() <= 0) || (Y > 0 && other.getY() > 0));
    }

    /**
     * Permet de récupérer la taille d'un vecteur.
     * @return La taille.
     */
    public double norm() {
        return Math.sqrt(X * X + Y * Y);
    }

    // Getters et setters
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
