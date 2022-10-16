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

import com.example.myrest.Modulo2.AsignarPlatillo.DAO_Platillo;
import com.example.myrest.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reg_Carta_Platillo extends AppCompatActivity {

    public  String EnvioID;
    Button ingresarmenubtn;
    // Llamado al CustomAdapter platillo
    DAO_Platillo myDB_platillo;
    RecyclerView recyclerView_platillo;
    ArrayList<String> Arow_platillo_id, Arow_platillo_platillo,
    Arow_platillo_categoria, Arow_platillo_descripcion,
    Arow_platillo_precio, Arow_idsaved_platillo;
    CustomAdapter_Carta_platillo customAdapter_platillo;
    ////
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reg_carta_platillo);
    ingresarmenubtn = findViewById(R.id.ingresarcartabtn_select_platillo);
        EnvioID = getIntent().getStringExtra("id");
        recyclerView_platillo = findViewById(R.id.reg_cartarecyclerViewPlatillos_select_platillo);
        CompledtbAllPlatillo();
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

                if (Arow_idsaved_platillo.size() == 0) {

                    Toast.makeText(getApplicationContext(), "Seleccione un platillo", Toast.LENGTH_SHORT).show();

                } else {
                    // ingresar el menu del dia

                    try {
                        DAO_Carta_Detalles myDBmenu_detalle = new DAO_Carta_Detalles(Reg_Carta_Platillo.this);
                        myDBmenu_detalle.addBookdetallecarta(
                                fechaactual,
                                valmenu,
                                fechaactual,
                                "platillo"
                        );

                        checkvalida = myDBmenu_detalle.checkbebida;


                        try {
                            DAO_Carta_Platillo myDB = new DAO_Carta_Platillo(Reg_Carta_Platillo.this);
                            ;


                            for (int i = 0; i < Arow_idsaved_platillo.toArray().length; i++) {
                                EnvioID = getIntent().getStringExtra("id");

                                myDB.checkplatillo = 1;
                                myDB.addBookplatillo(
                                        Arow_idsaved_platillo.get(i).trim(),
                                        fechaactual,
                                        valmenu,
                                        EnvioID,
                                        Arow_idsaved_platillo.get(i).trim());
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

    public  void CompledtbAllPlatillo(){
        try {


            myDB_platillo = new DAO_Platillo(Reg_Carta_Platillo.this);
            Arow_platillo_id = new ArrayList<>();
            Arow_platillo_platillo= new ArrayList<>();
            Arow_platillo_categoria = new ArrayList<>();
            Arow_platillo_descripcion= new ArrayList<>();
            Arow_platillo_precio= new ArrayList<>();
            Arow_idsaved_platillo = new ArrayList<>();


            llenarListaPlatillo();
            customAdapter_platillo = new CustomAdapter_Carta_platillo(
                    Reg_Carta_Platillo.this, this, Arow_platillo_id ,Arow_platillo_platillo,
                    Arow_platillo_categoria,Arow_platillo_descripcion,
                    Arow_platillo_precio,Arow_idsaved_platillo);
            recyclerView_platillo.setAdapter(customAdapter_platillo);
            recyclerView_platillo.setLayoutManager(new LinearLayoutManager(Reg_Carta_Platillo.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }

    }
    void llenarListaPlatillo(){
        try {


            Cursor cursor =  myDB_platillo.ListarTodo();
            if (cursor.getCount() ==0){
                Toast.makeText(this,"Not find data",Toast.LENGTH_LONG).show();
            }else{
                while (cursor.moveToNext()){
                    Arow_platillo_id.add(cursor.getString(0));
                    Arow_platillo_platillo.add(cursor.getString(1));
                    Arow_platillo_categoria.add(cursor.getString(2));
                    Arow_platillo_descripcion.add(cursor.getString(3));
                    Arow_platillo_precio.add(cursor.getString(4));
                }
            }} catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }



}
