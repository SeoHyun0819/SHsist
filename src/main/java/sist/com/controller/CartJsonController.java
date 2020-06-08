package sist.com.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sist.com.dao.CartDao;

@RestController
public class CartJsonController {
	
	@Autowired
	private CartDao dao;
	
	@RequestMapping(value="adminInfo.do")
	public Object adminInfo(int pk) {
		return dao.adminProductInfo(pk);
	}

}
