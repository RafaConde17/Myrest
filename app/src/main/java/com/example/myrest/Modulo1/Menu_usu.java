package com.example.myrest.Modulo1;

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

public class Menu_usu extends AppCompatActivity {

    public String Enviardato;
    EditText buscartxt;
    FloatingActionButton add_button , buscaruno ;
    ImageButton txtrefresh;
    Usuario_DAO myDB;
    ConstraintLayout ventanamenuusu;
    RecyclerView recyclerView;
    ArrayList<String> Aid ,Ausuario, Atipousuario, Adatos ;
    ArrayList<String> Apass,Acorreo,Aestado;
    CustomAdapterUsu customAdapter;
    Usuario_DAO DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_usu);

        recyclerView = findViewById(R.id.recyclerViewusu);
        add_button = findViewById(R.id.menu_usu_ingresar);
        buscartxt = findViewById(R.id.txtbuscar);
        txtrefresh = findViewById(R.id.txtrefresh);
        buscaruno  = findViewById(R.id.txtbuscaruno);

        CompledtbAll();


        ((EditText)findViewById(R.id.txtbuscar)).setOnEditorActionListener(
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
                EditText usuario = findViewById(R.id.txtbuscar);
                String usuario1=usuario.getText().toString();
                CompledtbOne();
            }
        });
        buscartxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usuario = findViewById(R.id.txtbuscar);
                String usuario1=usuario.getText().toString();
                if ( usuario1.length() ==0)
                {
                    CompledtbAll();

                }else{
                    CompledtbOne();

                }
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Enviardato= "0";
                    Intent intent = new Intent(Menu_usu.this, Reg_usuario.class);
                    intent.putExtra("Enviardato", Enviardato);
                    startActivityForResult(intent,1);

                }catch ( Exception e ) {

                    Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

                }
            }
        });


    }





    public  void CompledtbAll(){
        try {


        myDB = new Usuario_DAO(Menu_usu.this);
        Aid = new ArrayList<>();
        Ausuario= new ArrayList<>();
        Apass = new ArrayList<>();
        Atipousuario= new ArrayList<>();
        Adatos= new ArrayList<>();
        Acorreo = new ArrayList<>();
        Aestado = new ArrayList<>();
        llenarLista();
        customAdapter = new CustomAdapterUsu( Menu_usu.this, this,Aid ,Ausuario,Atipousuario,Adatos);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Menu_usu.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void CompledtbOne(){
        try {


        myDB = new Usuario_DAO(Menu_usu.this);
        Aid = new ArrayList<>();
        Ausuario= new ArrayList<>();
        Apass = new ArrayList<>();
        Atipousuario= new ArrayList<>();
        Adatos= new ArrayList<>();
        Aestado = new ArrayList<>();

        EditText usuario2 = findViewById(R.id.txtbuscar);
        String  Textbuscado = usuario2.getText().toString();
        llenaruno(Textbuscado);
        customAdapter = new CustomAdapterUsu(Menu_usu.this  ,this,Aid ,Ausuario,Atipousuario,Adatos);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Menu_usu.this));

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
        DB = new Usuario_DAO(Menu_usu.this);
        Cursor cursor=  DB.Listaruno(buscar);
        while (cursor.moveToNext()){
            Aid.add(cursor.getString(0));
            Ausuario.add(cursor.getString(1));
            Apass.add(cursor.getString(2));
            Atipousuario.add(cursor.getString(3));
            Adatos.add(cursor.getString(4));
            Acorreo.add(cursor.getString(5));
            Aestado.add(cursor.getString(6));
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
                Aid.add(cursor.getString(0));
                Ausuario.add(cursor.getString(1));
                Apass.add(cursor.getString(2));
                Atipousuario.add(cursor.getString(3));
                Adatos.add(cursor.getString(4));

                    Acorreo.add(cursor.getString(5));
                    Aestado.add(cursor.getString(6));
            }
        }} catch ( Exception e ) {

        Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

    }

}}
