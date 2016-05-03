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
package com.ab.view.pullview;

import android.view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class AbViewInfo.
 */
public class AbViewInfo {

	/** The view. */
	private View view;
	
	/** The width. */
	private int width;
	
	/** The height. */
	private int height;
	
	/** The top. */
	private int top;
	
	/** The bottom. */
	private int bottom;

	/**
	 * Instantiates a new ab view info.
	 *
	 * @param view the view
	 * @param width the width
	 * @param height the height
	 * @param top the top
	 * @param bottom the bottom
	 */
	public AbViewInfo(View view, int width, int height, int top, int bottom) {
		super();
		this.view = view;
		this.width = width;
		this.height = height;
		this.top = top;
		this.bottom = bottom;
	}
	
	/**
	 * Instantiates a new ab view info.
	 *
	 * @param view the view
	 * @param width the width
	 * @param height the height
	 */
	public AbViewInfo(View view, int width, int height) {
		super();
		this.view = view;
		this.width = width;
		this.height = height;
	}

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets the top.
	 *
	 * @return the top
	 */
	public int getTop() {
		return top;
	}

	/**
	 * Sets the top.
	 *
	 * @param top the new top
	 */
	public void setTop(int top) {
		this.top = top;
	}

	/**
	 * Gets the bottom.
	 *
	 * @return the bottom
	 */
	public int getBottom() {
		return bottom;
	}

	/**
	 * Sets the bottom.
	 *
	 * @param bottom the new bottom
	 */
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

}
