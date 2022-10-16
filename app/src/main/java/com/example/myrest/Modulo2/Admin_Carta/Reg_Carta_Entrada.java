package com.example.myrest.Modulo2.Admin_Carta;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.Modulo2.AsignarEntradas.DAO_Entradas;
import com.example.myrest.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reg_Carta_Entrada extends AppCompatActivity {

    public  String EnvioID;
    Button ingresarmenubtn;
    // Llamado al CustomAdapter
    DAO_Entradas myDB_entrada;
    RecyclerView recyclerView_entrada;
    ArrayList<String> Arow_entrada_id, Arow_entrada_entrada,
            Arow_entrada_categoria, Arow_entrada_descripcion,
            Arow_entrada_precio, Arow_idsaved_entrada;
    CustomAdapter_Carta_entrada customAdapter_entrada;
    ////
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_carta_entrada);
        EnvioID = getIntent().getStringExtra("id");

        ingresarmenubtn = findViewById(R.id.ingresarcartabtn_select_entrada);
        EnvioID = getIntent().getStringExtra("id");
        recyclerView_entrada = findViewById(R.id.reg_cartarecyclerViewEntradas_select_entrada);
        CompledtbAllentrada();
        ingresarmenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long ahora = System.currentTimeMillis();
                Date fecha = new Date(ahora);
                @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yy");
                String fechaactual = df.format(fecha);

                String fechaplatiilo;
                String valmenu;

                fechaplatiilo = fechaactual;
                valmenu = "C" + fechaplatiilo.replaceAll("/", "");
                //obtener fecha actual

                int checkvalida;
                if (Arow_idsaved_entrada.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Seleccione un entrada", Toast.LENGTH_SHORT).show();
                } else {
                    // ingresar el menu del dia
                    try {
                        DAO_Carta_Detalles myDBmenu_detalle = new DAO_Carta_Detalles(Reg_Carta_Entrada.this);
                        myDBmenu_detalle.addBookdetallecarta(
                                fechaactual,
                                valmenu,
                                fechaactual,
                                "entrada"
                        );
                        checkvalida = myDBmenu_detalle.checkbebida;
                        try {
                            DAO_Carta_Entrada myDB = new DAO_Carta_Entrada(Reg_Carta_Entrada.this);
                            for (int i = 0; i < Arow_idsaved_entrada.toArray().length; i++) {
                                EnvioID = getIntent().getStringExtra("id");
                                myDB.addBookentrada(
                                        Arow_idsaved_entrada.get(i).trim(),
                                        fechaactual,
                                        valmenu,
                                        EnvioID,
                                        Arow_idsaved_entrada.get(i).trim());
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();
                    }
                    //
                }
            }
        });
    }
    public  void CompledtbAllentrada(){
        try {
            myDB_entrada = new DAO_Entradas(Reg_Carta_Entrada.this);
            Arow_entrada_id = new ArrayList<>();
            Arow_entrada_entrada= new ArrayList<>();
            Arow_entrada_categoria = new ArrayList<>();
            Arow_entrada_descripcion= new ArrayList<>();
            Arow_entrada_precio= new ArrayList<>();
            Arow_idsaved_entrada = new ArrayList<>();
            llenarListaentrada();
            customAdapter_entrada = new CustomAdapter_Carta_entrada(
                    Reg_Carta_Entrada.this, this, Arow_entrada_id ,Arow_entrada_entrada,
                    Arow_entrada_categoria,Arow_entrada_descripcion,
                    Arow_entrada_precio,Arow_idsaved_entrada);
            recyclerView_entrada.setAdapter(customAdapter_entrada);
            recyclerView_entrada.setLayoutManager(new LinearLayoutManager(Reg_Carta_Entrada.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    void llenarListaentrada(){
        try {


            Cursor cursor =  myDB_entrada.ListarTodo();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else{
                while (cursor.moveToNext()){
                    Arow_entrada_id.add(cursor.getString(0));
                    Arow_entrada_entrada.add(cursor.getString(1));
                    Arow_entrada_categoria.add(cursor.getString(2));
                    Arow_entrada_descripcion.add(cursor.getString(3));
                    Arow_entrada_precio.add(cursor.getString(4));
                }
            }} catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }



}
