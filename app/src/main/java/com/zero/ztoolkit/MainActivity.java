package com.zero.ztoolkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.res.AssetManager;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.topjohnwu.superuser.Shell;

public class MainActivity extends Activity {

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	// Start Up Functions
        CheckForRoot();
	Context context = getApplicationContext();
	copyFile(context);
	setScriptsPermissions();

        setContentView(R.layout.activity_wheel);

	// Create Buttons
        toolButton01();
        toolButton02();
        toolButton03();
        toolButton04();
        toolButton05();


    }

    private void CheckForRoot() {
        try {
                    Shell.getShell();
        } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,
                    	    "ERROR: Unable to get root access!", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyFile(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
		    InputStream in = assetManager.open("dummy.sh");

		    //Creating an internal dir;
		    File APP_PATH = context.getFilesDir();
		    String scripts = "scripts";
   		            File APP_SCRIPTS_PATH = new File(APP_PATH, scripts);
  		            APP_SCRIPTS_PATH.mkdirs(); // this line creates files/script directory

		    // Copy scriptfile to APP_SCRIPTS_PATH
		    String scriptFile = "dummy.sh";
		    OutputStream out = new FileOutputStream(APP_SCRIPTS_PATH + "/" + scriptFile);

		    byte[] buffer = new byte[1024];
		    int read = in.read(buffer);
		    while (read != -1) {
		        out.write(buffer, 0, read);
		        read = in.read(buffer);
		       }
		    } catch (Exception e) {
		    e.getMessage();
                    Toast.makeText(MainActivity.this,
                    	    "ERROR: Unable to copy assets to internal directory!", Toast.LENGTH_SHORT).show();
		  }
		} 

    private void setScriptsPermissions() {
	Shell.Sync.su("chmod 777 /data/data/com.zero.ztoolkit/files/scripts/*");
	Shell.Sync.su("chmod a+x /data/data/com.zero.ztoolkit/files/scripts/*");
    }

    private class LaunchDummyScript extends AsyncTask<Void, Void, Void> {
        private static final String INITIAL_COMMAND = "/data/data/com.zero.ztoolkit/files/scripts/dummy.sh";
        private static final String INITIAL_COMMAND_PROPERTY = "com.zero.zterm.iInitialCommand";
        private static final String REMOTE_INTENT = "com.zero.zterm.RUN_SCRIPT_SU";
        private static final String INTENT_CATEGORY = "android.intent.category.DEFAULT";

        @Override
        protected Void doInBackground(Void... params) {
            Intent intent = new Intent(REMOTE_INTENT);
            intent.addCategory(INTENT_CATEGORY);
            intent.putExtra(INITIAL_COMMAND_PROPERTY, INITIAL_COMMAND);
            startActivity(intent);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {finishAndRemoveTask();}
    }

    public void toolButton01() {

        imageButton = (ImageButton) findViewById(R.id.imageButton01);
        final LaunchDummyScript term = new LaunchDummyScript();

        imageButton.setOnClickListener(new OnClickListener() {
        
            @Override
            public void onClick(View arg0) {

                term.execute();

            }
        });
    }
        public void toolButton02() {

            imageButton = (ImageButton) findViewById(R.id.imageButton02);

            imageButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Toast.makeText(MainActivity.this,
                            "This tool is currently unavailable!", Toast.LENGTH_SHORT).show();

                }
            });
    }
    public void toolButton03() {

        imageButton = (ImageButton) findViewById(R.id.imageButton03);

        imageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(MainActivity.this,
                        "This tool is currently unavailable!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void toolButton04() {

        imageButton = (ImageButton) findViewById(R.id.imageButton04);

        imageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(MainActivity.this,
                        "This tool is currently unavailable!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void toolButton05() {

        imageButton = (ImageButton) findViewById(R.id.imageButton05);

        imageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(MainActivity.this,
                        "This tool is currently unavailable!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
