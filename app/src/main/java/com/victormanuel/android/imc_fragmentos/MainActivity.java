
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.victormanuel.android.imc_fragmentos.R;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_imc);
        if(fragment==null){
            fragment = new imc.android.victorchan.com.imc.ImcFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_imc, fragment)
                    .commit();
        }
    }
}
