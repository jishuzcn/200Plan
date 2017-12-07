package top.code666.a200plan.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by code666 on 2017/11/20.
 */

//第二次更改后 无用的实体类
/*
public class Economization implements Parcelable {
    private int id;
    private String title,time;
    private BigDecimal money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.time);
        dest.writeSerializable(this.money);
    }

    public Economization() {
    }

    protected Economization(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.time = in.readString();
        this.money = (BigDecimal) in.readSerializable();
    }

    public static final Parcelable.Creator<Economization> CREATOR = new Parcelable.Creator<Economization>() {
        @Override
        public Economization createFromParcel(Parcel source) {
            return new Economization(source);
        }

        @Override
        public Economization[] newArray(int size) {
            return new Economization[size];
        }
    };

    @Override
    public String toString() {
        return "Economization{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", money=" + money +
                '}';
    }
}
*/
