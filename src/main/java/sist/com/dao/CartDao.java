package sist.com.dao;




import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import sist.com.bean.AdminProductBean;



@Component
public class CartDao extends SqlSessionDaoSupport {

	@Resource
	protected void initDao(SqlSessionTemplate st) throws Exception {
		// TODO Auto-generated method stub
		this.setSqlSessionTemplate(st);
	}
	public boolean adminLoginCheck(String id,String pw) {
		String dbPass=this.getSqlSession().selectOne("adminLoginCheck",id);
		return dbPass!=null&&dbPass.equals(pw);
	}
	
	public List<AdminProductBean>selectAdmin(String id){
		return this.getSqlSession().selectList("selectAdmin",id);
	}
	
	public void insertProduct(AdminProductBean bean) {
		this.getSqlSession().insert("insertProduct",bean);
	}
	
	public void deleteProduct(int no) {
		this.getSqlSession().delete("deleteProduct",no);
	}
	
	public Object adminProductInfo(int pk) {
		return this.getSqlSession().selectOne("adminProductInfo",pk);
	}
	
	public void updateProduct(AdminProductBean bean) {
		this.getSqlSession().update("updateProduct",bean);
	}
	
	public List<AdminProductBean>selectClient(){
		return this.getSqlSession().selectList("selectClient");
	}
	

	

}
