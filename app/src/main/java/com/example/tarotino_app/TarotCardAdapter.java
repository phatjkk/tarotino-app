package com.example.tarotino_app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TarotCardAdapter extends RecyclerView.Adapter<TarotCardAdapter.ViewHolder> {

    /**
     * Lớp nắm giữ cấu trúc view
     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;

        public final TextView cardName;
        public final TextView cardDetail;
        public final Button detail_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            cardName = (TextView)itemView.findViewById(R.id.card_name_item);
            cardDetail = (TextView)itemView.findViewById(R.id.card_detail);
            detail_button = (Button)itemView.findViewById(R.id.detail_button);

            //Xử lý khi nút Chi tiết được bấm
            detail_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                                    cardName.getText() +" | "
                                            + " Demo function", Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }

        public View getItemview() {
            return itemview;
        }

        public TextView getCardName() {
            return cardName;
        }

        public TextView getCardDetail() {
            return cardDetail;
        }
    }
    //Dữ liệu hiện thị là danh sách sinh viên
    private List mTarotCards;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public TarotCardAdapter(List _card, Context mContext) {
        this.mTarotCards = _card;
        this.mContext = mContext;
    }

    @Override
    public TarotCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View studentView =
                inflater.inflate(R.layout.card_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(studentView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TarotCard card = (TarotCard) mTarotCards.get(position);

        holder.cardName.setText(card.getCardName());
        holder.cardDetail.setText(card.getCardDetail()+"");
    }

//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        TarotCard card = (TarotCard) mTarotCards.get(position);
////        holder.get
//        holder.getCardName.setText(card.getCardName());
//        holder.itemView.setText(card.getCardDetail()+"");
//    }


    @Override
    public int getItemCount() {
        return mTarotCards.size();
    }



}