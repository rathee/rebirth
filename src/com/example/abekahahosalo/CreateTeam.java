package com.example.abekahahosalo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreateTeam extends Fragment{
	
	@Override
	public View onCreateView( LayoutInflater layoutInflater, ViewGroup container,
			Bundle savedInstance){
		View rootView = layoutInflater.inflate(R.layout.create_team_layout, container, false);
		return rootView;
	}

}
