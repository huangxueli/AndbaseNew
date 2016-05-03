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

import java.util.concurrent.Executor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.ab.task.AbTask;
import com.ab.task.AbThreadFactory;
import com.ab.util.AbFileUtil;
import com.ab.util.AbImageUtil;
import com.ab.util.AbLogUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.AbViewUtil;

// TODO: Auto-generated Javadoc

/**
 * © 2012 amsoft.cn
 * 名称：AbImageDownloader.java
 * 描述：下载图片并显示的工具类.
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2011-12-10 上午10:10:53
 */
public class AbImageDownloader { 
	
    /** Context. */
    private Context mContext = null;
    
    /** 显示的图片的宽. */
    private int width;
	
	/** 显示的图片的高. */
    private int height;
	
	/** 图片的处理类型（剪切或者缩放到指定大小）. */
    private int type  = AbImageUtil.ORIGINALIMG;
    
    /** 显示为下载中的图片. */
    private Drawable loadingImage;
    
    /** 显示为下载中的View. */
    private View loadingView;
    
    /** 显示下载失败的图片. */
    private Drawable errorImage;
    
    /** 图片未找到的图片. */
    private Drawable noImage;
    
    /** 线程执行器. */
	public static Executor mExecutorService = null;
    
    /**
     * 构造图片下载器.
     *
     * @param context the context
     */
    public AbImageDownloader(Context context) {
    	this.mContext = context;
    	mExecutorService = AbThreadFactory.getExecutorService();
    } 
    
    
	/**
	 * New instance.
	 *
	 * @param context the context
	 * @return the ab image downloader
	 */
	public static AbImageDownloader newInstance(Context context) {
		AbImageDownloader imageDownloader = new AbImageDownloader(context);
		return imageDownloader;
	}
	
     
    /**
     * 显示这个图片,解决了列表问题.
     * 列表问题：滑动过程中，getView的imageView会重复利用，导致图片会串位
     *
     * @param imageView 显得的View
     * @param url the url
     */
    public Bitmap display(final ImageView imageView,String url) { 
    	if(AbStrUtil.isEmpty(url)){
    		if(noImage != null){
    			if(loadingView != null){
        			loadingView.setVisibility(View.INVISIBLE);
					imageView.setVisibility(View.VISIBLE);
        		}
    			imageView.setImageDrawable(noImage);
    		}
    		return null;
    	}
    	
    	//设置下载项
        final AbImageDownloadItem item = new AbImageDownloadItem(); 
        //设置显示的大小
        item.width = AbViewUtil.scale(mContext,width);
        item.height = AbViewUtil.scale(mContext,height);
        //设置为缩放
        item.type = type;
        item.imageUrl = url;
        final String cacheKey = AbImageCache.getCacheKey(item.imageUrl, item.width, item.height, item.type);
        item.bitmap =  AbImageCache.getBitmapFromCache(cacheKey);
		//if(D) Log.d(TAG, "缓存中获取的"+cacheKey+":"+item.bitmap);
		
        //设置标记
        imageView.setTag(url);
        
    	if(item.bitmap == null){
    		
    		//先显示加载中
        	if(loadingView!=null){
    			loadingView.setVisibility(View.VISIBLE);
    			imageView.setVisibility(View.INVISIBLE);
    		}else if(loadingImage != null){
    			imageView.setImageDrawable(loadingImage);
    		}
    		
    		//下载完成后更新界面
            item.setListener(new AbImageDownloadListener() { 
                
                @Override 
                public void update(Bitmap bitmap, String imageUrl) { 
                	
                    //未设置加载中的图片，并且设置了隐藏的View
            		if(loadingView != null && imageUrl.equals(imageView.getTag())){
            			loadingView.setVisibility(View.INVISIBLE);
						imageView.setVisibility(View.VISIBLE);
            		}
            		//要判断这个imageView的url有变化，如果没有变化才set，
                    //有变化就取消，解决列表的重复利用View的问题
                	if(bitmap!=null && imageUrl.equals(imageView.getTag())){
                	    AbLogUtil.d(mContext, "图片下载，设置:"+imageUrl);
                		imageView.setImageBitmap(bitmap);
                	}else{
                		if(errorImage != null && imageUrl.equals(imageView.getTag())){
                			imageView.setImageDrawable(errorImage);
                		}
                	}
                } 
            }); 
            AbLogUtil.d(mContext, "图片下载，执行:"+url);
            execute(item);
    	}else{
    		if(loadingView != null){
    		    loadingView.setVisibility(View.INVISIBLE);
    		    imageView.setVisibility(View.VISIBLE);
    		}
    		imageView.setImageBitmap(item.bitmap);
    		return item.bitmap;
    	}
    	return null;
    	
    } 
    
    /**
     * 执行下载.
     * @param item the item
     */
    public void execute(final AbImageDownloadItem item) {  
    	
    	String imageUrl = item.imageUrl;
    	if(AbStrUtil.isEmpty(imageUrl)){
    		AbLogUtil.d(mContext, "图片URL为空，请先判断");
    	}else{
    		imageUrl = imageUrl.trim();
    	}
    	
		//从缓存中获取图片
    	final String cacheKey = AbImageCache.getCacheKey(imageUrl, item.width, item.height, item.type);
		item.bitmap =  AbImageCache.getBitmapFromCache(cacheKey);
    	
    	if(item.bitmap == null){
    		
			// 缓存中没有图像，则从网络上取出数据，并将取出的数据缓存到内存中
    		mExecutorService.execute(new Runnable() { 
	    		public void run() {
	    			try {
	    				//逻辑：判断这个任务是否有其他线程再执行，如果有等待，直到下载完成唤醒显示
	    	    		Runnable runnable = AbImageCache.getRunRunnableFromCache(cacheKey);
	    				if(runnable != null){
	    					
	    	            	//线程等待通知后显示
    	                	//if(D) Log.d(TAG, "线程等待:"+item.imageUrl+","+cacheKey);
    	                	AbImageCache.addToWaitRunnableCache(cacheKey, this);
    	                	synchronized(this){
    	                		this.wait();
    	                	}
    	                	//if(D) Log.d(TAG, "线程被唤醒:"+item.imageUrl);
    	    				//直接获取
    	    				item.bitmap =  AbImageCache.getBitmapFromCache(cacheKey);
	    				}else{
	    					//增加下载中的线程记录
	    					//if(D) Log.d(TAG, "从内存取或者下载:"+item.imageUrl+","+cacheKey);
	    					AbImageCache.addToRunRunnableCache(cacheKey, this);
    	    				item.bitmap = AbFileUtil.getBitmapFromSDCache(item.imageUrl,item.type,item.width,item.height);
    	    				//增加到下载完成的缓存，删除下载中的记录和等待的记录，同时唤醒所有等待列表中key与其key相同的线程
        	    			AbImageCache.addBitmapToCache(cacheKey,item.bitmap);     
    	    				
	    				}
	    				
	    			} catch (Exception e) { 
	    				AbLogUtil.d(mContext, "error:"+item.imageUrl);
	    				e.printStackTrace();
	    			} finally{ 
		    			if (item.getListener() != null) { 
	    	                Message msg = handler.obtainMessage(); 
	    	                msg.obj = item; 
	    	                handler.sendMessage(msg); 
	    	            } 
	    			}
	    		}                 
	    	});  
    		
    	}else{
    		//if(D) Log.d(TAG, "从内存缓存中得到图片:"+cacheKey+","+item.bitmap);
    		if (item.getListener() != null) { 
                Message msg = handler.obtainMessage(); 
                msg.obj = item; 
                handler.sendMessage(msg); 
            } 
    	}
    	
    }
    
    /**
     * 描述：设置下载中的图片.
     *
     * @param resID the new loading image
     */
	public void setLoadingImage(int resID) {
		this.loadingImage = mContext.getResources().getDrawable(resID);
	}
	
	/**
	 * 描述：设置下载中的View，优先级高于setLoadingImage.
	 *
	 * @param view 放在ImageView的上边或者下边的View
	 */
	public void setLoadingView(View view) {
		this.loadingView = view;
	}

	/**
	 * 描述：设置下载失败的图片.
	 *
	 * @param resID the new error image
	 */
	public void setErrorImage(int resID) {
		this.errorImage = mContext.getResources().getDrawable(resID);
	}

	/**
	 * 描述：设置未找到的图片.
	 *
	 * @param resID the new no image
	 */
	public void setNoImage(int resID) {
		this.noImage = mContext.getResources().getDrawable(resID);
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
	 * 描述：设置图片的宽度.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) {
		this.width = AbViewUtil.scale(mContext, width);
	}

	/**
	 * 获取图片的高度.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 描述：设置图片的高度.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		this.height = AbViewUtil.scale(mContext, height);
	}
	
	
	/**
	 * 获取图片处理类型.
	 *
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * 描述：图片的处理类型（剪切或者缩放到指定大小，参考AbConstant类）.
	 *
	 * @param type the new type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/** 下载完成后的消息句柄. */
    private static Handler handler = new Handler() { 
        @Override 
        public void handleMessage(Message msg) { 
            AbImageDownloadItem item = (AbImageDownloadItem)msg.obj; 
            item.getListener().update(item.bitmap, item.imageUrl); 
        } 
    }; 
	
    
}

