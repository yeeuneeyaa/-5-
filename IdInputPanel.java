package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import frame.MainFrame;
import image.Background;
import main.*;

public class IdInputPanel extends JPanel{
    //MainFrame
    MainFrame superFrame;

    //IdInputPanel Component
    JTextField idInputField = new JTextField("10 글자 이하의 유저이름을 입력하시오.", 20);
    JButton idInputButton = new JButton("확인");
    JLabel idInputPanelBackGround = new JLabel();

    //IdInputPanel initialize method
    public IdInputPanel(MainFrame superFrame){
        this.superFrame = superFrame;   //상위 프레임 접근을 위한 Frame 변수
        this.setPanel();                //Panel 설정
        this.setComponent();            //Panel Component 설정
    }
    private void setPanel(){
        this.setLayout(null);
    }
    private void setComponent(){
        idInputField.setBounds(300,200, 300, 40);
        idInputField.setBackground(Color.BLACK);
        idInputField.setForeground(Color.GREEN);
        idInputField.setHorizontalAlignment(JTextField.CENTER);
        idInputField.setFont(idInputField.getFont().deriveFont(Font.BOLD, 14));
        idInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idInputField.getText().equals("10 글자 이하의 유저이름을 입력하시오.")) {
                    idInputField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Optionally, you can add code here to restore the default text if the field is empty
                if (idInputField.getText().isEmpty()) {
                    idInputField.setText("10 글자 이하의 유저이름을 입력하시오.");
                }
            }
        });
        this.add(idInputField);

        idInputButton.setBounds(400,260,100,50);
        idInputButton.setBackground(Color.GREEN);
        idInputButton.setForeground(Color.BLACK);
        idInputButton.setFont(idInputButton.getFont().deriveFont(Font.BOLD, 14f));
        idInputButton.addMouseListener(new IdInputButtonMouseListener());
        this.add(idInputButton);

        idInputPanelBackGround.setIcon(new Background("img/idInput/idInputBackground.png"));
        idInputPanelBackGround.setBounds(0, 0, 900, 500);
        this.add(idInputPanelBackGround);
    }

    class IdInputButtonMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            String userInput = idInputField.getText().trim();
            if (userInput.isEmpty() || userInput.equals("10 글자 이하의 유저이름을 입력하시오.")) {
                // ID 입력란이 비어 있거나 기본 텍스트인 경우 오류 메시지 표시
                JOptionPane.showMessageDialog(superFrame, "유저이름을 입력하세요!", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Main.userName = idInputField.getText();
            //유저이름의 길이를 10글자로 고정
            if(Main.userName.length() < 10) {
                int insufficientNumberOfCharacters = 10 - Main.userName.length();
                for(int i = 0; i < insufficientNumberOfCharacters; i++) Main.userName += " ";
            }
            else Main.userName = Main.userName.substring(0, 10);

            superFrame.getLayout().show(superFrame.getContentPane(), "GameModeSelectPanel"); //gameModeSelect 패널을 카드레이아웃 최상단으로 변경
            superFrame.setVisible(true);
        }
    }
}