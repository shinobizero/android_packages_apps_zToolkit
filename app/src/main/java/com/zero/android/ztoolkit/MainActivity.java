package com.zero.android.ztoolkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	Context context = getApplicationContext();
	copyFile(context);
        LaunchTerminalEmulator term = new LaunchTerminalEmulator();
        term.execute();
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
		  }
		} 

    private class LaunchTerminalEmulator extends AsyncTask<Void, Void, Void> {
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
}
