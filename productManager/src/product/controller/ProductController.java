package product.controller;

import java.util.ArrayList;

import product.model.dao.ProductDAO;
import product.model.vo.Product;
import product.view.ProductView;

public class ProductController {
	public ProductController(){}
	
	public Product selectOne(String id){
		Product p = new ProductDAO().selectOne(id);
		if(p != null) System.out.println("데이터 조회에 성공하였습니다.");
		else  System.out.println("데이터 조회에 성공하였습니다.");
		return p;
	}
	
	public ArrayList<Product> selectAll(){
		ArrayList<Product> pList = new ProductDAO().selectAll();
		if(pList != null) System.out.println("데이터 조회에 성공하였습니다.");
		else  System.out.println("데이터 조회에 성공하였습니다.");
		return pList;
	}
	
	public int insertProduct(Product p){
		int result = new ProductDAO().insertProduct(p);
		
		if(result > 0){
			// 성공
			System.out.println(result+"행의 추가가 완료되었습니다.");
			new ProductView().printOne(selectOne(p.getProductId()));
		} else {
			// 실패
			System.out.println("실패하였습니다.");
		}
		return result;
	}
	
	public int updateProduct(Product p){
		int result = new ProductDAO().updateProduct(p.getProductId(), p.getPrice());
		
		if(result > 0){
			// 성공
			System.out.println(result+"행 수정이 완료되었습니다.");
		} else {
			// 실패
			System.out.println("실패하였습니다.");
		}
		return result;
	}
	
	public int deleteProduct(String id){
		int result = new ProductDAO().deleteProduct(id);
		
		if(result > 0){
			// 성공
			System.out.println(result+"행 삭제가 완료되었습니다.");
		} else {
			// 실패
			System.out.println("실패하였습니다.");
		}
		return result;
	}
	// 오버로딩한 에러로만 가져 오자!!
	public void errors(int a){
		if(a > 0) System.out.println("성공하였습니다.");
		else System.out.println("실패하였습니다.");
	}
	public void errors(Product a){
		if(a != null) System.out.println("성공하였습니다.");
		else System.out.println("실패하였습니다.");
	}
	
}