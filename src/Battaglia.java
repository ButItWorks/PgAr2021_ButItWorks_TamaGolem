import it.butitworks.model.*;
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
    private static final String MSG_DANNI = "%s vince sul %s  => %s di %s infligge %d a %s di %s";
    private static final String SPAZ_LIST_GOL_RIM_GIOC = "****** LISTA GOLEM RIMANENTI GIOCATORE %s ******";
    private static final String SPAZ_PIETRE_SCORTA = "******* PIETRE NELLA SCORTA *******";
    private static final String SPAZ = "***********************************";
    private static final String SPAZ_SOST_GOL_GIOC = "******** SOSTITUZIONE GOLEM GIOCATORE %s *******";
    private static final String MSG_NOME_GOLEM = "Inserire il nome del golem da evocare: ";
    private static final String MSG_GOLEM_INESISTENTE = "Non esiste un golem con questo nome...riprovare!";
    private static final String MSG_VINCITORE = "Lo scontro e' terminato, ed il vincitore e'........................%s";
    private static final String MSG_SET_PIETRE_UGUALE = "Il set di pietre inserite e' esattamente uguale a quello del tamagolem avversario....riprovare!";
    private static final String MSG_PIETRA_DA_INGURGITARE = "Inserire pietra da fare ingurgitare: ";
    private static final String MSG_PIETRA_INVALIDA = "Pietra non valida...riprovare!";
    private static final String MSG_PIETRE_EQUIVALENTI = "Le pietre scagliate sono equivalenti, nessuno subisce danni!";
    private static final String MSG_EVOCAZIONE = "***********************\nEVOCAZIONE %s\n***********************";

    //ATTRIBUTI
    private Giocatore g1;
    private Giocatore g2;
    private HashMap<Elemento, Integer> scortaPietre = new HashMap<>();
    private Equilibrio equilibrioBattaglia;
    private int pietrePerGolem;

    /**
     * metodo che stampa i golem che rimangono in squadra a un giocatore
     * @param giocatore
     */
    private void stampaGolemRimanenti(Giocatore giocatore)
    {
        System.out.println(String.format(SPAZ_LIST_GOL_RIM_GIOC, giocatore.getNome()));
        for (TamaGolem golem : giocatore.getSquadra()) {
            if(!golem.isMorto()) {
                System.out.println("=> " + golem.getNome());
            }
        }
        System.out.println(SPAZ);
    }

    public Battaglia(Giocatore g1, Giocatore g2, HashMap<Elemento, Integer> scortaPietre, Equilibrio equilibrioBattaglia, int pietrePerGolem) {
        this.g1 = g1;
        this.g2 = g2;
        this.scortaPietre = scortaPietre;
        this.equilibrioBattaglia = equilibrioBattaglia;
        this.pietrePerGolem = pietrePerGolem;
    }

    /**
     * metodo che gestisce tutto il match
     */
    public void scontro() {
        boolean isBattagliaTerminata = false;
        Giocatore vincitore = new Giocatore();

        System.out.println(MSG_INIZIO_SCONTRO);
        while(!isBattagliaTerminata) {
            if(!g1.getTamaGolemInCampo().isMorto() && !g2.getTamaGolemInCampo().isMorto()) {
                evocazione(g1, g1.getTamaGolemInCampo());

                //Controllo se il set di pietre e' equivalente
                boolean isSetPietreEquals = false;
                do {
                    evocazione(g2, g2.getTamaGolemInCampo());
                    isSetPietreEquals = g1.getTamaGolemInCampo().isSetPietreEquals(g2.getTamaGolemInCampo());
                    if(isSetPietreEquals) {
                        System.out.println(MSG_SET_PIETRE_UGUALE);
                        resetPietreGolem(g2.getTamaGolemInCampo());
                        Utilities.clearScreenStop();
                    }
                } while (isSetPietreEquals);
            }
            else if(g1.getTamaGolemInCampo().isMorto()) {
                if(g1.isSquadraEsausta()) {
                    isBattagliaTerminata = true;
                    vincitore = g2;
                } else {
                    System.out.println(String.format(SPAZ_SOST_GOL_GIOC, g1.getNome()));
                    stampaGolemRimanenti(g1);
                    TamaGolem golemDaEvocare = new TamaGolem();
                    String nomeGolemDaEvocare = "";

                    do {
                        nomeGolemDaEvocare = InputDati.leggiStringa(MSG_NOME_GOLEM);
                        golemDaEvocare = new TamaGolem(nomeGolemDaEvocare);
                        if(!g1.getSquadra().contains(golemDaEvocare)) {
                            System.out.println(MSG_GOLEM_INESISTENTE);
                        } else {
                            for (TamaGolem golem : g1.getSquadra()) {
                                if(golem.equals(golemDaEvocare)) {
                                    golemDaEvocare = golem;
                                }
                            }
                        }
                    } while (!g1.getSquadra().contains(golemDaEvocare) || golemDaEvocare.isMorto());

                    //Controllo se il set di pietre e' equivalente
                    boolean isSetPietreEquals = false;
                    do {
                        evocazione(g1, golemDaEvocare);
                        isSetPietreEquals = g2.getTamaGolemInCampo().isSetPietreEquals(g1.getTamaGolemInCampo());
                        if(isSetPietreEquals) {
                            System.out.println(MSG_SET_PIETRE_UGUALE);
                            resetPietreGolem(g1.getTamaGolemInCampo());
                            Utilities.clearScreenStop();
                        }
                    } while (isSetPietreEquals);
                }
            }
            else {
                if(g2.getTamaGolemInCampo().isMorto()) {
                    if(g2.isSquadraEsausta()) {
                        isBattagliaTerminata = true;
                        vincitore = g1;
                    } else {
                        System.out.println(String.format(SPAZ_SOST_GOL_GIOC, g2.getNome()));
                        stampaGolemRimanenti(g2);
                        TamaGolem golemDaEvocare = new TamaGolem();
                        String nomeGolemDaEvocare = "";

                        do {
                            nomeGolemDaEvocare = InputDati.leggiStringa(MSG_NOME_GOLEM);
                            golemDaEvocare = new TamaGolem(nomeGolemDaEvocare);
                            if (!g2.getSquadra().contains(golemDaEvocare)) {
                                System.out.println(MSG_GOLEM_INESISTENTE);
                            } else {
                                for (TamaGolem golem : g2.getSquadra()) {
                                    if (golem.equals(golemDaEvocare)) {
                                        golemDaEvocare = golem;
                                    }
                                }
                            }
                        } while (!g2.getSquadra().contains(golemDaEvocare) || golemDaEvocare.isMorto());

                        //Controllo se il set di pietre e' equivalente
                        boolean isSetPietreEquals = false;
                        do {
                            evocazione(g2, golemDaEvocare);
                            isSetPietreEquals = g1.getTamaGolemInCampo().isSetPietreEquals(g2.getTamaGolemInCampo());
                            if(isSetPietreEquals) {
                                System.out.println(MSG_SET_PIETRE_UGUALE);
                                resetPietreGolem(g2.getTamaGolemInCampo());
                                Utilities.clearScreenStop();
                            }
                        } while (isSetPietreEquals);
                    }
                }
            }
            Utilities.clearScreen();

            if(!isBattagliaTerminata) {
                this.scontroTamaGolem(g1.getTamaGolemInCampo(), g2.getTamaGolemInCampo(),g1,g2);
            }
        }
        System.out.println(String.format(MSG_VINCITORE, vincitore.getNome().toUpperCase()));

    }

    /**
     * metodo per stabilire l'iterazione fra i due elementi e la loro potenza
     * @param iterazioniElemento sono le iterazio i che possiede un elemento con gli altri
     * @param elementoAvversario
     * @return
     */
    private int potenzaIterazione(Set<Iterazione> iterazioniElemento, Elemento elementoAvversario) {
        for (Iterazione iterazione : iterazioniElemento) {
            if(iterazione.getSecondoNodo().equals(elementoAvversario)) {
                return iterazione.getPotenzaIterazione();
            }
        }
        return 0;
    }

    /**
     * metodo che gestisce lo scontro
     * @param golem1
     * @param golem2
     */
    private void scontroTamaGolem(TamaGolem golem1, TamaGolem golem2, Giocatore g1, Giocatore g2) {
        String nomeGolem1 = golem1.getNome();
        String nomeGolem2 = golem2.getNome();
        String giocatore1 = g1.getNome();
        String giocatore2 = g2.getNome();

        while(!golem1.isMorto() && !golem2.isMorto()) {
            Elemento pietraGolem1 = golem1.getPietraAttuale();
            Elemento pietraGolem2 = golem2.getPietraAttuale();
            String elementoPietraGolem1 = pietraGolem1.getNomeElemento();
            String elementoPietraGolem2 = pietraGolem2.getNomeElemento();
            Set<Iterazione> iterazioniPrimoElemento = this.equilibrioBattaglia.getIterazioniElemento(pietraGolem1);
            Set<Iterazione> iterazioniSecondoElemento = this.equilibrioBattaglia.getIterazioniElemento(pietraGolem2);
            int potenzaIterazione = 0;

            if(pietraGolem1.equals(pietraGolem2)) {
                System.out.println(MSG_PIETRE_EQUIVALENTI);
            } else {
                if(potenzaIterazione(iterazioniPrimoElemento, pietraGolem2) != 0) {
                    potenzaIterazione = potenzaIterazione(iterazioniPrimoElemento, pietraGolem2);
                    golem2.infliggiDanno(potenzaIterazione);
                    if(golem2.isMorto()) {
                        System.out.println(String.format(MSG_GOLEM_MORTO, golem2.getNome()));
                    } else {
                        System.out.println(String.format(MSG_DANNI, elementoPietraGolem1, elementoPietraGolem2, nomeGolem1, giocatore1, potenzaIterazione, nomeGolem2, giocatore2));
                    }
                }
                else {
                    potenzaIterazione = potenzaIterazione(iterazioniSecondoElemento, pietraGolem1);
                    golem1.infliggiDanno(potenzaIterazione);
                    if(golem1.isMorto()) {
                        System.out.println(String.format(MSG_GOLEM_MORTO, golem1.getNome()));
                    } else {
                        System.out.println(String.format(MSG_DANNI, elementoPietraGolem2, elementoPietraGolem1, nomeGolem2, giocatore2, potenzaIterazione, nomeGolem1,giocatore1));
                    }
                }
            }
            Utilities.clearScreenStop();
            golem1.giraPietre();
            golem2.giraPietre();
        }
    }

    private void resetPietreGolem(TamaGolem golem) {
        for (Elemento pietra : golem.getPietre()) {
            scortaPietre.put(pietra, scortaPietre.get(pietra) + 1);
        }
        golem.setPietre(new ArrayList<Elemento>());
    }

    /**
     * metodo per mostrare al giocatore le pietre nella riserva, che quindi può scegliere
     */
    private void stampaPietreTotali() {
        System.out.println(SPAZ_PIETRE_SCORTA);
        int counter = 1;

        for (Elemento pietra : scortaPietre.keySet()) {
            System.out.println(String.format(MSG_STAMPA_PIETRA, counter, pietra.getNomeElemento(), scortaPietre.get(pietra)));
            counter++;
        }

        System.out.println(SPAZ);
    }

    /**
     * metodo che permette al giocatore di scegliere le pietre da far mangiare al tamagolem
     * @param pietreGolem
     */
    private void sceltaPietra(String nomeGolem, ArrayList<Elemento> pietreGolem)
    {
        Utilities.clearScreen();
        System.out.println(String.format(MSG_EVOCAZIONE, nomeGolem));
        stampaPietreTotali();
        String elementoPietra = "";
        Elemento pietra;

        do {
            elementoPietra = InputDati.leggiStringa(MSG_PIETRA_DA_INGURGITARE);
            pietra = new Elemento(elementoPietra.toUpperCase());
            if(!scortaPietre.containsKey(pietra) || scortaPietre.get(pietra) == 0) {
                System.out.println(MSG_PIETRA_INVALIDA);
            }
        } while (!scortaPietre.containsKey(pietra) || scortaPietre.get(pietra) == 0);

        scortaPietre.put(pietra, scortaPietre.get(pietra) - 1);
        pietreGolem.add(pietra);
    }

    /**
     * metodo che gestisce l'evocazione del it.butitworks.model.TamaGolem
     * @param giocatore
     * @param golemDaEvocare
     */
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
