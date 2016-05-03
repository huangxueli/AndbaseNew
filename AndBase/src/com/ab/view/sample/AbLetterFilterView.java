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
package com.ab.view.sample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * © 2012 amsoft.cn 名称：AbLetterFilterView.java 描述：字母索引条
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2013-10-24 下午1:36:45
 */
public class AbLetterFilterView extends View {
	
	/** The l. */
	private char[] l;
	
	/** The section indexter. */
	private SectionIndexer sectionIndexter = null;
	
	/** The list. */
	private ListView list;
	
	/** The m dialog text. */
	private TextView mDialogText;
	
	/** The paint. */
	private Paint paint;
	
	/** The background resource. */
	private int backgroundResource;
	// private int bg_touch = R.drawable.im_contact_sidebar_bg_trans;
	// private int bg_leave = R.color.im_white_side;
	/** The width center. */
	private float widthCenter;

	/** 字母之间的间距. */
	private float singleHeight;

	/**
	 * Instantiates a new ab letter filter view.
	 *
	 * @param context the context
	 */
	public AbLetterFilterView(Context context) {
		super(context);
		init();
	}

	/**
	 * Instantiates a new ab letter filter view.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public AbLetterFilterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		l = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z', '#' };
		paint = new Paint();
		paint.setColor(Color.parseColor("#333333"));
		paint.setTypeface(Typeface.DEFAULT);
		paint.setTextSize(12);
		paint.setAntiAlias(true);
		paint.setTextAlign(Paint.Align.CENTER);

	}

	/**
	 * Instantiates a new ab letter filter view.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 * @param defStyle the def style
	 */
	public AbLetterFilterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/**
	 * Sets the list view.
	 *
	 * @param _list the new list view
	 */
	public void setListView(ListView _list) {
		list = _list;
		sectionIndexter = (SectionIndexer) _list.getAdapter();
	}

	/**
	 * Sets the text view.
	 *
	 * @param mDialogText the new text view
	 */
	public void setTextView(TextView mDialogText) {
		this.mDialogText = mDialogText;
	}

	/* (non-Javadoc)
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		int i = (int) event.getY();
		int div = (int) singleHeight;
		int idx = 0;
		if (div != 0) {
			idx = i / div;
		}
		if (idx >= l.length) {
			idx = l.length - 1;
		} else if (idx < 0) {
			idx = 0;
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_MOVE) {

			mDialogText.setVisibility(View.VISIBLE);
			mDialogText.setText("" + l[idx]);
			// 首先先将listView强制转换为HeaderViewListAdapter
			if (list.getAdapter() != null) {
				HeaderViewListAdapter listAdapter = (HeaderViewListAdapter) list
						.getAdapter();
				if (sectionIndexter == null) {
					sectionIndexter = (SectionIndexer) listAdapter
							.getWrappedAdapter();
				}
				int position = sectionIndexter.getPositionForSection(l[idx]);
				if (position == -1) {

					return true;
				}
				list.setSelection(position);
			}
		} else {
			mDialogText.setVisibility(View.INVISIBLE);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	protected void onDraw(Canvas canvas) {
		float height = getHeight();
		singleHeight = height / l.length;
		widthCenter = getMeasuredWidth() / (float) 2;
		for (int i = 0; i < l.length; i++) {
			canvas.drawText(String.valueOf(l[i]), widthCenter, singleHeight
					+ (i * singleHeight), paint);
		}
		super.onDraw(canvas);
	}

	/**
	 * Gets the background resource.
	 *
	 * @return the background resource
	 */
	public int getBackgroundResource() {
		return backgroundResource;
	}

	/* (non-Javadoc)
	 * @see android.view.View#setBackgroundResource(int)
	 */
	public void setBackgroundResource(int backgroundResource) {
		this.backgroundResource = backgroundResource;
		this.setBackgroundResource(backgroundResource);
	}

}
