import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Battaglia {

    private static HashMap<Elemento, Integer> pietreTotali = new HashMap<>();
    private static int pietrePerGolem;
    private static Equilibrio equilibrio;

    public static void variabiliDiConfigurazione(HashMap<Elemento, Integer> scortaPietre, int numeroPietrePerGolem, Equilibrio equilibrioBattaglia) {
        pietreTotali = scortaPietre;
        pietrePerGolem = numeroPietrePerGolem;
        equilibrio = equilibrioBattaglia;
    }

    /**
     * metogo che gestisce tutto il match
     * @param g1 giocatore 1 e relativa squadra
     * @param g2 giocatore 1 e relativa squadra
     */
    public static void scontro(Giocatore g1, Giocatore g2) {
        System.out.println("****** INIZIO SCONTRO ******");
        boolean isBattagliaTerminata = false;
        Giocatore vincitore = new Giocatore();
        while(!isBattagliaTerminata) {
            if(!g1.getTamaGolemInCampo().isMorto() && !g2.getTamaGolemInCampo().isMorto()) {
                evocazione(g1);
                evocazione(g2);
            }
            else if(g1.getTamaGolemInCampo().isMorto()) {
                if(g1.isSquadraEsausta()) {
                    isBattagliaTerminata = true;
                    vincitore = g2;
                } else {
                    System.out.println("****** GIOCATORE " + g1.getNome() + " ******");
                    System.out.println("**** GOLEM " + g1.getTamaGolemInCampo().getNome() + " E' MORTO ****");
                    TamaGolem golemDaEvocare = new TamaGolem();
                    String nomeGolemDaEvocare = "";

                    do {
                        nomeGolemDaEvocare = InputDati.leggiStringa("Inserire il nome del golem da evocare: ");
                        golemDaEvocare = new TamaGolem(nomeGolemDaEvocare);
                        if(!g1.getSquadra().contains(golemDaEvocare)) {
                            System.out.println("Non esiste un golem con questo nome...riprovare!");
                        } else {
                            for (TamaGolem golem : g1.getSquadra()) {
                                if(golem.equals(golemDaEvocare)) {
                                    golemDaEvocare = golem;
                                }
                            }
                        }
                    } while (!g1.getSquadra().contains(golemDaEvocare) || golemDaEvocare.isMorto());
                    g1.setTamaGolemInCampo(golemDaEvocare);
                    evocazione(g1);
                }
            }
            else {
                if(g2.getTamaGolemInCampo().isMorto()) {
                    if(g1.isSquadraEsausta()) {
                        isBattagliaTerminata = true;
                        vincitore = g1;
                    } else {
                        System.out.println("****** GIOCATORE " + g2.getNome() + " ******");
                        System.out.println("**** GOLEM " + g2.getTamaGolemInCampo().getNome() + " E' MORTO ****");
                        TamaGolem golemDaEvocare = new TamaGolem();
                        String nomeGolemDaEvocare = "";

                        do {
                            nomeGolemDaEvocare = InputDati.leggiStringa("Inserire il nome del golem da evocare: ");
                            golemDaEvocare = new TamaGolem(nomeGolemDaEvocare);
                            if (!g2.getSquadra().contains(golemDaEvocare)) {
                                System.out.println("Non esiste un golem con questo nome...riprovare!");
                            } else {
                                for (TamaGolem golem : g2.getSquadra()) {
                                    if (golem.equals(golemDaEvocare)) {
                                        golemDaEvocare = golem;
                                    }
                                }
                            }
                        } while (!g2.getSquadra().contains(golemDaEvocare) || golemDaEvocare.isMorto());
                        g2.setTamaGolemInCampo(golemDaEvocare);
                        evocazione(g2);
                    }
                }
            }

            if(!isBattagliaTerminata) {
                scontroTamagolem(g1.getTamaGolemInCampo(), g2.getTamaGolemInCampo());
            }
        }
        System.out.println("Lo scontro e' terminato, ed il vincitore e'........................" + vincitore.getNome().toUpperCase());
    }

    /**
     * metodo per stabilire l'iterazione fra i due elementi e la loro potenza
     * @param iterazioniElemento
     * @param elementoAvversario
     * @return
     */
    private static int potenzaIterazione(Set<Iterazione> iterazioniElemento, Elemento elementoAvversario) {
        for (Iterazione iterazione : iterazioniElemento) {
            if(iterazione.getSecondoNodo().equals(elementoAvversario)) {
                return iterazione.getPotenzaIterazione();
            }
        }
        return 0;
    }

    /**
     * metodo che gestisce lo scontro
     * @param primo TamaGolem
     * @param secondo TamaGolem
     */
    public static void scontroTamagolem(TamaGolem primo, TamaGolem secondo) {
        while(!primo.isMorto() && !secondo.isMorto()) {
            Elemento pietraPrimo = primo.getPietraAttuale();
            Elemento pietraSecondo = secondo.getPietraAttuale();
            Set<Iterazione> iterazioniPrimoElemento = equilibrio.getIterazioniElemento(pietraPrimo);
            Set<Iterazione> iterazioniSecondoElemento = equilibrio.getIterazioniElemento(pietraSecondo);

            if(potenzaIterazione(iterazioniPrimoElemento, pietraSecondo) != 0) {
                int potenzaIterazione = potenzaIterazione(iterazioniPrimoElemento, pietraSecondo);
                secondo.infliggiDanno(potenzaIterazione);
                if(secondo.isMorto()) {
                    System.out.println(secondo.getNome() + " e' morto");
                } else {
                    System.out.println(pietraPrimo.getNomeElemento() + " vince sul " + pietraSecondo.getNomeElemento() + "  =>  " + primo.getNome() + " infligge " + potenzaIterazione + " a " + secondo.getNome());
                    //InputDati.leggiStringa("Premere invio per continuare...");
                }
            } else {
                int potenzaIterazione = potenzaIterazione(iterazioniSecondoElemento, pietraPrimo);
                primo.infliggiDanno(potenzaIterazione);
                if(primo.isMorto()) {
                    System.out.println(primo.getNome() + " e' morto");
                } else {
                    System.out.println(pietraSecondo.getNomeElemento() + " vince sul " + pietraPrimo.getNomeElemento() + "  =>  " + secondo.getNome() + " infligge " + potenzaIterazione + " a " + primo.getNome());
                    //InputDati.leggiStringa("Premere invio per continuare...");
                }
            }
            primo.giraPietre();
            secondo.giraPietre();
        }
    }

    /**
     * metodo per mostrare al giocatore le pietre nella riserva, che quindi può scegliere
     */
    public static void stampaPietreTotali() {
        System.out.println("****** PIETRE NELLA SCORTA ******");
        int counter = 1;
        for (Elemento tipoPietra : pietreTotali.keySet()) {
            System.out.println(counter + ") Tipo Pietra: " + tipoPietra.getNomeElemento() + " => " + pietreTotali.get(tipoPietra));
            counter++;
        }
        System.out.println("********************************");
    }

    /**
     * metodo che permette al giocatore di scegliere le pietre da far mangiare al tamagolem
     * @param pietreGolem
     */
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

    /**
     * metodo che gestisce l'evocazione del TamaGolem
     * @param giocatore
     */
    public static void evocazione(Giocatore giocatore) {
        TamaGolem golemDaEvocare = giocatore.getTamaGolemInCampo();
        System.out.println("********* " + golemDaEvocare.getNome() + " *********");
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
