package com.example.homework12;

import static com.example.homework12.MessageType.LEFT_CONTENTS;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<Message> list;

    MessageAdapter(List<Message> list){
        this.list = list;
    }
    /*
        위의 list 변수에는 null이 초기화되어 있으므로 list에 값을 넣어줄 수 있는 생성자나 메소드 정의해야 함
    */

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (MessageType.values()[viewType]) {
            case LEFT_CONTENTS:
                return new MessageViewHolder(inflater.inflate(R.layout.message_left_item, parent, false));
            case RIGHT_CONTENTS:
                return new MessageViewHolder(inflater.inflate(R.layout.message_right_item, parent, false));
            default:
                return new MessageViewHolder(inflater.inflate(R.layout.message_center_item, parent, false));
        }
    }

    /*
        이 메소드를 구현하시오.
    */
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder viewHolder, int position) {
            viewHolder.setMessage(list.get(position));
    }

    /*
    이 메소드를 구현하시오.
    */
    @Override
    public int getItemCount() {
        return list.size();
    }

    // 이 메소드를 재정의 하면 onCreateViewHolder 메소드의 두번째 파라미터 viewType 변수에 이 메소드의 리턴값이 들어간다.
    @Override
    public int getItemViewType(int position) {
        // enum 타입이라서 ordianl() 메소드의 순서값 가져옴
        // 0: 왼쪽화면, 1: 가운데 화면, 2: 오른쪽 화면
        return list.get(position).getMessageType().ordinal();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private final TextView datetimeText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            datetimeText = itemView.findViewById(R.id.datetimeText);
        }

        public void setMessage(Message message){
            messageText.setText(message.getMessage());
            datetimeText.setText(message.getRegisterDate());
        }
    }
}
