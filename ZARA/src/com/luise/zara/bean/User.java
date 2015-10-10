package com.luise.zara.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
	private int id;
	private String ImageName;
	private String pet;
	private int flag;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageName() {
		return ImageName;
	}

	public void setImageName(String imageName) {
		ImageName = imageName;
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(ImageName);
		dest.writeString(pet);
	}

	public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {

		@Override
		public User[] newArray(int size) {

			return new User[size];
		}

		@Override
		public User createFromParcel(Parcel source) {
			User user = new User();
			user.setId(source.readInt());
			user.setImageName(source.readString());
			user.setPet(source.readString());
			return user;
		}
	};

}
