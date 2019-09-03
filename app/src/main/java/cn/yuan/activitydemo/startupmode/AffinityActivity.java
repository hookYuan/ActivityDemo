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

import android.os.Bundle;

import cn.yuan.activitydemo.R;
import yuan.core.title.ActionBarUtil;

/**
 * 描述：指定affinity
 * <p>
 * taskAffinity 单独使用并不会生效。
 * 要想其生效，需要配合其他属性使用，或者配合 Intent.FLAG_ACTIVITY_NEW_TASK，或者配合
 * allowTaskReparenting 。使用时用其中的一个就行，下面将详细介绍这两个属性。
 *
 * @author yuanye
 * @date 2019/9/3 16:45
 */
public class AffinityActivity extends StartUpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.create(this)
                .setTextColor(getColor(R.color.white))
                .setLeftIcon(R.drawable.ic_base_back_white)
                .setTitleText("TaskAffinity属性")
                .setLeftClickFinish();
    }
}
