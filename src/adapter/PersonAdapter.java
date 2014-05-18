package adapter;

import helper.LoadProfileImage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fglp.futeba.R;
import com.google.android.gms.plus.model.people.Person;

public class PersonAdapter extends ArrayAdapter<Person> {
    private Context context;
    private List<Person> personList;
    private ArrayList<String> selectedPeopleId = new ArrayList<String>();
    private TextView textName, textId;
    private CheckBox check;
    
    public PersonAdapter(Activity context, List<Person> personList){
        super(context, R.layout.listview_people, personList);
    	this.context = context;
        this.personList = personList;
    }
    
    @Override
    public int getCount() {
        return personList.size();
    }
 
    @Override
    public Person getItem(int position) {
        return personList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        Person person = personList.get(position);
        
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_people, null);
        
        // Nome
        textName = (TextView)view.findViewById(R.id.personName);
        textName.setText(String.valueOf(person.getDisplayName()));
        
        // ID
        textId = (TextView)view.findViewById(R.id.personId);
        textId.setText(String.valueOf(person.getId()));
        textId.setVisibility(View.INVISIBLE);
        
        check = (CheckBox) view.findViewById(R.id.check);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
					if (isChecked) {
	                    selectedPeopleId.add(textName.getText().toString());
	                } else {
	                	selectedPeopleId.remove(textName.getText().toString());
	                }
				}
		    });
        check.setTag(personList.get(position));
        
        //Setando Avatar
        ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        String personPhotoUrl = person.getImage().getUrl();
        if (personPhotoUrl != null && avatar != null) {
        	new LoadProfileImage(avatar).execute(personPhotoUrl.substring(0, personPhotoUrl.length() - 2) + 50);
        }
        return view;
    }
    
    public List<String> getSelectedPeopleId() {
    	return selectedPeopleId;
    }
}