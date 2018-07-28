package com.example.poorva.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int qty=1;
    boolean check1,check2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        check1=whippedCream.isChecked();
        CheckBox chocolate=(CheckBox) findViewById(R.id.chocolate_checkbox);
        check2=chocolate.isChecked();
        createOrderSummary(calculatePrice());
    }
    public void increment(View view){
        if(qty<100){
        qty++;
        display(qty);}
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Thats too much caffeine!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    public void decrement(View view){
        if(qty>1)
        {qty--;
        display(qty);}
        else
        {
            //display toast
            Context context = getApplicationContext();
            CharSequence text = "Thats too less!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void createOrderSummary(int total){
        EditText e=(EditText)findViewById(R.id.name_edit_text);
        String nm=e.getText().toString();

       /* EditText i=(EditText)findViewById(R.id.email_edit_text);
        String email=i.getText().toString();
*/
        String sum="Name: "+nm+"\nAnd Whipped Cream? "+check1 + "\nAnd Chocolate? "+check2 + "\nQuantity: "+qty+"\nTotal: $"+total+"\nThank you !";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        String subject="Just Java order for "+nm;
//        intent.putExtra(intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, sum);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private int calculatePrice(){
        if(check1&&check2)
        return qty*(5+1+2);
        else if(check1)
            return qty*(5+1);
        else if(check2)
            return qty*(5+2);
        else
            return qty*5;
    }
}