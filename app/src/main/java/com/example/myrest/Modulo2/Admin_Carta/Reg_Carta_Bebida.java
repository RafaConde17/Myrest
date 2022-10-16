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

import com.example.myrest.Modulo2.AsignarBebidas.DAO_bebidas;
import com.example.myrest.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reg_Carta_Bebida extends AppCompatActivity {

    public  String EnvioID;
    Button ingresarmenubtn;
    // Llamado al CustomAdapter
    DAO_bebidas myDB_bebida;
    RecyclerView recyclerView_bebida;
    ArrayList<String> Arow_bebida_id, Arow_bebida_bebida,
            Arow_bebida_categoria, Arow_bebida_descripcion,
            Arow_bebida_precio, Arow_idsaved_bebida;
    CustomAdapter_Carta_bebida customAdapter_bebida;
    ////
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_carta_bebida);
        EnvioID = getIntent().getStringExtra("id");
        ingresarmenubtn = findViewById(R.id.ingresarcartabtn_select_bebida);
        EnvioID = getIntent().getStringExtra("id");
        recyclerView_bebida = findViewById(R.id.reg_cartarecyclerViewBebidas_select_bebida);
        CompledtbAllbebida();
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
                if (Arow_idsaved_bebida.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Seleccione un bebida", Toast.LENGTH_SHORT).show();
                } else {
                    // ingresar el menu del dia
                    try {
                        DAO_Carta_Detalles myDBmenu_detalle = new DAO_Carta_Detalles(Reg_Carta_Bebida.this);
                        myDBmenu_detalle.addBookdetallecarta(
                                fechaactual,
                                valmenu,
                                fechaactual,
                                "bebida"
                        );
                        checkvalida = myDBmenu_detalle.checkbebida;
                        try {
                            DAO_Carta_Bebida myDB = new DAO_Carta_Bebida(Reg_Carta_Bebida.this);
                            for (int i = 0; i < Arow_idsaved_bebida.toArray().length; i++) {
                                EnvioID = getIntent().getStringExtra("id");
                                myDB.addBookbebida(
                                        Arow_idsaved_bebida.get(i).trim(),
                                        fechaactual,
                                        valmenu,
                                        EnvioID,
                                        Arow_idsaved_bebida.get(i).trim());
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
    public  void CompledtbAllbebida(){
        try {
            myDB_bebida = new DAO_bebidas(Reg_Carta_Bebida.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida= new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion= new ArrayList<>();
            Arow_bebida_precio= new ArrayList<>();
            Arow_idsaved_bebida = new ArrayList<>();
            llenarListabebida();
            customAdapter_bebida = new CustomAdapter_Carta_bebida(
                    Reg_Carta_Bebida.this, this, Arow_bebida_id ,Arow_bebida_bebida,
                    Arow_bebida_categoria,Arow_bebida_descripcion,
                    Arow_bebida_precio,Arow_idsaved_bebida);
            recyclerView_bebida.setAdapter(customAdapter_bebida);
            recyclerView_bebida.setLayoutManager(new LinearLayoutManager(Reg_Carta_Bebida.this));
        } catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }

    }
    void llenarListabebida(){
        try {


            Cursor cursor =  myDB_bebida.ListarTodo();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else{
                while (cursor.moveToNext()){
                    Arow_bebida_id.add(cursor.getString(0));
                    Arow_bebida_bebida.add(cursor.getString(1));
                    Arow_bebida_categoria.add(cursor.getString(2));
                    Arow_bebida_descripcion.add(cursor.getString(3));
                    Arow_bebida_precio.add(cursor.getString(4));
                }
            }} catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }



}
