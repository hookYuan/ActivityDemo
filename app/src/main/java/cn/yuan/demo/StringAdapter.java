package cn.yuan.demo;

import android.view.ViewConfiguration;
import android.view.ViewParent;

import java.util.List;

import yuan.core.list.BaseViewHolder;
import yuan.core.list.RecyclerAdapter;

/**
 * 描述：
 *
 * @author yuanye
 * @date 2019/9/3 15:46
 */
public class StringAdapter extends RecyclerAdapter<String> {

    public StringAdapter(List<String> data) {
        super(data, android.R.layout.simple_list_item_1);
    }

    @Override
    public void onBindHolder(BaseViewHolder holder, String item, int position) {
        holder.setText(android.R.id.text1, item);
        ViewConfiguration.get(mContext)
                .getKeyRepeatTimeout();
    }
}
