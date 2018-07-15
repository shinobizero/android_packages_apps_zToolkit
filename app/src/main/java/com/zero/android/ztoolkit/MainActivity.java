package com.zero.android.ztoolkit;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LaunchTerminalEmulator term = new LaunchTerminalEmulator();
        term.execute();
    }

    private class LaunchTerminalEmulator extends AsyncTask<Void, Void, Void> {
        private static final String INITIAL_COMMAND = "ztoolkit.sh";
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
