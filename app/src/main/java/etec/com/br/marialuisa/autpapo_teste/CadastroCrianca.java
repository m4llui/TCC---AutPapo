package etec.com.br.marialuisa.autpapo_teste;

import android.annotation.SuppressLint;
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
        this.banco = new BancoAutPapo(tela_cadastro_crianca);
        this.nome = nome;
        this.grau = grau;
        this.nacionalidade = nacionalidade;
        this.ano = ano;
    }

    // Construtor para consultar crianças
    public CadastroCrianca(Tela_Desempenho tela_desempenho) {
        this.banco = new BancoAutPapo(tela_desempenho);
    }

    // Construtor para consultar crianças
    public CadastroCrianca(Tela_AcertosErros tela_acertosErros) {
        this.banco = new BancoAutPapo(tela_acertosErros);
    }
    public CadastroCrianca(Context context) {
        this.banco = new BancoAutPapo(context);
    }


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
    public long cadastrarCrianca(String nome, int grau, String nacionalidade, int ano, int codUsuario) {
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("grau", grau);
        values.put("nacionalidade", nacionalidade);
        values.put("ano", ano);
        values.put("codUsuario", codUsuario);

        long codCrianca = db.insert("cadastrocrianca", null, values);
        db.close();

        return codCrianca;
    }


    // Método para consultar crianças por codUsuario e retornar uma lista formatada
    public ArrayList<SpannableString> consultaCriancaPorUsuario(Context context, int codUsuario, ArrayList<Integer> codCriancaList) {
        SQLiteDatabase db = banco.getReadableDatabase();
        ArrayList<SpannableString> listaCriancas = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from cadastrocrianca where codUsuario = ?", new String[]{String.valueOf(codUsuario)});

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                int grau = cursor.getInt(cursor.getColumnIndexOrThrow("grau"));
                int codigo = cursor.getInt(cursor.getColumnIndexOrThrow("codCrianca"));
                int anoNascimento = cursor.getInt(cursor.getColumnIndexOrThrow("ano"));

                // Calcular idade
                int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
                int idade = anoAtual - anoNascimento;

                String texto =  nome + ", " + idade + " anos\n" + "NÍVEL " + grau + " Meu Desempenho";

                SpannableString spannable = new SpannableString(texto);

                int startNome = 0;
                int endNome = nome.length();
                int startIdade = endNome + 2; // 2 é para incluir ", " entre nome e idade
                int endIdade = startIdade + String.valueOf(idade).length() + 5;

                //cor azul
                spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.azul)), startNome, endIdade, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startNome, endIdade, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                //cor amarela
                int startGrau = texto.indexOf("NÍVEL ") + "NÍVEL ".length();
                int endGrau = startGrau + String.valueOf(grau).length();
                spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startGrau, endGrau, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.amarelo)), startGrau - "NÍVEL ".length(), endGrau, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Corrigindo o índice de "NÍVEL"

                //Sublinhando
                int startClique = texto.indexOf("Meu Desempenho");
                int endClique = startClique + "Meu Desempenho".length();
                spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.azul)), startClique, endClique, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new android.text.style.UnderlineSpan(), startClique, endClique, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                listaCriancas.add(spannable);
                codCriancaList.add(codigo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaCriancas;
    }

    //Consultar criança por codCriança
    @SuppressLint("Range")
    public String[] ConsultaPorCodigo(int codCriança) {
        SQLiteDatabase db = banco.getReadableDatabase();

        // Corrigindo a consulta para a tabela 'cadastrocrianca'
        Cursor cursor = db.rawQuery("select nome, grau, ano from cadastrocrianca " +
                "where codCrianca = ?", new String[]{String.valueOf(codCriança)});

        String[] info = null; // Array para armazenar as informações

        if (cursor.moveToFirst()) {
            info = new String[3];
            info[0] = cursor.getString(cursor.getColumnIndex("nome"));
            int anoNascimento = cursor.getInt(cursor.getColumnIndex("ano"));

            // Calculando a idade
            int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
            int idade = anoAtual - anoNascimento;

            info[1] = String.valueOf(idade);
            info[2] = cursor.getString(cursor.getColumnIndex("grau"));
        }

        cursor.close();
        db.close();

        return info;
    }
   //retornar codCriança
   public int ConsultaCodCrianca(int codCriança) {
       SQLiteDatabase db = banco.getReadableDatabase();
       Cursor cursor = db.rawQuery("select * from cadastrocrianca where codCrianca = ?", new String[]{String.valueOf(codCriança)});

       int codigoCriança = -1;  // Nome da variável alterado para evitar conflito
       if (cursor.moveToFirst()) {
           codigoCriança = cursor.getInt(cursor.getColumnIndexOrThrow("codCrianca"));
       }
       cursor.close();
       db.close();

       return codigoCriança;
   }

}