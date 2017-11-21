package top.code666.a200plan.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by code666 on 2017/11/20.
 */

public class Plan implements Parcelable {
    private int id;
    private String name,content,pl_time,pb_time,situation;

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

    public String getPl_time() {
        return pl_time;
    }

    public void setPl_time(String pl_time) {
        this.pl_time = pl_time;
    }

    public String getPb_time() {
        return pb_time;
    }

    public void setPb_time(String pb_time) {
        this.pb_time = pb_time;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
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
        dest.writeString(this.pl_time);
        dest.writeString(this.pb_time);
        dest.writeString(this.situation);
    }

    public Plan() {
    }

    protected Plan(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.content = in.readString();
        this.pl_time = in.readString();
        this.pb_time = in.readString();
        this.situation = in.readString();
    }

    public static final Parcelable.Creator<Plan> CREATOR = new Parcelable.Creator<Plan>() {
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
                ", pl_time='" + pl_time + '\'' +
                ", pb_time='" + pb_time + '\'' +
                ", situation='" + situation + '\'' +
                '}';
    }
}
