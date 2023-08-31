package com.thushan.ecommerce.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.thushan.ecommerce.R;
import com.thushan.ecommerce.adapters.CategoryAdapter;
import com.thushan.ecommerce.adapters.SellerAddItemAdapter;
import com.thushan.ecommerce.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;


public class SellerFragment extends Fragment {

    RecyclerView catRecyclerview;
    SellerAddItemAdapter sellerAddItemAdapter;
    List<CategoryModel> categoryModelList;
    FirebaseFirestore db;
    public SellerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db= FirebaseFirestore.getInstance();
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_seller, container, false);
        catRecyclerview=root.findViewById(R.id.rec_category);

        ImageSlider imageSlider=root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels=new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.delivery,"Fast Delivery All Items", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.sellerank,"Get Best Seller Rank", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.superchange,"Superchange", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        //Category
        catRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),4));
        categoryModelList=new ArrayList<>();
        sellerAddItemAdapter=new SellerAddItemAdapter(getContext(),categoryModelList);
        catRecyclerview.setAdapter(sellerAddItemAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {


                                CategoryModel categoryModel =document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                sellerAddItemAdapter.notifyDataSetChanged();

                            }
                        } else {

                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        return root;
    }
}