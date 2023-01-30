package com.iu.home.product;

public class ProductService {

	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductName("product1");
		productDTO.setProductDetail("detail1");
		
		ProductOptinDTO productOptinDTO = new ProductOptinDTO();
		productOptinDTO.setOptionName("optionName1");
		productOptinDTO.setOptionPrice(100L);
		productOptinDTO.setOptionAmount(10L);
		productOptinDTO.setProductNum(null);
		
		ProductOptinDTO productOptinDTO2 = new ProductOptinDTO();
		productOptinDTO2.setOptionName("optionName2");
		productOptinDTO2.setOptionPrice(200L);
		productOptinDTO2.setOptionAmount(20L);
		productOptinDTO2.setProductNum(null);
		
		try {
			Long num = productDAO.getProductNum();
			
			productDTO.setProductNum(num);
			
			int result = productDAO.setAddProduct(productDTO);
			
			productOptinDTO.setProductNum(num);
			
			if(result>0) {
				productDAO.setAddProductOption(productOptinDTO);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
