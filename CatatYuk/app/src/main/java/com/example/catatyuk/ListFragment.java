package com.example.catatyuk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<dataModel> dataholder;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputFragment newInstance(String param1, String param2) {
        InputFragment fragment = new InputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView=view.findViewById(R.id.list_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder=new ArrayList<>();

        dataModel ob1=new dataModel("Angular","Web Application");
        dataholder.add(ob1);

        dataModel ob2=new dataModel("C Programming","Embed Programming");
        dataholder.add(ob2);

        dataModel ob3=new dataModel("C++ Programming","Embed Programming");
        dataholder.add(ob3);

        dataModel ob4=new dataModel(".NET Programming","Desktop and Web Programming");
        dataholder.add(ob4);

        dataModel ob5=new dataModel("Java Programming","Desktop and Web Programming");
        dataholder.add(ob5);

        dataModel ob6=new dataModel("Magento","Web Application Framework");
        dataholder.add(ob6);

        dataModel ob7=new dataModel("NodeJS","Web Application Framework");
        dataholder.add(ob7);

        dataModel ob8=new dataModel("Python","Desktop and Web Programming");
        dataholder.add(ob8);

        dataModel ob9=new dataModel("Shopify","E-Commerce Framework");
        dataholder.add(ob9);

        dataModel ob10=new dataModel("Wordpress","WebApplication Framewrok");
        dataholder.add(ob10);

        recyclerView.setAdapter(new myadapter(dataholder));

        return view;
    }
}