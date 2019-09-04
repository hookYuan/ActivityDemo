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
package cn.yuan.demo.activitydemo.startupmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.yuan.demo.R;
import cn.yuan.demo.activitydemo.lifecycle.LifeActivity;

/**
 * 描述：
 *
 * @author yuanye
 * @date 2019/9/3 16:32
 */
class StartUpActivity extends LifeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView lifeOrder = findViewById(R.id.text_life_order);
        lifeOrder.setText("生命周期执行顺序：" + mLifeMethod);

        TextView taskId = findViewById(R.id.text_task);
        taskId.setText("任务栈id ：" + getTaskId());
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_standard:
                Intent intent = new Intent(this, StandardActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_singleTop:
                Intent intent2 = new Intent(this, SingleTopActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_singleTask:
                Intent intent3 = new Intent(this, SingleTaskActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_singleInstance:
                Intent intent4 = new Intent(this, SingleInstanceActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_affinity:
                /*Manifest 中配置taskAffinity后 设置FLAG_ACTIVITY_NEW_TASK或者 singleTask*/
                Intent intent5 = new Intent(this, AffinityActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent5);
                break;
        }
    }
}
