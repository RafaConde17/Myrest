package com.example.myrest.Modulo2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.Modulo1.Menu_usu;
import com.example.myrest.Modulo1.Usuario_DAO;
import com.example.myrest.Modulo2.Admin_Carta.Menu_Carta_Principal;
import com.example.myrest.Modulo2.Admin_Carta.Menu_carta;
import com.example.myrest.Modulo2.Admin_Menu.CustomAdapter_Menu_Menudia_bebidas;
import com.example.myrest.Modulo2.Admin_Menu.CustomAdapter_Menu_Menudia_entradas;
import com.example.myrest.Modulo2.Admin_Menu.CustomAdapter_Menu_Menudia_platillos;
import com.example.myrest.Modulo2.Admin_Menu.DAO_Menu_Detalles;
import com.example.myrest.Modulo2.Admin_Menu.Menu_menu;
import com.example.myrest.Modulo2.AsignarBebidas.Menu_bebidas;
import com.example.myrest.Modulo2.AsignarEntradas.Menu_Entradas;
import com.example.myrest.Modulo2.AsignarPlatillo.Menu_platillo;
import com.example.myrest.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Menu_Principal extends AppCompatActivity {
    public   int año;
    public int mes;
    public int dia;
    String Fechaactual;
    Toolbar menu_principal_toolbar;
    Button Cartabtn , pedidobtn;
    public  String EnvioID;
    Button menu;
    DAO_Menu_Detalles myDB;
    DAO_Menu_Detalles DB;
    // Llamado al CustomAdapter platillo
    RecyclerView recyclerViewplatillos;
    ArrayList<String> Arow_menu_menudia_platillo_id , Arow_menu_menudia_platillo_fecha,
            Arow_menu_menudia_platillo_idplatillo,Arow_menu_menudia_platillo_platillo,
            Arow_menu_menudia_platillo_categoria,Arow_menu_menudia_platillo_descripcion
        ;
    CustomAdapter_Menu_Menudia_platillos customAdapter_platillo;
    ////
    // Llamado al CustomAdapterentrada
    RecyclerView recyclerViewentradas;
    ArrayList<String> Arow_menu_menudia_entrada_id , Arow_menu_menudia_entrada_fecha,
            Arow_menu_menudia_entrada_identrada,Arow_menu_menudia_entrada_entrada,
            Arow_menu_menudia_entrada_categoria,Arow_menu_menudia_entrada_descripcion
            ;
    CustomAdapter_Menu_Menudia_entradas customAdapter_entrada;
    ////
    // Llamado al CustomAdapter bebidas
    RecyclerView recyclerViewbebidas;
    ArrayList<String> Arow_menu_menudia_bebida_id , Arow_menu_menudia_bebida_fecha,
            Arow_menu_menudia_bebida_idbebida,Arow_menu_menudia_bebida_bebida,
            Arow_menu_menudia_bebida_categoria,Arow_menu_menudia_bebida_descripcion
            ;
    CustomAdapter_Menu_Menudia_bebidas customAdapter_bebida;
    ////
    public void ingresarusu(){


        Usuario_DAO myDB1 = new Usuario_DAO(Menu_Principal.this);
        myDB1.checkusu = 0;
        myDB1.addBook(
                "Admin",
                "Admin",
                "admin",
                "Administrador",
                "Rafael Conde",
                "Conte_r_@hotmail.com",
                1

        );
    }
    public void ingresarusuinvitado(){


        Usuario_DAO myDB = new Usuario_DAO(Menu_Principal.this);
        myDB.checkusu = 0;
        myDB.addBook(
                "Invitado",
                "Invitado",
                "#",
                "Cliente",
                "Defual User",
                "Conte_r_@hotmail.com",
                1

        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true ;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.opcion1){

            try {
                Intent intent = new Intent(Menu_Principal.this, Menu_usu.class);

                startActivityForResult(intent,1);;

            }catch ( Exception e ) {

                Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

            }
        }else if (id==R.id.opcion2){

            try {
                Intent intent = new Intent(Menu_Principal.this, Menu_menu.class);
                intent.putExtra("id", EnvioID);
                startActivityForResult(intent,1);;

            }catch ( Exception e ) {

                Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

            }

        }else if (id==R.id.opcion3){

            try {
                Intent intent = new Intent(Menu_Principal.this, Menu_carta.class);
                intent.putExtra("id", EnvioID);
                startActivityForResult(intent,1);;

            }catch ( Exception e ) {

                Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

            }
        }else if (id==R.id.opcion4){

            try {
                Intent intent = new Intent(Menu_Principal.this, Menu_platillo.class);

                startActivityForResult(intent,1);;

            }catch ( Exception e ) {

                Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

            }


        }else if (id==R.id.opcion5){

            try {
                Intent intent = new Intent(Menu_Principal.this, Menu_Entradas.class);

                startActivityForResult(intent,1);;

            }catch ( Exception e ) {

                Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

            }
        }else if (id==R.id.opcion6) {

            try {
                Intent intent = new Intent(Menu_Principal.this, Menu_bebidas.class);

                startActivityForResult(intent, 1);
                ;

            } catch (Exception e) {

                Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();

            }
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH) ;
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mostrarfecha();

        setContentView(R.layout.activity_menu_principal);
        menu_principal_toolbar = findViewById(R.id.menu_principal_toolbar);
        setSupportActionBar(menu_principal_toolbar);
        recyclerViewplatillos = findViewById(R.id.reg_menurecyclerViewmenudiaPlatillos);
        recyclerViewentradas = findViewById(R.id.reg_menurecyclerViewEntradas);
        recyclerViewbebidas = findViewById(R.id.reg_menurecyclerViewBebidas);
        CompledtbOne_platillo();
        CompledtbOne_entradas();
        CompledtbOne_bebidas();




        String valor4 = getIntent().getStringExtra("id");
        Cartabtn = findViewById(R.id.cartabtn);

        if (valor4==null){
            EnvioID ="0";

        }else {
            EnvioID = getIntent().getStringExtra("id");
        }
        ingresarusu();
        ingresarusuinvitado();
        TextView usertxt;

        usertxt    = findViewById(R.id.usetext);

        String valor3 = getIntent().getStringExtra("datousuario");
        String valor5 = getIntent().getStringExtra("tipousu");
        if (valor3==null) {

            usertxt.setText("Usuario: Invitado");
        }else
        {

            usertxt.setText(""+ valor3);
        }

        Cartabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Menu_Principal.this, Menu_Carta_Principal.class);

                startActivityForResult(intent,1);;


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



    public void mostrarfecha(){

        String fecha = (año + "/" + (mes+1) + "/" + dia);
        Fechaactual=(fecha);

    }

    void CompledtbOne_platillo(){
        try {

             myDB = new DAO_Menu_Detalles(Menu_Principal.this);

            Arow_menu_menudia_platillo_id = new ArrayList<>();
            Arow_menu_menudia_platillo_fecha= new ArrayList<>();
            Arow_menu_menudia_platillo_idplatillo= new ArrayList<>();
            Arow_menu_menudia_platillo_platillo= new ArrayList<>();
            Arow_menu_menudia_platillo_categoria = new ArrayList<>();
            Arow_menu_menudia_platillo_descripcion= new ArrayList<>();

            llenaruno_platillo(Fechaactual);
            customAdapter_platillo = new CustomAdapter_Menu_Menudia_platillos( Menu_Principal.this,
            this, Arow_menu_menudia_platillo_id ,Arow_menu_menudia_platillo_platillo,
                    Arow_menu_menudia_platillo_categoria,Arow_menu_menudia_platillo_descripcion);
            recyclerViewplatillos.setAdapter(customAdapter_platillo);

            recyclerViewplatillos.setLayoutManager(new LinearLayoutManager(Menu_Principal.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void llenaruno_platillo(String buscar){
        try {



            DB = new DAO_Menu_Detalles(Menu_Principal.this);
            Cursor cursor=  DB.ListarTodomenudedia_platillo(buscar);
            while (cursor.moveToNext()){
                Arow_menu_menudia_platillo_id.add(cursor.getString(0));
               Arow_menu_menudia_platillo_fecha.add(cursor.getString(1));
                Arow_menu_menudia_platillo_idplatillo.add(cursor.getString(2));
                Arow_menu_menudia_platillo_platillo.add(cursor.getString(3));
                Arow_menu_menudia_platillo_categoria.add(cursor.getString(4));
                Arow_menu_menudia_platillo_descripcion.add(cursor.getString(5));
            }

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void CompledtbOne_entradas(){
        try {

            myDB = new DAO_Menu_Detalles(Menu_Principal.this);

            Arow_menu_menudia_entrada_id = new ArrayList<>();
            Arow_menu_menudia_entrada_fecha= new ArrayList<>();
            Arow_menu_menudia_entrada_identrada= new ArrayList<>();
            Arow_menu_menudia_entrada_entrada= new ArrayList<>();
            Arow_menu_menudia_entrada_categoria = new ArrayList<>();
            Arow_menu_menudia_entrada_descripcion= new ArrayList<>();

            llenaruno_entradas(Fechaactual);
            customAdapter_entrada = new CustomAdapter_Menu_Menudia_entradas( Menu_Principal.this,
                    this, Arow_menu_menudia_entrada_id ,Arow_menu_menudia_entrada_entrada,
                    Arow_menu_menudia_entrada_categoria,Arow_menu_menudia_entrada_descripcion);
            recyclerViewentradas.setAdapter(customAdapter_entrada);

            recyclerViewentradas.setLayoutManager(new LinearLayoutManager(Menu_Principal.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void llenaruno_entradas(String buscar){
        try {



            DB = new DAO_Menu_Detalles(Menu_Principal.this);
            Cursor cursor=  DB.ListarTodomenudedia_entrada(buscar);
            while (cursor.moveToNext()){
                Arow_menu_menudia_entrada_id.add(cursor.getString(0));
                Arow_menu_menudia_entrada_fecha.add(cursor.getString(1));
                Arow_menu_menudia_entrada_identrada.add(cursor.getString(2));
                Arow_menu_menudia_entrada_entrada.add(cursor.getString(3));
                Arow_menu_menudia_entrada_categoria.add(cursor.getString(4));
                Arow_menu_menudia_entrada_descripcion.add(cursor.getString(5));
            }

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void CompledtbOne_bebidas(){
        try {

            myDB = new DAO_Menu_Detalles(Menu_Principal.this);

            Arow_menu_menudia_bebida_id = new ArrayList<>();
            Arow_menu_menudia_bebida_fecha= new ArrayList<>();
            Arow_menu_menudia_bebida_idbebida= new ArrayList<>();
            Arow_menu_menudia_bebida_bebida= new ArrayList<>();
            Arow_menu_menudia_bebida_categoria = new ArrayList<>();
            Arow_menu_menudia_bebida_descripcion= new ArrayList<>();

            llenaruno_bebidas(Fechaactual);
            customAdapter_bebida = new CustomAdapter_Menu_Menudia_bebidas( Menu_Principal.this,
                    this, Arow_menu_menudia_bebida_id ,Arow_menu_menudia_bebida_bebida,
                    Arow_menu_menudia_bebida_categoria,Arow_menu_menudia_bebida_descripcion);
            recyclerViewbebidas.setAdapter(customAdapter_bebida);

            recyclerViewbebidas.setLayoutManager(new LinearLayoutManager(Menu_Principal.this));

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
    void llenaruno_bebidas(String buscar){
        try {



            DB = new DAO_Menu_Detalles(Menu_Principal.this);
            Cursor cursor=  DB.ListarTodomenudedia_bebida(buscar);
            while (cursor.moveToNext()){
                Arow_menu_menudia_bebida_id.add(cursor.getString(0));
                Arow_menu_menudia_bebida_fecha.add(cursor.getString(1));
                Arow_menu_menudia_bebida_idbebida.add(cursor.getString(2));
                Arow_menu_menudia_bebida_bebida.add(cursor.getString(3));
                Arow_menu_menudia_bebida_categoria.add(cursor.getString(4));
                Arow_menu_menudia_bebida_descripcion.add(cursor.getString(5));
            }

        } catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

        }
    }
}