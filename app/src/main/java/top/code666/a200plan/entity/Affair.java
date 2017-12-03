package top.code666.a200plan.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by code666 on 2017/11/20.
 */
//事件实体类--------------------------已废弃
public class Affair implements Parcelable {
    private int id;
    private String content,time,name,tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.content);
        dest.writeString(this.time);
        dest.writeString(this.name);
        dest.writeString(this.tag);
    }

    public Affair() {
    }

    protected Affair(Parcel in) {
        this.id = in.readInt();
        this.content = in.readString();
        this.time = in.readString();
        this.name = in.readString();
        this.tag = in.readString();
    }

    public static final Parcelable.Creator<Affair> CREATOR = new Parcelable.Creator<Affair>() {
        @Override
        public Affair createFromParcel(Parcel source) {
            return new Affair(source);
        }

        @Override
        public Affair[] newArray(int size) {
            return new Affair[size];
        }
    };

    @Override
    public String toString() {
        return "Affair{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
