/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ab.bitmap;

import android.graphics.Bitmap;

// TODO: Auto-generated Javadoc

/**
 * © 2012 amsoft.cn
 * 名称：AbImageDownloadItem.java
 * 描述：图片下载单位.
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2011-12-10 上午10:10:53
 */
public class AbImageDownloadItem {
	
	/** 需要下载的图片的互联网地址. */
	public String imageUrl;
	
	/** 显示的图片的宽. */
	public int width;
	
	/** 显示的图片的高. */
	public int height;
	
	/** 图片的处理类型（剪切或者缩放到指定大小，参考AbConstant类）. */
	public int type;
	
	/** 下载完成的到的Bitmap对象. */
	public Bitmap bitmap;
	
	/** 下载完成的回调接口. */
	private AbImageDownloadListener listener;
	
	/**
	 * 构造.
	 */
	public AbImageDownloadItem() {
		super();
	}

	/**
	 * 用监听器构造.
	 *
	 * @param listener the listener
	 */
	public AbImageDownloadItem(AbImageDownloadListener listener) {
		super();
		this.listener = listener;
	}

	/**
	 * 获取图片请求URL.
	 *
	 * @return the image url
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 设置图片请求的URL.
	 *
	 * @param imageUrl the new image url
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * 获取图片宽度.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 设置图片宽度.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 获取图片高度.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 设置图片高度.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 获取处理类型.
	 *
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置图片处理类型.
	 *
	 * @param type the new type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 获取下载后结果的Bitmap.
	 *
	 * @return the bitmap
	 */
	public Bitmap getBitmap() {
		return bitmap;
	}

	/**
	 * 设置结果Bitmap置.
	 *
	 * @param bitmap the new bitmap
	 */
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	/**
	 * 获取监听器.
	 *
	 * @return the listener
	 */
	public AbImageDownloadListener getListener() {
		return listener;
	}

	/**
	 * 设置监听器.
	 *
	 * @param listener the new listener
	 */
	public void setListener(AbImageDownloadListener listener) {
		this.listener = listener;
	}

}
