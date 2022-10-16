package com.example.myrest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperDao extends SQLiteOpenHelper {
    private final Context context;

    public static final String DATABASE_NAME = "Myrest.db";
    public static final int DATABASE_VERSION = 1;


    //TB usuario
    public static final String Usuario_TABLE_NAME = "usuariotb";
    public static final String Usuario_COLUMN_ID = "_id";
    public static final String Usuario_COLUMN_USUARIO = "usuario";
    public static final String Usuario_COLUMN_PASS = "pass";
    public static final String Usuario_COLUMN_TIPOUSU = "tipousuario";
    public static final String Usuario_COLUMN_DATOS = "datos";
    public static final String Usuario_COLUMN_CORREO= "correo";
    public static final String Usuario_COLUMN_ESTADO = "estado";

    //TB Platillos
    public static final String Platillos_TABLE_NAME = "platillotb";
    public static final String Platillos_COLUMN_ID = "_Id";
    public static final String Platillos_COLUMN_PLATILLO = "platillo";
    public static final String Platillos_COLUMN_CATEGORIA = "categoria";
    public static final String Platillos_COLUMN_DESCRIPCION = "descripcion";
    public static final String Platillos_COLUMN_PRECIO = "precio";

    //TB Entradas
    public static final String Entradas_TABLE_NAME = "entradastb";
    public static final String Entradas_COLUMN_ID = "_Id";
    public static final String Entradas_COLUMN_ENTRADA = "entradas";
    public static final String Entradas_COLUMN_CATEGORIA = "categoria";
    public static final String Entradas_COLUMN_DESCRIPCION = "descripcion";
    public static final String Entradas_COLUMN_PRECIO = "precio";

    //TB Bebidas
    public static final String Bebidas_TABLE_NAME = "Bebidastb";
    public static final String Bebidas_COLUMN_ID = "_Id";
    public static final String Bebidas_COLUMN_BEBIDAS = "Bebidas";
    public static final String Bebidas_COLUMN_CATEGORIA = "categoria";
    public static final String Bebidas_COLUMN_DESCRIPCION = "descripcion";
    public static final String Bebidas_COLUMN_PRECIO = "precio";
    // menu table
    //TB Menu_platillos
    public static final String Menu_platillo_TABLE_NAME = "menuplatillotb";
    public static final String Menu_platillo_COLUMN_ID = "_Id";
    public static final String Menu_platillo_COLUMN_FECHA = "fecha";
    public static final String Menu_platillo_COLUMN_VALMENU = "valmenu";
    public static final String Menu_platillo_COLUMN_IDUSUARIO = "idusuario";
    public static final String Menu_platillo_COLUMN_IDPLATILLO = "idplatillo";

    //TB Menu_entradas
    public static final String Menu_entradas_TABLE_NAME = "menuentradastb";
    public static final String Menu_entradas_COLUMN_ID = "_Id";
    public static final String Menu_entradas_COLUMN_FECHA = "fecha";
    public static final String Menu_entradas_COLUMN_VALMENU = "valmenu";
    public static final String Menu_entradas_COLUMN_IDUSUARIO = "idusuario";

    public static final String Menu_entradas_COLUMN_IDENTRADA = "identrada";


    //TB Menu_bebidas
    public static final String Menu_bebidas_TABLE_NAME = "menubebidastb";
    public static final String Menu_bebidas_COLUMN_ID = "_Id";
    public static final String Menu_bebidas_COLUMN_FECHA = "fecha";
    public static final String Menu_bebidas_COLUMN_VALMENU = "valmenu";
    public static final String Menu_bebidas_COLUMN_IDUSUARIO = "idusuario";
    public static final String Menu_bebidas_COLUMN_IDBEBIDA = "idbebidas";

    //TB Menu_detalle
    public static final String Menu_detalle_TABLE_NAME = "menudetalletb";
    public static final String Menu_detalle_COLUMN_ID = "_Id";
    public static final String Menu_detalle_COLUMN_FECHA = "fecha";
    public static final String Menu_detalle_COLUMN_VALMENU = "valmenu";
    public static final String Menu_detalle_COLUMN_VALFECHA = "valfecha";
    public static final String Menu_detalle_COLUMN_VALTIPO = "valtipo";
    public static final String Menu_detalle_COLUMN_VALMESA = "valmesa";
    // carta table
    //TB carta_platillos
    public static final String Carta_platillo_TABLE_NAME = "Cartaplatillotb";
    public static final String Carta_platillo_COLUMN_ID = "_Id";
    public static final String Carta_platillo_COLUMN_FECHA = "fecha";
    public static final String Carta_platillo_COLUMN_VALCarta = "valCarta";
    public static final String Carta_platillo_COLUMN_IDUSUARIO = "idusuario";
    public static final String Carta_platillo_COLUMN_IDPLATILLO = "idplatillo";

    //TB Carta_entradas
    public static final String Carta_entradas_TABLE_NAME = "Cartaentradastb";
    public static final String Carta_entradas_COLUMN_ID = "_Id";
    public static final String Carta_entradas_COLUMN_FECHA = "fecha";
    public static final String Carta_entradas_COLUMN_VALCarta = "valCarta";
    public static final String Carta_entradas_COLUMN_IDUSUARIO = "idusuario";

    public static final String Carta_entradas_COLUMN_IDENTRADA = "identrada";


    //TB Carta_bebidas
    public static final String Carta_bebidas_TABLE_NAME = "Cartabebidastb";
    public static final String Carta_bebidas_COLUMN_ID = "_Id";
    public static final String Carta_bebidas_COLUMN_FECHA = "fecha";
    public static final String Carta_bebidas_COLUMN_VALCarta = "valCarta";
    public static final String Carta_bebidas_COLUMN_IDUSUARIO = "idusuario";
    public static final String Carta_bebidas_COLUMN_IDBEBIDA = "idbebidas";

    //TB Carta_detalle
    public static final String Carta_detalle_TABLE_NAME = "Cartadetalletb";
    public static final String Carta_detalle_COLUMN_ID = "_Id";
    public static final String Carta_detalle_COLUMN_FECHA = "fecha";
    public static final String Carta_detalle_COLUMN_VALCarta = "valCarta";
    public static final String Carta_detalle_COLUMN_VALFECHA = "valfecha";
    public static final String Carta_detalle_COLUMN_VALTIPO = "valtipo";
    public static final String Carta_detalle_COLUMN_VALMESA = "valmesa";


    public HelperDao(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }




    public static String[] tablas1 = {
            "CREATE TABLE " + Usuario_TABLE_NAME +
                    " (" + Usuario_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Usuario_COLUMN_USUARIO + " TEXT, " +
                    Usuario_COLUMN_PASS + " TEXT, " +
                    Usuario_COLUMN_TIPOUSU + " TEXT, " +
                    Usuario_COLUMN_DATOS + " TEXT, " +
                    Usuario_COLUMN_CORREO + " TEXT, " +
                    Usuario_COLUMN_ESTADO + " INTEGER);",

            "CREATE TABLE " + Platillos_TABLE_NAME +
                    " (" + Platillos_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Platillos_COLUMN_PLATILLO + " TEXT, " +
                    Platillos_COLUMN_CATEGORIA + " TEXT ," +
                    Platillos_COLUMN_DESCRIPCION + " TEXT, " +
                    Platillos_COLUMN_PRECIO    + " TEXT);",

            "CREATE TABLE " + Entradas_TABLE_NAME +
                    " (" + Entradas_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Entradas_COLUMN_ENTRADA + " TEXT, " +
                    Entradas_COLUMN_CATEGORIA + " TEXT ," +
                    Entradas_COLUMN_DESCRIPCION + " TEXT, " +
                    Entradas_COLUMN_PRECIO    + " TEXT);",

            "CREATE TABLE " + Bebidas_TABLE_NAME +
                    " (" + Entradas_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Bebidas_COLUMN_BEBIDAS + " TEXT, " +
                    Bebidas_COLUMN_CATEGORIA + " TEXT ," +
                    Bebidas_COLUMN_DESCRIPCION + " TEXT, " +
                    Bebidas_COLUMN_PRECIO    + " TEXT);",
            // menu table
            "CREATE TABLE " + Menu_platillo_TABLE_NAME +
                    " (" + Menu_platillo_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Menu_platillo_COLUMN_FECHA + " TEXT, " +
                     Menu_platillo_COLUMN_VALMENU + " TEXT, " +
                    Menu_platillo_COLUMN_IDUSUARIO + " TEXT ," +
                    Menu_platillo_COLUMN_IDPLATILLO   + " TEXT);",

            "CREATE TABLE " + Menu_entradas_TABLE_NAME +
                    " (" + Menu_entradas_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Menu_entradas_COLUMN_FECHA + " TEXT, " +
                    Menu_entradas_COLUMN_VALMENU + " TEXT, " +
                    Menu_entradas_COLUMN_IDUSUARIO + " TEXT ," +
                    Menu_entradas_COLUMN_IDENTRADA    + " TEXT);",

            "CREATE TABLE " + Menu_bebidas_TABLE_NAME +
                    " (" + Menu_bebidas_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Menu_bebidas_COLUMN_FECHA + " TEXT, " +
                    Menu_bebidas_COLUMN_VALMENU + " TEXT, " +
                    Menu_bebidas_COLUMN_IDUSUARIO + " TEXT ," +
                    Menu_bebidas_COLUMN_IDBEBIDA    + " TEXT);",

            "CREATE TABLE " + Menu_detalle_TABLE_NAME +
                    " (" + Menu_detalle_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Menu_detalle_COLUMN_FECHA + " TEXT, " +
                     Menu_detalle_COLUMN_VALMENU + " TEXT, " +
                     Menu_detalle_COLUMN_VALFECHA + " TEXT, " +
                    Menu_detalle_COLUMN_VALTIPO + " TEXT, " +
                   Menu_detalle_COLUMN_VALMESA  + " TEXT);",

            // carta table
            "CREATE TABLE " + Carta_platillo_TABLE_NAME +
                    " (" + Carta_platillo_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Carta_platillo_COLUMN_FECHA + " TEXT, " +
                    Carta_platillo_COLUMN_VALCarta + " TEXT, " +
                    Carta_platillo_COLUMN_IDUSUARIO + " TEXT ," +
                    Carta_platillo_COLUMN_IDPLATILLO   + " TEXT);",

            "CREATE TABLE " + Carta_entradas_TABLE_NAME +
                    " (" + Carta_entradas_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Carta_entradas_COLUMN_FECHA + " TEXT, " +
                    Carta_entradas_COLUMN_VALCarta + " TEXT, " +
                    Carta_entradas_COLUMN_IDUSUARIO + " TEXT ," +
                    Carta_entradas_COLUMN_IDENTRADA    + " TEXT);",

            "CREATE TABLE " + Carta_bebidas_TABLE_NAME +
                    " (" + Carta_bebidas_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Carta_bebidas_COLUMN_FECHA + " TEXT, " +
                    Carta_bebidas_COLUMN_VALCarta + " TEXT, " +
                    Carta_bebidas_COLUMN_IDUSUARIO + " TEXT ," +
                    Carta_bebidas_COLUMN_IDBEBIDA    + " TEXT);",

            "CREATE TABLE " + Carta_detalle_TABLE_NAME +
                    " (" + Carta_detalle_COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Carta_detalle_COLUMN_FECHA + " TEXT, " +
                    Carta_detalle_COLUMN_VALCarta + " TEXT, " +
                    Carta_detalle_COLUMN_VALFECHA + " TEXT, " +
                    Carta_detalle_COLUMN_VALTIPO + " TEXT, " +
                    Carta_detalle_COLUMN_VALMESA  + " TEXT);",

    };
    @Override

    public void onCreate(SQLiteDatabase db) {

        for (String tabla : tablas1) {
            db.execSQL(tabla);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Usuario_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Platillos_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Entradas_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Bebidas_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Menu_platillo_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Menu_entradas_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Menu_bebidas_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Menu_detalle_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Carta_platillo_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Carta_entradas_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Carta_bebidas_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Carta_detalle_TABLE_NAME);
        onCreate(db);



    }
}
