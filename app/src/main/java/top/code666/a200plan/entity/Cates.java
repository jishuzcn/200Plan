package top.code666.a200plan.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by code666 on 2017/11/26.
 */

//类别实体类
public class Cates implements Parcelable {
    private int id,imageSrc;
    private String name;//分类名称和图片地址

    public Cates(){

    }

    public Cates(int id,int imageSrc,String name) {
        this.id = id;
        this.imageSrc = imageSrc;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.imageSrc);
        dest.writeString(this.name);
    }

    protected Cates(Parcel in) {
        this.id = in.readInt();
        this.imageSrc = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Cates> CREATOR = new Creator<Cates>() {
        @Override
        public Cates createFromParcel(Parcel source) {
            return new Cates(source);
        }

        @Override
        public Cates[] newArray(int size) {
            return new Cates[size];
        }
    };

    @Override
    public String toString() {
        return "Cates{" +
                "id=" + id +
                ", imageSrc=" + imageSrc +
                ", name='" + name + '\'' +
                '}';
    }
}
