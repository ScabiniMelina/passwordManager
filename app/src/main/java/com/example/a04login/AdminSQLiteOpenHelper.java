package com.example.a04login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USUARIOS (ID_USUARIO  INTEGER PRIMARY KEY AUTOINCREMENT,nombre varchar(255),apellido varchar(255), usuario varchar(255), contrasena varchar(255))");
        db.execSQL("CREATE TABLE WEBS (ID_WEB INTEGER PRIMARY KEY AUTOINCREMENT,fk_usuario INTEGER,dominio varchar(255), email varchar(255), contrasena varchar(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
