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
package cn.yuan.activitydemo.startupmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.yuan.activitydemo.R;
import cn.yuan.activitydemo.lifecycle.LifeActivity;
import yuan.core.title.ActionBarUtil;

/**
 * 描述：栈顶唯一
 *
 * @author yuanye
 * @date 2019/9/3 14:58
 */
public class SingleTopActivity extends StartUpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.create(this)
                .setTextColor(getColor(R.color.white))
                .setLeftIcon(R.drawable.ic_base_back_white)
                .setTitleText("栈顶唯一模式:singleTop")
                .setLeftClickFinish();
    }

}
