package configuration;

import java.util.HashMap;

//import java.awt.List;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;

public class ResourceHasMap {
	private HashMap<String, String> hm = new HashMap<String, String>();

	public ResourceHasMap() {
		genResource();
		orderResource();		
		dayOffWeek();
	}

	private void genResource() {
		hm.put("noPass", "Vui lòng nhập mật khẩu");
		
	}
	
	private void searchResource(){
		hm.put("banhcanhsieungon", "Bánh canh siêu ngon");		
	}
	
	private void orderResource(){
		hm.put("setVi", "phần");
		hm.put("peopleVi", "người");
		hm.put("titleMesg", "DeliveryNow thông báo");
		hm.put("contentMesg", "Bạn vui lòng chọn món ăn sẽ gọi.");
		hm.put("buttonName", "Đồng ý");
		hm.put("buttonName", "Chính xác");
		hm.put("orderNoted", "Giao hàng đúng giờ!");
		
	}
	
	private void dayOffWeek(){
		hm.put("mon", "Thứ hai");
		hm.put("tue", "Thứ ba");
		hm.put("web", "Thứ tư");
		hm.put("thu", "Thứ năm");
		hm.put("fri", "Thứ sáu");
		hm.put("sat", "Thứ bảy");
		hm.put("sun", "Chủ nhật");
		hm.put("lbl_Day", "ngày ");
		hm.put("lbl_Month", "tháng ");
		hm.put("lbl_Year", "năm ");
	}

	public String getResource(String key){
		return getHm().get(key);
	}

	public HashMap<String, String> getHm() {
		return hm;
	}

	public void setHm(HashMap<String, String> hm) {
		this.hm = hm;
	}
}
