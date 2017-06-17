package gokhangobus.assignment3currencyconverter;
/**
 * Created by User on 27.11.2016.
 * gokhangobus
 */
import android.content.Context;
import java.lang.String;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpThread extends Thread{

    String linka;
    String linkb;
    URL url;
    String hostName;
    String data;
    Context context;
    FileProcess f;
    HttpURLConnection urlConnection;
    InputStream in;

    HttpThread(Context c,FileProcess fp){
        context=c;
        f=fp;
        //link="http://tempuri.org";
        linkb="http://currencyconverter.kowabunga.net/converter.asmx/GetCurrencyRates?RateDate=";//POST olunca ?asd=asd olucak
        linka="http://currencyconverter.kowabunga.net/converter.asmx/GetLastUpdateDate";

    }

    public void run (){

        try {

            url=new URL(linka);
            hostName=url.getHost();

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");//GETorPOST
            //urlConnection.setRequestProperty("Accept-Encoding","gzip");

            in = new BufferedInputStream(urlConnection.getInputStream());
            data = readStream(in);

            f.toFile(data,"TheDataDate");
            linkb+=f.LastDatefromFile();
            /////
            url=new URL(linkb);
            hostName=url.getHost();

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");//GETorPOST
            //urlConnection.setRequestProperty("Accept-Encoding","gzip");

            in = new BufferedInputStream(urlConnection.getInputStream());
            data = readStream(in);

            f.toFile(data,"TheDataG");


        }catch(Exception e){
            e.printStackTrace();
        }

    }


    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }


}
