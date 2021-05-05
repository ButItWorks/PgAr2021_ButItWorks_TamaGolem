import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nodo {
    private String nomeNodo;
    private int dannoFatto;
    private int dannoSubito;
    private ArrayList<Arco> archiAssociati = new ArrayList<>();

    public Nodo(String nomeNodo) {
        this.setNomeNodo(nomeNodo);
    }

    public int getDannoFatto() {
        return dannoFatto;
    }

    public void setDannoFatto(int dannoFatto) {
        this.dannoFatto = dannoFatto;
    }

    public int getDannoSubito() {
        return dannoSubito;
    }

    public void setDannoSubito(int dannoSubito) {
        this.dannoSubito = dannoSubito;
    }

    public String getNomeNodo() {
        return nomeNodo;
    }

    public void setNomeNodo(String nomeNodo) {
        this.nomeNodo = nomeNodo;
    }

    public ArrayList<Arco> getArchiAssociati() {
        return archiAssociati;
    }

    public void aggiungiArco(Arco a) {
        archiAssociati.add(a);
    }

    public void aggiungiDannoSubito(int danno) {
        this.setDannoSubito(this.getDannoSubito() + danno);
    }

    public void aggiungiDannoFatto(int danno) {
        this.setDannoFatto(this.getDannoFatto() + danno);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Nodo) {
            if (this == o) return true;
            Nodo n = (Nodo) o;
            return this.nomeNodo.equals(n.getNomeNodo());
        }
        return false;
    }

}
