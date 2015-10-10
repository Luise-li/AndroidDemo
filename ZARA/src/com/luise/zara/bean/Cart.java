package com.luise.zara.bean;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
	private int cartId;
	private String goodsImage;
	private String goodsName;
	private String goodsColor;
	private String goodsSize;
	private String goodsNum;
	private String goodsDiscount;

	private String totalNum;
	private String totalPrice;

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsColor() {
		return goodsColor;
	}

	public void setGoodsColor(String goodsColor) {
		this.goodsColor = goodsColor;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsDiscount() {
		return goodsDiscount;
	}

	public void setGoodsDiscount(String goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(goodsImage);// write out goods image name.
		dest.writeString(goodsName);// write out goods name.
		dest.writeString(goodsColor);// write out goods color.
		dest.writeString(goodsSize);// write out goods size.
		dest.writeString(goodsNum);// write out goods num.
		dest.writeString(goodsDiscount);// write out goods discount price.
		dest.writeString(totalPrice);// write out goods total price.

	}

	public static final Parcelable.Creator<Cart> CREATOR = new Creator<Cart>() {

		@Override
		public Cart[] newArray(int size) {

			return new Cart[size];
		}

		@Override
		public Cart createFromParcel(Parcel source) {
			Cart cart = new Cart();
			cart.setGoodsImage(source.readString());
			cart.setGoodsName(source.readString());
			cart.setGoodsColor(source.readString());
			cart.setGoodsSize(source.readString());
			cart.setGoodsNum(source.readString());
			cart.setGoodsDiscount(source.readString());
			cart.setTotalPrice(source.readString());

			return cart;
		}
	};

}
