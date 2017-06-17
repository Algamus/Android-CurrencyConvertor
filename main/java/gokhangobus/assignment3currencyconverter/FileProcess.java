package gokhangobus.assignment3currencyconverter;
/**
 * Created by User on 27.11.2016.
 */


import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//"TheDataG""ThaDataDate"
//"Country""Rate"
public class FileProcess {
    private static final int READ_BLOCK_SIZE = 100;
    Context context;
    FileProcess(Context c){
        context=c;
    }
    public boolean isSet(String s) throws FileNotFoundException {
        FileInputStream fos = null;
        String filename=s;
        fos = context.openFileInput(filename);
        if(fos != null) {
            return true;
        }else{
            return false;
        }

    }
    public void toFile(String data,String filename) throws  IOException{

            FileOutputStream fOut =context.openFileOutput(filename, context.MODE_WORLD_READABLE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(data);
            osw.flush();
            osw.close();

    }
    public String fromFiletoSring(String filename){


            String s = "";
            try {
                FileInputStream fIn = context.openFileInput(filename);
                InputStreamReader isr = new InputStreamReader(fIn);
                char[] inputBuffer = new char[READ_BLOCK_SIZE];
                int charRead;
                while ((charRead = isr.read(inputBuffer)) > 0) {
                    String readString = String.copyValueOf(inputBuffer, 0, charRead);
                    s += readString;
                    inputBuffer = new char[READ_BLOCK_SIZE];
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return s;
    }
    public void StringtoFileData(String data){
        String s1="";
        String s2="";

        Pattern myPatterna = Pattern.compile("<Description>.+</Description>");
        Matcher myMatchera = myPatterna.matcher(data);

        while(myMatchera.find()){
            s1+="<Country>";
            s1+=myMatchera.group().substring(13,16);
            s1+="</Country>\n";
        }
        try {
            toFile(s1, "Country");
        }catch (Exception e){
            e.printStackTrace();
        }
        /////--------------------------------------
        Pattern myPatternb = Pattern.compile("<Rate>.+</Rate>");
        Matcher myMatcherb = myPatternb.matcher(data);

        while(myMatcherb.find()){
            s2+="<Rate>";
            s2+=myMatcherb.group().substring(6,11);
            s2+="</Rate>\n";
        }
        try {
            toFile(s2, "Rate");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public Map<String,Float> GetData(){
        Map<String,Float> G = new HashMap<String,Float>();

        ArrayList<String> G1 = new ArrayList<String>();
        ArrayList<String> G2 = new ArrayList<String>();

        String G1s=fromFiletoSring("Country");
        String G2s=fromFiletoSring("Rate");

        int count;

        Pattern myPatterna = Pattern.compile("<Country>.+</Country>");
        Matcher myMatchera = myPatterna.matcher(G1s);

        while(myMatchera.find()){

            G1.add(myMatchera.group().substring(9,12));
        }

        Pattern myPatternb = Pattern.compile("<Rate>.+</Rate>");
        Matcher myMatcherb = myPatternb.matcher(G2s);

        while(myMatcherb.find()){

            G2.add(myMatcherb.group().substring(6,11));
        }

        for(count=0;count<G1.size();count++){
            G.put(G1.get(count),Float.valueOf(G2.get(count)));
        }


        return G;
    }
    public String LastDatefromFile() {
        Pattern myPatternb = Pattern.compile("<dateTime.+</dateTime>");
        Matcher myMatcherb = myPatternb.matcher(fromFiletoSring("TheDataDate"));

        myMatcherb.find();

        return myMatcherb.group().substring(38, 57);

    }

}
