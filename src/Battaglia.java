import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.HashMap;

public class Battaglia {

    public static HashMap<Elemento, Integer> pietreTotali = new HashMap<>();
    private static int pietrePerGolem;

    public static void variabiliDiConfigurazione(HashMap<Elemento, Integer> scortaPietre, int numeroPietrePerGolem) {
        pietreTotali = scortaPietre;
        pietrePerGolem = numeroPietrePerGolem;
    }

    public static void stampaPietreTotali() {
        System.out.println("****** PIETRE NELLA SCORTA ******");
        int counter = 1;
        for (Elemento tipoPietra : pietreTotali.keySet()) {
            System.out.println(counter + ") Tipo Pietra: " + tipoPietra.getNomeElemento() + " => " + pietreTotali.get(tipoPietra));
            counter++;
        }
        System.out.println("********************************");
    }

    private static void sceltaPietra(ArrayList<Elemento> pietreGolem)
    {
        String elementoPietra = "";
        Elemento pietra;

        do {
            elementoPietra = InputDati.leggiStringa("Inserire pietra da fare ingurgitare: ");
            pietra = new Elemento(elementoPietra);
            if(!pietreTotali.containsKey(pietra) || pietreTotali.get(pietra) == 0) {
                System.out.println("Pietra non valida...riprovare!");
            }
        } while (!pietreTotali.containsKey(pietra) || pietreTotali.get(pietra) == 0);

        pietreTotali.put(pietra, pietreTotali.get(pietra) - 1);

        pietreGolem.add(pietra);
    }

    public static void evocazione(Giocatore giocatore) {
        TamaGolem golemDaEvocare = giocatore.getTamaGolemInCampo();
        ArrayList<Elemento> pietreGolem = new ArrayList<>();
        int pietreAssegnate = 0;

        while(pietreAssegnate < pietrePerGolem) {
            stampaPietreTotali();
            sceltaPietra(pietreGolem);
            pietreAssegnate++;
        }

        golemDaEvocare.setPietre(pietreGolem);
    }

}
