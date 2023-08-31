package com.thushan.ecommerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thushan.ecommerce.R;

import es.dmoral.toasty.Toasty;


public class PaymentActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView subTotal,discount,shipping,total;

    FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        //Toolbar
        toolbar=findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        double amount=0.0;
        amount=getIntent().getDoubleExtra("totalBill",0.0);

        subTotal=findViewById(R.id.sub);
        discount=findViewById(R.id.dis);
        shipping=findViewById(R.id.ship);
        total=findViewById(R.id.tot);

        subTotal.setText("$ "+amount);

        double finalTotalAmount=amount+15-(amount*0.1);
        total.setText("$ "+finalTotalAmount);





    }
    public void checkOutButton(View view){
        Toasty.success(this,"Payment are successfully ",Toast.LENGTH_SHORT).show();

        firebaseFirestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
             // .collection("User")
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });



    }

}