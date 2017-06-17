package gokhangobus.assignment3currencyconverter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Map;
import gokhangobus.assignment3currencyconverter.widget.adapters.AbstractWheelTextAdapter;


/**
 * Created by User on 25.11.2016.
 * gokhangobus
 */
public class WheelAdapter extends AbstractWheelTextAdapter{


    private String countries[];
    private int flags[];
    public WheelAdapter(Context context,Map<String,Float> k){
        super(context,R.layout.currency_item,NO_RESOURCE);
        SetwheelItems(k);
        setItemTextResource(R.id.country_name);
        setItemTextResource(R.id.country_name);
    }
    private void SetwheelItems(Map<String,Float> k){
        countries =new String[k.size()];
        flags =new int[k.size()];
        int count=0;
        for(Map.Entry<String,Float> pairs:k.entrySet()){
            countries[count]=pairs.getKey();
            count++;
        }
        int i;
        for(i=0;i<count;i++){
            if(countries[i].compareTo("AUD")==0){
                flags[i]=R.drawable.aud;
            }else if(countries[i].compareTo("BGN")==0){
                flags[i]=R.drawable.bgn;
            }else if(countries[i].compareTo("BRL")==0){
                flags[i]=R.drawable.brl;
            }else if(countries[i].compareTo("CAD")==0){
                flags[i]=R.drawable.cad;
            }else if(countries[i].compareTo("CHF")==0){
                flags[i]=R.drawable.chf;
            }else if(countries[i].compareTo("CNY")==0){
                flags[i]=R.drawable.cny;
            }else if(countries[i].compareTo("CYP")==0){
                flags[i]=R.drawable.cyp;
            }else if(countries[i].compareTo("CZK")==0){
                flags[i]=R.drawable.czk;
            }else if(countries[i].compareTo("DKK")==0){
                flags[i]=R.drawable.dkk;
            }else if(countries[i].compareTo("EEK")==0){
                flags[i]=R.drawable.eek;
            }else if(countries[i].compareTo("EUR")==0){
                flags[i]=R.drawable.eur;
            }else if(countries[i].compareTo("GBP")==0){
                flags[i]=R.drawable.gbp;
            }else if(countries[i].compareTo("HKD")==0){
                flags[i]=R.drawable.hkd;
            }else if(countries[i].compareTo("HRK")==0){
                flags[i]=R.drawable.hrk;
            }else if(countries[i].compareTo("HUF")==0){
                flags[i]=R.drawable.huf;
            }else if(countries[i].compareTo("IDR")==0){
                flags[i]=R.drawable.idr;
            }else if(countries[i].compareTo("ILS")==0){
                flags[i]=R.drawable.ils;
            }else if(countries[i].compareTo("INR")==0){
                flags[i]=R.drawable.inr;
            }else if(countries[i].compareTo("ISK")==0){
                flags[i]=R.drawable.isk;
            }else if(countries[i].compareTo("JPY")==0){
                flags[i]=R.drawable.jpy;
            }else if(countries[i].compareTo("KRW")==0){
                flags[i]=R.drawable.krw;
            }else if(countries[i].compareTo("LTL")==0){
                flags[i]=R.drawable.ltl;
            }else if(countries[i].compareTo("LVL")==0){
                flags[i]=R.drawable.lvl;
            }else if(countries[i].compareTo("MTL")==0){
                flags[i]=R.drawable.mtl;
            }else if(countries[i].compareTo("MXN")==0){
                flags[i]=R.drawable.mxn;
            }else if(countries[i].compareTo("MYR")==0){
                flags[i]=R.drawable.myr;
            }else if(countries[i].compareTo("NOK")==0){
                flags[i]=R.drawable.nok;
            }else if(countries[i].compareTo("NZD")==0){
                flags[i]=R.drawable.nzd;
            }else if(countries[i].compareTo("PHP")==0){
                flags[i]=R.drawable.php;
            }else if(countries[i].compareTo("PLN")==0){
                flags[i]=R.drawable.pln;
            }else if(countries[i].compareTo("RON")==0){
                flags[i]=R.drawable.ron;
            }else if(countries[i].compareTo("RUB")==0){
                flags[i]=R.drawable.rub;
            }else if(countries[i].compareTo("SEK")==0){
                flags[i]=R.drawable.sek;
            }else if(countries[i].compareTo("SGD")==0){
                flags[i]=R.drawable.sgd;
            }else if(countries[i].compareTo("SIT")==0){
                flags[i]=R.drawable.sit;
            }else if(countries[i].compareTo("SKK")==0){
                flags[i]=R.drawable.skk;
            }else if(countries[i].compareTo("THB")==0){
                flags[i]=R.drawable.thb;
            }else if(countries[i].compareTo("TRY")==0){
                flags[i]=R.drawable.tryx;
            }else if(countries[i].compareTo("USD")==0){
                flags[i]=R.drawable.usd;
            }else if(countries[i].compareTo("ZAR")==0){
                flags[i]=R.drawable.zar;
            }else{
                flags[i]=R.drawable.noflag;
            }
        }






    }
    public String getCountry(int i){
        return countries[i];
    }
    @Override
    public View getItem(int index, View cachedView, ViewGroup parent){

        View view=super.getItem(index,cachedView,parent);
        ImageView img=(ImageView) view.findViewById(R.id.flag);
        img.setImageResource(flags[index]);
        return view;

    }

    @Override
    protected CharSequence getItemText(int index) {
        return countries[index];
    }

    @Override
    public int getItemsCount() {
        return countries.length;
    }
}
