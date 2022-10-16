package com.example.myrest.Modulo1;

import android.content.DialogInterface;
import android.database.Cursor;
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

public class Update_delete_usu extends AppCompatActivity {
Button update_delete_usuario_updaterbtn, update_delete_usuario_deletebtn    ;
EditText update_delete_usuario_clientetxt,
    update_delete_usuario_passwordtxt,
    update_delete_usuario_Apellidonombretxt,
            update_delete_usuario_correotxt;
Spinner update_delete_usuario_Sp1;

ArrayList<String> Aid ,Ausuario, Atipousuario, Adatos ;
ArrayList<String> Apass,ACorreo,Aestado;
Usuario_DAO DB;
String guarpass;

private char title;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.update_delete_usuario);



    update_delete_usuario_updaterbtn  =  findViewById(R.id.update_delete_usuario_updaterbtn);
    update_delete_usuario_deletebtn     = findViewById(R.id.update_delete_usuario_deletebtn);

    update_delete_usuario_clientetxt =  findViewById(R.id.update_delete_usuario_clientetxt) ;
    update_delete_usuario_passwordtxt      =findViewById(R.id.update_delete_usuario_passwordtxt) ;
    update_delete_usuario_Apellidonombretxt            =  findViewById(R.id.update_delete_usuario_Apellidonombretxt);
    update_delete_usuario_correotxt =  findViewById(R.id.update_delete_usuario_correotxt);

    update_delete_usuario_Sp1          =findViewById(R.id.update_delete_usuario_Sp1);

       getIntentData();
      CompledtbOne();




    String pass;
    update_delete_usuario_deletebtn.setOnClickListener(new View.OnClickListener() {
        String valusu = update_delete_usuario_clientetxt.getText().toString().trim();

        @Override
        public void onClick(View view) {
            if (valusu.equals("Admin") ){
            Toast.makeText(Update_delete_usu.this, "can't delete this user",Toast.LENGTH_SHORT).show();
            }else if (valusu.equals("Invitado")) {
            Toast.makeText(Update_delete_usu.this, "can't delete this user",Toast.LENGTH_SHORT).show();
            }else{
            confirmardialog();
            }
        }
    });



    update_delete_usuario_updaterbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(update_delete_usuario_clientetxt.length()==0 ||
                            update_delete_usuario_passwordtxt.length()==0 ||
                            update_delete_usuario_Apellidonombretxt.length()==0 ){

                        Toast.makeText(Update_delete_usu.this,"Imcomplete data",Toast.LENGTH_LONG).show();

                    }else {
                        if (update_delete_usuario_clientetxt.length() > 10 || update_delete_usuario_passwordtxt.length() > 10) {
                            Toast.makeText(Update_delete_usu.this, "Username and Password only accept a maximum of 10 character", Toast.LENGTH_LONG).show();


                        }
                        else {


                            Usuario_DAO myDB= new Usuario_DAO(Update_delete_usu.this);
                            usuario    = update_delete_usuario_clientetxt.getText().toString();
                            guarpass    =update_delete_usuario_passwordtxt.getText().toString();
                            tipousuario   =update_delete_usuario_Sp1.getSelectedItem().toString();
                            datos     =update_delete_usuario_Apellidonombretxt.getText().toString().trim();
                            correo     =update_delete_usuario_correotxt.getText().toString().trim();

                            myDB.uppDate(
                                    id,usuario,usuario,guarpass,tipousuario,datos,correo,1   );


                        }
                    }

                }
            });





    setIntent(getIntent());

    getParentActivityIntent();


}


    String id , usuario , tipousuario, datos, correo;
    void confirmardialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this );
        builder.setTitle("Delete "+ title + "?");
        builder.setMessage("Are you sure you want to delete "+ title+ " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Usuario_DAO bd = new Usuario_DAO(Update_delete_usu.this);

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


            if (getIntent().hasExtra("id") && getIntent().hasExtra("usuario")
                    && getIntent().hasExtra("tipousuario") && getIntent().hasExtra("datos")) {


                id = getIntent().getStringExtra("id");
                usuario = getIntent().getStringExtra("usuario");
                tipousuario = getIntent().getStringExtra("tipousuario");
                datos = getIntent().getStringExtra("datos");

                if (tipousuario.equals("Administrador") ) {

                    update_delete_usuario_Sp1.setSelection(0);
   }
                if (tipousuario.equals("Mesero") ) {
                    update_delete_usuario_Sp1.setSelection(1);

                }
                if (tipousuario.equals("Cliente") ) {
                    update_delete_usuario_Sp1.setSelection(2);

                }

                update_delete_usuario_clientetxt.setText(usuario);


                update_delete_usuario_Apellidonombretxt.setText(datos);

            } else {


                Toast.makeText(this, "Not Nota", Toast.LENGTH_SHORT).show();


            }

         }catch ( Exception e ) {

        Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();



        }
    }


    void CompledtbOne(){

        DB = new Usuario_DAO(Update_delete_usu.this);

        Aid = new ArrayList<>();
        Ausuario= new ArrayList<>();
        Apass = new ArrayList<>();
        Atipousuario= new ArrayList<>();
        Adatos= new ArrayList<>();
        ACorreo= new ArrayList<>();
        Aestado = new ArrayList<>();



        String  Textbuscado =id;


        llenaruno(Textbuscado);


        update_delete_usuario_passwordtxt.setText(String.valueOf(Apass).replace("[", "").replace("]", ""));
        guarpass = String.valueOf(update_delete_usuario_passwordtxt) ;
    }

    void llenaruno(String buscar){

        DB = new Usuario_DAO(Update_delete_usu.this);
        Cursor cursor=  DB.Listarunoupdate(buscar);



        while (cursor.moveToNext()) {
            Apass.add(cursor.getString(2));
            Atipousuario.add(cursor.getString(3));
            Adatos.add(cursor.getString(4));
            ACorreo.add(cursor.getString(5));
            Aestado.add(cursor.getString(6));
            Aid.add(cursor.getString(0));
            Ausuario.add(cursor.getString(1));




        }


    }



}