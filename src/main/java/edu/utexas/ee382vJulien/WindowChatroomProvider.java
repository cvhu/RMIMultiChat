package edu.utexas.ee382vJulien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

public class WindowChatroomProvider {

    private JFrame frame;
    private JTextField textFieldName;
    private JTextField textFieldDescription;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowChatroomProvider window = new WindowChatroomProvider();
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
    public WindowChatroomProvider() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 576, 730);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelAddChatroom = new JPanel();
        
        JPanel panelChatrooms = new JPanel();
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panelAddChatroom, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                        .addComponent(panelChatrooms, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE))
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
        panelChatrooms.setLayout(new BorderLayout(0, 0));
        
        JLabel lblName = new JLabel("Name");
        
        JLabel lblDescription = new JLabel("Description");
        
        textFieldName = new JTextField();
        textFieldName.setColumns(10);
        
        textFieldDescription = new JTextField();
        textFieldDescription.setColumns(10);
        
        JButton btnAddChatroom = new JButton("Add Chatroom");
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
                        .addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gl_panelAddChatroom.createSequentialGroup()
                            .addComponent(textFieldDescription, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                            .addComponent(btnAddChatroom)))
                    .addContainerGap())
        );
        gl_panelAddChatroom.setVerticalGroup(
            gl_panelAddChatroom.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelAddChatroom.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelAddChatroom.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(12)
                    .addGroup(gl_panelAddChatroom.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblDescription)
                        .addComponent(textFieldDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddChatroom))
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        panelAddChatroom.setLayout(gl_panelAddChatroom);
        frame.getContentPane().setLayout(groupLayout);
    }
}
