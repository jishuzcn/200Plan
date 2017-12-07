package top.code666.a200plan.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

/**
 * Created by code666 on 2017/11/20.
 */

public class Plan implements Parcelable {
    private int id;
    private String name,content,situation;
    private Timestamp pl_time,pb_time;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Timestamp getPl_time() {
        return pl_time;
    }

    public void setPl_time(Timestamp pl_time) {
        this.pl_time = pl_time;
    }

    public Timestamp getPb_time() {
        return pb_time;
    }

    public void setPb_time(Timestamp pb_time) {
        this.pb_time = pb_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.content);
        dest.writeString(this.situation);
        dest.writeSerializable(this.pl_time);
        dest.writeSerializable(this.pb_time);
    }

    public Plan() {
    }

    protected Plan(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.content = in.readString();
        this.situation = in.readString();
        this.pl_time = (Timestamp) in.readSerializable();
        this.pb_time = (Timestamp) in.readSerializable();
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel source) {
            return new Plan(source);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", situation='" + situation + '\'' +
                ", pl_time=" + pl_time +
                ", pb_time=" + pb_time +
                '}';
    }
}
