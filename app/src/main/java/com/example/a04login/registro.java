package com.example.a04login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registro extends AppCompatActivity {
    EditText nombre, apellido, usuario, contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = findViewById(R.id.usuarioLogin);
        apellido = findViewById(R.id.contrasenaLogin);
        usuario = findViewById(R.id.usuario);
        contrasena = findViewById(R.id.contrasena);

    }
    public void registrarse(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre1 = nombre.getText().toString();
        String apellido1 = apellido.getText().toString();
        String usuario1 = usuario.getText().toString();
        String contrasena1 = contrasena.getText().toString();

        if (usuario1.length() == 0 || contrasena1.length() == 0 || apellido1.length() == 0 || nombre1.length() == 0) {
            Toast.makeText(this, "El usuario o la contraseña o el nombre o el apellido no pueden estar vacíos", Toast.LENGTH_LONG).show();
        } else {
            ContentValues registro = new ContentValues();

            registro.put("nombre", nombre1);
            registro.put("apellido", apellido1);
            registro.put("usuario", usuario1);
            registro.put("contrasena", contrasena1);
            bd.insert("USUARIOS", null, registro);

            bd.close();
            nombre.setText("");
            apellido.setText("");
            usuario.setText("");
            contrasena.setText("");
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        }
    }
}