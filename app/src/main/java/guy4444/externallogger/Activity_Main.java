package guy4444.externallogger;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import guy4444.externallogger.LoggerDB.Log;
import guy4444.externallogger.LoggerDB.MyLoggerDB;

public class Activity_Main extends AppCompatActivity {

    private TextView text;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private long lastCreateDate = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("External Logger Demo App");

        text = (TextView) findViewById(R.id.text);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        text.setMovementMethod(LinkMovementMethod.getInstance());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log log = new Log("Click", "Button Clicked");
                MyLoggerDB.getInstance().addLogToDB(log, new MyLoggerDB.LoggerDBCallBack_OnCompleted() {
                    @Override
                    public void onCompleted() {
                        MyLoggerDB.getInstance().getAllLogs(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                            @Override
                            public void logsReturned(List<Log> logs) {
                                updateText(logs);
                            }
                        });
                    }
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLoggerDB.getInstance().getAllLogsByTag("Click", new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                    @Override
                    public void logsReturned(List<Log> logs) {
                        updateText(logs);
                    }
                });
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLoggerDB.getInstance().getAllLogsByTag("Fab", new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                    @Override
                    public void logsReturned(List<Log> logs) {
                        updateText(logs);
                    }
                });
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLoggerDB.getInstance().getAllLogsFromDate(lastCreateDate, new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                    @Override
                    public void logsReturned(List<Log> logs) {
                        updateText(logs);
                    }
                });
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log log = new Log("fab", "Button Clicked");
                MyLoggerDB.getInstance().addLogToDB(log, new MyLoggerDB.LoggerDBCallBack_OnCompleted() {
                    @Override
                    public void onCompleted() {
                        MyLoggerDB.getInstance().getAllLogs(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                            @Override
                            public void logsReturned(List<Log> logs) {
                                updateText(logs);
                            }
                        });
                    }
                });
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLoggerDB.getInstance().deleteAll();
                updateText(null);
                Snackbar.make(view, "All data removed", Snackbar.LENGTH_LONG).setAction("got It", null).show();
            }
        });

        MyLoggerDB.getInstance().getAllLogs(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
            @Override
            public void logsReturned(List<Log> logs) {
                updateText(logs);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateText(final List<Log> logs) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
                String str = "";
                if (logs != null) {
                    Collections.sort(logs);
                    android.util.Log.d("pttt", "SIZE= " + logs.size());
                    for (int i = 0; i < logs.size(); i++) {
                        str += logs.get(i).getUid()
                                + ". "
                                + sdf.format(logs.get(i).getTime())
                                + " - "
                                + logs.get(i).getTag()
                                + " "
                                + logs.get(i).getText()
                                + "\n";
                    }
                }
                text.setText(str);
            }
        });
    }
}
