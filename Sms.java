package xuxin1029;

import java.util.Scanner;
/**
	ѧ����Ϣ����ϵͳ
	����ѧ����Ϣ�ģ���ɾ�Ĳ飩
	����ѧ����Ϣ���浽���	ѧ������
	���е�ѧ����Ϣ���浽���	������
*/
public class Sms{
	private Bike[] biks = new Bike[3];//���ڱ���ѧ������
	private int index;	//��¼������ʵ��ѧ���ĸ���

	/**
	 * ���ѧ����Ϣ
	   save 
	     stus[0] = stu;  index = 1
		 stus[1] = stu;  index = 2
		 stus[2] = stu;	 index = 3
		 stus[3]
	 */
	public void save(Bike bike){
		if(index >= biks.length){
			//�������չ
			Bike[] demo = new Bike[biks.length+3];
			System.arraycopy(biks,0,demo,0,index);
			biks = demo;
		}
		biks[index++] = bike;
	}

    /**
	 *�޸�ѧ����Ϣ
	 stus = {
		1001 terry,
		1003 tom3,
		1004 tom4,
		1005 tom5,
		1006 tom6,
		null
	 }
		1006 terry
	 */
	public void update(Bike bike){
		for(int i=0;i<index;i++){
			if(bike.getNo() == biks[i].getNo()){
				biks[i].setName(bike.getName());
			}
		}
	}

	/**
	 *ɾ��ѧ����Ϣ
	 stus = new Student[6];
	 stus = {
		1001 terry,
		1003 tom3,
		1004 tom4,
		1005 tom5,
		1006 tom6,
		null
	 }
	 index = 6;
	 1002    num = 1;
	 for(int i=1;i<5;i++){
		stus[i] = stus[i+1]
		//stus[4] = stus[5]

	 }
	 */
    public void deleteByNo(long no){
		int num = getIndexByNo(no);
		for(int i=num ;i<index-1;i++){
			biks[i] = biks[i+1];
		}
		biks[--index] = null;
	}

	/**
	 *��ѯ����ѧ����Ϣ
	 */
	public Bike[] queryAll(){
		Bike[] demo = new Bike[index];
		System.arraycopy(biks,0,demo,0,index);
		return demo;
	}

	/**
	 *ͨ��id����ѧ����Ϣ

	 */
	public Bike queryByNo(long no){
		int num = getIndexByNo(no);
		return num==-1?null:biks[num];
	}

	/**
	 ����ѧ�������id��ȡ��ѧ�������������е�����
	 stus = new Student[3];
	 1001 terry
	 1002 larry
	 null 

	 1003
	*/
	private int getIndexByNo(long no){
		int num = -1;
		for(int i=0;i<index;i++){
			if(biks[i].getNo() == no){
				num = i;
				break;
			}
		}
		return num;
	}

	/**
	 * �˵�
  	 */
	public void menu(){
		System.out.println("********���г���Ϣ����ϵͳ********");
		System.out.println("*1 ��ѯ�������г���Ϣ");
		System.out.println("*2 ¼�����г���Ϣ");
		System.out.println("*3 ɾ�����г���Ϣ");
		System.out.println("*4 ͨ����Ų������г���Ϣ");
		System.out.println("*5 �޸����г���Ϣ");
		System.out.println("*exit �˳�ϵͳ��");
		System.out.println("*help ��ȡ����");
		System.out.println("********************************");
	}

	public static void main(String[] args){
		Sms sms = new Sms();
		sms.menu();
		//ɨ��������
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("*�������Ӧָ��:");
			String option = sc.nextLine();
			switch(option){
				case "1":	//��ѯ����
					System.out.println("�������������г�����Ϣ��");
					Bike[] biks = sms.queryAll();
					for(Bike bik : biks){
						System.out.println(bik);
					}
					System.out.println("�ܹ���ѯ��"+sms.index+"�����г�");
					break;
				case "2":	//¼��
					while(true){
						System.out.println("������γ̵���Ϣ��no#name#price�������롾break��������һ��Ŀ¼");
						String bikStr = sc.nextLine();
						if(bikStr.equals("break")){
							break;//���ص���һ��Ŀ¼
						}
						//stuStr 1001#terry#12�ַ��� ->����->����
						String[] bikArr = bikStr.split("#");
						//�������и�Ԫ��ת��Ϊѧ����������Ҫ����������
						long no  = Long.parseLong(bikArr[0]);
						String name = bikArr[1];
						int price = Integer.parseInt(bikArr[2]);
						//��װ����
						Bike bik = new Bike(no,name,price);
						sms.save(bik);
						System.out.println("����ɹ���");
					}
					break;
				case "3":	//ɾ��
					while(true){
						System.out.println("������Ҫɾ�����г��ı��,��������break������һ��Ŀ¼");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						//��ѯҪɾ����ѧ����Ϣ�Ƿ����
						Bike bik = sms.queryByNo(no);
						if(bik==null){
							System.out.println("��Ҫɾ�����û���Ϣ�����ڣ�");
							continue;
						}
						sms.deleteByNo(no);
						System.out.println("ɾ���ɹ���");
					}	
					break;
				case "4":	//ͨ��id��ȡ
					while(true){
						System.out.println("������Ҫ����ѧ����id,��������break������һ��Ŀ¼");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						Bike bik = sms.queryByNo(no);
						System.out.println(bik==null?"sorry,not found!":bik);
					}	
					break;
				case "5":	//�޸�
					while(true){
						System.out.println("������Ҫ�޸Ŀγ̵ı��,��������break������һ��Ŀ¼");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						//��ѯҪɾ����ѧ����Ϣ�Ƿ����
						Bike bik = sms.queryByNo(no);
						if(bik==null){
							System.out.println("��Ҫ�޸ĵ��û���Ϣ�����ڣ�");
							continue;
						}
						System.out.println("ԭ��ϢΪ��"+bik);
						System.out.println("����������Ϣ��name#price����");
						// tom#12
						String str = sc.nextLine();
						String[] bikArr = str.split("#");
						String name = bikArr[0];
						int price = Integer.parseInt(bikArr[1]);
						Bike newBik = new Bike(no,name,price);
						sms.update(newBik);
						System.out.println("�޸ĳɹ���");
					}	
					break;
				case "exit":
					System.out.println("bye bye,��ӭ�ٴ�ʹ�ã�");
					System.exit(0);
				case "help":
					sms.menu();
					break;
				default:
					System.out.println("�������");

			}
		}
	}
}