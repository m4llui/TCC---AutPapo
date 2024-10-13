package etec.com.br.marialuisa.autpapo_teste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import java.util.ArrayList;
import java.util.Calendar;

public class CadastroCrianca {
    // Atributos da classe
    private BancoAutPapo banco;
    private int codCriança;
    private String nome;
    private int grau;
    private String nacionalidade;
    private int ano;
    private int codUsuario;

    // Construtor para cadastrar criança
    public CadastroCrianca(Tela_Cadastro_Crianca tela_cadastro_crianca, String nome, int grau, String nacionalidade, int ano) {
        this.banco = new BancoAutPapo(tela_cadastro_crianca); // Inicializa a instância do banco com o contexto
        this.nome = nome;
        this.grau = grau;
        this.nacionalidade = nacionalidade;
        this.ano = ano;
    }

    // Construtor para consultar crianças
    public CadastroCrianca(Tela_Desempenho tela_desempenho) {
        this.banco = new BancoAutPapo(tela_desempenho); // Inicializa a instância do banco com o contexto
    }

    // Métodos getters e setters
    public int getCodCriança() {
        return codCriança;
    }

    public void setCodCriança(int codCriança) {
        this.codCriança = codCriança;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getCodUsuario() {

        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    // Método para cadastrar criança no banco de dados
    public boolean cadastrarCrianca(String nome, int grau, String nacionalidade, int ano) {
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("grau", grau);
        values.put("nacionalidade", nacionalidade);
        values.put("ano", ano);
        values.put("codUsuario", codUsuario);

        long result = db.insert("cadastrocrianca", null, values);
        db.close();

        return result != -1; // Retorna true se a inserção for bem-sucedida
    }

    // Método para consultar crianças e retornar uma lista formatada
    public ArrayList<SpannableString> consultaCrianca(Context context) { // Adicionando o contexto como parâmetro
        SQLiteDatabase db = banco.getReadableDatabase();
        ArrayList<SpannableString> listaCriancas = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM cadastrocrianca", null);

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                int grau = cursor.getInt(cursor.getColumnIndexOrThrow("grau"));
                int anoNascimento = cursor.getInt(cursor.getColumnIndexOrThrow("ano"));

                // Calcula a idade com base no ano de nascimento
                int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
                int idade = anoAtual - anoNascimento;

                String texto = nome + ", " + idade + " anos\n" + "NÍVEL " + grau + " Clique aqui"; // Adicionando espaço antes de "Clique aqui"

                 // Criar um SpannableString
                SpannableString spannable = new SpannableString(texto);

                // Definir a cor azul para nome e idade
                int startNome = 0;
                int endNome = nome.length();
                int startIdade = endNome + 2; // 2 é para incluir ", " entre nome e idade
                int endIdade = startIdade + String.valueOf(idade).length() + 5;

                // Definir a cor azul para nome e idade
                spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.azul)), startNome, endIdade, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startNome, endIdade, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                 // Definir a cor amarela para grau
                int startGrau = texto.indexOf("NÍVEL ") + "NÍVEL ".length();
                int endGrau = startGrau + String.valueOf(grau).length();
                spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startGrau, endGrau, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.amarelo)), startGrau - "NÍVEL ".length(), endGrau, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Corrigindo o índice de "NÍVEL"

                // Definir estilo para "Clique aqui" (sublinhado)
                int startClique = texto.indexOf("Clique aqui");
                int endClique = startClique + "Clique aqui".length();
                spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.azul)), startClique, endClique, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new android.text.style.UnderlineSpan(), startClique, endClique, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                listaCriancas.add(spannable);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaCriancas; // Retorna a lista com os dados das crianças
    }
}