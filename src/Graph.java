import java.util.*;

/**
 * Class Graph
 * Modelise un graphe en utilisant une liste de sommets
 * Contient une liste de componentes qui contiendra par la suite
 * la liste des composantes fortement connexes
 * @author Mesbahi Maroua
 *
 */
public class Graph {

    /**
     * Liste de sommets du graphe
     */
    private ArrayList<Vertex> vertices;

    /**
     * Liste de composantes fortement connexe
     */
    ArrayList<Component> partition;

    /**
     * Auxiliaire de l'algorithme de Tarjan
     */
    static int time = 0;

    /**
     * Construit un graphe a partir d'une fnc
     * @param fnc
     */
    public Graph(Fnc fnc) {
        vertices = new ArrayList<>();
        partition = new ArrayList<>();
        Fni f = new Fni(fnc);
        ListIterator<Clause> it = f.getFni().listIterator();
        while (it.hasNext()) {
            Clause c = it.next();
            Variable a = c.getA();
            Variable b = c.getB();
            Vertex v = getVertex(a);
            Vertex u = getVertex(b);
            v.addNeighbour(u);
        }
    }

    /**
    * @param b objet variable
    * @return le sommet associee a la variable
     */
    private Vertex getVertex(Variable b) {
        ListIterator<Vertex> it = vertices.listIterator();
        while (it.hasNext()) {
            Vertex v = it.next();
            if (v.getNode().getId() == b.getId() &&
                    v.getNode().isNeg() == b.isNeg())
                return v;
        }
        Vertex v = new Vertex(b);
        vertices.add(v);
        return v;
    }

    public void print() {
        ListIterator<Vertex> it = vertices.listIterator();
        while (it.hasNext()) {
            Vertex v = it.next();
            v.print();
        }
    }
    /**
    * @return vrai si la fnc peut etre resolue, faux sinon
    * Se base sur le fait qu'une fnc a une solution ssi
    * il n'existe pas une variable et son opposee dans
    * la meme composante fortement connexe
     */
    public boolean isSolvable() {
        ListIterator<Component> it = partition.listIterator();
        while (it.hasNext()) {
            ArrayList<Vertex> l = it.next().getList();
            if (!checkCC(l))
                return false;
        }
        return true;
    }

    /**
     * @param l liste de sommets d'une composante fortement connexe
     * @return true s'il existe deux sommets opposee dans la liste
     * false sinon
     */
    private boolean checkCC(ArrayList<Vertex> l) {
        ListIterator<Vertex> it = l.listIterator();
        while (it.hasNext()) {
            Vertex v = it.next();
            ListIterator<Vertex> jt = l.listIterator();
            while (jt.hasNext()) {
                Vertex u = jt.next();
                if (u.getNode().getId() == v.getNode().getId() &&
                        u.getNode().isNeg() != v.getNode().isNeg())
                    return false;
            }
        }
        return true;
    }

    /**
     * fonction auxiliaire de l'algorithme de Tarjan
     * @param v sommet source
     * @param st pile contenant les sommets visitee
     */
    public void followVertex(Vertex v, Stack<Vertex> st) {
        v.setDisc(time);
        v.setLow(time);
        time++;
        st.push(v);

        ListIterator<Vertex> it = v.getAdj().listIterator();
        while (it.hasNext())
        {
            Vertex u = it.next();
            if (!u.isDefined())
            {
                u.setDefined(true);
                followVertex(u, st);
                v.setLow(Math.min(u.getLow(), v.getLow()));
            }
            else if (st.contains(u)) {
                v.setLow(Math.min(v.getLow(), u.getDisc()));
            }
        }

        if (v.getLow() == v.getDisc()) {
            Component cc = new Component();
            Vertex w = null;
            do {
                w = st.pop();
                cc.addVertex(w);
            } while (w != v);
            partition.add(cc);
        }
    }

    /**
     * Algorithme de Tarjan
     */
    public void buildCC() {
        Stack<Vertex> st = new Stack<>();
        ListIterator<Vertex> it = vertices.listIterator();

        while (it.hasNext()) {
            Vertex v = it.next();
            if (!v.isDefined()) {
                followVertex(v, st);
            }
        }
    }

    public void printCC() {
        ListIterator<Component> it = partition.listIterator();
        while (it.hasNext()) {
            ArrayList<Vertex> l = it.next().getList();
            ListIterator<Vertex> jt = l.listIterator();
            while (jt.hasNext()) {
                Vertex u = jt.next();
                if (u.getNode().isNeg())
                    System.out.print("-");
                System.out.print(u.getNode().getId() + " **");
            }
            System.out.println();
        }
    }

    /**
     * Resout l'expression,
     * Parcours la liste de composante fortement connexe dans
     * le sens inverse
     * Si, une composante n'a pas ete valuee, lui associde la valeur
     * True et la valeur False a sa complementaire
     * Sinon, ne fait rien
     */
    public void solve() {
        ListIterator<Component> it = partition.listIterator(partition.size());
        while (it.hasPrevious()) {
            Component c = it.previous();
            if (!c.isEvaluated()) {
                ArrayList<Vertex> l = c.getList();
                ListIterator<Vertex> jt = l.listIterator();
                while (jt.hasNext()) {
                    Vertex v = jt.next();
                    if (!v.isValuated()) {
                        v.setValue(true);
                        v.setValuated(true);
                        setOpposite(v);
                    }
                }
            }
            c.setEvaluated(true);
        }
    }

    /**
     * Associe la valeur False au sommet oppose
     * @param v sommet
     */
    private void setOpposite(Vertex v) {
        ListIterator<Vertex> it = vertices.listIterator();
        while (it.hasNext()) {
            Vertex u = it.next();
            if (u.getNode().getId() == v.getNode().getId() &&
                    u.getNode().isNeg() != v.getNode().isNeg() && !u.isValuated()) {
                u.setValue(false);
                u.setValuated(true);
            }
        }
    }

    /**
     * Affiche la solution
     */
    public void printSol() {
        ListIterator<Vertex> it = vertices.listIterator();
        System.out.print("SAT: ");
        while (it.hasNext()) {
            Vertex u = it.next();
            if (!u.getNode().isNeg()) {
                if (!u.getValue())
                    System.out.print("-");
               System.out.print(u.getNode().getId() + " ");
             }
        }
        System.out.println();
    }
}
