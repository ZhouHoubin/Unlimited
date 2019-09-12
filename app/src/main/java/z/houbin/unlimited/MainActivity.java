package z.houbin.unlimited;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import z.houbin.xposed.lib.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button supportLabel = findViewById(R.id.support);
        supportLabel.append(System.lineSeparator());

        supportLabel.append("1.巧影(com.nexstreaming.app.kinemasterfree)(4.11.13.14060.CZ),");
        supportLabel.append(System.lineSeparator());
        supportLabel.append(System.lineSeparator());
        supportLabel.append("2.用量分析(info.kfsoft.usageanalyzer)(1.0.122),");
        supportLabel.append(System.lineSeparator());
        supportLabel.append(System.lineSeparator());
    }

    public void checkModule(View view) {
        Toast.makeText(getApplicationContext(),"模块检测 - "+ Util.isHook(),Toast.LENGTH_SHORT).show();
    }
}
