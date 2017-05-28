package bombey77.sa93;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        startService(new Intent(this, MyService.class).putExtra("time", 7));
        startService(new Intent(this, MyService.class).putExtra("time", 5));
        startService(new Intent(this, MyService.class).putExtra("time", 5));
        startService(new Intent(this, MyService.class).putExtra("time", 3));
    }
}
