package com.example.nicolaptop.nicoexpressconsumingws;


import com.example.nicolaptop.nicoexpressconsumingws.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.*;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static TextView lblResultado;
    public static Button btnWS;
    public static EditText inputNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNombre = (EditText) findViewById(R.id.inputNombre);
        lblResultado = (TextView) findViewById(R.id.lblResultado);
        btnWS = (Button) findViewById(R.id.btnWS);
        btnWS.setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static void pedirParametros(String nombre)
    {
        System.out.println("Comienzo a pedir Parametros:");

        NetworkingWS nws = new NetworkingWS(nombre);
        nws.execute();


        System.out.println("Fin pedir Parametros.");
    }

    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;

        if(botonPresionado == btnWS)
        {
            pedirParametros(String.valueOf(inputNombre.getText()));
            System.out.println("Pedi Parametros");
        }
    }
}
