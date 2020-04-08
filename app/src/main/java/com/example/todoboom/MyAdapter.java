package com.example.todoboom;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<TodoItem> todoItems;
    private static final String TAG = "TodoRecyclerAdapter";
    private OnTodoListener mOnTodoListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<TodoItem> todoItemsArray, OnTodoListener onTodoListener) {
        this.todoItems = todoItemsArray;
        this.mOnTodoListener = onTodoListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v, mOnTodoListener);
        return vh;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        TextView textView;
        OnTodoListener mOnTodoListener;
        MyViewHolder(View v, OnTodoListener onTodoListener) {
            super(v);
            textView = v.findViewById(R.id.todo_title);
            mOnTodoListener = onTodoListener;
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnTodoListener.onTodoClick(getAdapterPosition());
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(todoItems.get(position).description);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public interface OnTodoListener{
        void onTodoClick(int position);
    }
}