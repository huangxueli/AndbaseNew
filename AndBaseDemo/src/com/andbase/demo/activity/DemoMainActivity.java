package com.andbase.demo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.andbase.R;
import com.andbase.demo.adapter.MyListViewAdapter;
import com.andbase.global.Constant;
import com.andbase.global.MyApplication;
/**
 * 
 * © 2012 amsoft.cn
 * 名称：DemoMainActivity.java 
 * 描述：Demo主界面
 * @author 还如一梦中
 * @date：2013-12-13 上午11:01:21
 * @version v1.0
 */
public class DemoMainActivity extends AbActivity {
	
	private MyApplication application;
	private ListView mListView = null;
	private MyListViewAdapter myListViewAdapter = null;
	private List<Map<String, Object>> list = null;
	private ArrayList<String> mPhotoList = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.main);

		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.demo_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		// mAbTitleBar.setVisibility(View.GONE);
		// 设置AbTitleBar在最上
		this.setTitleBarOverlay(true);
		application = (MyApplication) abApplication;
		mAbTitleBar.getLogoView().setOnClickListener(
			new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
		});
		
		for (int i = 0; i < 75; i++) {
	            mPhotoList.add(Constant.BASEURL+"content/templates/amsoft/images/head/"+i+".png");
	    }

		// 获取ListView对象
		mListView = (ListView) findViewById(R.id.mListView);
		// ListView数据
		list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(0));
		map.put("itemsTitle", "1.AbActivity基本用法");
		map.put("itemsText", "AbActivity使用示例");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(1));
		map.put("itemsTitle", "2.数据库ORM");
		map.put("itemsText", "注解，数据库对象映射");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(2));
		map.put("itemsTitle", "3.IOC 适配View");
		map.put("itemsText", "像findViewById说no");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(3));
		map.put("itemsTitle", "4.Http工具类");
		map.put("itemsText", "网络通信首选1");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(4));
		map.put("itemsTitle", "5.Soap工具类");
		map.put("itemsText", "网络通信首选2");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(5));
		map.put("itemsTitle", "6.线程池与线程队列");
		map.put("itemsText", "适应于Http工具类管理范围外，更灵活的应用");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(6));
		map.put("itemsTitle", "7.图片下载与处理");
		map.put("itemsText", "图片下载,裁剪,缩放");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(7));
		map.put("itemsTitle", "8.下拉刷新与分页查询");
		map.put("itemsText", "支持下拉刷新，上拉加载下一页");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(8));
		map.put("itemsTitle", "9.常规表格");
		map.put("itemsText", "多能适配的表格（支持文本，图片，复选框）");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(9));
		map.put("itemsTitle", "10.滑动按钮");
		map.put("itemsText", "滑动按钮");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(10));
		map.put("itemsTitle", "11.图片联播");
		map.put("itemsText", "图片联播,View播放");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(11));
		map.put("itemsTitle", "12.下载器");
		map.put("itemsText", "多线程，断点续传");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(12));
		map.put("itemsTitle", "13.动画欢迎页面");
		map.put("itemsText", "从远到近的显示的图片切换");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(13));
		map.put("itemsTitle", "14.侧边栏");
		map.put("itemsText", "左右侧边栏");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(14));
		map.put("itemsTitle", "15.Tab切换");
		map.put("itemsText", "可滑动的tab标签,顶部和底部");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(15));
		map.put("itemsTitle", "16.仿Iphone轮子选择控件");
		map.put("itemsText", "仿Iphone轮子选择控件");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(16));
		map.put("itemsTitle", "17.拍照和相册选取图片");
		map.put("itemsText", "拍照和相册选取图片");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(17));
		map.put("itemsTitle", "18.图表");
		map.put("itemsText", "线状图，柱状图，饼状图，等级条图");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(18));
		map.put("itemsTitle", "19.日历选择器");
		map.put("itemsText", "日历选择器哦");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(19));
		map.put("itemsTitle", "20.图片相近搜索");
		map.put("itemsText", "phash算法");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(20));
		map.put("itemsTitle", "21.旋转木马");
		map.put("itemsText", "旋转木马");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(21));
		map.put("itemsTitle", "22.水平，环形进度条");
		map.put("itemsText", "漂亮的水平，环形进度条控件");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(22));
		map.put("itemsTitle", "23.3D翻转效果");
		map.put("itemsText", "2013纪念币,3D切换界面");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(23));
		map.put("itemsTitle", "24.各种滑动嵌套问题");
		map.put("itemsText", "各种滑动嵌套问题的解决例子");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(24));
		map.put("itemsTitle", "25.场景化UI");
		map.put("itemsText", "这玩意很流行");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(25));
		map.put("itemsTitle", "26.Pop提示框");
		map.put("itemsText", "挺实用，小提示");
		list.add(map);

		// 使用自定义的Adapter
		myListViewAdapter = new MyListViewAdapter(DemoMainActivity.this, list,
				R.layout.item_list, new String[] { "itemsIcon", "itemsTitle",
						"itemsText" }, new int[] { R.id.itemsIcon,
						R.id.itemsTitle, R.id.itemsText });
		mListView.setAdapter(myListViewAdapter);
		// item被点击事件
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = null;
				switch (position) {
				case 0:
					intent = new Intent(DemoMainActivity.this, DemoAbActivity.class);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(DemoMainActivity.this, DBActivity.class);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(DemoMainActivity.this, IocViewActivity.class);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(DemoMainActivity.this, HttpActivity.class);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(DemoMainActivity.this, SoapActivity.class);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent(DemoMainActivity.this, ThreadControlActivity.class);
					startActivity(intent);
					break;
				case 6:
					intent = new Intent(DemoMainActivity.this, ImageDownActivity.class);
					startActivity(intent);
					break;
				case 7:
					intent = new Intent(DemoMainActivity.this, PullToRefreshActivity.class);
					startActivity(intent);
					break;
				case 8:
					intent = new Intent(DemoMainActivity.this, TableActivity.class);
					startActivity(intent);
					break;
				case 9:
					intent = new Intent(DemoMainActivity.this, SlidingButtonActivity.class);
					startActivity(intent);
					break;
				case 10:
					intent = new Intent(DemoMainActivity.this,SlidingPlayViewActivity.class);
					startActivity(intent);
					break;
				case 11:
					intent = new Intent(DemoMainActivity.this, DownListActivity.class);
					startActivity(intent);
					break;
				case 12:
					intent = new Intent(DemoMainActivity.this, WelcomeActivity.class);
					startActivity(intent);
					break;
				case 13:
					intent = new Intent(DemoMainActivity.this, SlidingMenuActivity.class);
					startActivity(intent);
					break;
				case 14:
					intent = new Intent(DemoMainActivity.this, TabActivity.class);
					startActivity(intent);
					break;
				case 15:
					intent = new Intent(DemoMainActivity.this, WheelActivity.class);
					startActivity(intent);
					break;
				case 16:
					intent = new Intent(DemoMainActivity.this, AddPhotoActivity.class);
					startActivity(intent);
					break;
				case 17:
					intent = new Intent(DemoMainActivity.this, ChartActivity.class);
					startActivity(intent);
					break;
				case 18:
					intent = new Intent(DemoMainActivity.this, CalendarActivity.class);
					startActivity(intent);
					break;
				case 19:
					intent = new Intent(DemoMainActivity.this, PHashActivity.class);
					startActivity(intent);
					break;
				case 20:
					intent = new Intent(DemoMainActivity.this, CarouselActivity.class);
					startActivity(intent);
					break;
				case 21:
					intent = new Intent(DemoMainActivity.this, ProgressBarActivity.class);
					startActivity(intent);
					break;
				case 22:
					intent = new Intent(DemoMainActivity.this, Rotate3DActivity.class);
					startActivity(intent);
					break;
				case 23:
					intent = new Intent(DemoMainActivity.this, NestScrollActivity.class);
					startActivity(intent);
					break;
				case 24:
					intent = new Intent(DemoMainActivity.this, SceneActivity.class);
					startActivity(intent);
					break;
				case 25:
					intent = new Intent(DemoMainActivity.this, PopoverActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
	}

}
