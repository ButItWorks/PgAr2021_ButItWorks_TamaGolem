import java.util.Objects;

public class Iterazione {
    private Elemento primoElemento;
    private Elemento secondoElemento;
    private int potenzaIterazione;

    public Iterazione(Elemento primoElemento, Elemento secondoElemento, int potenzaIterazione) {
        this.primoElemento = primoElemento;
        this.secondoElemento = secondoElemento;
        this.potenzaIterazione = potenzaIterazione;
    }

    public Elemento getPrimoNodo() {
        return this.primoElemento;
    }

    public void setPrimoElemento(Elemento primoElemento) {
        this.primoElemento = primoElemento;
    }

    public Elemento getSecondoNodo() {
        return this.secondoElemento;
    }

    public void setSecondoNodo(Elemento secondoElemento) {
        this.secondoElemento = secondoElemento;
    }

    public int getPotenzaIterazione() {
        return this.potenzaIterazione;
    }

    public void setPotenzaIterazione(int potenzaIterazione) {
        this.potenzaIterazione = potenzaIterazione;
    }

    @Override
    public String toString() {
        return "<" + this.primoElemento.getNomeElemento() +" -> " + this.secondoElemento.getNomeElemento() + "; " + this.getPotenzaIterazione() + ">";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Iterazione) {
            if (this == o) return true;
            Iterazione a = (Iterazione) o;
            return (this.primoElemento.equals(a.getPrimoNodo()) && this.secondoElemento.equals(a.getSecondoNodo()))
                    || (this.primoElemento.equals(a.getSecondoNodo()) && this.secondoElemento.equals(a.getPrimoNodo()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(primoElemento, secondoElemento, potenzaIterazione);
    }
}
