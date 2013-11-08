package edu.utexas.ee382vJulien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

public class WindowChatClient {

    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
    private JPanel panelChatrooms;
    private JComboBox comboBoxChatroom;
    private StringBuffer stringBuffer;
    private ChatClientImpl chatClient;
    private HashMap<String, PanelChatClient> panelsMap;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowChatClient window = new WindowChatClient(new ChatClientImpl("localhost"));
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
    public WindowChatClient(ChatClientImpl chatClient) {
        this.chatClient = chatClient;
        panelsMap = new HashMap<String, PanelChatClient>();
        chatClient.attachWindow(this);
        this.stringBuffer = new StringBuffer();
        initialize();
        frame.setVisible(true);
    }
    
    public void joinChatroom(ChatroomServer chatroom) {
        try {
            ComboItem comboItem = new ComboItem(chatroom.getName(), chatroom.getId());
            comboBoxChatroom.addItem(comboItem);
        } catch (RemoteException e) {
            comboBoxChatroom.addItem("Connection Error");
        }
    }
    
    public void addChatroom(ChatroomServer chatroom) {
        PanelChatClient panelChatroom = new PanelChatClient(chatroom, chatClient);
        panelChatrooms.add(panelChatroom);
        panelChatrooms.revalidate();
        panelChatrooms.repaint();
        try {
            panelsMap.put(chatroom.getId(), panelChatroom);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public void leaveChatroom(String chatroomId) {
        for (int i = 0; i < comboBoxChatroom.getItemCount(); i++) {
            ComboItem comboItem = (ComboItem) comboBoxChatroom.getItemAt(i);
            if (comboItem.getValue().equals(chatroomId)) {
                comboBoxChatroom.remove(i);
                break;
            }
        }
    }
    
    public void removeChatroom(String chatroomId) {
        leaveChatroom(chatroomId);
        panelChatrooms.remove(panelsMap.remove(chatroomId));
        panelChatrooms.revalidate();
        panelChatrooms.repaint();
    }
    
    public void updateJoins(String chatroomId, Integer count) {
        panelsMap.get(chatroomId).updateJoins(count);
    }
    
    public void sendMessage() {
        chatClient.talk((String) ((ComboItem) comboBoxChatroom.getSelectedObjects()[0]).getValue(), textField.getText());
        textField.setText("");
    }
    
    public void showMessage(String message) {
        stringBuffer.append(message);
        stringBuffer.append("\n");
        textArea.setText(stringBuffer.toString());
    }
    
    public void setTitle(String title) {
        frame.setTitle(String.format("Chat Client (ID:%s)", title));
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 647, 748);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chat Client");
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowChatClient.this.chatClient.closeClient();
                e.getWindow().dispose();
            }
        });
        
        JPanel panelChatroomsWrapper = new JPanel();
        
        JPanel panelChatHistory = new JPanel();
        
        JPanel panelSendMessage = new JPanel();
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panelSendMessage, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                            .addComponent(panelChatroomsWrapper, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(panelChatHistory, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panelChatroomsWrapper, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                        .addComponent(panelChatHistory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelSendMessage, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                    .addGap(4))
        );
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        GroupLayout gl_panelChatHistory = new GroupLayout(panelChatHistory);
        gl_panelChatHistory.setHorizontalGroup(
            gl_panelChatHistory.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panelChatHistory.createSequentialGroup()
                    .addContainerGap(73, Short.MAX_VALUE)
                    .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        gl_panelChatHistory.setVerticalGroup(
            gl_panelChatHistory.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelChatHistory.createSequentialGroup()
                    .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChatHistory.setLayout(gl_panelChatHistory);
        
        comboBoxChatroom = new JComboBox();
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        
        JLabel lblChooseChatroom = new JLabel("Choose Chatroom");
        
        JLabel lblMessage = new JLabel("Message");
        
        JLabel lblStatus = new JLabel("");
        lblStatus.setForeground(Color.RED);
        GroupLayout gl_panelSendMessage = new GroupLayout(panelSendMessage);
        gl_panelSendMessage.setHorizontalGroup(
            gl_panelSendMessage.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelSendMessage.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelSendMessage.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblChooseChatroom)
                        .addComponent(comboBoxChatroom, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
                    .addGroup(gl_panelSendMessage.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelSendMessage.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                            .addGroup(gl_panelSendMessage.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_panelSendMessage.createSequentialGroup()
                                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(btnSend))
                                .addGroup(gl_panelSendMessage.createSequentialGroup()
                                    .addComponent(lblStatus)
                                    .addContainerGap())))
                        .addGroup(gl_panelSendMessage.createSequentialGroup()
                            .addGap(27)
                            .addComponent(lblMessage)
                            .addContainerGap())))
        );
        gl_panelSendMessage.setVerticalGroup(
            gl_panelSendMessage.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panelSendMessage.createSequentialGroup()
                    .addContainerGap(9, Short.MAX_VALUE)
                    .addGroup(gl_panelSendMessage.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblChooseChatroom)
                        .addComponent(lblStatus)
                        .addComponent(lblMessage))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelSendMessage.createParallelGroup(Alignment.BASELINE)
                        .addComponent(comboBoxChatroom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSend))
                    .addContainerGap())
        );
        panelSendMessage.setLayout(gl_panelSendMessage);
        frame.getContentPane().setLayout(groupLayout);
        
        panelChatroomsWrapper.setLayout(new BorderLayout(4, 4));
        panelChatroomsWrapper.setBorder(new TitledBorder("Chatrooms"));
        
        panelChatrooms = new JPanel(new GridLayout(0, 1, 3, 1));
        panelChatroomsWrapper.add(new JScrollPane(panelChatrooms), BorderLayout.CENTER);
    }
}

class ComboItem {
    private String label;
    private String value;
    
    public ComboItem (String label, String value) {
        this.label = label;
        this.value = value;
    }
    
    public String toString() {
        return label;
    }
    
    public String getValue() {
        return value;
    }
}
