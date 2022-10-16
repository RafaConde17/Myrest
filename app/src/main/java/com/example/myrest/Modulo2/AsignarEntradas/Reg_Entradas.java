package com.example.myrest.Modulo2.AsignarEntradas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myrest.R;

public class Reg_Entradas extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_entradas);



        Button registrarbtn =(Button) findViewById(R.id.reg_Entradas_ingresarbtn);

        EditText Entradatxt =(EditText) findViewById(R.id.reg_Entradas_Entrada);
        Spinner categoria =(Spinner) findViewById(R.id.reg_Entradas_categoria);
        EditText descripcion =(EditText) findViewById(R.id.reg_Entradas_descripciontxt);
        EditText precio =(EditText) findViewById(R.id.reg_Entradas_Preciotxt);
        registrarbtn.setOnClickListener(view -> {
            try {
                if (Entradatxt.length() == 0 || precio.length() == 0) {

                    Toast.makeText(Reg_Entradas.this, "Imcomplete data", Toast.LENGTH_LONG).show();

                } else {


                    DAO_Entradas myDB = new DAO_Entradas(Reg_Entradas.this);
                    myDB.checkentrada = 1;
                    myDB.addBook(
                            Entradatxt.getText().toString().trim(),
                            Entradatxt.getText().toString().trim(),
                            categoria.getSelectedItem().toString().trim(),
                            descripcion.getText().toString().trim(),
                            precio.getText().toString().trim()


                    );

                    if (myDB.CheckLimp == 0) {

                        Entradatxt.setText("");

                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), "Entrada ya existe", duration);
                        toast.show();

                    } else {
                        Entradatxt.setText("");
                        categoria.setSelection(0);
                        descripcion.setText("");
                        precio.setText("");


                    }
                }
            } catch (Exception e) {

                Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();

            }

        });

    }
}


