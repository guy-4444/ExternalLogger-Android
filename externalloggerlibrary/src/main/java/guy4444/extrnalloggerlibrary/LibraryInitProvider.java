package guy4444.extrnalloggerlibrary;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.Nullable;

public final class LibraryInitProvider extends ContentProvider {

    public LibraryInitProvider() {
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        MyLoggerDB.initHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

/*
V1.00.03:
Auto init with Content Provider
Improve 'New Log' event methods

 */