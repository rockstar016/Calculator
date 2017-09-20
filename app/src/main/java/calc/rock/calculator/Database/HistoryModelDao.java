package calc.rock.calculator.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by kkkkk on 18/09/2017.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface HistoryModelDao {

    @Query("select * from HistoryModel")
    LiveData<List<HistoryModel>> getAllHistoryItems();

    @Query("select * from HistoryModel where id = :id")
    HistoryModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addHistory(HistoryModel historyModel);

    @Delete
    void deleteHistory(HistoryModel historyModel);

}
