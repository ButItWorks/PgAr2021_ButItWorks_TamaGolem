import java.util.ArrayList;

public class Giocatore {

    private String nome;

    private ArrayList<TamaGolem> squadra = new ArrayList<>();

    private TamaGolem tamaGolemInCampo;

    public Giocatore() { }

    public Giocatore(String nome, ArrayList<TamaGolem> squadra) {
        this.nome = nome;
        this.squadra = squadra;
        this.tamaGolemInCampo = squadra.get(0);
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

    public TamaGolem getTamaGolemInCampo() {
        return tamaGolemInCampo;
    }

    public void setTamaGolemInCampo(TamaGolem tamaGolemInCampo) {
        this.tamaGolemInCampo = tamaGolemInCampo;
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

    //questo metodo a cosa serve??
    public boolean scambiaGolemInCampo(String nomeTamaGolem) {
        boolean trovato = false;
        TamaGolem golemCercato = new TamaGolem();

        for (TamaGolem golem : this.squadra) {
            if(golem.getNome().equals(nomeTamaGolem)) {
                trovato = true;
                golemCercato = golem;
            }
        }

        if(trovato && !golemCercato.isMorto()) {
            this.setTamaGolemInCampo(golemCercato);
            return true;
        }

        return false;
    }

    /**
     *
     * @return true se la squadra è esausta
     */
    public boolean isSquadraEsausta() {
        for (TamaGolem golem : this.getSquadra()) {
            if(!golem.isMorto()) {
                return false;
            }
        }
        return true;
    }
}
