package com.example.myrest.Modulo1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myrest.R;

public class Reg_usuario extends AppCompatActivity {

    EditText clientetxt , passwordtxt,Apellidonombretxt, correotxt;
    Spinner Sp1;
    Button loginbtn ,registrarbtn;
    Usuario_DAO DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_usuario);

        Usuario_DAO g = new Usuario_DAO(this);

        Button registrarbtn =(Button) findViewById(R.id.reg_ingresarbtn);
        EditText clientetxt =(EditText) findViewById(R.id.reg_clientetxt);
        EditText passwordtxt =(EditText) findViewById(R.id.reg_passwordtxt);
        EditText Apellidonombretxt =(EditText) findViewById(R.id.reg_Apellidonombretxt);
        EditText correotxt =(EditText) findViewById(R.id.reg_correotxt);
        Spinner Sp1 =(Spinner) findViewById(R.id.reg_Sp1);



//Bloqueo de objeto + envio  de dato
        String Enviardato =getIntent().getStringExtra("Enviardato");

        if (Enviardato.equals("1")){

            Sp1.setEnabled(false);
            Sp1.setSelection(2);


        }

//----

        registrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(clientetxt.length()==0 || passwordtxt.length()==0 || Apellidonombretxt.length()==0){

                    Toast.makeText(Reg_usuario.this,"Imcomplete data",Toast.LENGTH_LONG).show();

                }else {
                    if (clientetxt.length() > 9 ) {

                        Toast.makeText(Reg_usuario.this, "Usuario debil ", Toast.LENGTH_LONG).show();

                    } else {

                        Usuario_DAO myDB = new Usuario_DAO(Reg_usuario.this);
                        myDB.checkusu = 1;
                        myDB.addBook(
                                clientetxt.getText().toString().trim(),
                                clientetxt.getText().toString().trim(),
                                passwordtxt.getText().toString().trim(),
                                Sp1.getSelectedItem().toString().trim(),
                                Apellidonombretxt.getText().toString().trim(),
                                correotxt.getText().toString().trim(),
                                1
                        );

                        if   (myDB.CheckLimp == 0){

                            clientetxt.setText("");

                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(getApplicationContext(), "Usuario ya existe", duration);
                            toast.show();

                        }else {
                            clientetxt.setText("");
                            passwordtxt.setText("");
                            Sp1.setSelection(0);
                            correotxt.setText("");
                            Apellidonombretxt.setText("");
                        }

                    }
                }
            }
        });




    }
}
