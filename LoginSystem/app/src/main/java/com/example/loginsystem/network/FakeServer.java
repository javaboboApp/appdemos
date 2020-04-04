package com.example.loginsystem.network;

import android.os.Parcel;
import android.os.Parcelable;

public class FakeServer implements Parcelable {

    private String username;
    private String password;


    protected FakeServer(Parcel in) {
        username = in.readString();
        password = in.readString();
    }

    public String getUsername() {
        return username;
    }

    public FakeServer() {

    }

    public static final Creator<FakeServer> CREATOR = new Creator<FakeServer>() {
        @Override
        public FakeServer createFromParcel(Parcel in) {
            return new FakeServer(in);
        }

        @Override
        public FakeServer[] newArray(int size) {
            return new FakeServer[size];
        }
    };

    public void registerUser(String userName, String passWord)
    {
        this.username = userName;
        this.password = passWord;
    }

    public boolean isExistingUser(String userName, String passWord)
    {
        return (this.username.equals(userName) && this.password.equals(passWord));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
    }
}