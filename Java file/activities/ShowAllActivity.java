package com.thushan.ecommerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.thushan.ecommerce.R;
import com.thushan.ecommerce.adapters.ShowAllAdapter;
import com.thushan.ecommerce.models.CategoryModel;
import com.thushan.ecommerce.models.ShowAllModel;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelsLList;

    Toolbar toolbar;

    FirebaseFirestore firestore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        toolbar=findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firestore=FirebaseFirestore.getInstance();

        String type=getIntent().getStringExtra("type");

        recyclerView=findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelsLList=new ArrayList<>();
        showAllAdapter=new ShowAllAdapter(this,showAllModelsLList);
        recyclerView.setAdapter(showAllAdapter);

        if(type==null || type.isEmpty()) {

            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });
        }

        if(type!=null && type.equalsIgnoreCase("men")){

            firestore.collection("ShowAll").whereEqualTo("type","men")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("shoes")){

            firestore.collection("ShowAll").whereEqualTo("type","shoes")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("cosmetic")){

            firestore.collection("ShowAll").whereEqualTo("type","cosmetic")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("phone")){

            firestore.collection("ShowAll").whereEqualTo("type","phone")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("sports")){

            firestore.collection("ShowAll").whereEqualTo("type","sports")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("computer")){

            firestore.collection("ShowAll").whereEqualTo("type","computer")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }


        if(type!=null && type.equalsIgnoreCase("electric")){

            firestore.collection("ShowAll").whereEqualTo("type","electric")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("food")){

            firestore.collection("ShowAll").whereEqualTo("type","food")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("toy")){

            firestore.collection("ShowAll").whereEqualTo("type","toy")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("vehicle")){

            firestore.collection("ShowAll").whereEqualTo("type","vehicle")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("liqueur")){

            firestore.collection("ShowAll").whereEqualTo("type","liqueur")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("book")){

            firestore.collection("ShowAll").whereEqualTo("type","book")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("women")){

            firestore.collection("ShowAll").whereEqualTo("type","women")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("baby")){

            firestore.collection("ShowAll").whereEqualTo("type","baby")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("camera")){

            firestore.collection("ShowAll").whereEqualTo("type","camera")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }

        if(type!=null && type.equalsIgnoreCase("watch")){

            firestore.collection("ShowAll").whereEqualTo("type","watch")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {


                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelsLList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

        }


    }
}