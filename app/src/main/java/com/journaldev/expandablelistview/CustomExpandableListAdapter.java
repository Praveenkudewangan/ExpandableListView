package com.journaldev.expandablelistview;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private int groupexpan;
    private Context context;
    private List<Parentname> expandableListTitle;
    ToggleButton toggle;

    public CustomExpandableListAdapter(Context context, List<Parentname> expandableListTitle ) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
    }


    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListTitle.get(listPosition).getChildnameArrayList().get(expandedListPosition).getChildName();
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }


        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        expandableListTitle.get(listPosition).getChildnameArrayList().get(expandedListPosition);

    }
});
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListTitle.get(listPosition).getChildnameArrayList().size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition).getName();
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
         toggle = (ToggleButton) convertView.findViewById(R.id.togglespinner);

        CheckBox checkBoxgroup = (CheckBox) convertView.findViewById(R.id.checkboxgroup);

        if (getChildrenCount(listPosition) == 0){
            toggle.setVisibility(View.INVISIBLE);
            checkBoxgroup.setVisibility(View.VISIBLE);
        }
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

     if (isExpanded){

         toggle.setChecked(true);
     }else {
         toggle.setChecked(false);
     }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition){
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

        super.onGroupCollapsed(groupPosition);
    }
}