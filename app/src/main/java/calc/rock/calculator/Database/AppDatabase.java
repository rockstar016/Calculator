package calc.rock.calculator.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by kkkkk on 18/09/2017.
 */

@Database(entities = {HistoryModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ttaa")
                            .build();
        }
        return INSTANCE;
    }

    public abstract HistoryModelDao itemAndPersonModel();

}