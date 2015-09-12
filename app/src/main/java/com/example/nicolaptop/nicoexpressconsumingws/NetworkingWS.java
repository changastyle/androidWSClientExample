package com.example.nicolaptop.nicoexpressconsumingws;

import android.os.AsyncTask;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;



public class NetworkingWS extends AsyncTask<String, Void, String>
{
    String nombre = "";
    public NetworkingWS(String nombre)
    {
        this.nombre = nombre;
    }
    private String resultado = null;
    @Override
    protected String doInBackground(String... params)
    {
        System.out.println("ASYNC -> doInBackground: " );

        String URL = "http://192.168.5.41:8080/webServerNicoExpress/ParametrosNicoExpress";
        String NAMESPACE = "http://webservices/";
        String METHOD = "hello";
        String resp;
        String SOAP_ACTION_PREFIX = NAMESPACE +  METHOD;

        WSClientAndroid wsClientAndroid = new WSClientAndroid(NAMESPACE,URL);
        this.resultado =  wsClientAndroid.ejecutarMetodo(METHOD,nombre).toString();
        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {

        System.out.println("RESULTADO ASYNC = " + result.toString());

        MainActivity.lblResultado.setText(resultado);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values)
    {

    }

}
