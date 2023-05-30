package com.esraa.hp.recyclerviewviewtypes;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model> models;
    Context context;
    MediaPlayer player;
    private boolean fabStateVolume ;

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.type);

        }

    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.type);
            this.image = itemView.findViewById(R.id.background);

        }

    }

    public static class AudioTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        FloatingActionButton fab;

        public AudioTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.type);
            this.fab = itemView.findViewById(R.id.fab);

        }

    }

    public MultiViewTypeAdapter(ArrayList<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type, parent, false);
                return new TextTypeViewHolder(view);
            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type, parent, false);
                return new ImageTypeViewHolder(view);
            case Model.AUDIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_type, parent, false);
                return new AudioTypeViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (models.get(position).getType()) {
            case 0:
                return Model.TEXT_TYPE;
            case 1:
                return Model.IMAGE_TYPE;
            case 2:
                return Model.AUDIO_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        Model model = models.get(listPosition);
            switch (model.getType()) {
                case Model.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).txtType.setText(model.getText());

                    break;
                case Model.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).txtType.setText(model.getText());
                    ((ImageTypeViewHolder) holder).image.setImageResource(model.getData());
                    break;
                case Model.AUDIO_TYPE:

                    ((AudioTypeViewHolder) holder).txtType.setText(model.getText());


                    ((AudioTypeViewHolder) holder).fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (fabStateVolume) {
                                if (player.isPlaying()) {
                                    player.stop();

                                }
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.volume);
                                fabStateVolume = false;

                            } else {
                                player = MediaPlayer.create(context, R.raw.sound);
                                player.setLooping(true);
                                player.start();
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.mute);
                                fabStateVolume = true;
                            }
                        }
                    });


                    break;
            }
        }



    @Override
    public int getItemCount() {
        return models.size();
    }


}
