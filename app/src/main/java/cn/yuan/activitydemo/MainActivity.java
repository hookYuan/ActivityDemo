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
package cn.yuan.activitydemo;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import cn.yuan.activitydemo.intentflag.FlagActivity;
import cn.yuan.activitydemo.startupmode.StandardActivity;
import yuan.core.tool.RouteUtil;


/**
 * 描述：测试、研究Activity
 * 1.Activity启动流程
 * 2.Activity生命周期
 * 3.Activity启动模式
 * 4.taskAffinity
 * 5.Intent和Flag
 *
 * @author yuanye
 * @date 2019/9/3 14:10
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_up_mode:
                Intent intent = new Intent(this, StandardActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_intent_flag:
                RouteUtil.open(this, FlagActivity.class);
                break;
        }
    }
}
