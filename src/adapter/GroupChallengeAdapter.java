package adapter;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fglp.futeba.ChallengeGroup;
import com.fglp.futeba.PatotaAPIFactory;
import com.fglp.futeba.R;

public class GroupChallengeAdapter extends ArrayAdapter<ChallengeGroup> {

	private Context context;
	private List<ChallengeGroup> ccList;

	public GroupChallengeAdapter(Activity context, List<ChallengeGroup> ccList) {
		super(context, R.layout.groupchallenge_listview, ccList);
		this.context = context;
		this.ccList = ccList;
	}

	@Override
	public int getCount() {
		return ccList.size();
	}

	@Override
	public ChallengeGroup getItem(int position) {
		return ccList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Recupera o estado da posição atual
		final ChallengeGroup group = ccList.get(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.groupchallenge_listview, null);

		// Nome
		TextView textName = (TextView) view.findViewById(R.id.textView1);
		textName.setText(String.valueOf(group.groupName));

		// Distance
		TextView textId = (TextView) view.findViewById(R.id.textView2);
		textId.setText(String.valueOf(group.distance + " km"));
		textId.setVisibility(View.INVISIBLE);

		ImageButton ib = (ImageButton) view.findViewById(R.id.imageButton1);
		ib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							PatotaAPIFactory.getService().acceptChallenge(
									group.groupId, group.challengeId);
						} catch (IOException e) {
							throw new IllegalStateException(
									"Erro aceitando challenge");
						}

					}
				}).start();
			}
		});

		return view;
	}

}