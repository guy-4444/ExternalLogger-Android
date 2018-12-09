package guy4444.externallogger;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import guy4444.externallogger.LoggerDB.Log;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static Log addLogToDB(final AppDatabase db, Log log) {
        db.logDao().insertAll(log);
        return log;
    }

    private static void populateWithTestData(AppDatabase db) {
        Log log = new Log(); log.setType("Click");log.setTime(System.currentTimeMillis());log.setText("Button Clicked");addLogToDB(db, log);

        List<Log> logList = db.logDao().getAll();
        android.util.Log.d(DatabaseInitializer.TAG, "Rows Count: " + logList.size());
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDb.logDao().deleteAll();
            populateWithTestData(mDb);


            List<Log> logs = mDb.logDao().getAll();
            Collections.sort(logs);
            android.util.Log.d("pttt", "SIZE= " + logs.size());
            for (int i = 0; i < logs.size(); i++) {
                android.util.Log.d("pttt", logs.get(i).getUid() + ". " + logs.get(i).getTime() + " - " + logs.get(i).getType() + " " + logs.get(i).getText());
            }

            android.util.Log.d("pttt", "- - - - - - - - - ----");

            List<Log> selective_logs = mDb.logDao().getAll("Guy");
            for (int i = 0; i < selective_logs.size(); i++) {
                android.util.Log.d("pttt", selective_logs.get(i).getUid() + ". " + selective_logs.get(i).getType() + " " + selective_logs.get(i).getText());
            }
            return null;
        }
    }
}