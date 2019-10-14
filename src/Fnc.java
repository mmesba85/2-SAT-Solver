import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Class Fnc
 * Implemente une Forme Normale Conjonctive
 * @author Mesbahi Maroua
 */
public class Fnc {

    /**
     * Liste de clauses
     */
    private ArrayList<Clause> fnc;

    /**
     * Construit une fnc a partir du fichier d'entree
     * @param filename
     */
    public Fnc(String filename) {
        try {
            fnc = new ArrayList<>();
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] tab = line.split(" ");
            if (tab.length != 4)
                throw new IllegalArgumentException();
            while ((line = br.readLine()) != null) {
                tab = line.split(" ");
                if (tab.length != 3)
                    throw new IllegalArgumentException();
                boolean neg_a = false;
                boolean neg_b = false;
                int a = Integer.valueOf(tab[0]);
                int b = Integer.valueOf(tab[1]);
                if (a == 0 || b == 0 || Integer.valueOf(tab[2]) != 0)
                    throw new IllegalArgumentException();
                if (a < 0)
                    neg_a = true;
                if (b < 0)
                    neg_b = true;
                fnc.add(new Clause(new Variable(a, neg_a), new Variable(b, neg_b)));
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Getter
     * @return liste de clause
     */
    public ArrayList<Clause> getFnc() {
        return fnc;
    }

}
