package com.example.a04login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class P5 extends AppCompatActivity {
    EditText idWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);
        idWeb =  findViewById(R.id.idWeb1);
    }


    public void ponerPantallaEditar(View v) {
        String idWeb1= idWeb.getText().toString();
        Bundle bundle = getIntent().getExtras();
        String idUsuario1=  bundle.getString("idUsuario");
        String usuario = bundle.getString("usuarioLog");
        if(idWeb1.length() == 0){
            Toast.makeText(this, "El ID de la web no puede estar vacio", Toast.LENGTH_SHORT).show();
        }else{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            Cursor registro = bd.rawQuery("SELECT ID_WEB FROM WEBS  WHERE ID_WEB= '"+ idWeb1 +"' AND fk_usuario= '"+ idUsuario1 +"' ", null);
            if (registro.moveToFirst()) {
                Toast.makeText(this, "Se encontró el ID de la web", Toast.LENGTH_SHORT).show();
                bd.close();
                Intent i = new Intent(this, P1.class);
                i.putExtra("idWeb", idWeb1);
                i.putExtra("idUsuario", idUsuario1);
                i.putExtra("usuarioLog", usuario);
                startActivity(i);

            } else {
                Toast.makeText(this, "El ID de la web es invalido", Toast.LENGTH_SHORT).show();
                bd.close();
            }

        }

    }

}