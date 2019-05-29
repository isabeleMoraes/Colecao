package imo.moraes.isabele.com.br.colecao.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CriaBanco extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "Colecao";
    public static final int VERSAO = 1;


    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tabelaActionFigure = "CREATE TABLE \"action_figure\" (\n" +
                "\t\"_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"nome\"\tTEXT NOT NULL,\n" +
                "\t\"quantidade\"\tNUMERIC NOT NULL,\n" +
                "\t\"dt_compra\"\tTEXT NOT NULL,\n" +
                "\t\"valor\"\tREAL,\n" +
                "\t\"observacao\"\tTEXT,\n" +
                "\t\"preferido\"\tINTEGER,\n" +
                "\t\"marca\"\tINTEGER NOT NULL\n" +
                ");";

        sqLiteDatabase.execSQL(tabelaActionFigure);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String deletaTabelaActionFigure = "DROP TABLE IF EXISTS action_figure";

        sqLiteDatabase.execSQL(deletaTabelaActionFigure);
        onCreate(sqLiteDatabase);
    }
}
