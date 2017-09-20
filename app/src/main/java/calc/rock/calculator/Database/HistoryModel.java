package calc.rock.calculator.Database;

/**
 * Created by kkkkk on 18/09/2017.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
@Entity(tableName = "HistoryModel")
public class HistoryModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String historyName;
    @TypeConverters(DateConverter.class)
    private Date historyDate;
    private String equation;
    private String result;

    public HistoryModel(String historyName, Date historyDate, String equation, String result) {
        this.historyName = historyName;
        this.historyDate = historyDate;
        this.equation = equation;
        this.result = result;
    }

    public String getHistoryName() {
        return historyName;
    }
    public Date getHistoryDate() {
        return historyDate;
    }
    public String getEquation() {
        return equation;
    }
    public String getResult() {
        return result;
    }
}
