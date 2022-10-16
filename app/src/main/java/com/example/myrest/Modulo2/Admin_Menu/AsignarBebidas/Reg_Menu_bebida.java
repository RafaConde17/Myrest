package com.example.myrest.Modulo2.Admin_Menu.AsignarBebidas;

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

import com.example.myrest.Modulo2.AsignarBebidas.DAO_bebidas;
import com.example.myrest.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Reg_Menu_bebida extends AppCompatActivity {

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


    DAO_Menu_Bebidas myDB = new DAO_Menu_Bebidas(Reg_Menu_bebida.this);
    // Llamado al CustomAdapter platillo
    DAO_bebidas myDB_bebida;

    RecyclerView recyclerView_bebida;
    ArrayList<String> Arow_bebida_id, Arow_bebida_bebida,
            Arow_bebida_categoria, Arow_bebida_descripcion, Arow_bebida_precio, Arow_idsaved_bebida;

    CustomAdapter_Menu_Bebidas CustomAdapter_Menu_bebida;


    ////
    @SuppressLint("SetTextI18n")

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_menu_bebidas);

        menu_menu_fechatxt = (EditText) findViewById(R.id.menu_menu_fechatxt_select_bebida);
        menu_menu_selecfecha = (ImageButton) findViewById(R.id.menu_menu_selecfecha_select_bebida);

        recyclerView_bebida = findViewById(R.id.reg_menurecyclerViewbebida_select_bebida);
        ingresarmenubtn = findViewById(R.id.ingresarmenubtn_select_bebida);
        EnvioID = getIntent().getStringExtra("id");

        ingresarmenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fechaplatiilo;
                String valmenu;
                fechaplatiilo = menu_menu_fechatxt.getText().toString().trim();
                valmenu = "M" + fechaplatiilo.replaceAll("/", "");
                //obtener fecha actual





                if (Arow_idsaved_bebida.size() == 0) {

                    Toast.makeText(getApplicationContext(), "Seleccione una bebida", Toast.LENGTH_SHORT).show();

                } else {

                            try {


                                for (int i = 0; i < Arow_idsaved_bebida.toArray().length; i++) {
                                    EnvioID = getIntent().getStringExtra("id");
                                    myDB.checkbebida = 1;
                                    myDB.addBookbebida(
                                            menu_menu_fechatxt.getText().toString().trim(),
                                            Arow_idsaved_bebida.get(i).trim(),
                                            menu_menu_fechatxt.getText().toString().trim(),
                                            valmenu,
                                            EnvioID,
                                            Arow_idsaved_bebida.get(i).trim());
                                }

                            } catch (Exception e) {

                                Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();

                            }




            }}
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


            myDB_bebida = new DAO_bebidas(Reg_Menu_bebida.this);
            Arow_bebida_id = new ArrayList<>();
            Arow_bebida_bebida = new ArrayList<>();
            Arow_bebida_categoria = new ArrayList<>();
            Arow_bebida_descripcion = new ArrayList<>();
            Arow_bebida_precio = new ArrayList<>();
            Arow_idsaved_bebida = new ArrayList<>();


            llenarListaentrada();
            CustomAdapter_Menu_bebida = new CustomAdapter_Menu_Bebidas(
                    Reg_Menu_bebida.this, this, Arow_bebida_id, Arow_bebida_bebida,
                    Arow_bebida_categoria, Arow_bebida_descripcion,
                    Arow_bebida_precio, Arow_idsaved_bebida);
            recyclerView_bebida.setAdapter(CustomAdapter_Menu_bebida);
            recyclerView_bebida.setLayoutManager(new LinearLayoutManager(Reg_Menu_bebida.this));

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();

        }

    }

    void llenarListaentrada() {
        try {


            Cursor cursor = myDB_bebida.ListarTodo();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "Not find data", Toast.LENGTH_LONG).show();
            } else {
                while (cursor.moveToNext()) {
                    Arow_bebida_id.add(cursor.getString(0));
                    Arow_bebida_bebida.add(cursor.getString(1));
                    Arow_bebida_categoria.add(cursor.getString(2));
                    Arow_bebida_descripcion.add(cursor.getString(3));
                    Arow_bebida_precio.add(cursor.getString(4));
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

}
///// <- cuadro calendario

