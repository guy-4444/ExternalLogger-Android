package guy4444.externallogger.LoggerDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LogDao {

    @Query("SELECT * FROM logs_table")
    List<Log> getAll();

    @Query("SELECT * FROM logs_table where type LIKE  :type")
    List<Log> getAll(String type);

    @Query("SELECT * FROM logs_table where type LIKE  :type AND text LIKE :text")
    Log findByName(String type, String text);

    @Query("SELECT COUNT(*) from logs_table")
    int countLogs();

    @Query("DELETE FROM logs_table WHERE 1")
    void deleteAll();

    @Insert
    void insertAll(Log... logs);

    @Delete
    void delete(Log log);
}