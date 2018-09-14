package com.fheebiy.trying.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouwenbo on 15/12/8.
 * 序列化方式
 */
public class Student implements Parcelable{


    private String name;

    private int age;

    private String area;


    protected Student(Parcel in) {
        name = in.readString();
        age = in.readInt();
        area = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(area);
    }
}
