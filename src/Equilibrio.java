import java.util.*;
import it.unibs.fp.mylib.NumeriCasuali;

public class Equilibrio {
    HashMap<Elemento, Set<Iterazione>> elementi = new HashMap<>();
    int numeroIterazioni = 0;

    public Equilibrio(List<Elemento> elementi, int vitaGolem) {
        creaEquilibrio(elementi, vitaGolem);
    }

    public int getNumeroElementi() {
        return this.elementi.size();
    }

    public int getNumeroIterazioni() {
        return numeroIterazioni;
    }

    public void aggiungiElemento(Elemento elemento) {
        if (!elementi.containsKey(elemento)) {
            Set<Iterazione> iterazioniElemento = new HashSet<>();
            elementi.put(elemento, iterazioniElemento);
        }
    }

    /**
     * aggiunge un arco tra i nodi x e y se tale arco non e' gia' presente e restituisce true,
     * altrimenti non modifica il grafo e restituisce false.
     *
     * @param elementoForte primo Elemento dell'iterazione
     * @param elementoDebole secondo Elemento dell'iterazione
     * @param potenzaIterazione potenza iterazione
     * @return vero se l'iterazione e' stato aggiunto false altrimenti
     */

    public boolean aggiungiIterazione(Elemento elementoForte, Elemento elementoDebole, int potenzaIterazione) {
        if (!elementi.containsKey(elementoForte))
            this.aggiungiElemento(elementoForte);
        if (!elementi.containsKey(elementoDebole))
            this.aggiungiElemento(elementoDebole);
        Iterazione nuovaIterazione = new Iterazione(elementoForte, elementoDebole, potenzaIterazione);
        boolean isIterazioneAggiunta = elementi.get(elementoForte).add(nuovaIterazione);
        if (isIterazioneAggiunta)
            numeroIterazioni++;
        return isIterazioneAggiunta;
    }

    public Set<Iterazione> getInsiemeIterazioni() {
        Set<Iterazione> totIterazioni = new HashSet<>();
        for (Set<Iterazione> iterazioniElemento : elementi.values())
            totIterazioni.addAll(iterazioniElemento);
        return totIterazioni;
    }

    public Set<Iterazione> getIterazioniElemento(Elemento elemento) {
        return elementi.getOrDefault(elemento, null);
    }

    public Set<Elemento> getTotaleElementi() {
        return elementi.keySet();
    }

    /**
     * metodo che genera l'equilibrio secondo l'algoritmo che abbiamo pensato
     * @param elementi
     * @param vitaGolem
     */
    private void creaEquilibrio(List<Elemento> elementi, int vitaGolem) {
        int numeroElementi = elementi.size();
        int[][] equilibrio = new int[numeroElementi][numeroElementi];

        for (int i =0; i < numeroElementi; i++) {
            for (int j =0; j < numeroElementi; j++) {
                equilibrio[i][j] = 0;
            }
        }

        for (int i =0; i < numeroElementi; i++) {
            int sommaRiga = 0;
            for (int j =0; j < numeroElementi; j++) {
                int sommaColonna = 0;
                //Riempio somma colonna specifica
                for (int k = 0; k < numeroElementi - 1; k++) {
                    sommaColonna += equilibrio[k][j];
                }

                if(j == (numeroElementi - 1) && i < j || (i == (numeroElementi - 1) && j == (numeroElementi - 2))) {
                    equilibrio[i][j] = -(sommaRiga);
                }
                //Applico algoritmo
                else if(i < j) {
                    int x = 0;
                    if(sommaRiga >= 0) {
                        do {
                            x = NumeriCasuali.estraiIntero((-vitaGolem), (vitaGolem - sommaRiga));
                        } while(x == 0 || sommaRiga + x == 0 || sommaColonna + x == 0 || Math.abs(sommaColonna + x) > vitaGolem);
                    } else {
                        do {
                            x = NumeriCasuali.estraiIntero((-vitaGolem - sommaRiga), vitaGolem);
                        } while(x == 0 || sommaRiga + x == 0 || sommaColonna + x == 0 || Math.abs(sommaColonna + x) > vitaGolem);
                    }
                    equilibrio[i][j] = x;
                    sommaRiga += x;
                    sommaColonna += equilibrio[i][j];
                }
                //Riporto il simmetrico opposto
                else if(i > j) {
                    equilibrio[i][j] = -equilibrio[j][i];
                    sommaRiga += equilibrio[i][j];
                }
            }
        }

        for (int i = 0; i < numeroElementi; i++) {
            for (int j = 0; j < numeroElementi; j++) {
                if(equilibrio[i][j] > 0) {
                    Elemento elementoForte = elementi.get(i);
                    Elemento elementoDebole = elementi.get(j);
                    int potenzaIterazione = equilibrio[i][j];
                    this.aggiungiIterazione(elementoForte, elementoDebole, potenzaIterazione);
                }
            }
        }

    }



}
