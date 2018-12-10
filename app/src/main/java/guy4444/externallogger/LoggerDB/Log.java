package guy4444.externallogger.LoggerDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "logs_table")
public class Log implements Comparable<Log> {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "time")
    private long time = System.currentTimeMillis();

    @ColumnInfo(name = "tag")
    private String tag;

    @ColumnInfo(name = "text")
    private String text;

    public Log(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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