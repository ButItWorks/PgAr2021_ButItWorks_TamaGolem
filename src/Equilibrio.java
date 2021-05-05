import it.unibs.fp.mylib.NumeriCasuali;

import java.util.*;


public class Equilibrio {
    /*
    HashMap<Nodo,Set<Arco>> nodi;
    int nArchi;


    public Equilibrio() {
        nodi = new HashMap<>();
        nArchi = 0;
    }

    public int numeroNodi() {
        return nodi.size();
    }

    public int numeroArchi() {
        return nArchi;
    }

    public void add(Nodo nodo) {
        if (!nodi.containsKey(nodo)) {
            Set<Arco> listaNodi = new HashSet<>();
            nodi.put(nodo,listaNodi);
        }
    }

    /**
     * rimuove il nodo x dal grafo se x e' presente nel grafo,
     * altrimenti non modifica il grafo.
     *
     * @param x il nodo da rimuovere
     */
/*
    public void remove(Object x) {
        if (nodi.containsKey(x)) {
            Iterator<Arco> arcoIncidenteI = ( (HashSet<Arco>) nodi.get(x) ).iterator();
            Arco a;
            Object y;
            while (arcoIncidenteI.hasNext()) {
                a = arcoIncidenteI.next();
                y = ( a.getNode1().equals(x) ) ? a.getNode2() : a.getNode1();
                if ( ((HashSet) nodi.get(y)).remove(a) )
                    nArchi--;
            }
            nodi.remove(x);
        }
    }


    /**
     * aggiunge un arco tra i nodi x e y se tale arco non e' gia' presente e restituisce true,
     * altrimenti non modifica il grafo e restituisce false.
     *
     * @param x primo nodo dell'arco
     * @param y secondo nodo dell'arvo
     * @param value valore dell'arco
     * @return vero se l'arco e' stato rimosso false altrimenti
     */
/*
    public boolean add(Object x, Object y, Object value) {
        boolean flag = false, flag1 = false;
        if (!nodi.containsKey(x))
            add(x);
        if (!nodi.containsKey(y))
            add(y);
        Arco a = new Arco(x,y,value);
        flag = (nodi.get(x) ).add(a);
        flag1 =(nodi.get(y) ).add(a);
        flag = flag && flag1;
        if (flag)
            nArchi++;
        return flag;
    }

    /**
     * Aggiunge l'arco a al grafo se l'arco non e' presente e restituisce true,
     * altrimenti non modifica il grafo e restituisce false
     *
     * @param a l'arco da aggiungere
     * @return true se l'arco e' stato aggiunto, false altrimenti
     */
/*
    public boolean add(Arco a) {
        return add(a.getNode1(),a.getNode2(),a.getValue());
    }

    /**
     * Rimuove l'arco tra i nodi x e y se tale arco e' presente e restituisce true,
     * altrimenti non modifica il grafo e restituisce false.
     *
     * @param x primo nodo dell'arco
     * @param y secondo nodo dell'arvo
     * @param value valore dell'arco
     * @return vero se l'arco e' stato rimosso false altrimenti
     */
/*
    public boolean remove(Object x, Object y, Object value) {
        Arco a = new Arco(x,y,value);
        return remove(a);
    }

    /**
     * Rimuove l'arco a dal grafo se l'arco e' presente e restituisce true,
     * altrimenti non modifica il grafo e restituisce false
     *
     * @param a l'arco da aggiungere
     * @return true se l'arco e' stato rimosso, false altrimenti
     */
/*
    public boolean remove(Arco a) {
        boolean flag = false,  flag1 = false;
        if (nodi.containsKey(a.getNode1()) && nodi.containsKey(a.getNode2())) {
            flag = ( (HashSet) nodi.get(a.getNode1()) ).remove(a);
            flag1 = ( (HashSet) nodi.get(a.getNode2()) ).remove(a);
        }
        return flag || flag1;
    }

    /**
     * Restituisce l'insieme degli archi presenti nel grafo
     *
     * @return l'insieme di archi presenti nel grafo
     */
/*
    public Set<Arco> getEdgeSet() {
        Set<Arco> setArchi = new HashSet<Arco>();
        Iterator<Set<Arco>> hashSetI = nodi.values().iterator();
        while (hashSetI.hasNext())
            setArchi.addAll(hashSetI.next());
        return setArchi;
    }

    /**
     * Restituisce l'insieme di archi incidenti sul nodo nodo,
     * se nodo e' presente nel grafo, null altrimenti
     *
     * @param nodo nodo di cui si vuole conoscere l'insieme di archi incidenti
     * @return l'insieme di archi incidenti sul nodo nodo,
     * se nodo e' presente nel grafo null altrimenti
     */
/*
    public Set<Arco> getEdgeSet(Object nodo) {
        if (nodi.containsKey(nodo)) //se il nodo e' presente nel grafo
            return nodi.get(nodo);
        else
            return null;
    }

    /**
     * Restituisce l'insieme di nodi del grafo
     *
     * @return l'insieme di nodi del grafo
     */
/*
    public Set<Object> getNodeSet() {
        return nodi.keySet();
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        Object nodo;
        Arco a;
        Iterator<Arco> arcoI;
        Iterator<Object> nodoI = nodi.keySet().iterator();
        while (nodoI.hasNext()) {
            arcoI = nodi.get( nodo = nodoI.next() ).iterator();
            out.append("Nodo " + nodo.toString() + ": ");
            while (arcoI.hasNext()) {
                a = arcoI.next();
                //out.append( ((a.x == nodo ) ? a.y.toString() : a.x.toString()) + "("+a.value.toString()+"), ");
                out.append(a.toString()+", ");
            }
            out.append("\n");
        }
        return out.toString();
    }
    */
    public static void creaEquilibrio() {
        List<String> elementi = new ArrayList<>();
        elementi.add("fuoco");
        elementi.add("acqua");
        elementi.add("erba");
        elementi.add("aria");
        int numeroElementi = elementi.size();
        int vitaGolem = 10;
        int equilibrio[][] = new int[numeroElementi][numeroElementi];

        for (int i =0; i < numeroElementi; i++) {
            for (int j =0; j < numeroElementi; j++) {
                equilibrio[i][j] = 0;
                System.out.print(equilibrio[i][j] + "\t");
            }
            System.out.println("");
        }

        int sommaColonnaSpeciale = 0;
        for (int i =0; i < numeroElementi; i++) {
            int sommaRiga = 0;
            for (int j =0; j < numeroElementi; j++) {
                //Applico algoritmo
                if(i < j) {
                    if(j == (numeroElementi - 2)) {
                        if(sommaRiga == 0) {
                            j--;
                            sommaRiga -= equilibrio[i][j];
                        } else {
                            if(i == (numeroElementi - 3)) {
                                if(sommaColonnaSpeciale == 0) {
                                    j--;
                                    sommaRiga -= equilibrio[i][j];
                                }
                            } else {
                                int x = 0;
                                if(sommaRiga >= 0) {
                                    do {
                                        x = NumeriCasuali.estraiIntero((-vitaGolem), (vitaGolem - sommaRiga));
                                    } while(x == 0);
                                } else {
                                    do {
                                        x = NumeriCasuali.estraiIntero((-vitaGolem - sommaRiga), vitaGolem);
                                    } while(x == 0);
                                }
                                equilibrio[i][j] = x;
                                sommaRiga += x;
                                sommaColonnaSpeciale += equilibrio[i][j];
                            }
                        }
                    } else {
                        int x = 0;
                        if(sommaRiga >= 0) {
                            do {
                                x = NumeriCasuali.estraiIntero((-vitaGolem), (vitaGolem - sommaRiga));
                            } while(x == 0);
                        } else {
                            do {
                                x = NumeriCasuali.estraiIntero((-vitaGolem - sommaRiga), vitaGolem);
                            } while(x == 0);
                        }
                        equilibrio[i][j] = x;
                        sommaRiga += x;
                    }

                }
                //Riporto il simmetrico opposto
                if(i > j) {
                    equilibrio[i][j] = -equilibrio[j][i];
                    sommaRiga += equilibrio[i][j];
                }
            }
        }

        for (int i =0; i < numeroElementi; i++) {
            for (int j =0; j < numeroElementi; j++) {
                System.out.print(equilibrio[i][j] + "\t");
            }
            System.out.println("");
        }

    }



}
