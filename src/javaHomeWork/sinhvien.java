package javaHomeWork;

public class sinhvien
{
	private String mssv,hoten,ngaysinh,lop,khoa,diem;
	public sinhvien()
	{
		
	}
	public sinhvien(String mssv,String hoten,String ngaysinh,String lop, String khoa, String diem)
	{
		this.mssv = mssv;
		this.hoten = hoten;
		this.ngaysinh = ngaysinh;
		this.lop = lop;
		this.khoa = khoa;
		this.diem = diem;
	}
	public void setMssv(String mssv)
	{
		this.mssv = mssv;
	}
	public String getMssv()
	{
		return this.mssv;
	}
	public void setHoten(String hoten)
	{
		this.hoten = hoten;
	}
	public String getHoten()
	{
		return this.hoten;
	}
	public void setNgaysinh(String ngaysinh)
	{
		this.ngaysinh = ngaysinh;
	}
	public String getNgaysinh()
	{
		return this.ngaysinh;
	}
	public void setLop(String lop)
	{
		this.lop = lop;
	}
	public String getLop()
	{
		return this.lop;
	}
	public void setKhoa(String khoa)
	{
		this.khoa = khoa;
	}
	public String getKhoa()
	{
		return this.khoa;
	}
	public void setDiem(String diem)
	{
		this.diem = diem;
	}
	public String getDiem()
	{
		return this.diem;
	}
}
