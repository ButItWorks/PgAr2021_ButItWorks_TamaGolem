import java.util.Objects;

public class Elemento {
    private String nomeElemento;

    public Elemento(String nomeElemento) {
        this.setNomeElemento(nomeElemento);
    }

    public String getNomeElemento() {
        return this.nomeElemento;
    }

    public void setNomeElemento(String nomeElemento) {
        this.nomeElemento = nomeElemento;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Elemento) {
            if (this == o) return true;
            Elemento n = (Elemento) o;
            return this.nomeElemento.equals(n.getNomeElemento());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeElemento);
    }
}
