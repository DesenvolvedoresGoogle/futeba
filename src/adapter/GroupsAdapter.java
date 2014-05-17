package adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appspot.patota_hackathon.api.model.Group;
import com.fglp.futeba.R;

public class GroupsAdapter extends ArrayAdapter<Group> {
    private Context context;
    private List<Group> groupList;
    
    public GroupsAdapter(Activity context, List<Group> groupList){
        super(context, R.layout.listview_groups, groupList);
    	this.context = context;
        this.groupList = groupList;
    }
    
    @Override
    public int getCount() {
        return groupList.size();
    }
 
    @Override
    public Group getItem(int position) {
        return groupList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        Group group = groupList.get(position);
        
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_people, null);
        
        // Nome
        TextView textName = (TextView)view.findViewById(R.id.personName);
        textName.setText(String.valueOf(group.getName()));
        
        // ID
        TextView textId = (TextView)view.findViewById(R.id.personId);
        textId.setText(String.valueOf(group.getId()));
        textId.setVisibility(View.INVISIBLE);
        
        
        return view;
    }
}