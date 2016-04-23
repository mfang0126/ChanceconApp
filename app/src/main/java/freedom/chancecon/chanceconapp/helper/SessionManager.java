package freedom.chancecon.chanceconapp.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Freedom on 23/04/16.
 */
public class SessionManager {
    private static final String TAG = SessionManager.class.getSimpleName();

    private final SharedPreferences preferences;

    private final SharedPreferences.Editor editor;
    private final Context _context;

    private final int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Login";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this._context = context;
        preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        editor.commit();

        Log.d(TAG,"User Login session modified!");
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}
