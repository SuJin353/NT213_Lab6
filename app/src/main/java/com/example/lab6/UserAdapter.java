package com.example.lab6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class UserAdapter extends BaseAdapter {
    ArrayList<UserModel> userArrayList;
    Context context;

    public UserAdapter(ArrayList<UserModel> userArrayList, Context context) {
        this.userArrayList = userArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.lv_user_item, viewGroup, false);
        }
        TextView tv_username, tv_id;

        tv_username = view.findViewById(R.id.tv_username_info);
        tv_id = view.findViewById(R.id.tv_id_info);

        tv_username.setText(userArrayList.get(position).getUsername());
        tv_id.setText(userArrayList.get(position).getId().toString());
        return view;
    }
}
