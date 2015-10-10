package com.luise.zara.bean;

import java.io.Serializable;

public class Order implements Serializable {
	private int orderId;
	private String goodsImage;
	private String goodsName;
	private String goodsColor;
	private String goodsSize;
	private String goodsNum;
	private String goodsDiscount;
	private int orderFlag;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public int getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(int orderFlag) {
		this.orderFlag = orderFlag;
	}

}
