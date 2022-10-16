package com.example.myrest.Modulo1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myrest.HelperDao;
import com.example.myrest.Modulo2.Menu_Principal;
import com.example.myrest.R;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    public int valRegister;

    HelperDao db ;
    Usuario_DAO DB;
    String u , p;


    public String Enviardato;
    public String usuariologin;
    public String tipousu;
    public String EnvioID;
    EditText login_clientetxt , login_passwordtxt;

    Button login_loginbtn ,login_registrarbtn;


    public void ingresarusuinvitado(){


        Usuario_DAO myDB = new Usuario_DAO(Login.this);
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
    public void ingresarusu(){


        Usuario_DAO myDB = new Usuario_DAO(Login.this);
        myDB.checkusu = 0;
        myDB.addBook(
                "Admin",
                "Admin",
                "admin",
                "Administrador",
                "Rafael Conde",
                "Conte_r_@hotmail.com",
                1

        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_loginbtn =(Button) findViewById(R.id.login_ingresarbtn);
        Button login_registrarbtn =(Button) findViewById(R.id.login_registrarbtn);
        EditText login_clientetxt= (EditText) findViewById(R.id.login_clientetxt);
        EditText login_passwordtxt= (EditText) findViewById(R.id.login_passwordtxt);
        db = new HelperDao(Login.this);
        ingresarusu();
        ingresarusuinvitado();


        login_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usu = login_clientetxt.getText().toString().trim();

                String pass = login_passwordtxt.getText().toString().trim();
                Usuario_DAO db = new Usuario_DAO(Login.this);

                try {
                    if (login_clientetxt.getText().toString().trim().equals("")
                            || login_passwordtxt.getText().toString().trim().equals("")) {
                        Toast.makeText(Login.this, "DATA INCOMPLETE", Toast.LENGTH_SHORT).show();

                    } else {
                        if (db.login(usu, pass) == 1) {
                            Toast.makeText(Login.this, "LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();

                            CompledtbOne(usu,pass);

                            Intent ini = new Intent(Login.this, Menu_Principal.class);
                            ini.putExtra("id", EnvioID);
                            ini.putExtra("usu", usu);
                            ini.putExtra("pass", pass);
                            ini.putExtra("tipousu", tipousu);
                            ini.putExtra("datousuario", usuariologin);

                            startActivity(ini);

                        } else {
                            Toast.makeText(Login.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch ( Exception e ) {

                    Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

                }
            }
        });
        login_registrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Enviardato= "1";
                    Intent intent = new Intent(Login.this, Reg_usuario.class);
                    intent.putExtra("Enviardato", Enviardato);
                    startActivityForResult(intent,1);;
                }catch ( Exception e ) {

                    Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    void CompledtbOne(String val1, String val2){

        DB = new Usuario_DAO(Login.this);


        Atxtid = new ArrayList<>();
        AUsario= new ArrayList<>();

        Apassword= new ArrayList<>();
        Atipousuario= new ArrayList<>();
        Ainformacion= new ArrayList<>();



        llenaruno(val1, val2);


        String complemento =  (String.valueOf(AUsario).replace("[", "").replace("]", ""))+
             " Tipo: "+   (String.valueOf(Atipousuario).replace("[", "").replace("]", ""));
        String complemento2 = (String.valueOf(Atipousuario).replace("[", "").replace("]", ""));
        usuariologin = "Usuario; " + complemento;
        tipousu = complemento2;
        EnvioID = String.valueOf(Atxtid).replace("[", "").replace("]", "");


        //+ String.valueOf(Ainformacion);
    }

    void llenaruno(String buscar1, String buscar2){

        DB = new Usuario_DAO(Login.this);

        Cursor cursor=  DB.obtenerusuario(buscar1,buscar2);
        while (cursor.moveToNext()){
            Atxtid.add(cursor.getString(0));
            AUsario.add(cursor.getString(1));
            Apassword.add(cursor.getString(2));
            Atipousuario.add(cursor.getString(3));
            Ainformacion.add(cursor.getString(4));


        }


    }

    ArrayList<String> Atxtid,AUsario,Apassword,Atipousuario,Ainformacion;

}
