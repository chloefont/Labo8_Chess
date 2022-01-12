package game;

public class Vector {
    public static final Vector LEFT = new Vector(-1, 0);
    public static final Vector RIGHT = new Vector(1, 0);
    public static final Vector UP = new Vector(0, 1);
    public static final Vector DOWN = new Vector(0, -1);

    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector vector){
        this(vector.x, vector.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // TODO faire la fonction pour le hash aussi non ?
    /**
     * Permet de vérifier si deux vecteurs sont égaux.
     * @param other L'autre Vecteur.
     * @return Vrai s'ils sont égaux.
     */
    public boolean equals (Vector other) {
        return x == other.x && y == other.y;
    }

    /**
     * Permet de soustraire un vecteur à un autre.
     * @param other L'autre Vecteur.
     * @return Un nouveau vecteur.
     */
    public Vector sub(Vector other) {
        return new Vector(x - other.getX(), y - other.getY());
    }

    /**
     * Permet d'additionner un vecteur à un autre.
     * @param other L'autre Vecteur.
     * @return Un nouveau vecteur.
     */
    public Vector add(Vector other) {
        return new Vector(x + other.getX(), y + other.getY());
    }

    /**
     * Permet de multiplier un vecteur par un scalaire.
     * @param by un scalaire.
     * @return Un nouveau vecteur.
     */
    public Vector mult(int by){
        return new Vector(x * by, y * by);
    }

    /**
     * Permet de savoir si un vecteur est colinéaire à celui-ci.
     * @param other L'autre vecteur
     * @return Vrai si colinéaire.
     */
    public boolean colinear(Vector other) {
        return x * other.getY() == y * other.getX();
    }

    /**
     * Permet de savoir si 2 vecteurs vont dans la même direction.
     * @param other L'autre vecteur.
     * @return Vrai si même direction.
     */
    public boolean sameDirection(Vector other) {
        return ((x <= 0 && other.getX() <= 0) || (x > 0 && other.getX() > 0))
                && ((y <= 0 && other.getY() <= 0) || (y > 0 && other.getY() > 0));
    }

    /**
     * Permet de récupérer la taille d'un vecteur.
     * @return La taille.
     */
    public double norm() {
        return Math.sqrt(x*x + y*y);
    }
}
