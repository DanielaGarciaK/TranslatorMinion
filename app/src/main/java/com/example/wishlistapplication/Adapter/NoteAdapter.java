package com.example.wishlistapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wishlistapplication.FragmenController;
import com.example.wishlistapplication.Model.NoteCard;
import com.example.wishlistapplication.R;

import com.example.wishlistapplication.ui.List.NoteDetailFragment;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<NoteCard> mnotes;

    private OnNoteListener listener;

    public NoteAdapter(ArrayList<NoteCard> notes) {
        mnotes = notes;

    }

    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_list_item, parent, false);
        return new NoteAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.title.setText(mnotes.get(position).getTitle());
        viewHolder.transl.setText(mnotes.get(position).getTranslartion());
        viewHolder.note.setText(mnotes.get(position).getNotes());
        /*viewHolder.base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmenController.getInstance().ChangeFragment(NoteDetailFragment.newInstance(mnotes.get(position)));
            }
        });*/
    }


    public int getItemCount() {
        return mnotes.size();
    }

    public void setNotes(ArrayList<NoteCard> notes) {
        mnotes = notes;
        notifyDataSetChanged();
    }

    public NoteCard getNoteAt(int position) {
        return mnotes.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout base;
        TextView title;
        TextView transl;
        TextView note;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newword);
            transl = itemView.findViewById(R.id.navigationtranslate);
            base = itemView.findViewById(R.id.parent_layout);
            note = itemView.findViewById(R.id.extranote);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onNoteClick(mnotes.get(position));
                }
            });
        }

    }

    public interface OnNoteListener {
        void onNoteClick(NoteCard note);
    }

    public void setOnNoteClickListener(OnNoteListener listener) {
        this.listener = listener;
    }
}
