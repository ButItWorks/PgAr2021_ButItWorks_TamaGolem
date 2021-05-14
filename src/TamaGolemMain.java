import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.Utilities;

import java.util.*;

public class TamaGolemMain {

    private static boolean exit = false;
    private static final int MIN_CHOICE_VALUE = 0;
    private static final int MAX_CHOICE_VALUE = 1;

    public static void menuHeader(){
        System.out.println("+------------------------------------------------+");
        System.out.println("|             BENVENUTO IN TAMAGOLEM             |");
        System.out.println("+------------------------------------------------+");
        System.out.println("Seleziona una delle seguenti opzioni:");
        System.out.println("1)  Inizia una nuova partita");
        System.out.println("0)  Esci dal programma");
    }

    public static void menuSwitch(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Arrivederci!");
                exit = true;
                break;
            case 1:
                Utilities.clearScreen();
                SetupBattaglia.inizializzaBattaglia();
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        while (!exit) {
            menuHeader();
            int choice = InputDati.leggiIntero("Si prega di effettuare una scelta: ", MIN_CHOICE_VALUE, MAX_CHOICE_VALUE);
            menuSwitch(choice);
        }
    }

}
