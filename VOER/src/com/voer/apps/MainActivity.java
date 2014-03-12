package com.voer.apps;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {
	SlidingMenu menu;
	EditText searchText;
	GetContent getContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showHome();
		getContent = new GetContent("http://dev.voer.vn:2013/1/materials/?format=json") {
			
			@Override
			void loadFailed() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			void loadCompleted() {
				// TODO Auto-generated method stub
				try {
					Log.e("count: ", getContent.getResponse().get("count").toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	private void setMenu() {
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		// menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.menu);
	}

	@Override
	public void onBackPressed() {
		if (menu.isMenuShowing()) {
			menu.toggle();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			this.menu.toggle();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.menu.toggle();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showHome() {
		setContentView(R.layout.activity_main);
		Display display = getWindowManager().getDefaultDisplay();
		setMenu();

		searchText = (EditText) findViewById(R.id.search_box);
		searchText.setWidth((int) (display.getWidth() * 0.9));
	}

	public void showHome(View v) {
		showHome();
	}

	public void showAbout(View v) {
		setContentView(R.layout.activity_about);
		setMenu();
	}

}
