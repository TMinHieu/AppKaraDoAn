package application;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.LoaiSanPhamDAO;
import dao.DichVuDAO;
import entity.LoaiDichVu;
import entity.DichVu;
import helpers.DataValidator;
import helpers.MessageDialogHelpers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnlQuanLyDichVu extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaDV;
	private JTextField txtTenDV;
	private JTextField txtDonGia;
	private JTextField txtMaLoaiDV;
	private JTextField txtTenLoaiDV;
	private JButton btnThemLoaiDV, btnCapNhatLoaiDV, btnXoaLoaiDV;
	private JButton btnXoaDV, btnCapNhatDV, btnThem, btnLamMoi;
	private JTable tblDichVu, tblLoaiDichVu;
	private JComboBox<String> cmbTim;
	private DefaultTableModel modelDichVu;
	private DefaultTableModel modelLoaiDV;
	private MainFrame mainFrame;
	private JComboBox<String> cmbLoaiDichVu;
	private JTextField txtTim;

	/**
	 * Create the panel.
	 */
	public PnlQuanLyDichVu() {
		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(88, 159, 177);
		Color seperatorColor = new Color(204, 204, 204);
		Color blackColor = new Color(51, 51, 51);
		Color hovertextColor = new Color(250, 130, 49);
		Color hoverColor = new Color(121, 178, 192);
		Color tableHeaderColor = new Color(42, 143, 178);

		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma16Bold = new Font("Tahoma", Font.BOLD, 16);
		Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
		Font tahoma14Bold = new Font("Tahoma", Font.BOLD, 14);
		Font tahoma20Bold = new Font("Tahoma", Font.BOLD, 20);

		JPanel panel = new JPanel();
		panel.setBackground(whiteColor);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(whiteColor);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(whiteColor);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE).addContainerGap()));

		JScrollPane scrollPane = new JScrollPane();

		cmbTim = new JComboBox<String>();
		cmbTim.addItem("Tìm theo tên dịch vụ");
		cmbTim.setFont(tahoma14);
		cmbTim.setBorder(null);

		JLabel lblNewLabel_1_3 = new JLabel("Chọn dịch vụ:");
		lblNewLabel_1_3.setFont(tahoma16);

		JLabel lblNewLabel_1_4 = new JLabel("Danh sách dịch vụ:");
		lblNewLabel_1_4.setFont(tahoma16);

		// btn làm mới text field sản phẩm
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnLamMoi.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnLamMoi.setBackground(mainColor);
			}
		});
		btnLamMoi.setBorder(null);

		btnLamMoi.setIcon(new ImageIcon(getClass().getResource("/images/icons8-refresh-16.png")));
		btnLamMoi.setForeground(whiteColor);
		btnLamMoi.setFont(tahoma14Bold);
		btnLamMoi.setFocusable(false);
		btnLamMoi.setBackground(mainColor);
		btnLamMoi.addActionListener(this);
		
		txtTim = new JTextField();
		txtTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtTim.getText());
			}
		});
		txtTim.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cmbTim, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtTim, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
							.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbTim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addContainerGap())
		);

		tblDichVu = new JTable();
		tblDichVu.setFont(tahoma16);
		tblDichVu.setRowHeight(28);
		tblDichVu.setAutoCreateRowSorter(true);
		tblDichVu.getTableHeader().setFont(tahoma16Bold);
		tblDichVu.getTableHeader().setBackground(tableHeaderColor);
		tblDichVu.getTableHeader().setForeground(whiteColor);
		scrollPane.setViewportView(tblDichVu);
		initTableSanPham();
		panel_2.setLayout(gl_panel_2);

		/**
		 * load dữ liệu từ table sản phẩm lên jtext
		 */
		tblDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tblDichVu.getSelectedRow();
					if (row >= 0) {
						String ma = (String) tblDichVu.getValueAt(row, 0);
						DichVuDAO dichVuDAO = new DichVuDAO();
						DichVu dichVu = dichVuDAO.getDichVuTheoMa(ma);
						if (dichVu != null) {
							txtMaDV.setText(ma);
							txtTenDV.setText(dichVu.getTenDichVu());
							double donGia = dichVu.getDonGia();
							txtDonGia.setText(Double.toString(donGia));
							cmbLoaiDichVu.setSelectedItem(dichVu.getLoaiDichVu().getTenLoaiDV());
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		JLabel lblNewLabel_1_2 = new JLabel("Mã loại dịch vụ:");
		lblNewLabel_1_2.setFont(tahoma16);

		txtMaLoaiDV = new JTextField();
		txtMaLoaiDV.setFont(tahoma14);
		txtMaLoaiDV.setEditable(false);
		txtMaLoaiDV.setColumns(10);

		JLabel lblNewLabel_1_1_3 = new JLabel("Tên loại dịch vụ:");
		lblNewLabel_1_1_3.setFont(tahoma16);

		txtTenLoaiDV = new JTextField();
		txtTenLoaiDV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTenLoaiDV.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtTenLoaiDV.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTenLoaiDV.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtTenLoaiDV.setForeground(blackColor);
			}
		});
		txtTenLoaiDV.setFont(tahoma14);
		txtTenLoaiDV.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();

		// btn thêm loại sản phẩm
		btnThemLoaiDV = new JButton("Thêm");
		btnThemLoaiDV.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThemLoaiDV.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThemLoaiDV.setBackground(mainColor);
			}
		});
		btnThemLoaiDV.setBorder(null);

		btnThemLoaiDV.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));
		btnThemLoaiDV.setForeground(whiteColor);
		btnThemLoaiDV.setFont(tahoma14Bold);
		btnThemLoaiDV.setFocusable(false);
		btnThemLoaiDV.setBackground(mainColor);

		// btn cập nhật loại sản phẩm
		btnCapNhatLoaiDV = new JButton("Cập nhật");
		btnCapNhatLoaiDV.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCapNhatLoaiDV.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCapNhatLoaiDV.setBackground(mainColor);
			}
		});
		btnCapNhatLoaiDV.setBorder(null);

		btnCapNhatLoaiDV.setIcon(new ImageIcon(getClass().getResource("/images/icons8-pencil-16.png")));
		btnCapNhatLoaiDV.setForeground(whiteColor);
		btnCapNhatLoaiDV.setFont(tahoma14Bold);
		btnCapNhatLoaiDV.setFocusable(false);
		btnCapNhatLoaiDV.setBackground(mainColor);

		// btn xóa loại phòng
		btnXoaLoaiDV = new JButton("Xóa");
		btnXoaLoaiDV.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnXoaLoaiDV.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnXoaLoaiDV.setBackground(mainColor);
			}
		});
		btnXoaLoaiDV.setBorder(null);

		btnXoaLoaiDV.setIcon(new ImageIcon(getClass().getResource("/images/icons8-remove-24.png")));
		btnXoaLoaiDV.setForeground(whiteColor);
		btnXoaLoaiDV.setFont(tahoma14Bold);
		btnXoaLoaiDV.setFocusable(false);
		btnXoaLoaiDV.setBackground(mainColor);

		JLabel lblThngTinLoi = new JLabel("Thông tin loại dịch vụ ");
		lblThngTinLoi.setFont(tahoma20Bold);

		JLabel lblNewLabel_1_1_3_1 = new JLabel("Danh sách loại dịch vụ");
		lblNewLabel_1_1_3_1.setFont(tahoma16);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_1_1_3_1)
								.addContainerGap(301, Short.MAX_VALUE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblNewLabel_1_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNewLabel_1_1_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(53)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
											.addComponent(txtTenLoaiDV, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
											.addComponent(txtMaLoaiDV, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblThngTinLoi, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(btnThemLoaiDV, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnCapNhatLoaiDV, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnXoaLoaiDV, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
								.addGap(26)))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblThngTinLoi, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtMaLoaiDV, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTenLoaiDV, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1_1_3_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnThemLoaiDV, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoaLoaiDV, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCapNhatLoaiDV, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);

		tblLoaiDichVu = new JTable();
		tblLoaiDichVu.setFont(tahoma16);
		tblLoaiDichVu.setRowHeight(28);
		tblLoaiDichVu.setAutoCreateRowSorter(true);
		tblLoaiDichVu.getTableHeader().setFont(tahoma16Bold);
		tblLoaiDichVu.getTableHeader().setBackground(tableHeaderColor);
		tblLoaiDichVu.getTableHeader().setForeground(whiteColor);
		initTableLoaiSP();
		scrollPane_1.setViewportView(tblLoaiDichVu);

		/**
		 * load dữ liệu từ table loại sản phẩm lên jtext
		 */
		tblLoaiDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tblLoaiDichVu.getSelectedRow();
					txtMaLoaiDV.setText(tblLoaiDichVu.getValueAt(row, 0).toString());
					txtTenLoaiDV.setText(tblLoaiDichVu.getValueAt(row, 1).toString());

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("Thông tin dịch vụ");
		lblNewLabel.setFont(tahoma20Bold);

		JLabel lblNewLabel_1 = new JLabel("Mã dịch vụ:");
		lblNewLabel_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1 = new JLabel("Tên dịch vụ:");
		lblNewLabel_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_1 = new JLabel("Loại dịch vụ:");
		lblNewLabel_1_1_1.setFont(tahoma16);

		JLabel lblNewLabel_1_1_2 = new JLabel("Đơn giá:");
		lblNewLabel_1_1_2.setFont(tahoma16);

		txtMaDV = new JTextField();
		txtMaDV.setFont(tahoma16);
		txtMaDV.setEditable(false);
		txtMaDV.setColumns(10);

		// btn xóa sản phẩm
		btnXoaDV = new JButton("Xóa");
		btnXoaDV.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnXoaDV.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnXoaDV.setBackground(mainColor);
			}
		});
		btnXoaDV.setBorder(null);

		btnXoaDV.setIcon(new ImageIcon(getClass().getResource("/images/icons8-remove-24.png")));
		btnXoaDV.setForeground(whiteColor);
		btnXoaDV.setFont(tahoma14Bold);
		btnXoaDV.setFocusable(false);
		btnXoaDV.setBackground(mainColor);

		txtTenDV = new JTextField();
		txtTenDV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTenDV.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtTenDV.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTenDV.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtTenDV.setForeground(blackColor);
			}
		});
		txtTenDV.setFont(tahoma16);
		txtTenDV.setColumns(10);

		txtDonGia = new JTextField();
		txtDonGia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDonGia.setBorder(BorderFactory.createLineBorder(hoverColor));
				txtDonGia.setForeground(hovertextColor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtDonGia.setBorder(BorderFactory.createLineBorder(seperatorColor));
				txtDonGia.setForeground(blackColor);
			}
		});
		txtDonGia.setFont(tahoma16);
		txtDonGia.setColumns(10);

		// btn cập nhật sản phẩm
		btnCapNhatDV = new JButton("Cập nhật");
		btnCapNhatDV.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCapNhatDV.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCapNhatDV.setBackground(mainColor);
			}
		});
		btnCapNhatDV.setBorder(null);

		btnCapNhatDV.setIcon(new ImageIcon(getClass().getResource("/images/icons8-pencil-16.png")));
		btnCapNhatDV.setForeground(whiteColor);
		btnCapNhatDV.setFont(tahoma14Bold);
		btnCapNhatDV.setFocusable(false);
		btnCapNhatDV.setBackground(mainColor);

		// btn Thêm sản phẩm
		btnThem = new JButton("Thêm");
		btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThem.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThem.setBackground(mainColor);
			}
		});
		btnThem.setBorder(null);

		btnThem.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));
		btnThem.setForeground(whiteColor);
		btnThem.setFont(tahoma14Bold);
		btnThem.setFocusable(false);
		btnThem.setBackground(mainColor);

		cmbLoaiDichVu = new JComboBox<String>();
		cmbLoaiDichVu.setFont(tahoma16);
		cmbLoaiDichVu.setBackground(whiteColor);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 121,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel_1_1_2)
														.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE,
																121, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 121,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(cmbLoaiDichVu, 0, 241, Short.MAX_VALUE)
														.addComponent(txtDonGia, GroupLayout.DEFAULT_SIZE, 241,
																Short.MAX_VALUE)
														.addComponent(txtTenDV, GroupLayout.DEFAULT_SIZE, 241,
																Short.MAX_VALUE)
														.addComponent(txtMaDV, Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(btnThem, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnCapNhatDV, GroupLayout.DEFAULT_SIZE, 117,
														Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnXoaDV,
														GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
								.addGap(41))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(173, Short.MAX_VALUE)))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE).addGap(35)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(txtMaDV, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtTenDV, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(cmbLoaiDichVu, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE))
								.addGap(14)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(txtDonGia, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel_1))
				.addGap(65)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoaDV, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCapNhatDV, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		panel.setLayout(gl_panel);

		setLayout(groupLayout);
		btnThem.addActionListener(this);
		btnXoaDV.addActionListener(this);
		btnCapNhatDV.addActionListener(this);

		// LOẠI SẢN PHẨM
		btnThemLoaiDV.addActionListener(this);
		btnXoaLoaiDV.addActionListener(this);
		btnCapNhatLoaiDV.addActionListener(this);

		// LOAD DATA
		loadDataToTblLoaiSanPham();
		loadDataToTblSanPham();
		loadDataToCmbLoaiSanPham();

	}

	/* =================Tạo df table model================ */
	/**
	 * Tạo default table model cho sản phẩm add các row cho tblLoaiSanPham
	 */
	private void initTableSanPham() {
		modelDichVu = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modelDichVu.setColumnIdentifiers(new String[] { "Mã dịch vụ", "Tên dịch vụ", "Loại dịch vụ", "Đơn giá" });
		tblDichVu.setModel(modelDichVu);
	}

	/**
	 * Tạo default table model cho loại sản phẩm , add các row cho tblLoaiSanPham
	 */
	private void initTableLoaiSP() {
		modelLoaiDV = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modelLoaiDV.setColumnIdentifiers(new String[] { "Mã loại dịch vụ", "Tên loại dịch vụ" });
		tblLoaiDichVu.setModel(modelLoaiDV);
	}
	/* =================================== */

	/* ==================Tải dữ liệu vào 2 table=============== */
	private void loadDataToTblLoaiDichVu() {
		LoaiDichVuDAO ds = new LoaiDichVuDAO();
		List<LoaiDichVu> list = ds.getDanhSachLoaiDichVu();
		for (LoaiDichVu lsp : list) {
			modelLoaiDV.addRow(new Object[] { lsp.getMaLoaiSP(), lsp.getTenLoaiDV() });
		}
	}

	private void loadDataToTblSanPham() {
		DichVuDAO ds = new DichVuDAO();
		List<DichVu> list = ds.getDanhSachSanPham();

		for (DichVu dichVu : list) {
			modelDichVu.addRow(new Object[] { dichVu.getMaDichVu(), dichVu.getTenDichVu(),
					dichVu.getLoaiDichVu().getTenLoaiDV(), dichVu.getDonGia() });
		}
		tblDichVu.setModel(modelDichVu);
	}
	/* =================================== */

	/* ==================Tải dữ liệu vào combobox================== */
	

	private void loadDataToCmbLoaiSanPham() {
		LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
		List<LoaiDichVu> lsp = loaiSanPhamDAO.getDanhSachLoaiSanPham();
		for (LoaiDichVu loaiSanPham : lsp) {
			cmbLoaiDichVu.addItem(loaiSanPham.getTenLoaiSP());
		}
	}
	/* =================================== */

	/* ================Xóa rỗng textfields================= */
	private void xoaRongTextfieldLoaiSP() {
		txtMaLoaiDV.setText("");
		txtTenLoaiDV.setText("");
		txtMaLoaiDV.requestFocus();
	}

	private void xoaRongTextfieldsSanPham() {
		txtMaDV.setText("");
		txtTenDV.setText("");
		txtDonGia.setText("");
		cmbLoaiDichVu.setSelectedItem(0);
		tblDichVu.setRowSorter(null);
		txtMaDV.requestFocus();
	}
	/* =================================== */

	/* ==================Tạo đối tượng sản phẩm và loại sản phẩm================= */
	private LoaiDichVu createLoaiDichVu() {
		LoaiDichVu loaiSanPham = new LoaiDichVu();
		loaiSanPham.setTenLoaiDV(txtTenLoaiDV.getText());
		return loaiSanPham;
	}

	private DichVu createSanPham() {
		DichVu dv = new DichVu();
		LoaiDichVu loaiSanPham = new LoaiDichVu();
		LoaiSanPhamDAO loaiDichVuDAO = new LoaiDichVuDAO();

		String tenLoaiDV = (String) cmbLoaiSanPham.getSelectedItem();
		String maLoaiDV = loaiDichVuDAO.getMaTheoTenLoai(tenLoaiSP);
		loaiSanPham.setMaLoaiSP(maLoaiSP);
		loaiSanPham.setTenLoaiSP(tenLoaiSP);

		sp.setTenSanPham(txtTenSP.getText());
		sp.setDonGia(Double.parseDouble(txtDonGia.getText()));
		sp.setLoaiSanPham(loaiSanPham);

		return sp;
	}
	/* =================================== */

	/**
	 * kiểm tra biểu thức chính quy
	 * 
	 * @param sb
	 */
	private void dataValidateLoaiSP(StringBuilder sb) {
		DataValidator.validateEmpty(txtTenLoaiSP, sb, "Tên loại dịch vụ không được để trống");
		DataValidator.validateVietnameseCharacters(txtTenLoaiSP, sb,
				"Tên loại dịch vụ sai.Không được nhập số và kí tự đặt biệt");
	}

	private void dataValidateSP(StringBuilder sb) {
		DataValidator.validateEmpty(txtDonGia, sb, "Đơn giá không được để trống");
		DataValidator.validateEmpty(txtTenSP, sb, "Tên dịch vụ không được để trống");
		DataValidator.validateDonGia(txtDonGia, sb, "Đơn giá chỉ được nhập số");
		DataValidator.validateVietnameseCharactersAndNumber(txtTenSP, sb, "Tên dịch vụ sai.Không có kí tự đặt biệt");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();

		// SẢN PHẨM
		// THÊM SẢN PHẨM
		if (o.equals(btnThem)) {

			DichVu sanPham = createSanPham();
			DichVuDAO sanPhamDAO = new DichVuDAO();

			StringBuilder sb = new StringBuilder();
			dataValidateSP(sb);

			if (sb.length() > 0) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
				return;
			}

			if (sanPhamDAO.checkExist(txtTenSP.getText())) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo", "dịch vụ đã tồn tại, tên dịch vụ trùng");
				return;
			} else {
				if (sanPhamDAO.addSanPham(sanPham)) {
					MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Bạn đã thêm thành công");

					modelSanPham.setRowCount(0);
					loadDataToTblSanPham();
					xoaRongTextfieldsSanPham();
				}

			}

		}

		// CẬP NHẬT SẢN PHẨM
		if (o.equals(btnCapNhatSP)) {

			int row = tblSanPham.getSelectedRow();

			DichVu sp = createSanPham();
			sp.setMaSanPham(txtMaSP.getText());
			DichVuDAO sanPhamDAO = new DichVuDAO();

			if (row >= 0) {
				StringBuilder sb = new StringBuilder();
				dataValidateSP(sb);
				if (sb.length() > 0) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
					return;
				}
				if (sanPhamDAO.checkExist(txtTenSP.getText())) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo", "Tên dịch vụ trùng");
					return;
				} else {
					if (MessageDialogHelpers.showConfirmDialog(mainFrame, "Lỗi",
							"Bạn có chắc muốn cập nhật") == JOptionPane.NO_OPTION) {
						return;
					}
					try {

						if (sanPhamDAO.updateSanPham(sp)) {
							MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo",
									"Bạn đã cập nhật thành công");
							modelSanPham.setRowCount(0);
							loadDataToTblSanPham();

						} else {
							MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Cập nhật không thành công");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			} else {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Phải chọn một dòng trong bảng");
			}
			xoaRongTextfieldsSanPham();

		}

		// XÓA SẢN PHẨM
		if (o.equals(btnXoaSP)) {

			int row = tblSanPham.getSelectedRow();
			DichVuDAO spDAO = new DichVuDAO();

			if (row >= 0) {
				String maSP = (String) tblSanPham.getValueAt(row, 0);
				String stt = maSP.replaceAll("SP", "");
				if (MessageDialogHelpers.showConfirmDialog(mainFrame, "Cảnh báo",
						"Bạn có chắc muốn xóa tài khoản này") == JOptionPane.WARNING_MESSAGE) {
					return;
				}

				try {

					if (spDAO.deleteSanPham(stt)) {
						MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Xóa thành công");
						modelSanPham.setRowCount(0);
						loadDataToTblSanPham();
						cmbTim.removeAllItems();

					} else {
						MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Xóa không thành công");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Phải chọn 1 dòng trong bảng");
			}

		}

		// LÀM RỖNG TEXTFIELD SẢN PHẨM
		if (o.equals(btnLamMoi)) {
			xoaRongTextfieldsSanPham();
			modelSanPham.setRowCount(0);
			loadDataToTblSanPham();

			xoaRongTextfieldLoaiSP();
			modelLoaiSP.setRowCount(0);
			loadDataToTblLoaiSanPham();
			
			txtTim.setText("");
		}

		// LOẠI SẢN PHẨM
		// THÊM LOẠI SẢN PHẨM
		if (o.equals(btnThemLoaiSP)) {

			LoaiDichVu lsp = createLoaiSanPham();
			LoaiSanPhamDAO loaiSPDAO = new LoaiSanPhamDAO();

			StringBuilder sb = new StringBuilder();
			dataValidateLoaiSP(sb);
			if (sb.length() > 0) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
				return;
			}
			if (loaiSPDAO.checkExist(txtTenLoaiSP.getText())) {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo",
						"Loại dịch vụ đã tồn tại, tên loại dịch vụ trùng");
				return;
			} else {
				if (loaiSPDAO.addLoaiSanPham(lsp)) {
					MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Bạn đã thêm thành công");
					modelLoaiSP.setRowCount(0);

					loadDataToTblLoaiSanPham();
					cmbLoaiSanPham.removeAllItems();
					loadDataToCmbLoaiSanPham();
					xoaRongTextfieldLoaiSP();
				}

			}
		}

		// CẬP NHẬT LOẠI SẢN PHẨM
		if (o.equals(btnCapNhatLoaiSP)) {

			int row = tblLoaiSanPham.getSelectedRow();

			LoaiDichVu lsp = createLoaiSanPham();
			lsp.setMaLoaiSP(txtMaLoaiSP.getText());
			LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();

			if (row >= 0) {
				StringBuilder sb = new StringBuilder();
				dataValidateLoaiSP(sb);
				if (sb.length() > 0) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", sb.toString());
					return;
				}
				if (loaiSanPhamDAO.checkExist(txtTenLoaiSP.getText())) {
					MessageDialogHelpers.showErrorDialog(mainFrame, "Cảnh báo", "Tên loại dịch vụ trùng");
					return;
				} else {
					if (MessageDialogHelpers.showConfirmDialog(mainFrame, "Lỗi",
							"Bạn có chắc muốn cập nhật") == JOptionPane.NO_OPTION) {
						return;
					}
					try {

						if (loaiSanPhamDAO.updateLoaiSanPham(lsp)) {
							MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo",
									"Bạn đã cập nhật thành công");
							modelLoaiSP.setRowCount(0);
							loadDataToTblLoaiSanPham();

							cmbLoaiSanPham.removeAllItems();
							loadDataToCmbLoaiSanPham();
						} else {
							MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Cập nhật không thành công");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			} else {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Phải chọn một dòng trong bảng");
			}
			xoaRongTextfieldLoaiSP();

		}

		// XÓA LOẠI SẢN PHẨM
		if (o.equals(btnXoaLoaiSP)) {

			int row = tblLoaiSanPham.getSelectedRow();
			LoaiSanPhamDAO lspDAO = new LoaiSanPhamDAO();

			if (row >= 0) {
				String maLoaiSP = (String) tblLoaiSanPham.getValueAt(row, 0);
				String stt = maLoaiSP.replaceAll("LSP", "");
				if (MessageDialogHelpers.showConfirmDialog(mainFrame, "Cảnh báo",
						"Bạn có chắc muốn xóa dịch vụ này") == JOptionPane.NO_OPTION) {
					return;
				}

				try {

					if (lspDAO.deleteLoaiSanPham(stt)) {
						MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Xóa thành công");
						modelLoaiSP.setRowCount(0);
						loadDataToTblLoaiSanPham();
						cmbLoaiSanPham.removeAllItems();
						loadDataToCmbLoaiSanPham();

					} else {
						MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Xóa không thành công");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else {
				MessageDialogHelpers.showErrorDialog(mainFrame, "Lỗi", "Phải chọn 1 dòng trong bảng");
			}

		}

	}
	
	public void search(String str) {
		modelSanPham = (DefaultTableModel) tblSanPham.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(modelSanPham);
		tblSanPham.setRowSorter(trs);

		if (cmbTim.getSelectedItem().toString().equals("Tìm theo tên dịch vụ")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 1));
		}
	}

}
