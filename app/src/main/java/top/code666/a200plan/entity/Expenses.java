package top.code666.a200plan.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by code666 on 2017/11/20.
 */

//支出实体类 -----收入也是同一个
public class Expenses implements Parcelable {
    private int id,cate,type;
    private String notes;
    private double money;
    private Timestamp times;

    public Expenses(int cate,String notes,String times,Double money,int type) {
        this.cate = cate;
        this.notes = notes;
        this.times = Timestamp.valueOf(times);
        this.money = money;
        this.type = type;
    }

    public Expenses(int cate,String notes,Timestamp times,Double money,int type) {
        this.cate = cate;
        this.notes = notes;
        this.times = times;
        this.money = money;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Timestamp getTimes() {
        return times;
    }

    public void setTimes(Timestamp times) {
        this.times = times;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.cate);
        dest.writeInt(this.type);
        dest.writeString(this.notes);
        dest.writeDouble(this.money);
        dest.writeSerializable(this.times);
    }

    protected Expenses(Parcel in) {
        this.id = in.readInt();
        this.cate = in.readInt();
        this.type = in.readInt();
        this.notes = in.readString();
        this.money = in.readDouble();
        this.times = (Timestamp) in.readSerializable();
    }

    public static final Creator<Expenses> CREATOR = new Creator<Expenses>() {
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
                ", cate=" + cate +
                ", type=" + type +
                ", notes='" + notes + '\'' +
                ", money=" + money +
                ", times=" + times +
                '}';
    }
}
