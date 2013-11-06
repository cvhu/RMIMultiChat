package edu.utexas.ee382vJulien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class WindowChatClient {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowChatClient window = new WindowChatClient();
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
    public WindowChatClient() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 581, 748);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        
        JPanel panel_1 = new JPanel();
        
        JPanel panel_2 = new JPanel();
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 565, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(10, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(8, Short.MAX_VALUE))
        );
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
        
        JComboBox comboBoxChatroom = new JComboBox();
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JButton btnSend = new JButton("Send");
        
        JLabel lblChooseChatroom = new JLabel("Choose Chatroom");
        
        JLabel lblMessage = new JLabel("Message");
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
            gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addComponent(comboBoxChatroom, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblChooseChatroom))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                            .addComponent(textField, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnSend))
                        .addGroup(gl_panel_2.createSequentialGroup()
                            .addComponent(lblMessage)
                            .addContainerGap(345, Short.MAX_VALUE))))
        );
        gl_panel_2.setVerticalGroup(
            gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
                    .addContainerGap(9, Short.MAX_VALUE)
                    .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblChooseChatroom)
                        .addComponent(lblMessage))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
                        .addComponent(comboBoxChatroom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSend))
                    .addContainerGap())
        );
        panel_2.setLayout(gl_panel_2);
        frame.getContentPane().setLayout(groupLayout);
    }
}
