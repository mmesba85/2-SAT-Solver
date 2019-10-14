import java.util.ArrayList;

/**
 * Class Component
 * Implemente une composante fortement connexe
 * @author Mesbahi Maroua
 */
public class Component {

    /**
     * Liste de sommets de la cc
     */
    private ArrayList<Vertex> list;

    /**
     * Si la composante a ete valuee
     */
    private boolean evaluated = false;

    /**
     * Constructeur vide
     */
    public Component() {
        list = new ArrayList<>();
    }

    /**
     * Ajoute un sommet a la cc
     * @param v sommet
     */
    public void addVertex(Vertex v) {
        list.add(v);
    }

    /**
     * Getter
     * @return liste de sommets de la cc
     */
    public ArrayList<Vertex> getList() {
        return list;
    }

    /**
     * Getter
     * @return evaluated
     */
    public boolean isEvaluated() {
        return evaluated;
    }

    /**
     * Setter
     * @param evaluated
     */
    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }
}
