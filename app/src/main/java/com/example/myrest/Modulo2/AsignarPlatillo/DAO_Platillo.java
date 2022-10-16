package com.example.myrest.Modulo2.AsignarPlatillo;

import static com.example.myrest.HelperDao.Platillos_COLUMN_CATEGORIA;
import static com.example.myrest.HelperDao.Platillos_COLUMN_DESCRIPCION;
import static com.example.myrest.HelperDao.Platillos_COLUMN_ID;
import static com.example.myrest.HelperDao.Platillos_COLUMN_PLATILLO;
import static com.example.myrest.HelperDao.Platillos_COLUMN_PRECIO;
import static com.example.myrest.HelperDao.Platillos_TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myrest.HelperDao;
public class DAO_Platillo extends SQLiteOpenHelper
{
    private final Context context;
    public DAO_Platillo(@Nullable Context context) {
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
    public void addBook(String pallitoval,String patillo, String categoria, String descripcion, String precio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Platillos_COLUMN_PLATILLO, patillo);
        cv.put(Platillos_COLUMN_CATEGORIA, categoria);
        cv.put(Platillos_COLUMN_DESCRIPCION, descripcion);
        cv.put(Platillos_COLUMN_PRECIO, precio);

        Cursor check = db.rawQuery("Select * from "+ Platillos_TABLE_NAME+ " where "+Platillos_COLUMN_PLATILLO+" =?", new String[]{pallitoval} );
        if ( check.getCount() >0 ){
            if (checkplatillo==0){
            }else {
                Toast.makeText(context, "El Platillo ya existe", Toast.LENGTH_SHORT).show();
                CheckLimp = 0;
            }
        }else {
             CheckLimp = 1;
            long result = db.insert(Platillos_TABLE_NAME,null, cv);
            if(result == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else {
                if (checkplatillo==0){
                }else {
                    Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
                }}
        }
    }
    public  void uppDate(String id  ,String pallitoval,String patillo, String categoria, String descripcion, String precio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Platillos_COLUMN_PLATILLO, patillo);
        cv.put(Platillos_COLUMN_CATEGORIA, categoria);
        cv.put(Platillos_COLUMN_DESCRIPCION, descripcion);
        cv.put(Platillos_COLUMN_PRECIO, precio);
        Cursor check = db.rawQuery("Select * from "+ Platillos_TABLE_NAME+ " where "+Platillos_COLUMN_PLATILLO+" =?", new String[]{pallitoval} );

            long result = db.update(Platillos_TABLE_NAME, cv,"_id=?",new String[]{id});
            if(result == -1){
                Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Update Successfully!", Toast.LENGTH_SHORT).show();
            }
            CheckLimp= 0;

    }
    public Cursor ListarTodo(){
        String query = "SELECT * FROM " + Platillos_TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor Listaruno(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery("select * FROM "+Platillos_TABLE_NAME+ " Where "+Platillos_COLUMN_PLATILLO+" LIKE ?  ", new String[]{Buscar+"%"} );
        return cursor;
    }
    public Cursor Listarunoupdate(String Buscar){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor   = DB.rawQuery("select * FROM "+Platillos_TABLE_NAME+" Where "+Platillos_COLUMN_ID+"  =?  ", new String[]{Buscar} );
        return cursor;
    }
    public int login (String user , String pass) {
        SQLiteDatabase DB = this.getReadableDatabase();
        int a = 0;
        Cursor cr = DB.rawQuery("select * from "+ Platillos_TABLE_NAME, null);
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

    void deleteOnerow(String id ){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Platillos_TABLE_NAME,"_id= ?", new String[]{id});
        if (result ==1) {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
    }
}
