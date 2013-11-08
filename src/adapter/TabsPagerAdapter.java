package adapter;

import com.example.abekahahosalo.About;
import com.example.abekahahosalo.CreateTeam;
import com.example.abekahahosalo.JoinTeam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{
	
	public TabsPagerAdapter( FragmentManager fm){
		super(fm);
	}
	
	@Override
	public Fragment getItem( int index ) {
		
		switch (index) {
		case 0:
			return new CreateTeam();
			
		case 1:
			return new JoinTeam();
			
		case 2:
			return new About();
			

		default:
			break;
		}
		return null;
	}

	@Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
}
