package guy4444.externallogger.LoggerDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "logs_table")
public class Log implements Comparable<Log> {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "time")
    private long time;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "text")
    private String text;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(Log o) {
        return this.time > o.time ? 1 : -1;
    }
}