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

        try
        {
            SoapObject request = new SoapObject(NAMESPACE, METHOD);
            PropertyInfo propInfo=new PropertyInfo();
            propInfo.setName("name");
            propInfo.setType(String.class);
            //propInfo.setValue("Nicolas Grossi");
            propInfo.setValue(this.nombre);
            request.addProperty(propInfo);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.call(SOAP_ACTION_PREFIX, envelope);

            SoapPrimitive  resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();

            resultado = resultsRequestSOAP.toString();
            System.out.println("RESULTADO :" + resultado);



        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR: con el WS." );
        }
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
