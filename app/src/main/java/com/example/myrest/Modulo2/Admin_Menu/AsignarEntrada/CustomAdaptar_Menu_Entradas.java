package com.example.myrest.Modulo2.Admin_Menu.AsignarEntrada;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.R;

import java.util.ArrayList;

public class CustomAdaptar_Menu_Entradas

        extends RecyclerView.Adapter<CustomAdaptar_Menu_Entradas.Menu_Entradas_MyViewHolder>{

        private Context context;



        Activity activity;

        private ArrayList Aguar_Entradaid;
        private ArrayList Arow_Menu_entradas_id , Arow_Menu_entradas_entrada,
                Arow_Menu_entradas_categoria,Arow_Menu_entradas_descripcion ,Arow_Menu_entradas_precio ;
        private CustomAdaptar_Menu_Entradas.Menu_Entradas_MyViewHolder holder;
        private int position;
        public CustomAdaptar_Menu_Entradas(Activity activity, Context context,
                                     ArrayList row_Menu_entradas_id,
                                     ArrayList row_Menu_entradas_entrada,
                                     ArrayList row_Menu_entradas_categoria,
                                     ArrayList row_Menu_entradas_descripcion,
                                     ArrayList row_Menu_entradas_precio,
                                     ArrayList    Aval_Entradaid

                                    )
                                                                            {
            this.activity = activity;
            this.context= context;
            this.Arow_Menu_entradas_id = row_Menu_entradas_id;
            this.Arow_Menu_entradas_entrada = row_Menu_entradas_entrada;
            this.Arow_Menu_entradas_categoria= row_Menu_entradas_categoria;
            this.Arow_Menu_entradas_descripcion = row_Menu_entradas_descripcion;
            this.Arow_Menu_entradas_precio = row_Menu_entradas_precio;
            this.Aguar_Entradaid = Aval_Entradaid;

        }

        @NonNull
        @Override
        public CustomAdaptar_Menu_Entradas.Menu_Entradas_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.row_menu_entradas,parent,false);
            return new CustomAdaptar_Menu_Entradas.Menu_Entradas_MyViewHolder(view);


        }




        @Override
        public void onBindViewHolder(@NonNull Menu_Entradas_MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


            this.position =position;
            holder.row_Menu_entradas_id.setText(String.valueOf(Arow_Menu_entradas_id.get(position)));
            holder.row_Menu_entradas_entrada.setText(String.valueOf(Arow_Menu_entradas_entrada.get(position)));
            holder.row_Menu_entradas_categoria.setText(String.valueOf(Arow_Menu_entradas_categoria.get(position)));
            holder.row_Menu_entradas_descripcion.setText(String.valueOf(Arow_Menu_entradas_descripcion.get(position)));
            holder.row_Menu_entradas_precio.setText(String.valueOf(Arow_Menu_entradas_precio.get(position)));

            holder.row_check1.setOnClickListener(view -> {
                if (((CheckBox) view).isChecked())
                {
                    Aguar_Entradaid.add(String.valueOf(Arow_Menu_entradas_id.get(position)));
                }else
                {
                        for (int i = 0;i<Aguar_Entradaid.size();i++){
                            if (Aguar_Entradaid.get(i) == Arow_Menu_entradas_id.get(position) ) {
                                Aguar_Entradaid.remove(i);
                            }
                        }
                }
            });


        }

        @Override
        public int getItemCount() {
            return Arow_Menu_entradas_id.size();

        }

public class Menu_Entradas_MyViewHolder extends RecyclerView.ViewHolder {

    TextView row_Menu_entradas_id, row_Menu_entradas_entrada,row_Menu_entradas_categoria,
            row_Menu_entradas_descripcion, row_Menu_entradas_precio;
    CheckBox row_check1;
    LinearLayout mainLayout;
    public Menu_Entradas_MyViewHolder(@NonNull View itemView) {
        super(itemView);
        row_Menu_entradas_id = itemView.findViewById(R.id.row_menu_Entradas_id);
        row_Menu_entradas_entrada = itemView.findViewById(R.id.row_menu_Entradas_Entrada);
        row_Menu_entradas_categoria = itemView.findViewById(R.id.row_menu_Entradas_categoria);
        row_Menu_entradas_descripcion = itemView.findViewById(R.id.row_menu_Entradas_descripcion);
        row_Menu_entradas_precio = itemView.findViewById(R.id.row_menu_Entradas_precio);
        row_check1 = itemView.findViewById(R.id.menu_Entradas_checkbox_1);
        mainLayout= itemView.findViewById(R.id.row_menu_Entradas_mainLayout);
    }
}


}

