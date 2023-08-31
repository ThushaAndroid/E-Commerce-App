package com.thushan.ecommerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.thushan.ecommerce.R;

import java.util.HashMap;

public class SellerItemActivity extends AppCompatActivity {

    TextView itemname,itemdescription,itemprice;
    private MaterialCardView materialCardView;
    private ImageView imageView;
    private Uri imageUri;
    private Bitmap bitmap;
    FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    Toolbar toolbar;
    Button addItem;

    Spinner spinner;
    String[] qty={"Quantity","1","2","3","4","5"};
    String value=null;
    private String photoUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_item);

        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        toolbar=findViewById(R.id.seller_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        materialCardView=findViewById(R.id.selectPhoto);
        imageView=findViewById(R.id.uploadPhoto);
        itemname=findViewById(R.id.item_name);
        itemdescription=findViewById(R.id.item_description);
        itemprice=findViewById(R.id.item_price);
        spinner=findViewById(R.id.item_qty);
        addItem=findViewById(R.id.add_item);
        String itemtype=getIntent().getStringExtra("type");

        ArrayAdapter<String>adapter=new ArrayAdapter<String>(SellerItemActivity.this, android.R.layout.simple_spinner_item,qty);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 value=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final HashMap<String,Object> cartMap=new HashMap<>();

                cartMap.put("name",itemname.getText().toString());
                cartMap.put("price",Integer.parseInt(itemprice.getText().toString()));
                //cartMap.put("rating",itemrating.getText().toString());
                cartMap.put("description",itemdescription.getText().toString());
                cartMap.put("quantity",Integer.parseInt(value));
                cartMap.put("type",itemtype);
                cartMap.put("img_url",photoUrl);

                firebaseFirestore.collection("ShowAll")
                        .add(cartMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(SellerItemActivity.this, "Added To A Item", Toast.LENGTH_SHORT).show();
                                finish();
                            }



                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String error=e.getMessage();

                                Toast.makeText(SellerItemActivity.this,"Error :"+error,Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        });




    }


    }


