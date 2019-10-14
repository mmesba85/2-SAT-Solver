public class Main {
    public static void main(String[] args) {
        try {
            Fnc f = new Fnc(args[0]);
            Graph g = new Graph(f);
            g.buildCC();
            boolean sol = g.isSolvable();
            if (!sol)
                System.out.println("UNSAT");
            else {
                g.solve();
                g.printSol();
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
