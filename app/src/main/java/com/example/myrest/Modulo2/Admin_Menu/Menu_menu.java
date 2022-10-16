package com.example.myrest.Modulo2.Admin_Menu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.Modulo2.Admin_Menu.AsignarBebidas.CustomAdapter_Menu_menu_bebidaCant;
import com.example.myrest.Modulo2.Admin_Menu.AsignarEntrada.CustomAdapter_Menu_menu_entradaCant;
import com.example.myrest.Modulo2.Admin_Menu.AsignarPlatillo.CustomAdapter_Menu_menu_platilloCant;
import com.example.myrest.Modulo2.Admin_Menu.AsignarBebidas.Menu_menu_bebidaselect;
import com.example.myrest.Modulo2.Admin_Menu.AsignarEntrada.Menu_menu_entradaselect;
import com.example.myrest.Modulo2.Admin_Menu.AsignarPlatillo.Menu_menu_platilloselect;
import com.example.myrest.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
public class Menu_menu extends AppCompatActivity {
    public   int año;
    public int mes;
    public int dia;
    static final int TIPO_DIALOGO= 0;
    static DatePickerDialog.OnDateSetListener seleccionfecha;
    //titulo
    Toolbar menu_menu_toolbar;
    //Variables para asignar
    EditText buscartxt;
    ImageButton menu_menu_selecfecha;
    FloatingActionButton  buscaruno ;
    public  String EnvioID;
    /////
    DAO_Menu_Detalles myDB;
    DAO_Menu_Detalles DB;
    // Llamado al CustomAdapter platillo
    RecyclerView recyclerView_platillo;
    ArrayList<String>  Arow_menu_detalle_platillo_id ,
            Arow_menu_detalle_platillo_fecha,
            Arow_menu_detalle_platillo_valmenu,
            Arow_menu_detalle_platillo_valtipo,
            Arow_menu_detalle_platillo_valfecha,
            Arow_menu_detalle_platillo_Cant;
    CustomAdapter_Menu_menu_platilloCant customAdapter_platillo;
    ////
    RecyclerView recyclerView_entrada;
    ArrayList<String>  Arow_menu_detalle_entrada_id ,
            Arow_menu_detalle_entrada_fecha,
            Arow_menu_detalle_entrada_valmenu,
            Arow_menu_detalle_entrada_valtipo,
            Arow_menu_detalle_entrada_valfecha,
            Arow_menu_detalle_entrada_Cant;
    CustomAdapter_Menu_menu_entradaCant customAdapter_entrada;
    ////
    RecyclerView recyclerView_bebida;
    ArrayList<String>  Arow_menu_detalle_bebida_id ,
            Arow_menu_detalle_bebida_fecha,
            Arow_menu_detalle_bebida_valmenu,
            Arow_menu_detalle_bebida_valtipo,
            Arow_menu_detalle_bebida_valfecha,
            Arow_menu_detalle_bebida_Cant;
    CustomAdapter_Menu_menu_bebidaCant customAdapter_bebida;
    ////
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
                Intent intent = new Intent(Menu_menu.this, Menu_menu_platilloselect.class);
                intent.putExtra("id", EnvioID);
                startActivityForResult(intent,1);
            }catch ( Exception e ) {
                Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
            }
        }else if (id==R.id.menu_opcion2){
            try {
                Intent intent = new Intent(Menu_menu.this, Menu_menu_entradaselect.class);
                intent.putExtra("id", EnvioID);
                startActivityForResult(intent,1);
            }catch ( Exception e ) {
                Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
            }
        }else if (id==R.id.menu_opcion3) {
            try {
                Intent intent = new Intent(Menu_menu.this, Menu_menu_bebidaselect.class);
                intent.putExtra("id", EnvioID);
                startActivityForResult(intent,1);
            } catch (Exception e) {

                Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();
        }}
        return true;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_menu);
        menu_menu_toolbar = findViewById(R.id.toolbarhistorialmenu);
        setSupportActionBar(menu_menu_toolbar);
        //llamar las variables del layuot
        recyclerView_platillo = findViewById(R.id.reg_menurecyclerViewmenu_Platillos);
        recyclerView_entrada = findViewById(R.id.reg_menurecyclerViewEntradas);
        recyclerView_bebida=findViewById(R.id.reg_menurecyclerViewBebidas);
        buscaruno  = findViewById(R.id.menu_menu_buscarunobtn);
        menu_menu_selecfecha = findViewById(R.id.menu_menu_selecfecha);
        buscartxt = findViewById(R.id.menu_menu_buscartxt);
        EnvioID = getIntent().getStringExtra("id");
        CompledtbAll_platillo();
        CompledtbAll_entrada();
        CompledtbAll_bebida();
        buscaruno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompledtbOnePlatillo();
                CompledtbOnebebida();
                CompledtbOneEntrada();
            }
        });
        /// asignar fecha a texto
        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH) ;
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mostrarfecha();
        ///boton calendario
        menu_menu_selecfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarcalendario(view );
            }});
        /////
        //asignar fecha a las variables
        seleccionfecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                año = year;
                mes =monthOfYear;
                dia=dayOfMonth;
                mostrarfecha();
            }};}
    // aparecer cuadro de calendario
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return new DatePickerDialog(this,seleccionfecha,año,mes,dia);
        }
        return null;
    }
    public void mostrarcalendario(View control){
        showDialog(TIPO_DIALOGO);
    }
    public void mostrarfecha(){

        String fecha = (año + "/" + (mes+1) + "/" + dia);
        buscartxt.setText(fecha);
    }
    ///// <- cuadro calendario
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }}
    public  void CompledtbAll_platillo(){
        try {
            myDB = new DAO_Menu_Detalles(Menu_menu.this);
            Arow_menu_detalle_platillo_id = new ArrayList<>();
            Arow_menu_detalle_platillo_valmenu = new ArrayList<>();
            Arow_menu_detalle_platillo_valfecha= new ArrayList<>();
            Arow_menu_detalle_platillo_Cant = new ArrayList<>();
            llenarLista_platillo();
            customAdapter_platillo = new CustomAdapter_Menu_menu_platilloCant(
        Menu_menu.this, this,
            Arow_menu_detalle_platillo_id ,Arow_menu_detalle_platillo_valfecha,
                    Arow_menu_detalle_platillo_valmenu,Arow_menu_detalle_platillo_Cant);
            recyclerView_platillo.setAdapter(customAdapter_platillo);
            recyclerView_platillo.setLayoutManager(new LinearLayoutManager(Menu_menu.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    void llenarLista_platillo(){
        try {
            Cursor cursor =  myDB.ListarTodomenudetalle_platillo();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else {
                while (cursor.moveToNext()) {
                    // id
                    Arow_menu_detalle_platillo_valmenu.add(cursor.getString(0));
                    Arow_menu_detalle_platillo_id.add(cursor.getString(1));
                    // fecha
                    Arow_menu_detalle_platillo_valfecha.add(cursor.getString(2));
                    //valmenu
                    Arow_menu_detalle_platillo_Cant.add(cursor.getString(3));
                } }
            } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    public  void CompledtbAll_entrada(){
        try {
            myDB = new DAO_Menu_Detalles(Menu_menu.this);
            Arow_menu_detalle_entrada_id = new ArrayList<>();
            Arow_menu_detalle_entrada_valmenu = new ArrayList<>();
            Arow_menu_detalle_entrada_valfecha = new ArrayList<>();
            Arow_menu_detalle_entrada_Cant = new ArrayList<>();
            llenarLista_entrada();
            customAdapter_entrada = new CustomAdapter_Menu_menu_entradaCant(
                    Menu_menu.this, this,
                    Arow_menu_detalle_entrada_id ,Arow_menu_detalle_entrada_valfecha,
                    Arow_menu_detalle_entrada_valmenu,Arow_menu_detalle_entrada_Cant);
            recyclerView_entrada.setAdapter(customAdapter_entrada);
            recyclerView_entrada.setLayoutManager(new LinearLayoutManager(Menu_menu.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    void llenarLista_entrada(){
        try {
            Cursor cursor =  myDB.ListarTodomenudetalle_entrada();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else {
                while (cursor.moveToNext()) {
                    // id
                    Arow_menu_detalle_entrada_valmenu.add(cursor.getString(0));
                    Arow_menu_detalle_entrada_id.add(cursor.getString(1));
                    // fecha
                    Arow_menu_detalle_entrada_valfecha.add(cursor.getString(2));
                    //valmenu

                    Arow_menu_detalle_entrada_Cant.add(cursor.getString(3));
                } }
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    public  void CompledtbAll_bebida(){
        try {
            myDB = new DAO_Menu_Detalles(Menu_menu.this);
            Arow_menu_detalle_bebida_id = new ArrayList<>();
            Arow_menu_detalle_bebida_valmenu = new ArrayList<>();
            Arow_menu_detalle_bebida_valfecha = new ArrayList<>();

            Arow_menu_detalle_bebida_Cant = new ArrayList<>();
            llenarLista_bebida();
            customAdapter_bebida = new CustomAdapter_Menu_menu_bebidaCant(
                    Menu_menu.this, this,
                    Arow_menu_detalle_bebida_id ,Arow_menu_detalle_bebida_valfecha,
                    Arow_menu_detalle_bebida_valmenu,Arow_menu_detalle_bebida_Cant);
            recyclerView_bebida.setAdapter(customAdapter_bebida);
            recyclerView_bebida.setLayoutManager(new LinearLayoutManager(Menu_menu.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    void llenarLista_bebida(){
        try {
            Cursor cursor =  myDB.ListarTodomenudetalle_bebida();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else {
                while (cursor.moveToNext()) {
                    // id
                    Arow_menu_detalle_bebida_valmenu.add(cursor.getString(0));
                    Arow_menu_detalle_bebida_id.add(cursor.getString(1));
                    // fecha
                    Arow_menu_detalle_bebida_valfecha.add(cursor.getString(2));
                    //valmenu

                    Arow_menu_detalle_bebida_Cant.add(cursor.getString(3));
                } }
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }

    void CompledtbOnePlatillo(){
        try {
            Arow_menu_detalle_platillo_id = new ArrayList<>();
            Arow_menu_detalle_platillo_valmenu = new ArrayList<>();
            Arow_menu_detalle_platillo_valfecha = new ArrayList<>();

            Arow_menu_detalle_platillo_Cant = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_menu_buscartxt);
            String  Textbuscado = dato.getText().toString();
            llenarunoplatillo(Textbuscado);
            customAdapter_platillo = new CustomAdapter_Menu_menu_platilloCant(
            Menu_menu.this, this,
            Arow_menu_detalle_platillo_id ,Arow_menu_detalle_platillo_valfecha,
            Arow_menu_detalle_platillo_valmenu,Arow_menu_detalle_platillo_Cant);
            recyclerView_platillo.setAdapter(customAdapter_platillo);
            recyclerView_platillo.setLayoutManager(new LinearLayoutManager(Menu_menu.this));

        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void llenarunoplatillo(String buscar){
        try {
            DB = new DAO_Menu_Detalles(Menu_menu.this);
            Cursor cursor=  DB.Listarunoplatillo(buscar);
            while (cursor.moveToNext()){
                Arow_menu_detalle_platillo_valmenu.add(cursor.getString(0));
                Arow_menu_detalle_platillo_id.add(cursor.getString(1));
                // fecha
                Arow_menu_detalle_platillo_valfecha.add(cursor.getString(2));
                //valmenu
                Arow_menu_detalle_platillo_Cant.add(cursor.getString(3));
            }} catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }

    void CompledtbOneEntrada(){
        try {
            Arow_menu_detalle_entrada_id = new ArrayList<>();
            Arow_menu_detalle_entrada_valmenu = new ArrayList<>();
            Arow_menu_detalle_entrada_valfecha = new ArrayList<>();

            Arow_menu_detalle_entrada_Cant = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_menu_buscartxt);
            String  Textbuscado = dato.getText().toString();
            llenarunoentrada(Textbuscado);
            customAdapter_entrada = new CustomAdapter_Menu_menu_entradaCant(
            Menu_menu.this, this,
            Arow_menu_detalle_entrada_id ,Arow_menu_detalle_entrada_valfecha,
            Arow_menu_detalle_entrada_valmenu,Arow_menu_detalle_entrada_Cant);
            recyclerView_entrada.setAdapter(customAdapter_entrada);
            recyclerView_entrada.setLayoutManager(new LinearLayoutManager(Menu_menu.this));

        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void llenarunoentrada(String buscar){
        try {
            DB = new DAO_Menu_Detalles(Menu_menu.this);
            Cursor cursor=  DB.Listarunoentrada(buscar);
            while (cursor.moveToNext()){
                Arow_menu_detalle_entrada_valmenu.add(cursor.getString(0));
                Arow_menu_detalle_entrada_id.add(cursor.getString(1));
                // fecha
                Arow_menu_detalle_entrada_valfecha.add(cursor.getString(2));
                //valmenu

                Arow_menu_detalle_entrada_Cant.add(cursor.getString(3));
            }} catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }

    void CompledtbOnebebida(){
        try {
            Arow_menu_detalle_bebida_id = new ArrayList<>();
            Arow_menu_detalle_bebida_valmenu = new ArrayList<>();
            Arow_menu_detalle_bebida_valfecha = new ArrayList<>();

            Arow_menu_detalle_bebida_Cant = new ArrayList<>();
            EditText dato = findViewById(R.id.menu_menu_buscartxt);
            String  Textbuscado = dato.getText().toString();
            llenarunobebida(Textbuscado);
            customAdapter_bebida = new CustomAdapter_Menu_menu_bebidaCant(
                    Menu_menu.this, this,
                    Arow_menu_detalle_bebida_id ,Arow_menu_detalle_bebida_valfecha,
                    Arow_menu_detalle_bebida_valmenu,Arow_menu_detalle_bebida_Cant);
            recyclerView_bebida.setAdapter(customAdapter_bebida);
            recyclerView_bebida.setLayoutManager(new LinearLayoutManager(Menu_menu.this));

        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void llenarunobebida(String buscar){
        try {
            DB = new DAO_Menu_Detalles(Menu_menu.this);
            Cursor cursor=  DB.Listarunobebida(buscar);
            while (cursor.moveToNext()){
                Arow_menu_detalle_bebida_valmenu.add(cursor.getString(0));
                Arow_menu_detalle_bebida_id.add(cursor.getString(1));
                // fecha
                Arow_menu_detalle_bebida_valfecha.add(cursor.getString(2));
                //valmenu

                Arow_menu_detalle_bebida_Cant.add(cursor.getString(3));
            }} catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }

    }
