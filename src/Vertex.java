import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Class Vertex
 * Implemente un sommet du graphe
 * @author Mesbahi Maroua
 */
public class Vertex implements Comparable<Vertex>{

    /**
     * Variable assosiee au sommet
     */
    private Variable node;

    /**
     * Liste d'adjacence
     */
    private ArrayList<Vertex> adj;

    /**
     * Valeur qui dit si la valeur a ete definie
     */
    private boolean defined = false;

    /**
     * Valeur
     */
    private boolean value = false;

    /**
     * Si la variable a ete valuee
     */
    private boolean valuated = false;

    /**
     * Auxiliaire de l'algorithme de Tarjan
     */
    private int disc = -1;

    /**
     * Auxiliaire de l'algorithme de Tajan
     */
    private int low = -1;

    /**
     * Construit un sommet a partir d'une variable
     * @param node Variable associee au sommet
     */
    public Vertex(Variable node) {
        this.node = node;
        adj = new ArrayList<>();
    }

    /**
     * Getter
     * @return La variable associee
     */
    public Variable getNode() {
        return node;
    }

    /**
     * Getter
     * @return La liste d'adjacence
     */
    public ArrayList<Vertex> getAdj() {
        return adj;
    }

    /**
     * Ajoute un sommet a la liste d'adjacence
     * @param b Sommet
     */
    public void addNeighbour(Vertex b) {
        adj.add(b);
    }

    public void print() {
        ListIterator<Vertex> it = adj.listIterator();
        while (it.hasNext()) {
            Vertex a = it.next();
            if (node.isNeg())
                System.out.print("-");
            System.out.print(node.getId() + " -> ");
            if (a.getNode().isNeg())
                System.out.print("-");
            System.out.println(a.getNode().getId());
        }
    }

    @Override
    public int compareTo(Vertex o) {
        if (o.getNode().getId() == node.getId())
            return 0;
        return 1;
    }

    /**
     * Getter
     * @return disc
     */
    public int getDisc() {
        return disc;
    }

    /**
     * Getter
     * @return low
     */
    public int getLow() {
        return low;
    }

    /**
     * Setter
     * @param disc
     */
    public void setDisc(int disc) {
        this.disc = disc;
    }

    /**
     * Setter
     * @param low
     */
    public void setLow(int low) {
        this.low = low;
    }

    /**
     * Getter
     * @return defined
     */
    public boolean isDefined() {
        return defined;
    }

    /**
     * Setter
     * @param defined
     */
    public void setDefined(boolean defined) {
        this.defined = defined;
    }

    /**
     * Getter
     * @return valuated
     */
    public boolean isValuated() {
        return valuated;
    }

    /**
     * Setter
     * @param value
     */
    public void setValue(boolean value) {
        this.value = value;
    }

    /**
     * Setter
     * @param valuated
     */
    public void setValuated(boolean valuated) {
        this.valuated = valuated;
    }

    /**
     * Getter
     * @return value
     */
    public boolean getValue() {
        return value;
    }
}
