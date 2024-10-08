package etec.com.br.marialuisa.autpapo_teste;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CadastroUsuario {
    //classe Usuário
    private BancoAutPapo banco;  // Instância do banco
    private int codUsuario;
    private String email;
    private String senha;

    // inicializar o banco e atribuir valores de email e senha
    public CadastroUsuario(Tela_CriarConta tela_criarConta, String email, String senha) {
        this.banco = new BancoAutPapo(tela_criarConta);  // Inicializa a instância do banco com o contexto
        this.email = email;
        this.senha = senha;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {

        this.codUsuario = codUsuario;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getSenha() {

        return senha;
    }

    public void setSenha(String senha) {

        this.senha = senha;
    }
    //Cadastro do usuario
    public boolean cadastrarUsuario() {
        SQLiteDatabase db = banco.getWritableDatabase();  // Abre o banco para escrita
        ContentValues values = new ContentValues();
        values.put("email", this.email);
        values.put("senha", this.senha);

        //incluindo os dados sem o codUsuario por ser auto increment
        long result = db.insert("cadastrousuario", null, values);
        db.close();

        return result != -1;  // Retorna inserção for bem-sucedida
    }

        //Consultar se o usuário já está cadastrado com base no email
        public boolean consultarUsuarioPorEmail(String email) {
            SQLiteDatabase db = banco.getReadableDatabase();  // Abre o banco para leitura
            Cursor cursor = db.query("cadastrousuario",  // Nome da tabela
                    new String[]{"email"},  // Coluna que queremos verificar
                    "email = ?",  // Condição WHERE
                    new String[]{email},
                    null,
                    null,
                    null
            );

            boolean usuarioExiste = cursor.moveToFirst();  // Retorna true se houver resultado
            cursor.close();
            db.close();

            return usuarioExiste;
        }
        //Login usuario
    }



