import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class View extends JFrame {

    private JTextField encrypt_text;
    private JTextField decrypt_text;
    private JTextField encrypt_key_text;
    private JTextField decrypt_key_text;

    private JButton encrypt_button;
    private JButton decrypt_button;
    private JTextArea output_area;

    private JRadioButton binary_button;
    private JRadioButton ascii_button;
    private Box box1,box2,box3,box4,box5,boxV;
    public View(){
        setTitle("SAES加密机");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());


        boxV = Box.createVerticalBox();
        box1 = Box.createHorizontalBox();
        box2 = Box.createHorizontalBox();
        box3 = Box.createHorizontalBox();
        box4 = Box.createHorizontalBox();
        box5 = Box.createHorizontalBox();

        JLabel title = new JLabel("SDES加密机");
        title.setFont(new Font("微软雅黑",Font.BOLD,45));
        box1.add(title);

        box2.add(new JLabel("加密信息：  "));
        encrypt_text = new JTextField(20);
        box2.add(encrypt_text);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(new JLabel("密钥：  "));
        encrypt_key_text = new JTextField(20);
        box2.add(encrypt_key_text);
        box2.add(Box.createHorizontalStrut(20));
        encrypt_button = new JButton("加密");
        encrypt_button.setFont(new Font("微软雅黑",Font.BOLD,12));
        encrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(binary_button.isSelected()){
                    System.out.println("二进制加密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    //正则匹配01字符串
                    Pattern pattern = Pattern.compile("[0-1]*");
                    if(!pattern.matcher(encrypt_text.getText()).matches() || encrypt_text.getText().length()!=16 ){
                        JOptionPane.showMessageDialog( null,"请输入16bit二进制加密信息！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        info = encrypt_text.getText();
                    }

                    if(!pattern.matcher(encrypt_key_text.getText()).matches() || encrypt_key_text.getText().length()%16 !=0){
                        JOptionPane.showMessageDialog( null,"请输入16的整数倍bit的二进制密钥！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        key = encrypt_key_text.getText();
                    }

                    if(flag==true){
                        //System.out.println(info);

                        //加密过程


                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());

                        if(output_area.getText().length()!=0){
                            output_area.setText(output_area.getText()+"\n\n"+"时间："+formatter.format(date)+"\n操作：二进制加密\n明文："+info+"\n密钥："+key+"\n密文："+Multiple_encryptor.encrypt(info,key,"binary")+"\n");
                        } else{
                            output_area.setText("时间："+formatter.format(date)+"\n操作：二进制加密\n明文："+info+"\n密钥："+key+"\n密文："+Multiple_encryptor.encrypt(info,key,"binary")+"\n");
                        }

                    }else {
                        System.out.println("未加密");
                    }




                } else if (ascii_button.isSelected()) {
                    System.out.println("ascii加密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    //正则匹配01字符串
                    Pattern pattern_01 = Pattern.compile("[0-1]*");
                    Pattern pattern_ascii = Pattern.compile("\\A\\p{ASCII}*\\z");
                    if(!pattern_ascii.matcher(encrypt_text.getText()).matches() || encrypt_text.getText().length()==0 ){
                        JOptionPane.showMessageDialog( null,"请输入ASCII加密信息！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        info = encrypt_text.getText();
                        if(info.length()%2 !=0){
                            info = encrypt_text.getText();
                            info = info+" ";
                            System.out.println(info);
                        }
                    }

                    if(!pattern_01.matcher(encrypt_key_text.getText()).matches() || encrypt_key_text.getText().length()%16 !=0){
                        JOptionPane.showMessageDialog( null,"请输入16的整数倍bit的二进制密钥！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        key = encrypt_key_text.getText();
                    }

                    if(flag==true){
                        //System.out.println(info);
                        //加密过程


                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());

                        if(output_area.getText().length()!=0){
                            output_area.setText(output_area.getText()+"\n\n"+"时间："+formatter.format(date)+"\n操作：ASCII码加密\n明文："+info+"\n密钥："+key+"\n密文："+Multiple_encryptor.encrypt(info,key,"ascii")+"\n");
                        } else{
                            output_area.setText("时间："+formatter.format(date)+"\n操作：ASCII码加密\n明文："+info+"\n密钥："+key+"\n密文："+Multiple_encryptor.encrypt(info,key,"ascii")+"\n");
                        }

                    }else {
                        System.out.println("未加密");
                    }

                }else {
                    JOptionPane.showMessageDialog( null,"请先选择输入格式","执行结果", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        box2.add(encrypt_button);


        box3.add(new JLabel("解密信息：  "));
        decrypt_text = new JTextField(20);
        box3.add(decrypt_text);
        box3.add(Box.createHorizontalStrut(20));
        box3.add(new JLabel("密钥：  "));
        decrypt_key_text = new JTextField(20);
        box3.add(decrypt_key_text);
        box3.add(Box.createHorizontalStrut(20));
        decrypt_button = new JButton("解密");
        decrypt_button.setFont(new Font("微软雅黑",Font.BOLD,12));
        decrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(binary_button.isSelected()){
                    System.out.println("二进制解密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    //正则匹配01字符串
                    Pattern pattern = Pattern.compile("[0-1]*");
                    if(!pattern.matcher(decrypt_text.getText()).matches() || decrypt_text.getText().length()!=16 ){
                        JOptionPane.showMessageDialog( null,"请输入16bit二进制解密信息！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        info = decrypt_text.getText();
                    }

                    if(!pattern.matcher(decrypt_key_text.getText()).matches() || decrypt_key_text.getText().length()%16 !=0){
                        JOptionPane.showMessageDialog( null,"请输入16的整数倍bit的二进制密钥！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        key = decrypt_key_text.getText();
                    }

                    if(flag==true){
                        //System.out.println(info);

                        //解密过程


                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());

                        if(output_area.getText().length()!=0){
                            output_area.setText(output_area.getText()+"\n\n"+"时间："+formatter.format(date)+"\n操作：二进制解密\n密文："+info+"\n密钥："+key+"\n明文："+Multiple_decryptor.decrypt(info,key,"binary")+"\n");
                        } else{
                            output_area.setText("时间："+formatter.format(date)+"\n操作：二进制解密\n密文："+info+"\n密钥："+key+"\n明文："+Multiple_decryptor.decrypt(info,key,"binary")+"\n");
                        }
                    }else {
                        System.out.println("未加密");
                    }
                } else if (ascii_button.isSelected()) {
                    System.out.println("ascii解密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    //正则匹配01字符串
                    Pattern pattern_01 = Pattern.compile("[0-1]*");
                    Pattern pattern_ascii = Pattern.compile("\\A\\p{ASCII}*\\z");
//                    if(!pattern_ascii.matcher(decrypt_text.getText()).matches() || decrypt_text.getText().length()==0 ){
//                        JOptionPane.showMessageDialog( null,"请输入ASCII解密信息！","警告", JOptionPane.WARNING_MESSAGE);
//                        flag = false;
//                    }else {
                    info = decrypt_text.getText();
                    if(info.length()%2 !=0){
                        info = decrypt_text.getText();
                        info = info+" ";
                        System.out.println(info);
                    }
//                    }

                    if(!pattern_01.matcher(decrypt_key_text.getText()).matches() || decrypt_key_text.getText().length()%16 !=0){
                        JOptionPane.showMessageDialog( null,"请输入16的整数倍bit的二进制密钥！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        key = decrypt_key_text.getText();
                    }

                    if(flag==true){
                        //System.out.println(info);

                        //解密过程

                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());

                        if(output_area.getText().length()!=0){
                            output_area.setText(output_area.getText()+"\n\n"+"时间："+formatter.format(date)+"\n操作：ASCII码解密\n密文："+info+"\n密钥："+key+"\n明文："+Multiple_decryptor.decrypt(info,key,"ascii")+"\n");
                        } else{
                            output_area.setText("时间："+formatter.format(date)+"\n操作：ASCII码解密\n密文："+info+"\n密钥："+key+"\n明文："+Multiple_decryptor.decrypt(info,key,"ascii")+"\n");
                        }
                    }else {
                        System.out.println("未加密");
                    }

                }else {
                    JOptionPane.showMessageDialog( null,"请先选择输入格式","提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        box3.add(decrypt_button);

        output_area = new JTextArea(22,60);
        output_area.setEditable(false);
        box4.add(new JScrollPane(output_area));

        binary_button = new JRadioButton("二进制");
        ascii_button = new JRadioButton("ASCII");
        ButtonGroup group = new ButtonGroup();
        group.add(binary_button);
        group.add(ascii_button);
        box5.add(new JLabel("输入信息格式："));
        box5.add(binary_button);
        box5.add(ascii_button);


        boxV.add(box1);
        boxV.add(Box.createVerticalStrut(30));
        boxV.add(box2);
        boxV.add(box3);
        boxV.add(box5);
        boxV.add(Box.createVerticalStrut(20));
        boxV.add(box4);


        add(boxV);
    }

    public static void main(String[] args){
        View view = new View();
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }
}
