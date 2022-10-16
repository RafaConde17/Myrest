package com.example.myrest.Modulo2.AsignarBebidas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myrest.R;

public class Reg_bebidas extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_bebidas);

        Button registrarbtn =(Button) findViewById(R.id.reg_bebidas_ingresarbtn);

        EditText bebidatxt =(EditText) findViewById(R.id.reg_bebidas_bebida);
        Spinner categoria =(Spinner) findViewById(R.id.reg_bebidas_categoria);
        EditText descripcion =(EditText) findViewById(R.id.reg_bebidas_descripciontxt);
        EditText precio =(EditText) findViewById(R.id.reg_bebidas_Preciotxt);
        registrarbtn.setOnClickListener(view -> {
            try {
                if (bebidatxt.length() == 0 || precio.length() == 0) {

                    Toast.makeText(Reg_bebidas.this, "Imcomplete data", Toast.LENGTH_LONG).show();

                } else {


                    DAO_bebidas myDB = new DAO_bebidas(Reg_bebidas.this);
                    myDB.checkbebida = 1;
                    myDB.addBook(
                            bebidatxt.getText().toString().trim(),
                            bebidatxt.getText().toString().trim(),
                            categoria.getSelectedItem().toString().trim(),
                            descripcion.getText().toString().trim(),
                            precio.getText().toString().trim()


                    );

                    if (myDB.CheckLimp == 0) {

                        bebidatxt.setText("");

                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), "La Bebida ya existe", duration);
                        toast.show();

                    } else {
                        bebidatxt.setText("");
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


