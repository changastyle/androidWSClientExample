package com.example.nicolaptop.nicoexpressconsumingws;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by NicoLaptop on 12/09/15.
 */
public class WSClientAndroid
{
    private String URL = "http://192.168.5.41:8080/webServerNicoExpress/ParametrosNicoExpress";
    private String NAMESPACE = "http://webservices/";
    //private String METHOD = "hello";

    public WSClientAndroid( String NAMESPACE, String URL )
    {
        this.URL = URL;
        this.NAMESPACE = NAMESPACE;

        /*String URL = "http://192.168.5.41:8080/webServerNicoExpress/ParametrosNicoExpress";
        String NAMESPACE = "http://webservices/";
        String METHOD = "hello";*/
    }
    public Object ejecutarMetodo(String METHOD , Object parametro)
    {
        Object respuesta = null;
        String resultado = "";
        System.out.println("WSCLIENTANDROID -> ejecutarMetodo :" + METHOD  );
        String SOAP_ACTION_PREFIX = NAMESPACE +  METHOD;

        try
        {
            SoapObject request = new SoapObject(NAMESPACE, METHOD);
            PropertyInfo propInfo=new PropertyInfo();
            propInfo.setName("name");
            propInfo.setType(String.class);
            //propInfo.setValue("Nicolas Grossi");
            propInfo.setValue(parametro);
            request.addProperty(propInfo);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.call(SOAP_ACTION_PREFIX, envelope);

            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();

            respuesta = resultsRequestSOAP;
            //PARA SABER QUE VINO:
            resultado = resultsRequestSOAP.toString();
            System.out.println("RESULTADO :" + resultado);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR: con el WS." );
        }

        return respuesta;
    }







}
