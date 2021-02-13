package com.example.tareasqlite.gestion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tareasqlite.data.AdminDB;
import com.example.tareasqlite.model.Producto;

public class ProductoGestion {

    private static AdminDB data = null;
    private static SQLiteDatabase conexion;

    public static void init(AdminDB data) {

        ProductoGestion.data = data;

    }


    public static boolean inserta(Producto producto){

        long registro = -1;

        if(producto!=null){
            ContentValues info = new ContentValues();
            info.put("id",producto.getId());
            info.put("nombre",producto.getNombre());
            info.put("precio",producto.getPrecio());
            info.put("stock",producto.getStock());
            info.put("descripcion",producto.getDescripcion());
            conexion = data.getWritableDatabase();

            registro = conexion.insert("producto",null,info);
            conexion.close();
        }
        return registro!=-1;
    }



    public static Producto busca(int id) {
        Producto producto = null;

        conexion = data.getReadableDatabase();
        Cursor datos = conexion.rawQuery("Select * from producto where id = ?",
                new String[]{""+id+""});


        if(datos.moveToFirst()){

            producto = new Producto(id,
                    datos.getString(1),
                    datos.getInt(2),
                    datos.getInt(3),
                    datos.getString(4));
        }
        conexion.close();
        return producto;
    }

    public static boolean modifica(Producto producto){

        long registro = -1;

        if(producto!=null){
            ContentValues info = new ContentValues();
            info.put("id",producto.getId());
            info.put("nombre",producto.getNombre());
            info.put("precio",producto.getPrecio());
            info.put("stock",producto.getStock());
            info.put("descripcion",producto.getDescripcion());

            conexion = data.getWritableDatabase();
            registro = conexion.update("producto",info,"id=?",new String[]{""+producto.getId()+""});
            conexion.close();
        }
        return registro==1;
    }

    public static boolean elimina(int id) {

        conexion = data.getWritableDatabase();
        long registro = conexion.delete("producto","id=?",new String[]{""+id+""});

        conexion.close();
        return registro==1;

    }

}
