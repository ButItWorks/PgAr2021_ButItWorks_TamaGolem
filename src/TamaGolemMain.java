import it.unibs.fp.mylib.NumeriCasuali;

import java.util.*;

public class TamaGolemMain {

    public static void main(String[] args) {
        Equilibrio.creaEquilibrio();

        /*
        List<Nodo> elementi = new ArrayList<>();
        List<Arco> listaTotaleArchi = new ArrayList<>();
        int vitaGolem = 10;

        elementi.add(new Nodo("fuoco"));
        elementi.add(new Nodo("acqua"));
        elementi.add(new Nodo("erba"));
        elementi.add(new Nodo("terra"));
        //elementi.add(new Nodo("ghiaccio"));

        /*
        for (int i = 0; i < elementi.size(); i++) {
            List<Nodo> altriElementi = new ArrayList<>(elementi);
            altriElementi.remove(elementi.get(i));
            //creazione archi con elementi diversi dall'elemento su cui si sta iterando
            for (Nodo altroElemento : altriElementi) {
                if(i == 0 && altroElemento.equals(altriElementi.get(altriElementi.size()-1)) ) {
                    break;
                } else {
                    Arco nuovoArco = new Arco(elementi.get(i), altroElemento, 0);
                    boolean isEsistente = false;
                    //controllo se arco gia esistente controllo con equals tra archi
                    for (Arco arco : listaTotaleArchi) {
                        if(arco.equals(nuovoArco)) {
                            isEsistente = true;
                            break;
                        }
                    }
                    if(!isEsistente) {
                        listaTotaleArchi.add(nuovoArco);
                        elementi.get(i).aggiungiArco(nuovoArco);
                    }
                }
            }
        }
        */
        /*
        for(int i = 0; i < elementi.size(); i++) {
            List<Arco> archiUscentiElemento = new ArrayList<>();
            List<Arco> archiEntrantiElemento = new ArrayList<>();
            List<Nodo> altriElementi = new ArrayList<>(elementi);
            altriElementi.remove(elementi.get(i));

            int maxArchiUscenti = elementi.size() - 1;
            int minArchiEntranti = 1;

            /*
            while((minArchiEntranti * vitaGolem) < maxArchiUscenti) {
                maxArchiUscenti--;
                minArchiEntranti++;
            }

            if(i != 0) {
                for (Arco a : listaTotaleArchi) {
                    if(a.getPrimoNodo().getNomeNodo().equals(elementi.get(i).getNomeNodo())) {
                        archiUscentiElemento.add(a);
                    }
                }

                for (Arco a : listaTotaleArchi) {
                    if(a.getPrimoNodo().getNomeNodo().equals(elementi.get(i).getNomeNodo())) {
                        archiEntrantiElemento.add(a);
                    }
                }

                int numeroArchiEntranti = archiEntrantiElemento.size();
                int numeroArchiUscenti = archiUscentiElemento.size();

                System.out.println("*********");
                System.out.println(numeroArchiEntranti);
                System.out.println(numeroArchiUscenti);
                System.out.println("*********");

                if(numeroArchiEntranti == 0 && numeroArchiUscenti == 0) {
                    maxArchiUscenti -= 1;
                }
                else {
                    maxArchiUscenti = maxArchiUscenti - numeroArchiUscenti - numeroArchiEntranti;
                }
            } else {
                maxArchiUscenti -= 1;
            }



            System.out.println(maxArchiUscenti);

            int numeroArchiUscentiEffettivi = NumeriCasuali.estraiIntero(1, maxArchiUscenti);
            //int numeroArchiEntrantiEffettivi = NumeriCasuali.estraiIntero(1, minArchiEntranti);

            int numeroArchiUscentiInseriti = 0;
            //int numeroArchiEntrantiInseriti = 0;

            int j = 0;

            while(numeroArchiUscentiInseriti < numeroArchiUscentiEffettivi) {
                Arco arcoUscente = new Arco(elementi.get(i), altriElementi.get(j), 0);
                boolean isEsistente = false;
                for (Arco arco : listaTotaleArchi) {
                    if(arco.equals(arcoUscente)) {
                        isEsistente = true;
                        break;
                    }
                }
                if(!isEsistente) {
                    listaTotaleArchi.add(arcoUscente);
                    elementi.get(i).aggiungiArco(arcoUscente);
                    numeroArchiUscentiInseriti++;
                }
                j++;
            }

            for (Nodo altroElemento : altriElementi) {
                Arco arcoEntrante = new Arco(altroElemento ,elementi.get(i), 0);
                boolean isEsistente = false;
                for (Arco arco : listaTotaleArchi) {
                    if(arco.equals(arcoEntrante)) {
                        isEsistente = true;
                        break;
                    }
                }
                if(!isEsistente) {
                    elementi.get(i).aggiungiArco(arcoEntrante);
                    listaTotaleArchi.add(arcoEntrante);
                    //numeroArchiEntrantiInseriti++;
                }
            }

            /*
            while(numeroArchiEntrantiInseriti < numeroArchiEntrantiEffettivi) {
                Arco arcoUscente = new Arco(elementi.get(i), altriElementi.get(j), 0);
                boolean isEsistente = false;
                for (Arco arco : listaTotaleArchi) {
                    if(arco.equals(arcoUscente)) {
                        isEsistente = true;
                        break;
                    }
                }
                if(!isEsistente) {
                    Arco ar = new Arco(altriElementi.get(j), elementi.get(i), 0);
                    elementi.get(i).aggiungiArco(ar);
                    listaTotaleArchi.add(ar);
                    numeroArchiEntrantiInseriti++;
                }
                j++;
            }

            for (j = 0; j < numeroArchiUscentiEffettivi; j++) {
                Arco arcoUscente = new Arco(elementi.get(i), altriElementi.get(j), 0);
                boolean isEsistente = false;
                for (Arco arco : listaTotaleArchi) {
                    if(arco.equals(arcoUscente)) {
                        isEsistente = true;
                        break;
                    }
                }
                if(!isEsistente) {
                    listaTotaleArchi.add(arcoUscente);
                    elementi.get(i).aggiungiArco(arcoUscente);
                }
            }

            for (int k = 0; k < numeroArchiEntrantiEffettivi; k++) {
                Arco ar = new Arco(altriElementi.get(j), elementi.get(i), 0);
                elementi.get(i).aggiungiArco(ar);
                listaTotaleArchi.add(ar);
                j++;
            }

        }

        for (Nodo el : elementi) {
            System.out.println("*** " + el.getNomeNodo() + " ***");
            for (Arco ar : el.getArchiAssociati()) {
                if(ar.getPrimoNodo().getNomeNodo().equals(el.getNomeNodo()))
                System.out.println(ar.toString());
            }
        }
        */





    }

}
