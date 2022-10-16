package com.example.myrest.Modulo2.Admin_Carta;

import static com.example.myrest.HelperDao.Carta_entradas_COLUMN_FECHA;
import static com.example.myrest.HelperDao.Carta_entradas_COLUMN_IDENTRADA;
import static com.example.myrest.HelperDao.Carta_entradas_COLUMN_IDUSUARIO;
import static com.example.myrest.HelperDao.Carta_entradas_COLUMN_VALCarta;
import static com.example.myrest.HelperDao.Carta_entradas_TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myrest.HelperDao;

public class DAO_Carta_Entrada extends SQLiteOpenHelper {

    private final Context context;

    public DAO_Carta_Entrada(@Nullable Context context) {
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

    public int CheckLimp;
    public int checkentrada;


    public void addBookentrada(String val1, String fecha, String valmenu,
                                String idusuario, String identrada
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Carta_entradas_COLUMN_FECHA, fecha);
        cv.put(Carta_entradas_COLUMN_VALCarta, valmenu);
        cv.put(Carta_entradas_COLUMN_IDUSUARIO, idusuario);
        cv.put(Carta_entradas_COLUMN_IDENTRADA, identrada);

        Cursor check = db.rawQuery(
                "Select * from " + Carta_entradas_TABLE_NAME + " where " +
                        Carta_entradas_COLUMN_IDENTRADA + " =? ",
                new String[]{val1});

        if (check.getCount() > 0) {

            Toast.makeText(context, "El Entrada ya esta asignado", Toast.LENGTH_SHORT).show();

        } else {
            long result = db.insert(Carta_entradas_TABLE_NAME, null, cv);
            if (result == -1) {
                Toast.makeText(context, "No se agrego Entrada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Se agrego Entrada", Toast.LENGTH_SHORT).show();

            }
        }
    }


}