import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainActivity14 {		
	
	//TODO 메인 메소드 시작 부분
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[GUI (awt/swing) - 직원 근퇴관리 프로그램 만들기 (DefaultTableModel , JTable , JScrollPane)]");
		
		/*[설 명]
		 * 1. 자바에서는 JFrame을 사용해서 GUI 프로그램을 만들 수 있습니다
		 * 2. DefaultTableModel : 테이블 데이터를 관리합니다
		 * 3. JTable : 테이블을 생성합니다
		 * 4. JScrollPane : 스크롤 패널을 생성합니다
		 * */	
		
		//TODO 부모 프레임 생성 및 기본 스타일 지정 실시
		JFrame frm = new JFrame("근퇴 관리 프로그램");
		setJFrameStyle(frm);
		
		
		//TODO 자식 레이아웃 [테이블] 생성
		String tittleArray[] = {"이름", "부서", "성별","상벌점"};
		DefaultTableModel model = new DefaultTableModel(tittleArray, 0);
		JTable table = new JTable(model);		
		JScrollPane scrollPane = new JScrollPane(table);	
		scrollPane.getViewport().setBackground(Color.LIGHT_GRAY); //JScrollPane에 배경색 지정
		scrollPane.setBounds(0, 0, 500, 350); //TODO setBounds(가로위치, 세로위치, 가로길이, 세로길이);
		
		
		//TODO 자식 레이아웃 [입력 부분] 생성
		JTextField tfName = new JTextField(6); // 텍스트 입력 창 자리수 지정
		JTextField tfAge = new JTextField(6); // 텍스트 입력 창 자리수 지정
		JRadioButton rb1 = new JRadioButton("남자");		
		JRadioButton rb2 = new JRadioButton("여자");
		rb1.setSelected(true); // 초기 성별 선택 설정
		
		JPanel input_flow_panel = new JPanel();
		input_flow_panel.setLayout(new FlowLayout()); // 가운데 중심 배치 레이아웃 (윈도우창 크기를 벗어나면 자동 줄바꿈 실시)
		input_flow_panel.add(new JLabel("이름"));
		input_flow_panel.add(tfName);
		input_flow_panel.add(new JLabel("부서"));
		input_flow_panel.add(tfAge);
		input_flow_panel.add(rb1);
		input_flow_panel.add(rb2);
		input_flow_panel.setBackground(Color.RED);
		input_flow_panel.setBounds(0, 350, 500, 50); // setBounds(가로위치, 세로위치, 가로길이, 세로길이);
		
		
		//TODO 자식 레이아웃 [추가 버튼] 생성
		JButton add_btn = new JButton("추가");
		setJButtonStyle(add_btn); // 기본 버튼 스타일 지정 메소드 호출
		
		JButton del_btn = new JButton("삭제");			
		setJButtonStyle(del_btn); // 기본 버튼 스타일 지정 메소드 호출
		
		JPanel btn_flow_panel = new JPanel();
		btn_flow_panel.setLayout(new FlowLayout()); // 가운데 중심 배치 레이아웃 (윈도우창 크기를 벗어나면 자동 줄바꿈 실시)
		btn_flow_panel.add(add_btn);
		btn_flow_panel.add(del_btn);
		btn_flow_panel.setBackground(Color.LIGHT_GRAY);
		btn_flow_panel.setBounds(0, 400, 500, 100); // setBounds(가로위치, 세로위치, 가로길이, 세로길이);
		
		
		//TODO 자식 레이아웃 [버튼] 클릭 이벤트 정의 실시		
		ActionListener action = new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if (e.getActionCommand().equals("추가")) { // 추가 버튼 클릭이벤트 처리
					//TODO 입력된 값 테이블에 추가하기
					String info[] = new String[3]; //한행 (row) 에 저장할 데이터 모음
					info[0] = tfName.getText();
					info[1] = tfAge.getText();
					if(rb1.isSelected()) {
						info[2] = "남자";
					} 
					else {
						info[2] = "여자";
					} 
					model.addRow(info); //테이블 모델에 데이터 삽입 실시
					
					//TODO 입력후 텍스트 필드 값 제거
					tfName.setText("");
					tfAge.setText("");
					rb1.setSelected(true); //라디오 초기화
					rb2.setSelected(false); //라디오 초기화
				}
				else if(e.getActionCommand().equals("삭제")) { // 삭제 버튼 클릭이벤트 처리
					//선택한 줄(row)의 번호 알아내기
					int rowIndex = table.getSelectedRow();
					
					//선택 안하고 누를 경우 리턴값 -1
					if(rowIndex == -1) {
						return;	
					}
					model.removeRow(rowIndex);
				}
				else if(e.getActionCommand().equals("남자")) { // 라디오 버튼 클릭이벤트 처리
					if(rb1.isSelected() == true) { //선택된 경우						
						rb2.setSelected(false); //라디오 변경
					}					
				}
				else if(e.getActionCommand().equals("여자")) { // 라디오 버튼 클릭이벤트 처리
					if(rb2.isSelected() == true) { //선택된 경우
						rb1.setSelected(false); //라디오 변경
					}					
				}
			}
		};
		add_btn.addActionListener(action);
		del_btn.addActionListener(action);
		rb1.addActionListener(action);
		rb2.addActionListener(action);
				
		
		//TODO 부모 프레임에다가 자식 컴포넌트 추가		
		frm.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frm.getContentPane().add(input_flow_panel);
		frm.getContentPane().add(btn_flow_panel);
		
		
		//TODO 부모 프레임이 보이도록 설정
		frm.setVisible(true);
		
	}//메인 종료
	
	//TODO JFrame 부모 기본 스타일 지정 메소드
	public static void setJFrameStyle(JFrame frame) {
		//TODO 부모 프레임 크기 설정 (가로, 세로) 및 배경색 지정
		frame.setSize(500, 500);
		frame.setBackground(Color.BLACK);
		 
		//TODO 부모 프레임을 화면 가운데에 배치
		frame.setLocationRelativeTo(null);
		 
		//TODO 부모 프레임을 닫았을 때 메모리에서 제거되도록 설정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//TODO 부모 프레임 창 크기 고정 실시
		frame.setResizable(false);
		 
		//TODO 부모 레이아웃 설정
		frame.getContentPane().setLayout(null);
	}
	
	//TODO JButton 기본 스타일 지정 메소드
	public static void setJButtonStyle(JButton btn) {
		btn.setBackground(Color.BLACK); //TODO 백그라운드 색상 정의
		btn.setForeground(Color.WHITE); //TODO 텍스트 색상 정의
		btn.setFont(new Font("맑은 고딕", 0, 20)); //TODO 폰트 정의
		btn.setHorizontalAlignment(JLabel.CENTER); //TODO 텍스트 센터 표시 설정
	}		

}//클래스 종료