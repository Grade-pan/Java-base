package NIO和TCP;

import javax.swing.*;

public interface GUI_way {
    //获取下拉列表内容
    static String methodA(JComboBox jc) {
        return jc.getSelectedItem().toString();
    }

    //退出程序
    static void exit() {
        System.exit(0);
    }

    //获取文本框内容
    static String getText(JTextField jt) {
        String text = jt.getText();
        return text;
    }

    //将文本框内容清空
    static void Clear_Text(JTextField jt) {
        jt.setText("");
    }
}
