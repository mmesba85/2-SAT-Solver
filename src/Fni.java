import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Class Fni
 * Implemente une forme normale implicative
 * @author Mesbahi Maroua
 */
public class Fni {

    /**
     * Liste de clause
     */
    private ArrayList<Clause> fni;

    /**
     * Construit deux fni a partir d'une fnc
     * @param fnc
     */
    public Fni(Fnc fnc) {
        fni = new ArrayList<>();
        ListIterator<Clause> it = fnc.getFnc().listIterator();
        while (it.hasNext()) {
            Clause c = it.next();
            int a = c.getA().getId();
            int b = c.getB().getId();
            boolean neg_a = !(c.getA().isNeg());
            boolean neg_b = !(c.getB().isNeg());
            fni.add(new Clause(new Variable(a, neg_a), new Variable(b, c.getB().isNeg())));
            fni.add(new Clause(new Variable(b, neg_b), new Variable(a, c.getA().isNeg())));
        }
    }

    /**
     * Getter
     * @return Liste de clause
     */
    public ArrayList<Clause> getFni() {
        return fni;
    }
}
