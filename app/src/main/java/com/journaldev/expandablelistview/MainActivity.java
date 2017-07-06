package com.journaldev.expandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.width;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    ArrayList<Parentname> arraylistcricParent;
    HashMap<String, List<String>> expandableListDetail;
    JsonObjectRequest Saloondistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
         arraylistcricParent = new ArrayList<Parentname>();
//        expandableListDataPump = new ExpandableListDataPump(arraylistcricParent);
//        expandableListDetail = ExpandableListDataPump.getData();
//        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());



//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        arraylistcricParent.get(groupPosition).getName()+ " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

//
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        arraylistcricParent.get(groupPosition).getName() + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(
                        getApplicationContext(),
                        arraylistcricParent.get(groupPosition).getName()
                                + " -> "
                                +
                                arraylistcricParent.get(groupPosition).getChildnameArrayList().get(
                                childPosition).getChildName(), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
        Saloondistance = new JsonObjectRequest(Request.Method.GET, "https://mobile.beautycnslt.com/Api/GroupServiceProviderProfileService/FindByID/20021", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    saloonlist(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        Singleton_VolleyClass.getInstance(getApplicationContext()).addrequest(Saloondistance);


    }

    private void saloonlist(JSONObject response) throws JSONException {

        JSONObject entity = response.getJSONObject("Entity");
        JSONArray Jaservicesarray = entity.getJSONArray("Services");
        for (int i = 0; i < 5; i++) {
            JSONObject Jochildservices = Jaservicesarray.getJSONObject(i);
            String servicesenglish = Jochildservices.getString("EnglishName");
            int servicesid = Jochildservices.getInt("ServiceCategories_ID");



            JSONArray Jachildservicesarray = Jochildservices.getJSONArray("Children");
            ArrayList<Childname> arraylistdetailcricchild   = new ArrayList<Childname>();

            for (int j = 0; j < Jachildservicesarray.length(); j++) {

                JSONObject Jochildservices2 = Jachildservicesarray.getJSONObject(j);

                String childrenservices = Jochildservices2.getString("EnglishName");
                String childrenservices2 = Jochildservices2.getString("Name");
                int childrenservices2id = Jochildservices2.getInt("Parent_ID");

                if (servicesid == 8){
                    break;
                }
                arraylistdetailcricchild.add(new Childname(childrenservices, childrenservices2id));



            }
            arraylistcricParent.add(new Parentname(servicesenglish,servicesid, arraylistdetailcricchild));


        }

 expandableListAdapter = new CustomExpandableListAdapter(this, arraylistcricParent);
        expandableListView.setAdapter(expandableListAdapter);
    }
    public int GetDipsFromPixel(float pixels)
    {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}
