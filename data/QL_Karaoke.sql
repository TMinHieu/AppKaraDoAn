USE [master]
GO
CREATE DATABASE [TheKaraoke]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TheKaraoke', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\TheKaraoke.mdf', SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'TheKaraoke_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\TheKaraoke_log.ldf', SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TheKaraoke].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TheKaraoke] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TheKaraoke] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TheKaraoke] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TheKaraoke] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TheKaraoke] SET ARITHABORT OFF 
GO
ALTER DATABASE [TheKaraoke] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TheKaraoke] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TheKaraoke] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TheKaraoke] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TheKaraoke] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TheKaraoke] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TheKaraoke] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TheKaraoke] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TheKaraoke] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TheKaraoke] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TheKaraoke] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TheKaraoke] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TheKaraoke] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TheKaraoke] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TheKaraoke] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TheKaraoke] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TheKaraoke] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TheKaraoke] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [TheKaraoke] SET  MULTI_USER 
GO
ALTER DATABASE [TheKaraoke] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TheKaraoke] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TheKaraoke] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TheKaraoke] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [TheKaraoke] SET DELAYED_DURABILITY = DISABLED 
GO
USE [TheKaraoke]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Chi_Tiet_Hoa_Don](
	[MaHoaDon] [int] NOT NULL,
	[MaSanPham] [int] NOT NULL,
	[SoLuong] [int] NOT NULL,
	[MaPhong] [int] NOT NULL,
 CONSTRAINT [PK_Chi_Tiet_Hoa_Don_1] PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC,
	[MaSanPham] ASC,
	[MaPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Don_Dat_Phong](
	[MaDatPhong] [int] IDENTITY(1,1) NOT NULL,
	[ThoiGianVao] [datetime] NOT NULL,
	[MaKhachHang] [int] NOT NULL,
	[MaPhong] [int] NOT NULL,
	[TrangThaiDon] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_DonDatPhong] PRIMARY KEY CLUSTERED 
(
	[MaDatPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hoa_Don](
	[MaHoaDon] [int] IDENTITY(1,1) NOT NULL,
	[MaNhanVien] [int] NOT NULL,
	[TenKhachHang] [nvarchar](50) NOT NULL,
	[NgayTao] [date] NOT NULL,
	[TongTien] [money] NULL,
 CONSTRAINT [PK_HoaDon_1] PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Khach_Hang](
	[MaKhachHang] [int] IDENTITY(1,1) NOT NULL,
	[TenKhachHang] [nvarchar](50) NOT NULL,
	[SDT] [varchar](50) NULL,
	[LoaiKhachHang] [nvarchar](50) NULL,
	[SoLanDen] [int] NULL,
 CONSTRAINT [PK_Khach_Hang] PRIMARY KEY CLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Loai_Phong](
	[MaLoaiPhong] [nvarchar](50) NOT NULL,
	[TenLoaiPhong] [nvarchar](50) NOT NULL,
	[GiaPhong] [money] NOT NULL,
 CONSTRAINT [PK_Table_1] PRIMARY KEY CLUSTERED 
(
	[MaLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Loai_SP](
	[MaLoaiSP] [int] IDENTITY(1,1) NOT NULL,
	[TenLoaiSP] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Loai_SP] PRIMARY KEY CLUSTERED 
(
	[MaLoaiSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Nhan_vien_le_tan](
	[MaNhanVien] [int] IDENTITY(1,1) NOT NULL,
	[HoTen] [nvarchar](50) NOT NULL,
	[GioiTinh] [int] NOT NULL,
	[SoDT] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[DiaChi] [nvarchar](50) NULL,
	[NamSinh] [nvarchar](50) NOT NULL,
	[Hinh] [image] NULL,
 CONSTRAINT [PK_Nhan_vien_le_tan] PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[MaPhong] [int] IDENTITY(1,1) NOT NULL,
	[TenPhong] [nvarchar](50) NOT NULL,
	[MaLoaiPhong] [nvarchar](50) NOT NULL,
	[TinhTrang] [nvarchar](50) NULL,
 CONSTRAINT [PK_Phong1] PRIMARY KEY CLUSTERED 
(
	[MaPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[San_Pham](
	[MaSanPham] [int] IDENTITY(1,1) NOT NULL,
	[TenSanPham] [nvarchar](50) NOT NULL,
	[DonGia] [money] NOT NULL,
	[MaLoaiSP] [int] NOT NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[MaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[TenDangNhap] [varchar](50) NOT NULL,
	[MatKhau] [varchar](50) NOT NULL,
	[VaiTro] [nvarchar](50) NOT NULL,
	[MaNhanVien] [int] NOT NULL,
	[CauHoi] [nvarchar](50) NOT NULL,
	[TraLoi] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Chi_Tiet_Hoa_Don] ([MaHoaDon], [MaSanPham], [SoLuong], [MaPhong]) VALUES (1, 1, 3, 1)
GO
SET IDENTITY_INSERT [dbo].[Don_Dat_Phong] ON 

INSERT [dbo].[Don_Dat_Phong] ([MaDatPhong], [ThoiGianVao], [MaKhachHang], [MaPhong], [TrangThaiDon]) VALUES (1, CAST(N'2024-09-18T14:30:14.000' AS DateTime), 2, 1, N'Đã Thanh Toán')
INSERT [dbo].[Don_Dat_Phong] ([MaDatPhong], [ThoiGianVao], [MaKhachHang], [MaPhong], [TrangThaiDon]) VALUES (2, CAST(N'2024-09-19T21:45:10.000' AS DateTime), 1, 3, N'Đặt Trước')
INSERT [dbo].[Don_Dat_Phong] ([MaDatPhong], [ThoiGianVao], [MaKhachHang], [MaPhong], [TrangThaiDon]) VALUES (3, CAST(N'2024-09-19T21:46:48.000' AS DateTime), 10, 10, N'Đặt Trước')
SET IDENTITY_INSERT [dbo].[Don_Dat_Phong] OFF
GO
SET IDENTITY_INSERT [dbo].[Hoa_Don] ON 

INSERT [dbo].[Hoa_Don] ([MaHoaDon], [MaNhanVien], [TenKhachHang], [NgayTao], [TongTien]) VALUES (1, 1, N'Trần Thanh Minh', CAST(N'2024-09-18' AS Date), 53000.0000)
SET IDENTITY_INSERT [dbo].[Hoa_Don] OFF
GO
SET IDENTITY_INSERT [dbo].[Khach_Hang] ON 

INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (1, N'Trần Quốc Phòng', N'0846212127', N'Khách Hàng Tiềm Năng', 1)
INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (2, N'Nguyễn Thanh Thanh', N'0376545891', N'Khách Hàng Tiềm Năng', 1)
INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (3, N'Nguyễn Ngọc Minh', N'0846567894', N'Khách Hàng Tiềm Năng', 1)
INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (4, N'Võ Thành Trung', N'0378787894', N'Khách Hàng Tiềm Năng', 1)
INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (5, N'Lâm Thanh Nhàn', N'0357521469', N'Khách Hàng Tiềm Năng', 1)
INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (6, N'Trần Nhật Minh', N'0866589731', N'Khách Hàng Tiềm Năng', 1)
INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (7, N'Ngô Thanh Hữu', N'0846989875', N'Khách Hàng Tiềm Năng', 1)
INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (8, N'Nguyễn Nhật Nguyệt', N'0912374949', N'Khách Hàng Tiềm Năng', 1)
INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (9, N'Trần Trung Kiên', N'0846298731', N'Khách Hàng Tiềm Năng', 1)
INSERT [dbo].[Khach_Hang] ([MaKhachHang], [TenKhachHang], [SDT], [LoaiKhachHang], [SoLanDen]) VALUES (10, N'Trần Thanh Minh', N'0846535358', N'Khách Hàng Tiềm Năng', 1)
SET IDENTITY_INSERT [dbo].[Khach_Hang] OFF
GO
INSERT [dbo].[Loai_Phong] ([MaLoaiPhong], [TenLoaiPhong], [GiaPhong]) VALUES (N'LP_10N', N'Premium', 120000.0000)
INSERT [dbo].[Loai_Phong] ([MaLoaiPhong], [TenLoaiPhong], [GiaPhong]) VALUES (N'LP_15N', N'Luxury', 150000.0000)
INSERT [dbo].[Loai_Phong] ([MaLoaiPhong], [TenLoaiPhong], [GiaPhong]) VALUES (N'LP_5N', N'Normal', 80000.0000)
GO
SET IDENTITY_INSERT [dbo].[Loai_SP] ON 

INSERT [dbo].[Loai_SP] ([MaLoaiSP], [TenLoaiSP]) VALUES (1, N'Nước giải khát')
INSERT [dbo].[Loai_SP] ([MaLoaiSP], [TenLoaiSP]) VALUES (2, N'Bia')
INSERT [dbo].[Loai_SP] ([MaLoaiSP], [TenLoaiSP]) VALUES (3, N'Món khai vị')
INSERT [dbo].[Loai_SP] ([MaLoaiSP], [TenLoaiSP]) VALUES (4, N'Trái cây')
INSERT [dbo].[Loai_SP] ([MaLoaiSP], [TenLoaiSP]) VALUES (5, N'Thức ăn nhanh')
INSERT [dbo].[Loai_SP] ([MaLoaiSP], [TenLoaiSP]) VALUES (6, N'Món gỏi')
SET IDENTITY_INSERT [dbo].[Loai_SP] OFF
GO
SET IDENTITY_INSERT [dbo].[Nhan_vien_le_tan] ON 

INSERT [dbo].[Nhan_vien_le_tan] ([MaNhanVien], [HoTen], [GioiTinh], [SoDT], [Email], [DiaChi], [NamSinh], [Hinh]) VALUES (1, N'Trần Minh Hiếu', 1, N'0846212127', N'trantran.th8@gmail.com', N'EA Hleo, Dak Lak', N'2002', NULL)
INSERT [dbo].[Nhan_vien_le_tan] ([MaNhanVien], [HoTen], [GioiTinh], [SoDT], [Email], [DiaChi], [NamSinh], [Hinh]) VALUES (2, N'Nguyễn Hà Duy Đông', 0, N'0989942842', N'linhdoan@outlook.com', N'EA Hleo, Dak Lak', N'2002', NULL)
SET IDENTITY_INSERT [dbo].[Nhan_vien_le_tan] OFF
GO
SET IDENTITY_INSERT [dbo].[Phong] ON 

INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (1, N'Phòng N1', N'LP_5N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (2, N'Phòng P1', N'LP_10N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (3, N'Phòng N2', N'LP_5N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (4, N'Phòng N3', N'LP_5N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (5, N'Phòng N4', N'LP_5N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (6, N'Phòng N5', N'LP_5N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (8, N'Phòng P2', N'LP_10N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (9, N'Phòng P3', N'LP_10N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (10, N'Phòng L1', N'LP_15N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (11, N'Phòng L2', N'LP_15N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (12, N'Phòng L3', N'LP_15N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (13, N'Phòng P4', N'LP_10N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (14, N'Phòng N6', N'LP_5N', N'Trống')
INSERT [dbo].[Phong] ([MaPhong], [TenPhong], [MaLoaiPhong], [TinhTrang]) VALUES (15, N'Phòng L4', N'LP_15N', N'Trống')
SET IDENTITY_INSERT [dbo].[Phong] OFF
GO
SET IDENTITY_INSERT [dbo].[San_Pham] ON 

INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (1, N'Sting', 15000.0000, 1)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (2, N'Pepsi', 15000.0000, 1)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (3, N'Coca Cola', 15000.0000, 1)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (4, N'Redbull', 22000.0000, 1)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (5, N'Tiger', 25000.0000, 2)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (6, N'Heneiken', 28000.0000, 2)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (7, N'Aquafina', 12000.0000, 1)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (8, N'Khoai tây chiên', 100000.0000, 3)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (9, N'Chả giò', 120000.0000, 3)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (10, N'Khô mực nướng', 80000.0000, 3)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (11, N'Trái cây đĩa lớn', 80000.0000, 4)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (12, N'Trái cây đặc biệt', 120000.0000, 4)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (13, N'Bắp rang bơ', 25000.0000, 5)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (14, N'Xúc xích', 40000.0000, 5)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (15, N'Khô bò', 30000.0000, 5)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (16, N'Gỏi ngô sen tôm thịt', 125000.0000, 6)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (17, N'Gỏi ngô sen hải sản', 125000.0000, 6)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (18, N'Bia 333', 20000.0000, 2)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (19, N'Sapporo', 45000.0000, 2)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (20, N'Budweiser', 50000.0000, 2)
INSERT [dbo].[San_Pham] ([MaSanPham], [TenSanPham], [DonGia], [MaLoaiSP]) VALUES (21, N'Bia Huda', 28000.0000, 2)
SET IDENTITY_INSERT [dbo].[San_Pham] OFF
GO
INSERT [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [VaiTro], [MaNhanVien], [CauHoi], [TraLoi]) VALUES (N'hieutran2002', N'Hieu2002@', N'Quản lý', 1, N'Màu yêu thích của bạn là màu gì?', N'Xanh')
INSERT [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [VaiTro], [MaNhanVien], [CauHoi], [TraLoi]) VALUES (N'dong2002', N'Dong2002@', N'Nhân viên lễ tân', 2, N'Màu yêu thích của bạn là màu gì?', N'Vàng')
GO
ALTER TABLE [dbo].[Chi_Tiet_Hoa_Don]  WITH CHECK ADD  CONSTRAINT [FK_Chi_Tiet_Hoa_Don_HoaDon_1] FOREIGN KEY([MaHoaDon])
REFERENCES [dbo].[Hoa_Don] ([MaHoaDon])
GO
ALTER TABLE [dbo].[Chi_Tiet_Hoa_Don] CHECK CONSTRAINT [FK_Chi_Tiet_Hoa_Don_HoaDon_1]
GO
ALTER TABLE [dbo].[Chi_Tiet_Hoa_Don]  WITH CHECK ADD  CONSTRAINT [FK_Chi_Tiet_Hoa_Don_Phong] FOREIGN KEY([MaPhong])
REFERENCES [dbo].[Phong] ([MaPhong])
GO
ALTER TABLE [dbo].[Chi_Tiet_Hoa_Don] CHECK CONSTRAINT [FK_Chi_Tiet_Hoa_Don_Phong]
GO
ALTER TABLE [dbo].[Chi_Tiet_Hoa_Don]  WITH CHECK ADD  CONSTRAINT [FK_Chi_Tiet_Hoa_Don_San_Pham] FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[San_Pham] ([MaSanPham])
GO
ALTER TABLE [dbo].[Chi_Tiet_Hoa_Don] CHECK CONSTRAINT [FK_Chi_Tiet_Hoa_Don_San_Pham]
GO
ALTER TABLE [dbo].[Don_Dat_Phong]  WITH CHECK ADD  CONSTRAINT [FK_DonDatPhong_Khach_Hang] FOREIGN KEY([MaKhachHang])
REFERENCES [dbo].[Khach_Hang] ([MaKhachHang])
GO
ALTER TABLE [dbo].[Don_Dat_Phong] CHECK CONSTRAINT [FK_DonDatPhong_Khach_Hang]
GO
ALTER TABLE [dbo].[Don_Dat_Phong]  WITH CHECK ADD  CONSTRAINT [FK_DonDatPhong_Phong] FOREIGN KEY([MaPhong])
REFERENCES [dbo].[Phong] ([MaPhong])
GO
ALTER TABLE [dbo].[Don_Dat_Phong] CHECK CONSTRAINT [FK_DonDatPhong_Phong]
GO
ALTER TABLE [dbo].[Hoa_Don]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_1_Nhan_vien_le_tan] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[Nhan_vien_le_tan] ([MaNhanVien])
GO
ALTER TABLE [dbo].[Hoa_Don] CHECK CONSTRAINT [FK_HoaDon_1_Nhan_vien_le_tan]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [FK_Phong_Loai_Phong] FOREIGN KEY([MaLoaiPhong])
REFERENCES [dbo].[Loai_Phong] ([MaLoaiPhong])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [FK_Phong_Loai_Phong]
GO
ALTER TABLE [dbo].[San_Pham]  WITH CHECK ADD  CONSTRAINT [FK_San_Pham_Loai_SP] FOREIGN KEY([MaLoaiSP])
REFERENCES [dbo].[Loai_SP] ([MaLoaiSP])
GO
ALTER TABLE [dbo].[San_Pham] CHECK CONSTRAINT [FK_San_Pham_Loai_SP]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_Nhan_vien_le_tan] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[Nhan_vien_le_tan] ([MaNhanVien])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_Nhan_vien_le_tan]
GO
USE [master]
GO
ALTER DATABASE [TheKaraoke] SET  READ_WRITE 
GO
