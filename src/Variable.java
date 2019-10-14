/**
 * Class Variable
 * @author Mesbahi Maroua
 */
public class Variable {

    /**
     * Id de la variable comme donnee dans le fichier d'entree
     */
    private int id;

    /**
     * Si la variable est negative ou pas
     */
    private boolean neg = false;

    /**
     * Constructeur
     * @param id
     * @param neg_a
     */
    public Variable(int id, boolean neg_a) {
        this.id = Math.abs(id);
        this.neg = neg_a;
    }

    /**
     * Getter
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter
     * @return isNeg
     */
    public boolean isNeg() {
        return neg;
    }
}
