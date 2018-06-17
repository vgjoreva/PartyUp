package com.vergjor.android.partyup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.UploadRequest;
import com.cloudinary.utils.ObjectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class AddEventActivity extends AppCompatActivity {

    static final int RESULT_LOAD_IMG = 1;
    static EditText e_name;
    static EditText nr_R;
    static Button subm;
    static Button imagepick;
    static String url;
    //final UserDatabase db = Room.databaseBuilder(getApplicationContext(),
    //        UserDatabase.class, "user-database").allowMainThreadQueries().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        e_name = findViewById(R.id.txteventname);
        nr_R=findViewById(R.id.txtReservInt);
        subm=findViewById(R.id.btnSubmitAdd);
        imagepick=findViewById(R.id.btnImagePick);

        subm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success= jsonResponse.getBoolean("success");
                            if (success){
                                Intent intent = new Intent(AddEventActivity.this, OwnerProfileActivity.class);
                                AddEventActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddEventActivity.this);
                                builder.setMessage("Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                AddEventRequest registerRequest = new AddEventRequest(e_name.getText().toString(),"111111111111" ,url ,nr_R.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddEventActivity.this);
                queue.add(registerRequest);



            }
        });


        imagepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, RESULT_LOAD_IMG);
                }

            }


            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                AddEventActivity.super.onActivityResult(requestCode,resultCode,data);


                if (resultCode == RESULT_OK) {
                    Uri fullPhotoUri = data.getData();

                    url = MediaManager.get().upload(fullPhotoUri).dispatch();


                }
            }
        });
    }
}

