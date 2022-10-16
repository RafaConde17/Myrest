package com.example.myrest.Modulo1;

import static com.example.myrest.HelperDao.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myrest.HelperDao;



public class Usuario_DAO extends SQLiteOpenHelper {

    private final Context context;


    public Usuario_DAO(@Nullable Context context) {
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
    public int checkusu;
    public void addBook(String usuval,String usuario, String pass, String tipousu, String datos,String correo, int estado){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Usuario_COLUMN_USUARIO, usuario);
        cv.put(Usuario_COLUMN_PASS, pass);
        cv.put(Usuario_COLUMN_TIPOUSU, tipousu);
        cv.put(Usuario_COLUMN_DATOS, datos);
        cv.put(Usuario_COLUMN_CORREO, correo);
        cv.put(Usuario_COLUMN_ESTADO, estado);


        Cursor check = db.rawQuery("Select * from "+ Usuario_TABLE_NAME+ " where "+Usuario_COLUMN_USUARIO+" =?", new String[]{usuval} );
        if ( check.getCount() >0 ){

             if (checkusu==0){


             }else {
                 Toast.makeText(context, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                 CheckLimp = 0;
             }
        }else {
            CheckLimp = 1;

        long result = db.insert(Usuario_TABLE_NAME,null, cv);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {

            if (checkusu==0){

            }else {
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }}
        }
    }


    public  void uppDate(String id ,String usuval,String usuario, String pass, String tipousu, String datos,String correo, int estado){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Usuario_COLUMN_USUARIO, usuario);
        cv.put(Usuario_COLUMN_PASS, pass);
        cv.put(Usuario_COLUMN_TIPOUSU, tipousu);
        cv.put(Usuario_COLUMN_DATOS, datos);
        cv.put(Usuario_COLUMN_CORREO, correo);
        cv.put(Usuario_COLUMN_ESTADO, estado);

        Cursor check = db.rawQuery("Select * from "+ Usuario_TABLE_NAME+ " where "+Usuario_COLUMN_USUARIO+" =?", new String[]{usuval} );

        if ( check.getCount() == 1 || Usuario_COLUMN_USUARIO ==usuval){


            long result = db.update(Usuario_TABLE_NAME, cv,"_id=?",new String[]{id});
            if(result == -1){
                Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Update Successfully!", Toast.LENGTH_SHORT).show();
            }


            CheckLimp= 0;
        }else {
            CheckLimp = 1;

            Toast.makeText(context, "Can't change the user!", Toast.LENGTH_SHORT).show();

    }

    }
    public Cursor ListarTodo(){
        String query = "SELECT * FROM " + Usuario_TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public Cursor Listaruno(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery("select * FROM usuariotb Where usuario LIKE ?  ", new String[]{Buscar+"%"} );
        return cursor;

    }
    public Cursor Listarunoupdate(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery("select * FROM usuariotb Where "+Usuario_COLUMN_ID+"  =?  ", new String[]{Buscar} );
        return cursor;

    }

    public int login (String user , String pass ) {
        SQLiteDatabase DB = this.getReadableDatabase();
        int a = 0;
        Cursor cr = DB.rawQuery("select * from usuariotb ", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if (cr.getString(1).equals(user) && cr.getString(2).equals(pass)) {
                        a++;
                }
            } while (
                    cr.moveToNext()
            );


        }
        return a;

    }


    public Cursor  obtenerusuario( String usu, String pass){

        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery("select * FROM usuariotb Where "+ Usuario_COLUMN_USUARIO+ " =? and "+Usuario_COLUMN_PASS+"=?", new String[]{usu,pass} );
        return cursor;
    }

    void deleteOnerow(String id ){

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Usuario_TABLE_NAME,"_id= ?", new String[]{id});
        if (result ==1) {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
    }
}
