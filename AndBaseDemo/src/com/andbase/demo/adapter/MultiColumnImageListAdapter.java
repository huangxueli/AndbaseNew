package com.andbase.demo.adapter;




import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ab.bitmap.AbImageDownloader;
import com.ab.util.AbImageUtil;
import com.ab.view.pullview.AbMultiColumnListAdapter;
import com.ab.view.pullview.AbViewInfo;
import com.andbase.R;
import com.andbase.demo.model.ImageInfo;
/**
 * © 2012 amsoft.cn
 * 名称：MyListViewAdapter
 * 描述：在Adapter中释放Bitmap
 * @author 还如一梦中
 * @date 2011-12-10
 * @version
 */
public class MultiColumnImageListAdapter extends AbMultiColumnListAdapter{
	
	private Context mContext;
    //列表展现的数据
    private LinkedList<ImageInfo> mImageList;
    //图片下载器
    private AbImageDownloader mAbImageDownloader = null;
    
    
   /**
    * 构造方法
    * @param context
    * @param data 列表展现的数据
    * @param resource 单行的布局
    * @param from Map中的key
    * @param to view的id
    */
    public MultiColumnImageListAdapter(Context context, LinkedList<ImageInfo> imageList){
    	this.mContext = context;
    	this.mImageList = imageList;
    	//图片下载器
        mAbImageDownloader = new AbImageDownloader(mContext);
        mAbImageDownloader.setType(AbImageUtil.SCALEIMG);
    }   
    
    @Override
    public int getCount() {
        return mImageList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return mImageList.get(position);
    }

    @Override
    public long getItemId(int position){
      return position;
    }
    
    @Override
    public AbViewInfo getView(int position, View convertView, ViewGroup parent){
    	  final ViewHolder holder;
          if(convertView == null){
        	   //减少findView的次数
			   holder = new ViewHolder();
	           //使用自定义的list_items作为Layout
	           convertView = LayoutInflater.from(mContext).inflate(R.layout.multi_list_items, parent);
	           
	           //初始化布局中的元素
			   holder.itemsIcon = (ImageView) convertView.findViewById(R.id.itemsIcon);
			   convertView.setTag(holder);
          }else{
        	   holder = (ViewHolder) convertView.getTag();
          }
          
          //获取该行的数据
          ImageInfo  mImageInfo = mImageList.get(position);
		  //图片下载的最大宽高
          if(mImageInfo.getWidth()>600){
        	  mImageInfo.setWidth(mImageInfo.getWidth()/2);
        	  mImageInfo.setHeight(mImageInfo.getHeight()/2);
          }
		  mAbImageDownloader.setWidth(mImageInfo.getWidth());
		  mAbImageDownloader.setHeight(mImageInfo.getHeight());
		  String url = mImageInfo.getUrl();
		  //设置加载中的View
          mAbImageDownloader.setLoadingView(convertView.findViewById(R.id.progressBar));
          mAbImageDownloader.display(holder.itemsIcon,url);
          
          return new AbViewInfo(convertView,mImageInfo.getWidth(),mImageInfo.getHeight());
    }
    
    /**
     * 
     * 描述：添加数据
     * @param items
     * @throws 
     */
    public void addItems(List<ImageInfo> items) {
    	mImageList.addAll(items);
	}

    /**
     * 
     * 描述：添加数据
     * @param items
     * @throws 
     */
	public void addItems(List<ImageInfo> items,boolean clear) {
		if(clear){
			mImageList.clear();
		}
		mImageList.addAll(items);
	}
    
    /**
	 * View元素
	 */
	static class ViewHolder {
		ImageView itemsIcon;
	}
    
}
