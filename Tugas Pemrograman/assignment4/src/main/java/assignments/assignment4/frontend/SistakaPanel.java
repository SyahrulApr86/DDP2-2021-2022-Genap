package assignments.assignment4.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public abstract class SistakaPanel extends JPanel {
    public static Font fontButton = new Font("Century Gothic", Font.BOLD , 12);
    public static Font fontGeneral = new Font("Century Gothic", Font.BOLD , 13);
    public static Font fontTitle = new Font("Century Gothic", Font.BOLD, 23);
    public static Font fontComboBox = new Font("Century Gothic", Font.BOLD, 15);
    public static Color darkBlue = new Color(2,0,148,255);
    public static Color pink = new Color(223,113,211,255);
    public static Color purple = new Color(50, 16, 149,255);
    public static Color darkPurple = new Color(24,0,115, 255);
    public static Color darkerPurple = new Color(5,0,46,255);

    protected final HomeGUI main;

    public SistakaPanel(HomeGUI main) {
        this.main = main;
        setBorder(new EmptyBorder(10,10,10,10));
    }

    public abstract void refresh();

    // Utility
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isDateValid(String tanggal){
        String[] arrOfTanggalLahir = tanggal.split("/");

        // Cek apakah ada 3 input (untuk dd, mm, dan yyyy)
        if (arrOfTanggalLahir.length != 3) {
            return false;
        }

        // Cek apakah semuanya numerik
        for (String s : arrOfTanggalLahir) {
            if (!isNumeric(s)) {
                return false;
            }
        }

        // Cek apakah jumlah digitnya memenuhi aturan (2 digit untuk tanggal dan bulan, serta 4 digit untuk year)
        if (arrOfTanggalLahir[0].length() != 2 || arrOfTanggalLahir[1].length() != 2 || arrOfTanggalLahir[2].length() != 4) {
            return false;
        }

        // Cek apakah memenuhi aturan tanggal (tanggal antara 1 - 31)
        int hari = Integer.parseInt(arrOfTanggalLahir[0]);
        if (hari < 1 || hari > 31) {
            return false;
        }

        // Cek apakah memenuhi aturan bulan (bulan antara 1 - 12)
        int bulan = Integer.parseInt(arrOfTanggalLahir[1]);
        return bulan >= 1 && bulan <= 12;
    }

    // mengatur setiap button yang ada di panel
    protected void modifyAndSettingButton(JButton b, String nextPage, JPanel panel) {
        // mengatur setiap button
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBackground(Color.WHITE);
        b.setPreferredSize(new Dimension(350, 40));
        b.setMaximumSize(new Dimension(350, 40));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFont(fontButton);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panel.add(b);
        b.setForeground(purple);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                switch (nextPage) {
                    case "login", "tambahMhs", "tambahDosen", "tambahKategori",
                            "tambahBuku", "hapusBuku", "peringkat", "detailAnggota",
                            "daftarPeminjam", "staf", "peminjaman", "pengembalian",
                            "pembayaran", "detailUser", "anggota" -> main.setPanel(nextPage);
                    case "exit" -> main.exit();
                    case "logout" -> main.setPanel("welcome");
                }
            }
        });

        // mengubah warna button saat mouse hover
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            Color c;
            public void mouseEntered(MouseEvent e){
                c = b.getForeground();
                b.setBackground(Color.BLACK);
                b.setForeground(darkBlue);
            }
            public void mouseExited(MouseEvent e){
                b.setBackground(Color.WHITE);
                b.setForeground(c);
            }
        });
    }

    // mengatur setiap textfield yang ada di panel
    protected static void modifyField(JTextField f){
        // mengatur setiap textField
        f.setFont(fontGeneral);
        f.setMaximumSize(new Dimension(250, 30));
        f.setAlignmentX(Component.CENTER_ALIGNMENT);
        f.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        f.setOpaque(false);


        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
    }

    protected static void modifyComboBox(JComboBox cb) {
        // mengatur setiap combobox
        cb.setBackground(Color.WHITE);
        cb.setMaximumSize(new Dimension(200, 20));
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.setFont(fontComboBox);
        cb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cb.setForeground(darkPurple);
        cb.setFocusable(false);
        cb.setBorder(BorderFactory.createEmptyBorder());
    }

    protected static void modifyButton(JButton b){
        // mengatur setiap button
        b.setBackground(Color.WHITE);
        b.setMaximumSize(new Dimension(200, 20));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFont(fontButton);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setForeground(darkPurple);


        // mengubah warna button saat mouse hover
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            Color c;
            public void mouseEntered(MouseEvent e){
                c = b.getForeground();
                b.setBackground(Color.BLACK);
                b.setForeground(purple);
            }
            public void mouseExited(MouseEvent e){
                b.setBackground(Color.WHITE);
                b.setForeground(c);
            }
        });
    }

}
