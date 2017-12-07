package top.code666.a200plan.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by code666 on 2017/11/26.
 */

//收入实体类 ---  已废弃
/*
public class Income implements Parcelable {
    private int id,cate;
    private String notes;
    private BigDecimal money;
    private Timestamp times;

    public Income(int cate,String notes,Timestamp times,BigDecimal money) {
        this.cate = cate;
        this.notes = notes;
        this.times = times;
        this.money = money;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
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
        dest.writeString(this.notes);
        dest.writeSerializable(this.money);
        dest.writeSerializable(this.times);
    }

    protected Income(Parcel in) {
        this.id = in.readInt();
        this.cate = in.readInt();
        this.notes = in.readString();
        this.money = (BigDecimal) in.readSerializable();
        this.times = (Timestamp) in.readSerializable();
    }

    public static final Creator<Income> CREATOR = new Creator<Income>() {
        @Override
        public Income createFromParcel(Parcel source) {
            return new Income(source);
        }

        @Override
        public Income[] newArray(int size) {
            return new Income[size];
        }
    };

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", cate=" + cate +
                ", notes='" + notes + '\'' +
                ", money=" + money +
                ", times=" + times +
                '}';
    }
}
*/
