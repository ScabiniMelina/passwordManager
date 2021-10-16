package com.example.a04login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {
    private TextView pantalla1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        pantalla1 = findViewById(R.id.pantalla);
        Bundle bundle = getIntent().getExtras();
        String usuario = bundle.getString("usuarioLog");
        if(usuario == null || usuario.length() == 0){
            pantalla1.setText("Hola!");
        }else{
            pantalla1.setText("Hola "+ usuario + "!");
        }

    }

    public void cambiarPantalla1(View v) {
        Bundle bundle = getIntent().getExtras();
        String idUsuario = bundle.getString("idUsuario");
        String usuario = bundle.getString("usuarioLog");
        Intent i = new Intent(this, P4.class);
        i.putExtra("idUsuario", idUsuario);
        i.putExtra("usuarioLog", usuario);
        startActivity(i);
    }

    public void cambiarPantalla2(View v) {
        Bundle bundle = getIntent().getExtras();
        String idUsuario = bundle.getString("idUsuario");
        String usuario = bundle.getString("usuarioLog");
        Intent i = new Intent(this, P3.class);
        i.putExtra("idUsuario", idUsuario );
        i.putExtra("usuarioLog", usuario);
        startActivity(i);
    }



}