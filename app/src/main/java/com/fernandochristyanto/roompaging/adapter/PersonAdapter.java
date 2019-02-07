package com.fernandochristyanto.roompaging.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernandochristyanto.roompaging.R;
import com.fernandochristyanto.roompaging.entity.Person;

public class PersonAdapter extends PagedListAdapter<Person, PersonAdapter.ViewHolder> {
    Context context;

    public PersonAdapter(Context context){
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_person, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder, int i) {
        Person person = getItem(i);
        if(person != null) viewHolder.bind(person);
    }

    private static DiffUtil.ItemCallback<Person> diffCallback = new DiffUtil.ItemCallback<Person>()  {

        @Override
        public boolean areItemsTheSame(@NonNull Person person, @NonNull Person t1) {
            return person.getId().equals(t1.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person person, @NonNull Person t1) {
            return person == t1;
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
        }

        public void bind(Person person){
            tvName.setText(person.getName());
        }
    }
}
