package edu.utexas.ee382vJulien;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class PanelChatroomProvider extends JPanel {

    /**
     * Create the panel.
     */
    public PanelChatroomProvider() {
        
        JLabel lblName = new JLabel("Name: ");
        
        JLabel lblDescription = new JLabel("Description:");
        
        JLabel lblClients = new JLabel("Clients: ");
        
        JButton btnCloseChatroom = new JButton("Close Chatroom");
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblClients, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
                            .addComponent(btnCloseChatroom))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(lblDescription))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblClients)
                        .addComponent(btnCloseChatroom))
                    .addContainerGap(25, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

    }

}
