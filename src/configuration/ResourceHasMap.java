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
		hm.put("orderNoted", "Giao hàng đúng giờ!");
		hm.put("popupTitle", "DeliveryNow thông báo");
		hm.put("continueorder", "Tiếp tục chọn thêm món");
		hm.put("aceptfee", "Chấp nhận phí dịch vụ là 20,000 đ và tiến hành thanh toán");
		hm.put("checkout_address", "Thời gian & Địa chỉ nhận hàng");
		hm.put("checkout_infoorder", "Thông tin đơn hàng");
		hm.put("checkout_finish", "Hoàn tất");
		hm.put("checkout_city", ", Viet Nam");
		hm.put("checkout_city", ", Việt Nam"); // len live thi bo cai nay di, dung cai tren
		hm.put("checkout_optionconfirm", "Chọn Hình Thức Giao Hàng");
		hm.put("checkout_optiondefault", "Giao hàng chuẩn");
		hm.put("checkout_timereceive", "Thời Gian Nhận Hàng");	
		hm.put("checkout_noted", "Ghi chú cho đơn hàng");
		hm.put("checkout_addlabel", "+ Thêm");
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
