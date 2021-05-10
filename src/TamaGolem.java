import java.util.ArrayList;

public class TamaGolem {
    private static final int MAX_VITA = 10;

    private String nome;

    private int vita;

    private ArrayList<Elemento> pietre = new ArrayList<>();

    private boolean isMorto;

    public TamaGolem(String nome, ArrayList<Elemento> pietre) {
        this.setVita(MAX_VITA);
        this.setMorto(false);
        this.setNome(nome);
        this.setPietre(pietre);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        if(vita <= 0) {
            this.vita = 0;
            this.setMorto(true);
        } else {
            this.vita = vita;
        }
    }

    public ArrayList<Elemento> getPietre() {
        return pietre;
    }

    public void setPietre(ArrayList<Elemento> pietre) {
        this.pietre = pietre;
    }

    public boolean isMorto() {
        return isMorto;
    }

    public void setMorto(boolean morto) {
        isMorto = morto;
    }

    public Elemento getPietraAttuale() {
        return pietre.get(0);
    }

    public int infliggiDanno(int danno) {
        this.setVita(this.vita - danno);
        return this.vita;
    }

    public void giraPietre() {
        Elemento primaPietra = pietre.get(0);

        for(int i = 0; i < pietre.size(); i++) {
            pietre.set(i, pietre.get(i + 1));
        }

        pietre.set(pietre.size() - 1, primaPietra);
    }
}
