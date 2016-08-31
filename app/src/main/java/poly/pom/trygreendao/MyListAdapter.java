package poly.pom.trygreendao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import poly.pom.trygreendao.model.User;

/**
 * Created by User on 31/8/2016.
 */
public class MyListAdapter extends RealmBaseAdapter<User> implements ListAdapter {


    public MyListAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<User> data) {
        super(context, data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = inflater.inflate(R.layout.array_list, viewGroup, false);
            vh = new ViewHolder(view);
            view.setTag(vh);

        } else
            vh = (ViewHolder) view.getTag();

        User user = adapterData.get(i);
        vh.tvName.setText(user.getName());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
