package guy4444.extrnalloggerlibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LogDao {

    @Query("SELECT * FROM logs_table")
    List<ExtLog> getAllLogs();

    @Query("SELECT * FROM logs_table WHERE time BETWEEN :start AND :end")
    List<ExtLog> getAllLogsBetweenDates(long start, long end);

    @Query("SELECT * FROM logs_table WHERE time > :start")
    List<ExtLog> getAllLogsFromDate(long start);


    @Query("SELECT * FROM logs_table where tag LIKE :tag")
    List<ExtLog> getAllLogsByTag(String tag);

    @Query("SELECT * FROM logs_table WHERE tag LIKE :tag AND time BETWEEN :start AND :end")
    List<ExtLog> getAllLogsByTagBetweenDates(long start, long end, String tag);

    @Query("SELECT * FROM logs_table WHERE tag LIKE :tag AND time > :start")
    List<ExtLog> getAllLogsByTagFromDate(long start, String tag);


    @Query("SELECT * FROM logs_table where tag LIKE :tag AND text LIKE :text")
    List<ExtLog> getAllLogsByTagAndText(String tag, String text);

    @Query("SELECT * FROM logs_table where tag LIKE :tag AND text LIKE :text AND time BETWEEN :start AND :end")
    List<ExtLog> getAllLogsByTagAndTextBetweenDates(long start, long end, String tag, String text);

    @Query("SELECT * FROM logs_table where tag LIKE :tag AND text LIKE :text AND time > :start")
    List<ExtLog> getAllLogsByTagAndTextFromDate(long start, String tag, String text);


    @Query("SELECT COUNT(*) from logs_table")
    int countLogs();

    @Query("DELETE FROM logs_table WHERE 1")
    void deleteAll();

    @Insert
    void insertAll(ExtLog... extLogs);

    @Delete
    void delete(ExtLog extLog);
}