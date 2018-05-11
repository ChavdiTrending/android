package com.absathe.gravitate;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.absathe.gravitate.adapters.InstaItemAdapter;
import com.absathe.gravitate.items.InstaItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InstaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InstaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<InstaItem> instaItemList = null;
    private RecyclerView recyclerView = null;
    private InstaItem item = new InstaItem();
    private InstaItemAdapter adapter = null;
    private static OnFragmentInteractionListener mListener;

    public InstaFragment() {
        /*
        String jsonString = null;
        JSONObject reader = null;
        JSONObject links = null;
        JSONObject url = null;
        try {
            reader = new JSONObject(jsonString);
        } catch(JSONException e) {
            e.printStackTrace();
            return;
        } catch(Exception e) {
            System.out.println("InstaFragment" + e.getMessage());
            e.printStackTrace();
            return;
        }
        try {
            links = reader.getJSONObject("links");
        } catch(JSONException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 137; i++) {
            try {
                url = links.getJSONObject(Integer.toString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(i == 0)
                instaItemList = new ArrayList<InstaItem>();

            item = new InstaItem();
            try {
                item.setImageURL(url.getString("display_src"));
                System.out.println(url.getString("display_src"));
            } catch(JSONException e) {
                item.setImageURL("https://127.0.0.1");
                e.printStackTrace();
            }
            instaItemList.add(item);
        }
        */
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InstaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InstaFragment newInstance(String param1, String param2) {
        InstaFragment fragment = new InstaFragment();
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
        View view =  inflater.inflate(R.layout.fragment_insta, container, false);
        getDataFromSharedPreferences();
        if(instaItemList != null) {
            view.findViewById(R.id.insta_loading).setVisibility(View.GONE);
            recyclerView = view.findViewById(R.id.instafragment_recycler);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), GridLayoutManager.VERTICAL));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), GridLayoutManager.HORIZONTAL));
            adapter = new InstaItemAdapter();
            adapter.setInstaItemList(instaItemList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
        }
        return view;
    }

    private void getDataFromSharedPreferences() {
        String jsonString ;
        SharedPreferences pref = getActivity().getSharedPreferences("JSONData", Context.MODE_PRIVATE);
        jsonString = pref.getString("InstagramJSON", null);
        JSONObject reader;
        JSONArray jsonArray;

        try {
            jsonString = jsonString.replace("\"{", "{");
            jsonString = jsonString.replace("}\"", "}");
            jsonString = jsonString.replace("\\\"", "\"");
            jsonString = jsonString.replace("\\n", "");
            System.out.println(jsonString);
            reader = new JSONObject(jsonString);
        }catch(JSONException e) {
            e.printStackTrace();
            return;
        }catch(Exception e) {
            System.out.println("Unknown exception " + e.getMessage());
            return;
        }
        try {
            jsonArray = reader.getJSONArray("links");
        }catch(JSONException e) {
            e.printStackTrace();
            return;
        }
        for(int i = 0; i < 38; i += 2) {
            if(i == 0)
                instaItemList = new ArrayList<>();
            try {
                InstaItem item = new InstaItem();
                item.setImageURL(jsonArray.getString(i));
                System.out.println(item.getImageURL() + "i = " + i);
                item.setCaption(jsonArray.getString(i + 1));
                instaItemList.add(item);
            }catch(JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The contents of instaitemlist are \n" + instaItemList.toString());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            // TODO :mListener.onFragmentInteraction(uri);
        }
    }
    public static void callTheMainFunction(String string) {
       mListener.onInstagramFragmentInteraction(string);
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
        void onInstagramFragmentInteraction(String string);
    }
}
