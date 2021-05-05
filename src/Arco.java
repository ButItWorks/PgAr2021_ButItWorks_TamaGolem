import java.util.Objects;

public class Arco {
    private Nodo primoNodo;
    private Nodo secondoNodo;
    private int pesoArco;

    public Arco(Nodo primoNodo, Nodo secondoNodo, int pesoArco) {
        this.primoNodo = primoNodo;
        this.secondoNodo = secondoNodo;
        this.pesoArco = pesoArco;
    }

    public Nodo getPrimoNodo() {
        return primoNodo;
    }

    public void setPrimoNodo(Nodo primoNodo) {
        this.primoNodo = primoNodo;
    }

    public Nodo getSecondoNodo() {
        return secondoNodo;
    }

    public void setSecondoNodo(Nodo secondoNodo) {
        this.secondoNodo = secondoNodo;
    }

    public int getPesoArco() {
        return pesoArco;
    }

    public void setPesoArco(int pesoArco) {
        this.pesoArco = pesoArco;
    }

    @Override
    public String toString() {
        return "<" + this.primoNodo.getNomeNodo() +" -> " + this.secondoNodo.getNomeNodo() + "; " + this.pesoArco + ">";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Arco) {
            if (this == o) return true;
            Arco a = (Arco) o;
            return (this.primoNodo.equals(a.getPrimoNodo()) && this.secondoNodo.equals(a.getSecondoNodo()))
                    || (this.primoNodo.equals(a.getSecondoNodo()) && this.secondoNodo.equals(a.getPrimoNodo()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(primoNodo, secondoNodo, pesoArco);
    }
}
