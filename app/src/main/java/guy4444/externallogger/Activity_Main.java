package guy4444.externallogger;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import guy4444.externallogger.LoggerDB.Log;
import guy4444.externallogger.LoggerDB.MyLoggerDB;

public class Activity_Main extends AppCompatActivity {

    AppDatabase appDatabase;
    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);

        //DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));

        appDatabase = AppDatabase.getAppDatabase(this);

        final Thread thread = new Thread(new Runnable() {
            public void run() {
                Log log = new Log(); log.setType("Click");log.setTime(System.currentTimeMillis());log.setText("Button Clicked");
                appDatabase.logDao().insertAll(log);
                List<Log> logs = appDatabase.logDao().getAll();
                updateText(logs);
            }
        });

        final Thread addThread = new Thread(new Runnable() {
            public void run() {

            }
        });

        final Thread printThread = new Thread(new Runnable() {
            public void run() {

            }
        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thread.start();

                Log log = new Log(); log.setType("Click");log.setTime(System.currentTimeMillis());log.setText("Button Clicked");
                MyLoggerDB.getInstance().addLogToDB(log);

                MyLoggerDB.getInstance().getAll(new MyLoggerDB.LoggerDBCallBack() {
                    @Override
                    public void logsReturned(List<Log> logs) {
                        updateText(logs);
                    }
                });
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateText(final List<Log> logs) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String str = "";
                Collections.sort(logs);
                android.util.Log.d("pttt", "SIZE= " + logs.size());
                for (int i = 0; i < logs.size(); i++) {
                    str += logs.get(i).getUid() + ". " + logs.get(i).getTime() + " - " + logs.get(i).getType() + " " + logs.get(i).getText() + "\n";
                }
                text.setText(str);
            }
        });
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
