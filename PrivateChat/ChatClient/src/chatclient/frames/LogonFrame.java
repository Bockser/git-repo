package chatclient.frames;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;

/**
 * Created by Bockser on 03.12.2016.
 */
public class LogonFrame extends JFrame implements ChangeListener {

    private ChatClient cahtClient;

    private final String FRM_TITLE = "Logon PrivateChat";

    private final int FRM_LOC_X = 200;
    private final int FRM_LOC_Y = 200;
    private final int FRM_WIDTH = 400;
    private final int FRM_HEIGHT = 600;

    JFormattedTextField addressHost;

    public LogonFrame(ChatClient cahtClient) {
        this.cahtClient = cahtClient;
        setTitle(FRM_TITLE);
        setLocation(FRM_LOC_X, FRM_LOC_Y);
        setSize(FRM_WIDTH, FRM_HEIGHT);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout(50,50));

        Font font = new Font("Arial",0 , 32);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        Border labelBorder = new EmptyBorder(0, 20, 0 , 0);
        JLabel caption = new JLabel("Wellcome!");
        caption.setFont(font);
        caption.setBorder( new EmptyBorder(50, 0,50, 0));
        caption.setHorizontalAlignment(SwingConstants.CENTER);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(caption, c);

        JLabel enterHost = new JLabel("Enter address server:");
        enterHost.setBorder(labelBorder);
        enterHost.setHorizontalAlignment(SwingConstants.LEFT);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(enterHost, c);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("###.###.###.###");
            addressHost = new JFormattedTextField(maskFormatter);
            addressHost.setHorizontalAlignment(SwingConstants.LEFT);
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 1;
            c.gridwidth = 1;
            add(addressHost, c);
        } catch (ParseException e) { e.printStackTrace();}

        JRadioButton signIn = new JRadioButton("Sign in:", true);
        signIn.setBorder(new EmptyBorder(30, 0, 30, 0));
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        add(signIn, c);

        JLabel labelLogin = new JLabel("Enter login:");
        labelLogin.setBorder(labelBorder);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        add(labelLogin, c);

        JTextField tfLogin = new JTextField();
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        add(tfLogin, c);

        JLabel labelPassword = new JLabel("Enter password:");
        labelPassword.setBorder(labelBorder);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        add(labelPassword, c);

        JPasswordField pfPassowrd = new JPasswordField();
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        add(pfPassowrd, c);

        JRadioButton signUp = new JRadioButton("Sign Up:", false);
        signUp.setBorder(new EmptyBorder(30,0,30,0));
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        add(signUp, c);

        ButtonGroup bg = new ButtonGroup();

        JLabel labelNewLogin = new JLabel("Enter new login:");
        labelNewLogin.setBorder(labelBorder);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        add(labelNewLogin, c);

        JTextField tfNewLogin = new JTextField();
        tfNewLogin.setEnabled(false);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 8;
        c.gridwidth = 1;
        add(tfNewLogin, c);

        JLabel labelNewPassword1 = new JLabel("Enter password:");
        labelNewPassword1.setBorder(labelBorder);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        add(labelNewPassword1, c);

        JPasswordField pfNewPassowrd1 = new JPasswordField();
        pfNewPassowrd1.setEnabled(false);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 9;
        c.gridwidth = 1;
        add(pfNewPassowrd1, c);

        bg.add(signIn);
        bg.add(signUp);

        JButton btnConnect = new JButton("Connect");
        btnConnect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                try {
                    cahtClient.connect(InetAddress.getByName(addressHost.getText()), tfLogin.getText());
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
            }
        });

        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 2;
        add(btnConnect, c);

        //pack();
        /*JLabel enterHost = new JLabel("Enter address server:");
        caption.setBorder( new EmptyBorder(0, 20,0, 0));
        enterHost.setHorizontalAlignment(SwingConstants.LEFT);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(enterHost, c);*/


    }

    private JTextField serverAdress;

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
