package com.gxl.common.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;

import com.gxl.biz.ThreadBiz;
import com.gxl.common.Cache;

/**
 * 虚拟会员自动获取任务
 * @author gongxl
 *
 */
public class VtlMbrTask {
	private static final String startIdBoy = "628468660";
	private static final String startIdGirl = "643495167";
	
	private List<String> mbrList = new ArrayList<String>();
	private List<String> mbrDtList = new ArrayList<String>();
	
	private static String getUrl(String id){
		return "http://www.youyuan.com/" + id + "-profile/";
	}
	private ThreadBiz threadBiz;
	private static Integer maxRecs = 50;
	private Integer recs;
	private Integer doRecs;
	private String tempStr;
	private String lastId = "";
	private String nicName = "";
	
	public VtlMbrTask(ThreadBiz threadBiz) throws Exception {
		this.threadBiz = threadBiz;
		this.recs = 0;
		this.doRecs = 0;
		if(Cache.urlMap.isEmpty()){
			Cache.urlMap.put(startIdBoy,1);
			Cache.urlMap.put(startIdGirl,2);
		}
		run();
	}
	private void run(){
		while(!Cache.urlMap.isEmpty()){
			try {
				Thread.currentThread();
				Thread.sleep(50);//睡眠防止卡死
				for(String key : Cache.urlMap.keySet()){
					if(!Cache.vtlMbrIds.contains(key)){
						getPerInfo(key,Cache.urlMap.get(key));
					}
					Cache.urlMap.remove(key);
					break;
				}
				if(recs >= maxRecs){
					System.out.println("=========================正在批量录入"+recs+"条数据========================");
					threadBiz.txAddVtlMbr(mbrList,mbrDtList);
					mbrList.clear();
					mbrDtList.clear();
					System.out.println("=========================录入成功========================");
					doRecs = doRecs + recs;
					recs = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(recs > 0){
			System.out.println("=========================正在批量录入"+recs+"条数据========================");
			try {
				threadBiz.txAddVtlMbr(mbrList,mbrDtList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			mbrList.clear();
			mbrDtList.clear();
			System.out.println("=========================全部录入成功========================");
			doRecs = doRecs + recs;
			System.out.println("=========================本次总共录入"+doRecs+"条数据========================");
			recs = 0;
		}
	}
	
	private void getPerInfo(String id,Integer sex) throws Exception{
		if(lastId.equals(id)){
			return;
		}
		String url = getUrl(id);
		System.out.println("******************************************开始获取第" + recs + "个会员（id："+id+"）信息******************************************");
		Parser parser = new Parser( (HttpURLConnection) (new URL(url)).openConnection());
		nicName = getNickName(parser);//mbr_name
		if("".equals(nicName))return;
		recs = recs + 1;
		mbrList.add(id);//mbr_id
		mbrDtList.add(id);//mbr_id
		mbrList.add(nicName);//mbr_name
		mbrList.add(sex+"");//sex
		getLocalInfo(parser);
		getDetail(parser);
		getPicUrl(parser,id);
		getHoby(parser);
		getSay(parser);
		getIds(parser,sex);
		Cache.vtlMbrIds.add(id);
		mbrList.add("2");
		mbrList.add("有缘");
		lastId = id;
	}
	private void getIds(Parser parser,Integer sex) throws Exception{
		parser.reset();
		NodeList list = parser.parse(getFilter("class|inPerson"));
		for (int i = 0; i < list.size(); i++) {
			Bullet tag = (Bullet)list.elementAt(i);
			String id = tag.getAttribute("data-kd");
			if(!Cache.vtlMbrIds.contains(id)){
				if(!Cache.urlMap.containsKey(id)){
					Cache.urlMap.put(id,sex);
				}
			}
		}
	}
	private void getPicUrl(Parser parser,String id) throws Exception{
		parser.reset();
		Node node = parser.parse(getFilter("class|personal_cen")).elementAt(0).getFirstChild();
		ImageTag tag = (ImageTag)node.getFirstChild();
		String src = tag.getImageURL();
		//System.out.println("头像链接：" + src);
		mbrList.add(id+".jpg");//pic_url
		download(src,id+".jpg","D:\\headPid");
	}
	
	private void download(String urlString, String filename,String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		//设置请求超时为5s
		con.setConnectTimeout(5*1000);
		// 输入流
		InputStream is = con.getInputStream();
		
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf=new File(savePath);
		if(!sf.exists()){
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}
	
	private void getDetail(Parser parser) throws Exception{
		parser.reset();
		NodeFilter filter1 = getFilter("class|black");
		NodeFilter filter2 = new HasChildFilter(filter1);
		NodeList list = parser.parse(filter2);
		if(list == null)return;
		for (int i = 0; i < list.size(); i++) {
			Node node = list.elementAt(i).getFirstChild();
			Node node1 = node.getNextSibling().getFirstChild();
			//System.out.println(node.getText().replaceAll(" ","")+node1.getText().replaceAll(" ",""));
			tempStr = tempStr + "," + node1.getText().replaceAll(" ","");
		}
		String[] arr = tempStr.split(",");
		mbrList.add(arr[1]);//native_place
		mbrList.add(arr[2].replaceAll("斤", ""));//weight
		mbrList.add(Cache.getKeyByVal("education", arr[3]));//education
		mbrList.add(Cache.getKeyByVal("marryStatus", arr[8]));//marryStatus
		mbrList.add(Cache.getKeyByVal("occupation", arr[10]));//occupation

		mbrDtList.add(Cache.getKeyByVal("wantType", arr[5]));//need_child
		mbrDtList.add(Cache.getKeyByVal("canType", arr[12]));//can_yidi
		mbrDtList.add(Cache.getKeyByVal("likeType", arr[6]));//like_type
		mbrDtList.add(Cache.getKeyByVal("canType", arr[13]));//can_sex
		mbrDtList.add(Cache.getKeyByVal("wishType", arr[7]));//with_parents
		mbrDtList.add(Cache.getKeyByVal("bestPart", arr[14]));//best_part
	}
	
	private void getSay(Parser parser) throws Exception{
		parser.reset();
		Node node = parser.parse(getFilter("class|requre")).elementAt(0).getFirstChild().getLastChild().getFirstChild();
		//System.out.println("内心独白：" + node.getText().trim());
		mbrDtList.add(node.getText().trim());//soliloquy
	}
	
	private void getHoby(Parser parser) throws Exception{
		parser.reset();
		NodeList list = parser.parse(getFilter("class|hoby")).elementAt(0).getChildren();
		String hobbys = "";
		if(list == null){
			mbrDtList.add(hobbys);//hobbys
			return;
		}
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			Node node = list.elementAt(i).getFirstChild();
			str = node.getText().trim().replaceAll("&nbsp;", "");
			if ("".equals(hobbys)){
				hobbys = str;
			}else{
				hobbys = hobbys + "," + str;
			}
		}
		mbrDtList.add(hobbys);//hobbys
	}
	
	private void getLocalInfo(Parser parser) throws Exception{
		parser.reset();
		Node node = parser.parse(getFilter("class|local")).elementAt(0).getFirstChild();
		String list = node.getText();
		String [] arr = list.split("\\s+");
		mbrList.add(arr[1]);//age
		mbrList.add(arr[2].replaceAll("cm", ""));//height
		mbrList.add(Cache.getKeyByVal("monthIncome", arr[3]));//month_income
		mbrList.add(Cache.getKeyByVal("havaHouse", arr[4]));//hava_house
		mbrList.add(arr[0]);//location
		/*System.out.println("所在地：" + arr[0]);
		System.out.println("年龄：" + arr[1]);
		System.out.println("身高：" + arr[2]);
		System.out.println("月收入：" + arr[3]);
		System.out.println("是否有房：" + arr[4]);*/
	}
	private String getNickName(Parser parser){
		String str = "";
		parser.reset();
		Node node;
		try {
			node = parser.parse(getFilter("div|class|main")).elementAt(0).getChildren().elementAt(1);
			str = node.getText();	
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return str;
	}
	
	private NodeFilter getFilter(String param){
		String [] arr = param.split("\\|");
		if(arr.length == 1){
			return new TagNameFilter (arr[0]);
		}else if(arr.length == 2){
			return new HasAttributeFilter(arr[0],arr[1]);
		}else if(arr.length == 3){
			NodeFilter filter1 = new TagNameFilter (arr[0]);
			NodeFilter filter2 = new HasAttributeFilter(arr[1],arr[2]);
			return new AndFilter(filter1, filter2);
		}else{
			return null;
		}
	}
}