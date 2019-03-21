package com.bw.xiangmu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xiangmu.Contract.Register_Contract;
import com.bw.xiangmu.Persenter.Register_Persenter;
import com.bw.xiangmu.R;

import org.json.JSONObject;

/**
 * @Auther: len
 * @Date: 2019/3/1 09:48:01
 * @Description:
 */
public class Rregister extends AppCompatActivity implements Register_Contract.Register_View {

    private EditText pwd1, phone1;
    private Button zhu;
    private TextView deng;
    private Register_Persenter register_persenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        phone1 = findViewById(R.id.phone1);
        pwd1 = findViewById(R.id.pwd1);
        zhu = findViewById(R.id.zhu);
        deng = findViewById(R.id.deng);
        register_persenter = new Register_Persenter();
        register_persenter.attachView(this);
        //注册
        zhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phone1.getText().toString().trim();
                String pwd = pwd1.getText().toString().trim();
                register_persenter.requestData(phone, pwd);
            }
        });
        deng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rregister.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void Model_Register(final String message) {
        runOnUiThread(new Runnable() {

            private JSONObject json = null;

            @Override
            public void run() {
                try {

                    json = new JSONObject(message);
                    String message1 = json.getString("message");
                    String status = json.getString("status");
                    if (status.equals("0000")) {
                        //注册成功
                        Intent intent = new Intent(Rregister.this, Login.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Rregister.this, "" + message1, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Rregister.this, "" + message1, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
