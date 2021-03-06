package edu.uw.viewpager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private OnSearchListener callback;


    interface OnSearchListener {
        void onSearchSubmitted (String searchTerm);
    }

    public static SearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        try{
            callback = (OnSearchListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnSearchListener");
        }
    }

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        Button searchButton = rootView.findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText text = (EditText)rootView.findViewById(R.id.txt_search);
                String searchTerm = text.getText().toString();
                callback.onSearchSubmitted(searchTerm);
            }
        });
        return rootView;
    }

}
