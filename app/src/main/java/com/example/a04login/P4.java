package com.example.a04login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

public class P4 extends AppCompatActivity {
    EditText dominio,email,contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4);
        dominio = findViewById(R.id.usuarioLogin);
        email = findViewById(R.id.contrasenaLogin);
        contrasena = findViewById(R.id.contrasenaWeb);
    }

    public void registrarWeb(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Bundle bundle = getIntent().getExtras();
        String idUsuario = bundle.getString("idUsuario");
        String dominio1 = dominio.getText().toString();
        String email1 = email.getText().toString();
        String contrasena1 = contrasena.getText().toString();

        if (dominio1.length() == 0 || contrasena1.length() == 0 || email1.length() == 0) {
            Toast.makeText(this, "El dominio o la contraseña o el email no pueden estar vacíos", Toast.LENGTH_LONG).show();
        } else {
            ContentValues registro = new ContentValues();
            registro.put("dominio", dominio1);
            registro.put("email", email1);
            registro.put("contrasena", contrasena1);
            registro.put("fk_usuario", idUsuario);
            bd.insert("WEBS", null, registro);
            bd.close();
            dominio.setText("");
            email.setText("");
            contrasena.setText("");
            Toast.makeText(this, "Se guardo la web exitosamente", Toast.LENGTH_SHORT).show();
        }
    }

}