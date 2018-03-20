
package com.example.anmol.efarming;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
    EditText  name;
    EditText number;
    EditText adhar;
    EditText gstno;
    EditText state;
    EditText city;
    EditText street;
    RadioGroup type;
    RadioButton farmer;
    RadioButton consumer;
    Button submit;
    TextView register;
    TextView textname;
    TextView textnumber;
    TextView textadhar;
    TextView textstate;
    TextView textcity;
    TextView textstreet;
    TextView textlocation;
    TextView textpassword;
    TextView textregister;
    TextView textgstno;

    public String mname;
    public String mnumber;
    public String madhar;
    public String mgstno;
    public String mstate;
    public String mcity;
    public String mstreet;
    public String mpassword;
    public String mtype;

    TextInputLayout contactName;
    TextInputLayout Text;
    TextInputLayout adharinput;
    TextInputLayout gstinput;
    TextInputLayout stateinput;
    TextInputLayout cityinput;
    TextInputLayout passinput;
    TextInputLayout streetinput;

    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        register=(TextView)findViewById(R.id.textView);
        textname=(TextView)findViewById(R.id.Nametextview);
        textnumber=(TextView)findViewById(R.id.MobileNumbertextview);
        textadhar=(TextView)findViewById(R.id.AadharCardNumbertextview);
        textgstno=(TextView)findViewById(R.id.GSTNumbertextview);
        textstate=(TextView)findViewById(R.id.Statetextview);
        textcity=(TextView)findViewById(R.id.Citytextview);
        textstreet=(TextView)findViewById(R.id.streettextview);
        textpassword=(TextView)findViewById(R.id.Passwordtextview);

        //addition
        contactName = (TextInputLayout) findViewById(R.id.ContactInputName);
        Text = (TextInputLayout) findViewById(R.id.TextInputName);
        adharinput = (TextInputLayout) findViewById(R.id.adharinputlayout);
        gstinput = (TextInputLayout) findViewById(R.id.gstinputlayout);
        stateinput = (TextInputLayout) findViewById(R.id.stateinputname);
        cityinput = (TextInputLayout) findViewById(R.id.cityinputlayout);
        streetinput = (TextInputLayout) findViewById(R.id.streetinput);
        passinput = (TextInputLayout) findViewById(R.id.passwordinput);


        farmer=(RadioButton)findViewById(R.id.Buttonfarmer);
        consumer=(RadioButton)findViewById(R.id.buttonconsumer);
        name=(EditText)findViewById(R.id.NameditText);
        number=(EditText)findViewById(R.id.ContacteditText);
        adhar=(EditText)findViewById(R.id.AAdharCardNumbereditText);
        gstno=(EditText)findViewById(R.id.GstNumberdeditText);
        state=(EditText)findViewById(R.id.StateeditText);
        city=(EditText)findViewById(R.id.CityeditText);
        street=(EditText)findViewById(R.id.StreetNameeditText);
        password=(TextInputEditText)findViewById(R.id.PasswordeditText);        //change2
        submit=(Button)findViewById(R.id.submit);
        type=(RadioGroup)findViewById(R.id.typeRadioGroup);

        if (language.languageselection != "English") {

            textname.setText("नाम");
            textnumber.setText("मोबाइल नंबर");
            textadhar.setText("आधार कार्ड नंबर");
            textgstno.setText("GST नंबर");
            textcity.setText("शहर");
            textstate.setText("राज्य");
            textstreet.setText("गली");
            textpassword.setText("पासवर्ड");
            register.setText("पंजीकरण");
            contactName.setHint("अपना मोबाइल नंबर दर्ज करे");
            adharinput.setHint("अपना आधार कार्ड नंबर दर्ज करे");
            gstinput.setHint("अपना GST नंबर दर्ज करे");
            cityinput.setHint("अपना शहर दर्ज करे");
            stateinput.setHint("अपना राज्य दर्ज करे");
            streetinput.setHint("अपना गली दर्ज करे");
            passinput.setHint("अपना पासवर्ड दर्ज करे");
            farmer.setText("किसान");
            consumer.setText("उपभोक्ता");
            submit.setText("जमा करे");
        }

        }

        public void ssubmit(View view) {

        mname = name.getText().toString();
        mnumber = number.getText().toString();
        madhar = adhar.getText().toString();
        mgstno = gstno.getText().toString();
        mstate = state.getText().toString();
        mcity = city.getText().toString();
        mstreet = street.getText().toString();
        mpassword = password.getText().toString();

        if (farmer.isChecked())
        {
        if (!(mname.isEmpty()||mnumber.isEmpty()||madhar.isEmpty()||mgstno.isEmpty()||mcity.isEmpty()||mstreet.isEmpty()||mpassword.isEmpty()))
            {
                myref = myref.child("root").child("farmers").child(mnumber);
                myref.child("name").setValue(mname);
                myref.child("phone number").setValue(mnumber);
                myref.child("aadhar number").setValue(madhar);
                myref.child("gst number").setValue(mgstno);
                myref.child("state").setValue(mstate);
                myref.child("city").setValue(mcity);
                myref.child("street").setValue(mstreet);
                myref.child("password").setValue(mpassword);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
            else
        {
            Toast.makeText(this, "Fields Can't be empty", Toast.LENGTH_SHORT).show();
        }
        }
        else if (consumer.isChecked())
            {
                if (!(mname.isEmpty()||mnumber.isEmpty()||madhar.isEmpty()||mgstno.isEmpty()||mcity.isEmpty()||mstreet.isEmpty()||mpassword.isEmpty()))
                {
                    myref = myref.child("root").child("consumers").child(mnumber);
                    myref.child("name").setValue(mname);
                    myref.child("phone number").setValue(mnumber);
                    myref.child("aadhar number").setValue(madhar);
                    myref.child("gst number").setValue(mgstno);
                    myref.child("state").setValue(mstate);
                    myref.child("city").setValue(mcity);
                    myref.child("street").setValue(mstreet);
                    myref.child("password").setValue(mpassword);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Fields Can't be Empty", Toast.LENGTH_SHORT).show();
                }
            }

        else
                {
                    Toast.makeText(signup.this, "Please Select your user Type", Toast.LENGTH_SHORT).show();
                }
            }

}
