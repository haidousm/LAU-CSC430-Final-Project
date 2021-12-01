package com.haidousm.rona.client.gui;

import com.haidousm.rona.client.client.Client;
import com.haidousm.rona.common.entity.HealthStatus;
import com.haidousm.rona.common.entity.User;
import com.haidousm.rona.common.enums.Method;
import com.haidousm.rona.common.requests.Request;
import com.haidousm.rona.common.requests.builders.AuthorizedRequestBuilder;
import com.haidousm.rona.common.responses.builders.HealthStatusResponseBuilder;
import com.haidousm.rona.common.responses.builders.UserResponseBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrustedByUsersListGUI extends JFrame {
    private JPanel mainPanel;
    private JTable trustedByUsersTable;
    private JButton backButton;

    private final Client client;

    public TrustedByUsersListGUI(String title, Client client) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        setLocationRelativeTo(null);
        this.client = client;

        backButton.addActionListener(e -> {
            dispose();
            new HomeGUI("Home", client).setVisible(true);
        });

        setupTrustedByUsersTable();

    }

    private void setupTrustedByUsersTable() {
        String[] columnNames = {"Username", "First Name", "Last Name", "Health Status"};
        List<Object[]> data = new ArrayList<>();
        Request request = AuthorizedRequestBuilder.builder().setMethod(Method.GET_TRUSTED_BY_USERS).setToken(client.getToken()).build();
        List<User> users = UserResponseBuilder.builder().buildList(client.sendAndReceive(request));

        for (User user : users) {
            data.add(new Object[]{user.getUsername(), user.getFirstName(), user.getLastName(), user.getHealthStatuses().get(0).getStatus()});
        }

        trustedByUsersTable.setModel(new DefaultTableModel(data.toArray(new Object[data.size()][]), columnNames));
        trustedByUsersTable.setRowHeight(25);
        trustedByUsersTable.setFont(new Font("Arial", Font.PLAIN, 14));
        trustedByUsersTable.setGridColor(Color.LIGHT_GRAY);
        trustedByUsersTable.setShowGrid(true);
        trustedByUsersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        trustedByUsersTable.setRowSelectionAllowed(true);
        trustedByUsersTable.setColumnSelectionAllowed(false);
        trustedByUsersTable.setCellSelectionEnabled(false);
        trustedByUsersTable.setFocusable(false);

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        final JScrollPane scrollPane1 = new JScrollPane();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane1, gbc);
        trustedByUsersTable = new JTable();
        scrollPane1.setViewportView(trustedByUsersTable);
        backButton = new JButton();
        backButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(backButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
