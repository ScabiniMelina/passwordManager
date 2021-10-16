package com.example.a04login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.usuarioLogin);
        contrasena = findViewById(R.id.contrasenaLogin);
    }

    public void logIn(View V) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String usuario1, contrasena1;
        usuario1 = usuario.getText().toString();
        contrasena1 = contrasena.getText().toString();
        if (usuario1.length() == 0 || contrasena1.length() == 0 ) {
            Toast.makeText(this, "El usuario o la contraseña no pueden estar vacíos", Toast.LENGTH_LONG).show();
        } else {
            Cursor registro = bd.rawQuery("SELECT ID_USUARIO FROM USUARIOS  WHERE usuario= '"+ usuario1 +"' AND contrasena= '"+ contrasena1 +"' ", null);
            //clase Cursor y la inicializamos con el valor devuelto por el método llamado rawQuery. La clase Cursor almacena en este caso una fila o cero filas (una en caso que hayamos ingresado un codigo existente en la tabla articulos), llamamos al método moveToFirst() de la clase Cursor y retorna true en caso de existir un articulo con el codigo ingresado, en caso contrario retorna cero
            if (registro.moveToFirst()) {
                Intent i = new Intent(this, Inicio.class);
                i.putExtra("usuarioLog", usuario1);
                i.putExtra("idUsuario", registro.getString(0));
                startActivity(i);
            } else {
                Toast.makeText(this, "El usuario o la contraseña son incorrectas", Toast.LENGTH_SHORT).show();
                bd.close();
            }
        }
    }

    public void registrarse(View v){
        Intent i = new Intent(this, registro.class);
        startActivity(i);
    }
}