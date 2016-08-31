package poly.pom.trygreendao.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by User on 31/8/2016.
 */
public class User extends RealmObject {
    @PrimaryKey
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
