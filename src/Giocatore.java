import java.util.ArrayList;

public class Giocatore {

    private String nome;

    private ArrayList<TamaGolem> squadra = new ArrayList<>();

    public Giocatore(String nome, ArrayList<TamaGolem> squadra) {
        this.nome = nome;
        this.squadra = squadra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<TamaGolem> getSquadra() {
        return squadra;
    }

    public void setSquadra(ArrayList<TamaGolem> squadra) {
        this.squadra = squadra;
    }

    public int getNumeroGolemVivi() {
        int numeroGolemVivi = 0;
        for (TamaGolem golem : squadra) {
            if(!golem.isMorto()) {
                numeroGolemVivi ++;
            }
        }
        return numeroGolemVivi;
    }
}
