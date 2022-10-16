package com.example.myrest.Modulo2.AsignarPlatillo;

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

public class Update_delete_platillo extends AppCompatActivity {
    Button update_delete_platillo_deletebtn
            , update_delete_platillo_ingresarbtn;
    EditText update_delete_platillo_Preciotxt,
            update_delete_platillo_platillotxt,
            update_delete_platillo_descripciontxt;
    Spinner update_delete_platillo_categoria;

    ArrayList<String> Aid ,Aplatillo, Acategoria, Adescripcion ;
    ArrayList<String> Aprecio;
    DAO_Platillo DB;
    String guarpass;

    String id , platillo , categoria, descripcion, precio;

    private char title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete_platillo);



        update_delete_platillo_ingresarbtn  =  findViewById(R.id.update_delete_platillo_ingresarbtn);
        update_delete_platillo_deletebtn     = findViewById(R.id.update_delete_platillo_deletebtn);

        update_delete_platillo_Preciotxt =  findViewById(R.id.update_delete_platillo_Preciotxt) ;
        update_delete_platillo_platillotxt      =findViewById(R.id.update_delete_platillo_platillotxt) ;
        update_delete_platillo_descripciontxt            =  findViewById(R.id.update_delete_platillo_descripcion);
        update_delete_platillo_categoria =  findViewById(R.id.update_delete_platillo_categoria);



    getIntentData();
    CompledtbOne();




    String pass;

        update_delete_platillo_deletebtn.setOnClickListener(new View.OnClickListener() {
        String valusu = update_delete_platillo_platillotxt.getText().toString().trim();

        @Override
        public void onClick(View view) {

                confirmardialog();

        }
    });



        update_delete_platillo_ingresarbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(update_delete_platillo_Preciotxt.length()==0 ||
                    update_delete_platillo_platillotxt.length()==0
                     ){

                Toast.makeText(Update_delete_platillo.this,"Imcomplete data",Toast.LENGTH_LONG).show();

            }else {



                    DAO_Platillo myDB= new DAO_Platillo(Update_delete_platillo.this);

                    platillo    = update_delete_platillo_platillotxt.getText().toString();
                    categoria    =update_delete_platillo_categoria.getSelectedItem().toString();
                descripcion   =update_delete_platillo_descripciontxt.getText().toString();
                    precio     =update_delete_platillo_Preciotxt.getText().toString().trim();


                    myDB.uppDate(
                            id,platillo,platillo,categoria,descripcion,precio   );



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
                DAO_Platillo bd = new DAO_Platillo(Update_delete_platillo.this);

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


            if (getIntent().hasExtra("id") && getIntent().hasExtra("platillo")
                    && getIntent().hasExtra("categoria") && getIntent().hasExtra("descripcion")
                    && getIntent().hasExtra("precio")) {

                id = getIntent().getStringExtra("id");
                platillo = getIntent().getStringExtra("platillo");
                categoria = getIntent().getStringExtra("categoria");
                descripcion = getIntent().getStringExtra("descripcion");

                precio = getIntent().getStringExtra("precio");





                if (categoria.equals("Platillo Marino") ) {
                    update_delete_platillo_categoria.setSelection(0);}
                if (categoria.equals("Platillo Chino") ) {
                    update_delete_platillo_categoria.setSelection(1);}
                if (categoria.equals("Platillo Criollo") ) {
                    update_delete_platillo_categoria.setSelection(2);}
                if (categoria.equals("Pastas") ) {
                    update_delete_platillo_categoria.setSelection(3);}


                update_delete_platillo_platillotxt.setText(platillo);

                update_delete_platillo_descripciontxt.setText(descripcion);
                update_delete_platillo_Preciotxt.setText(precio);

            } else {


                Toast.makeText(this, "Not Nota", Toast.LENGTH_SHORT).show();


            }

        }catch ( Exception e ) {

            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();


        }
    }


    void CompledtbOne(){

        DB = new DAO_Platillo(Update_delete_platillo.this);

        Aid = new ArrayList<>();
        Aplatillo = new ArrayList<>();
        Acategoria = new ArrayList<>();
        Adescripcion =new ArrayList<>();
        Aprecio= new ArrayList<>();





    }




    }







