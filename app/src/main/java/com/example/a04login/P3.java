package com.example.a04login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class P3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3);
        Tabla tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        Bundle bundle = getIntent().getExtras();
        String idUsuario1 = bundle.getString("idUsuario");
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String[] campos = new String[] {"ID_WEB","dominio", "email", "contrasena"};
        Cursor fila = bd.query("WEBS", campos,"fk_usuario='"+idUsuario1+"'",null,null  ,null   ,null);
        if (fila.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya más registros
            do {
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(fila.getString(0));
                elementos.add(fila.getString(1));
                elementos.add(fila.getString(2));
                elementos.add(fila.getString(3));
                tabla.agregarFilaTabla(elementos);
            } while (fila.moveToNext());
        }

    }

    public void ponerPantallaEditar(View v) {
        Bundle bundle = getIntent().getExtras();
        String idUsuario = bundle.getString("idUsuario");
        String usuario = bundle.getString("usuarioLog");
        Intent i = new Intent(this, P5.class);
        i.putExtra("idUsuario", idUsuario);
        i.putExtra("usuarioLog", usuario);
        startActivity(i);
    }

    public void ponerPantallaEliminar(View v) {
        Bundle bundle = getIntent().getExtras();
        String usuario = bundle.getString("usuarioLog");
        String idUsuario = bundle.getString("idUsuario");
        Intent i = new Intent(this, P2.class);
        i.putExtra("idUsuario", idUsuario );
        i.putExtra("usuarioLog", usuario);
        startActivity(i);
    }

}

