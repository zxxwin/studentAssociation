package studentAssociation;

public class Controller {
	studentTable stuTab = new studentTable();
	associationTable assTab = new associationTable();
	stu_assTable stu_assTab = new stu_assTable();
	
	Controller(){
		stuTab.readData();
		assTab.readData();
		stu_assTab.readData();
	}
	void addAssociation(String record){	
		//���������
		
		assTab.addData(record);
		
		
//		System.out.println("���������:");
//		assTab.showData();
//		ass_indexTab.showData();
	}
	
	//�޸�������Ϣ	
	void setAssociationInfo(String record){
		//����ass_index
//		System.out.println("�޸�������Ϣ:");
		assTab.setData(record);
	}
	//ɾ������
	void deleteAssociation(String primaryKey){
		String assToDel = assTab.getNameByPk(primaryKey);
		String[][] stu_assData = stu_assTab.getData();
		int stu_assDataNum = stu_assTab.getRecordNum();
		for(int row = 0;row < stu_assDataNum; row++){
//			for(String str:stu_assData[row]){
//				System.out.print(str +" ");
//			}
//			System.out.println();
			if(stu_assData[row][1].equals(primaryKey)){
				stu_assTab.deleteData(stu_assData[row][0], primaryKey);
			}
		}
//		for(int row = 0;row < stu_assDataNum; row++){
//			System.out.println(stu_assData[row][1]);
////			if(stu_assData[row][1].equals(primaryKey)){
////				stu_assTab.deleteData(stu_assData[row][1], primaryKey);
////			}
//		}
		assTab.deleteData(primaryKey);
//		System.out.println("assTab:");
//		assTab.showData();
//		System.out.println("stu_assTab:");
//		stu_assTab.showData();
//		System.out.println("ass_indexTab:");
//		
	}
	
	
	//���һ��ѧ��
	void addStudent(String record){
		stuTab.addData(record);
//		System.out.println("���һ��ѧ��:");
//		stuTab.showData();
	}
	
	
	//�޸�ѧ����Ϣ
	void setStudentInfo(String record){
		stuTab.setData(record);
//		System.out.println("�޸�ѧ����Ϣ:");
//		stuTab.showData();
	}

	
	//ѧ����������
	/*
	 * ���stu_assTab ��¼
	 * ���ass_index ��¼
	 * "201503164998 1009 ��Ա "
	 * */
	void studentJoinAssociation(String record){

		stu_assTab.addData(record);
//		String[] recordArr = record.split(" ");
//		String stuId = recordArr[0];
//		String assID = recordArr[1];
//		String assName = assTab.getNameByPk(assID);
//		String[] ass_indexRecord = ass_indexTab.getRecordByPk(assName);
//		int recordLength = ass_indexRecord.length;
//		String[] newRecord = new String[recordLength+1];
//		
//		int index=0;
//		for(String value:ass_indexRecord){
//			newRecord[index++] = value;
//		}
//		newRecord[recordLength] = stuTab.getNameByPk(stuId);
//		
//		int resultPos = ass_indexTab.getDataPos(assName);
//		ass_indexTab.updataRecord(resultPos, newRecord);
		
//		System.out.println("ѧ����������:");
//		stu_assTab.showData();
//		ass_indexTab.showData();
	}
	
	

	//�޸�ѧ������������Ϣ
	//"201503764338 1004 ��Ա"
	void setStudentJob(String record){

		String[] recordArr = record.split(" ");
		String stuId = recordArr[0];
		String assId = recordArr[1];
		String studentJob = recordArr[2];
		
		int resultPos = stu_assTab.getDataPos(stuId, assId);		
		String[] targetRecord = stu_assTab.getRecordByPk(stuId, assId);
		targetRecord[2] = studentJob;
		stu_assTab.updataRecord(resultPos, targetRecord);
//		System.out.println("�޸�ѧ������������Ϣ");
//		stu_assTab.showData();
		
	}
	
	//ѧ���Ƴ�����
	void studentQuitAssociation(String stuId,String assId){
//		String stuNo = "201503164124", assNo = "1001";
//		ass_indexTab.readData();
//		String stuName = stuTab.getNameByPk(stuId);
//		String assName = assTab.getNameByPk(assId);
//		String[] resultRecord = ass_indexTab.getRecordByPk(assName);
//		String[] newRecord = new String[resultRecord.length-1];
//		System.out.println("here");
//		for(int i = 0,j=0; i < resultRecord.length; i++){
//			if(!resultRecord[i].equals(stuName)){
//				newRecord[j++] = resultRecord[i];
//			}
//		}
//		int ass_indexPos = ass_indexTab.getDataPos(assName);
//		ass_indexTab.updataRecord(ass_indexPos, newRecord);
		stu_assTab.deleteData(stuId, assId);
//		System.out.println("ѧ���Ƴ�����");
//		stu_assTab.showData();
//		ass_indexTab.showData();
	}
	
	//ɾ��ѧ��
	/*
	 * ����ϵ��stu_assTab��òμӵ��������֣�
	 * �����������ֻ��ass_indexTab��ѧ���������ŵļ�¼��
	 * ��ɾ��ass_indexTab��¼�е�ѧ������
	 * ��ɾ��stu_assTab��ѧ���μӵ����ż�¼
	 * ���ɾ��stuTab�еļ�¼
	 * 
	 * */
//	String pk_stu = "201503164124", pk_ass = "1001";
//	String[] resultRecord = stu_assTab.getRecordByPk(pk_stu, pk_ass);
	void deleteStudent(String stuId){
		
		int resultPos = stu_assTab.getDataPos(stuId);
//		System.out.println(resultPos);
		
		//��������ɾ��ѧ������
		while(resultPos != -1){
			String[] recordArr = stu_assTab.getRecordByPpos(resultPos);
			String assId = recordArr[1];
			this.studentQuitAssociation(stuId, assId);
			resultPos = stu_assTab.getDataPos(stuId);
		}
		stuTab.deleteData(stuId);
		
//		System.out.println("ɾ��ѧ��:");
//		stuTab.showData();
//		stu_assTab.showData();
//		System.out.println();
		
	}
	//��ѯ����
	
	//��ѯѧ��
	String[][] getStuAss(String keyWord){
		stu_assTab.readData();
		int recordArrIndex = 0;
		String[][] recordArr = new String[5][2];
		int firstResultPos = stu_assTab.getDataPos(keyWord);
		if(firstResultPos == -1){
			return recordArr;
		}
		int recordNum = stu_assTab.getRecordNum();
		String[][] data = stu_assTab.getData();
		if(data[firstResultPos][0].equals(keyWord)){
			recordArr[recordArrIndex][0] = data[firstResultPos][1];
			recordArr[recordArrIndex][1] = data[firstResultPos][2];
			recordArrIndex++;
		}

		int upperRecordIndex =  firstResultPos -1;
		
		while( upperRecordIndex >= 0 &&  data[upperRecordIndex][0].equals(keyWord)){
			recordArr[recordArrIndex][0] = data[upperRecordIndex][1];
			recordArr[recordArrIndex][1] = data[upperRecordIndex][2];
			recordArrIndex++;
			upperRecordIndex--;
		}

		int lowerRecordIndex =  firstResultPos +1;
		while( lowerRecordIndex < recordNum && data[lowerRecordIndex][0].equals(keyWord)){
			recordArr[recordArrIndex][0] = data[lowerRecordIndex][1];
			recordArr[recordArrIndex][1] = data[lowerRecordIndex][2];
			recordArrIndex++;
			lowerRecordIndex++;
		}
		return recordArr;
	}
	void saveData(){
		stuTab.writeData();
		stu_assTab.writeData();
		assTab.writeData();
	}
	
	
	
	
	public static void main(String[] args) {
		
		Controller controller = new Controller();
		//���������Ϣ
//		controller.addAssociation("1001 �캽������ 2011-12-3 ");
//		controller.addAssociation("1002 ������ֲ� 2011-12-3 ");
//		controller.addAssociation("1003 java���ֲ� 2011-12-3");
//		controller.addAssociation("1004 ������ 2011-12-3");
//		controller.addAssociation("1005 acm���ֲ� 2011-12-3 ");
//		controller.addAssociation("1006 ѧ���� 2011-12-3 ");
//		controller.addAssociation("1008 3DЭ�� 2011-12-16 ");
		

		//�޸�������Ϣ
//		controller.setAssociationInfo("1006 ѧ���� 2001-12-3 ");
		
		//ɾ������
//		controller.deleteAssociation("1006");

		//���ѧ����Ϣ
//		controller.addStudent("201503161222 �������� �� ��ľ���� ");
//		controller.addStudent("201503164124 �������� Ů ��������  ");
//		controller.addStudent("201503164312 ������ �� ���  ");
//		controller.addStudent("201503164322 ��� �� ����  ");
//		controller.addStudent("201503164338 ֣���� �� ���繤�� ");
//		controller.addStudent("201503164352 ���� �� �������ѧ�뼼��");
//		controller.addStudent("201503164362 ������ �� �������ѧ�뼼��  ");
//		controller.addStudent("201503164998 С��è Ů �ٴ�ҽѧ ");
//		controller.addStudent("201503764338 ������ Ů ��е�Զ���  ");

		//�޸�ѧ����Ϣ
//		controller.setStudentInfo("201503764338 ������ �� ��е�Զ���  ");
		
		//ɾ��ѧ��
//		controller.deleteStudent("201503764338");
		
		//ѧ����������
//		controller.studentJoinAssociation("201503164124 1003 ���� ");
//		controller.studentJoinAssociation("201503164352 1001 ���� ");
//		controller.studentJoinAssociation("201503164362 1002 ���� ");
//		controller.studentJoinAssociation("201503764338 1004 ���� ");
		
		//�޸�ѧ���������е���Ϣ
//		controller.setStudentJob("201503764338 1004 �᳤");
		
		//ѧ���˳�����
//		controller.studentQuitAssociation("201503164338", "1001");
		
		//��ȡ���Ż᳤
//		controller.stu_assTab.getMasters("1001");
		
		//��ȡ��������
//		controller.stu_assTab.getSecreataries("1001");
		

		//��ȡ���ų�Ա
//		controller.stu_assTab.getMembers("1001");

		//��ȡѧ��������Ϣ
//		String[][] s = controller.getStuAss("201503164338");
//		for(String[] str:s){
//			for(String ss:str)
//			System.out.print(ss + " ");
//		System.out.println();
//		}
		
		//�����޸�
//		controller.saveData();
		
		
	}

}


