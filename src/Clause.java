
/**
 * Class Clause
 * Implemente une clause
 */
public class Clause {

    /**
     * Premiere variable de la clause
     */
    private Variable a;

    /**
     * Seconde variable de la clause
     */
    private Variable b;

    /**
     * Construit une clause a partir de deux variables
     * @param a
     * @param b
     */
    public Clause(Variable a, Variable b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Getter
     * @return La premiere variable
     */
    public Variable getA() {
        return a;
    }

    /**
     * Getter
     * @return La seconde variable
     */
    public Variable getB() {
        return b;
    }
}
