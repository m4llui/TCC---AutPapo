package etec.com.br.marialuisa.autpapo_teste;

public class Atividade {
    //classe atividadee
    private int codAtividade;
    private String nomeAtividade;
    private int fase;

    public Atividade(int codAtividade, String nomeAtividade, int fase) {
        this.codAtividade = codAtividade;
        this.nomeAtividade = nomeAtividade;
        this.fase = fase;
    }

    public int getCodAtividade() {
        return codAtividade;
    }

    public void setCodAtividade(int codAtividade) {
        this.codAtividade = codAtividade;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }


}
