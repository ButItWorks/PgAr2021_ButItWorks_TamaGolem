import it.unibs.fp.mylib.InputDati;

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

    //VARIABILI DI GIOCO
    private static int difficolta;
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
        System.out.println("Selezionare la difficolta':");
        System.out.println("1)  facile");
        System.out.println("2)  intermedio");
        System.out.println("3)  difficile");
    }

    private static void menuSwitch(int choice) {
        switch (choice) {
            case 1:
                difficolta = 1;
                break;
            case 2:
                difficolta = 2;
                break;
            case 3:
                difficolta = 3;
                break;
            default:
                break;
        }
    }

    private static Elemento[] generaElementi(int numeroElementi) {
        ArrayList<String> nomiMischiati = new ArrayList<>(Arrays.asList(NOMI_ELEMENTI));
        Collections.shuffle(nomiMischiati);
        Elemento[] elementi = new Elemento[numeroElementi];
        for (int i = 0; i < numeroElementi; i++) {
            elementi[i] = new Elemento(nomiMischiati.get(i));
        }
        return elementi;
    }

    private static void stampaEquilibrio(Equilibrio equilibrio) {
        for (Elemento el : equilibrio.getTotaleElementi()) {
            System.out.println("**** " + el.getNomeElemento() + " ****");
            for(Iterazione it : equilibrio.getIterazioniElemento(el)) {
                System.out.println(it.toString());
            }
        }
    }

    private static Giocatore creazioneGiocatore(String numeroGiocatore) {
        System.out.println(String.format("************ CONFIGURAZIONE %s GIOCATORE ************", numeroGiocatore));
        String nomeGiocatore = InputDati.leggiStringa(String.format("Inserire il nome del %s giocatore: ", numeroGiocatore));
        ArrayList<TamaGolem> squadraGiocatore = new ArrayList<>();
        System.out.println("============== CREAZIONE SQUADRA ==============");
        for (int i = 0; i < numeroTamaGolem; i++) {
            String nomeGolem = InputDati.leggiStringa(String.format("Inserire il nome del %d golem: ", i + 1));
            TamaGolem golem = new TamaGolem(nomeGolem);
            squadraGiocatore.add(golem);
        }
        return new Giocatore(nomeGiocatore, squadraGiocatore);
    }

    private static void stampaGiocatore(Giocatore giocatore) {
        System.out.printf("************ %s ************%n", giocatore.getNome());
        System.out.println("============== SQUADRA ==============");
        for (TamaGolem golem : giocatore.getSquadra()) {
            System.out.println(String.format("%s => %d", golem.getNome(), golem.getVita()));
        }
    }

    public static void inizializzaBattaglia() {
        benvenuto();
        menuDifficolta();
        int choice = InputDati.leggiIntero("Si prega di effettuare una scelta: ", MIN_CHOICE_VALUE, MAX_CHOICE_VALUE);
        menuSwitch(choice);
        if(difficolta == 1) {
            System.out.println("************ DIFFICOLTA SCELTA: FACILE ************");
            numeroElementi = InputDati.leggiIntero("Scegliere il numero di elementi esistenti( da 3 a 5 ): ", MIN_N_VALUE_FACILE, MAX_N_VALUE_FACILE);
        } else if(difficolta == 2) {
            System.out.println("************ DIFFICOLTA SCELTA: MEDIA ************");
            numeroElementi = InputDati.leggiIntero("Scegliere il numero di elementi esistenti( da 6 a 8 ): ", MIN_N_VALUE_MEDIA, MAX_N_VALUE_MEDIA);
        } else {
            System.out.println("************ DIFFICOLTA SCELTA: DIFFICILE ************");
            numeroElementi = InputDati.leggiIntero("Scegliere il numero di elementi esistenti( da 9 a 10 ): ", MIN_N_VALUE_DIFFICILE, MAX_N_VALUE_DIFFICILE);
        }
        //Inizializzazione variabili
        numeroPietrePerGolem = (int) Math.ceil( (double) (numeroElementi + 1) / 3) + 1;
        numeroTamaGolem = (int) Math.ceil((double) (numeroElementi - 1) * (numeroElementi - 2) / (2 * numeroPietrePerGolem));
        numeroPietreScorta = (int) Math.ceil((double) (2 * numeroTamaGolem * numeroPietrePerGolem) / numeroElementi) * numeroElementi;
        numeroPietrePerElemento = (int) Math.ceil((double) (2 * numeroTamaGolem * numeroPietrePerGolem) / numeroElementi);

        //Inserimento elementi
        Elemento[] elementi = generaElementi(numeroElementi);
        HashMap<Elemento, Integer> scortaPietre = new HashMap<>();
        int pietreInserite = 0;

        for (int i = 0; i < numeroElementi; i++) {
            scortaPietre.put(elementi[i], numeroPietrePerElemento);
        }

        for (Elemento i : scortaPietre.keySet()) {
            System.out.println("key: " + i.getNomeElemento() + " value: " + scortaPietre.get(i));
        }


        //Genera equilibrio
        Equilibrio equilibrio = new Equilibrio(Arrays.asList(elementi), VITA_GOLEM);

        //Creazione giocatori
        Giocatore primoGiocatore = creazioneGiocatore("primo");
        System.out.println("----------------------------------------");
        Giocatore secondoGiocatore = creazioneGiocatore("secondo");

        //Stampa giocatori
        stampaGiocatore(primoGiocatore);
        stampaGiocatore(secondoGiocatore);

        Battaglia.variabiliDiConfigurazione(scortaPietre, numeroPietrePerGolem, equilibrio);
        Battaglia.scontro(primoGiocatore, secondoGiocatore);
    }

}
