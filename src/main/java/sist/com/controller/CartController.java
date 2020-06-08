package sist.com.controller;

import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sist.com.bean.AdminProductBean;
import sist.com.dao.CartDao;

@Controller
public class CartController {
	
	
	@Autowired
	private CartDao dao;
	
	@RequestMapping(value="adminLogin.do")
	public String adminLoginCheck(String id,String pw,HttpSession session) {
		session.setAttribute("id", id);
		return dao.adminLoginCheck(id, pw)?"redirect:adminProductList.do":"redirect:admin/jsp/adminLogin.jsp";
	}
	
	@RequestMapping(value="adminProductList.do")
	public String productListProcess(Model model,HttpSession session) {
		model.addAttribute("adminList",dao.selectAdmin(((String)session.getAttribute("id"))));
		return "cart/productAdd";
	}
	
	@RequestMapping(value="productInsert.do")
	public String insertProductAdmin(AdminProductBean bean,@RequestParam(value="file",required=false)MultipartFile file) {
		String locations="C:\\workspace\\Cart\\web\\src\\main\\webapp\\cartUp\\";
		FileOutputStream fos=null;
		String fileName=file.getOriginalFilename();
		if(fileName.length()>0) {
			try {
				fos=new FileOutputStream(locations.concat(fileName));
				fos.write(file.getBytes());
				bean.setFileName(fileName);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					if(fos!=null)fos.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}//upload
		bean.setPk(bean.getJob().startsWith("modify")?Integer.parseInt(bean.getJob().split("#")[1]):0);
		if(fileName.length()==0&&bean.getJob().equals("new")) {
			bean.setFileName("0.jpg");
		}
		System.out.println(bean);
		if(bean.getJob().equals("new")) {
			dao.insertProduct(bean);
		}else{
			dao.updateProduct(bean);
		}
		return "redirect:adminProductList.do";
	}
	
	@RequestMapping(value="productDelete.do")
	public String deleteProductAdmin(int pk) {
		dao.deleteProduct(pk);
		return "redirect:adminProductList.do";
	}
	
	@RequestMapping(value="chartAction.do")
	public String chartProcess(Model model) {
		model.addAttribute("title","¹¶Ä¡ ÀÏ±â");
		
		model.addAttribute("chartList",dao.selectAdmin("BLUE"));
		
		return "cart/chart";
	}
	
	/*@RequestMapping(value="adminModify.do")
	public String modifyProductAdmin(AdminProductBean bean,@RequestParam(value="file",required=false)MultipartFile file) {
		System.out.println(bean+"Modify");
		System.out.println(file);
		return "redirect:adminProductList.do";
	}*/
	
	@RequestMapping(value="clientList.do")
	public String clientProcessList(Model model) {
		model.addAttribute("clientList",dao.selectClient());
		return "cart/productList";
	}
	
	

}
