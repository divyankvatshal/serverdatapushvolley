package barcode.com.serverformapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static barcode.com.serverformapp.R.id.sname;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
      EditText name,email,mobile,course;
    Button btnSubmit;
    public static final String KEY_USERNAME = "username";
    public static final String KEY_COURSE = "course";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILE = "mobile";


    private final static String url="http://192.168.0.100:80/a.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name =(EditText)findViewById(sname);
        email=(EditText)findViewById(R.id.email);
        course=(EditText)findViewById(R.id.course);
        mobile=(EditText)findViewById(R.id.mobile);
        btnSubmit = (Button)findViewById(R.id.submit);
        btnSubmit.setOnClickListener(this);

    }
 public void registerUser(){
     final String sname = name.getText().toString().trim();
     final String semail = email.getText().toString().trim();
     final String smobile =mobile.getText().toString().trim();
     final String scourse =course.getText().toString().trim();
     StringRequest strreq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {

                 Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();

         }
     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
         }
     }){
         @Override
         protected Map<String,String> getParams(){
             Map<String,String> params = new HashMap<String, String>();
             params.put(KEY_USERNAME,sname);
             params.put(KEY_COURSE,scourse);
             params.put(KEY_EMAIL, semail);
             params.put(KEY_MOBILE, smobile);
             return params;
         }


     };

     RequestQueue requestQueue = Volley.newRequestQueue(this);
     requestQueue.add(strreq);
 }

    @Override
    public void onClick(View view) {
     registerUser();
    }
}
