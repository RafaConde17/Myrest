package com.example.myrest.Modulo2.Admin_Menu.AsignarEntrada;

import static com.example.myrest.HelperDao.Entradas_TABLE_NAME;
import static com.example.myrest.HelperDao.Menu_entradas_COLUMN_FECHA;
import static com.example.myrest.HelperDao.Menu_entradas_COLUMN_IDENTRADA;
import static com.example.myrest.HelperDao.Menu_entradas_COLUMN_IDUSUARIO;
import static com.example.myrest.HelperDao.Menu_entradas_COLUMN_VALMENU;
import static com.example.myrest.HelperDao.Menu_entradas_TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myrest.HelperDao;

public class DAO_Menu_Entradas extends SQLiteOpenHelper {


    private final Context context;
    public DAO_Menu_Entradas(@Nullable Context context) {
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
    public int checkentrada;
    public void addBookEntradas(String val1,String val2,String fecha, String valmenu,
                        String idusuario, String identrada
    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Menu_entradas_COLUMN_FECHA, fecha);
        cv.put(Menu_entradas_COLUMN_VALMENU, valmenu);
        cv.put(Menu_entradas_COLUMN_IDUSUARIO, idusuario);
        cv.put(Menu_entradas_COLUMN_IDENTRADA, identrada);

        Cursor check = db.rawQuery(
                "Select * from "+ Menu_entradas_TABLE_NAME+ " where "+Menu_entradas_COLUMN_FECHA+" =? " +
                        "and " +  Menu_entradas_COLUMN_IDENTRADA +" =? ",
                new String[]{val1,val2} );

        if ( check.getCount() >0 ){

            Toast.makeText(context, "La Entrada ya esta asignado" , Toast.LENGTH_SHORT).show();

        }else {
            long result = db.insert(Menu_entradas_TABLE_NAME,null, cv);
            if(result == -1){
                Toast.makeText(context, "No se agrego Entrada" , Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Se agrego Entrada" , Toast.LENGTH_SHORT).show();

            }
        }
    }



    public Cursor ListarTodomenuentrada
            (){
        String query =
                "select  "+ Menu_entradas_TABLE_NAME + "._Id, " +
                        Entradas_TABLE_NAME + ".entradas" +  "," +
                        Entradas_TABLE_NAME + ".categoria" + "," +
                        Entradas_TABLE_NAME + ".descripcion"+  ","  +
                        Menu_entradas_TABLE_NAME + ".fecha" +
                        " from " + Entradas_TABLE_NAME +
                        " inner JOIN " +  Menu_entradas_TABLE_NAME +" on "+  Menu_entradas_TABLE_NAME + ".identrada " +"=" + Entradas_TABLE_NAME +"._Id "
                        +" ORDER BY "+  Menu_entradas_TABLE_NAME+ ".fecha desc";
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
                "select  "+ Menu_entradas_TABLE_NAME + "._Id, " +
                        Entradas_TABLE_NAME + ".entradas" +  "," +
                        Entradas_TABLE_NAME + ".categoria" + "," +
                        Entradas_TABLE_NAME + ".descripcion"+  ","  +
                        Menu_entradas_TABLE_NAME + ".fecha" +
                        " from " + Entradas_TABLE_NAME +
                        " inner JOIN " +  Menu_entradas_TABLE_NAME +" on "+  Menu_entradas_TABLE_NAME + ".identrada " +"=" + Entradas_TABLE_NAME +"._Id "
                        +" Where "+ Menu_entradas_TABLE_NAME + ".fecha"+" = ?  "
                        +" ORDER BY "+  Menu_entradas_TABLE_NAME+ ".fecha desc ", new String[]{Buscar} );
        return cursor;
    }

    void deleteOnerow(String id ){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Menu_entradas_TABLE_NAME,"_id= ?", new String[]{id});
        if (result ==1) {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
    }
}





