package com.example.a04login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class P2 extends AppCompatActivity {
    EditText idWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2);
        idWeb =  findViewById(R.id.idWeb1);
    }

    public void eliminarWeb(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idWeb1= idWeb.getText().toString();
        Bundle bundle = getIntent().getExtras();
        String usuario = bundle.getString("usuarioLog");
        String idUsuario1=  bundle.getString("idUsuario");
        int cant =  bd.delete("WEBS","ID_WEB=? AND fk_usuario=?",new String[]{idWeb1,idUsuario1});
        bd.close();
        if (cant == 1) {
            Toast.makeText(this, "Se borró la web", Toast.LENGTH_SHORT).show();
            idWeb.setText("");
            Intent i = new Intent(this, Inicio.class);
            i.putExtra("idLog", idUsuario1);
            i.putExtra("idUsuario", idUsuario1);
            i.putExtra("usuarioLog", usuario);
            startActivity(i);
        }else {
            Toast.makeText(this, "No existe la web", Toast.LENGTH_SHORT).show();
        }
    }

}