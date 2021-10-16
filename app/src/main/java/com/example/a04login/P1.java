package com.example.a04login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class P1 extends AppCompatActivity {
    EditText dominio,email,contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1);
        dominio = findViewById(R.id.usuarioLogin);
        email = findViewById(R.id.contrasenaLogin);
        contrasena = findViewById(R.id.contrasenaWeb);
       Bundle bundle = getIntent().getExtras();
        String idWeb = bundle.getString("idWeb");
        String idUsuario = bundle.getString("idUsuario");
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String[] campos = new String[] {"dominio", "email", "contrasena"};
        Cursor fila = bd.query("WEBS", campos,"ID_WEB=? AND fk_usuario=?",new String[]{idWeb,idUsuario},null  ,null   ,null);

        //Cursor fila = bd.rawQuery("SELECT dominio, email, contrasena FROM WEBS WHERE  ID_WEB='"+ idWeb +"'AND fk_usuario '"+ idUsuario +"' ", null);
        if (fila.moveToFirst()) {
            dominio.setText(fila.getString(0));
            email.setText(fila.getString(1));
            contrasena.setText(fila.getString(2));
        } else {
            Toast.makeText(this, "Web invalida", Toast.LENGTH_SHORT).show();
            bd.close();
        }

    }

    public void editarWeb(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Bundle bundle = getIntent().getExtras();
        String idWeb = bundle.getString("idWeb");
        String idUsuario = bundle.getString("idUsuario");
        String dominio1 = dominio.getText().toString();
        String email1 = email.getText().toString();
        String contrasena1 = contrasena.getText().toString();
        if (dominio1.length() == 0 || contrasena1.length() == 0 || email1.length() == 0 ) {
            Toast.makeText(this, "El dominio o la contraseña o el email no pueden estar vacíos", Toast.LENGTH_LONG).show();
        } else {
            ContentValues registro = new ContentValues();
            registro.put("dominio", dominio1);
            registro.put("email", email1);
            registro.put("contrasena", contrasena1);
            int cant = bd.update("WEBS", registro, "ID_WEB=? AND fk_usuario=?",new String[]{idWeb,idUsuario});
            bd.close();
            if (cant == 1) {
                Toast.makeText(this, "se modifico la web", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Inicio.class);
                i.putExtra("idUsuario", idUsuario);
                startActivity(i);
            }else{
                Toast.makeText(this, "No existe una web con ese ID",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}