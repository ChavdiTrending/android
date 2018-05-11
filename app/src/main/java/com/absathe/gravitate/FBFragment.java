package com.absathe.gravitate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.absathe.gravitate.adapters.FBItemAdapter;
import com.absathe.gravitate.items.FBItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FBFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FBFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<FBItem> fbItemList = null;
    private FBItem item = new FBItem();
    private RecyclerView recyclerView;
    private FBItemAdapter itemAdapter;

    private static OnFragmentInteractionListener mListener;

    public FBFragment() {
        /*
        String jsonString = null;
        JSONObject reader = null;
        JSONArray jsonArray = null;
        try {
            reader = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            jsonArray = reader.getJSONArray("links");
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Unable to read the JSON format. Please report this to developer", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return;
        }
        fbItemList = new ArrayList<>();
        for(int i = 0; i < 15; i++) {
            try {
                item = new FBItem().setWebViewURL(jsonArray.getString(i));
            } catch(final JSONException e) {
                e.printStackTrace();
                return;
            }
            fbItemList.add(item);
        }
        */
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FBFragment newInstance(String param1, String param2) {
        FBFragment fragment = new FBFragment();
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
        View view = inflater.inflate(R.layout.fragment_fb, container, false);
        mListener.getDataFromServer();
        getDataFromSharedPReferences();
        if(fbItemList != null) {
            view.findViewById(R.id.fb_loading).setVisibility(View.GONE);
            recyclerView = view.findViewById(R.id.fbfragment_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            itemAdapter = new FBItemAdapter();
            itemAdapter.setItemList(fbItemList);
            recyclerView.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
        }
        return view;
    }

    private void getDataFromSharedPReferences() {
        String jsonString ;
        JSONObject reader ;
        JSONArray jsonArray ;
        SharedPreferences pref = getActivity().getSharedPreferences("JSONData", Context.MODE_PRIVATE);
        jsonString = pref.getString("FacebookJSON", null);
        try {
            jsonString = jsonString.replace("\"{", "{");
            jsonString = jsonString.replace("}\"", "}");
            jsonString = jsonString.replace("\\\"", "\"");
            System.out.println(jsonString);
            reader = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            jsonArray = reader.getJSONArray("links");
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Unable to read the JSON format. Please report this to developer", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return;
        }
        fbItemList = new ArrayList<>();
        for(int i = 0; i < 15; i++) {
            try {
                item = new FBItem().setWebViewURL(jsonArray.getString(i));
            } catch(final JSONException e) {
                e.printStackTrace();
                return;
            }
            fbItemList.add(item);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static void openFacebookPost(String webViewURL) {
        System.out.println("I'm in the click listener");
        mListener.onFacebookFragmentInteraction(webViewURL);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFacebookFragmentInteraction(String string);
        void getDataFromServer();
    }
}
