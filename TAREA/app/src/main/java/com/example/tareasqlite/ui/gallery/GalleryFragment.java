package com.example.tareasqlite.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tareasqlite.R;
import com.example.tareasqlite.data.AdminDB;
import com.example.tareasqlite.gestion.ProductoGestion;
import com.example.tareasqlite.model.Producto;

public class GalleryFragment extends Fragment {

    private EditText etId;
    private EditText etNombre;
    private EditText etPrecio;
    private EditText etStock;
    private EditText etDescripcion;

    private Button btInsertar;
    private Button btConsultar;
    private Button btModificar;
    private Button btEliminar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        etId = root.findViewById(R.id.etId);
        etNombre = root.findViewById(R.id.etNombre);
        etPrecio = root.findViewById(R.id.etPrecio);
        etStock = root.findViewById(R.id.etStock);
        etDescripcion = root.findViewById(R.id.etDescripcion);
//Obtenemos los datos que estan en los campos
        btInsertar = root.findViewById(R.id.btInsertar);
        btConsultar = root.findViewById(R.id.btConsulta);
        btModificar = root.findViewById(R.id.btModificar);
        btEliminar = root.findViewById(R.id.btEliminar);

        AdminDB data = new AdminDB(getContext(),"base.db",null,1);
        ProductoGestion.init(data);

        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserta();
            }
        });
        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifica();
            }
        });

        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulta();
            }
        });
        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elimina();
            }
        });


        return root;
    }
    private void limpia(){
        etId.setText("");
        etNombre.setText("");
        etPrecio.setText("");
        etStock.setText("");
        etDescripcion.setText("");

    }

    private void presenta(Producto producto){
        if(producto!=null){
            etId.setText(""+producto.getId());
            etPrecio.setText(""+producto.getPrecio());
            etNombre.setText(producto.getNombre());
            etStock.setText(""+producto.getStock());
            etDescripcion.setText(producto.getDescripcion());
        }else{
            limpia();
        }

    }


    private Producto getProducto(){
        Producto producto = null;

        String id = etId.getText().toString();
        String nombre = etNombre.getText().toString();
        String precio = etPrecio.getText().toString();
        String stock = etStock.getText().toString();
        String descripcion = etDescripcion.getText().toString();

        if(!id.isEmpty()&&!nombre.isEmpty()&&!precio.isEmpty()&&!stock.isEmpty()&&!descripcion.isEmpty()){
            producto = new Producto(
                    Integer.parseInt(id),
                    nombre,
                    Integer.parseInt(precio),
                    Integer.parseInt(stock),
                    descripcion
            );
        }

        return producto;
    }
    private void elimina() {

        String id = etId.getText().toString();
        boolean eliminado = false;
        if(!id.isEmpty()){
            int llave = Integer.parseInt(id);
            eliminado=ProductoGestion.elimina(llave);

        }
        if(eliminado){
            Toast.makeText(getContext(),"Producto eliminado",Toast.LENGTH_SHORT).show();
            limpia();
        }else{
            Toast.makeText(getContext(),"Producto NO eliminado",Toast.LENGTH_SHORT).show();
        }


    }

    private void consulta() {

        String id = etId.getText().toString();
        boolean encontrado = false;
        if(!id.isEmpty()){
            int llave = Integer.parseInt(id);
            Producto producto = ProductoGestion.busca(llave);
            if(producto!=null){
                presenta(producto);
                encontrado=true;
            }
        }
        if(encontrado==false){
            Toast.makeText(getContext(),"Producto no encontrado",Toast.LENGTH_SHORT).show();
        }

    }
    private void modifica() {

        if(ProductoGestion.modifica(getProducto())){
            Toast.makeText(getContext(),"Se modificó",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),"No se modificó",Toast.LENGTH_SHORT).show();
        }

    }
    private void inserta() {

        if(ProductoGestion.inserta(getProducto())){
            Toast.makeText(getContext(),"Se insertó",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),"No se insertó",Toast.LENGTH_SHORT).show();
        }

    }

}