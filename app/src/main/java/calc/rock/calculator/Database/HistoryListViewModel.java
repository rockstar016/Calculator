package calc.rock.calculator.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by kkkkk on 18/09/2017.
 */

public class HistoryListViewModel extends AndroidViewModel {

    private final LiveData<List<HistoryModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public HistoryListViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        itemAndPersonList = appDatabase.itemAndPersonModel().getAllHistoryItems();
        Log.d("HISTORYLISTVIEWMODEL", "ERROR IS HERE");
    }


    public LiveData<List<HistoryModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(HistoryModel historyModel) {
        new deleteAsyncTask(appDatabase).execute(historyModel);
    }

    private static class deleteAsyncTask extends AsyncTask<HistoryModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final HistoryModel... params) {
            db.itemAndPersonModel().deleteHistory(params[0]);
            return null;
        }

    }

    public void addHistory(final HistoryModel historyModel) {
        new addAsyncTask(appDatabase).execute(historyModel);
    }

    private static class addAsyncTask extends AsyncTask<HistoryModel, Void, Void> {

        private AppDatabase db;
        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }
        @Override
        protected Void doInBackground(final HistoryModel... params) {
            db.itemAndPersonModel().addHistory(params[0]);
            return null;
        }
    }

}
