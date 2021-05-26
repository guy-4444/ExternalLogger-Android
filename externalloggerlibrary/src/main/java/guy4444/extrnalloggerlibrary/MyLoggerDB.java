package guy4444.extrnalloggerlibrary;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;


@Database(entities = {ExtLog.class}, version = 1)
public abstract class MyLoggerDB extends RoomDatabase {

    public interface LoggerDBCallBack_LogsReturned {
        void logsReturned(List<ExtLog> extLogs);
    }

    public interface LoggerDBCallBack_OnCompleted {
        void onCompleted();
    }

    private static MyLoggerDB instance;
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

    public void addLogToDB(@NonNull final String tag, final String text) {
        if (tag != null) {
            addLogToDB(new ExtLog(tag, text));
        }
    }

    public void addLogToDB(@NonNull final String tag, final String text, final LoggerDBCallBack_OnCompleted loggerDBCallBack_onCompleted) {
        if (tag != null) {
            addLogToDB(new ExtLog(tag, text), loggerDBCallBack_onCompleted);
        }
    }

    public void addLogToDB(@NonNull final ExtLog extLog) {
        if (extLog != null) {
            addLogToDB(extLog, null);
        }
    }

    public void addLogToDB(@Nullable final ExtLog extLog, final LoggerDBCallBack_OnCompleted loggerDBCallBack_onCompleted) {
        if (extLog != null) {
            new Thread(new Runnable() {
                public void run() {
                    logDao().insertAll(extLog);
                    if (loggerDBCallBack_onCompleted != null) {
                        loggerDBCallBack_onCompleted.onCompleted();
                    }
                }
            }).start();
        }
    }

    public void deleteAll() {
        new Thread(new Runnable() {
            public void run() {
                logDao().deleteAll();
            }
        }).start();
    }

    public void getAllLogs(final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAllLogs());
                }
            }
        }).start();
    }

    public void getAllLogsBetweenDates(final long start, final long end, final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAllLogsBetweenDates(start, end));
                }
            }
        }).start();
    }

    public void getAllLogsFromDate(final long start, final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAllLogsFromDate(start));
                }
            }
        }).start();
    }

    public void getAllLogsByTag(final String tag, final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAllLogsByTag(tag));
                }
            }
        }).start();
    }

    public void getAllLogsByTagBetweenDates(final long start, final long end, final String tag, final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAllLogsByTagBetweenDates(start, end, tag));
                }
            }
        }).start();
    }

    public void getAllLogsByTagFromDate(final long start, final String tag, final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAllLogsByTagFromDate(start, tag));
                }
            }
        }).start();
    }

    public void getAllLogsByTagAndText(final String tag, final String text, final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAllLogsByTagAndText(tag, text));
                }
            }
        }).start();
    }

    public void getAllLogsByTagAndTextBetweenDates(final long start, final long end, final String tag, final String text, final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAllLogsByTagAndTextBetweenDates(start, end, tag, text));
                }
            }
        }).start();
    }

    public void getAllLogsByTagAndTextFromDate(final long start, final String tag, final String text, final LoggerDBCallBack_LogsReturned loggerDBCallBack_logsReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (loggerDBCallBack_logsReturned != null) {
                    loggerDBCallBack_logsReturned.logsReturned(logDao().getAllLogsByTagAndTextFromDate(start, tag, text));
                }
            }
        }).start();
    }

}
