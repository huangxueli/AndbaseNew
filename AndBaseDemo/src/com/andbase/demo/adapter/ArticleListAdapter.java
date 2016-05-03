package com.andbase.demo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.bitmap.AbImageDownloader;
import com.ab.util.AbImageUtil;
import com.andbase.R;
import com.andbase.demo.model.Article;

/**
 * © 2012 amsoft.cn 
 * 名称：ArticleListAdapter 
 * 描述：文章对象自定义Adapter例子
 * @author 还如一梦中
 * @date 2011-11-8
 * @version
 */
public class ArticleListAdapter extends BaseAdapter{
  
	private Context mContext;
    //列表展现的数据
    private List<Article> mList;
    //图片下载器
    private AbImageDownloader mAbImageDownloader = null;
    
   /**
    * 构造方法
    * @param context
    * @param list 列表展现的数据
    */
    public ArticleListAdapter(Context context, List<Article> list){
    	 mContext = context;
    	 mList = list;
         //图片下载器
         mAbImageDownloader = new AbImageDownloader(mContext);
         mAbImageDownloader.setWidth(200);
         mAbImageDownloader.setHeight(200);
         mAbImageDownloader.setLoadingImage(R.drawable.image_loading);
         mAbImageDownloader.setErrorImage(R.drawable.image_error);
         mAbImageDownloader.setNoImage(R.drawable.image_no);
         mAbImageDownloader.setType(AbImageUtil.SCALEIMG);
    }   
    
    @Override
    public int getCount() {
    	if(mList==null){
    		return 0;
    	}
        return mList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position){
      return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    	  final ViewHolder holder;
          if(convertView == null){
	          //使用自定义的list_items作为Layout
	          convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
	          //使用减少findView的次数
			  holder = new ViewHolder();
			  holder.itemsIcon = ((ImageView) convertView.findViewById(R.id.itemsIcon)) ;
			  holder.itemsTitle = ((TextView) convertView.findViewById(R.id.itemsTitle));
			  holder.itemsText = ((TextView) convertView.findViewById(R.id.itemsText));
			  //设置标记
			  convertView.setTag(holder);
          }else{
        	  holder = (ViewHolder) convertView.getTag();
          }
          
          //获取该行数据
          Article mArticle = (Article)mList.get(position);
          
          //设置数据到View
          String imageUrl = (String)mArticle.getImageUrl();
          //设置加载中的View
          mAbImageDownloader.setLoadingView(convertView.findViewById(R.id.progressBar));
          //图片的下载
          mAbImageDownloader.display(holder.itemsIcon,imageUrl);
          holder.itemsTitle.setText(mArticle.getTitle());
          holder.itemsText.setText(mArticle.getCreateTime());
          return convertView;
    }
    
    /**
	 * ViewHolder类
	 */
	static class ViewHolder {
		ImageView itemsIcon;
		TextView itemsTitle;
		TextView itemsText;
	}
}
