import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.fp.mylib.Utilities;

import java.util.*;

public class SetupBattaglia {
    //COSTANTI
    private static final int MIN_CHOICE_VALUE = 1;
    private static final int MAX_CHOICE_VALUE = 3;
    private static final int MIN_N_VALUE_FACILE = 3;
    private static final int MAX_N_VALUE_FACILE = 5;
    private static final int MIN_N_VALUE_MEDIA = 6;
    private static final int MAX_N_VALUE_MEDIA = 8;
    private static final int MIN_N_VALUE_DIFFICILE = 9;
    private static final int MAX_N_VALUE_DIFFICILE = 10;
    private static final String[] NOMI_ELEMENTI = { "Nikita", "Robb", "TetoFonta", "Enrico", "Massimiliano", "Lange", "Ilaria", "Jacopo", "Saetti", "Serina" };
    private static final int VITA_GOLEM = 20;
    private static final String SEL_DIF = "Difficolta' di gioco: ";
    private static final String DIF_FAC = "1)  facile";
    private static final String DIF_MED = "2)  intermedio";
    private static final String DIF_DIF = "3)  difficile";
    private static final String SPAZ_CONF_N_GIOCATORE = "\"************ CONFIGURAZIONE %s GIOCATORE ************\"";
    private static final String INS_NOME_GIOCATORE = "Inserire il nome del %s giocatore: ";
    private static final String INS_NOME_GOLEM = "Inserire il nome del %d golem: ";
    private static final String SPAZ_CREAZ_SQUADRA = "============== CREAZIONE SQUADRA ==============";
    private static final String NOME_GIOCATORE = "\"************ %s ************%n\"";
    private static final String SPAZ_SQUADRA = "============== SQUADRA ==============";
    private static final String SCELTA = "Si prega di effettuare una scelta: ";
    private static final String DIF_SCEL_FAC = "************ DIFFICOLTA SCELTA: FACILE ************";
    private static final String DIF_SCEL_MED = "************ DIFFICOLTA SCELTA: MEDIA ************";
    private static final String DIF_SCEL_DIF = "************ DIFFICOLTA SCELTA: DIFFICILE ************";
    private static final String SCEL_NUM_EL_FAC = "Scegliere il numero di elementi esistenti( da 3 a 5 ): ";
    private static final String SCEL_NUM_EL_MED = "Scegliere il numero di elementi esistenti( da 6 a 8 ): ";
    private static final String SCEL_NUM_EL_DIF = "Scegliere il numero di elementi esistenti( da 9 a 10 ): ";
    private static final String PRIMO = "Primo";
    private static final String SECONDO = "Secondo";

    //VARIABILI DI GIOCO
    private static int numeroElementi;
    private static int numeroTamaGolem;
    private static int numeroPietrePerGolem;
    private static int numeroPietreScorta;
    private static int numeroPietrePerElemento;

    private static void benvenuto() {
        System.out.println("+------------------------------------------------+");
        System.out.println("|             BENVENUTO IN TAMAGOLEM             |");
        System.out.println("+------------------------------------------------+");
    }

    private static void menuDifficolta()
    {
        System.out.println(SEL_DIF);
        System.out.println(DIF_FAC);
        System.out.println(DIF_MED);
        System.out.println(DIF_DIF);
    }
    private static void menuDifficoltaSwitch(int difficolta) {
        switch (difficolta) {
            case 1:
                System.out.println(DIF_SCEL_FAC);
                numeroElementi = InputDati.leggiIntero(SCEL_NUM_EL_FAC, MIN_N_VALUE_FACILE, MAX_N_VALUE_FACILE);
                break;
            case 2:
                System.out.println(DIF_SCEL_MED);
                numeroElementi = InputDati.leggiIntero(SCEL_NUM_EL_MED, MIN_N_VALUE_MEDIA, MAX_N_VALUE_MEDIA);
                break;
            case 3:
                System.out.println(DIF_SCEL_DIF);
                numeroElementi = InputDati.leggiIntero(SCEL_NUM_EL_DIF, MIN_N_VALUE_DIFFICILE, MAX_N_VALUE_DIFFICILE);
                break;
            default:
                break;
        }
    }

    /**
     * metodo che genera gli elementi che verranno utilizzati nella partita
     * @param numeroElementi indica il numero di elementi richiesti dal giocatore in base alla difficoltà
     * @return tutti gli elementi che verranno usati (li mischia per non aver sempre gli stessi)
     */
    private static Elemento[] generaElementi(int numeroElementi) {
        ArrayList<String> nomiMischiati = new ArrayList<>(Arrays.asList(NOMI_ELEMENTI));
        Collections.shuffle(nomiMischiati);
        Elemento[] elementi = new Elemento[numeroElementi];
        for (int i = 0; i < numeroElementi; i++) {
            elementi[i] = new Elemento(nomiMischiati.get(i));
        }
        return elementi;
    }

    /**
     * metodo che serve per mostrare l'Equilibrio del mondo restituendo le iterazioni tra gli elementi
     * (verrà utilizzato alla fine della partita
     * @param equilibrio
     */
    private static void stampaEquilibrio(Equilibrio equilibrio) {
        for (Elemento el : equilibrio.getTotaleElementi()) {
            System.out.println("**** " + el.getNomeElemento() + " ****");
            for(Iterazione it : equilibrio.getIterazioniElemento(el)) {
                System.out.println(it.toString());
            }
        }
    }

    /**
     * metodo che serve a far creare un giocatore all'utente
     * @param numeroGiocatore
     * @return il giocatore creato con la squadra di Tamagolem
     */
    private static Giocatore creazioneGiocatore(String numeroGiocatore) {
        System.out.println(String.format(SPAZ_CONF_N_GIOCATORE, numeroGiocatore.toUpperCase()));
        String nomeGiocatore = InputDati.leggiStringa(String.format(INS_NOME_GIOCATORE, numeroGiocatore));
        ArrayList<TamaGolem> squadraGiocatore = new ArrayList<>();
        System.out.println(SPAZ_CREAZ_SQUADRA);
        for (int i = 0; i < numeroTamaGolem; i++) {
            String nomeGolem = InputDati.leggiStringa(String.format(INS_NOME_GOLEM, i + 1));
            TamaGolem golem = new TamaGolem(nomeGolem);
            squadraGiocatore.add(golem);
        }
        return new Giocatore(nomeGiocatore, squadraGiocatore);
    }

    /**
     * metodo che stampa il giocatore e la sua squadra
     * @param giocatore
     */
    private static void stampaGiocatore(Giocatore giocatore) {
        System.out.printf(NOME_GIOCATORE, giocatore.getNome());
        System.out.println(SPAZ_SQUADRA);
        for (TamaGolem golem : giocatore.getSquadra()) {
            System.out.println(String.format("%s => %d", golem.getNome(), golem.getVita()));
        }
    }

    /**
     * metodo che fa scegliere al giocatore la difficoltà dell'incontro e di conseguenza inizializza i valori di gioco
     * (Numero di TamaGolem, numero delle pietre che un TamaGolem può mangiare,
     * il numero di pietre nella riserva e il numero di pietre per ogni elemento)
     * secondo le formule assegnate, crea l'equilibrio del mondo, crea i giocatori,i TamaGolem e mostra la situazione
     * attuale delle squadre. Insomma gestisce il gioco prima della battaglia.
     */
    public static void inizializzaBattaglia() {
        benvenuto();

        //Scelta della difficolta'
        menuDifficolta();
        int choice = InputDati.leggiIntero(SCELTA, MIN_CHOICE_VALUE, MAX_CHOICE_VALUE);
        menuDifficoltaSwitch(choice);

        //Pulire console
        Utilities.clearScreen();

        //Inizializzazione variabili
        numeroPietrePerGolem = (int) Math.ceil( (double) (numeroElementi + 1) / 3) + 1;
        numeroTamaGolem = (int) Math.ceil((double) (numeroElementi - 1) * (numeroElementi - 2) / (2 * numeroPietrePerGolem));
        numeroPietreScorta = (int) Math.ceil((double) (2 * numeroTamaGolem * numeroPietrePerGolem) / numeroElementi) * numeroElementi;
        numeroPietrePerElemento = (int) Math.ceil((double) (2 * numeroTamaGolem * numeroPietrePerGolem) / numeroElementi);

        //Inserimento pietre nella scorta
        Elemento[] elementi = generaElementi(numeroElementi);
        HashMap<Elemento, Integer> scortaPietre = new HashMap<>();
        int pietreInserite = 0;
        for (int i = 0; i < numeroElementi; i++) {
            scortaPietre.put(elementi[i], numeroPietrePerElemento);
        }
        //Genera equilibrio
        Equilibrio equilibrio = new Equilibrio(Arrays.asList(elementi), VITA_GOLEM);

        //Creazione giocatori
        Giocatore primoGiocatore = creazioneGiocatore(PRIMO);
        Utilities.clearScreen();
        Giocatore secondoGiocatore = creazioneGiocatore(SECONDO);
        Utilities.clearScreen();

        //Stampa giocatori
        stampaGiocatore(primoGiocatore);
        stampaGiocatore(secondoGiocatore);
        //Pulire console
        Utilities.clearScreen();

        //Realizzazione della battaglia
        Battaglia battaglia = new Battaglia(primoGiocatore, secondoGiocatore, scortaPietre, equilibrio, numeroPietrePerGolem);
        battaglia.scontro();
    }

}

