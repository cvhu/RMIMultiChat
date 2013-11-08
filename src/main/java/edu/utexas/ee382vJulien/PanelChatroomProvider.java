package edu.utexas.ee382vJulien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * The GUI components used to display chatroom information on the Chatroom Provider GUI.
 * 
 * @author cvhu
 *
 */
@SuppressWarnings("serial")
public class PanelChatroomProvider extends JPanel {
    
    private JLabel lblName;
    private JLabel lblDescription;
    private JLabel lblClients;
    private ChatroomServerImpl chatroom;
    private WindowChatroomProvider provider;
    
    /**
     * Create the panel.
     */
    public PanelChatroomProvider(ChatroomServerImpl chatroom, WindowChatroomProvider provider) {
        this.chatroom = chatroom;
        this.provider = provider;
        initialize();
        setChatroom();
    }
    
    /** 
     * Set the corresponding chatroom information on the GUI.
     */
    public void setChatroom() {
        try {
            lblName.setText("Name:" + chatroom.getName());
            lblDescription.setText("Description:" + chatroom.getDescription());
            lblClients.setText(chatroom.getClientCount() + " joined");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Update the clients count on the GUI.
     * 
     * @param count The new clients count to be updated.
     */
    public void updateJoins(Integer count) {
        lblClients.setText(count + " joined");
    }
    
    /**
     * Setup the GUI components.
     */
    public void initialize() {
        lblName = new JLabel("Name: ");
        
        lblDescription = new JLabel("Description:");
        
        lblClients = new JLabel("Clients: ");
        
        JButton btnCloseChatroom = new JButton("Close Chatroom");
        btnCloseChatroom.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                chatroom.closeChatroom();
                try {
                    PanelChatroomProvider.this.provider.removePanel(PanelChatroomProvider.this.chatroom.getId());
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });
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
