package edu.utexas.ee382vJulien;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class PanelChatClient extends JPanel {
    
    private JLabel lblName;
    private JLabel lblDescription;
    private JLabel lblJoined;
    private ChatroomServer chatroom;
    private ChatClientImpl chatClient;
    private JButton btnJoin;
    private JButton btnLeave;

    /**
     * Create the panel.
     */
    public PanelChatClient(ChatroomServer chatroom, ChatClientImpl chatClient) {
        this.chatroom = chatroom;
        this.chatClient = chatClient;
        initialize();
        setChatroom();
    }
    
    public void updateJoins(Integer count) {
        lblJoined.setText(count + " joined");
        revalidate();
        repaint();
    }
    
    public void setChatroom() {
        try {
            lblName.setText("Name: " + chatroom.getName());
            lblDescription.setText("Description: " + chatroom.getDescription());
            lblJoined.setText(chatroom.getClientCount() + " joined");
        } catch (RemoteException e) {
            lblName.setText("Connection error");
            lblDescription.setText("");
            lblJoined.setText("");
        }
    }
    
    public void joinChatroom() {
        btnLeave.setEnabled(true);
        btnJoin.setEnabled(false);
        try {
            chatClient.joinChatroom(chatroom.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public void leaveChatroom() {
        btnLeave.setEnabled(false);
        btnJoin.setEnabled(true);
        try {
            chatClient.leaveChatroom(chatroom.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public void initialize() {
        lblName = new JLabel("Name: ");
        
        lblDescription = new JLabel("Description: ");
        lblDescription.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        lblDescription.setVerticalAlignment(SwingConstants.TOP);
        
        btnJoin = new JButton("Join");
        btnJoin.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                joinChatroom();
            }
        });
        
        btnLeave = new JButton("Leave");
        btnLeave.setEnabled(false);
        btnLeave.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                leaveChatroom();
            }
        });
        
        lblJoined = new JLabel("0 joined");
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
