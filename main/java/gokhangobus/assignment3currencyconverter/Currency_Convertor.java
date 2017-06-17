package gokhangobus.assignment3currencyconverter;



/**
 * Created by User on 26.11.2016.
 */


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Map;
import gokhangobus.assignment3currencyconverter.widget.OnWheelChangedListener;
import gokhangobus.assignment3currencyconverter.widget.OnWheelScrollListener;
import gokhangobus.assignment3currencyconverter.widget.WheelView;


public class Currency_Convertor extends Activity {
    FileProcess f;
    Map<String,Float> G;
    TextView rightBox;
    EditText leftBox;
    Button convert;
    WheelAdapter wa;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_convertor_layout);
        f=new FileProcess(this);
        G=f.GetData();
        initWheel(R.id.LeftWheel);
        initWheel(R.id.RightWheel);
        rightBox=(TextView)findViewById(R.id.RightBox);
        leftBox=(EditText)findViewById(R.id.LeftBox);
        convert=(Button)findViewById(R.id.Converter);
        convert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                /////
                controlmachanism();

            }
        });
        controlmachanism();

    }

    public void initWheel(int id){
        WheelView wheel= (WheelView) findViewById(id);
        wa=new WheelAdapter(this,G);
        wheel.setVisibleItems(3);
        wheel.setViewAdapter(wa);
        wheel.setCyclic(true);
        wheel.setEnabled(true);
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrollListener);
        wheel.setCurrentItem(1);

    }

    public void controlmachanism(){
        float ato,rto,afrom,rfrom;
        WheelView l=(WheelView)findViewById(R.id.LeftWheel);
        WheelView r=(WheelView)findViewById(R.id.RightWheel);
        rfrom=G.get(wa.getCountry(l.getCurrentItem()));
        rto=G.get(wa.getCountry(r.getCurrentItem()));
        afrom=Float.valueOf(leftBox.getText().toString());
        ato=rto/(afrom*rfrom);
        rightBox.setText(Float.toString(ato));
    }

    private boolean wheelScrolled=false;

    private void updateStatus(){
        //TextView text = (TextView) findViewById(R.id.pwdstatus)
    }
    OnWheelScrollListener scrollListener= new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {

        }
    };
    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {

        }
    };


}
