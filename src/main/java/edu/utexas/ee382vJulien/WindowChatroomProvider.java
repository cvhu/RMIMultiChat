package edu.utexas.ee382vJulien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

/**
 * The GUI window for Chatroom Provider.
 * 
 * @author cvhu
 *
 */
public class WindowChatroomProvider {

    private JFrame frame;
    private JTextField textFieldName;
    private JTextField textFieldDescription;
    private JLabel lblMessage;
    private JPanel chatrooms;
    private ChatroomProvider chatroomProvider;
    private HashMap<String, PanelChatroomProvider> panelsMap;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowChatroomProvider window = new WindowChatroomProvider(new ChatroomProvider("localhost"));
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public WindowChatroomProvider(ChatroomProvider chatroomProvider) {
        this.chatroomProvider = chatroomProvider;
        panelsMap = new HashMap<String, PanelChatroomProvider>();
        initialize();
        frame.setVisible(true);
    }

    /**
     * Add a new chatroom.
     */
    public void addChatroom() {
        String name = textFieldName.getText().trim();
        String description = textFieldDescription.getText().trim();
        if (name.isEmpty() || description.isEmpty()) {
            lblMessage.setText("Both name and description are required");
        } else {
            Chatroom chatroom = new Chatroom(name, description);
            ChatroomServerImpl server = chatroomProvider.addChatroom(chatroom);
            PanelChatroomProvider chatroomPanel = new PanelChatroomProvider(server, this);
            chatrooms.add(chatroomPanel);
            panelsMap.put(chatroom.getId(), chatroomPanel);
            chatrooms.revalidate();
            chatrooms.repaint();
            server.attachPanel(chatroomPanel);
            resetForm();
        }
    }
    
    /**
     * Remove a chatroom when closed.
     * 
     * @param chatroomId The chatroom to be removed.
     */
    public void removePanel(String chatroomId) {
        chatrooms.remove(panelsMap.remove(chatroomId));
        chatrooms.revalidate();
        chatrooms.repaint();
    }
    
    /**
     * Reset the form when submitted.
     */
    public void resetForm() {
        textFieldName.setText("");
        textFieldDescription.setText("");
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 667, 730);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chatroom Provider");
        
        JPanel panelAddChatroom = new JPanel();
        
        JPanel panelChatrooms = new JPanel();
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(panelChatrooms, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                        .addComponent(panelAddChatroom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelAddChatroom, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelChatrooms, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(7, Short.MAX_VALUE))
        );
        panelChatrooms.setLayout(new BorderLayout(4, 4));
        
        JLabel lblName = new JLabel("Name");
        
        JLabel lblDescription = new JLabel("Description");
        
        textFieldName = new JTextField();
        textFieldName.setColumns(10);
        
        textFieldDescription = new JTextField();
        textFieldDescription.setColumns(10);
        
        JButton btnAddChatroom = new JButton("Add Chatroom");
        btnAddChatroom.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addChatroom();
            }
        });
        
        lblMessage = new JLabel("");
        lblMessage.setForeground(Color.RED);
        GroupLayout gl_panelAddChatroom = new GroupLayout(panelAddChatroom);
        gl_panelAddChatroom.setHorizontalGroup(
            gl_panelAddChatroom.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelAddChatroom.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelAddChatroom.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblName)
                        .addComponent(lblDescription))
                    .addGap(18)
                    .addGroup(gl_panelAddChatroom.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelAddChatroom.createSequentialGroup()
                            .addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                            .addComponent(lblMessage))
                        .addGroup(gl_panelAddChatroom.createSequentialGroup()
                            .addComponent(textFieldDescription, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                            .addComponent(btnAddChatroom)))
                    .addContainerGap())
        );
        gl_panelAddChatroom.setVerticalGroup(
            gl_panelAddChatroom.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelAddChatroom.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelAddChatroom.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMessage))
                    .addGap(12)
                    .addGroup(gl_panelAddChatroom.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblDescription)
                        .addComponent(textFieldDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddChatroom))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAddChatroom.setLayout(gl_panelAddChatroom);
        frame.getContentPane().setLayout(groupLayout);
        
        panelChatrooms.setBorder(new TitledBorder("Chatrooms"));
        
        chatrooms = new JPanel(new GridLayout(0, 1, 3, 3));
        panelChatrooms.add(new JScrollPane(chatrooms), BorderLayout.CENTER);
    }
}
