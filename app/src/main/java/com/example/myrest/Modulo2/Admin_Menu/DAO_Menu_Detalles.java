package com.example.myrest.Modulo2.Admin_Menu;

import static com.example.myrest.HelperDao.Bebidas_TABLE_NAME;
import static com.example.myrest.HelperDao.Entradas_TABLE_NAME;
import static com.example.myrest.HelperDao.Menu_bebidas_COLUMN_FECHA;
import static com.example.myrest.HelperDao.Menu_bebidas_COLUMN_ID;
import static com.example.myrest.HelperDao.Menu_bebidas_COLUMN_VALMENU;
import static com.example.myrest.HelperDao.Menu_bebidas_TABLE_NAME;
import static com.example.myrest.HelperDao.Menu_detalle_COLUMN_FECHA;
import static com.example.myrest.HelperDao.Menu_detalle_COLUMN_VALFECHA;
import static com.example.myrest.HelperDao.Menu_detalle_COLUMN_VALMENU;
import static com.example.myrest.HelperDao.Menu_detalle_COLUMN_VALTIPO;
import static com.example.myrest.HelperDao.Menu_detalle_TABLE_NAME;
import static com.example.myrest.HelperDao.Menu_entradas_COLUMN_FECHA;
import static com.example.myrest.HelperDao.Menu_entradas_COLUMN_ID;
import static com.example.myrest.HelperDao.Menu_entradas_COLUMN_VALMENU;
import static com.example.myrest.HelperDao.Menu_entradas_TABLE_NAME;
import static com.example.myrest.HelperDao.Menu_platillo_COLUMN_FECHA;
import static com.example.myrest.HelperDao.Menu_platillo_COLUMN_ID;
import static com.example.myrest.HelperDao.Menu_platillo_COLUMN_VALMENU;
import static com.example.myrest.HelperDao.Menu_platillo_TABLE_NAME;
import static com.example.myrest.HelperDao.Platillos_TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myrest.HelperDao;

public class DAO_Menu_Detalles extends SQLiteOpenHelper {
    private final Context context;
    public DAO_Menu_Detalles(@Nullable Context context) {
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
    public void addBookdetallemenu(String fecha, String valmenu, String valfecha
                                   ,String valtipo
    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Menu_detalle_COLUMN_FECHA, fecha);
        cv.put(Menu_detalle_COLUMN_VALMENU, valmenu);
        cv.put(Menu_detalle_COLUMN_VALFECHA, valfecha);
        cv.put(Menu_detalle_COLUMN_VALTIPO, valtipo);
       long result = db.insert(Menu_detalle_TABLE_NAME,null, cv);
    }

    public Cursor ListarTodomenudetalle_platillo
            (){
        String query =
                "select DISTINCT ("+ Menu_platillo_TABLE_NAME+"."+Menu_platillo_COLUMN_VALMENU+")," +
                        Menu_platillo_TABLE_NAME+"."+Menu_platillo_COLUMN_ID +" , " +
                        Menu_platillo_TABLE_NAME+"."+Menu_platillo_COLUMN_FECHA +" , " +
                        " count(*)  from "+
                        Menu_platillo_TABLE_NAME +
                        " group by "+ Menu_platillo_TABLE_NAME + "."+Menu_platillo_COLUMN_VALMENU  ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor ListarTodomenudetalle_entrada
            (){
        String query =
                "select DISTINCT ("+ Menu_entradas_TABLE_NAME+"."+Menu_entradas_COLUMN_VALMENU+")," +
                        Menu_entradas_TABLE_NAME+"."+Menu_entradas_COLUMN_ID +" , " +
                        Menu_entradas_TABLE_NAME+"."+Menu_entradas_COLUMN_FECHA +" , " +
                       " count(*)  from "+
                        Menu_entradas_TABLE_NAME +
                        " group by "+ Menu_entradas_TABLE_NAME + "."+Menu_entradas_COLUMN_VALMENU  ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor ListarTodomenudetalle_bebida
            (){
        String query =
                "select DISTINCT ("+ Menu_bebidas_TABLE_NAME+"."+Menu_bebidas_COLUMN_VALMENU+")," +
                        Menu_bebidas_TABLE_NAME+"."+Menu_bebidas_COLUMN_ID +" , " +
                        Menu_bebidas_TABLE_NAME+"."+Menu_bebidas_COLUMN_FECHA +" , " +
                        "count(*)  from "+
                        Menu_bebidas_TABLE_NAME +
                        " group by "+ Menu_bebidas_TABLE_NAME + "."+Menu_bebidas_COLUMN_VALMENU  ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor ListarTodomenudedia_platillo(String Buscar){
    SQLiteDatabase DB= this.getReadableDatabase();
    Cursor cursor   = DB.rawQuery(
                "select " +
                       Menu_platillo_TABLE_NAME + "._Id,"+
                        Menu_platillo_TABLE_NAME +".fecha,+" +
                        Platillos_TABLE_NAME+ "._Id," +
                        Platillos_TABLE_NAME+ ".platillo," +
                        Platillos_TABLE_NAME +".categoria," +
                        Platillos_TABLE_NAME +".descripcion from "+ Menu_platillo_TABLE_NAME +
                       " inner join " +Platillos_TABLE_NAME  + " on "+
                        Platillos_TABLE_NAME+ "._Id = " + Menu_platillo_TABLE_NAME +".idplatillo" +
                        " where "+ Menu_platillo_TABLE_NAME + ".fecha = ?", new String[]{Buscar}
    );
        return cursor;
    }
    public Cursor ListarTodomenudedia_entrada(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
                "select " +
                        Menu_entradas_TABLE_NAME + "._Id,"+
                        Menu_entradas_TABLE_NAME +".fecha,+" +
                        Entradas_TABLE_NAME+ "._Id," +
                        Entradas_TABLE_NAME+ ".entradas," +
                        Entradas_TABLE_NAME +".categoria," +
                        Entradas_TABLE_NAME +".descripcion from "+ Menu_entradas_TABLE_NAME +
                        " inner join " +Entradas_TABLE_NAME  + " on "+
                        Entradas_TABLE_NAME+ "._Id = " + Menu_entradas_TABLE_NAME +".identrada" +
                        " where "+ Menu_entradas_TABLE_NAME + ".fecha = ?", new String[]{Buscar}
        );
        return cursor;
    }
    public Cursor ListarTodomenudedia_bebida(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
                "select " +
                        Menu_bebidas_TABLE_NAME + "._Id,"+
                        Menu_bebidas_TABLE_NAME +".fecha,+" +
                        Bebidas_TABLE_NAME+ "._Id," +
                        Bebidas_TABLE_NAME+ ".Bebidas," +
                        Bebidas_TABLE_NAME +".categoria," +
                        Bebidas_TABLE_NAME +".descripcion from "+ Menu_bebidas_TABLE_NAME +
                        " inner join " +Bebidas_TABLE_NAME  + " on "+
                        Bebidas_TABLE_NAME+ "._Id = " + Menu_bebidas_TABLE_NAME +".idbebidas" +
                        " where "+ Menu_bebidas_TABLE_NAME + ".fecha = ?", new String[]{Buscar}
        );
        return cursor;
    }


    public Cursor Listarunoplatillo(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
       "select DISTINCT ("+ Menu_platillo_TABLE_NAME+"."+Menu_platillo_COLUMN_VALMENU+")," +
               Menu_platillo_TABLE_NAME+"."+Menu_platillo_COLUMN_ID +" , " +
               Menu_platillo_TABLE_NAME+"."+Menu_platillo_COLUMN_FECHA +" , " +
        " count(*)  from "+
               Menu_platillo_TABLE_NAME +
        " where "
         + Menu_platillo_TABLE_NAME+"."+Menu_platillo_COLUMN_FECHA +" = ?"   +
        " group by "+ Menu_platillo_TABLE_NAME + "."+Menu_platillo_COLUMN_VALMENU
                , new String[]{Buscar} );
        return cursor;

    }
    public Cursor Listarunoentrada(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
        "select DISTINCT ("+ Menu_entradas_TABLE_NAME+"."+Menu_entradas_COLUMN_VALMENU+")," +
                Menu_entradas_TABLE_NAME+"."+Menu_entradas_COLUMN_ID +" , " +
                Menu_entradas_TABLE_NAME+"."+Menu_entradas_COLUMN_FECHA +
                ",count(*)  from "+
                Menu_entradas_TABLE_NAME +
        " where "+ Menu_entradas_TABLE_NAME+"."+Menu_entradas_COLUMN_FECHA +" = ?"   +
        " group by "+ Menu_entradas_TABLE_NAME + "."+Menu_entradas_COLUMN_VALMENU
        , new String[]{Buscar} );
        return cursor;

    }
    public Cursor Listarunobebida(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
       "select DISTINCT ("+ Menu_bebidas_TABLE_NAME+"."+Menu_bebidas_COLUMN_VALMENU+")," +
               Menu_bebidas_TABLE_NAME+"."+Menu_bebidas_COLUMN_ID +" , " +
               Menu_bebidas_TABLE_NAME+"."+Menu_bebidas_COLUMN_FECHA +
               ",count(*)  from "+ Menu_bebidas_TABLE_NAME +
        " where "+ Menu_bebidas_TABLE_NAME+"."+Menu_detalle_COLUMN_VALFECHA +" = ?"   +
        " group by "+ Menu_bebidas_TABLE_NAME + "."+Menu_bebidas_COLUMN_VALMENU  , new String[]{Buscar} );
        return cursor;
    }

}
