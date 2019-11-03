package guy4444.externallogger;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import guy4444.extrnalloggerlibrary.ExtLog;
import guy4444.extrnalloggerlibrary.MyLoggerDB;
import guy4444.extrnalloggerlibrary.MyLoggerDB_Impl;

public class Activity_Main extends AppCompatActivity {

    private TextView data;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private FloatingActionButton fab_android;
    private FloatingActionButton fab_apple;
    private long lastCreateDate = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Toolbar mActionBarToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("External Logger Demo App");

        data = findViewById(R.id.data);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        fab_android = findViewById(R.id.fab_android);
        fab_apple = findViewById(R.id.fab_apple);

        data.setMovementMethod(LinkMovementMethod.getInstance());

        MyLoggerDB.getInstance().addLogToDB("Life", "Activity Created");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLoggerDB.getInstance().deleteAll();
                updateText(null);
                Snackbar.make(view, "All data removed", Snackbar.LENGTH_LONG).setAction("got It", null).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLoggerDB.getInstance().getAllLogsByTag("Android", new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                    @Override
                    public void logsReturned(List<ExtLog> logs) {
                        updateText(logs);
                    }
                });
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLoggerDB.getInstance().getAllLogsByTag("Apple", new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                    @Override
                    public void logsReturned(List<ExtLog> logs) {
                        updateText(logs);
                    }
                });
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLoggerDB.getInstance().getAllLogsFromDate(lastCreateDate, new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                    @Override
                    public void logsReturned(List<ExtLog> logs) {
                        updateText(logs);
                    }
                });
            }
        });

        fab_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExtLog log = new ExtLog("android", "Android Clicked");
                MyLoggerDB.getInstance().addLogToDB(log, new MyLoggerDB.LoggerDBCallBack_OnCompleted() {
                    @Override
                    public void onCompleted() {
                        MyLoggerDB.getInstance().getAllLogs(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                            @Override
                            public void logsReturned(List<ExtLog> logs) {
                                updateText(logs);
                            }
                        });
                    }
                });
            }
        });

        fab_apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExtLog log = new ExtLog("apple", "Apple Clicked");
                MyLoggerDB.getInstance().addLogToDB(log, new MyLoggerDB.LoggerDBCallBack_OnCompleted() {
                    @Override
                    public void onCompleted() {
                        MyLoggerDB.getInstance().getAllLogs(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                            @Override
                            public void logsReturned(List<ExtLog> logs) {
                                updateText(logs);
                            }
                        });
                    }
                });
            }
        });

        MyLoggerDB.getInstance().getAllLogs(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
            @Override
            public void logsReturned(List<ExtLog> logs) {
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
        if (id == R.id.action_about) {
            openAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openAbout() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/guy-4444/ExternalLogger-Android"));
        startActivity(browserIntent);
    }

    private void updateText(final List<ExtLog> logs) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
                String str = "";
                if (logs != null) {
                    Collections.sort(logs);
                    Log.d("pttt", "SIZE= " + logs.size());
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
                
                data.setText(str);
            }
        });
    }
}
