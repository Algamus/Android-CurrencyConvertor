package gokhangobus.assignment3currencyconverter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by User on 24.11.2016.
 * gokhangobus
 */

public class MainActivity extends AppCompatActivity {


    TextView info;
    FileProcess f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        info=(TextView) findViewById(R.id.informationBox);
        f=new FileProcess(this);
        try {
            if(checkConnection() || f.isSet("TheDataG")){


                stateOne();
                stateTwo();
                stateThree();
            }else{
                Toast.makeText(this,"Inernet is not connected",Toast.LENGTH_SHORT).show();
                info.setText("There is no Internet connection Please Restart the Apk.");
                info.setTextColor(Color.RED);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void stateOne() throws Exception{

            Thread thread = new Thread(new HttpThread(this,f));
            thread.start();
            thread.join();

    }
    private void stateTwo() throws Exception{
        f.StringtoFileData(f.fromFiletoSring("TheDataG"));
    }

    private void stateThree(){
        Intent i = new Intent(getApplicationContext(), Currency_Convertor.class);
       startActivity(i);

    }

    private boolean checkConnection(){
        ConnectivityManager check = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info= check.getAllNetworkInfo();
        for(int i=0;i<info.length;i++){
            if (info[i].getState()== NetworkInfo.State.CONNECTED){
                return true;
            }
        }
        return false;

    }


}
