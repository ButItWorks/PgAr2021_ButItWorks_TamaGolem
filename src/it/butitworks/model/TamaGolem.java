package it.butitworks.model;

import java.util.ArrayList;
import java.util.Objects;

public class TamaGolem {

    //attributi
    public static final int MAX_VITA = 20;
    private String nome;
    private int vita;
    private ArrayList<Elemento> pietre = new ArrayList<>();
    private boolean isMorto;

    //costruttori
    public TamaGolem() {}
    public TamaGolem(String nome) {
        this.setVita(MAX_VITA);
        this.setMorto(false);
        this.setNome(nome);
    }
    public TamaGolem(String nome, ArrayList<Elemento> pietre) {
        this.setVita(MAX_VITA);
        this.setMorto(false);
        this.setNome(nome);
        this.setPietre(pietre);
    }

  //getters and setters
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

    /**
     * @return la prima pietra della lista (quella che verra lanciata)
     */
    public Elemento getPietraAttuale() {
        return pietre.get(0);
    }

    /**
     * metodo che calcola il danno
     * @param danno
     * @return la vita dopo il calcolo dei danni
     */
    public int infliggiDanno(int danno) {
        this.setVita(this.vita - danno);
        return this.vita;
    }

    /**
     * metodo che fa girare le pietre (ma va?) come girano nell'apparato digerente del it.butitworks.model.TamaGolem
     * e permette di stabilire la prossima pietra che verra lanciata
     */
    public void giraPietre() {
        Elemento primaPietra = pietre.get(0);

        for(int i = 0; i < pietre.size() - 1; i++) {
            pietre.set(i, pietre.get(i + 1));
        }

        pietre.set(pietre.size() - 1, primaPietra);
    }

    public boolean isSetPietreEquals(TamaGolem golemAvversario) {
        ArrayList<Elemento> pietreAvversario = golemAvversario.getPietre();
        for (int i = 0; i < pietre.size(); i++) {
            if(!pietre.get(i).equals(pietreAvversario.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof TamaGolem) {
            if (this == o) return true;
            TamaGolem t = (TamaGolem) o;
            return (this.getNome().equals(t.getNome()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, vita, pietre, isMorto);
    }

}