package assignments.assignment4.frontend;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WelcomePanel extends SistakaPanel {
    // Fields
    JPanel mainPanel, leftPanel, rightPanel;
    JLabel titleHome, imgLabel;
    JButton btnLogin, btnExit;
    Border border;
    ImageIcon icon, setIcon;

    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);
        // mengatur layout WelcomePanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        border = BorderFactory.createLineBorder(darkPurple, 2);
        setBorder(border);

        // membuat panel utama
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // membuat panel kiri
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(1,1));
        leftPanel.setBackground(darkPurple);

        // membuat panel kanan
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(darkPurple);

        // membuat dan mengatur label untuk judul
        titleHome = new JLabel("Welcome to SistakaNG");
        titleHome.setFont(fontTitle);
        titleHome.setForeground(Color.WHITE);
        titleHome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat button
        btnLogin = new JButton("Login");
        btnExit = new JButton("Exit");

        // menampilkan gambar
        icon = new ImageIcon("./img/city2.gif");
        setIcon = new ImageIcon(icon.getImage().getScaledInstance(450, 500, Image.SCALE_DEFAULT));
        imgLabel = new JLabel(setIcon);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // menambahkan dan mengatur setiap komponen ke Panel
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(titleHome);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        modifyAndSettingButton(btnLogin, "login", rightPanel);
        modifyAndSettingButton(btnExit, "exit", rightPanel);
        rightPanel.add(Box.createVerticalGlue());

        // mengatur panel kiri
        leftPanel.add(imgLabel);

        // mengatur panel utama
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel);

        // menambahkan panel utama ke frame
        add(mainPanel);
    }

    @Override
    public void refresh() {
        // ignored
    }
}
