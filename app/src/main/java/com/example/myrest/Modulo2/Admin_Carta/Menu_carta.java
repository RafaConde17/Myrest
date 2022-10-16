package com.example.myrest.Modulo2.Admin_Carta;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Menu_carta extends AppCompatActivity {
    public  String EnvioID;
    DAO_Carta_Detalles myDB;
    DAO_Carta_Detalles DB;
    Spinner spi;
    FloatingActionButton buscaruno ;
    ImageButton refresh;
    EditText buscartxt;
    // Llamado al CustomAdapter

    RecyclerView recyclerView_entrada;
    RecyclerView recyclerView_bebida;
    RecyclerView recyclerView_platillo;

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
    ////
    Toolbar menu_menu_toolbar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select, menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.menu_opcion1){
            try {
                Intent intent = new Intent(Menu_carta.this, Reg_Carta_Platillo.class);
                intent.putExtra("id", EnvioID);
                startActivityForResult(intent,1);
            }catch ( Exception e ) {
                Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
            }
        }else if (id==R.id.menu_opcion2){
            try {
                Intent intent = new Intent(Menu_carta.this, Reg_Carta_Entrada.class);
                intent.putExtra("id", EnvioID);
                startActivityForResult(intent,1);
            }catch ( Exception e ) {
                Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
            }
        }else if (id==R.id.menu_opcion3) {
            try {
                Intent intent = new Intent(Menu_carta.this, Reg_Carta_Bebida.class);
                intent.putExtra("id", EnvioID);
                startActivityForResult(intent,1);
            } catch (Exception e) {

                Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();
            }}
        return true;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_carta);
        menu_menu_toolbar = findViewById(R.id.toolbarhistorialcarta);
        setSupportActionBar(menu_menu_toolbar);
        EnvioID = getIntent().getStringExtra("id");
        recyclerView_platillo = findViewById(R.id.reg_menurecyclerViewcarta_Platillos);
        recyclerView_entrada = findViewById(R.id.reg_menurecyclerViewcarta_Entradas);
        recyclerView_bebida=findViewById(R.id.reg_menurecyclerViewcarta_Bebidas);
        refresh = findViewById(R.id.menu_carta_refresh);
        spi = findViewById(R.id.reg_SpCarta);
        buscartxt = findViewById(R.id.menu_carta_buscartxt);
        buscaruno  = findViewById(R.id.menu_carta_buscarunobtn);

        CompledtbAll_platillo();
        CompledtbAll_entrada();
        CompledtbAll_bebida();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompledtbAll_platillo();
                CompledtbAll_entrada();
                CompledtbAll_bebida();
                buscartxt.setText("");
            }});
        buscaruno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spi.getSelectedItemPosition() ==0 ) {
                    CompledtbOneplatillo();
                        }else
                if  (spi.getSelectedItemPosition()==1) {
                    CompledtbOneentrada();
                        }else
                if  (spi.getSelectedItemPosition()==2) {
                    CompledtbOnebebida();
                }}});
    }
    //listar todos los detalles
    public  void CompledtbAll_platillo(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_carta.this);
            Arow_platillo_id = new ArrayList<>();
            Arow_platillo_platillo = new ArrayList<>();
            Arow_platillo_categoria = new ArrayList<>();
            Arow_platillo_descripcion= new ArrayList<>();
            Arow_platillo_precio = new ArrayList<>();
            llenarLista_platillo();
            customAdapter_platillo = new CustomAdapter_Menu_Carta_platillo(
            Menu_carta.this, this,
            Arow_platillo_id ,Arow_platillo_platillo,
            Arow_platillo_categoria,Arow_platillo_descripcion,Arow_platillo_precio);
            recyclerView_platillo.setAdapter(customAdapter_platillo);
            recyclerView_platillo.setLayoutManager(new LinearLayoutManager(Menu_carta.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void llenarLista_platillo(){
        try {
            Cursor cursor =  myDB.ListarTodocarta_platillo();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else {
                while (cursor.moveToNext()) {
                    // id
                    Arow_platillo_id.add(cursor.getString(0));
                    Arow_platillo_platillo.add(cursor.getString(2));
                    Arow_platillo_categoria.add(cursor.getString(3));
                    Arow_platillo_descripcion.add(cursor.getString(4));
                    Arow_platillo_precio.add(cursor.getString(5));
                } }
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    public  void CompledtbAll_entrada(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_carta.this);
            Arow_entrada_id = new ArrayList<>();
            Arow_entrada_entrada = new ArrayList<>();
            Arow_entrada_categoria = new ArrayList<>();
            Arow_entrada_descripcion= new ArrayList<>();
            Arow_entrada_precio = new ArrayList<>();
            llenarLista_entrada();
            customAdapter_entrada = new CustomAdapter_Menu_Carta_entrada(
                    Menu_carta.this, this,
                    Arow_entrada_id ,Arow_entrada_entrada,
                    Arow_entrada_categoria,Arow_entrada_descripcion,Arow_entrada_precio);
            recyclerView_entrada.setAdapter(customAdapter_entrada);
            recyclerView_entrada.setLayoutManager(new LinearLayoutManager(Menu_carta.this));

        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    void llenarLista_entrada(){
        try {
            Cursor cursor =  myDB.ListarTodoCartadedia_entrada();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else {
                while (cursor.moveToNext()) {
                    // id
                    Arow_entrada_id.add(cursor.getString(0));
                    Arow_entrada_entrada.add(cursor.getString(2));
                    Arow_entrada_categoria.add(cursor.getString(3));
                    Arow_entrada_descripcion.add(cursor.getString(4));
                    Arow_entrada_precio.add(cursor.getString(5));
                } }
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    public  void CompledtbAll_bebida(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_carta.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida = new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion= new ArrayList<>();
            Arow_bebida_precio = new ArrayList<>();
            llenarLista_bebida();
            customAdapter_bebida = new CustomAdapter_Menu_Carta_bebida(
                    Menu_carta.this, this,
                    Arow_bebida_id ,Arow_bebida_bebida,
                    Arow_bebida_categoria,Arow_bebida_descripcion,Arow_bebida_precio);
            recyclerView_bebida.setAdapter(customAdapter_bebida);
            recyclerView_bebida.setLayoutManager(new LinearLayoutManager(Menu_carta.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    void llenarLista_bebida(){
        try {
            Cursor cursor =  myDB.ListarTodoCartadedia_bebida();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else {
                while (cursor.moveToNext()) {
                    // id
                    Arow_bebida_id.add(cursor.getString(0));
                    Arow_bebida_bebida.add(cursor.getString(2));
                    Arow_bebida_categoria.add(cursor.getString(3));
                    Arow_bebida_descripcion.add(cursor.getString(4));
                    Arow_bebida_precio.add(cursor.getString(5));
                } }
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    //listar uno btn
    void CompledtbOneplatillo(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_carta.this);
            Arow_platillo_id = new ArrayList<>();
            Arow_platillo_platillo = new ArrayList<>();
            Arow_platillo_categoria = new ArrayList<>();
            Arow_platillo_descripcion= new ArrayList<>();
            Arow_platillo_precio = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_carta_buscartxt);
            String  Textbuscado = dato.getText().toString();
            llenarunoplatillo(Textbuscado);
            customAdapter_platillo = new CustomAdapter_Menu_Carta_platillo( Menu_carta.this, this,
            Arow_platillo_id ,Arow_platillo_platillo,Arow_platillo_categoria,
            Arow_platillo_descripcion,Arow_platillo_precio);
            recyclerView_platillo.setAdapter(customAdapter_platillo);
            recyclerView_platillo.setLayoutManager(new LinearLayoutManager(Menu_carta.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void llenarunoplatillo(String buscar){
        try {
            DB = new DAO_Carta_Detalles(Menu_carta.this);
            Cursor cursor=  DB.Listarunoplatillo(buscar);
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
    void CompledtbOneentrada(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_carta.this);
            Arow_entrada_id = new ArrayList<>();
            Arow_entrada_entrada = new ArrayList<>();
            Arow_entrada_categoria = new ArrayList<>();
            Arow_entrada_descripcion= new ArrayList<>();
            Arow_entrada_precio = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_carta_buscartxt);
            String  Textbuscado = dato.getText().toString();
            llenarunoentrada(Textbuscado);
            customAdapter_entrada = new CustomAdapter_Menu_Carta_entrada( Menu_carta.this, this,
            Arow_entrada_id ,Arow_entrada_entrada,Arow_entrada_categoria,
            Arow_entrada_descripcion,Arow_entrada_precio);
            recyclerView_entrada.setAdapter(customAdapter_entrada);
             recyclerView_entrada.setLayoutManager(new LinearLayoutManager(Menu_carta.this));
       } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void llenarunoentrada(String buscar){
        try {
            DB = new DAO_Carta_Detalles(Menu_carta.this);
            Cursor cursor=  DB.Listarunoentrada(buscar);
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
    void CompledtbOnebebida(){
        try {
            myDB = new DAO_Carta_Detalles(Menu_carta.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida = new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion= new ArrayList<>();
            Arow_bebida_precio = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_carta_buscartxt);
            String  Textbuscado = dato.getText().toString();
            llenarunobebida(Textbuscado);
            customAdapter_bebida = new CustomAdapter_Menu_Carta_bebida( Menu_carta.this, this,
            Arow_bebida_id ,Arow_bebida_bebida,Arow_bebida_categoria,
            Arow_bebida_descripcion,Arow_bebida_precio);
            recyclerView_bebida.setAdapter(customAdapter_bebida);
            recyclerView_bebida.setLayoutManager(new LinearLayoutManager(Menu_carta.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void llenarunobebida(String buscar){
        try {
            DB = new DAO_Carta_Detalles(Menu_carta.this);
            Cursor cursor=  DB.Listarunobebida(buscar);
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
    // Actualizar el activity
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    // Mostrar datos de la tabla


}
