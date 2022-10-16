package com.example.myrest.Modulo2.AsignarPlatillo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myrest.R;

public class Reg_platillo extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_platillo);


        DAO_Platillo g = new DAO_Platillo(this);

        Button registrarbtn =(Button) findViewById(R.id.reg_platillo_ingresarbtn);

        EditText platillotxt =(EditText) findViewById(R.id.reg_platillo_platillo);
        Spinner categoria =(Spinner) findViewById(R.id.reg_platillo_categoria);
        EditText descripcion =(EditText) findViewById(R.id.reg_platillo_descripciontxt);
        EditText precio =(EditText) findViewById(R.id.reg_platillo_Preciotxt);
        registrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (platillotxt.length() == 0 || precio.length() == 0) {

                        Toast.makeText(Reg_platillo.this, "Imcomplete data", Toast.LENGTH_LONG).show();

                    } else {


                        DAO_Platillo myDB = new DAO_Platillo(Reg_platillo.this);
                        myDB.checkplatillo = 1;
                        myDB.addBook(
                                platillotxt.getText().toString().trim(),
                                platillotxt.getText().toString().trim(),
                                categoria.getSelectedItem().toString().trim(),
                                descripcion.getText().toString().trim(),
                                precio.getText().toString().trim()


                        );

                        if (myDB.CheckLimp == 0) {

                            platillotxt.setText("");

                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(getApplicationContext(), "Platillo ya existe", duration);
                            toast.show();

                        } else {
                            platillotxt.setText("");
                            categoria.setSelection(0);
                            descripcion.setText("");
                            precio.setText("");


                        }
                    }
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), ": " + e, Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
