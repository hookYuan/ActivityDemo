/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.yuan.activitydemo.intentflag;

import android.content.Intent;
import android.view.View;

import cn.yuan.activitydemo.R;
import yuan.core.dialog.DialogUtil;
import yuan.core.list.BaseViewHolder;
import yuan.core.list.GridDivider;
import yuan.core.list.RecyclerAdapter;
import yuan.core.title.ActionBarUtil;
import yuan.core.ui.Adapter;
import yuan.core.ui.RecyclerActivity;

/**
 * 描述：
 *
 * @author yuanye
 * @date 2019/9/3 15:30
 */
@Adapter(adapter = FlagAdapter.class)
public class FlagActivity extends RecyclerActivity {

    @Override
    public void initData() {
        ActionBarUtil.create(this)
                .setTextColor(getColor(R.color.white))
                .setLeftIcon(R.drawable.ic_base_back_white)
                .setTitleText("Intent Flag分析")
                .setLeftClickFinish();
        mRecyclerView.addItemDecoration(new GridDivider());

        mData.add("长按显示说明，点击跳转实例");
        mData.add("FLAG_ACTIVITY_BROUGHT_TO_FRONT");
        mData.add("FLAG_ACTIVITY_NEW_TASK");
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClick(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder holder, View view, int position) {
                Intent intent = new Intent(mContext, FlagTargetActivity.class);
                switch (position) {
                    case 0:
                        return;
                    case 1:
                        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        break;
                    case 2:
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        break;
                }
                startActivity(intent);
            }
        });

        mAdapter.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseViewHolder adapter, View view, int position) {
                String hintText = "";
                switch (position) {
                    case 0:
                        return false;
                    case 1:
                        hintText = "把当前活动的Activity放到栈顶";
                        break;
                    case 2:
                        hintText = "设置了这个标志后，新启动的Activity并非就一定在新的Task中创建，如果A和B在属于同一个package，而且都是使用默认的Task Affinity，那B还是会在A的task中被创建。 所以，只有A和B的Task Affinity不同时，设置了这个标志才会使B被创建到新的Task。注意如果试图从非Activity的非正常途径启动一个Activity，比如从一个Receiver中启动一个Activity，则Intent必须要添加FLAG_ACTIVITY_NEW_TASK标记\n";
                        break;
                }
                DialogUtil.create(mContext)
                        .alertText(hintText);
                return false;
            }
        });
    }
}
