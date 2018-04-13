package com.absathe.gravitate;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    private List<FBItem> fbItemList = new ArrayList<>();
    private FBItem item = new FBItem();
    private RecyclerView recyclerView;
    private FBItemAdapter itemAdapter;

    private static OnFragmentInteractionListener mListener;

    public FBFragment() {
        String jsonString = "{\"links\": [\"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151536636474953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151536514644953/?type=3\",\"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151536330524953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151536293994953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151536206464953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151535748899953/\", \"https://goo.gl/4wniht\", \"https://www.facebook.com/RealMadrid/videos/10151535436194953/\", \"http://bit.ly/HugoSanchez_EN\", \"https://www.facebook.com/RealMadrid/videos/10151534903344953/\", \"http://bit.ly/SanitasCardRM\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151534690849953/?type=3\", \"https://www.realmadrid.com/landings/Universidad_Europea/index_en.html\", \"https://www.facebook.com/RealMadrid/videos/10151534351364953/\", \"https://www.facebook.com/RealMadrid/photos/a.10150564203974953.1073742298.19034719952/10151534211024953/?type=3\", \"http://bit.ly/RMPSGGoals\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151533937189953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151533902099953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151533824359953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151533716749953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151533651889953/\", \"https://www.facebook.com/RealMadrid/videos/10151533542314953/\", \"https://www.facebook.com/RealMadrid/videos/10151533451609953/\", \"https://www.facebook.com/RealMadrid/videos/10151533410944953/\", \"https://www.facebook.com/RealMadrid/videos/10151533135479953/\", \"https://www.facebook.com/RealMadrid/videos/10151533027449953/\", \"https://aplicacion.realmadrid.com/p/23939?lc=eng\", \"https://goo.gl/EGJPxn\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151532476319953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151532412704953/\", \"http://hyperurl.co/rmfantasymanager\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151531687924953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151531884729953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151531688179953/?type=3\", \"http://bit.ly/GOALS_RM_RSoc_home\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151531650639953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151531610959953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151531309729953/\", \"https://www.facebook.com/RealMadrid/videos/10151530999734953/\", \"http://bit.ly/GOAL_Ra\\u00fal_RealSociedad\", \"http://www.tactigol.com/\", \"https://www.facebook.com/RealMadrid/videos/10151530257934953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151530077274953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151529886234953/\", \"https://goo.gl/HFUT2K\", \"https://www.facebook.com/RealMadrid/videos/10151529226854953/\", \"https://www.facebook.com/RealMadrid/videos/10151529093479953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151528714834953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151528619119953/\", \"https://www.facebook.com/RealMadrid/videos/10151528559204953/\", \"http://bit.ly/LevanteGoals\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151527873189953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151527792634953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151527768509953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151527600879953/\", \"https://www.facebook.com/RealMadrid/videos/10151527563594953/\", \"https://www.facebook.com/RealMadrid/videos/10151527223654953/\", \"http://bit.ly/RonaldoVsLevante\", \"http://hyperurl.co/rmfantasymanager\", \"https://www.facebook.com/RealMadrid/videos/10151526766739953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151526650869953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151526173694953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151526356704953/?type=3\", \"http://bit.ly/JanGoalsRM\", \"https://goo.gl/tPGi2x\", \"https://www.facebook.com/RealMadrid/videos/10151525499794953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151525263359953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151524957034953/\", \"http://bit.ly/GOALS_Valencia_RM\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151524560459953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151524522304953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151524466114953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151524391859953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151524363634953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151524268904953/\", \"https://www.facebook.com/RealMadrid/videos/10151523782774953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151522936504953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151522805369953/\", \"https://www.facebook.com/RealMadrid/videos/10151522500829953/\", \"https://www.facebook.com/RealMadrid/videos/10151522065309953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151521965449953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151521953434953/\", \"https://www.facebook.com/RealMadrid/videos/10151521826939953/\", \"https://www.facebook.com/RealMadrid/videos/10151520891949953/\", \"https://www.facebook.com/RealMadrid/videos/10151520932304953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151520683879953/?type=3\", \"http://bit.ly/RMDeporGoals\", \"https://www.facebook.com/RealMadrid/photos/a.10150564203974953.1073742298.19034719952/10151520273204953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151520134149953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151519976804953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151519941214953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151519814814953/\", \"https://www.facebook.com/RealMadrid/videos/10151519761599953/\", \"https://www.facebook.com/RealMadrid/photos/a.10150564203974953.1073742298.19034719952/10151519400309953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151519276209953/\", \"https://www.facebook.com/RealMadrid/videos/10151519138134953/\", \"https://goo.gl/Zh4njn\", \"https://www.facebook.com/RealMadrid/photos/a.10150564203974953.1073742298.19034719952/10151518851379953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151518705639953/?type=3\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151518569684953/?type=3\", \"http://bit.ly/GOLAZO_Legan\\u00e9s_RM\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151518172074953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151518061814953/\", \"https://www.facebook.com/RealMadrid/videos/10151517716709953/\", \"https://www.facebook.com/RealMadrid/videos/10151517234339953/\", \"https://aplicacion.realmadrid.com/p/23573?lc=eng\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151517203609953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151516970229953/\", \"https://www.facebook.com/RealMadrid/videos/10151516548569953/\", \"https://www.facebook.com/RealMadrid/videos/10151516360994953/\", \"https://www.facebook.com/RealMadrid/videos/10151515873849953/\", \"https://goo.gl/FN1Tv7\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151514613409953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151514008539953/\", \"https://www.facebook.com/RealMadrid/videos/10151513908874953/\", \"https://www.facebook.com/RealMadrid/videos/10151513884079953/\", \"http://bit.ly/RonaldoVsVillarreal\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151513708389953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151513655694953/\", \"https://www.facebook.com/RealMadrid/videos/10151513215124953/\", \"https://www.facebook.com/RealMadrid/videos/10151512785109953/\", \"http://bit.ly/FBNumanciaGoals\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151512740664953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151512639504953/\", \"https://www.facebook.com/RealMadrid/videos/10151512309854953/\", \"https://www.facebook.com/RealMadrid/videos/10151511897969953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151511686624953/?type=3\", \"https://aplicacion.realmadrid.com/p/23374?lc=eng\", \"https://www.facebook.com/RealMadrid/videos/10151511558989953/\", \"http://bit.ly/CeltaGoals\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151510507439953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151510368859953/\", \"https://www.facebook.com/RealMadrid/videos/10151510315809953/\", \"https://www.facebook.com/RealMadrid/videos/10151510037234953/\", \"https://www.facebook.com/RealMadrid/videos/10151510016809953/\", \"https://www.facebook.com/RealMadrid/videos/10151509584354953/\", \"http://bit.ly/RobertoCarlosVigo\", \"https://www.facebook.com/RealMadrid/videos/10151509419469953/\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151508907299953/?type=3\", \"https://goo.gl/DPuXVz\", \"https://goo.gl/WfMos3\", \"https://www.facebook.com/RealMadrid/videos/10151508744854953/\", \"http://bit.ly/NumanciaGoals\", \"https://www.facebook.com/RealMadrid/photos/a.74265819952.19583.19034719952/10151508364359953/?type=3\", \"https://www.facebook.com/RealMadrid/videos/10151508291979953/\", \"https://www.facebook.com/RealMadrid/videos/10151508254759953/\", \"https://www.facebook.com/RealMadrid/videos/10151508179874953/\", \"https://realmadrid.onelink.me/haIY/b0d2cd13\", \"https://www.facebook.com/RealMadrid/videos/10151507923804953/\", \"https://www.facebook.com/RealMadrid/videos/10151507885549953/\", \"https://www.facebook.com/RealMadrid/videos/10151507468329953/\", \"https://www.facebook.com/RealMadrid/videos/10151507396419953/\", \"https://www.facebook.com/RealMadrid/videos/10151507231359953/\", \"https://www.facebook.com/RealMadrid/videos/10151506770319953/\", \"https://goo.gl/kgCGfH\", \"http://bit.ly/WATCH_2017_finals_goals\", \"https://www.facebook.com/RealMadrid/videos/10151504029989953/\", \"https://www.facebook.com/RealMadrid/videos/10151505311079953/\"]}";
        JSONObject reader = null;
        JSONArray jsonArray = null;
        try {
            reader = new JSONObject(jsonString);
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Unable to read the JSON format. Please report this to developer", Toast.LENGTH_LONG).show();
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
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                item = new FBItem().setWebViewURL(jsonArray.getString(i));
            } catch(final JSONException e) {
                e.printStackTrace();
                return;
            }
            fbItemList.add(item);
        }
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
        recyclerView = view.findViewById(R.id.fbfragment_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemAdapter = new FBItemAdapter();
        itemAdapter.setItemList(fbItemList);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
        return view;
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
    }
}
