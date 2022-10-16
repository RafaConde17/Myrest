package com.example.myrest.Modulo2.Admin_Menu.AsignarBebidas;

import static com.example.myrest.HelperDao.Bebidas_TABLE_NAME;
import static com.example.myrest.HelperDao.Menu_bebidas_COLUMN_FECHA;
import static com.example.myrest.HelperDao.Menu_bebidas_COLUMN_IDBEBIDA;
import static com.example.myrest.HelperDao.Menu_bebidas_COLUMN_IDUSUARIO;
import static com.example.myrest.HelperDao.Menu_bebidas_COLUMN_VALMENU;
import static com.example.myrest.HelperDao.Menu_bebidas_TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myrest.HelperDao;

public class DAO_Menu_Bebidas extends SQLiteOpenHelper {
    private final Context context;
    public DAO_Menu_Bebidas(@Nullable Context context) {
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
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
    public  int CheckLimp;
    public int checkbebida;
    public void addBookbebida(String val1,String val2,String fecha, String valmenu,
                                String idusuario, String idbebidas
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Menu_bebidas_COLUMN_FECHA, fecha);
        cv.put(Menu_bebidas_COLUMN_VALMENU, valmenu);
        cv.put(Menu_bebidas_COLUMN_IDUSUARIO, idusuario);
        cv.put(Menu_bebidas_COLUMN_IDBEBIDA, idbebidas);

        Cursor check = db.rawQuery(
                "Select * from " + Menu_bebidas_TABLE_NAME + " where " + Menu_bebidas_COLUMN_FECHA + " =? " +
                        "and " + Menu_bebidas_COLUMN_IDBEBIDA + " =? ",
                new String[]{val1, val2});

        if (check.getCount() > 0) {

            Toast.makeText(context, "La bebida ya esta asignado", Toast.LENGTH_SHORT).show();

        } else {
            long result = db.insert(Menu_bebidas_TABLE_NAME, null, cv);
            if (result == -1) {
                Toast.makeText(context, "No se agrego Bebida", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Se agrego Bebida", Toast.LENGTH_SHORT).show();

            }
        }

    }


    public Cursor ListarTodomenubebida
            (){
        String query =
                "select  "+ Menu_bebidas_TABLE_NAME + "._Id, " +
                        Bebidas_TABLE_NAME + ".Bebidas" +  "," +
                        Bebidas_TABLE_NAME + ".categoria" + "," +
                        Bebidas_TABLE_NAME + ".descripcion"+  ","  +
                        Menu_bebidas_TABLE_NAME + ".fecha" +
                        " from " + Bebidas_TABLE_NAME +
                        " inner JOIN " +  Menu_bebidas_TABLE_NAME +" on "+
                        Menu_bebidas_TABLE_NAME
                        + ".idbebidas " +"=" + Bebidas_TABLE_NAME +"._Id "
                        +" ORDER BY "+  Menu_bebidas_TABLE_NAME+ ".fecha desc";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor Listaruno(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery(
                "select  "+ Menu_bebidas_TABLE_NAME + "._Id, " +
                        Bebidas_TABLE_NAME + ".Bebidas" +  "," +
                        Bebidas_TABLE_NAME + ".categoria" + "," +
                        Bebidas_TABLE_NAME + ".descripcion"+  ","  +
                        Menu_bebidas_TABLE_NAME + ".fecha" +
                        " from " + Bebidas_TABLE_NAME +
                        " inner JOIN " +  Menu_bebidas_TABLE_NAME +" on "+  Menu_bebidas_TABLE_NAME
                        + ".idbebidas " +"=" + Bebidas_TABLE_NAME +"._Id "
                        +" Where "+ Menu_bebidas_TABLE_NAME + ".fecha"+" = ?  "
                        +" ORDER BY "+  Menu_bebidas_TABLE_NAME+ ".fecha desc ", new String[]{Buscar} );
        return cursor;
    }
}
