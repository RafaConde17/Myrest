package com.example.myrest.Modulo2.Admin_Menu.AsignarEntrada;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.Modulo2.AsignarEntradas.DAO_Entradas;
import com.example.myrest.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Reg_Menu_entrada extends AppCompatActivity {

    //date
    public int año;
    public int mes;
    public int dia;
    static final int TIPO_DIALOGO = 0;
    static DatePickerDialog.OnDateSetListener seleccionfecha;
    EditText menu_menu_fechatxt;
    ImageButton menu_menu_selecfecha;
    //<<


    public String EnvioID;

    Button ingresarmenubtn;


    // Llamado al CustomAdapter platillo
    DAO_Entradas myDB_entrada;

    RecyclerView recyclerView_entrada;
    ArrayList<String> Arow_entrada_id, Arow_entrada_entrada,
            Arow_entrada_categoria, Arow_entrada_descripcion, Arow_entrada_precio, Arow_idsaved_entrada;

    CustomAdaptar_Menu_Entradas CustomAdapter_Menu_Entradas;


    ////
    @SuppressLint("SetTextI18n")

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_menu_entradas);

        menu_menu_fechatxt = (EditText) findViewById(R.id.menu_menu_fechatxt_select_entrada);
        menu_menu_selecfecha = (ImageButton) findViewById(R.id.menu_menu_selecfecha_select_entrada);

        recyclerView_entrada = findViewById(R.id.reg_menurecyclerViewentrada_select_entrada);
        ingresarmenubtn = findViewById(R.id.ingresarmenubtn_select_entrada);
        EnvioID = getIntent().getStringExtra("id");

        ingresarmenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fechaplatiilo;
                String valmenu;
                fechaplatiilo = menu_menu_fechatxt.getText().toString().trim();
                valmenu = "M" + fechaplatiilo.replaceAll("/", "");
                //obtener fecha actual




                if (Arow_idsaved_entrada.size() == 0) {

                    Toast.makeText(getApplicationContext(), "Seleccione una entrada", Toast.LENGTH_SHORT).show();

                } else {
                    // ingresar el menu del dia


                            try {
                                DAO_Menu_Entradas myDB = new DAO_Menu_Entradas(Reg_Menu_entrada.this);
                                for (int i = 0; i < Arow_idsaved_entrada.toArray().length; i++) {
                                    EnvioID = getIntent().getStringExtra("id");
                                    myDB.checkentrada = 1;
                                    myDB.addBookEntradas(
                                            menu_menu_fechatxt.getText().toString().trim(),
                                            Arow_idsaved_entrada.get(i).trim(),
                                            menu_menu_fechatxt.getText().toString().trim(),
                                            valmenu,
                                            EnvioID,
                                            Arow_idsaved_entrada.get(i).trim());
                                }

                            } catch (Exception e) {

                                Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();

                            }

                        }



            }
        });


        CompledtbAllPlatillo();
        /// asignar fecha a texto
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
        ///


    }

    public void CompledtbAllPlatillo() {
        try {


            myDB_entrada = new DAO_Entradas(Reg_Menu_entrada.this);
            Arow_entrada_id = new ArrayList<>();
            Arow_entrada_entrada = new ArrayList<>();
            Arow_entrada_categoria = new ArrayList<>();
            Arow_entrada_descripcion = new ArrayList<>();
            Arow_entrada_precio = new ArrayList<>();
            Arow_idsaved_entrada = new ArrayList<>();


            llenarListaentrada();
            CustomAdapter_Menu_Entradas = new CustomAdaptar_Menu_Entradas(
                    Reg_Menu_entrada.this, this, Arow_entrada_id, Arow_entrada_entrada,
                    Arow_entrada_categoria, Arow_entrada_descripcion,
                    Arow_entrada_precio, Arow_idsaved_entrada);
            recyclerView_entrada.setAdapter(CustomAdapter_Menu_Entradas);
            recyclerView_entrada.setLayoutManager(new LinearLayoutManager(Reg_Menu_entrada.this));

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();

        }

    }

    void llenarListaentrada() {
        try {


            Cursor cursor = myDB_entrada.ListarTodo();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "Not find data", Toast.LENGTH_LONG).show();
            } else {
                while (cursor.moveToNext()) {
                    Arow_entrada_id.add(cursor.getString(0));
                    Arow_entrada_entrada.add(cursor.getString(1));
                    Arow_entrada_categoria.add(cursor.getString(2));
                    Arow_entrada_descripcion.add(cursor.getString(3));
                    Arow_entrada_precio.add(cursor.getString(4));
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();
        }
    }


// aparecer cuadro de calendario


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new DatePickerDialog(this, seleccionfecha, año, mes, dia);


        }
        return null;
    }

    public void mostrarcalendario(View control) {

        showDialog(TIPO_DIALOGO);

    }

    public void mostrarfecha() {

        String fecha = (año + "/" + (mes + 1) + "/" + dia);
        menu_menu_fechatxt.setText(fecha);

    }


///// <- cuadro calendario

}