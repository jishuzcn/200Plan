package top.code666.a200plan.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by code666 on 2017/11/20.
 */

public class Expenses implements Parcelable {
    private int id;
    private String name,notes,times;
    private BigDecimal morning_money,noon_money,evening_money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public BigDecimal getMorning_money() {
        return morning_money;
    }

    public void setMorning_money(BigDecimal morning_money) {
        this.morning_money = morning_money;
    }

    public BigDecimal getNoon_money() {
        return noon_money;
    }

    public void setNoon_money(BigDecimal noon_money) {
        this.noon_money = noon_money;
    }

    public BigDecimal getEvening_money() {
        return evening_money;
    }

    public void setEvening_money(BigDecimal evening_money) {
        this.evening_money = evening_money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.notes);
        dest.writeString(this.times);
        dest.writeSerializable(this.morning_money);
        dest.writeSerializable(this.noon_money);
        dest.writeSerializable(this.evening_money);
    }

    public Expenses() {
    }

    protected Expenses(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.notes = in.readString();
        this.times = in.readString();
        this.morning_money = (BigDecimal) in.readSerializable();
        this.noon_money = (BigDecimal) in.readSerializable();
        this.evening_money = (BigDecimal) in.readSerializable();
    }

    public static final Parcelable.Creator<Expenses> CREATOR = new Parcelable.Creator<Expenses>() {
        @Override
        public Expenses createFromParcel(Parcel source) {
            return new Expenses(source);
        }

        @Override
        public Expenses[] newArray(int size) {
            return new Expenses[size];
        }
    };

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", times='" + times + '\'' +
                ", morning_money=" + morning_money +
                ", noon_money=" + noon_money +
                ", evening_money=" + evening_money +
                '}';
    }
}
