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
        this.banco = new BancoAutPapo(tela_criarConta);
        this.email = email;
        this.senha = senha;
    }
    // inicializar o banco e atribuir valores de email e senha
    public CadastroUsuario(Tela_Login tela_login, String email, String senha) {
        this.banco = new BancoAutPapo(tela_login);  // Inicializa a instância do banco com o contexto
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
    public int cadastrarUsuario() {
        SQLiteDatabase db = banco.getWritableDatabase();  // Abre o banco para escrita
        ContentValues values = new ContentValues();
        values.put("email", this.email);
        values.put("senha", this.senha);

        // Incluindo os dados sem o codUsuario por ser auto-increment
        long result = db.insert("cadastrousuario", null, values);
        db.close();

        // Retorna o código do usuário se a inserção for bem-sucedida, caso contrário, retorna -1
        return result != -1 ? (int) result : -1;
    }


        //Consultando o usuario pelo email
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
        //Login usuario, agora retorna o codUsuario
        public int loginUsuario(String email, String senha) {
            SQLiteDatabase db = banco.getReadableDatabase();
            Cursor cursor = db.rawQuery("select codUsuario from cadastrousuario where email = ? and senha = ?", new String[]{email, senha});

            int codUsuario = -1;
            if (cursor.moveToFirst()) {
                codUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("codUsuario"));  // Retorna o codUsuario se o login for bem-sucedido
            }
            cursor.close();
            db.close();

            return codUsuario;
        }

}




