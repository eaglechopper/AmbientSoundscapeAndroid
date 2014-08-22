package com.eaglechopper.ambientsoundscape;






import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import au.com.eaglechopper.ambientsoundscape.R;

import com.eaglechopper.errorhandle.AppLog;
import com.eaglechopper.errorhandle.DebugLog;
import com.eaglechopper.features.ActionManager;
import com.eaglechopper.features.OptionsMenu;
import com.eaglechopper.info.CreditActivity;
import com.eaglechopper.player.AmbientPlayer;
import com.eaglechopper.player.PlayerID;
import com.eaglechopper.sound.Sound;
import com.eaglechopper.sound.SoundAdapter;

public class MainActivity extends FragmentActivity implements OnItemClickListener, 
OnClickListener, OnSeekBarChangeListener, OnTimeSetListener, DebugLog, 
OnItemLongClickListener
{
	
	public static final int maxSeek = 100;
	public static int num_players;
	private ActionManager action;
	
	
	//Features
	
	
	//UI arrays
	private SeekBar[] seeks;
	private TextView[] texts;
	private Button[] buttons;

	private ListView list;
	private SoundAdapter soundAdapt;
	//Navigation stuff
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle = "Navgate";
    
    private String[] mPlanetTitles;
    
    private NavAdapter navAdapt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navdraw);
		num_players = PlayerID.values().length;
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		action = ActionManager.getInstance(this);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_gradient));
		initUI();
		//initAmbientManager();
		initNav();
		
		initSounds();
		
		//initFeatures();
	
		
	}
	private void initNav()
	{
		
		mPlanetTitles = new String[3];
		mPlanetTitles[0] = "Pizza";
		mPlanetTitles[1] = "Melbourne";
		mPlanetTitles[2] = "Trains";
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        List<NavItem> navContent = new ArrayList<NavItem>();
        navContent.add(new NavItem("Dynamic Soundscape Engine"));
        navContent.add(new NavItem("Timer"));
       
		navAdapt = new NavAdapter(this, android.R.layout.simple_list_item_1, navContent );
		mDrawerList.setAdapter(navAdapt);
		
		
       // mDrawerList.setAdapter(new ArrayAdapter<String>(this,
         //       android.R.layout.simple_list_item_1, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(this);
        
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(getTitle());
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
            }
        };
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
	}
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

   
   

	private void initSounds()
	{	
		soundAdapt = new SoundAdapter(this, android.R.layout.simple_list_item_1, ActionManager.ambMngr.getSoundList().getSounds());
		list = (ListView) findViewById(R.id.listContent);
		list.setAdapter(soundAdapt);
		list.setOnItemClickListener(this);
		list.setOnItemLongClickListener(this);
		
		
	}
	private void initUI()
	{
		seeks = new SeekBar[num_players];
		texts = new TextView[num_players];
		buttons = new Button[num_players];
		
		//Init UI instances
		seeks[PlayerID.PLAYER_1.getID()] = (SeekBar) findViewById(R.id.seekTrack1);
		seeks[PlayerID.PLAYER_2.getID()] = (SeekBar) findViewById(R.id.seekTrack2);
		seeks[PlayerID.PLAYER_3.getID()] = (SeekBar) findViewById(R.id.seekTrack3);
		seeks[PlayerID.PLAYER_4.getID()] = (SeekBar) findViewById(R.id.seekTrack4);
		
		texts[PlayerID.PLAYER_1.getID()] = (TextView) findViewById(R.id.track1);
		texts[PlayerID.PLAYER_2.getID()] = (TextView) findViewById(R.id.track2);
		texts[PlayerID.PLAYER_3.getID()] = (TextView) findViewById(R.id.track3);
		texts[PlayerID.PLAYER_4.getID()] = (TextView) findViewById(R.id.track4);
		
		buttons[PlayerID.PLAYER_1.getID()] = (Button) findViewById(R.id.stop1);
		buttons[PlayerID.PLAYER_2.getID()] = (Button) findViewById(R.id.stop2);
		buttons[PlayerID.PLAYER_3.getID()] = (Button) findViewById(R.id.stop3);
		buttons[PlayerID.PLAYER_4.getID()] = (Button) findViewById(R.id.stop4);
		
		//Set seekBar onSeek
		for(SeekBar bar : seeks)
		{
			bar.setMax(maxSeek);
			bar.setOnSeekBarChangeListener(this);
			bar.setProgress(maxSeek / 2);
		}
		//Set button onClick
		for(Button button : buttons)
		{
			button.setOnClickListener(this);
		}
		
	
		
		
	}
	//Interface methods below
	@Override
	public void onClick(View v)
	{
		if(ActionManager.ambMngr == null)
			return;
		AmbientPlayer player = null;
		switch(v.getId())
		{
		case R.id.stop1:
			player = ActionManager.ambMngr.stopPlayer(PlayerID.PLAYER_1.getID());
			break;
		case R.id.stop2:
			player = ActionManager.ambMngr.stopPlayer(PlayerID.PLAYER_2.getID());
			break;
		case R.id.stop3:
			player = ActionManager.ambMngr.stopPlayer(PlayerID.PLAYER_3.getID());
			break;
		case R.id.stop4:
			player = ActionManager.ambMngr.stopPlayer(PlayerID.PLAYER_4.getID());
			break;
		}
		//player is refernce to player that was stopped
		if(player != null)
		{
			setTrackText(player.getPlayerID(), "...");
			resetSeek(player.getPlayerID());
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onItemClick(AdapterView<?> av, View v, int pos, long l)
	{
		if(av.getId() == list.getId())
		{
			Sound sound = soundAdapt.getItem(pos);
			AmbientPlayer player = ActionManager.ambMngr.playSound(sound);
			if(player != null)
				setTrackText(player.getPlayerID(), sound.getName());
			//Toast.makeText(this, sound.toString(), Toast.LENGTH_SHORT).show()
		}
		else if(av.getId() == mDrawerList.getId())
		{
			Toast.makeText(this, "Drawer item: " + pos,Toast.LENGTH_SHORT).show();
			OptionsMenu.setUpTimer(this, this, ActionManager.timer);
			
		}
		
			
	}
	

	@Override
	public boolean onItemLongClick(AdapterView<?> adapt, View view, int pos,
			long arg3) 
	{
		if(adapt.getId() == list.getId())
		{
			//Toast.makeText(this, "Favorited " + soundAdapt.getItem(pos),Toast.LENGTH_SHORT).show();
			//ActionManager.ambMngr.sort();
			//soundAdapt.notifyDataSetChanged();
			return true;
		}
		else
		{
			return false;
		}
		
		
		
		
		
	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if(ActionManager.ambMngr != null)
			ActionManager.ambMngr.releaseAllPlayers();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
		switch(item.getItemId())
		{
		case R.id.appInfo:
			OptionsMenu.showAppInfo(this, ActionManager.timer);
			break;
		case R.id.appTimer:
			OptionsMenu.setUpTimer(this, this, ActionManager.timer);
			break;
		case R.id.betaMessage:
			ActionManager.news.firstTimeMessage();
			break;
		case R.id.creditsMenu:
			Intent intent = new Intent(this, CreditActivity.class);
			startActivity(intent);
			break;
		case R.id.showcat:
			Sound.toggleShowCat(this);
			soundAdapt.notifyDataSetChanged();
			break;
		case R.id.sortByCat:
			Sound.toggleSortByCat(this);
			ActionManager.ambMngr.sort();
			soundAdapt.notifyDataSetChanged();
			break;
		}
		if (mDrawerToggle.onOptionsItemSelected(item))
		{
	          return true;
	    }
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar){

	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTimeSet(TimePicker arg0, int hour, int min)
	{
		if(hour == 0 && min == 0)
			return;
		else
			ActionManager.timer.setUpTimer(hour, min);
	}
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser)
	{
		if(ActionManager.ambMngr == null)
			return;
		switch(seekBar.getId())
		{
		case R.id.seekTrack1:
			ActionManager.ambMngr.setVolume(PlayerID.PLAYER_1.getID(), progress);
			break;
		case R.id.seekTrack2:
			ActionManager.ambMngr.setVolume(PlayerID.PLAYER_2.getID(), progress);
			break;
		case R.id.seekTrack3:
			ActionManager.ambMngr.setVolume(PlayerID.PLAYER_3.getID(), progress);
			break;
		case R.id.seekTrack4:
			ActionManager.ambMngr.setVolume(PlayerID.PLAYER_4.getID(), progress);
			break;
		}
	}
	public void setTrackText(PlayerID id, String text)
	{
		texts[id.getID()].setText(text);
	}
	public void resetSeek(PlayerID id)
	{
		seeks[id.getID()].setProgress(maxSeek / 2);
	}
	@Override
	public void debugLog()
	{
		AppLog.info(AppLog.TAG_APPLOG_REPORT, "debug log for MainActivity");
		if(ActionManager.ambMngr == null)
			AppLog.debug(AppLog.TAG_NULL, "AmbientManager manager= null");
		if(soundAdapt == null)
			AppLog.debug(AppLog.TAG_NULL, "SoundAdapt soundAdapt= null");
		if(ActionManager.ambMngr == null)
			AppLog.debug(AppLog.TAG_NULL, "telephony= null");
		if(ActionManager.ambMngr == null)
			AppLog.debug(AppLog.TAG_NULL, "phoneStatus= null");
		if(ActionManager.news == null)
			AppLog.debug(AppLog.TAG_NULL, "news= null");
		if(ActionManager.timer == null)
			AppLog.debug(AppLog.TAG_NULL, "timer= null");
		if(list == null)
			AppLog.debug(AppLog.TAG_NULL, "list= null");
		
		for(int i=0;i<seeks.length;i++)
		{
			if(seeks[i] == null)
				AppLog.debug(AppLog.TAG_NULL, "seek: "+ i +"= null");
		}
		for(int i=0;i<texts.length;i++)
		{
			if(texts[i] == null)
				AppLog.debug(AppLog.TAG_NULL, "text: "+ i +"= null");
		}
		for(int i=0;i<buttons.length;i++)
		{
			if(buttons[i] == null)
				AppLog.debug(AppLog.TAG_NULL, "button: "+ i +"= null");
		}
	}
	public void stringToNotify(String string)
	{
		ActionManager.news.stringToNotify(string);
	}
	public void closeThreads()
	{
		ActionManager.ambMngr.stopAllThreads();
	}
	
	
	

	

}
