import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Battaglia {
    //COSTANTI
    private static final String MSG_INIZIO_SCONTRO = "************** INIZIO SCONTRO **************";
    private static final String MSG_STAMPA_PIETRA = "%d) %s => %s";
    private static final String MSG_GOLEM_MORTO = "il golem \"%s\" e' morto";
    private static final String MSG_DANNI = "%s vince sul %s  => %s infligge %d a %s";

    //ATTRIBUTI
    private Giocatore g1;
    private Giocatore g2;
    private HashMap<Elemento, Integer> scortaPietre = new HashMap<>();
    private Equilibrio equilibrioBattaglia;
    private int pietrePerGolem;

    private void stampaGolemRimanenti(Giocatore giocatore)
    {
        System.out.println(String.format("****** LISTA GOLEM RIMANENTI GIOCATORE %s ******", giocatore.getNome()));
        for (TamaGolem golem : giocatore.getSquadra()) {
            if(!golem.isMorto()) {
                System.out.println("=> " + golem.getNome());
            }
        }
        System.out.println("***********************************");
    }

    public Battaglia(Giocatore g1, Giocatore g2, HashMap<Elemento, Integer> scortaPietre, Equilibrio equilibrioBattaglia, int pietrePerGolem) {
        this.g1 = g1;
        this.g2 = g2;
        this.scortaPietre = scortaPietre;
        this.equilibrioBattaglia = equilibrioBattaglia;
        this.pietrePerGolem = pietrePerGolem;
    }

    public void scontro() {
        boolean isBattagliaTerminata = false;
        Giocatore vincitore = new Giocatore();

        System.out.println(MSG_INIZIO_SCONTRO);
        while(!isBattagliaTerminata) {
            if(!g1.getTamaGolemInCampo().isMorto() && !g2.getTamaGolemInCampo().isMorto()) {
                this.evocazione(g1, g1.getTamaGolemInCampo());
                this.evocazione(g2, g2.getTamaGolemInCampo());
            }
            else if(g1.getTamaGolemInCampo().isMorto()) {
                if(g1.isSquadraEsausta()) {
                    isBattagliaTerminata = true;
                    vincitore = g2;
                } else {
                    System.out.println(String.format("******** SOSTITUZIONE GOLEM GIOCATORE %s *******", g1.getNome()));
                    stampaGolemRimanenti(g1);
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
                    evocazione(g1, golemDaEvocare);
                }
            }
            else {
                if(g2.getTamaGolemInCampo().isMorto()) {
                    if(g2.isSquadraEsausta()) {
                        isBattagliaTerminata = true;
                        vincitore = g1;
                    } else {
                        System.out.println(String.format("******** SOSTITUZIONE GOLEM GIOCATORE %s *******", g2.getNome()));
                        stampaGolemRimanenti(g2);
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
                        evocazione(g2, golemDaEvocare);
                    }
                }
            }

            Utilities.clearScreen();

            if(!isBattagliaTerminata) {
                this.scontroTamaGolem(g1.getTamaGolemInCampo(), g2.getTamaGolemInCampo());
            }
        }
        System.out.println("Lo scontro e' terminato, ed il vincitore e'........................" + vincitore.getNome().toUpperCase());
    }

    private int potenzaIterazione(Set<Iterazione> iterazioniElemento, Elemento elementoAvversario) {
        for (Iterazione iterazione : iterazioniElemento) {
            if(iterazione.getSecondoNodo().equals(elementoAvversario)) {
                return iterazione.getPotenzaIterazione();
            }
        }
        return 0;
    }

    private void scontroTamaGolem(TamaGolem golem1, TamaGolem golem2) {
        String nomeGolem1 = golem1.getNome();
        String nomeGolem2 = golem2.getNome();

        while(!golem1.isMorto() && !golem2.isMorto()) {
            Elemento pietraGolem1 = golem1.getPietraAttuale();
            Elemento pietraGolem2 = golem2.getPietraAttuale();
            String elementoPietraGolem1 = pietraGolem1.getNomeElemento();
            String elementoPietraGolem2 = pietraGolem2.getNomeElemento();
            Set<Iterazione> iterazioniPrimoElemento = this.equilibrioBattaglia.getIterazioniElemento(pietraGolem1);
            Set<Iterazione> iterazioniSecondoElemento = this.equilibrioBattaglia.getIterazioniElemento(pietraGolem2);
            int potenzaIterazione = 0;

            if(pietraGolem1.equals(pietraGolem2)) {
                System.out.println("Le pietre scagliate sono equivalenti, nessuno subisce danni!");
            } else {
                if(potenzaIterazione(iterazioniPrimoElemento, pietraGolem2) != 0) {
                    potenzaIterazione = potenzaIterazione(iterazioniPrimoElemento, pietraGolem2);
                    golem2.infliggiDanno(potenzaIterazione);
                    if(golem2.isMorto()) {
                        System.out.println(String.format(MSG_GOLEM_MORTO, golem2.getNome()));
                    } else {
                        System.out.println(String.format(MSG_DANNI, elementoPietraGolem1, elementoPietraGolem2, nomeGolem1, potenzaIterazione, nomeGolem2));
                    }
                }
                else {
                    potenzaIterazione = potenzaIterazione(iterazioniSecondoElemento, pietraGolem1);
                    golem1.infliggiDanno(potenzaIterazione);
                    if(golem1.isMorto()) {
                        System.out.println(String.format(MSG_GOLEM_MORTO, golem1.getNome()));
                    } else {
                        System.out.println(String.format(MSG_DANNI, elementoPietraGolem2, elementoPietraGolem1, nomeGolem2, potenzaIterazione, nomeGolem1));
                    }
                }
            }
            InputDati.leggiStringa("Premere invio per continuare...");
            Utilities.clearScreen();
            golem1.giraPietre();
            golem2.giraPietre();
        }
    }

    private void stampaPietreTotali() {
        System.out.println("****** PIETRE NELLA SCORTA ******");
        int counter = 1;

        for (Elemento pietra : scortaPietre.keySet()) {
            System.out.println(String.format(MSG_STAMPA_PIETRA, counter, pietra.getNomeElemento(), scortaPietre.get(pietra)));
            counter++;
        }

        System.out.println("********************************");
    }

    private void sceltaPietra(String nomeGolem, ArrayList<Elemento> pietreGolem)
    {
        Utilities.clearScreen();
        System.out.println("********* Evocazione " + nomeGolem + " *********");
        stampaPietreTotali();
        String elementoPietra = "";
        Elemento pietra;

        do {
            elementoPietra = InputDati.leggiStringa("Inserire pietra da fare ingurgitare: ");
            pietra = new Elemento(elementoPietra);
            if(!scortaPietre.containsKey(pietra) || scortaPietre.get(pietra) == 0) {
                System.out.println("Pietra non valida...riprovare!");
            }
        } while (!scortaPietre.containsKey(pietra) || scortaPietre.get(pietra) == 0);

        scortaPietre.put(pietra, scortaPietre.get(pietra) - 1);
        pietreGolem.add(pietra);
    }

    private void evocazione(Giocatore giocatore, TamaGolem golemDaEvocare) {
        Utilities.clearScreen();
        giocatore.setTamaGolemInCampo(golemDaEvocare);
        ArrayList<Elemento> pietreGolem = new ArrayList<>();
        int pietreAssegnate = 0;

        while(pietreAssegnate < pietrePerGolem) {
            sceltaPietra(golemDaEvocare.getNome() ,pietreGolem);
            pietreAssegnate++;
        }

        golemDaEvocare.setPietre(pietreGolem);
    }

}
