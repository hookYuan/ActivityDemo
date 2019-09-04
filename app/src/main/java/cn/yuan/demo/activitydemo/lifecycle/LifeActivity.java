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
package cn.yuan.demo.activitydemo.lifecycle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 描述：记录生命周期执行方法
 *
 * @author yuanye
 * @date 2019/9/3 14:42
 */
public class LifeActivity extends AppCompatActivity {
    /**
     * 生命周期字符串
     */
    protected String mLifeMethod = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLifeMethod = mLifeMethod + "onCreate->";
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        mLifeMethod = mLifeMethod + "onRestart->";
        super.onRestart();
    }

    @Override
    protected void onStart() {
        mLifeMethod = mLifeMethod + "onStart->";
        super.onStart();
    }

    @Override
    protected void onPause() {
        mLifeMethod = mLifeMethod + "onPause->";
        super.onPause();
    }

    @Override
    protected void onResume() {
        mLifeMethod = mLifeMethod + "onResume->";
        super.onResume();
    }

    @Override
    protected void onStop() {
        mLifeMethod = mLifeMethod + "onStop->";
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mLifeMethod = mLifeMethod + "onDestroy->";
        super.onDestroy();
    }
}
