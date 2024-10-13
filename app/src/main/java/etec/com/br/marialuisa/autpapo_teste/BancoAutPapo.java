package etec.com.br.marialuisa.autpapo_teste;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoAutPapo extends SQLiteOpenHelper {
    //classe do Banco
    private static final String DATABASE_NAME = "bd_autpapo.db";
    private static final int DATABASE_VERSION = 2;

    // Tabelas
    private static final String TABLE_ATIVIDADE = "atividade";
    private static final String TABLE_CADASTRO_CRIANCA = "cadastrocrianca";
    private static final String TABLE_CADASTRO_USUARIO = "cadastrousuario";
    private static final String TABLE_DESMPENHO = "desempenho";

    // Criação das tabelas
    private static final String CREATE_TABLE_ATIVIDADE =
            "CREATE TABLE " + TABLE_ATIVIDADE + " (" +
                    "codAtividade INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nomeAtividade TEXT NOT NULL," +
                    "fase INTEGER NOT NULL);";

    private static final String CREATE_TABLE_CADASTRO_CRIANCA =
            "CREATE TABLE " + TABLE_CADASTRO_CRIANCA + " (" +
                    "codCrianca INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "grau INTEGER NOT NULL," +
                    "nacionalidade TEXT NOT NULL," +
                    "ano INTEGER NOT NULL," +
                    "codUsuario INTEGER," +
                    "FOREIGN KEY(codUsuario) REFERENCES " + TABLE_CADASTRO_USUARIO + "(codUsuario));";

    private static final String CREATE_TABLE_CADASTRO_USUARIO =
            "CREATE TABLE " + TABLE_CADASTRO_USUARIO + " (" +
                    "codUsuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "email TEXT NOT NULL," +
                    "senha TEXT NOT NULL);";

    private static final String CREATE_TABLE_DESMPENHO =
            "CREATE TABLE " + TABLE_DESMPENHO + " (" +
                    "codDesempenho INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "totalAcertos INTEGER NOT NULL," +
                    "totalErros INTEGER NOT NULL," +
                    "codCrianca INTEGER NOT NULL," +
                    "codAtividade INTEGER NOT NULL," +
                    "FOREIGN KEY(codCrianca) REFERENCES " + TABLE_CADASTRO_CRIANCA + "(codCrianca)," +
                    "FOREIGN KEY(codAtividade) REFERENCES " + TABLE_ATIVIDADE + "(codAtividade));";

    public BancoAutPapo (Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ATIVIDADE);
        db.execSQL(CREATE_TABLE_CADASTRO_USUARIO);
        db.execSQL(CREATE_TABLE_CADASTRO_CRIANCA);
        db.execSQL(CREATE_TABLE_DESMPENHO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESMPENHO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CADASTRO_CRIANCA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CADASTRO_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATIVIDADE);
        onCreate(db);
    }
}

