package com.example.myrest.Modulo2.Admin_Menu.AsignarPlatillo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.Modulo2.AsignarPlatillo.DAO_Platillo;
import com.example.myrest.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Reg_Menu_platillo extends AppCompatActivity {

    //date
    public   int año;
    public int mes;
    public int dia;
    static final int TIPO_DIALOGO= 0;
    static DatePickerDialog.OnDateSetListener seleccionfecha;
    EditText menu_menu_fechatxt;
    ImageButton menu_menu_selecfecha;
    //<<


    public String EnvioID;

    Button ingresarmenubtn;


    // Llamado al CustomAdapter platillo
    DAO_Platillo myDB_platillo;

    RecyclerView recyclerView_platillo;
    ArrayList<String> Arow_platillo_id, Arow_platillo_platillo,
            Arow_platillo_categoria, Arow_platillo_descripcion, Arow_platillo_precio, Arow_idsaved_platillo;

    CustomAdapter_Menu_Platillo customAdapter_platillo;


    ////
    @SuppressLint("SetTextI18n")

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_menu_platillo);

        menu_menu_fechatxt = (EditText) findViewById(R.id.menu_menu_fechatxt_select_platillo);
        menu_menu_selecfecha = (ImageButton) findViewById(R.id.menu_menu_selecfecha_select_platillo);

        recyclerView_platillo = findViewById(R.id.reg_menurecyclerViewPlatillos_select_platillo);
        ingresarmenubtn = findViewById(R.id.ingresarmenubtn_select_platillo);
        EnvioID = getIntent().getStringExtra("id");

        ingresarmenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fechaplatiilo;
                String valmenu;
                fechaplatiilo = menu_menu_fechatxt.getText().toString().trim();
                valmenu = "M" + fechaplatiilo.replaceAll("/", "");
                //obtener fecha actual




                if (Arow_idsaved_platillo.size() == 0) {

                    Toast.makeText(getApplicationContext(), "Seleccione un platillo", Toast.LENGTH_SHORT).show();

                } else {
                    // ingresar el menu del dia

                            try {
                                DAO_Menu_Platillo myDB = new DAO_Menu_Platillo(Reg_Menu_platillo.this);
                                ;


                                for (int i = 0; i < Arow_idsaved_platillo.toArray().length; i++) {
                                    EnvioID = getIntent().getStringExtra("id");

                                    myDB.checkplatillo = 1;
                                    myDB.addBookplatillo(
                                            menu_menu_fechatxt.getText().toString().trim(),
                                            Arow_idsaved_platillo.get(i).trim(),
                                            menu_menu_fechatxt.getText().toString().trim(),
                                            valmenu,
                                            EnvioID,
                                            Arow_idsaved_platillo.get(i).trim());
                                }
                            } catch (Exception e) {

                                Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();

                            }

                    //
                }
            }
        });


        CompledtbAllPlatillo();
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



            }
        });
        //asignar fecha a las variables
        seleccionfecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                año = year;
                mes =monthOfYear;
                dia=dayOfMonth;
                mostrarfecha();
            }


        };
        ///





    }

    public  void CompledtbAllPlatillo(){
        try {


            myDB_platillo = new DAO_Platillo(Reg_Menu_platillo.this);
            Arow_platillo_id = new ArrayList<>();
            Arow_platillo_platillo= new ArrayList<>();
            Arow_platillo_categoria = new ArrayList<>();
            Arow_platillo_descripcion= new ArrayList<>();
            Arow_platillo_precio= new ArrayList<>();
            Arow_idsaved_platillo = new ArrayList<>();


            llenarListaPlatillo();
            customAdapter_platillo = new CustomAdapter_Menu_Platillo(
                    Reg_Menu_platillo.this, this, Arow_platillo_id ,Arow_platillo_platillo,
            Arow_platillo_categoria,Arow_platillo_descripcion,
            Arow_platillo_precio,Arow_idsaved_platillo);
            recyclerView_platillo.setAdapter(customAdapter_platillo);
            recyclerView_platillo.setLayoutManager(new LinearLayoutManager(Reg_Menu_platillo.this));

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
// Actualizar el activity
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
    // Mostrar datos de la tabla


}