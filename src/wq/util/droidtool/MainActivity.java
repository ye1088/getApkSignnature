package wq.util.droidtool;

import wq.util.droidtool.adapter.AppAdapter;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends Activity {

	ListView lvapp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    
    
    private void init(){
    	lvapp = (ListView) findViewById(R.id.lv_applist);
    	AppAdapter aadpater = new AppAdapter(this);
    	lvapp.setAdapter(aadpater);
    	lvapp.setOnItemClickListener(aadpater.new AppClickListener());
    }
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
