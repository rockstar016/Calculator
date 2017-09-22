package calc.rock.calculator;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

import calc.rock.calculator.Database.HistoryListViewModel;
import calc.rock.calculator.Database.HistoryModel;

/**
 * Created by kkkkk on 18/09/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<HistoryModel> historyModelList;


    public RecyclerViewAdapter(List<HistoryModel> historyModelList) {
        this.historyModelList = historyModelList;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        HistoryModel historyModel = historyModelList.get(position);
        holder.initView(historyModel, position);
    }

    @Override
    public int getItemCount() {
        return historyModelList.size();
    }

    public void addItems(List<HistoryModel> historyModelList) {
        this.historyModelList = historyModelList;
        notifyDataSetChanged();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private EditText historyName;
        private TextView historyDate;
        private TextView equation;
        private TextView result;
        private ImageButton btAdd, btRemove;
        HistoryModel model;
        HistoryListViewModel addHistoryViewModel, deleteHistoryViewModel;
        String stringDate;
        int position;

        RecyclerViewHolder(View view) {
            super(view);
            historyName = (EditText)view.findViewById(R.id.historyName);
            historyName.setEnabled(false);
            historyDate = (TextView) view.findViewById(R.id.historyDate);
            equation = (TextView)view.findViewById(R.id.equation);
            result = (TextView)view.findViewById(R.id.result);
            btAdd = (ImageButton)view.findViewById(R.id.add_history);
            btRemove = (ImageButton)view.findViewById(R.id.delete_history);
        }

        public void initView(HistoryModel model, int position)
        {
            this.model = model;
            this.position = position;
            stringDate = DateFormat.getDateTimeInstance().format(model.getHistoryDate());
            historyDate.setText(stringDate);
            historyName.setText(model.getHistoryName());
            equation.setText(model.getEquation());
            result.setText(model.getResult());
            btAdd.setVisibility(historyName.getText().toString().isEmpty()?View.VISIBLE:View.INVISIBLE);
            btAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editComment();
                }
            });
            //btRemove.setVisibility(historyName.getText().toString().isEmpty()?View.INVISIBLE:View.VISIBLE);
            btRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeModel();
                }
            });
        }

        public void removeModel()
        {
            historyModelList.remove(position);
            notifyDataSetChanged();
            notifyItemRangeChanged(position, historyModelList.size());
        }

        public void editComment()
        {
            if(historyName.isEnabled())
            {
                //save
                model = new HistoryModel(historyName.getText().toString(), model.getHistoryDate(), model.getEquation(), model.getResult());
                historyModelList.set(position, model);
                notifyDataSetChanged();
                //addHistoryViewModel.addHistory(new HistoryModel(historyName.getText().toString(), model.getHistoryDate(), model.getEquation(), model.getResult()));
                btAdd.setBackgroundResource(R.drawable.ic_add_black_24dp);
                historyName.setEnabled(false);
            }
            else
            {

                historyName.setEnabled(true);
                btAdd.setBackgroundResource(R.drawable.cpv_preset_checked);
            }
        }
    }
}
