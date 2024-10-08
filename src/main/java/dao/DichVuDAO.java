package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MSSQLConnection;
import entity.LoaiDichVu;
import entity.DichVu;

public class DichVuDAO {

	/**
	 * lấy tất cả sản phẩm hiện có trong csdl
	 * 
	 * @return danh sách sản phẩm
	 */
	public List<DichVu> getDanhSachSanPham() {
		LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
		List<DichVu> listSanPham = new ArrayList<DichVu>();

		String sql = "SELECT * FROM San_Pham";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				int ma = rs.getInt(1);
				String maSanPham = "SP" + ma;
				String tenSP = rs.getString(2);
				double giaTien = rs.getDouble(3);

				String maLoaiSanPham = rs.getString(4);
				LoaiDichVu loaiSanPham = loaiSanPhamDAO.getLoaiSanPhamTheoMaLoai(maLoaiSanPham);

				DichVu sanPham = new DichVu(maSanPham, tenSP, giaTien);
				sanPham.setLoaiSanPham(loaiSanPham);

				listSanPham.add(sanPham);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listSanPham;
	}

	/**
	 * Insert 1 sản phẩm vào cơ sở dữ liệu
	 * 
	 * @param sp tham số truyền vào là SanPham
	 * @return trả về true nếu insert thành công trả về false nếu insert thất bại
	 * @throws IOException
	 */
	public boolean addSanPham(DichVu sanPham) {
		String sql = "INSERT INTO [dbo].[San_Pham] ([TenSanPham],[DonGia],[MaLoaiSP])" + " VALUES(?,?,?)";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;
		try {

			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, sanPham.getTenDichVu());
			prepareStatement.setDouble(2, sanPham.getDonGia());

			String ma = sanPham.getLoaiDichVu().getMaLoaiDV().replace("LDV", "");
			prepareStatement.setInt(3, Integer.parseInt(ma));

			rs = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs > 0;
	}

	/**
	 * update thông tin cho 1 sản phẩm
	 * 
	 * @param sp sản phẩm cần update
	 * @return
	 */
	public boolean updateSanPham(DichVu sp) {
		String sql = "UPDATE San_Pham SET TenSanPham=?, DonGia=?, MaLoaiSP=?" + " where MaSanPham = ?";
		int rs = 0;

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;
		try {

			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, sp.getTenSanPham());
			prepareStatement.setDouble(2, sp.getDonGia());
			int ma = Integer.parseInt(sp.getLoaiSanPham().getMaLoaiSP().replaceAll("LSP", ""));
			prepareStatement.setInt(3, ma);

			String maSanPham = sp.getMaSanPham();
			String msp = maSanPham.replaceAll("SP", "");

			prepareStatement.setInt(4, Integer.parseInt(msp));
			rs = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rs > 0;
	}

	/**
	 * Lấy 1 sản phẩm theo mã
	 * 
	 * @param maSanPham
	 * @return SanPham
	 */
	public DichVu getSanPhamTheoMa(String maSanPham) {
		DichVu sanPham = new DichVu();
		LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
		String sql = "SELECT * FROM San_Pham " + " where MaSanPham = ?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			String msp = maSanPham.replaceAll("SP", "");
			prepareStatement.setInt(1, Integer.parseInt(msp));

			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				sanPham.setMaSanPham(rs.getString(1));
				sanPham.setTenSanPham(rs.getString(2));
				sanPham.setDonGia(rs.getDouble(3));

				LoaiDichVu loaiSanPham = loaiSanPhamDAO.getLoaiSanPhamTheoMaLoai(rs.getString("MaLoaiSP"));

				sanPham.setLoaiSanPham(loaiSanPham);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return sanPham;
	}

	/**
	 * Lấy 1 sản phẩm theo tên
	 * 
	 * @param tenSanPham
	 * @return SanPham
	 */
	public DichVu getSanPhamTheoTen(String tenSanPham) {
		DichVu sanPham = new DichVu();
		LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
		String sql = "SELECT * FROM San_Pham " + " where TenSanPham = ?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;
		try {

			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tenSanPham);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				sanPham.setMaSanPham("SP" + rs.getInt("MaSanPham"));
				sanPham.setTenSanPham(rs.getString("TenSanPham"));
				sanPham.setDonGia(rs.getDouble("DonGia"));

				int ma = rs.getInt("MaLoaiSP");
				String maLoaiSP = "LSP" + ma;

				LoaiDichVu loaiSanPham = loaiSanPhamDAO.getLoaiSanPhamTheoMaLoai(maLoaiSP);
				sanPham.setLoaiSanPham(loaiSanPham);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sanPham;

	}

	/**
	 * xóa thông tin của 1 sản phẩm theo mã
	 * 
	 * @param mã sản phẩm cần xóa
	 * @return true or false
	 */
	public boolean deleteSanPham(String maSanPham) {
		String sql = "DELETE FROM San_Pham" + " where MaSanPham = ?";
		int rs = 0;
		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, maSanPham);

			rs = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs > 0;
	}
	
	//kiểm tra trùng
	public boolean checkExist(String tenSanPham) {
		boolean check = false;
		String sql = "select * from San_Pham where TenSanPham = ?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tenSanPham);
			ResultSet rs = prepareStatement.executeQuery();
			check = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
	}
	
	public List<String> getSanPhamTheoLoai(String tenLoaiSP) {
		List<String> dsTenSanPham = new ArrayList<String>();
		String sql = "SELECT        San_Pham.TenSanPham\r\n"
				+ "FROM            Loai_SP INNER JOIN\r\n"
				+ "                         San_Pham ON Loai_SP.MaLoaiSP = San_Pham.MaLoaiSP\r\n"
				+ "where Loai_SP.TenLoaiSP =?";

		Connection con = MSSQLConnection.getJDBCConnection();
		PreparedStatement prepareStatement = null;
		try {

			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tenLoaiSP);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				dsTenSanPham.add(rs.getString("TenSanPham"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsTenSanPham;

	}
}
