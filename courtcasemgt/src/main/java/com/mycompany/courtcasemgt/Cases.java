/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.courtcasemgt;

/**
 *
 * @author PC
 */
    public class Cases extends javax.swing.JFrame {
        // Stores the ID of the selected category.
        private int selectedCategoryId;

        // Stores the ID of the selected status.
        private int selectedStatusId;

        // Stores the ID of the selected court.
        private int selectedCourtId;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Cases.class.getName());

    /**
     * Creates new form Cases
     */
    public Cases() {
        initComponents();
            // Load categories from the database.
    loadCategories();

    // Load statuses from the database.
    loadStatuses();

    // Load courts from the database.
    loadCourts();

    // Load existing cases into the JTable.
    loadCases();
    }
    private void loadCategories() {

    String sql =
            "SELECT category_id, category_name "
            + "FROM case_categories "
            + "ORDER BY category_name";

    try (
        java.sql.Connection connection =
                DBConnection.getConnection();

        java.sql.PreparedStatement statement =
                connection.prepareStatement(sql);

        java.sql.ResultSet result =
                statement.executeQuery()
    ) {

        cmbCategory.removeAllItems();

        while (result.next()) {

            int id = result.getInt("category_id");

            String name = result.getString("category_name");

            // Store the ID separately.
            cmbCategory.addItem(new ComboItem(id, name));
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error loading categories: "
                + e.getMessage()
        );
    }
}
     private void loadStatuses() {
        String sql = "SELECT status_id, status_name FROM case_statuses ORDER BY status_name";

        try (
            java.sql.Connection connection = DBConnection.getConnection();
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet result = statement.executeQuery()
        ) {
            cmbStatus.removeAllItems();

            while (result.next()) {
                int id = result.getInt("status_id");
                String name = result.getString("status_name");
                cmbStatus.addItem(new ComboItem(id, name));
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading statuses: " + e.getMessage());
        }
    }
    private void loadCourts() {

    // SQL query to retrieve courts.
    String sql =
            "SELECT court_id, court_name "
            + "FROM courts "
            + "ORDER BY court_name";

    try (
        java.sql.Connection connection =
                DBConnection.getConnection();

        java.sql.PreparedStatement statement =
                connection.prepareStatement(sql);

        java.sql.ResultSet result =
                statement.executeQuery()
    ) {

        // Clear existing ComboBox items.
        cmbCourt.removeAllItems();

        // Read each court.
        while (result.next()) {

            int id =
                    result.getInt("court_id");

            String name =
                    result.getString("court_name");

            // Add court to ComboBox.
            cmbCourt.addItem(
                    new ComboItem(id, name)
            );
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error loading courts: "
                + e.getMessage()
        );
    }
}
    private void loadCases() {

    // SQL query joining cases with their related tables.
    String sql =
            "SELECT c.case_id, "
            + "c.case_number, "
            + "c.case_title, "
            + "cc.category_name, "
            + "cs.status_name, "
            + "co.court_name, "
            + "c.filing_date "
            + "FROM cases c "
            + "JOIN case_categories cc "
            + "ON c.category_id = cc.category_id "
            + "JOIN case_statuses cs "
            + "ON c.status_id = cs.status_id "
            + "JOIN courts co "
            + "ON c.court_id = co.court_id "
            + "ORDER BY c.case_id";

    try (
        java.sql.Connection connection =
                DBConnection.getConnection();

        java.sql.PreparedStatement statement =
                connection.prepareStatement(sql);

        java.sql.ResultSet result =
                statement.executeQuery()
    ) {

        // Get the JTable model.
        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel)
                tblCases.getModel();

        // Remove old rows.
        model.setRowCount(0);

        // Read each case.
        while (result.next()) {

            // Add the case to the JTable.
            model.addRow(new Object[]{

                result.getInt("case_id"),

                result.getString("case_number"),

                result.getString("case_title"),

                result.getString("category_name"),

                result.getString("status_name"),

                result.getString("court_name"),

                result.getDate("filing_date")
            });
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error loading cases: "
                + e.getMessage()
        );
    }
}
    private void clearFields() {

    // Clear case number.
    txtCaseNumber.setText("");

    // Clear case title.
    txtFilingDate.setText("");

    // Clear filing date.
    txtFilingDate.setText("");

    // Select the first category.
    if (cmbCategory.getItemCount() > 0) {
        cmbCategory.setSelectedIndex(0);
    }

    // Select the first status.
    if (cmbStatus.getItemCount() > 0) {
        cmbStatus.setSelectedIndex(0);
    }

    // Select the first court.
    if (cmbCourt.getItemCount() > 0) {
        cmbCourt.setSelectedIndex(0);
    }

    // Return cursor to case number.
    txtCaseNumber.requestFocus();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCaseNumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFilingDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbCourt = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        cmbCategory = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCases = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtCaseTitle1 = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CASE MAGEMENT");

        txtCaseNumber.addActionListener(this::txtCaseNumberActionPerformed);

        jLabel2.setText("case number");

        jLabel3.setText("category");

        jLabel4.setText("case title");

        jLabel5.setText("filing data");

        jTextField5.setText("CLEAR");
        jTextField5.addActionListener(this::jTextField5ActionPerformed);

        jLabel6.setText("status");

        jLabel7.setText("court");

        cmbCourt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCourt.addItemListener(this::cmbCourtItemStateChanged);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbStatus.addActionListener(this::cmbStatusActionPerformed);

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCategory.addActionListener(this::cmbCategoryActionPerformed);

        tblCases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CASE NUMBER", "CASE TITLE", "CATEGORY", "STATUS", "COURT", "FILING DATE"
            }
        ));
        jScrollPane2.setViewportView(tblCases);

        jButton1.setText("SAVE");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel8.setText("UPDATE");

        jLabel9.setText("CLEAR");

        jButton2.setText("DELETE");

        jButton3.setText("CLOSE");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCourt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(156, 156, 156)
                                .addComponent(txtFilingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCaseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtCaseTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(93, 93, 93))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jButton3)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtCaseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cmbCourt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5))
                            .addComponent(txtFilingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jLabel8)
                            .addComponent(jButton2)
                            .addComponent(jLabel9)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtCaseTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        // Get case number.
        String caseNumber =
        txtCaseNumber.getText().trim();

        // Get case title.
        String caseTitle =
        txtFilingDate.getText().trim();

        // Get filing date.
        String filingDate =
        txtFilingDate.getText().trim();

        // Make sure text fields aren't empty.
        if (caseNumber.isEmpty()
            || caseTitle.isEmpty()
            || filingDate.isEmpty()) {

            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Please fill in all required fields."
            );

            return;
        }

        // Get the selected category.
        ComboItem category =
        (ComboItem) cmbCategory.getSelectedItem();

        // Get the selected status.
        ComboItem status =
        (ComboItem) cmbStatus.getSelectedItem();

        // Get the selected court.
        ComboItem court =
        (ComboItem) cmbCourt.getSelectedItem();

        // SQL INSERT statement.
        String sql =
        "INSERT INTO cases "
        + "(case_number, case_title, "
        + "category_id, status_id, court_id, filing_date) "
        + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            java.sql.Connection connection =
            DBConnection.getConnection();

            java.sql.PreparedStatement statement =
            connection.prepareStatement(sql)
        ) {

            // Set case number.
            statement.setString(1, caseNumber);

            // Set case title.
            statement.setString(2, caseTitle);

            // Set category ID.
            statement.setInt(3, category.getId());

            // Set status ID.
            statement.setInt(4, status.getId());

            // Set court ID.
            statement.setInt(5, court.getId());

            // Convert text date to SQL date.
            statement.setDate(
                6,
                java.sql.Date.valueOf(filingDate)
            );

            // Execute INSERT.
            statement.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Case saved successfully!"
            );

            // Clear fields.
            clearFields();

            // Refresh JTable.
            loadCases();

        } catch (IllegalArgumentException e) {

            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Invalid date. Use YYYY-MM-DD."
            );

        } catch (java.sql.SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error saving case: "
                + e.getMessage()
            );
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();//closes jframe
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoryActionPerformed

    private void txtCaseNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaseNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaseNumberActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void cmbCourtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCourtItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCourtItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Cases().setVisible(true));
    }
//private javax.swing.JComboBox<Object> cmbCategory;
//private javax.swing.JComboBox<Object> cmbCourt;
//private javax.swing.JComboBox<Object> cmbStatus;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> cmbCategory;
    private javax.swing.JComboBox<Object> cmbCourt;
    private javax.swing.JComboBox<Object> cmbStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTable tblCases;
    private javax.swing.JTextField txtCaseNumber;
    private javax.swing.JTextField txtCaseTitle1;
    private javax.swing.JTextField txtFilingDate;
    // End of variables declaration//GEN-END:variables
}
