package com.example.myrest.Modulo2.AsignarBebidas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myrest.R;

import java.util.ArrayList;

public class Update_delete_bebidas extends AppCompatActivity {

    Button update_delete_bebidas_deletebtn
            , update_delete_bebidas_ingresarbtn;
    EditText update_delete_bebidas_Preciotxt,
            update_delete_bebidas_bebidastxt,
            update_delete_bebidas_descripciontxt;
    Spinner update_delete_bebidas_categoria;

    ArrayList<String> Aid ,Abebidas, Acategoria, Adescripcion ;
    ArrayList<String> Aprecio;
    DAO_bebidas DB;
    String guarpass;

    String id , bebidas, categoria, descripcion, precio;

    private char title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete_bebidas);



        update_delete_bebidas_ingresarbtn  =  findViewById(R.id.update_delete_bebidas_ingresarbtn);
        update_delete_bebidas_deletebtn     = findViewById(R.id.update_delete_bebidas_deletebtn);

        update_delete_bebidas_Preciotxt =  findViewById(R.id.update_delete_bebidas_Preciotxt) ;
        update_delete_bebidas_bebidastxt      =findViewById(R.id.update_delete_bebidas_bebidastxt) ;
        update_delete_bebidas_descripciontxt            =  findViewById(R.id.update_delete_bebidas_descripcion);
        update_delete_bebidas_categoria =  findViewById(R.id.update_delete_bebidas_categoria);



        getIntentData();
        CompledtbOne();




        String pass;

        update_delete_bebidas_deletebtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                confirmardialog();

            }
        });



        update_delete_bebidas_ingresarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(update_delete_bebidas_Preciotxt.length()==0 ||
                        update_delete_bebidas_bebidastxt.length()==0
                ){

                    Toast.makeText(Update_delete_bebidas.this,"Imcomplete data",Toast.LENGTH_LONG).show();

                }else {



                    DAO_bebidas myDB= new DAO_bebidas(Update_delete_bebidas.this);

                    bebidas    = update_delete_bebidas_bebidastxt.getText().toString();
                    categoria    =update_delete_bebidas_categoria.getSelectedItem().toString();
                    descripcion   =update_delete_bebidas_descripciontxt.getText().toString();
                    precio     =update_delete_bebidas_Preciotxt.getText().toString().trim();


                    myDB.uppDate(
                            id,bebidas,bebidas,categoria,descripcion,precio   );



                }

            }
        });





        setIntent(getIntent());

        getParentActivityIntent();


    }



    void confirmardialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this );
        builder.setTitle("Delete "+ title + "?");
        builder.setMessage("Are you sure you want to delete "+ title+ " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DAO_bebidas bd = new DAO_bebidas(Update_delete_bebidas.this);

                bd.deleteOnerow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }



    void getIntentData() {
        try {


            if (getIntent().hasExtra("id") && getIntent().hasExtra("bebidas")
                    && getIntent().hasExtra("categoria") && getIntent().hasExtra("descripcion")
                    && getIntent().hasExtra("precio")) {

                id = getIntent().getStringExtra("id");
                bebidas = getIntent().getStringExtra("bebidas");
                categoria = getIntent().getStringExtra("categoria");
                descripcion = getIntent().getStringExtra("descripcion");

                precio = getIntent().getStringExtra("precio");





                if (categoria.equals("Gaseosa") ) {
                    update_delete_bebidas_categoria.setSelection(0);}
                if (categoria.equals("Refresco") ) {
                    update_delete_bebidas_categoria.setSelection(1);}
                if (categoria.equals("Infusiones") ) {
                    update_delete_bebidas_categoria.setSelection(2);}
                if (categoria.equals("Jugos") ) {
                    update_delete_bebidas_categoria.setSelection(3);}
                if (categoria.equals("Otros") ) {
                    update_delete_bebidas_categoria.setSelection(4);}


                update_delete_bebidas_bebidastxt.setText(bebidas);

                update_delete_bebidas_descripciontxt.setText(descripcion);
                update_delete_bebidas_Preciotxt.setText(precio);

            } else {


                Toast.makeText(this, "Not Nota", Toast.LENGTH_SHORT).show();


            }

        }catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();


        }
    }


    void CompledtbOne(){

        DB = new DAO_bebidas(Update_delete_bebidas.this);

        Aid = new ArrayList<>();
        Abebidas = new ArrayList<>();
        Acategoria = new ArrayList<>();
        Adescripcion =new ArrayList<>();
        Aprecio= new ArrayList<>();





    }




}







