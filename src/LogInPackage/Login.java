package LogInPackage;
import Game.Game;
import java.awt.Color; 
import java.sql.*;
import javax.swing.*;

public class Login extends javax.swing.JFrame{
    private final Icon icon1 = new ImageIcon(this.getClass().getResource("/menu/eye_small.png"));
    private final Icon icon2 = new ImageIcon(this.getClass().getResource("/menu/eye2_small.png"));
    private final Icon login1 = new ImageIcon(this.getClass().getResource("/menu/login1.png"));
    private final Icon login2 = new ImageIcon(this.getClass().getResource("/menu/login2.png"));
    private final Icon signup1 = new ImageIcon(this.getClass().getResource("/menu/signup1.png"));
    private final Icon signup2 = new ImageIcon(this.getClass().getResource("/menu/signup2.png"));
    private final Icon loginBg = new ImageIcon(this.getClass().getResource("/menu/login.png"));
    
    public boolean isSuccess;
    public String loginID;
    private boolean musicActive;
   
    public Login(){
        initComponents();
        musicActive = false;
        this.setLocationRelativeTo(null);   
        panel.setBackground(new Color(0,0,0,150));
        txtPassword.setEchoChar('•');
        btnSeePassword.setIcon(icon1);
        btnSeePassword.setContentAreaFilled(false);
        btnLogin.setIcon(login1);
        btnSignup.setIcon(signup1);
        bg.setIcon(loginBg);
    }
    
    public Login(boolean musicActive){
        this.musicActive = musicActive;
        initComponents();
        this.setLocationRelativeTo(null);   
        panel.setBackground(new Color(0,0,0,150));
        txtPassword.setEchoChar('•');
        btnSeePassword.setIcon(icon1);
        btnSeePassword.setContentAreaFilled(false);
        btnLogin.setIcon(login1);
        btnSignup.setIcon(signup1);
        bg.setIcon(loginBg);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();
        lbl_minimize = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnSignup = new javax.swing.JButton();
        btnSeePassword = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        cbSeePassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(null);

        lbl_close.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbl_close.setForeground(new java.awt.Color(255, 255, 255));
        lbl_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_close.setText("x");
        lbl_close.setToolTipText("Close");
        lbl_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_closeMouseClicked(evt);
            }
        });
        jPanel1.add(lbl_close);
        lbl_close.setBounds(1240, 0, 50, 30);

        lbl_minimize.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbl_minimize.setForeground(new java.awt.Color(255, 255, 255));
        lbl_minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_minimize.setText("-");
        lbl_minimize.setToolTipText("Minimise");
        lbl_minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_minimizeMouseClicked(evt);
            }
        });
        jPanel1.add(lbl_minimize);
        lbl_minimize.setBounds(1190, 0, 50, 30);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(null);

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        panel.add(btnLogin);
        btnLogin.setBounds(80, 170, 150, 40);

        btnSignup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSignup.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSignup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSignupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSignupMouseExited(evt);
            }
        });
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });
        panel.add(btnSignup);
        btnSignup.setBounds(80, 220, 150, 40);

        btnSeePassword.setBackground(new java.awt.Color(255, 255, 255));
        btnSeePassword.setForeground(new java.awt.Color(255, 255, 255));
        btnSeePassword.setBorder(null);
        btnSeePassword.setBorderPainted(false);
        btnSeePassword.setFocusPainted(false);
        btnSeePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeePasswordActionPerformed(evt);
            }
        });
        panel.add(btnSeePassword);
        btnSeePassword.setBounds(240, 110, 40, 40);

        txtUsername.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername.setToolTipText(null);
        txtUsername.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtUsername.setMargin(new java.awt.Insets(1, 1, 1, 1));
        txtUsername.setName(""); // NOI18N
        txtUsername.setSelectionColor(new java.awt.Color(153, 153, 153));
        panel.add(txtUsername);
        txtUsername.setBounds(20, 40, 270, 40);

        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.add(txtPassword);
        txtPassword.setBounds(20, 110, 270, 40);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username");
        panel.add(jLabel1);
        jLabel1.setBounds(20, 20, 70, 20);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        panel.add(jLabel2);
        jLabel2.setBounds(20, 90, 70, 20);

        jPanel1.add(panel);
        panel.setBounds(950, 430, 310, 280);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setForeground(new java.awt.Color(255, 255, 255));
        bg.setToolTipText("");
        jPanel1.add(bg);
        bg.setBounds(0, 0, 1290, 900);

        cbSeePassword.setText("see");
        jPanel1.add(cbSeePassword);
        cbSeePassword.setBounds(580, 270, 60, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1087, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeePasswordActionPerformed
        if(cbSeePassword.isSelected()){
            txtPassword.setEchoChar('•');
            cbSeePassword.setSelected(false);
            btnSeePassword.setIcon(icon1);
        }
        else{
            txtPassword.setEchoChar((char)0);
            cbSeePassword.setSelected(true);
            btnSeePassword.setIcon(icon2);
        }
    }//GEN-LAST:event_btnSeePasswordActionPerformed

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        Signup page = new Signup(musicActive);
        page.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnSignupActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String uname = txtUsername.getText();
        String pass = String.valueOf(txtPassword.getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/game", "root", "");
            Statement state = conDB.createStatement();
            ResultSet getData = state.executeQuery( "SELECT * FROM info_tbl WHERE Username = '" + uname + "' and Password = '" + pass + "'" );

            String getUsername = "", getPassword = "", getID = "";

            if(getData.next()){
                getUsername = getData.getString("Username");
                getPassword = getData.getString("Password");
                getID = getData.getString("ID");
            }

            if(uname.equals("") && pass.equals("")){
                JOptionPane.showMessageDialog( null, "One or more fields are empty", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if(uname.equals(getUsername) && pass.equals(getPassword)){
                setVisible(false);
                Game game = new Game(getID, musicActive);
                game.start(getID);
            }
            else {
                JOptionPane.showMessageDialog( null, "Invalid USERNAME or PASSWORD", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        catch(ClassNotFoundException | SQLException e){
            //E
            System.out.println(e);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lbl_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizeMouseClicked

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        btnLogin.setIcon(login2);
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        btnLogin.setIcon(login1);
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnSignupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMouseEntered
        btnSignup.setIcon(signup2);
    }//GEN-LAST:event_btnSignupMouseEntered

    private void btnSignupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMouseExited
        btnSignup.setIcon(signup1);
    }//GEN-LAST:event_btnSignupMouseExited

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login(false).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSeePassword;
    private javax.swing.JButton btnSignup;
    private javax.swing.JCheckBox cbSeePassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_minimize;
    private javax.swing.JPanel panel;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
