package com.example.myrest.Modulo2.AsignarEntradas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Menu_Entradas extends AppCompatActivity {
    //Variables para asignar
    EditText buscartxt;
    FloatingActionButton add_button , buscaruno ;
    ImageButton txtrefresh;

    /////

    // Llamado al CustomAdapter
    DAO_Entradas myDB;
    ConstraintLayout ventanamenuusu;
    RecyclerView recyclerView;
    ArrayList<String> Arow_Entradas_id , Arow_Entradas_Entrada,
            Arow_Entradas_categoria,Arow_Entradas_descripcion
            ,Arow_Entradas_precio ;

    CustomAdapterEntradas customAdapter;
    DAO_Entradas DB;
    ////

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_entradas);
        //llamar las variables del layuot

        recyclerView = findViewById(R.id.recyclerViewentradas);

        add_button = findViewById(R.id.menu_Entradas_ingresarbtn);
        buscartxt = findViewById(R.id.menu_Entradas_buscartxt);
        txtrefresh = findViewById(R.id.menu_Entradas_refreshbtn);
        buscaruno  = findViewById(R.id.menu_Entradas_buscarunobtn);

        ///
        CompledtbAll();


                ((EditText)findViewById(R.id.menu_Entradas_buscartxt)).setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {

                                CompledtbOne();

                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });

        txtrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CompledtbAll();
                    buscartxt.setText("");

                }catch ( Exception e ) {

                    Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

                }
            }
        });
        buscaruno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usuario = findViewById(R.id.menu_Entradas_buscartxt);
                String usuario1=usuario.getText().toString();
                CompledtbOne();
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Intent intent = new Intent(Menu_Entradas.this, Reg_Entradas.class);

                    startActivityForResult(intent,1);;

                }catch ( Exception e ) {

                    Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
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


    public  void CompledtbAll(){
        try {


            myDB = new DAO_Entradas(Menu_Entradas.this);
            Arow_Entradas_id = new ArrayList<>();
            Arow_Entradas_Entrada= new ArrayList<>();
            Arow_Entradas_categoria = new ArrayList<>();
            Arow_Entradas_descripcion= new ArrayList<>();
            Arow_Entradas_precio= new ArrayList<>();

            llenarLista();
            customAdapter = new CustomAdapterEntradas( Menu_Entradas.this, this, Arow_Entradas_id ,Arow_Entradas_Entrada,Arow_Entradas_categoria,Arow_Entradas_descripcion,Arow_Entradas_precio);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Menu_Entradas.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void CompledtbOne(){
        try {


            myDB = new DAO_Entradas(Menu_Entradas.this);

            Arow_Entradas_id = new ArrayList<>();
            Arow_Entradas_Entrada= new ArrayList<>();
            Arow_Entradas_categoria = new ArrayList<>();
            Arow_Entradas_descripcion= new ArrayList<>();
            Arow_Entradas_precio= new ArrayList<>();

            EditText dato = findViewById(R.id.menu_Entradas_buscartxt);
            String  Textbuscado = dato.getText().toString();
            llenaruno(Textbuscado);
            customAdapter = new CustomAdapterEntradas( Menu_Entradas.this, this, Arow_Entradas_id ,Arow_Entradas_Entrada,Arow_Entradas_categoria,Arow_Entradas_descripcion,Arow_Entradas_precio);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Menu_Entradas.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void llenaruno(String buscar){
        try {
            DB = new DAO_Entradas(Menu_Entradas.this);
            Cursor cursor=  DB.Listaruno(buscar);
            while (cursor.moveToNext()){
                Arow_Entradas_id.add(cursor.getString(0));
                Arow_Entradas_Entrada.add(cursor.getString(1));
                Arow_Entradas_categoria.add(cursor.getString(2));
                Arow_Entradas_descripcion.add(cursor.getString(3));
                Arow_Entradas_precio.add(cursor.getString(4));

            }} catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }


    void llenarLista(){
        try {


            Cursor cursor =  myDB.ListarTodo();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else{
                while (cursor.moveToNext()){
                    Arow_Entradas_id.add(cursor.getString(0));
                    Arow_Entradas_Entrada.add(cursor.getString(1));
                    Arow_Entradas_categoria.add(cursor.getString(2));
                    Arow_Entradas_descripcion.add(cursor.getString(3));
                    Arow_Entradas_precio.add(cursor.getString(4));
                }
            }} catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }

    }
}
