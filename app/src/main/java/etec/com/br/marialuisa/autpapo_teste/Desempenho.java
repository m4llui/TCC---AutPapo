package etec.com.br.marialuisa.autpapo_teste;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class Desempenho {
    // Classe Desempenho
    private int codDesempenho;
    private BancoAutPapo banco;  // Instância do banco

    private int totalAcertos;
    private int totalErros;
    private int codCriança;
    private int codAtividade;

    // Construtor que recebe o contexto da aplicação
    public Desempenho(Context context) {
        this.banco = new BancoAutPapo(context); // Inicializa a instância do banco
    }

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

    //INSERINDO
    public void inserirDesempenho(int codCrianca, int totalAcertos, int totalErros, int codAtividade, int codUsuario) {
        SQLiteDatabase db = banco.getWritableDatabase(); // Usando a instância do banco

        // Cria um ContentValues para armazenar os valores a serem inseridos
        ContentValues values = new ContentValues();
        values.put("codCrianca", codCrianca);
        values.put("totalAcertos", totalAcertos);
        values.put("totalErros", totalErros);
        values.put("codAtividade", codAtividade); // Adiciona codAtividade
        values.put("codUsuario", codUsuario); // Adiciona codUsuario

        // Insere os dados na tabela de desempenho
        db.insert(BancoAutPapo.TABLE_DESMPENHO, null, values);

        db.close(); // Fecha o banco de dados
    }



    //UPDATE PARA ATUALIZANDO OS ERROS E ACERTOS
    public void updateAcertosErros(int codCrianca, int totalAcertos, int totalErros) {
        SQLiteDatabase db = banco.getWritableDatabase(); // Usando a instância do banco

        // Cria um ContentValues para armazenar os novos valores
        ContentValues values = new ContentValues();
        values.put("totalAcertos", totalAcertos);
        values.put("totalErros", totalErros);

        // Atualiza a tabela
        db.update(BancoAutPapo.TABLE_DESMPENHO, values, "codCrianca = ?", new String[]{String.valueOf(codCrianca)});


        db.close();
    }
}
