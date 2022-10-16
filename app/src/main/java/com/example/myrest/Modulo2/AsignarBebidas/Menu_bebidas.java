package com.example.myrest.Modulo2.AsignarBebidas;

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

public class Menu_bebidas extends AppCompatActivity {
    //Variables para asignar
    EditText buscartxt;
    FloatingActionButton add_button , buscaruno ;
    ImageButton txtrefresh;

    /////

    // Llamado al CustomAdapter
    DAO_bebidas myDB;
    ConstraintLayout ventanamenuusu;
    RecyclerView recyclerView;
    ArrayList<String> Arow_bebidas_id , Arow_bebidas_bebidas,
            Arow_bebidas_categoria,Arow_bebidas_descripcion
            ,Arow_bebidas_precio ;

    CustomAdapterBebidas customAdapter;
    DAO_bebidas DB;
    ////

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_bebidas);
        //llamar las variables del layuot

        recyclerView = findViewById(R.id.recyclerViewbebidas);

        add_button = findViewById(R.id.menu_Bebidas_ingresarbtn);
        buscartxt = findViewById(R.id.menu_Bebidas_buscartxt);
        txtrefresh = findViewById(R.id.menu_Bebidas_refreshbtn);
        buscaruno  = findViewById(R.id.menu_Bebidas_buscarunobtn);

        ///
        CompledtbAll();


        ((EditText)findViewById(R.id.menu_Bebidas_buscartxt)).setOnEditorActionListener(
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
                EditText usuario = findViewById(R.id.menu_Bebidas_buscartxt);
                String usuario1=usuario.getText().toString();
                CompledtbOne();
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Intent intent = new Intent(Menu_bebidas.this, Reg_bebidas.class);

                    startActivityForResult(intent,1);;

                }catch ( Exception e ) {

                    Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



    public  void CompledtbAll(){
        try {


            myDB = new DAO_bebidas(Menu_bebidas.this);
            Arow_bebidas_id = new ArrayList<>();
            Arow_bebidas_bebidas= new ArrayList<>();
            Arow_bebidas_categoria = new ArrayList<>();
            Arow_bebidas_descripcion= new ArrayList<>();
            Arow_bebidas_precio= new ArrayList<>();

            llenarLista();
            customAdapter = new CustomAdapterBebidas( Menu_bebidas.this, this, Arow_bebidas_id ,Arow_bebidas_bebidas,Arow_bebidas_categoria,Arow_bebidas_descripcion,Arow_bebidas_precio);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Menu_bebidas.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void CompledtbOne(){
        try {


            myDB = new DAO_bebidas(Menu_bebidas.this);

            Arow_bebidas_id = new ArrayList<>();
            Arow_bebidas_bebidas= new ArrayList<>();
            Arow_bebidas_categoria = new ArrayList<>();
            Arow_bebidas_descripcion= new ArrayList<>();
            Arow_bebidas_precio= new ArrayList<>();

            EditText dato = findViewById(R.id.menu_Bebidas_buscartxt);
            String  Textbuscado = dato.getText().toString();
            llenaruno(Textbuscado);
            customAdapter = new CustomAdapterBebidas( Menu_bebidas.this, this, Arow_bebidas_id ,Arow_bebidas_bebidas,Arow_bebidas_categoria,Arow_bebidas_descripcion,Arow_bebidas_precio);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Menu_bebidas.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
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


    void llenaruno(String buscar){
        try {
            DB = new DAO_bebidas(Menu_bebidas.this);
            Cursor cursor=  DB.Listaruno(buscar);
            while (cursor.moveToNext()){
                Arow_bebidas_id.add(cursor.getString(0));
                Arow_bebidas_bebidas.add(cursor.getString(1));
                Arow_bebidas_categoria.add(cursor.getString(2));
                Arow_bebidas_descripcion.add(cursor.getString(3));
                Arow_bebidas_precio.add(cursor.getString(4));

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
                    Arow_bebidas_id.add(cursor.getString(0));
                    Arow_bebidas_bebidas.add(cursor.getString(1));
                    Arow_bebidas_categoria.add(cursor.getString(2));
                    Arow_bebidas_descripcion.add(cursor.getString(3));
                    Arow_bebidas_precio.add(cursor.getString(4));
                }
            }} catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }

    }
}
