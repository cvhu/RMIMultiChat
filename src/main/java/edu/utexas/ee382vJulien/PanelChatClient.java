package edu.utexas.ee382vJulien;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanelChatClient extends JPanel {

    /**
     * Create the panel.
     */
    public PanelChatClient() {
        
        JLabel lblName = new JLabel("Name: ");
        
        JLabel lblDescription = new JLabel("Description: ");
        lblDescription.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        lblDescription.setVerticalAlignment(SwingConstants.TOP);
        
        JButton btnJoin = new JButton("join");
        
        JButton btnLeave = new JButton("Leave");
        
        JLabel lblJoined = new JLabel("0 joined");
        lblJoined.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblDescription, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblJoined))
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnJoin)
                            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLeave)))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(lblJoined))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnJoin)
                        .addComponent(btnLeave)))
        );
        setLayout(groupLayout);

    }

}
