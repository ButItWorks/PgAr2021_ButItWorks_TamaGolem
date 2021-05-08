import java.util.*;

public class TamaGolemMain {

    public static void main(String[] args) {
        List<Elemento> elementi = new ArrayList<>();
        elementi.add(new Elemento("fuoco"));
        elementi.add(new Elemento("acqua"));
        elementi.add(new Elemento("erba"));
        elementi.add(new Elemento("aria"));
        elementi.add(new Elemento("tuono"));
        //elementi.add(new Elemento("sabbia"));
        //elementi.add(new Elemento("acciaio"));
        Equilibrio equilibrio = new Equilibrio(elementi, 10);

        for (Elemento el : equilibrio.getTotaleElementi()) {
            System.out.println("**** " + el.getNomeElemento() + " ****");
            for(Iterazione it : equilibrio.getIterazioniElemento(el)) {
                System.out.println(it.toString());
            }
        }

    }

}
