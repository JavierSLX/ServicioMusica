package com.morpheus.servicionotificacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, Servicio.class);
        Button btIniciar = (Button)findViewById(R.id.btIniciar);
        Button btDetener = (Button)findViewById(R.id.btDetener);

        //Inicia y termina el servicio
        btIniciar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startService(intent);
            }
        });

        btDetener.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                stopService(intent);
            }
        });
    }
}
