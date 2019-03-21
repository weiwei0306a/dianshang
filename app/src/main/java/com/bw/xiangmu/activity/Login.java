package com.bw.xiangmu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xiangmu.Contract.Login_Contract;
import com.bw.xiangmu.Persenter.Persenter_Login;
import com.bw.xiangmu.R;
import com.bw.xiangmu.fragment.Home;
import com.nostra13.universalimageloader.utils.L;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * @Auther: len
 * @Date: 2019/3/1 09:48:01
 * @Description:
 */
public class Login extends AppCompatActivity implements Login_Contract.Login_View {
    private EditText phone, pwd;
    private Button btn1;
    private Persenter_Login login_persenter;
    private TextView zhu;
    private CheckBox jizhu;
    private SharedPreferences sp;
    private String phone1;
    private String pwd1;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        zhu = findViewById(R.id.zhu1);
        phone = findViewById(R.id.phone);
        pwd = findViewById(R.id.pwd);
        btn1 = findViewById(R.id.dengl);
        jizhu = findViewById(R.id.che);
        //创建shared数据库
        sp = getSharedPreferences("user", MODE_PRIVATE);
        edit = sp.edit();
        //进入登录页面判断是否选中
        //是否记住密码SP

        if (sp.getBoolean("jizhu", false)) {
            jizhu.setChecked(sp.getBoolean("jizhu", false));
            phone.setText(sp.getString("phone", ""));
            pwd.setText(sp.getString("pwd", ""));
        }
        //新建一个P层
        login_persenter = new Persenter_Login();
        login_persenter.attachView(this);
        zhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Rregister.class);
                startActivity(intent);
                finish();
            }
        });
        //登录
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone1 = phone.getText().toString().trim();
                pwd1 = Login.this.pwd.getText().toString().trim();
                login_persenter.requestData(phone1, pwd1);

            }
        });

    }

    //请求数据
    @Override
    public void View_Login(final String message) {
        runOnUiThread(new Runnable() {
            private JSONObject json = null;

            @Override
            public void run() {
                try {
                    json = new JSONObject(message);
                    //得到集合
                    JSONObject result = json.getJSONObject("result");
                    String sessionId = result.getString("sessionId");
                    int userId = result.getInt("userId");
                    String message1 = json.getString("message");
                    String status = json.getString("status");
                    if (status.equals("0000")) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        edit.putBoolean("jizhu", jizhu.isChecked());
                       edit.putString("sessionId", sessionId);
                        edit.putInt("userId", userId);
                        edit.putString("phone", phone.getText().toString().trim());
                        edit.putString("pwd", pwd.getText().toString().trim());
                        edit.commit();
                        Toast.makeText(Login.this, "" + message1, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Login.this, "" + message1, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
