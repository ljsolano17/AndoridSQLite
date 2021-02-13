package com.example.tareasqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDB extends SQLiteOpenHelper {

    public AdminDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table producto (id int primary key, nombre text, precio decimal,stock int, descripcion)");
        db.execSQL("insert into producto values (1,'S10',500000,25,'Telefono Samsung s10 con 6gb Ram...')");
        db.execSQL("insert into producto values (2,'S9',500000,25,'Telefono Samsung s9 con 4gb Ram...')");
        db.execSQL("insert into producto values (3,'MacBook Air',500000,25,'Computadora tipo laptop de la marca Apple')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("create table producto (id int primary key, nombre text, precio decimal,stock int, descripcion)");
        db.execSQL("insert into producto values (1,'S10',500000,25,'Telefono Samsung s10 con 6gb Ram...')");
        db.execSQL("insert into producto values (2,'S9',500000,25,'Telefono Samsung s9 con 4gb Ram...')");
        db.execSQL("insert into producto values (3,'MacBook Air',500000,25,'Computadora tipo laptop de la marca Apple')");

    }
}
