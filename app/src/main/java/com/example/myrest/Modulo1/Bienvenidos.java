package com.example.myrest.Modulo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myrest.Modulo2.Menu_Principal;
import com.example.myrest.R;

public class Bienvenidos extends AppCompatActivity {


    Button loginbtn,Deliverybtn,ingresomenubtn,wspbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenidos);


       Button loginbtn = (Button) findViewById(R.id.loginbtn);
        Button Deliverybtn = (Button) findViewById(R.id.deliverybtn);
       Button Ingresomenubtn =(Button) findViewById(R.id.Ingresomenubtn);
        Button wspbtn =(Button) findViewById(R.id.wspbtn);




        Ingresomenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent(Bienvenidos.this, Menu_Principal.class);
                    startActivity(intent);
                }catch ( Exception e ) {

                    Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

                }
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Bienvenidos.this, Login.class);
                    startActivity(intent);
                }catch ( Exception e ) {

                    Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();

                }
            }
        });


    }}
