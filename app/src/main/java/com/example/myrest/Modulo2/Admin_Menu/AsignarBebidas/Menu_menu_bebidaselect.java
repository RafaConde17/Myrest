package com.example.myrest.Modulo2.Admin_Menu.AsignarBebidas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class Menu_menu_bebidaselect  extends AppCompatActivity {
    String EnvioID;
    //date
    public int año;
    public int mes;
    public int dia;
    static final int TIPO_DIALOGO = 0;
    static DatePickerDialog.OnDateSetListener seleccionfecha;
    EditText menu_menu_fechatxt;
    ImageButton menu_menu_selecfecha;
    //<<
    //Variables para asignar
    EditText buscartxt;
    FloatingActionButton add_button, buscaruno;
    ImageButton txtrefresh;
    CheckBox checkplatillo;
    /////
    // Llamado al CustomAdapter
    DAO_Menu_Bebidas myDB;
    RecyclerView recyclerView;
    ArrayList<String> Arow_bebida_id, Arow_bebida_bebida,
            Arow_bebida_categoria, Arow_bebida_descripcion, Arow_bebida_fecha;
    CustomAdapter_Menu_bebidaselect customAdapter;
    ////

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_menu_bebidas);
        //llamar las variables del layuot

        recyclerView = findViewById(R.id.recyclerViewmenu_bebida);
        CompledtbAll();
        EnvioID = getIntent().getStringExtra("id");

        //buscar 1 elemento
        buscaruno = findViewById(R.id.menu_menu_bebida_buscarunobtn);

        buscaruno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usuario = menu_menu_fechatxt;
                String usuario1 = usuario.getText().toString();
                CompledtbOne();
            }
        });
        ////


        ///variables de fecha
        menu_menu_fechatxt = (EditText) findViewById(R.id.menu_menu_bebida_buscartxt);
        menu_menu_selecfecha = (ImageButton) findViewById(R.id.menu_menu_bebida_refreshbtn);
        //agregar nuevo element0


        add_button = findViewById(R.id.menu_menu_bebida_ingresarbtn);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Intent intent = new Intent(Menu_menu_bebidaselect.this, Reg_Menu_bebida.class);

                    intent.putExtra("id", EnvioID);
                    startActivityForResult(intent, 1);
                    ;

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();

                }
            }
        });
        // asignar fecha a texto
        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mostrarfecha();
        ///boton calendario
        menu_menu_selecfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mostrarcalendario(view);


            }
        });
        //asignar fecha a las variables
        seleccionfecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                año = year;
                mes = monthOfYear;
                dia = dayOfMonth;
                mostrarfecha();
            }


        };
    }

    // Actualizar el activity
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
    // Mostrar datos de la tabla

    public  void CompledtbAll(){
        try {


            myDB = new DAO_Menu_Bebidas(Menu_menu_bebidaselect.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida= new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion= new ArrayList<>();
            Arow_bebida_fecha= new ArrayList<>();

            llenarLista();
            customAdapter = new CustomAdapter_Menu_bebidaselect(

                    Menu_menu_bebidaselect.this, this,
                    Arow_bebida_id ,Arow_bebida_bebida,Arow_bebida_categoria,
                    Arow_bebida_descripcion,Arow_bebida_fecha);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Menu_menu_bebidaselect.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }

    }
    void llenarLista(){
        try {


            Cursor cursor =  myDB.ListarTodomenubebida();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else{
                while (cursor.moveToNext()){
                    Arow_bebida_id.add(cursor.getString(0));
                    Arow_bebida_bebida.add(cursor.getString(1));
                    Arow_bebida_categoria.add(cursor.getString(2));
                    Arow_bebida_descripcion.add(cursor.getString(3));
                    Arow_bebida_fecha.add(cursor.getString(4));
                }
            }} catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }

    }


    // Mostrar 1 dato de la tabla
    void CompledtbOne(){
        try {
            myDB = new DAO_Menu_Bebidas(Menu_menu_bebidaselect.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida= new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion= new ArrayList<>();
            Arow_bebida_fecha= new ArrayList<>();
            EditText dato = menu_menu_fechatxt;
            String  Textbuscado = dato.getText().toString();
            llenaruno(Textbuscado);
            customAdapter = new CustomAdapter_Menu_bebidaselect( Menu_menu_bebidaselect.this, this,
                    Arow_bebida_id ,Arow_bebida_bebida,Arow_bebida_categoria,
                    Arow_bebida_descripcion,Arow_bebida_fecha);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Menu_menu_bebidaselect.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void llenaruno(String buscar){
        try {
            myDB = new DAO_Menu_Bebidas(Menu_menu_bebidaselect.this);
            Cursor cursor=  myDB.Listaruno(buscar);
            while (cursor.moveToNext()){
                Arow_bebida_id.add(cursor.getString(0));
                Arow_bebida_bebida.add(cursor.getString(1));
                Arow_bebida_categoria.add(cursor.getString(2));
                Arow_bebida_descripcion.add(cursor.getString(3));
                Arow_bebida_fecha.add(cursor.getString(4));

            }} catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
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
        menu_menu_fechatxt.setText(fecha);

    }



///// <- cuadro calendario
}
