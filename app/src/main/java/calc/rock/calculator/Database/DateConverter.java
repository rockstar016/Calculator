package calc.rock.calculator.Database;

/**
 * Created by kkkkk on 18/09/2017.
 */

import android.arch.persistence.room.TypeConverter;

import java.util.Date;
class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}