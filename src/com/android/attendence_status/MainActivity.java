package com.android.attendence_status;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    EditText A,T;
    TextView percentage;
    Button calculate;
    String att,tot,mysum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        A = (EditText) findViewById(R.id.attended1);
        A.setBackgroundColor(Color.CYAN);
        T = (EditText) findViewById(R.id.totalclass);
        T.setBackgroundColor(Color.BLUE);
        calculate = (Button) findViewById(R.id.calculate);
        percentage = (TextView) findViewById(R.id.percentage);
        SharedPreferences pre=getSharedPreferences("data",Context.MODE_PRIVATE);
        A.setText(pre.getString("att", ""));
        T.setText(pre.getString("tot", ""));
        percentage.setText(pre.getString("mysum", ""));
        calculate.setOnClickListener(new OnClick());

    }

    public class OnClick implements OnClickListener
    {

        @Override
        public void onClick(View v)
        {

            double n1 = Integer.parseInt(A.getText().toString());
            A.setBackgroundColor(Color.CYAN);
            double n2 = Integer.parseInt(T.getText().toString());
            T.setBackgroundColor(Color.BLUE);
            double mysum;
            mysum=(n1/n2)*100;
            if(mysum>65)
            {	percentage.setText(String.format("%.2f", mysum));
                percentage.setBackgroundColor(Color.GREEN);}
            else
            { percentage.setText(String.format("%.2f", mysum));
                percentage.setBackgroundColor(Color.RED);}
            InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            SharedPreferences prefre=getSharedPreferences("data",Context.MODE_PRIVATE);
            Editor editor=prefre.edit();
            editor.putString("att", A.getText().toString());
            editor.putString("tot", T.getText().toString());
            editor.putString("mysum", percentage.getText().toString());
            editor.commit();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void increment(View v) {
        int t1 = Integer.parseInt(A.getText().toString());
        A.setText(String.valueOf(t1+1));
        int t2 = Integer.parseInt(T.getText().toString());
        T.setText(String.valueOf(t2+1));
        t1++;
        t2++;
        double mysum;
        mysum=((double)t1/t2)*100;
        if(mysum>65)
        {	percentage.setText(String.format("%.2f", mysum));
            percentage.setBackgroundColor(Color.GREEN);
            Toast.makeText(getApplicationContext(), "keep it up",Toast.LENGTH_SHORT).show();
        }
        else
        { percentage.setText(String.format("%.2f", mysum));
            percentage.setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(), "need some classes",Toast.LENGTH_SHORT).show();
        }

        SharedPreferences prefre=getSharedPreferences("data",Context.MODE_PRIVATE);
        Editor editor=prefre.edit();
        editor.putString("att", A.getText().toString());
        editor.putString("tot", T.getText().toString());
        editor.putString("mysum", percentage.getText().toString());
        editor.commit();
    }

    public void decrement(View v) {
        int p1 = Integer.parseInt(A.getText().toString());
        int p2 = Integer.parseInt(T.getText().toString());
        T.setText(String.valueOf(p2+1));
        p2++;
        double mysum;
        mysum=((double)p1/p2)*100;
        if(mysum>64.90)
        {	percentage.setText(String.format("%.2f", mysum));
            percentage.setBackgroundColor(Color.GREEN);
            Toast.makeText(getApplicationContext(), "keep it up",Toast.LENGTH_SHORT).show();
        }
        else
        { percentage.setText(String.format("%.2f", mysum));
            percentage.setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(), "need some classes",Toast.LENGTH_SHORT).show();
        }
        SharedPreferences prefre=getSharedPreferences("data",Context.MODE_PRIVATE);
        Editor editor=prefre.edit();
        editor.putString("att", A.getText().toString());
        editor.putString("tot", T.getText().toString());
        editor.putString("mysum", percentage.getText().toString());
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
