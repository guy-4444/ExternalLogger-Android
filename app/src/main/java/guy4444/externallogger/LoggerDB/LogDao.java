package guy4444.externallogger.LoggerDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LogDao {

    @Query("SELECT * FROM logs_table")
    List<Log> getAllLogs();

    @Query("SELECT * FROM logs_table WHERE time BETWEEN :start AND :end")
    List<Log> getAllLogsBetweenDates(long start, long end);

    @Query("SELECT * FROM logs_table WHERE time > :start")
    List<Log> getAllLogsFromDate(long start);


    @Query("SELECT * FROM logs_table where tag LIKE :tag")
    List<Log> getAllLogsByTag(String tag);

    @Query("SELECT * FROM logs_table WHERE tag LIKE :tag AND time BETWEEN :start AND :end")
    List<Log> getAllLogsByTagBetweenDates(long start, long end, String tag);

    @Query("SELECT * FROM logs_table WHERE tag LIKE :tag AND time > :start")
    List<Log> getAllLogsByTagFromDate(long start, String tag);


    @Query("SELECT * FROM logs_table where tag LIKE :tag AND text LIKE :text")
    List<Log> getAllLogsByTagAndText(String tag, String text);

    @Query("SELECT * FROM logs_table where tag LIKE :tag AND text LIKE :text AND time BETWEEN :start AND :end")
    List<Log> getAllLogsByTagAndTextBetweenDates(long start, long end, String tag, String text);

    @Query("SELECT * FROM logs_table where tag LIKE :tag AND text LIKE :text AND time > :start")
    List<Log> getAllLogsByTagAndTextFromDate(long start, String tag, String text);


    @Query("SELECT COUNT(*) from logs_table")
    int countLogs();

    @Query("DELETE FROM logs_table WHERE 1")
    void deleteAll();

    @Insert
    void insertAll(Log... logs);

    @Delete
    void delete(Log log);
}