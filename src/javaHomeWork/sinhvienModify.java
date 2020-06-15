package javaHomeWork;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

import javaHomeWork.sinhvien;
public class sinhvienModify 
{
	public static Connection getJDBC()
	{
		String url  = "jdbc:sqlserver://localhost:1433;databasename=quanlisinhvien;integratedSecurity=true";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//System.out.println("OK");
			return DriverManager.getConnection(url);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	public static  List<sinhvien> findAll()
	{
		List<sinhvien> sinhvienList = new ArrayList<sinhvien>();
		//lay danh sach sinh vien
		Connection con = getJDBC();
		Statement stm = null;
		
		try {
			// truy van
			String sql ="select * from 	SINHVIEN";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next())
			{
				sinhvien sv = new sinhvien(rs.getString("mssv"), rs.getString("hoten"), 
						rs.getString("ngaysinh"), rs.getString("lop"), rs.getString("khoa"), rs.getString("diem"));
				sinhvienList.add(sv);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(stm!=null)
			{
				try {
					stm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sinhvienList;
	}
	public static void Insert(sinhvien sv)
	{
	
		Connection con = getJDBC();
		PreparedStatement stm = null;
		
		try {
			// truy van
			String sql ="insert into SINHVIEN(mssv,hoten,ngaysinh,lop,khoa,diem) values(?,?,?,?,?,?)";
			stm = con.prepareCall(sql);
			if(sv.getMssv().equals("") == true)
			{
				JOptionPane.showMessageDialog(null, "Please enter MSSV !");
				return;
			}
			if(sv.getHoten().equals("")== true)
			{
				JOptionPane.showMessageDialog(null, "Pleas enter student's name !");
				return;
			}
			if(sv.getNgaysinh().equals("")== true)
			{
				JOptionPane.showMessageDialog(null, "Please enter student's date !");
			}
			String date = sv.getNgaysinh();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
			Date date1 = null;
			try {
				 date1 = df.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
			 JOptionPane.showMessageDialog(null, "Please enter date in the form (dd/MM/yyyyy)");
			 return;
			}
			
			stm.setString(1, sv.getMssv());
			stm.setString(2, sv.getHoten());
			stm.setString(3, df2.format(date1));
			stm.setString(4, sv.getLop());
			stm.setString(5, sv.getKhoa());
			stm.setString(6, sv.getDiem());
			stm.execute();
			JOptionPane.showMessageDialog(null, "Insert success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(stm!=null)
			{
				try {
					stm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	
	}
	public static void Update(sinhvien sv)
	{
		Connection con = getJDBC();
		PreparedStatement stm = null;
		try {
			// truy van
			String sql ="update SINHVIEN set hoten=?,ngaysinh=?,lop=?,khoa=?,diem=? where mssv = ?";
			stm = con.prepareCall(sql);
			if(sv.getHoten().equals("")== true)
			{
				JOptionPane.showMessageDialog(null, "Pleas enter student's name !");
				return;
			}
			if(sv.getNgaysinh().equals("")== true)
			{
				JOptionPane.showMessageDialog(null, "Please enter student's date !");
			}
			String date = sv.getNgaysinh();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
			Date date1 = null;
			try {
				date1 = df.parse(date);
			}
			catch (ParseException e) 
			{
				// TODO Auto-generated catch block
			 //System.out.println("Parse error");
			 JOptionPane.showMessageDialog(null, "Please enter date in the form (dd/MM/yyyy)");
			 return;
			}
			
			stm.setString(1, sv.getHoten());
			stm.setString(2, df2.format(date1));
			stm.setString(3, sv.getLop());
			stm.setString(4, sv.getKhoa());
			stm.setString(5, sv.getDiem());
			stm.setString(6, sv.getMssv());
			stm.execute();
			JOptionPane.showMessageDialog(null, "Update success!!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			if(stm!=null)
			{
				try {
					stm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void Delete(String ms)
	{
		Connection con = getJDBC();
		PreparedStatement stm = null;
		try {
			// truy van
			String sql ="delete from SINHVIEN where mssv = ?";
			stm = con.prepareCall(sql);
			stm.setString(1, ms);
			stm.execute();
			JOptionPane.showMessageDialog(null, "Delete success");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(stm!=null)
			{
				try {
					stm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static List<sinhvien> searchByMSSV(String mssv)
	{
		List<sinhvien> listsv = new ArrayList<sinhvien>();
		Connection con = getJDBC();
		PreparedStatement stm = null;
		try {
			//con =  DriverManager.getConnection(url);
			String sql = "select * from SINHVIEN where mssv = ?";
			stm = con.prepareStatement(sql);
			stm.setString(1, mssv);
			ResultSet rs = stm.executeQuery();
			while(rs.next())
			{
				String ms = rs.getString("mssv");
				String hoten = rs.getString("hoten");
				String ngaysinh = rs.getString("ngaysinh");
				String lop = rs.getString("lop");
				String khoa = rs.getString("khoa");
				String diem = rs.getString("diem");
				sinhvien sv = new sinhvien(ms, hoten, ngaysinh, lop, khoa, diem);
				listsv.add(sv);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			if(stm!=null)
			{
				try {
					stm.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		return listsv;
	}
}
