package com.absathe.gravitate;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.absathe.gravitate.adapters.YTItemAdapter;
import com.absathe.gravitate.items.YTItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link YTFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link YTFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YTFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<YTItem> ytItemList = null;
    private RecyclerView recyclerView;
    private YTItemAdapter adapter;
    private static OnFragmentInteractionListener mListener;

    public YTFragment() {
        ytItemList = null;
    }
    

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YTFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YTFragment newInstance(String param1, String param2) {
        YTFragment fragment = new YTFragment();
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
    public static void openInBrowser(String string) {
        if(mListener == null) {
            Log.d("YoutubeFragment", "Listener is nullllllllllllllllllll");
        }
        mListener.onYouTubeFragmentInteraction(string);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_yt, container, false);
        getDataFromSharedPreferences();
        if(ytItemList != null) {
            recyclerView = view.findViewById(R.id.ytfragment_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            adapter = new YTItemAdapter();
            adapter.setYTItemList(ytItemList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        return view;
    }

    private void getDataFromSharedPreferences() {
        String jsonString;
        JSONObject reader;
        JSONArray jsonArray;
        SharedPreferences pref = getActivity().getSharedPreferences("JSONData", Context.MODE_PRIVATE);
        jsonString = pref.getString("YouTubeJSON", null);
        try {
            jsonString = jsonString.replace("\"{", "{");
            jsonString = jsonString.replace("}\"", "}");
            jsonString = jsonString.replace("\\\"", "\"");
            jsonString = jsonString.replace("\\n", "");
            reader = new JSONObject(jsonString);
        }catch(JSONException e) {
            e.printStackTrace();
            return;
        } catch(Exception e) {
            e.getMessage();
            return;
        }
        try {
            jsonArray = reader.getJSONArray("links");
        }catch(JSONException e) {
            e.printStackTrace();
            return;
        }

        for(int i = 0; i < 210; i += 3) {
            if(i == 0)
                ytItemList = new ArrayList<>();
            YTItem item = new YTItem();
            try {
                item.setVideoURL(jsonArray.getString(i));
                item.setVideoTitle(jsonArray.getString(i + 1));
                item.setThumnailURL(jsonArray.getString(i + 2));
                ytItemList.add(item);
            }catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
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
        void onYouTubeFragmentInteraction(String string);
        // TODO: Update argument type and name
    }
}
