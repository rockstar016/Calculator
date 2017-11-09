package calc.rock.calculator.Models;

/**
 * Created by kkkkk on 18/09/2017.
 */

import java.util.Date;
public class HistoryModel {

    public int id;
    private String historyName;
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
