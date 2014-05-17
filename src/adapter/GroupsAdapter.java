package adapter;

import java.io.InputStream;
import java.util.List;

import model.Group;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plusconnectiontest.LoadProfileImage;
import com.fglp.futeba.R;
import com.fglp.futeba.R.id;
import com.fglp.futeba.R.layout;
import com.google.android.gms.plus.model.people.Person;

public class GroupsAdapter extends ArrayAdapter<Person> {
    private Context context;
    private List<Group> groupList;
    
    public GroupsAdapter(Activity context, List<GrouPModel> groupList){
        super(context, R.layout.listview_groups, groupList);
    	this.context = context;
        this.groupList = groupList;
    }
    
    @Override
    public int getCount() {
        return groupList.size();
    }
 
    @Override
    public Person getItem(int position) {
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
        textName.setText(String.valueOf(person.getDisplayName()));
        
        // ID
        TextView textId = (TextView)view.findViewById(R.id.personId);
        textId.setText(String.valueOf(person.getId()));
        textId.setVisibility(View.INVISIBLE);
        
        
        return view;
    }
}