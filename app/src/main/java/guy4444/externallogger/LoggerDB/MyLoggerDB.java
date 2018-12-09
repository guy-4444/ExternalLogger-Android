package guy4444.externallogger.LoggerDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.List;


@Database(entities = {Log.class}, version = 1)
public abstract class MyLoggerDB extends RoomDatabase {

    public interface LoggerDBCallBack_LogsReturned {
        public void logsReturned(List<Log> logs);
    }

    public interface LoggerDBCallBack_OnCompleted {
        public void onCompleted();
    }

    private static MyLoggerDB instance;
    private static Context appContext;
    public abstract LogDao logDao();

    public static MyLoggerDB getInstance() {
        return instance;
    }

    public static MyLoggerDB initHelper(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyLoggerDB.class, "logger-database")
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    // .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public void showToast(final String message) {
        // If we put it into handler - we can call in from asynctask outside of main uithread
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addLogToDB(final Log log, final LoggerDBCallBack_OnCompleted loggerDBCallBack_onCompleted) {
        new Thread(new Runnable() {
            public void run() {
                logDao().insertAll(log);
                if (loggerDBCallBack_onCompleted != null) {
                    loggerDBCallBack_onCompleted.onCompleted();
                }
            }
        }).start();
    }

    public void getAll(final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAll());
                }
            }
        }).start();
    }

    public void deleteAll() {
        new Thread(new Runnable() {
            public void run() {
                logDao().deleteAll();
            }
        }).start();
    }
}
