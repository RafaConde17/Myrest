package com.example.myrest.Modulo2.Admin_Carta;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.R;

import java.util.ArrayList;

public class Menu_Carta_Principal extends AppCompatActivity {
    DAO_Carta_Detalles myDB;
    DAO_Carta_Detalles DB;
    RecyclerView recyclerView_platillo_marino,recyclerView_platillo_chino
            ,recyclerView_platillo_criollo,recyclerView_platillo_pastas;
    RecyclerView recyclerView_entrada_sopa,recyclerView_entrada_ensalada,
            recyclerView_entrada_piqueos,recyclerView_entrada_guarnicion ;
    RecyclerView recyclerView_bebida_gaseosa,recyclerView_bebida_refresco,
            recyclerView_bebida_infusiones,recyclerView_bebida_tragos;
    ArrayList<String>
            Arow_entrada_id,
            Arow_entrada_entrada,
            Arow_entrada_categoria,
            Arow_entrada_descripcion,
            Arow_entrada_precio;
    CustomAdapter_Menu_Carta_entrada customAdapter_entrada;
    ////

    ArrayList<String>
            Arow_platillo_id,
            Arow_platillo_platillo,
            Arow_platillo_categoria,
            Arow_platillo_descripcion,
            Arow_platillo_precio;
    CustomAdapter_Menu_Carta_platillo customAdapter_platillo;
    ////


    ArrayList<String>
            Arow_bebida_id,
            Arow_bebida_bebida,
            Arow_bebida_categoria,
            Arow_bebida_descripcion,
            Arow_bebida_precio;
    CustomAdapter_Menu_Carta_bebida customAdapter_bebida;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_carta_actual);
        recyclerView_platillo_marino = findViewById(R.id.reg_menurecyclerViewPlatillos_marino) ;
        recyclerView_platillo_chino= findViewById(R.id.reg_menurecyclerViewPlatillos_Chino);
        recyclerView_platillo_criollo= findViewById(R.id.reg_menurecyclerViewPlatillos_criollo);
        recyclerView_platillo_pastas= findViewById(R.id.reg_menurecyclerViewPlatillos_pastas);
        recyclerView_entrada_sopa= findViewById(R.id.reg_menurecyclerViewEntradas_Sopas);
        recyclerView_entrada_ensalada= findViewById(R.id.reg_menurecyclerViewEntrada_ensaladas);
        recyclerView_entrada_piqueos= findViewById(R.id.reg_menurecyclerViewEntrada_piqueos);
        recyclerView_entrada_guarnicion= findViewById(R.id.reg_menurecyclerViewEntrada_Guarnicion);
        recyclerView_bebida_gaseosa= findViewById(R.id.reg_menurecyclerViewBebida_gaseosa);
        recyclerView_bebida_refresco= findViewById(R.id.reg_menurecyclerViewBebida_refrescos);
        recyclerView_bebida_infusiones= findViewById(R.id.reg_menurecyclerViewBebida_infusiones);
        recyclerView_bebida_tragos= findViewById(R.id.reg_menurecyclerViewBebida_tragos);
        CompledtbOneplatillo_criollo();
        CompledtbOneplatillo_marino();
        CompledtbOneplatillo_chino();
        CompledtbOneplatillo_pastas();
        CompledtbOneentrada_sopa();
        CompledtbOneentrada_ensalada();
        CompledtbOneentrada_piqueo();
        CompledtbOneentrada_guarnicion();
        CompledtbOnebebida_gaseosa();
        CompledtbOnebebida_refresco();
         CompledtbOnebebida_infusiones();
        CompledtbOnebebida_trago();

    }
    String  platillomarino = "Platillo Marino";
    String  platillochino = "Platillo Chino";
    String  platillocriollo = "Platillo Criollo";
    String  platillopastas = "Pastas";
    String  entradasopa =  "Sopas";
    String  entradaensalada = "Ensaladas";
    String  entradapiqueos = "Piqueos";
    String  entradaguarnicion = "Guarnición";
    String  bebidagaseosa =  "Gaseosa";
    String  bebidarefreso = "Refresco";
    String  bebidainfusiones = "Infusiones";
    String  bebidatrago = "Trago";

    void CompledtbOneplatillo_criollo(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_platillo_id = new ArrayList<>();
            Arow_platillo_platillo = new ArrayList<>();
            Arow_platillo_categoria = new ArrayList<>();
            Arow_platillo_descripcion= new ArrayList<>();
            Arow_platillo_precio = new ArrayList<>();
            RecyclerView_platillo(platillocriollo);
            customAdapter_platillo = new CustomAdapter_Menu_Carta_platillo( Menu_Carta_Principal.this, this,
                    Arow_platillo_id ,Arow_platillo_platillo,Arow_platillo_categoria,
                    Arow_platillo_descripcion,Arow_platillo_precio);
            recyclerView_platillo_criollo.setAdapter(customAdapter_platillo);
            recyclerView_platillo_criollo.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOneplatillo_marino(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_platillo_id = new ArrayList<>();
            Arow_platillo_platillo = new ArrayList<>();
            Arow_platillo_categoria = new ArrayList<>();
            Arow_platillo_descripcion= new ArrayList<>();
            Arow_platillo_precio = new ArrayList<>();
            RecyclerView_platillo(platillomarino);
            customAdapter_platillo = new CustomAdapter_Menu_Carta_platillo( Menu_Carta_Principal.this, this,
                    Arow_platillo_id ,Arow_platillo_platillo,Arow_platillo_categoria,
                    Arow_platillo_descripcion,Arow_platillo_precio);
            recyclerView_platillo_marino.setAdapter(customAdapter_platillo);
            recyclerView_platillo_marino.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOneplatillo_chino(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_platillo_id = new ArrayList<>();
            Arow_platillo_platillo = new ArrayList<>();
            Arow_platillo_categoria = new ArrayList<>();
            Arow_platillo_descripcion= new ArrayList<>();
            Arow_platillo_precio = new ArrayList<>();
            RecyclerView_platillo(platillochino);
            customAdapter_platillo = new CustomAdapter_Menu_Carta_platillo( Menu_Carta_Principal.this, this,
                    Arow_platillo_id ,Arow_platillo_platillo,Arow_platillo_categoria,
                    Arow_platillo_descripcion,Arow_platillo_precio);
            recyclerView_platillo_chino.setAdapter(customAdapter_platillo);
            recyclerView_platillo_chino.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));

        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOneplatillo_pastas(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_platillo_id = new ArrayList<>();
            Arow_platillo_platillo = new ArrayList<>();
            Arow_platillo_categoria = new ArrayList<>();
            Arow_platillo_descripcion= new ArrayList<>();
            Arow_platillo_precio = new ArrayList<>();
            RecyclerView_platillo(platillopastas);
            customAdapter_platillo = new CustomAdapter_Menu_Carta_platillo( Menu_Carta_Principal.this, this,
                    Arow_platillo_id ,Arow_platillo_platillo,Arow_platillo_categoria,
                    Arow_platillo_descripcion,Arow_platillo_precio);
            recyclerView_platillo_pastas.setAdapter(customAdapter_platillo);
            recyclerView_platillo_pastas.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));

        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void RecyclerView_platillo(String buscar){
        try {
            DB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Cursor cursor=  DB.Listarunoplatillo_principal(buscar);
            while (cursor.moveToNext()){
                Arow_platillo_id.add(cursor.getString(0));
                Arow_platillo_platillo.add(cursor.getString(2));
                Arow_platillo_categoria.add(cursor.getString(3));
                Arow_platillo_descripcion.add(cursor.getString(4));
                Arow_platillo_precio.add(cursor.getString(5));
            }} catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOneentrada_sopa(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_entrada_id = new ArrayList<>();
            Arow_entrada_entrada = new ArrayList<>();
            Arow_entrada_categoria = new ArrayList<>();
            Arow_entrada_descripcion= new ArrayList<>();
            Arow_entrada_precio = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_carta_buscartxt);

            RecyclerView_entrada(entradasopa);
            customAdapter_entrada = new CustomAdapter_Menu_Carta_entrada( Menu_Carta_Principal.this, this,
                    Arow_entrada_id ,Arow_entrada_entrada,Arow_entrada_categoria,
                    Arow_entrada_descripcion,Arow_entrada_precio);
            recyclerView_entrada_sopa.setAdapter(customAdapter_entrada);
            recyclerView_entrada_sopa.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOneentrada_ensalada(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_entrada_id = new ArrayList<>();
            Arow_entrada_entrada = new ArrayList<>();
            Arow_entrada_categoria = new ArrayList<>();
            Arow_entrada_descripcion= new ArrayList<>();
            Arow_entrada_precio = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_carta_buscartxt);

            RecyclerView_entrada(entradaensalada);
            customAdapter_entrada = new CustomAdapter_Menu_Carta_entrada( Menu_Carta_Principal.this, this,
                    Arow_entrada_id ,Arow_entrada_entrada,Arow_entrada_categoria,
                    Arow_entrada_descripcion,Arow_entrada_precio);
            recyclerView_entrada_ensalada  .setAdapter(customAdapter_entrada);
            recyclerView_entrada_ensalada.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOneentrada_piqueo(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_entrada_id = new ArrayList<>();
            Arow_entrada_entrada = new ArrayList<>();
            Arow_entrada_categoria = new ArrayList<>();
            Arow_entrada_descripcion= new ArrayList<>();
            Arow_entrada_precio = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_carta_buscartxt);

            RecyclerView_entrada(entradapiqueos);
            customAdapter_entrada = new CustomAdapter_Menu_Carta_entrada( Menu_Carta_Principal.this, this,
                    Arow_entrada_id ,Arow_entrada_entrada,Arow_entrada_categoria,
                    Arow_entrada_descripcion,Arow_entrada_precio);
            recyclerView_entrada_piqueos  .setAdapter(customAdapter_entrada);
            recyclerView_entrada_piqueos.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOneentrada_guarnicion(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_entrada_id = new ArrayList<>();
            Arow_entrada_entrada = new ArrayList<>();
            Arow_entrada_categoria = new ArrayList<>();
            Arow_entrada_descripcion= new ArrayList<>();
            Arow_entrada_precio = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_carta_buscartxt);

            RecyclerView_entrada(entradaguarnicion);
            customAdapter_entrada = new CustomAdapter_Menu_Carta_entrada( Menu_Carta_Principal.this, this,
                    Arow_entrada_id ,Arow_entrada_entrada,Arow_entrada_categoria,
                    Arow_entrada_descripcion,Arow_entrada_precio);
            recyclerView_entrada_guarnicion  .setAdapter(customAdapter_entrada);
            recyclerView_entrada_guarnicion.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void RecyclerView_entrada(String buscar){
        try {
            DB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Cursor cursor=  DB.Listarunoentrada_pincipal(buscar);
            while (cursor.moveToNext()){
                Arow_entrada_id.add(cursor.getString(0));
                Arow_entrada_entrada.add(cursor.getString(2));
                Arow_entrada_categoria.add(cursor.getString(3));
                Arow_entrada_descripcion.add(cursor.getString(4));
                Arow_entrada_precio.add(cursor.getString(5));
            }} catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOnebebida_gaseosa(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida = new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion= new ArrayList<>();
            Arow_bebida_precio = new ArrayList<>();

            RecyclerView_bebida(bebidagaseosa);
            customAdapter_bebida = new CustomAdapter_Menu_Carta_bebida( Menu_Carta_Principal.this, this,
                    Arow_bebida_id ,Arow_bebida_bebida,Arow_bebida_categoria,
                    Arow_bebida_descripcion,Arow_bebida_precio);
            recyclerView_bebida_gaseosa.setAdapter(customAdapter_bebida);
            recyclerView_bebida_gaseosa.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOnebebida_refresco(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida = new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion= new ArrayList<>();
            Arow_bebida_precio = new ArrayList<>();


            RecyclerView_bebida(bebidarefreso);
            customAdapter_bebida = new CustomAdapter_Menu_Carta_bebida( Menu_Carta_Principal.this, this,
                    Arow_bebida_id ,Arow_bebida_bebida,Arow_bebida_categoria,
                    Arow_bebida_descripcion,Arow_bebida_precio);
            recyclerView_bebida_refresco.setAdapter(customAdapter_bebida);
            recyclerView_bebida_refresco.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOnebebida_infusiones(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida = new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion= new ArrayList<>();
            Arow_bebida_precio = new ArrayList<>();


            RecyclerView_bebida(bebidainfusiones);
            customAdapter_bebida = new CustomAdapter_Menu_Carta_bebida( Menu_Carta_Principal.this, this,
                    Arow_bebida_id ,Arow_bebida_bebida,Arow_bebida_categoria,
                    Arow_bebida_descripcion,Arow_bebida_precio);
            recyclerView_bebida_infusiones.setAdapter(customAdapter_bebida);
            recyclerView_bebida_infusiones.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOnebebida_trago(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida = new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion= new ArrayList<>();
            Arow_bebida_precio = new ArrayList<>();
            RecyclerView_bebida(bebidatrago);
            customAdapter_bebida = new CustomAdapter_Menu_Carta_bebida( Menu_Carta_Principal.this, this,
                    Arow_bebida_id ,Arow_bebida_bebida,Arow_bebida_categoria,
                    Arow_bebida_descripcion,Arow_bebida_precio);
            recyclerView_bebida_tragos.setAdapter(customAdapter_bebida);
            recyclerView_bebida_tragos.setLayoutManager(new LinearLayoutManager(Menu_Carta_Principal.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void RecyclerView_bebida(String buscar){
        try {
            DB = new DAO_Carta_Detalles(Menu_Carta_Principal.this);
            Cursor cursor=  DB.Listarunobebida_pincipal(buscar);
            while (cursor.moveToNext()){
                Arow_bebida_id.add(cursor.getString(0));
                Arow_bebida_bebida.add(cursor.getString(2));
                Arow_bebida_categoria.add(cursor.getString(3));
                Arow_bebida_descripcion.add(cursor.getString(4));
                Arow_bebida_precio.add(cursor.getString(5));
            }} catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }




}
