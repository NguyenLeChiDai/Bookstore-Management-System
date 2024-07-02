Create Database QuanLyHieuSach

Use QuanLyHieuSach
---Khach hang
Create Table KhachHang (
	maKH Char(10) not Null,
	hoTen Nvarchar(50) not Null,
	soDT Varchar(10),
	email Varchar(40),
	diaChi Nvarchar(70),
	Constraint [PK_KhachHang] primary key (maKH)
)

Select * from KhachHang
Drop table KhachHang
Delete from KhachHang

Insert into KhachHang
Values
	('KH-001', N'Trương Minh Hữu', '0776815130', 'minhhuu0705@gmail.com', N'P15, Bình Thạnh, TPHCM'),
	('KH-002', N'Phạm Minh Hiếu', '0235246424', 'hieu2002@gmail.com', N'Dĩ An, Bình Dương'),
	('KH-003', N'Nguyễn Lê Chí Đại', '0122586205', 'chidai2002@gmail.com', N'Bạc Liêu'),
	('KH-004', N'Nguyễn Văn A', '0122586205', 'vana2@gmail.com', N'Thanh Đa, Bình Thạnh, TPHCM'),
	('KH-005', N'Nguyễn Văn B', '0984847383', 'vanb@gmail.com', 'Bac Lieu'),
	('KH-006', N'Lê Vũ Đạt', '0985853762', 'dafinestwine@gmail.com', N'Phú Nhuận, TPHCM'),
	('KH-007', N'Trần Văn Lai Minh', '0756561938', 'minhlaitranvan@gmail.com', N'Phú Nhuận, TPHCM'),
	('KH-008', N'La Trường Di', '0985757563', 'larria@gmail.com', N'Phú Nhuận, TPHCM'),
	('KH-009', N'Nguyễn Anh Tú', '0922147583', 'tutoent@gmail.com', N'Vũng Tàu'),
	('KH-010', N'Võ Thiết Tâm', '0984710307', 'lazie@gmail.com', N'P19, Bình Thạnh, TPHCM'),
	('KH-011', N'Hồ Trung Hiếu', '0976768390', 'hieuho@gmail.com', N'Bình Dương'),
	('KH-012', N'Trương Minh A', '0854547296', 'minha433@gmail.com', N'TPHCM'),
	('KH-013', N'Nguyễn Thị C', '093848692', 'c102@gmail.com', N'Hà Nội'),
	('KH-014', N'Vương Ngọc Tân', '0777838310', 'sol7@gmail.com', N'Bồng Sơn, Quy Nhơn'),
	('KH-015', N'Phạm Ngọc Ngà', '0975759173', 'nhynhy2002@gmail.com', N'TPHCM'),
	('KH-016', N'Nguyễn Ngọc Ngạn', '0973759174', 'parisby9@gmail.com', N'TPHCM'),
	('KH-017', N'Nguyễn Trung Tiến', '0985926485', 'tienfullbright@gmail.com', N'TPHCM'),
	('KH-018', N'Lương Ngọc A', '0974719498', 'luonga@gmail.com', N'Quận 3, TPHCM'),
	('KH-019', N'Lưu Công Chiến', '0987589376', 'chiennhatlang@gmail.com', N'Lạng Sơn'),
	('KH-020', N'Huỳnh Long Hải', '0985729584', 'seachains@gmail.com', N'Cần Thơ'),
	('KH-021', N'Huỳnh Công Hiếu', '0985719482', 'conghieu431@gmail.com', N'TPHCM'),
	('KH-022', N'Nguyễn Phương Ly', '0948383619', 'maylyly@gmail.com', N'Hà Nội'),
	('KH-023', N'Hoàng Thùy Linh', '0984848567', 'hoang@gmail.com', N'Hà Nội')

	---NhanVien---
Create Table NhanVien (
	maNV Char(10) not Null,
	hoTen Nvarchar(50) not Null,
	soDT Varchar(10),
	email Varchar (40),
	diaChi Nvarchar (70),
	caLamViec Char(10),
	Constraint [PK_NhanVien] primary key (maNV),
	Constraint [FK_CaLamViec] foreign key (caLamViec) references CaLamViec(maCaLamViec)
)

Drop Table NhanVien

Select * from NhanVien

Insert into NhanVien
Values 
	('NV-001', N'Trương Minh Hữu', '0776815130', 'minhhuu0705@gmail.com', N'Bình Thạnh, TPHCM', 'C1'),
	('NV-002', N'Phạm Minh Hiếu', '0956443681', 'phamhieu2002@gmail.com', N'Bình Dương', 'C2'),
	('NV-003', N'Nguyễn Lê Chí Đại', '0733265771', 'chidai@gmail.com', N'Bạc Liêu', 'C2'),
	('NV-004', N'Phạm Tuấn Hùng', '0358795648', 'tuanhung@gmail.com', 'Quan 11, TPHCM', 'C1'),
	('NV-005', N'Bùi Mỹ Diện', '0772458796', 'mydien054@gmail.com', 'Quan 8, TPHCM', 'c2'),
	('NV-006', N'Nguyễn Thị Hạnh Na', '0985784698', 'hanhnavi58@gmail.com', 'Quan 11, TPHCM', 'C1'),
	('NV-007', N'Trần Đăng Khoa', '0982547896', 'khoatran154@gmail.com', 'Go vap, TPHCM', 'C1'),
	('NV-008', N'Phạm Trung Hiểu', '0779875648', 'hieutrung@gmail.com', 'Quan 12, TPHCM', 'C2'),
	('NV-009', N'Trương Hữu Tài', '0779985874', 'taitrung89@gmail.com', 'Binh Thanh, TPHCM', 'C2'),
	('NV-010', N'Nguyễn Lâm', '0355879548', 'nguyenlam999@gmail.com', 'Binh Thanh, TPHCM', 'C1'),
	('NV-011', N'Châu Quốc Cường', '0776984582', 'quoccuong123@gmail.com', 'Quan 10, TPHCM', 'C1'),
	('NV-012', N'Hà Anh Tuấn', '0949511141', 'tuanhaanh@gmail.com', 'Go Vap, TPHCM', 'C2'),
	('NV-013', N'Nguyễn Thị Hương Ly', '0987563248', 'huongly1994@gmail.com', 'Quan 10, TPHCM', 'C2'),
	('NV-014', N'Trần Thị Lệ Quyên', '0787452189', 'quyentran@gmail.com', 'Quan 11, TPHCM', 'C2'),
	('NV-015', N'Nguyễn thị Ly Sa', '0325489756', 'lysanguyen@gmail.com', 'Binh Thanh, TPHCM', 'C1'),
	('NV-016', N'Lê Anh Tuấn', '0798989875', 'minhhuu0705@gmail.com', 'Quan 6, TPHCM', 'C2'),
	('NV-017', N'Trần Mạnh Trung', '0969696857', 'manhtrung86@gmail.com', 'Tan Binh, TPHCM', 'C1'),
	('NV-018', N'Dương Phát Đạt', '0772489658', 'phatdatk@gmail.com', 'Tan Binh, TPHCM', 'C1'),
	('NV-019', N'Nguyễn Văn Đức', '0857965412', 'vanducnguyen@gmail.com', 'Go Vap, TPHCM', 'C1'),
	('NV-020', N'Trương Thị Thu Hà', '0985785469', 'hatrung789@gmail.com', 'Quan 12, TPHCM', 'C2')

Delete from NhanVien

--TacGia--
Create Table TacGia (
	maTacGia Char(10) not Null,
	tenTacGia Nvarchar(50) not Null,
	Constraint [PK_TacGia] primary key (maTacGia)
)

Insert into TacGia
Values
	('TG002', N'Hector Malot'),
	('TG001', N'Emily Bronte'),
	('TG003', N'Margaret Mitchell'),
	('TG005', N'Colleen Mccullough'),
	('TG006', N'Victor Hugo'),
	('TG007', N'Phan Xuân Thành'),
	('TG008', N'Jeff Kinney'),
	('TG009', N'Og Mandino'),
	('TG010', N'Nhiều Tác Giả'),
	('TG011', N'Paulo Coelho'),
	('TG012', N'Haruki Murakami'),
	('TG013', N'Nguyên Phong'),
	('TG014', N'Nguyễn Nhật Ánh'),
	('TG015', N'Napoleon Hill'),
	('TG016', N'Jim Collins'),
	('TG017', N'Patty McCord'),
	('TG018', N'John Doerr'),
	('TG019', N'Robin Sharma')

Select * from TacGia

Drop table TacGia

Delete from TacGia


---Nha Xuat ban---
Create Table NhaXuatBan (
	maNhaXB Char(10) not Null,
	tenNhaXB Nvarchar(50) not Null,
	Constraint [PK_NhaXuatBan] primary key (maNhaXB)
)

Insert into NhaXuatBan
Values
	('NXB-001', N'Nhà xuất bản Kim Đồng'),
	('NXB-002', N'Nhà xuất bản Thanh Niên'),
	('NXB-003', N'Nhà xuất bản Trẻ'),
	('NXB-004', N'Nhà xuất bản Đà Nẵng'),
	('NXB-005', N'Nhà xuất bản Hội Nhà Văn'),
	('NXB-006', N'Nhà xuất bản Tổng hợp TPHCM'),
	('NXB-007', N'Nhà xuất bản Phụ nữ Việt Nam'),
	('NXB-008', N'Nhà xuất bản Lao Động'),
	('NXB-009', N'Nhã Nam'),
	('NXB-010', N'Nhà xuất bản Văn học'),
	('NXB-011', N'Nhà xuất bản Bộ giáo dục và đào tạo'),
	('NXB-012', N'Nhà xuất bản Tổng Hợp TPHCM'),
	('NXB-013', N'Nhà xuất bản Dân Trí')

Select * from NhaXuatBan
Drop table NhaXuatBan
Delete from NhaXuatBan

---Loai sach
Create Table LoaiSach (
	maLoai Char(10) not Null,
	tenLoai Nvarchar(50) not Null,
	Constraint [PK_LoaiSach] primary key (maLoai)
)
Drop table LoaiSach

Insert into LoaiSach
Values
	('LS-001', N'Chính trị - pháp luật'),
	('LS-002', N'Khoa học công nghệ - Kinh tế'),
	('LS-003', N'Văn học - nghệ thuật'),
	('LS-004', N'Văn hóa xã hội - Lịch sử'),
	('LS-005', N'Giáo trình - Sách giáo khoa'),
	('LS-006', N'Truyện/tiểu thuyết'),
	('LS-007', N'Sách thiếu nhi')

Select * from LoaiSach
Delete from LoaiSach




--Nha cung cap--
Create Table NhaCungCap (
	maNhaCungCap Char(10) not Null,
	tenNhaCungCap Nvarchar(50) not Null,
	Constraint [PK_NhaCungCap] primary key (maNhaCungCap)
)

Drop Table NhaCungCap

Insert into NhaCungCap
Values
	('NCC-001', N'Công ty TNHH MTV An Lộc Việt'),
	('NCC-002', N'Công ty Thiên Long'),
	('NCC-003', N'Công ty Phượng Vũ'),
	('NCC-004', N'Công ty Phú Thịnh'),
	('NCC-005', N'Công ty TNHH TM DV Hoa Sen'),
	('NCC-006', N'Nhà cung cấp VPP Phú Thịnh'),
	('NCC-007', N'VPP Ánh Dương Xanh'),
	('NCC-008', N'Công ty TNHH Việt Hoàng'),
	('NCC-009', N'Công ty Hoàng Cương'),
	('NCC-010', N'VPP Tuấn Tú')

Select * from NhaCungCap
---Sach--
Create Table Sach (
	maSach Char(10) not Null,
	tenSach Nvarchar(50) not Null,
	ISBN Varchar(20) not Null,
	soTrang Int,
	soLuong Int,
	maLoai Char(10) not Null,
	tacGia Char(10) not Null,
	maNhaXB Char(10) not Null,
	donGiaNhap Float,
	donGiaBan Float,
	Constraint [PK_Sach] primary key (maSach),
	Constraint [FK_LoaiSach] foreign key (maLoai) references LoaiSach(maLoai),
	Constraint [FK_NhaXuatBan] foreign key (maNhaXB) references NhaXuatBan(maNhaXB),
	Constraint [FK_TacGia] foreign key (tacGia) references TacGia(maTacGia)
)

Drop Table Sach

Insert into Sach
Values
	('S-001', N'Đồi gió hú', '9781853260018', 248, 60, 'LS-006', 'TG001', 'NXB-001', 86000, 50000),
	('S-002', N'Không gia đình', '9786047892020', 532, 45, 'LS-006', 'TG002', 'NXB-006', 100000, 60000),
	('S-003', N'Cuốn theo chiều gió', '9780582418059', 1024, 34, 'LS-006', 'TG003', 'NXB-005', 215000, 125000),
	('S-004', N'Tiếng chim hót trong bụi mận gai', '9780380018178', 352, 60, 'LS-006', 'TG005', 'NXB-003', 130000, 60000),
	('S-005', N'Nhà thờ Đức Bà Paris', '9788420666112', 608, 22, 'LS-006', 'TG006', 'NXB-002', 125000, 50000),
	('S-006', N'Vật lý 12', '9786040001993', 218, 100, 'LS-005', 'TG007', 'NXB-011', 13400, 40000),
	('S-007', N'Giáo dục công dân 12', '9786040002075', 120, 100, 'LS-005', 'TG007', 'NXB-011', 6000, 2000),
	('S-008', N'Chú bé nhút nhát', '9786040002077', 307, 100, 'LS-007', 'TG008', 'NXB-010', 42000, 30000),
	('S-009', N'Người Bán Hàng Vĩ Đại Nhất Thế Giới', '9786040002079', 509, 100, 'LS-002', 'TG009', 'NXB-011', 69000, 30000),
	('S-010', N'Kinh Doanh Thời Trang Tại Thị Trường Việt Nam', '9786040002066', 807, 100, 'LS-002', 'TG010', 'NXB-011', 190000, 110000),
	('S-011', N'Nhà Giả Kim', '9786040002078', 500, 100, 'LS-006', 'TG011', 'NXB-005', 47000, 30000),
	('S-012', N'Rừng Nauy', '9786040002070', 700, 100, 'LS-006', N'TG012', 'NXB-005', 99000, 40000),
	('S-013', N'Dấu Chân Trên Cát', '9786040002066', 400, 100, 'LS-006', 'TG013', 'NXB-005', 140000, 60000),
	('S-014', N'Làm Bạn Với Bầu Trời', '978604000297', 400, 100, 'LS-003', 'TG014', 'NXB-005', 82000, 69000),
	('S-015', N'Có Hai Con Mèo Ngồi Bên Cửa Sổ ', '9786040005066', 360, 100, 'LS-003', 'TG014', 'NXB-005', 67000, 37000),
	('S-016', N'Chuyện Cổ Tích Dành Cho Người Lớn ', '9786040005066', 390, 100, 'LS-003', 'TG014', 'NXB-005', 49000, 25000),
	('S-017', N'Ngày Xưa Có Một Chuyện Tình ', '9786090005066', 309, 100, 'LS-003', 'TG014', 'NXB-005', 99000, 60000),
	('S-018', N'Cảm Ơn Người Lớn', '9786090805066', 296, 100, 'LS-003', 'TG014', 'NXB-005', 87000, 63000),
	('S-019', N'Mắt Biếc ', '9786090605066', 409, 100, 'LS-003', 'TG014', 'NXB-005', 82000, 62000),
	('S-020', N'Nghĩ Giàu & Làm Giàu ', '9786940002070', 400, 100, 'LS-002', 'TG015', 'NXB-006', 82000, 67000),
	('S-021', N'Từ Tốt Đến Vĩ Đại Jim Collins ', '9786941002070', 380, 100, 'LS-002', 'TG016', 'NXB-006', 105000, 70000),
	('S-022', N'Kỹ Năng Quản Lý Nhân Sự Chuyên Nghiệp', '9386940002070', 290, 100, 'LS-002', 'TG017', 'NXB-008', 113000, 96000),
	('S-023', N'Làm Điều Quan Trọng', '9726941002070', 380, 100, 'LS-002', 'TG018', 'NXB-006', 115000, 80000),
	('S-024', N'Quản Lý Doanh Nghiệp ', '9725941002070', 290, 100, 'LS-002', 'TG010', 'NXB-008', 50000, 34000),
	('S-025', N'Nhà Lãnh Đạo Không Chức Danh', '9795941002070', 340, 100, 'LS-002', 'TG019', 'NXB-003', 89000, 50000)

Delete from Sach

Select * from Sach

--san pham khac
Create Table SanPhamKhac (
	maSPK Char(10) not Null,
	tenSPK Nvarchar(50) not Null,
	soLuong Int,
	maNhaCungCap Char(10) not Null,
	donGiaNhap Float,
	donGiaBan Float,
	Constraint [PK_SanPham] primary key (maSPK),
	Constraint [FK_NhaCungCap] foreign key (maNhaCungCap) references NhaCungCap(maNhaCungCap)
)

Drop Table SanPhamKhac

Insert into SanPhamKhac
Values
	('SP-001', N'Bút bi xanh', 100, 'NCC-002', 3000, 5000),
	('SP-002', N'Bút bi đỏ', 100, 'NCC-002', 3000, 5000),
	('SP-003', N'Bút bi đen', 100, 'NCC-002', 3000, 5000),
	('SP-004', N'Tập 100 trang', 50, 'NCC-006', 5000, 10000),
	('SP-005', N'Giấy A4 Double A 70 GSM', 30, 'NCC-007', 40000, 70000),
	('SP-006', N'Bìa đựng hồ sơ Myclear', 200, 'NCC-001', 2000, 5000),
	('SP-007', N'Set 10 tập học sinh 200 trang 4 ô ly', 30, 'NCC-002', 20000, 40000),
	('SP-008', N'Thước kẻ', 300, 'NCC-003', 2000, 7000),
	('SP-009', N'Bút bi', 500, 'NCC-001', 1000, 5000),
	('SP-010', N'Bút chì', 500, 'NCC-009', 1000, 4000),
	('SP-011', N'Vở 5 ô ly 96 trang', 300, 'NCC-003', 2000, 7000),
	('SP-012', N'Vở 4 ô ly 96 trang', 400, 'NCC-003', 2000, 7000),
	('SP-013', N'Dây đeo bảng tên', 100, 'NCC-008', 1000, 5000),
	('SP-014', N'Bấm ghim', 200, 'NCC-004', 30000, 98000),
	('SP-015', N'Kẹp giấy', 100, 'NCC-007', 15000, 33000),
	('SP-016', N'Bút xóa', 200, 'NCC-001', 10000, 21000),
	('SP-017', N'Bút lông', 300, 'NCC-009', 10000, 21000),
	('SP-018', N'Kéo cắt giấy', 70, 'NCC-006', 10000, 30000),
	('SP-019', N'Máy Tính Casio FX 580 VN X', 300, 'NCC-008', 300000, 719000),
	('SP-020', N'Dao rọc giấy', 100, 'NCC-009', 20000, 44000),
	('SP-022', N'Cục tẩy', 100, 'NCC-004', 2000, 4000),
	('SP-023', N'Băng keo', 200, 'NCC-005', 5000, 28000),
	('SP-024', N'Bộ Compa Kèm Bút Chì Gỗ', 200, 'NCC-006', 10000, 38000),
	('SP-025', N'út Dạ Quang Cillumina Pentel SL60', 200, 'NCC-009', 10000, 27000),
	('SP-026', N'Tập Ghi Chú Elephant', 100, 'NCC-003', 5000, 19000),
	('SP-027', N'File Kẹp Tài Liệu Kokuyo', 70, 'NCC-009', 5000, 19000)

Select * from SanPhamKhac

Delete from SanPhamKhac

--HoaDon--
Create Table HoaDon (
	maHD Char(10) not Null,
	maKH Char(10) not Null,
	maNV Char(10) not Null,
	ngayLap Date not Null Default (getDate()),
	tongTien Float,
	Constraint [PK_HoaDon] primary key (maHD),
	Constraint [FK_KhachHang] foreign key (maKH) references KhachHang(maKH),
	Constraint [FK_NhanVien] foreign key (maNV) references NhanVien(maNV)
)

Drop Table HoaDon

Insert into HoaDon
Values
	('HD-001', 'KH-001', 'NV-007', '2022-10-23', 311000),
	('HD-002', 'KH-003', 'NV-010', '2022-10-23', 172000),
	('HD-003', 'KH-005', 'NV-001', '2022-10-23', 300000),
	('HD-004', 'KH-007', 'NV-002', '2022-10-23', 20000),
	('HD-005', 'KH-002', 'NV-011', '2022-10-23', 150000),
	('HD-006', 'KH-004', 'NV-010', '2022-10-23', 15000),
	('HD-007', 'KH-008', 'NV-009', '2022-10-23', 30000),
	('HD-008', 'KH-010', 'NV-015', '2022-10-23', 47000),
	('HD-009', 'KH-021', 'NV-006', '2022-10-23', 115000)

Delete from HoaDon

Select * from HoaDon

---chi tiet hoa don--
Create Table ChiTietHoaDon (
	maHD Char(10),
	maSP Char(10),
	soLuong Int,
	donGiaBan Float,
	thanhTien Float,
	Constraint [FK_HoaDon] foreign key (maHD) references HoaDon(maHD),
	Constraint [FK_Sach] foreign key (maSP) references Sach(maSach),
	Constraint [FK_SanPhamKhac] foreign key (maSP) references SanPhamKhac(maSPK)
)

Select * from ChiTietHoaDon

Insert into ChiTietHoaDon
Values
	('HD-001', 'S-001', 1, 86000, 86000),
	('HD-001', 'S-002', 1, 100000, 100000),
	('HD-001', 'S-005', 1, 125000, 125000),
	('HD-002', 'S-005', 1, 125000, 125000),
	('HD-002', 'S-005', 1, 125000, 125000),
	('HD-003', 'S-005', 1, 125000, 125000),
	('HD-004', 'S-005', 1, 125000, 125000),
	('HD-004', 'S-005', 1, 125000, 125000),
	('HD-004', 'S-005', 1, 125000, 125000),
	('HD-005', 'S-005', 1, 125000, 125000),
	('HD-006', 'S-005', 1, 125000, 125000),
	('HD-007', 'S-005', 1, 125000, 125000),
	('HD-008', 'S-005', 1, 125000, 125000),
	('HD-009', 'S-005', 1, 125000, 125000)

Drop Table ChiTietHoaDon

--PhieuDatHang
Create Table PhieuDatHang (
	maPhieu Char(10) not Null,
	maKH Char(10) not Null,
	ngayLap Date not Null Default (getDate()),
	Constraint [PK_PhieuDatHang] primary key (maPhieu),
	Constraint [FK_KhachHangDatHang] foreign key (maKH) references KhachHang(maKH)
)

Drop table PhieuDatHang

Insert into PhieuDatHang
Values
	('P-001', 'KH-001', '2022-10-23'),
	('P-002', 'KH-002', '2022-10-23'),
	('P-003', 'KH-003', '2022-10-23'),
	('P-004', 'KH-004', '2022-10-23'),
	('P-005', 'KH-005', '2022-10-23'),
	('P-006', 'KH-006', '2022-10-23'),
	('P-007', 'KH-007', '2022-10-23'),
	('P-008', 'KH-008', '2022-10-23')

Select * from PhieuDatHang

Delete from PhieuDatHang

--chi tiet phieu dat hang--
Create Table ChiTietPhieuDatHang (
	maPhieu Char (10) not Null,
	maSP Char(10) not Null,
	soLuong Int,
	donGiaBan Float,
	thanhTien Float,
	Constraint [FK_PhieuDatHang] foreign key (maPhieu) references PhieuDatHang(maPhieu),
	Constraint [FK_SachDatHang] foreign key (maSP) references Sach(maSach),
	Constraint [FK_SanPhamKhacDatHang] foreign key (maSP) references SanPhamKhac(maSPK)
)


Drop Table ChiTietPhieuDatHang

Insert into ChiTietPhieuDatHang
Values
	('P-001', 'S-001', 1, 86000, 86000)

	Select * from ChiTietPhieuDatHang
	---Dat hàng---
	Create Table DatHang (
	maHang char (10)  not Null,
	tenHang NVarChar (50) not Null,
	soLuong Int,
	donGiaBan Float,
	thanhTien Float,
)
Insert into DatHang
Values
('P-001', N'Đại', 1, 86000, 86000)
Select * from DatHang
	--Dat sach--
Create Table DatSach (
	maSach char (10)  not Null,
	tenSP NVarChar (50) not Null,
	soLuong Int,
	donGiaBan Float,
	thanhTien Float,
)
Drop table DatSach
Select * from DatSach
Insert into DatSach
Values

	delete from DatSach

---Dat san pham--
Create Table DatSanPham (
	maSanPham char (10)  not Null,
	tenSanPham NVarChar (50) not Null,
	soLuong Int,
	donGiaBan Float,
	thanhTien Float,
)

Drop table DatSanPham
Select * from DatSanPham
Insert into DatSanPham
Values

--TaiKhoan--
Create Table TaiKhoan (
	maTK Char(10) not Null,
	tenTK Varchar(20) not Null,
	matKhau Varchar(20) not Null,
	maPhanQuyen Char(10) not Null,
	maNhanVien Char(10) not Null,
	Constraint [FK_TaiKhoan] foreign key (maNhanVien) references NhanVien (maNV),
	Constraint [PK_TaiKhoan] primary key (maTK),
	Constraint [FK_PhanQuyen] foreign key (maPhanQuyen) references PhanQuyen(maPhanQuyen)
)

Drop Table TaiKhoan

Insert into TaiKhoan
Values
	('TK-123', 'trngmhuu', 'Minhhuu705', 'PQ003')

Select * from TaiKhoan

Delete from TaiKhoan

---Phan quyen--
Create Table PhanQuyen (
	maPhanQuyen Char(10) not Null,
	tenPhanQuyen Nvarchar(50) not Null,
	Constraint [PK_PhanQuyen] primary key (maPhanQuyen)
)

Drop Table PhanQuyen

Insert into PhanQuyen
Values
	('PQ001', N'Nhân viên'),
	('PQ002', N'Người quản lý'),
	('PQ003', N'Người quản trị hệ thống')

Drop table PhanQuyen

Select * from PhanQuyen

Delete from PhanQuyen

--CaLamViec--
Create Table CaLamViec (
	maCaLamViec Char(10) not Null,
	tenCaLamViec Nvarchar(10) not Null,
	Constraint [PK_CaLamViec] primary key (maCaLamViec)
)

Drop table CaLamViec

Insert into CaLamViec
Values
	('C1', N'Sáng'),
	('C2', N'Chiều')

Select * from CaLamViec

Delete from CaLamViec