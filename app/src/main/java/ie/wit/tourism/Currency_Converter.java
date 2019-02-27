package ie.wit.tourism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Integer.parseInt;

public class Currency_Converter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency__converter);
    }

    void convertUsdToEuro()
    {  EditText usd= (EditText) findViewById(R.id.usd);
        usd.setInputType(InputType.TYPE_CLASS_NUMBER);

        EditText  bdt=(EditText) findViewById(R.id.euro);
        bdt.setInputType(InputType.TYPE_CLASS_NUMBER);

        double a=Integer.parseInt(usd.getText().toString());
        double result=a*.88;
        bdt.setText(String.valueOf(result));

    }

    void convertEuroToUsd()
    {  EditText usd= (EditText) findViewById(R.id.usd);
        usd.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditText  bdt=(EditText) findViewById(R.id.euro);
        bdt.setInputType(InputType.TYPE_CLASS_NUMBER);

        double a=Integer.parseInt(bdt.getText().toString());
        double result=a/1.12;
        usd.setText(String.valueOf(result));
    }

    public void click(View view)
    {
        convertUsdToEuro();
        Button b2=(Button) findViewById(R.id.submit2);
        b2.setEnabled(false);
    }

    public void reset(View view)
    {
        Button b1=(Button) findViewById(R.id.submit);
        b1.setEnabled(true);
        Button b2=(Button) findViewById(R.id.submit2);
        b2.setEnabled(true);
        EditText usd= (EditText) findViewById(R.id.usd);
        EditText  bdt=(EditText) findViewById(R.id.euro);
        usd.setText("");
        bdt.setText("");

    }

    public void click2(View view)
    {
        Button b1=(Button) findViewById(R.id.submit);
        b1.setEnabled(false);
        convertEuroToUsd();
    }

}
