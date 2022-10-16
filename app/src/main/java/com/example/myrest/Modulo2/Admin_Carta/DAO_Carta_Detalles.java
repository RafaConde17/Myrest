package com.example.myrest.Modulo2.Admin_Carta;

import static com.example.myrest.HelperDao.Bebidas_TABLE_NAME;
import static com.example.myrest.HelperDao.Carta_bebidas_TABLE_NAME;
import static com.example.myrest.HelperDao.Carta_detalle_COLUMN_FECHA;
import static com.example.myrest.HelperDao.Carta_entradas_TABLE_NAME;
import static com.example.myrest.HelperDao.Carta_platillo_TABLE_NAME;
import static com.example.myrest.HelperDao.Entradas_TABLE_NAME;
import static com.example.myrest.HelperDao.Platillos_TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myrest.HelperDao;

public class DAO_Carta_Detalles extends SQLiteOpenHelper {
    private final Context context;
    public DAO_Carta_Detalles(@Nullable Context context) {
        super(context, HelperDao.DATABASE_NAME, null, HelperDao.DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String tabla : HelperDao.tablas1) {
            db.execSQL(tabla);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public  int CheckLimp;
    public int checkbebida;
    public void addBookdetallecarta(String fecha, String valCarta, String valfecha
            ,String valtipo
    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Carta_detalle_COLUMN_FECHA, fecha);
        cv.put(Carta_detalle_COLUMN_FECHA, valCarta);
        cv.put(Carta_detalle_COLUMN_FECHA, valfecha);
        cv.put(Carta_detalle_COLUMN_FECHA, valtipo);
        long result = db.insert(Carta_detalle_COLUMN_FECHA,null, cv);
    }

    public Cursor ListarTodocarta_platillo() {
            String query =
                "select " +
                        Carta_platillo_TABLE_NAME + "._Id,"+
                        Platillos_TABLE_NAME+ "._Id," +
                        Platillos_TABLE_NAME+ ".platillo," +
                        Platillos_TABLE_NAME +".categoria," +
                        Platillos_TABLE_NAME +".descripcion," +
                        Platillos_TABLE_NAME +".precio from "+ Carta_platillo_TABLE_NAME +
                        " inner join " +Platillos_TABLE_NAME  + " on "+
                        Platillos_TABLE_NAME+ "._Id = " + Carta_platillo_TABLE_NAME +".idplatillo";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = null;
            if (db != null) {
                cursor = db.rawQuery(query, null);
            }
            return cursor;
        }

    public Cursor ListarTodoCartadedia_entrada  () {
        String query =
                "select " +
                        Carta_entradas_TABLE_NAME + "._Id,"+
                        Entradas_TABLE_NAME+ "._Id," +
                        Entradas_TABLE_NAME+ ".entradas," +
                        Entradas_TABLE_NAME +".categoria," +
                        Entradas_TABLE_NAME +".descripcion," +
                        Entradas_TABLE_NAME +".precio from "+ Carta_entradas_TABLE_NAME +
                        " inner join " +Entradas_TABLE_NAME  + " on "+
                        Entradas_TABLE_NAME+ "._Id = " + Carta_entradas_TABLE_NAME +".identrada";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor ListarTodoCartadedia_bebida  () {
        String query =
                "select " +
                        Carta_bebidas_TABLE_NAME + "._Id,"+
                        Bebidas_TABLE_NAME+ "._Id," +
                        Bebidas_TABLE_NAME+ ".Bebidas," +
                        Bebidas_TABLE_NAME +".categoria," +
                        Bebidas_TABLE_NAME +".descripcion," +
                        Bebidas_TABLE_NAME +".precio from "+ Carta_bebidas_TABLE_NAME +
                        " inner join " +Bebidas_TABLE_NAME  + " on "+
                        Bebidas_TABLE_NAME+ "._Id = " + Carta_bebidas_TABLE_NAME +".idbebidas" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public Cursor Listarunoplatillo(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
                "select " +
                        Carta_platillo_TABLE_NAME + "._Id,"+
                        Platillos_TABLE_NAME+ "._Id," +
                        Platillos_TABLE_NAME+ ".platillo," +
                        Platillos_TABLE_NAME +".categoria," +
                        Platillos_TABLE_NAME +".descripcion," +
                        Platillos_TABLE_NAME +".precio from "+ Carta_platillo_TABLE_NAME +
                        " inner join " +Platillos_TABLE_NAME  + " on "+
                        Platillos_TABLE_NAME+ "._Id = " + Carta_platillo_TABLE_NAME +".idplatillo" +
                " where " + Platillos_TABLE_NAME+ ".platillo like ?"

                , new String[]{Buscar+"%"} );
        return cursor;
    }

    public Cursor Listarunoentrada(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
                "select " +
                        Carta_entradas_TABLE_NAME + "._Id,"+
                        Entradas_TABLE_NAME+ "._Id," +
                        Entradas_TABLE_NAME+ ".entradas," +
                        Entradas_TABLE_NAME +".categoria," +
                        Entradas_TABLE_NAME +".descripcion," +
                        Entradas_TABLE_NAME +".precio from "+ Carta_entradas_TABLE_NAME +
                        " inner join " +Entradas_TABLE_NAME  + " on "+
                        Entradas_TABLE_NAME+ "._Id = " + Carta_entradas_TABLE_NAME +".identrada" +
                        " where " + Entradas_TABLE_NAME+ ".entradas like ?"

                , new String[]{Buscar+"%"} );
        return cursor;
    }

    public Cursor Listarunobebida(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
                "select " +
                        Carta_bebidas_TABLE_NAME + "._Id,"+
                        Bebidas_TABLE_NAME+ "._Id," +
                        Bebidas_TABLE_NAME+ ".Bebidas," +
                        Bebidas_TABLE_NAME +".categoria," +
                        Bebidas_TABLE_NAME +".descripcion," +
                        Bebidas_TABLE_NAME +".precio from "+ Carta_bebidas_TABLE_NAME +
                        " inner join " +Bebidas_TABLE_NAME  + " on "+
                        Bebidas_TABLE_NAME+ "._Id = " + Carta_bebidas_TABLE_NAME +".idbebidas" +
                        " where " + Bebidas_TABLE_NAME+ ".Bebidas like ?"

                , new String[]{Buscar+"%"} );
        return cursor;
    }



// Pantalla principal carta

    public Cursor Listarunoplatillo_principal(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
                "select " +
                        Carta_platillo_TABLE_NAME + "._Id,"+
                        Platillos_TABLE_NAME+ "._Id," +
                        Platillos_TABLE_NAME+ ".platillo," +
                        Platillos_TABLE_NAME +".categoria," +
                        Platillos_TABLE_NAME +".descripcion," +
                        Platillos_TABLE_NAME +".precio from "+ Carta_platillo_TABLE_NAME +
                        " inner join " +Platillos_TABLE_NAME  + " on "+
                        Platillos_TABLE_NAME+ "._Id = " + Carta_platillo_TABLE_NAME +".idplatillo" +
                        " where " + Platillos_TABLE_NAME+ ".categoria = ?"

                , new String[]{Buscar} );
        return cursor;
    }

    public Cursor Listarunoentrada_pincipal(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
                "select " +
                        Carta_entradas_TABLE_NAME + "._Id,"+
                        Entradas_TABLE_NAME+ "._Id," +
                        Entradas_TABLE_NAME+ ".entradas," +
                        Entradas_TABLE_NAME +".categoria," +
                        Entradas_TABLE_NAME +".descripcion," +
                        Entradas_TABLE_NAME +".precio from "+ Carta_entradas_TABLE_NAME +
                        " inner join " +Entradas_TABLE_NAME  + " on "+
                        Entradas_TABLE_NAME+ "._Id = " + Carta_entradas_TABLE_NAME +".identrada" +
                        " where " + Entradas_TABLE_NAME+ ".categoria = ?"

                , new String[]{Buscar} );
        return cursor;
    }

    public Cursor Listarunobebida_pincipal(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
                "select " +
                        Carta_bebidas_TABLE_NAME + "._Id,"+
                        Bebidas_TABLE_NAME+ "._Id," +
                        Bebidas_TABLE_NAME+ ".Bebidas," +
                        Bebidas_TABLE_NAME +".categoria," +
                        Bebidas_TABLE_NAME +".descripcion," +
                        Bebidas_TABLE_NAME +".precio from "+ Carta_bebidas_TABLE_NAME +
                        " inner join " +Bebidas_TABLE_NAME  + " on "+
                        Bebidas_TABLE_NAME+ "._Id = " + Carta_bebidas_TABLE_NAME +".idbebidas" +
                        " where " + Bebidas_TABLE_NAME+ ".categoria = ?"

                , new String[]{Buscar} );
        return cursor;
    }

}
