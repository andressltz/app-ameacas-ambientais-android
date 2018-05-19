package feevale.br.ameaasambientais;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AmeacasAmbientaisDAO {

    private static final int VERSION = 1;

    private static final String DB_NAME = "ameacas_ambientais.db";

    private static final String TAB_AMEACAS_AMBIENTAIS = "AmeacaAmbiental";

    private static final String COL_ID_AMEACA_AMBIENTAL = "IdAmeacaAmbiental";

    private static final String COL_BAIRRO = "Bairro";

    private static final String COL_ENDERECO = "Endereco";

    private static final String COL_IMPACTO = "Impacto";

    private static final String COL_AMEACA = "Ameaca";

    private static final String COL_DTATUALIZACAO = "DtAtualizacao";

    private Context context;

    private SQLiteDatabase db;

    private DBHelper helper;

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TAB_AMEACAS_AMBIENTAIS + "(" +
            COL_ID_AMEACA_AMBIENTAL + " integer primary key autoincrement," +
            COL_BAIRRO + " text not null," +
            COL_ENDERECO + " text not null," +
            COL_IMPACTO + " text not null," +
            COL_AMEACA + " text not null," +
            COL_DTATUALIZACAO + " date not null" +
        ")";

    public AmeacasAmbientaisDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context, DB_NAME, null, VERSION);
        db = helper.getWritableDatabase();
    }

    public void save(AmeacaAmbiental ameacaAmbiental) {
        ContentValues values = new ContentValues();
        values.put(COL_BAIRRO, ameacaAmbiental.getBairro());
        values.put(COL_ENDERECO, ameacaAmbiental.getEndereco());
        values.put(COL_IMPACTO, ameacaAmbiental.getImpacto());
        values.put(COL_AMEACA, ameacaAmbiental.getAmeaca());
        values.put(COL_DTATUALIZACAO, new Date().toString());
        db.insert(TAB_AMEACAS_AMBIENTAIS, "", values);
    }

    public void update(AmeacaAmbiental ameacaAmbiental) {
        ContentValues values = new ContentValues();
        values.put(COL_BAIRRO, ameacaAmbiental.getBairro());
        values.put(COL_ENDERECO, ameacaAmbiental.getEndereco());
        values.put(COL_IMPACTO, ameacaAmbiental.getImpacto());
        values.put(COL_AMEACA, ameacaAmbiental.getAmeaca());
        values.put(COL_DTATUALIZACAO, new Date().toString());

        String[] whereArgs = new String[] { ameacaAmbiental.getId().toString() };

        db.update(TAB_AMEACAS_AMBIENTAIS, values, "id = ?", whereArgs);
//        db.update(TAB_AMEACAS_AMBIENTAIS, values, COL_ID_AMEACA_AMBIENTAL + "=" + ameacaAmbiental.getId(), whereArgs);
    }

    public Integer delete(int ameacaAmbientalId) {
        return db.delete(TAB_AMEACAS_AMBIENTAIS, COL_ID_AMEACA_AMBIENTAL + "=" + ameacaAmbientalId, null);
    }

    public List<AmeacaAmbiental> findAll() {
        String[] columns = { COL_ID_AMEACA_AMBIENTAL, COL_BAIRRO, COL_ENDERECO, COL_AMEACA, COL_IMPACTO, COL_DTATUALIZACAO };
        // FIXME: fix the getList because de cursor are equals in both finds

        Cursor cursor = db.query(TAB_AMEACAS_AMBIENTAIS, columns, null, null, null, null, COL_ID_AMEACA_AMBIENTAL);
        return getList(cursor);
    }

    public List<AmeacaAmbiental> findLast() {
        String[] columns = { COL_ID_AMEACA_AMBIENTAL, COL_BAIRRO, COL_ENDERECO, COL_AMEACA, COL_IMPACTO, COL_DTATUALIZACAO };

        Cursor cursor = db.query(TAB_AMEACAS_AMBIENTAIS, columns, null, null, null, null, COL_DTATUALIZACAO);
        return getList(cursor);
    }

    private List<AmeacaAmbiental> getList(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return new ArrayList<>();
        }

        List<AmeacaAmbiental> listAmeacas = new ArrayList<>();

        AmeacaAmbiental ameaca = new AmeacaAmbiental();
        cursor.moveToFirst();
        do {
            ameaca.setId(cursor.getInt(cursor.getColumnIndex(COL_ID_AMEACA_AMBIENTAL)));
            ameaca.setImpacto(cursor.getString(cursor.getColumnIndex(COL_IMPACTO)));
            ameaca.setBairro(cursor.getString(cursor.getColumnIndex(COL_BAIRRO)));
            ameaca.setEndereco(cursor.getString(cursor.getColumnIndex(COL_ENDERECO)));
            ameaca.setAmeaca(cursor.getString(cursor.getColumnIndex(COL_AMEACA)));
//            ameaca.setDtAtualizacao(new Date(cursor.getString(cursor.getColumnIndex(COL_DTATUALIZACAO))));
            listAmeacas.add(ameaca);
        } while (cursor.moveToNext());

        return listAmeacas;
    }

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String nome, SQLiteDatabase.CursorFactory factory, int versao) {
            super(context, nome, factory, versao);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("DATABASE", "criou a tabela: " + SQL_CREATE_TABLE);
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TAB_AMEACAS_AMBIENTAIS);
            onCreate(db);
        }

    }


}
