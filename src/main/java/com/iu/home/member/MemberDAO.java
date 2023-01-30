package com.iu.home.member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.iu.home.util.DBConnection;

public class MemberDAO {
	
	//setAddMember
	public int setAddMember(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnection.getConnection();
		
		String sql= "INSERT INTO MEMBER (ID, PW, NAME, ADDRESS, PHONE, EMAIL) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getAddress());
		st.setString(5, memberDTO.getPhone());
		st.setString(6, memberDTO.getEmail());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnection(st, con);
		
		return result;
		
	}
	
	public static void main(String[] args) {
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("id1");
		memberDTO.setPw("pw1");
		memberDTO.setName("name1");
		memberDTO.setAddress("address1");
		memberDTO.setPhone("phone1");
		memberDTO.setEmail("Email1");
		try {
			int result = memberDAO.setAddMember(memberDTO);
			System.out.println(result == 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
