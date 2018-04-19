package com.absathe.gravitate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private OnFragmentInteractionListener mListener;

    public YTFragment() {
        String jsonString = "{\"links\": [\"https://youtu.be/bYSRPuDEnTg\", \"Garmi Ke Side-Effects | Ashish Chanchlani\", \"https://i.ytimg.com/vi/bYSRPuDEnTg/hqdefault.jpg\", \"https://youtu.be/dUaCEs3HfdI\", \"Aastha Gill - Buzz feat Badshah | Priyank Sharma | Official Music Video\", \"https://i.ytimg.com/vi/dUaCEs3HfdI/hqdefault.jpg\", \"https://youtu.be/hLVi_IEuLMM\", \"Rangasthalam whole team Dance Perfomance @Rangasthalam #Vijayostavam\", \"https://i.ytimg.com/vi/hLVi_IEuLMM/hqdefault.jpg\", \"https://youtu.be/D4uWz6rm0RQ\", \"Pawan Kalyan Full Speech | Rangasthalam Vijayotsavam Event | Ram Charan | Samantha | Sukumar | DSP\", \"https://i.ytimg.com/vi/D4uWz6rm0RQ/hqdefault.jpg\", \"https://youtu.be/9dBepLCkjZQ\", \"Main Sehra Bandh Ke Aaunga | Superhit Full Bhojpuri Movie | Khesari Lal Yadav, Kajal Raghwani\", \"https://i.ytimg.com/vi/9dBepLCkjZQ/hqdefault.jpg\", \"https://youtu.be/f6NJvg-kWJ8\", \"Sangati - Amit Bhadana\", \"https://i.ytimg.com/vi/f6NJvg-kWJ8/hqdefault.jpg\", \"https://youtu.be/oRyaPq1-vro\", \"Pawan Kalyan Funny Explanation about his Spectacles @ Rangasthalam Vijayotsavam || Success Meet\", \"https://i.ytimg.com/vi/oRyaPq1-vro/hqdefault.jpg\", \"https://youtu.be/J-dv_DcDD_A\", \"ZAYN - Let Me (Official Video)\", \"https://i.ytimg.com/vi/J-dv_DcDD_A/hqdefault.jpg\", \"https://youtu.be/ZYR02RgRc1g\", \"Sudigaali Sudheer Performance | Extra Jabardasth | 13th April 2018 | ETV Telugu\", \"https://i.ytimg.com/vi/ZYR02RgRc1g/hqdefault.jpg\", \"https://youtu.be/EmUaWk5HBSI\", \"Monitor Ho Toh Aisa | Classroom Qtiyapa\", \"https://i.ytimg.com/vi/EmUaWk5HBSI/hqdefault.jpg\", \"https://youtu.be/YjMSttRJrhA\", \"\\u2018Raazi\\u2019 Official Trailer | Alia Bhatt, Vicky Kaushal | Directed by Meghna Gulzar | 11th May 2018\", \"https://i.ytimg.com/vi/YjMSttRJrhA/hqdefault.jpg\", \"https://youtu.be/i5qOzqD9Rms\", \"Incredibles 2 Official Trailer\", \"https://i.ytimg.com/vi/i5qOzqD9Rms/hqdefault.jpg\", \"https://youtu.be/VukVPfztS_0\", \"Thanni Varuma Varadha feat. Jump Cuts Hari Baskar | Fully\", \"https://i.ytimg.com/vi/VukVPfztS_0/hqdefault.jpg\", \"https://youtu.be/1534GZXeszY\", \"TYPES OF LATE NIGHT PEOPLE | Aashqeen\", \"https://i.ytimg.com/vi/1534GZXeszY/hqdefault.jpg\", \"https://youtu.be/u8VkK1ydBvI\", \"Types Of Phone Users You'll Meet | Hasley India\", \"https://i.ytimg.com/vi/u8VkK1ydBvI/hqdefault.jpg\", \"https://youtu.be/2FDYcrofU1w\", \"Monty's Lie Lands Tapu Sena In Trouble | Tapu Sena Special | Taarak Mehta Ka Ooltah Chashmah\", \"https://i.ytimg.com/vi/2FDYcrofU1w/hqdefault.jpg\", \"https://youtu.be/yliO6jWsdS8\", \"Pawan Kalyan Makes Fun of Anasuya | Rangasthalam Vijayotsavam | Ram Charan | Samantha\", \"https://i.ytimg.com/vi/yliO6jWsdS8/hqdefault.jpg\", \"https://youtu.be/VgyBAtA5yXs\", \"Dice Media | Adulting | Web Series | Official Trailer | Ep 1. Releasing on 18th April, 2018\", \"https://i.ytimg.com/vi/VgyBAtA5yXs/hqdefault.jpg\", \"https://youtu.be/67LFrXVIA00\", \"WITHOUT YOU - JASS MANAK (Full Song) Satti Dhillon | Latest Punjabi Songs 2018 | Geet MP3\", \"https://i.ytimg.com/vi/67LFrXVIA00/hqdefault.jpg\", \"https://youtu.be/yhXJLldSdkY\", \"DHEE 10 Latest Promo - 18th April 2018 - Sudheer, Rashmi, Priyamani, Sekhar Master\", \"https://i.ytimg.com/vi/yhXJLldSdkY/hqdefault.jpg\", \"https://youtu.be/NBQCMwnoP_w\", \"Bro Code in School | Exam Special | The Screen Patti\", \"https://i.ytimg.com/vi/NBQCMwnoP_w/hqdefault.jpg\", \"https://youtu.be/jCapB7AbXHw\", \"Sri Reddy Exposed Name of Another Executive Producer | Chiranjeevi | Vakada Apparao | Adya Media\", \"https://i.ytimg.com/vi/jCapB7AbXHw/hqdefault.jpg\", \"https://youtu.be/SR64ivbI94U\", \"Pawan Kalyan Responds On Actress Sri Reddy Controversy | ABN Telugu\", \"https://i.ytimg.com/vi/SR64ivbI94U/hqdefault.jpg\", \"https://youtu.be/7TRFf7uUfhQ\", \"Swag Se Swagat - Full Song | Tiger Zinda Hai | Salman Khan | Katrina Kaif | Vishal | Neha\", \"https://i.ytimg.com/vi/7TRFf7uUfhQ/hqdefault.jpg\", \"https://youtu.be/N6ZYJ__tiXM\", \"BYN : Things We Do For Love\", \"https://i.ytimg.com/vi/N6ZYJ__tiXM/hqdefault.jpg\", \"https://youtu.be/bqx08H6wfI0\", \"Beautiful Love Lyrical | Naa Peru Surya Naa Illu India Songs | Allu Arjun, Anu Emannuel\", \"https://i.ytimg.com/vi/bqx08H6wfI0/hqdefault.jpg\", \"https://youtu.be/M4ZoCHID9GI\", \"The Weeknd - Call Out My Name (Official Video)\", \"https://i.ytimg.com/vi/M4ZoCHID9GI/hqdefault.jpg\", \"https://youtu.be/rucOLpxX6WE\", \"Sri Reddy gets emotional seeing mother's interview || Tollywood Casting Couch - TV9\", \"https://i.ytimg.com/vi/rucOLpxX6WE/hqdefault.jpg\", \"https://youtu.be/JogJm2_EOFY\", \"This Gangster shot parmish verma || singer parmish verma shot by dilpreet singh ||\", \"https://i.ytimg.com/vi/JogJm2_EOFY/hqdefault.jpg\", \"https://youtu.be/io-WuGHSax4\", \"Desi Prem Kahani - Episode 03 - Faisla | Lalit Shokeen Films |\", \"https://i.ytimg.com/vi/io-WuGHSax4/hqdefault.jpg\", \"https://youtu.be/doUyKmNIbog\", \"Epic - Call Clash Prank on Cute Girls Part 2 | The HunGama Films\", \"https://i.ytimg.com/vi/doUyKmNIbog/hqdefault.jpg\", \"https://youtu.be/dqaDpwweeYM\", \"STAGS | Web Series | Episode 1 - On The House | The Timeliners\", \"https://i.ytimg.com/vi/dqaDpwweeYM/hqdefault.jpg\", \"https://youtu.be/0_6saEne8tY\", \"Breaking:- Punjabi Singer Parmish Verma shot at, admitted in Fortis Hospital\", \"https://i.ytimg.com/vi/0_6saEne8tY/hqdefault.jpg\", \"https://youtu.be/voJaBszuk2Q\", \"Uppum Mulakum\\u2502Flowers\\u2502EP# 581\", \"https://i.ytimg.com/vi/voJaBszuk2Q/hqdefault.jpg\", \"https://youtu.be/hpEjxsHT0Zo\", \"Priyamanaval Episode 989, 13/04/18\", \"https://i.ytimg.com/vi/hpEjxsHT0Zo/hqdefault.jpg\", \"https://youtu.be/ERqhLv0S0-0\", \"\\u0ab0\\u0abf\\u0a95\\u0acd\\u0ab7\\u0abe\\u0ab5\\u0abe\\u0ab3\\u0abe \\u0aa8\\u0ac0 \\u0aae\\u0acb\\u0a9c \\u0ab9\\u0acb || dhaval domadiya\", \"https://i.ytimg.com/vi/ERqhLv0S0-0/hqdefault.jpg\", \"https://youtu.be/-ysOCzfLGwE\", \"Jodi Teri Meri | Official Video | Jassi Gill | Desi Crew | Latest Song 2018 | Speed Records\", \"https://i.ytimg.com/vi/-ysOCzfLGwE/hqdefault.jpg\", \"https://youtu.be/UTqoHtOy8aM\", \"Pove Pora | 13th April 2018 | Full Episode 39| ETV Plus\", \"https://i.ytimg.com/vi/UTqoHtOy8aM/hqdefault.jpg\", \"https://youtu.be/jahJR9pZJA4\", \"\\u0a2e\\u0a48 \\u0a2e\\u0a3e\\u0a30\\u0a40 \\u0a39\\u0a48 Parmish Verma \\u0a28\\u0a42\\u0a70 \\u0a17\\u0a4b\\u0a32\\u0a40: Gangster Dilpreet Baba, \\u0a35\\u0a47\\u0a16\\u0a4b \\u0a2c\\u0a3e\\u0a2c\\u0a3e \\u0a28\\u0a47 \\u0a15\\u0a40-\\u0a15\\u0a40 \\u0a15\\u0a2c\\u0a42\\u0a32\\u0a3f\\u0a06\", \"https://i.ytimg.com/vi/jahJR9pZJA4/hqdefault.jpg\", \"https://youtu.be/Xe53gFvlUD4\", \"Gora Rang Gurnam Bhullar | Full Song | Latest Punjabi Song 2018 White Hill Music | New Punjabi Song\", \"https://i.ytimg.com/vi/Xe53gFvlUD4/hqdefault.jpg\", \"https://youtu.be/r1N8oKFiat4\", \"Sri Reddy's strong comments on Suresh Babu's son || Tollywood Casting Couch - TV9\", \"https://i.ytimg.com/vi/r1N8oKFiat4/hqdefault.jpg\", \"https://youtu.be/zHI0ui0qx3I\", \"Kuladheivam SUN TV Climax Episode - 897 (13-04-18)\", \"https://i.ytimg.com/vi/zHI0ui0qx3I/hqdefault.jpg\", \"https://youtu.be/3NKyqVWZQsI\", \"Sri Reddy Sri Leaks | \\u0c26\\u0c17\\u0c4d\\u0c17\\u0c41\\u0c2c\\u0c3e\\u0c1f\\u0c3f \\u0c38\\u0c41\\u0c30\\u0c47\\u0c37\\u0c4d \\u0c2c\\u0c3e\\u0c2c\\u0c41 \\u0c15\\u0c4a\\u0c21\\u0c41\\u0c15\\u0c41 \\u0c05\\u0c2d\\u0c3f\\u0c30\\u0c3e\\u0c2e\\u0c4d \\u0c28\\u0c40\\u0c15\\u0c41 \\u0c38\\u0c3f\\u0c17\\u0c4d\\u0c17\\u0c41\\u0c02\\u0c26\\u0c30\\u0c3e...\\u0c05\\u0c02\\u0c1f\\u0c42 \\u0c2c\\u0c2f\\u0c1f \\u0c2a\\u0c46\\u0c1f\\u0c4d\\u0c1f\\u0c3f\\u0c28..\\u0c36\\u0c4d\\u0c30\\u0c40\", \"https://i.ytimg.com/vi/3NKyqVWZQsI/hqdefault.jpg\", \"https://youtu.be/ctIaI1kHdgM\", \"\\u0c28\\u0c28\\u0c4d\\u0c28\\u0c41 \\u0c05\\u0c28\\u0c41\\u0c2d\\u0c35\\u0c3f\\u0c02\\u0c1a\\u0c02\\u0c21\\u0c3f \\u0c28\\u0c3e\\u0c15\\u0c41 \\u0c06\\u0c30\\u0c4d\\u200c\\u0c1c\\u0c46\\u0c02\\u0c1f\\u0c4d \\u0c17\\u0c3e \\u0c36\\u0c43\\u0c02\\u0c17\\u0c3e\\u0c30\\u0c02 \\u0c15\\u0c3e\\u0c35\\u0c3e\\u0c32\\u0c3f \\u0c05\\u0c02\\u0c1f\\u0c42 \\u0c30\\u0c4b\\u0c21\\u0c4d\\u0c21\\u0c46\\u0c15\\u0c4d\\u0c15\\u0c3f\\u0c28 \\u0c2f\\u0c41\\u0c35\\u0c24\\u0c3f |Mana Telugu\", \"https://i.ytimg.com/vi/ctIaI1kHdgM/hqdefault.jpg\", \"https://youtu.be/-QysGuAv0Bg\", \"Kona Venkat Forced Me For His Desire: Sri Reddy | #PTM\", \"https://i.ytimg.com/vi/-QysGuAv0Bg/hqdefault.jpg\", \"https://youtu.be/-EvDugo6fCo\", \"Go Back Modi Troll | Buddies Entertainment | #GoBackModi #WeWantCMB\", \"https://i.ytimg.com/vi/-EvDugo6fCo/hqdefault.jpg\", \"https://youtu.be/KWImTqoHbZo\", \"FilterCopy | Things Girl Roommates Do | Ft Aisha Ahmed & Yashaswini Dayama\", \"https://i.ytimg.com/vi/KWImTqoHbZo/hqdefault.jpg\", \"https://youtu.be/Pc4Q0Jr_yUg\", \"\\u0909\\u0928\\u094d\\u0928\\u093e\\u0935 \\u0917\\u0948\\u0902\\u0917\\u0930\\u0947\\u092a \\u0915\\u0947\\u0938:\\u092a\\u0940\\u0921\\u093c\\u093f\\u0924-\\u0935\\u093f\\u0927\\u093e\\u092f\\u0915 \\u0915\\u094b \\u0906\\u092e\\u0928\\u0947-\\u0938\\u093e\\u092e\\u0928\\u0947 \\u092c\\u093f\\u0920\\u093e\\u0915\\u0930 \\u0932\\u0916\\u0928\\u090a \\u092e\\u0947\\u0902 \\u092a\\u0942\\u091b\\u0924\\u093e\\u091b;\\u0915\\u0941\\u0932\\u0926\\u0940\\u092a \\u0938\\u0947\\u0902\\u0917\\u0930 \\u0915\\u0940 \\u0915\\u094b\\u0930\\u094d\\u091f \\u092e\\u0947\\u0902 \\u092a\\u0947\\u0936\\u0940\", \"https://i.ytimg.com/vi/Pc4Q0Jr_yUg/hqdefault.jpg\", \"https://youtu.be/jYjUjYGNjgc\", \"Manasu Mamata | 13th April 2018 |Full Episode No 2255| ETV Telugu\", \"https://i.ytimg.com/vi/jYjUjYGNjgc/hqdefault.jpg\", \"https://youtu.be/OFIBnDEj9G4\", \"\\u0bb5\\u0bb3\\u0bcd\\u0bb3\\u0bbf | VALLI | Sun TV | Tamil | Mega Serial | Episode 1531 - 13th April 2018\", \"https://i.ytimg.com/vi/OFIBnDEj9G4/hqdefault.jpg\", \"https://youtu.be/nfxnAMCo5DI\", \"Saat Bhai Champa - Episode 123 - April 14, 2018 - Preview\", \"https://i.ytimg.com/vi/nfxnAMCo5DI/hqdefault.jpg\", \"https://youtu.be/7go3ll78b4Y\", \"Bharya || General Promo || Mon to Sat at 9 PM || Asianet\", \"https://i.ytimg.com/vi/7go3ll78b4Y/hqdefault.jpg\", \"https://youtu.be/G0106lkCXnI\", \"'Gaal Ni Kadni' singer Parmish Verma shot at in Mohali\", \"https://i.ytimg.com/vi/G0106lkCXnI/hqdefault.jpg\", \"https://youtu.be/GQmantbJ7ms\", \"\\u0d15\\u0d4a\\u0d32\\u0d2e\\u0d3e\\u0d38\\u0d4d\\u0d38\\u0d4d | Kammara Sambhavam Review | Dileep | Rathish Ambat | Murali Gopy !\", \"https://i.ytimg.com/vi/GQmantbJ7ms/hqdefault.jpg\", \"https://youtu.be/B6GDG1jJtfE\", \"\\u0a96\\u0a9c\\u0ac1\\u0ab0\\u0aad\\u0abe\\u0a88 \\u0aa8\\u0acb IPL - IPL ni moj with Khajurbhai\", \"https://i.ytimg.com/vi/B6GDG1jJtfE/hqdefault.jpg\", \"https://youtu.be/uOba80Ql4Tw\", \"Kumkum Bhagya | From 23rd April | Mon-Fri 9:00pm | Promo\", \"https://i.ytimg.com/vi/uOba80Ql4Tw/hqdefault.jpg\", \"https://youtu.be/ZSityCJgGNY\", \"Baal Veer - \\u092c\\u093e\\u0932 \\u0935\\u0940\\u0930 - Episode 928 - 13th April , 2018\", \"https://i.ytimg.com/vi/ZSityCJgGNY/hqdefault.jpg\", \"https://youtu.be/YEIozlOEg08\", \"CRY BABIES Gulati & Kapil - The Kapil Sharma Show\", \"https://i.ytimg.com/vi/YEIozlOEg08/hqdefault.jpg\", \"https://youtu.be/03A3D08OFHc\", \"Babu Gogineni || MAA an illegal body || Sri Reddy leaks || Tollywood Casting Couch - TV9\", \"https://i.ytimg.com/vi/03A3D08OFHc/hqdefault.jpg\", \"https://youtu.be/im_GltEoqQ0\", \"ScoopWhoop: Types Of Students You Meet In An Exam Hall\", \"https://i.ytimg.com/vi/im_GltEoqQ0/hqdefault.jpg\", \"https://youtu.be/wq6Ve3M5iQ0\", \"Ram Charan Reaction On Actress Sri Reddy || Actress Sri Reddy Latest News | Tollywood Celebrity News\", \"https://i.ytimg.com/vi/wq6Ve3M5iQ0/hqdefault.jpg\", \"https://youtu.be/lRKMcS25CSI\", \"'How Long Will We Be Silent?': Prakash Raj Joins #JusticeForAsifa Chorus\", \"https://i.ytimg.com/vi/lRKMcS25CSI/hqdefault.jpg\", \"https://youtu.be/hvDDl1feEug\", \"Mehbooba Theatrical Trailer | Puri Jagannadh | Akash Puri | Neha Shetty | Sandeep Chowta\", \"https://i.ytimg.com/vi/hvDDl1feEug/hqdefault.jpg\", \"https://youtu.be/VCisJUj2T0s\", \"Poove Poochoodava - Episode 254 - April 13, 2018 - Best Scene\", \"https://i.ytimg.com/vi/VCisJUj2T0s/hqdefault.jpg\", \"https://youtu.be/2lsqbQkQS-M\", \"Tuzhat Jeev Rangala - Episode 485 - April 13, 2018 - Preview\", \"https://i.ytimg.com/vi/2lsqbQkQS-M/hqdefault.jpg\", \"https://youtu.be/Y53AevwVjSU\", \"\\u0b86\\u0bb0\\u0bcd\\u0baf\\u0bbe.. \\u0baf\\u0bbe\\u0bb0\\u0bcd \\u0bb5\\u0bc0\\u0b9f\\u0bcd\\u0b9f\\u0bc1 \\u0bae\\u0bbe\\u0baa\\u0bcd\\u0baa\\u0bbf\\u0bb3\\u0bcd\\u0bb3\\u0bc8?\", \"https://i.ytimg.com/vi/Y53AevwVjSU/hqdefault.jpg\", \"https://youtu.be/qD5HIMjbIbQ\", \"Simbu\\u0bb5\\u0bc8 \\u0b86\\u0ba4\\u0bb0\\u0bbf\\u0b95\\u0bcd\\u0b95\\u0bc1\\u0bae\\u0bcd \\u0b95\\u0ba9\\u0bcd\\u0ba9\\u0b9f \\u0bae\\u0b95\\u0bcd\\u0b95\\u0bb3\\u0bcd : Kannadigas Support Simbu | Cauvery Issue\", \"https://i.ytimg.com/vi/qD5HIMjbIbQ/hqdefault.jpg\", \"https://youtu.be/8aUsh5vdr3E\", \"\\u0bb5\\u0bbe\\u0ba3\\u0bbf \\u0bb0\\u0bbe\\u0ba3\\u0bbf - VAANI RANI - Episode 1542 - 13/04/2018\", \"https://i.ytimg.com/vi/8aUsh5vdr3E/hqdefault.jpg\", \"https://youtu.be/Bg_jvPZkYYg\", \"\\u0b9a\\u0bc0\\u0ba4\\u0bbe \\u0bb2\\u0b9f\\u0bcd\\u0b9a\\u0bc1\\u0bae\\u0bbf\\u0baf\\u0bc8 \\u0ba4\\u0bbf\\u0bb0\\u0bc1\\u0bae\\u0ba3\\u0bae\\u0bcd \\u0b9a\\u0bc6\\u0baf\\u0bcd\\u0ba4 \\u0b86\\u0bb0\\u0bcd\\u0baf\\u0bbe ! \\u0b86\\u0ba4\\u0bbe\\u0bb0\\u0bae\\u0bcd \\u0b87\\u0ba4\\u0bcb ! arya's wife Seetha Lakshmi ! Enga Veetu Mapillai\", \"https://i.ytimg.com/vi/Bg_jvPZkYYg/hqdefault.jpg\", \"https://youtu.be/_s-r7RHHiCg\", \"\\u0c30\\u0c47\\u0c2a\\u0c47 \\u0c36\\u0c28\\u0c3f \\u0c24\\u0c4d\\u0c30\\u0c2f\\u0c4b\\u0c26\\u0c36\\u0c3f.\\u0c2a\\u0c35\\u0c3f\\u0c24\\u0c4d\\u0c30\\u0c2e\\u0c48\\u0c28 \\u0c30\\u0c4b\\u0c1c\\u0c41.\\u0c08 \\u0c1a\\u0c3f\\u0c28\\u0c4d\\u0c28 \\u0c2a\\u0c28\\u0c3f\\u0c1a\\u0c47\\u0c38\\u0c4d\\u0c24\\u0c47 \\u0c12\\u0c15\\u0c47\\u0c38\\u0c3e\\u0c30\\u0c3f \\u0c2a\\u0c3e\\u0c2a\\u0c3e\\u0c32\\u0c28\\u0c4d\\u0c28\\u0c40 \\u0c2a\\u0c4b\\u0c24\\u0c3e\\u0c2f\\u0c3f | Shani Trayodashi 2018\", \"https://i.ytimg.com/vi/_s-r7RHHiCg/hqdefault.jpg\", \"https://youtu.be/T9WN2_ikz6Q\", \"BB Ki Vines- | The Sacrifice |\", \"https://i.ytimg.com/vi/T9WN2_ikz6Q/hqdefault.jpg\", \"https://youtu.be/mLyGd6PJWl4\", \"\\u0914\\u0930\\u0902\\u0917\\u093e\\u092c\\u093e\\u0926 : \\u092b\\u0915\\u093f\\u0930\\u093e\\u092c\\u093e\\u0926\\u0935\\u093e\\u0921\\u0940\\u091a\\u0947 \\u0938\\u0941\\u092a\\u0941\\u0924\\u094d\\u0930 \\u0936\\u0939\\u0940\\u0926 \\u0915\\u093f\\u0930\\u0923 \\u0925\\u094b\\u0930\\u093e\\u0924 \\u092f\\u093e\\u0902\\u091a\\u094d\\u092f\\u093e\\u0935\\u0930 \\u0932\\u0937\\u094d\\u0915\\u0930\\u0940 \\u0907\\u0924\\u092e\\u093e\\u092e\\u093e\\u0924 \\u0905\\u0902\\u0924\\u094d\\u092f\\u0938\\u0902\\u0938\\u094d\\u0915\\u093e\\u0930\", \"https://i.ytimg.com/vi/mLyGd6PJWl4/hqdefault.jpg\", \"https://youtu.be/BDhBBAnSZU4\", \"19 SIMPLE & FAST LIFE HACKS\", \"https://i.ytimg.com/vi/BDhBBAnSZU4/hqdefault.jpg\", \"https://youtu.be/07HDitEkDhg\", \"\\u0936\\u0928\\u093f\\u0926\\u0947\\u0935 Top 10 \\u092d\\u091c\\u0928 :- \\u0936\\u0928\\u093f \\u0915\\u0940 \\u092a\\u0942\\u091c\\u093e \\u0927\\u0941\\u092e \\u0927\\u093e\\u092e \\u0938\\u0947 \\u0939\\u094b\\u0924\\u0940 \\u0939\\u0948 || \\u0936\\u0928\\u093f\\u0926\\u0947\\u0935 \\u0915\\u094b \\u092a\\u094d\\u0930\\u0938\\u0928\\u094d\\u0928 \\u0915\\u0930\\u0928\\u0947 \\u0935\\u093e\\u0932\\u093e \\u092d\\u091c\\u0928\", \"https://i.ytimg.com/vi/07HDitEkDhg/hqdefault.jpg\", \"https://youtu.be/awLKl2g0pJY\", \"\\u0cb0\\u0cb5\\u0cbf\\u0c9a\\u0c82\\u0ca6\\u0ccd\\u0cb0\\u0ca8\\u0ccd \\u0c97\\u0cc6 \\u0c87\\u0cb0\\u0cc1\\u0cb5 \\u0c95\\u0cb7\\u0ccd\\u0c9f\\u0c97\\u0cb3\\u0ca8\\u0ccd\\u0ca8\\u0cc1 \\u0ca8\\u0ccb\\u0ca1\\u0cbf\\u0ca6\\u0ccd\\u0cb0\\u0cc6 \\u0cb6\\u0cbe\\u0c95\\u0ccd | V.Ravichandran Present Situation | Namma Karunadu\", \"https://i.ytimg.com/vi/awLKl2g0pJY/hqdefault.jpg\", \"https://youtu.be/on3iH4ktUxA\", \"\\u0906\\u0930\\u094b\\u092a\\u093f\\u092f\\u094b\\u0902 \\u0915\\u0947 '\\u0939\\u093f\\u092e\\u093e\\u092f\\u0924\\u0940' \\u092e\\u0902\\u0924\\u094d\\u0930\\u093f\\u092f\\u094b\\u0902 \\u0915\\u093e \\u0907\\u0938\\u094d\\u0924\\u0940\\u092b\\u093e; \\u0915\\u0920\\u0941\\u0906 \\u092e\\u093e\\u092e\\u0932\\u0947 \\u092a\\u0930 PDP \\u0926\\u094b\\u092a\\u0939\\u0930 2 \\u092c\\u091c\\u0947 \\u0915\\u0930\\u0947\\u0902\\u0917\\u0947 \\u092c\\u0948\\u0920\\u0915\", \"https://i.ytimg.com/vi/on3iH4ktUxA/hqdefault.jpg\", \"https://youtu.be/Cl4zOn1u7Vw\", \"Nandini | 12 Apr 2018 | SunTV\", \"https://i.ytimg.com/vi/Cl4zOn1u7Vw/hqdefault.jpg\", \"https://youtu.be/JwdMw5bKQ3o\", \"WHEN YOU GET CAUGHT WITH YOUR EX #MENWILLBEMEN || Hyderabad Diaries\", \"https://i.ytimg.com/vi/JwdMw5bKQ3o/hqdefault.jpg\", \"https://youtu.be/1Hu2E7tUfiM\", \"Bithiri Sathi Reporting On Sri Reddy's Controversy | Teenmaar News | V6 News\", \"https://i.ytimg.com/vi/1Hu2E7tUfiM/hqdefault.jpg\", \"https://youtu.be/GqCAVnvb3QA\", \"Samsung Galaxy S9 Top Features and Tips Tricks - Galaxy S9 Mega Giveaway \\ud83d\\udd25\\ud83d\\udd25\\ud83d\\udd25\", \"https://i.ytimg.com/vi/GqCAVnvb3QA/hqdefault.jpg\", \"https://youtu.be/w5kYxlxwui4\", \"Sri Reddy trapped Suresh Babu's son Abhiram : Karate Kalyani || Tollywood Casting Couch - TV9\", \"https://i.ytimg.com/vi/w5kYxlxwui4/hqdefault.jpg\", \"https://youtu.be/aWltBbwKuYk\", \"Nua Bohu | Full Ep 233 | 13th Apr 2018 | Odia Serial - TarangTv\", \"https://i.ytimg.com/vi/aWltBbwKuYk/hqdefault.jpg\", \"https://youtu.be/GJ5GBUpLCZE\", \"RONDA ROUSEY'S EMOTIONAL CELEBRATION with The Bella Twins - Diary of WrestleMania\", \"https://i.ytimg.com/vi/GJ5GBUpLCZE/hqdefault.jpg\", \"https://youtu.be/qrcS2u4QuLE\", \"Tammareddy Bharadwaj Responds to Actress Sri Reddy's Stripping Issue | Tammareddy Bharadwaj\", \"https://i.ytimg.com/vi/qrcS2u4QuLE/hqdefault.jpg\", \"https://youtu.be/1KiBhy7Sex0\", \"Awkward Moment With Chemists | Being Indian\", \"https://i.ytimg.com/vi/1KiBhy7Sex0/hqdefault.jpg\", \"https://youtu.be/0jShG-jXNvo\", \"\\u0a2a\\u0a30\\u0a2e\\u0a40\\u0a36 \\u0a35\\u0a30\\u0a2e\\u0a3e \\u0a26\\u0a47 \\u0a17\\u0a4b\\u0a32\\u0a40 \\u0a2e\\u0a3e\\u0a30\\u0a28 \\u0a35\\u0a3e\\u0a32\\u0a47 \\u0a17\\u0a48\\u0a02\\u0a17\\u0a38\\u0a1f\\u0a30 \\u0a26\\u0a3e \\u0a39\\u0a4b\\u0a07\\u0a06 \\u0a16\\u0a41\\u0a32\\u0a3e\\u0a38\\u0a3e, \\u0a2b\\u0a47\\u0a30 \\u0a39\\u0a2e\\u0a32\\u0a3e \\u0a15\\u0a30\\u0a28 \\u0a26\\u0a40 \\u0a26\\u0a3f\\u0a71\\u0a24\\u0a40 \\u0a27\\u0a2e\\u0a15\\u0a40\", \"https://i.ytimg.com/vi/0jShG-jXNvo/hqdefault.jpg\", \"https://youtu.be/E6MFMb7mVEQ\", \"Bhramanam I Episode 45 - 13 April 2018 I Mazhavil Manorama\", \"https://i.ytimg.com/vi/E6MFMb7mVEQ/hqdefault.jpg\", \"https://youtu.be/bO4WvZOC5qE\", \"Justice for Asifa | The Story of Asifa in Tamil | Kichdy\", \"https://i.ytimg.com/vi/bO4WvZOC5qE/hqdefault.jpg\", \"https://youtu.be/E_YaXDDbOQQ\", \"Padmaavat | Ranveer Singh - Behind The Scenes\", \"https://i.ytimg.com/vi/E_YaXDDbOQQ/hqdefault.jpg\", \"https://youtu.be/8xq9cQHkXP4\", \"\\u0baa\\u0bcb\\u0bb0\\u0bbe\\u0b9f\\u0bcd\\u0b9f\\u0ba4\\u0bcd\\u0ba4\\u0bbf\\u0bb2\\u0bcd \\u0b88\\u0b9f\\u0bc1\\u0baa\\u0b9f\\u0bcd\\u0b9f\\u0bb5\\u0bb0\\u0bcd \\u0b95\\u0bbe\\u0bb0\\u0bcd \\u0baa\\u0bbe\\u0ba9\\u0b9f\\u0bcd\\u0b9f\\u0bbf\\u0bb2\\u0bcd \\u0b8f\\u0bb1\\u0bbf\\u0baf \\u0ba8\\u0bbf\\u0bb2\\u0bc8\\u0baf\\u0bbf\\u0bb2\\u0bcd \\u0b85\\u0bb0\\u0b9a\\u0bc1 \\u0b85\\u0ba4\\u0bbf\\u0b95\\u0bbe\\u0bb0\\u0bbf \\u0ba8\\u0bbf\\u0bb1\\u0bc1\\u0ba4\\u0bcd\\u0ba4\\u0bbe\\u0bae\\u0bb2\\u0bcd \\u0b9a\\u0bc6\\u0ba9\\u0bcd\\u0bb1 \\u0bb5\\u0bc0\\u0b9f\\u0bbf\\u0baf\\u0bcb\", \"https://i.ytimg.com/vi/8xq9cQHkXP4/hqdefault.jpg\", \"https://youtu.be/mG20Ov6VrK8\", \"\\u2018\\u0d1f\\u0d4d\\u0d30\\u0d3e\\u0d2b\\u0d3f\\u0d15\\u0d4d\\u2019 \\u0d38\\u0d4d\\u0d31\\u0d4d\\u0d31\\u0d48\\u0d32\\u0d3f\\u0d7d \\u0d06\\u0d02\\u0d2c\\u0d41\\u0d32\\u0d7b\\u0d38\\u0d4d \\u0d15\\u0d41\\u0d24\\u0d3f\\u0d1a\\u0d4d\\u0d1a\\u0d41; \\u0d07\\u0d1f\\u0d19\\u0d4d\\u0d15\\u0d4b\\u0d32\\u0d3f\\u0d1f\\u0d4d\\u0d1f\\u0d4d \\u0d13\\u0d1f\\u0d4d\\u0d1f\\u0d4b; \\u0d0e\\u0d28\\u0d4d\\u0d28\\u0d3f\\u0d1f\\u0d4d\\u0d1f\\u0d41\\u0d02, \\u0d35\\u0d3f\\u0d21\\u0d3f\\u0d2f\\u0d4b\", \"https://i.ytimg.com/vi/mG20Ov6VrK8/hqdefault.jpg\", \"https://youtu.be/TeA293PMT1s\", \"Mercury | Official Trailer | Prabhu Deva | Karthik Subbaraj | Pen Movies | In Cinemas April 13th\", \"https://i.ytimg.com/vi/TeA293PMT1s/hqdefault.jpg\", \"https://youtu.be/OJCkO1w9JoY\", \"La La Laletta - Mohanlal | Manju Warrier & Indrajith Sukumaran | Prarthana Indrajith | Sajid Yahiya\", \"https://i.ytimg.com/vi/OJCkO1w9JoY/hqdefault.jpg\", \"https://youtu.be/vcKPjDUc5EQ\", \"Things People Say After A Break-up | MostlySane\", \"https://i.ytimg.com/vi/vcKPjDUc5EQ/hqdefault.jpg\", \"https://youtu.be/HoB7u3UAqWM\", \"NOOR BHAI AUTOWALE || SHEHBAAZ KHAN\", \"https://i.ytimg.com/vi/HoB7u3UAqWM/hqdefault.jpg\", \"https://youtu.be/RbwpLmRSljc\", \"Sri Reddy : Does Suresh Babu's son have a license to exploit heroines? - TV9\", \"https://i.ytimg.com/vi/RbwpLmRSljc/hqdefault.jpg\", \"https://youtu.be/bM0pcuiKhAQ\", \"Jalsa Party With Dhvanit \\u2013 Episode 4 : Kinjal Dave and Jignesh Kaviraj | Dhvanit Thaker |\", \"https://i.ytimg.com/vi/bM0pcuiKhAQ/hqdefault.jpg\", \"https://youtu.be/CqzKzeCZzyA\", \"\\u0b9a\\u0bbf\\u0bae\\u0bcd\\u0baa\\u0bc1\\u0bb5\\u0bbf\\u0ba9\\u0bcd \\u0b95\\u0bcb\\u0bb0\\u0bbf\\u0b95\\u0bcd\\u0b95\\u0bc8\\u0baf\\u0bc8 \\u0b8f\\u0bb1\\u0bcd\\u0bb1 \\u0b95\\u0ba9\\u0bcd\\u0ba9\\u0b9f\\u0bb0\\u0bcd\\u0b95\\u0bb3\\u0bcd \\u0ba8\\u0bc6\\u0b95\\u0bbf\\u0bb4\\u0bcd\\u0b9a\\u0bcd\\u0b9a\\u0bbf \\u0bb5\\u0bc0\\u0b9f\\u0bbf\\u0baf\\u0bcb | simbu speech about cauvery\", \"https://i.ytimg.com/vi/CqzKzeCZzyA/hqdefault.jpg\", \"https://youtu.be/vWio5TorrHw\", \"\\u0bb5\\u0ba8\\u0bcd\\u0ba4\\u0bc1\\u0b9f\\u0bcd\\u0b9f\\u0bbe\\u0ba9\\u0bcd\\u0baf\\u0bbe.. \\u0bb5\\u0ba8\\u0bcd\\u0ba4\\u0bc1\\u0b9f\\u0bcd\\u0b9f\\u0bbe\\u0ba9\\u0bcd\\u0baf\\u0bbe.. CSK-\\u0bb5\\u0bbf\\u0bb1\\u0bcd\\u0b95\\u0bc1 \\u0b95\\u0bb3\\u0bae\\u0bbf\\u0bb1\\u0b99\\u0bcd\\u0b95\\u0bbf\\u0baf\\u0bbf\\u0bb0\\u0bc1\\u0b95\\u0bcd\\u0b95\\u0bc1\\u0bae\\u0bcd \\u0baa\\u0bc1\\u0ba4\\u0bbf\\u0baf \\u0b9a\\u0bbf\\u0b99\\u0bcd\\u0b95\\u0bae\\u0bcd! \\u0b85\\u0ba4\\u0bbf\\u0bb0\\u0b9f\\u0bbf \\u0b86\\u0bb0\\u0bae\\u0bcd\\u0baa\\u0bae\\u0bcd\", \"https://i.ytimg.com/vi/vWio5TorrHw/hqdefault.jpg\", \"https://youtu.be/B_zvR6faKow\", \"Muddha Mandaram - Episode 1057 - April 13, 2018 - Best Scene\", \"https://i.ytimg.com/vi/B_zvR6faKow/hqdefault.jpg\"]}";

        JSONObject reader;
        JSONArray jsonArray;

        try {
            reader = new JSONObject(jsonString);
        }catch(JSONException e) {
            e.printStackTrace();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_yt, container, false);
        recyclerView = view.findViewById(R.id.ytfragment_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        adapter = new YTItemAdapter();
        adapter.setYTItemList(ytItemList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
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
        // TODO: Update argument type and name
    }
}
