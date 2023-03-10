package com.iu.home.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.home.util.DBConnection;

public class ProductDAO {
	//getMax
	public Long getProductNum()throws Exception{
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCT_SEQ.NEXTVAL FROM DUAL";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		Long num = rs.getLong(1);
		
		DBConnection.disConnection(rs, st, con);
		
		return num;
		
	}
	
	//--------------------------------------
	public List<ProductOptinDTO> getProductOptionList()throws Exception{
		List<ProductOptinDTO> ar = new ArrayList<ProductOptinDTO>();
		
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT * FROM PRODUCTOPTION";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductOptinDTO productOptinDTO = new ProductOptinDTO();
			productOptinDTO.setOptionNum(rs.getLong("OPTIONNUM"));
			productOptinDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productOptinDTO.setOptionName(rs.getString("OPTIONNAME"));
			productOptinDTO.setOptionPrice(rs.getLong("OPTIONPRICE"));
			productOptinDTO.setOptionAmount(rs.getLong("OPTIONAMOUNT"));
			ar.add(productOptinDTO);
		}
		
		DBConnection.disConnection(rs,  st, con);
		
		return ar;
	}
	
	public int setAddProductOption(ProductOptinDTO productOptinDTO) throws Exception {
		Connection con = DBConnection.getConnection();
		
		String sql="INSERT INTO PRODUCTOPTION (OPTIONNUM, PRODUCTNUM, OPTIONNAME, OPTIONPRICE, OPTIONAMOUNT) "
				+ "VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, productOptinDTO.getProductNum());
		st.setString(2, productOptinDTO.getOptionName());
		st.setLong(3, productOptinDTO.getOptionPrice());
		st.setLong(4, productOptinDTO.getOptionAmount());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnection(st, con);
		
		return result;
	}
	
	
	
	//--------------------------------------
	
	public List<ProductDTO> getProductList()throws Exception{
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCTNUM, PRODUCTNAME, PRODUCTJUMSU "
				+ "FROM PRODUCT ORDER BY PRODUCTJUMSU DESC";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productDTO.setProductName(rs.getString("PRODUCTNAME"));
			productDTO.setProductJumsu(rs.getDouble("PRODUCTJUMSU"));
			ar.add(productDTO);
		}
		
		DBConnection.disConnection(rs, st, con);
		
		return ar;
	}
	
	public int setAddProduct(ProductDTO productDTO)throws Exception{
		Connection con = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCT (PRODUCTNUM, PRODUCTNAME, PRODUCTDETAIL, PRODUCTJUMSU) "
				+ "VALUES (?,?,?,0.0)";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, productDTO.getProductNum());
		st.setString(2, productDTO.getProductName());
		st.setString(3, productDTO.getProductDetail());
		//st.setDouble(3, productDTO.getProductJumsu());
		
		int result = st.executeUpdate();
		DBConnection.disConnection(st, con);
		return result;	
	}
	
}
