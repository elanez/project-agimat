 package LogInPackage;
import java.awt.Color;
import java.sql.*;
import java.text.DateFormatSymbols;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;

public class Signup extends javax.swing.JFrame{
    private final Icon icon1 = new ImageIcon(this.getClass().getResource("/menu/eye_small.png"));
    private final Icon icon2 = new ImageIcon(this.getClass().getResource("/menu/eye2_small.png"));
    private final Icon login1 = new ImageIcon(this.getClass().getResource("/menu/login1.png"));
    private final Icon login2 = new ImageIcon(this.getClass().getResource("/menu/login2.png"));
    private final Icon signup1 = new ImageIcon(this.getClass().getResource("/menu/signup1.png"));
    private final Icon signup2 = new ImageIcon(this.getClass().getResource("/menu/signup2.png"));
    private final Icon signupBg = new ImageIcon(this.getClass().getResource("/menu/bg.png"));
    private boolean musicActive;
    
    public Signup(boolean musicActive) {
        this.musicActive = musicActive;
        initComponents();
        this.setLocationRelativeTo(null);
        panel.setBackground(new Color(0,0,0,150));
        bg.setIcon(signupBg);
        
        ButtonGroup group = new ButtonGroup();
        group.add(rbMale);
        group.add(rbFemale);
        
        txtPassword.setEchoChar('•');
        btnSeePassword.setIcon(icon1);
        btnSeePassword.setContentAreaFilled(false); 
        
        txtConfirmPassword.setEchoChar('•');
        btnSeeCPassword.setIcon(icon1);
        btnSeeCPassword.setContentAreaFilled(false); 
        
        lblErrorUsername.setVisible(false);
        lblErrorPassword.setVisible(false);
        lblErrorCPassword.setVisible(false);
        
        btnLogin.setIcon(login1);
        btnSignup.setIcon(signup1);
        
        //add item months
        String[] months = new DateFormatSymbols().getMonths();
        for(String month: months){
            cmbMonth.addItem(month);
        }
        
        //add item days
        for(int day = 1; day <= 31; day++){
            cmbDay.addItem(String.valueOf(day));
        }
        
        //add item years
        final int START = 2006;
        final int END = 1975;
        for(int i = START; i >= END; i--){
            cmbYear.addItem(String.valueOf(i));
        }
        txtUsername.addCaretListener((CaretEvent e) -> {
            if(validateUsername(txtUsername.getText())){
                lblErrorUsername.setVisible(false);
                btnSignup.setEnabled(true);
            }else{
                lblErrorUsername.setVisible(true);
                btnSignup.setEnabled(false); 
            }
        });
        txtPassword.addCaretListener((CaretEvent e) -> {
            if(passwordValidation(txtUsername.getText(),String.valueOf(txtPassword.getPassword()))){
                lblErrorPassword.setVisible(false);
                //btnSignup.setEnabled(true);
            }else{
                lblErrorPassword.setVisible(true);
                //btnSignup.setEnabled(false);
            }
        });
        txtConfirmPassword.addCaretListener((CaretEvent e) -> {
            if(txtPassword.getText().equals(String.valueOf(txtConfirmPassword.getText()))){
                lblErrorCPassword.setVisible(false);
                //btnSignup.setEnabled(true);
            }else{
                lblErrorCPassword.setVisible(true);
                //btnSignup.setEnabled(false);
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFirstname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLastname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSeePassword = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        btnSeeCPassword = new javax.swing.JButton();
        txtConfirmPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        cmbMonth = new javax.swing.JComboBox<>();
        cmbDay = new javax.swing.JComboBox<>();
        cmbYear = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        btnSignup = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        lblErrorUsername = new java.awt.Label();
        lblErrorPassword = new java.awt.Label();
        lblErrorCPassword = new java.awt.Label();
        bg = new javax.swing.JLabel();
        cbSeePassword = new javax.swing.JCheckBox();
        cbSeeCPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1440, 1080));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1440, 1080));
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("First Name");
        panel.add(jLabel1);
        jLabel1.setBounds(70, 30, 70, 20);

        txtFirstname.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtFirstname.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.add(txtFirstname);
        txtFirstname.setBounds(70, 50, 200, 30);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Last Name");
        panel.add(jLabel2);
        jLabel2.setBounds(280, 30, 70, 20);

        txtLastname.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtLastname.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.add(txtLastname);
        txtLastname.setBounds(280, 50, 200, 30);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");
        panel.add(jLabel3);
        jLabel3.setBounds(70, 90, 70, 20);

        txtUsername.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtUsername.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtUsername.setCaretColor(new java.awt.Color(255, 0, 0));
        panel.add(txtUsername);
        txtUsername.setBounds(70, 110, 410, 30);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password");
        panel.add(jLabel4);
        jLabel4.setBounds(70, 160, 70, 20);

        btnSeePassword.setBackground(new java.awt.Color(255, 255, 255));
        btnSeePassword.setForeground(new java.awt.Color(255, 255, 255));
        btnSeePassword.setContentAreaFilled(false);
        btnSeePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeePasswordActionPerformed(evt);
            }
        });
        panel.add(btnSeePassword);
        btnSeePassword.setBounds(445, 180, 30, 30);

        txtPassword.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.add(txtPassword);
        txtPassword.setBounds(70, 180, 410, 30);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Confirm Password");
        panel.add(jLabel5);
        jLabel5.setBounds(70, 230, 110, 20);

        btnSeeCPassword.setBackground(new java.awt.Color(255, 255, 255));
        btnSeeCPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnSeeCPassword.setContentAreaFilled(false);
        btnSeeCPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeCPasswordActionPerformed(evt);
            }
        });
        panel.add(btnSeeCPassword);
        btnSeeCPassword.setBounds(445, 250, 30, 30);

        txtConfirmPassword.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtConfirmPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.add(txtConfirmPassword);
        txtConfirmPassword.setBounds(70, 250, 410, 30);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Birthday");
        panel.add(jLabel6);
        jLabel6.setBounds(70, 300, 70, 20);

        cmbMonth.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        cmbMonth.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cmbMonth.setName("Month"); // NOI18N
        panel.add(cmbMonth);
        cmbMonth.setBounds(70, 320, 130, 30);

        cmbDay.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        cmbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));
        cmbDay.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.add(cmbDay);
        cmbDay.setBounds(210, 320, 130, 30);

        cmbYear.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        cmbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year" }));
        cmbYear.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.add(cmbYear);
        cmbYear.setBounds(350, 320, 130, 30);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Sex");
        panel.add(jLabel7);
        jLabel7.setBounds(70, 360, 70, 20);

        rbMale.setBackground(new java.awt.Color(255, 255, 255));
        rbMale.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        rbMale.setText("Male");
        rbMale.setAlignmentX(0.5F);
        rbMale.setBorder(null);
        rbMale.setContentAreaFilled(false);
        rbMale.setFocusPainted(false);
        rbMale.setName(""); // NOI18N
        rbMale.setOpaque(true);
        panel.add(rbMale);
        rbMale.setBounds(280, 380, 200, 30);

        rbFemale.setBackground(new java.awt.Color(255, 255, 255));
        rbFemale.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        rbFemale.setText("Female");
        rbFemale.setAlignmentX(0.5F);
        rbFemale.setBorder(null);
        rbFemale.setContentAreaFilled(false);
        rbFemale.setFocusPainted(false);
        rbFemale.setOpaque(true);
        panel.add(rbFemale);
        rbFemale.setBounds(70, 380, 200, 30);

        btnSignup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSignup.setBorder(null);
        btnSignup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignup.setFocusPainted(false);
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
        btnSignup.setBounds(200, 440, 150, 40);

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogin.setBorder(null);
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
        btnLogin.setBounds(200, 490, 150, 40);

        lblErrorUsername.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblErrorUsername.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorUsername.setText("Username already exists.");
        panel.add(lblErrorUsername);
        lblErrorUsername.setBounds(70, 140, 410, 10);

        lblErrorPassword.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblErrorPassword.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorPassword.setText("Use 8-15 characters with one uppercase, lowercase, digit and special character.");
        panel.add(lblErrorPassword);
        lblErrorPassword.setBounds(70, 210, 410, 10);

        lblErrorCPassword.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblErrorCPassword.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorCPassword.setText("Passwords didn't match.");
        panel.add(lblErrorCPassword);
        lblErrorCPassword.setBounds(70, 280, 410, 10);

        jPanel1.add(panel);
        panel.setBounds(445, 175, 550, 550);
        jPanel1.add(bg);
        bg.setBounds(0, -20, 1440, 1080);
        jPanel1.add(cbSeePassword);
        cbSeePassword.setBounds(615, 210, 30, 30);
        jPanel1.add(cbSeeCPassword);
        cbSeeCPassword.setBounds(615, 275, 30, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1440, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        try {      
            if((txtFirstname.getText() == null ? "" == null : txtFirstname.getText().equals("")) || (txtLastname.getText() == null ? "" == null : txtLastname.getText().equals(""))){
               JOptionPane.showMessageDialog(null, "ID or Name must contain value","System Message",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                String selected = (rbMale.isSelected()) ? "Male":"Female";
                    if(passwordValidation(txtUsername.getText(), String.valueOf(txtPassword.getText()))){
                    String sql = "INSERT INTO `game`.`info_tbl` (`ID`, `First Name`, `Last Name`, `Username`, `Password`, `Birth_Month`, `Birth_Day`, `Birth_Year`, `Sex`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/game", "root", "");
                    Statement state = conDB.createStatement();
                    PreparedStatement pst = conDB.prepareStatement(sql);
                    pst.setString(1,txtFirstname.getText());
                    pst.setString(2,txtLastname.getText());
                    pst.setString(3,txtUsername.getText());
                    pst.setString(4,String.valueOf(txtPassword.getText()));
                    pst.setString(5,(String)cmbMonth.getSelectedItem().toString());
                    pst.setString(6,(String)cmbDay.getSelectedItem().toString());
                    pst.setString(7,(String)cmbYear.getSelectedItem().toString());
                    pst.setString(8,selected);
                    pst.executeUpdate();
                    
                    state.executeUpdate("INSERT into playerinfo(Gold,Equipped,Inventory,Questlog,Health,Stamina,Damage,Score,Count)VALUES('250','0,0','0-1,3-5,4-5,5-5,','1','100','100','25','0','0')");
                    
                    JOptionPane.showMessageDialog(null,"New Account created","System Message",JOptionPane.INFORMATION_MESSAGE);
                    Login page = new Login(musicActive);
                    page.setVisible(true);
                    setVisible(false);
                }
            }
        }
        catch (ClassNotFoundException |SQLException ex) {
           // Logger.getLogger(frameNewAccount.class.getName()).log(Level.SEVERE, null, ex);
          JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnSignupActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
       Login page = new Login(musicActive);
       page.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_btnLoginActionPerformed

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

    private void btnSeeCPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeeCPasswordActionPerformed
        if(cbSeeCPassword.isSelected()){
            txtConfirmPassword.setEchoChar('•');
            cbSeeCPassword.setSelected(false);
            btnSeeCPassword.setIcon(icon1);
        }
        else{
            txtConfirmPassword.setEchoChar((char)0);
            cbSeeCPassword.setSelected(true); 
            btnSeeCPassword.setIcon(icon2);
        }
    }//GEN-LAST:event_btnSeeCPasswordActionPerformed

    private void btnSignupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMouseEntered
        btnSignup.setIcon(signup2);
    }//GEN-LAST:event_btnSignupMouseEntered

    private void btnSignupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMouseExited
        btnSignup.setIcon(signup1);
    }//GEN-LAST:event_btnSignupMouseExited

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        btnLogin.setIcon(login2);
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        btnLogin.setIcon(login1);
    }//GEN-LAST:event_btnLoginMouseExited

    public boolean passwordValidation(String userName, String password){
        if (password.length() > 15 || password.length() < 8)
            return false;
        if (password.contains(userName))
            return false;
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
            return false;
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
            return false;
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
            return false;
        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
        return password.matches(specialChars );   
    }
    
    public boolean validateUsername(String Username){
        try{
            Connection conDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/game", "root", "");
            PreparedStatement st = conDB.prepareStatement("select * from info_tbl where Username = ?");
            st.setString(1, Username);
            ResultSet r1=st.executeQuery();
            if(r1.next())
                return false;     
        }catch(SQLException ex){
        }
        return true;    
    }
    
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSeeCPassword;
    private javax.swing.JButton btnSeePassword;
    private javax.swing.JButton btnSignup;
    private javax.swing.JCheckBox cbSeeCPassword;
    private javax.swing.JCheckBox cbSeePassword;
    private javax.swing.JComboBox<String> cmbDay;
    private javax.swing.JComboBox<String> cmbMonth;
    private javax.swing.JComboBox<String> cmbYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label lblErrorCPassword;
    private java.awt.Label lblErrorPassword;
    private java.awt.Label lblErrorUsername;
    private javax.swing.JPanel panel;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
