package com.shenkar.shakedzrihen.mobileExercise.birthdayList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenkar.shakedzrihen.mobileExercise.R;

import java.text.ParseException;
import java.util.List;

class BirthdayListAdapter extends RecyclerView.Adapter<BirthdayListAdapter.ViewHolder> {

    private List<BirthdayListItem> birthday;

    public BirthdayListAdapter(List<BirthdayListItem> birthday) {
        this.birthday = birthday;
    }

    @Override
    public BirthdayListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.birthday_row,
                parent,
                false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BirthdayListAdapter.ViewHolder holder, int position) {
        try {
            holder.fullName.setText(birthday.get(position).getFullName() + " (" + birthday.get(position).getAge() + ")");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.birthday.setText(birthday.get(position).getBirthday());
        holder.comment.setText(birthday.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return birthday.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fullName;
        public TextView birthday;
        public TextView comment;

        public ViewHolder(View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            birthday = itemView.findViewById(R.id.bday);
            comment = itemView.findViewById(R.id.description);
        }
    }
}
