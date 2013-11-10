package com.example.abekahahosalo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class JoinTeam extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.join_team_layout, container, false);
		return rootView;

	}

	/*
	 public void sendMessage(View view) 
		{
		    Intent intent = new Intent(getActivity(), CreateTeam.class);
		    startActivity(intent);
		}
	 */
}
