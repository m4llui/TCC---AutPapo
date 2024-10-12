package etec.com.br.marialuisa.autpapo_teste;

public class Desempenho {
    //classe Desempenho
    private int codDesempenho;

    public int getTotalAcertos() {
        return totalAcertos;
    }

    public void setTotalAcertos(int totalAcertos) {
        this.totalAcertos = totalAcertos;
    }

    public int getTotalErros() {
        return totalErros;
    }

    public void setTotalErros(int totalErros) {
        this.totalErros = totalErros;
    }

    private int totalAcertos;
    private int totalErros;
    private int codCriança;
    private int codAtividade;

    public int getCodDesempenho() {

        return codDesempenho;
    }

    public void setCodDesempenho(int codDesempenho) {

        this.codDesempenho = codDesempenho;
    }


    public int getCodCriança() {
        return codCriança;
    }

    public void setCodCriança(int codCriança) {
        this.codCriança = codCriança;
    }

    public int getCodAtividade() {
        return codAtividade;
    }

    public void setCodAtividade(int codAtividade) {
        this.codAtividade = codAtividade;
    }


}
