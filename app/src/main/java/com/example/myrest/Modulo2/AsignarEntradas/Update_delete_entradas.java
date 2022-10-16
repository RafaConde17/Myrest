package com.example.myrest.Modulo2.AsignarEntradas;

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

public class Update_delete_entradas  extends AppCompatActivity {

    Button   update_delete_Entradas_deletebtn,
             update_delete_Entradas_ingresarbtn;
    EditText update_delete_Entradas_Preciotxt,
             update_delete_Entradas_Entradatxt,
             update_delete_Entradas_descripciontxt;
    Spinner  update_delete_Entradas_categoria;
    ArrayList<String> Aid, AEntradas, Acategoria, Adescripcion;
    ArrayList<String> Aprecio;
    DAO_Entradas DB;
    String guarpass;
    String id, Entrada, categoria, descripcion, precio;
    private char title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete_entradas);
        update_delete_Entradas_ingresarbtn=findViewById(R.id.update_delete_Entradas_ingresarbtn);
        update_delete_Entradas_deletebtn=findViewById(R.id.update_delete_Entradas_deletebtn);
        update_delete_Entradas_Preciotxt=findViewById(R.id.update_delete_Entradas_Preciotxt) ;
        update_delete_Entradas_Entradatxt=findViewById(R.id.update_delete_Entradas_Entradatxt) ;
        update_delete_Entradas_descripciontxt=findViewById(R.id.update_delete_Entradas_descripcion);
        update_delete_Entradas_categoria=findViewById(R.id.update_delete_Entradas_categoria);
        getIntentData();
        CompledtbOne();
        String pass;
        update_delete_Entradas_deletebtn.setOnClickListener(new View.OnClickListener() {
        String valusu = update_delete_Entradas_Entradatxt.getText().toString().trim();
            @Override
            public void onClick(View view) {
                confirmardialog();
            }
        });
        update_delete_Entradas_ingresarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update_delete_Entradas_Preciotxt.length()==0 ||
                        update_delete_Entradas_Entradatxt.length()==0
                ){
                    Toast.makeText(Update_delete_entradas.this,"Imcomplete data",Toast.LENGTH_LONG).show();
                }else {
                    DAO_Entradas myDB= new DAO_Entradas(Update_delete_entradas.this);
                    Entrada=update_delete_Entradas_Entradatxt.getText().toString();
                    categoria=update_delete_Entradas_categoria.getSelectedItem().toString();
                    descripcion=update_delete_Entradas_descripciontxt.getText().toString();
                    precio=update_delete_Entradas_Preciotxt.getText().toString().trim();
                    myDB.uppDate(
                            id,Entrada,Entrada,categoria,descripcion,precio   );
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
                DAO_Entradas bd = new DAO_Entradas(Update_delete_entradas.this);
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
            if (getIntent().hasExtra("id") && getIntent().hasExtra("entrada")
                    && getIntent().hasExtra("categoria") && getIntent().hasExtra("descripcion")
                    && getIntent().hasExtra("precio")) {
                    id = getIntent().getStringExtra("id");
                    Entrada = getIntent().getStringExtra("entrada");
                    categoria = getIntent().getStringExtra("categoria");
                    descripcion = getIntent().getStringExtra("descripcion");
                    precio = getIntent().getStringExtra("precio");
                if (categoria.equals("Sopas") ) {
                    update_delete_Entradas_categoria.setSelection(0);}
                if (categoria.equals("Ensaladas") ) {
                    update_delete_Entradas_categoria.setSelection(1);}
                if (categoria.equals("Otros") ) {
                    update_delete_Entradas_categoria.setSelection(2);}
                    update_delete_Entradas_Entradatxt.setText(Entrada);
                    update_delete_Entradas_descripciontxt.setText(descripcion);
                    update_delete_Entradas_Preciotxt.setText(precio);
            } else {
                Toast.makeText(this, "Not Nota", Toast.LENGTH_SHORT).show();
            }
        }catch ( Exception e ) {
            Toast.makeText(getApplicationContext(),  ": " + e, Toast.LENGTH_SHORT).show();
        }
    }
    void CompledtbOne(){
        DB = new DAO_Entradas(Update_delete_entradas.this);
        Aid = new ArrayList<>();
        AEntradas = new ArrayList<>();
        Acategoria = new ArrayList<>();
        Adescripcion =new ArrayList<>();
        Aprecio= new ArrayList<>();
    }
}







