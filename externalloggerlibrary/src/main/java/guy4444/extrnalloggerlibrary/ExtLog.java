package guy4444.extrnalloggerlibrary;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logs_table")
public class ExtLog implements Comparable<ExtLog> {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "time")
    private long time = System.currentTimeMillis();

    @ColumnInfo(name = "tag")
    private String tag;

    @ColumnInfo(name = "text")
    private String text;

    public ExtLog(@NonNull String tag, String text) {
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

    public void setTag(@NonNull String tag) {
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
    public int compareTo(ExtLog o) {
        return this.time > o.time ? 1 : -1;
    }
}