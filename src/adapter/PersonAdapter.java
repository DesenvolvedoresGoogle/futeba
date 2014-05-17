package adapter;

import helper.LoadProfileImage;

import java.io.InputStream;
import java.util.List;

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

import com.fglp.futeba.R;
import com.fglp.futeba.R.id;
import com.fglp.futeba.R.layout;
import com.google.android.gms.plus.model.people.Person;

public class PersonAdapter extends ArrayAdapter<Person> {
    private Context context;
    private List<Person> personList;
    
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
        TextView textName = (TextView)view.findViewById(R.id.personName);
        textName.setText(String.valueOf(person.getDisplayName()));
        
        // ID
        TextView textId = (TextView)view.findViewById(R.id.personId);
        textId.setText(String.valueOf(person.getId()));
        textId.setVisibility(View.INVISIBLE);
        
        final CheckBox check = (CheckBox) view.findViewById(R.id.check);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
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
}