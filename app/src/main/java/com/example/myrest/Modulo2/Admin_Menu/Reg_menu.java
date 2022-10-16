package com.example.myrest.Modulo2.Admin_Menu;

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

import com.example.myrest.Modulo2.Admin_Menu.AsignarEntrada.CustomAdaptar_Menu_Entradas;
import com.example.myrest.Modulo2.Admin_Menu.AsignarBebidas.CustomAdapter_Menu_Bebidas;
import com.example.myrest.Modulo2.Admin_Menu.AsignarPlatillo.CustomAdapter_Menu_Platillo;
import com.example.myrest.Modulo2.AsignarEntradas.DAO_Entradas;
import com.example.myrest.Modulo2.Admin_Menu.AsignarBebidas.DAO_Menu_Bebidas;
import com.example.myrest.Modulo2.Admin_Menu.AsignarEntrada.DAO_Menu_Entradas;
import com.example.myrest.Modulo2.Admin_Menu.AsignarPlatillo.DAO_Menu_Platillo;
import com.example.myrest.Modulo2.AsignarPlatillo.DAO_Platillo;
import com.example.myrest.Modulo2.AsignarBebidas.DAO_bebidas;
import com.example.myrest.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Reg_menu extends AppCompatActivity {
    public   int año;
    public int mes;
    public int dia;
    public  String EnvioID;
    EditText menu_menu_fechatxt;
    ImageButton menu_menu_selecfecha;
    Button ingresarmenubtn;
    static final int TIPO_DIALOGO= 0;
    static DatePickerDialog.OnDateSetListener seleccionfecha;
    // Llamado al CustomAdapter platillo
    DAO_Platillo myDB_platillo;
    RecyclerView recyclerView_platillo;
    ArrayList<String> Arow_platillo_id , Arow_platillo_platillo,
    Arow_platillo_categoria,Arow_platillo_descripcion,
    Arow_platillo_precio  ,Arow_idsaved_platillo ;
    CustomAdapter_Menu_Platillo customAdapter_platillo;
    DAO_Platillo DB_platillo;////
    // Llamado al CustomAdapter Entradas
    DAO_Entradas myDB_entrada;
    RecyclerView recyclerView_entrada;
    public ArrayList<String> Arow_Entradas_id , Arow_Entradas_Entrada,
    Arow_Entradas_categoria,Arow_Entradas_descripcion,
    Arow_Entradas_precio , Arow_idsaved_entrada ;
    CustomAdaptar_Menu_Entradas customAdapter_entrada;
    DAO_Entradas DB_entrada;
    ////
    // Llamado al CustomAdapter bebidas
    DAO_bebidas myDB_bebidas;
    RecyclerView recyclerView_bebidas;
    ArrayList<String> Arow_bebidas_id , Arow_bebidas_bebidas,
    Arow_bebidas_categoria,Arow_bebidas_descripcion,
    Arow_bebidas_precio  ,Arow_idsaved_bebida ;
    CustomAdapter_Menu_Bebidas customAdapter_bebidas;
    DAO_bebidas DB_bebidas; ////
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reg_menu);
    menu_menu_fechatxt = (EditText) findViewById(R.id.menu_menu_fechatxt);
    menu_menu_selecfecha = (ImageButton) findViewById(R.id.menu_menu_selecfecha);
    recyclerView_platillo = findViewById(R.id.reg_menurecyclerViewPlatillos);
    recyclerView_entrada = findViewById(R.id.reg_menurecyclerViewEntradas);
    recyclerView_bebidas = findViewById(R.id.reg_menurecyclerViewBebidas);
    ingresarmenubtn = findViewById(R.id.ingresarmenubtn);
    ingresarmenubtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    String fechaplatiilo;
    String platilloenvio;
    fechaplatiilo =   menu_menu_fechatxt.getText().toString().trim();
    platilloenvio = "M"+ fechaplatiilo.replaceAll("/","");
    //obtener fecha actual
    long ahora = System.currentTimeMillis();
    Date fecha = new Date(ahora);
    @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yy");
    String salida = df.format(fecha);
    int checkvalida;
    if (Arow_idsaved_platillo.size() == 0){
    Toast.makeText(getApplicationContext(), "Seleccione un platillo" , Toast.LENGTH_SHORT).show();
    }else if (Arow_idsaved_entrada.size() == 0){
    Toast.makeText(getApplicationContext(), "Seleccione una entrada" , Toast.LENGTH_SHORT).show();
    }else if (Arow_idsaved_bebida.size() == 0) {
    Toast.makeText(getApplicationContext(), "Seleccione un bebida" , Toast.LENGTH_SHORT).show();
    }else {
    // ingresar el menu del dia
    try {
    DAO_Menu_Detalles myDBmenu_detalle = new DAO_Menu_Detalles(Reg_menu.this);
    // registro platillos
    try {
    DAO_Menu_Platillo myDB = new DAO_Menu_Platillo(Reg_menu.this);
    for (int i = 0; i < Arow_idsaved_platillo.toArray().length; i++) {
    EnvioID = getIntent().getStringExtra("id");
    myDB.checkplatillo = 1;
    myDB.addBookplatillo(  menu_menu_fechatxt.getText().toString().trim(),
    platilloenvio,menu_menu_fechatxt.getText().toString().trim(),platilloenvio,EnvioID,Arow_idsaved_platillo.get(i).trim());
    }
    } catch (Exception e) {
    Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();
    }
    // registro entradas
    try {
    DAO_Menu_Entradas myDB = new DAO_Menu_Entradas(Reg_menu.this);
    for (int i = 0; i < Arow_idsaved_platillo.toArray().length; i++) {
    EnvioID = getIntent().getStringExtra("id");
    myDB.checkentrada = 1;
    myDB.addBookEntradas("1", platilloenvio,menu_menu_fechatxt.getText().toString().trim(),platilloenvio,EnvioID,Arow_idsaved_entrada.get(i).trim());}
    } catch (Exception e) {
    Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();
    }
    // registrar bebidas
    try {
    DAO_Menu_Bebidas myDB = new DAO_Menu_Bebidas(Reg_menu.this);
    for (int i = 0; i < Arow_idsaved_platillo.toArray().length; i++) {
    EnvioID = getIntent().getStringExtra("id");
    myDB.checkbebida = 1;
    myDB.addBookbebida("1",platilloenvio,menu_menu_fechatxt.getText().toString().trim(),platilloenvio,EnvioID,Arow_idsaved_bebida.get(i).trim());
    }} catch (Exception e) {
    Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();
    }
    } catch (Exception e) {
    Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();
    }}}
    });
        /// asignar fecha a texto
        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH) ;
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mostrarfecha();
        // CompledtbAllPlatillo();
        // CompledtbAllEntradas();
        // CompledtbAllBebidas();
        ///boton calendario
        menu_menu_selecfecha.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        mostrarcalendario(view );
        }});
        /////
        //asignar fecha a las variables
        seleccionfecha = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        año = year;
        mes =monthOfYear;
        dia=dayOfMonth;
        mostrarfecha();
        }};}
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
        public  void CompledtbAllPlatillo(){
        try {
        myDB_platillo = new DAO_Platillo(Reg_menu.this);
        Arow_platillo_id = new ArrayList<>();
        Arow_platillo_platillo= new ArrayList<>();
        Arow_platillo_categoria = new ArrayList<>();
         Arow_platillo_descripcion= new ArrayList<>();
            Arow_platillo_precio= new ArrayList<>();
            Arow_idsaved_platillo = new ArrayList<>();


            llenarListaPlatillo();
            customAdapter_platillo = new CustomAdapter_Menu_Platillo(
                    Reg_menu.this, this, Arow_platillo_id ,Arow_platillo_platillo,
                    Arow_platillo_categoria,Arow_platillo_descripcion,
                    Arow_platillo_precio,Arow_idsaved_platillo);
            recyclerView_platillo.setAdapter(customAdapter_platillo);
            recyclerView_platillo.setLayoutManager(new LinearLayoutManager(Reg_menu.this));

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
    public  void CompledtbAllEntradas(){
        try {
            myDB_entrada = new DAO_Entradas(Reg_menu.this);

            Arow_Entradas_id = new ArrayList<>();
            Arow_Entradas_Entrada= new ArrayList<>();
            Arow_Entradas_categoria = new ArrayList<>();
            Arow_Entradas_descripcion= new ArrayList<>();
            Arow_Entradas_precio= new ArrayList<>();
            Arow_idsaved_entrada = new ArrayList<>();

            llenarListaEntradas();
            customAdapter_entrada = new CustomAdaptar_Menu_Entradas(
                    Reg_menu.this, this, Arow_Entradas_id ,Arow_Entradas_Entrada,
                    Arow_Entradas_categoria,Arow_Entradas_descripcion
                    ,Arow_Entradas_precio,Arow_idsaved_entrada);
            recyclerView_entrada.setAdapter(customAdapter_entrada);
            recyclerView_entrada.setLayoutManager(new LinearLayoutManager(Reg_menu.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void llenarListaEntradas(){
        try {


            Cursor cursor =  myDB_entrada.ListarTodo();
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
    public  void CompledtbAllBebidas(){
        try {


            myDB_bebidas = new DAO_bebidas(Reg_menu.this);
            Arow_bebidas_id = new ArrayList<>();
            Arow_bebidas_bebidas= new ArrayList<>();
            Arow_bebidas_categoria = new ArrayList<>();
            Arow_bebidas_descripcion= new ArrayList<>();
            Arow_bebidas_precio= new ArrayList<>();
            Arow_idsaved_bebida = new ArrayList<>();

            llenarListaBebidas();
            customAdapter_bebidas = new CustomAdapter_Menu_Bebidas(
                    Reg_menu.this, this, Arow_bebidas_id ,Arow_bebidas_bebidas,
                    Arow_bebidas_categoria,Arow_bebidas_descripcion,Arow_bebidas_precio,Arow_idsaved_bebida);
            recyclerView_bebidas.setAdapter(customAdapter_bebidas);
            recyclerView_bebidas.setLayoutManager(new LinearLayoutManager(Reg_menu.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void llenarListaBebidas(){
        try {


            Cursor cursor =  myDB_bebidas.ListarTodo();
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