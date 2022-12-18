package com.example.tarotino_app;


import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TarotCardAdapter extends RecyclerView.Adapter<TarotCardAdapter.ViewHolder> {

    /**
     * Lớp nắm giữ cấu trúc view
     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;

        public final TextView cardName;
        public final TextView cardDetail;
        public final ImageView cardIMG;
        public final Button detail_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            cardName = (TextView)itemView.findViewById(R.id.card_name_item);
            cardDetail = (TextView)itemView.findViewById(R.id.card_detail);
            cardIMG = (ImageView)itemView.findViewById(R.id.img_card_item);
            detail_button = (Button)itemView.findViewById(R.id.detail_button);
            ContextWrapper contextWrapper = new ContextWrapper(mContext);
            Tarot tarot = new Tarot(contextWrapper);
            //Xử lý khi nút Chi tiết được bấm
            detail_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String cardJSON = tarot.getCardByPosition(getAdapterPosition());
                    Intent intent = new Intent(mContext, ViewCardActivity.class);
                    intent.putExtra("CardJSON", cardJSON);
                    mContext.startActivity(intent);
//                    Toast.makeText(view.getContext(),
//                                    cardName.getText() +" | "
//                                            + getAdapterPosition(), Toast.LENGTH_SHORT)
//                            .show();
                }
            });
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
        Matrix matrix = new Matrix();
        matrix.postRotate(180);


        TarotCard card = (TarotCard) mTarotCards.get(position);
        Bitmap bm = null;
        try {
            bm = getBitmapFromAsset(card.getCardImageURL());
            if(card.getCardDetail().contains("ngược")) {
                bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.cardIMG.setImageBitmap(bm);
        holder.cardName.setText(card.getCardName());
        holder.cardDetail.setText(card.getCardDetail()+"");
    }



    private Bitmap getBitmapFromAsset(String strName) throws IOException
    {
        AssetManager assetManager = mContext.getAssets();
        InputStream istr = assetManager.open(strName);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return mTarotCards.size();
    }



}