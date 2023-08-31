package com.thushan.ecommerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.thushan.ecommerce.R;
import com.thushan.ecommerce.adapters.MyCartAdapter;
import com.thushan.ecommerce.models.MyCartModel;
import com.thushan.ecommerce.models.ShowAllModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    int overAllTotalAmount;
    TextView overAllAmount;
    Button payButton;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<MyCartModel> myCartModelList;
    MyCartAdapter cartAdapter;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    //Context context;
    double totalBill=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        payButton=findViewById(R.id.pay);

        toolbar=findViewById(R.id.my_cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //get data from my cart adapter
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(messageReceiver,new IntentFilter("MyTotalAmount"));

        overAllAmount=findViewById(R.id.totalAmount);

        recyclerView=findViewById(R.id.cart_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myCartModelList=new ArrayList<>();
        cartAdapter=new MyCartAdapter(this,myCartModelList);
        recyclerView.setAdapter(cartAdapter);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                String documentId=doc.getId();

                                MyCartModel myCartModel = doc.toObject(MyCartModel.class);

                                myCartModel.setDocumentId(documentId);
                                
                                myCartModelList.add(myCartModel);
                                cartAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                });
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PaymentActivity.class);
                intent.putExtra("totalBill",totalBill);
                startActivity(intent);
            }
        });
    }
    public BroadcastReceiver messageReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            totalBill=intent.getDoubleExtra("totalAmount",0.0);
            overAllAmount.setText("Total Price : $ "+totalBill);

        }
    };

}