package poly.pom.trygreendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import poly.pom.trygreendao.model.User;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edInputName)
    EditText edInputName;
    @BindView(R.id.btSave)
    Button btSave;
    @BindView(R.id.lvUser)
    ListView lvUser;
    private Realm realm;
    private RealmQuery<User> query;
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //default
        realm = Realm.getDefaultInstance();

//        query the data
        query = realm.where(User.class);
        RealmResults<User> userData = query.findAll();

        adapter = new MyListAdapter(this, userData);

        lvUser.setAdapter(adapter);
    }

    @OnClick(R.id.btSave)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSave:


                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        if ((edInputName.getText().toString().isEmpty()))
                            return;
                        User user = realm.createObject(User.class);
                        user.setName(edInputName.getText().toString());

                    }

                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        edInputName.setText("");

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
