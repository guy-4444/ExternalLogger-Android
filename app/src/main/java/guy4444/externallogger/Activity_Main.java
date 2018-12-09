package guy4444.externallogger;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
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

    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);

        text.setMovementMethod(LinkMovementMethod.getInstance());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thread.start();

                Log log = new Log(); log.setType("Click");log.setTime(System.currentTimeMillis());log.setText("Button Clicked");
                MyLoggerDB.getInstance().addLogToDB(log, new MyLoggerDB.LoggerDBCallBack_OnCompleted() {
                    @Override
                    public void onCompleted() {
                        MyLoggerDB.getInstance().getAll(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                            @Override
                            public void logsReturned(List<Log> logs) {
                                updateText(logs);
                            }
                        });
                    }
                });

                MyLoggerDB.getInstance().getAll(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
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
                MyLoggerDB.getInstance().deleteAll();
                updateText(null);
                Snackbar.make(view, "All data removed", Snackbar.LENGTH_LONG).setAction("got It", null).show();
            }
        });

        MyLoggerDB.getInstance().getAll(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
            @Override
            public void logsReturned(List<Log> logs) {
                updateText(logs);
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
                if (logs != null) {
                    Collections.sort(logs);
                    android.util.Log.d("pttt", "SIZE= " + logs.size());
                    for (int i = 0; i < logs.size(); i++) {
                        str += logs.get(i).getUid() + ". " + logs.get(i).getTime() + " - " + logs.get(i).getType() + " " + logs.get(i).getText() + "\n";
                    }
                }
                text.setText(str);
            }
        });
    }
}
