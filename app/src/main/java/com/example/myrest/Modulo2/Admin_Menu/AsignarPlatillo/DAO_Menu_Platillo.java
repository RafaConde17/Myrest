package com.example.myrest.Modulo2.Admin_Menu.AsignarPlatillo;


import static com.example.myrest.HelperDao.Menu_platillo_COLUMN_FECHA;
import static com.example.myrest.HelperDao.Menu_platillo_COLUMN_IDPLATILLO;
import static com.example.myrest.HelperDao.Menu_platillo_COLUMN_IDUSUARIO;
import static com.example.myrest.HelperDao.Menu_platillo_COLUMN_VALMENU;
import static com.example.myrest.HelperDao.Menu_platillo_TABLE_NAME;
import static com.example.myrest.HelperDao.Platillos_TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myrest.HelperDao;

public class DAO_Menu_Platillo extends SQLiteOpenHelper {

    private final Context context;
    public DAO_Menu_Platillo(@Nullable Context context) {
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
    public int checkplatillo;



    public void addBookplatillo(String val1, String val2,String fecha, String valmenu,
     String idusuario, String idplatillo
        ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Menu_platillo_COLUMN_FECHA, fecha);
        cv.put(Menu_platillo_COLUMN_VALMENU, valmenu);
        cv.put(Menu_platillo_COLUMN_IDUSUARIO, idusuario);
        cv.put(Menu_platillo_COLUMN_IDPLATILLO, idplatillo);

        Cursor check = db.rawQuery(
   "Select * from "+ Menu_platillo_TABLE_NAME+ " where "+Menu_platillo_COLUMN_FECHA+" =? and " +
           Menu_platillo_COLUMN_IDPLATILLO +" =? ",
                new String[]{val1,val2} );

        if ( check.getCount() >0 ){

              Toast.makeText(context, "El Platillo ya esta asignado" , Toast.LENGTH_SHORT).show();

        }else {
            long result = db.insert(Menu_platillo_TABLE_NAME,null, cv);
            if(result == -1){
                Toast.makeText(context, "No se agrego platillo" , Toast.LENGTH_SHORT).show();
               }else {
                Toast.makeText(context, "Se agrego platillo" , Toast.LENGTH_SHORT).show();

            }
    }
    }


    public Cursor ListarTodomenuplatillo
            (){
        String query =
                "select  "+ Menu_platillo_TABLE_NAME + "._Id, " +
                        Platillos_TABLE_NAME + ".platillo" +  "," +
                        Platillos_TABLE_NAME + ".categoria" + "," +
                        Platillos_TABLE_NAME + ".descripcion"+  ","  +
                        Menu_platillo_TABLE_NAME + ".fecha" +
                        " from " + Platillos_TABLE_NAME +
                        " inner JOIN " +  Menu_platillo_TABLE_NAME +" on "+  Menu_platillo_TABLE_NAME + ".idplatillo " +"=" + Platillos_TABLE_NAME +"._Id "
                        +" ORDER BY "+  Menu_platillo_TABLE_NAME+ ".fecha desc";
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
                "select  "+ Menu_platillo_TABLE_NAME + "._Id, " +
                        Platillos_TABLE_NAME + ".platillo" +  "," +
                        Platillos_TABLE_NAME + ".categoria" + "," +
                        Platillos_TABLE_NAME + ".descripcion"+  ","  +
                        Menu_platillo_TABLE_NAME + ".fecha" +
                        " from " + Platillos_TABLE_NAME +
                        " inner JOIN " +  Menu_platillo_TABLE_NAME +" on "+  Menu_platillo_TABLE_NAME + ".idplatillo " +"=" + Platillos_TABLE_NAME +"._Id "
                        +" Where "+ Menu_platillo_TABLE_NAME + ".fecha"+" = ?  "
                        +" ORDER BY "+  Menu_platillo_TABLE_NAME+ ".fecha desc ", new String[]{Buscar} );
        return cursor;
    }










    public  void uppDate(String id  ,
                         String valmenuval,String fecha, String valmenu,
                         String idusuario, String idplatillo
    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Menu_platillo_COLUMN_FECHA, fecha);
        cv.put(Menu_platillo_COLUMN_VALMENU, valmenu);
        cv.put(Menu_platillo_COLUMN_IDUSUARIO, idusuario);
        cv.put(Menu_platillo_COLUMN_IDPLATILLO, idplatillo);
        Cursor check = db.rawQuery("Select * from "+ Menu_platillo_TABLE_NAME+ " where "+Menu_platillo_COLUMN_VALMENU+" =?", new String[]{valmenuval} );

        long result = db.update(Menu_platillo_TABLE_NAME, cv,"_id=?",new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Update Successfully!", Toast.LENGTH_SHORT).show();
        }
        CheckLimp= 0;

    }

    void deleteOnerow(String id ){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Menu_platillo_TABLE_NAME,"_id= ?", new String[]{id});
        if (result ==1) {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
    }
}





