package studentAssociation;

public class main {
	studentTable stuTab = new studentTable();
	associationTable assTab = new associationTable();
	stu_assTable stu_assTab = new stu_assTable();
	ass_indexTable ass_indexTab = new ass_indexTable();
	
	main(){
		stuTab.readData();
		assTab.readData();
		stu_assTab.readData();
		ass_indexTab.readData();
	}
	void addAssociation(String record){	
		//���������
		
		assTab.addData(record);
		
		String[] recordArr = record.split(" ");
		ass_indexTab.addData(recordArr[1]);
		
		System.out.println("���������:");
		assTab.showData();
		ass_indexTab.showData();
	}
	
	//�޸�������Ϣ	
	void setAssociationInfo(String record){
		assTab.setData(record);
		System.out.println("�޸�������Ϣ:");
		assTab.showData();		
	}
	//ɾ������
	void deleteAssociation(String primaryKey){
		String assToDel = assTab.getNameByPk(primaryKey);
		ass_indexTab.deleteData(assToDel);
		assTab.deleteData(primaryKey);
		System.out.println("ɾ������:");
		assTab.showData();
		ass_indexTab.showData();
		
	}
	
	
	//���һ��ѧ��
	void addStudent(String record){
		stuTab.addData(record);
		System.out.println("���һ��ѧ��:");
		stuTab.showData();
	}
	
	
	//�޸�ѧ����Ϣ
	void setStudentInfo(String record){
		stuTab.setData(record);
		System.out.println("�޸�ѧ����Ϣ:");
		stuTab.showData();
	}

	
	//ѧ����������
	/*
	 * ���stu_assTab ��¼
	 * ���ass_index ��¼
	 * "201503164998 1009 ��Ա "
	 * */
	void studentJoinAssociation(String record){

		stu_assTab.addData(record);
		String[] recordArr = record.split(" ");
		String stuId = recordArr[0];
		String assID = recordArr[1];
		String assName = assTab.getNameByPk(assID);
		String[] ass_indexRecord = ass_indexTab.getRecordByPk(assName);
		int recordLength = ass_indexRecord.length;
		String[] newRecord = new String[recordLength+1];
		
		int index=0;
		for(String value:ass_indexRecord){
			newRecord[index++] = value;
		}
		newRecord[recordLength] = stuTab.getNameByPk(stuId);
		
		int resultPos = ass_indexTab.getDataPos(assName);
		ass_indexTab.updataRecord(resultPos, newRecord);
		
		System.out.println("ѧ����������:");
		stu_assTab.showData();
		ass_indexTab.showData();
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
		System.out.println("�޸�ѧ������������Ϣ");
		stu_assTab.updataRecord(resultPos, targetRecord);
		stu_assTab.showData();
		
	}
	
	//ѧ���Ƴ�����
	void studentQuitAssociation(String stuId,String assId){
//		String stuNo = "201503164124", assNo = "1001";
		String stuName = stuTab.getNameByPk(stuId);
		String assName = assTab.getNameByPk(assId);
		String[] resultRecord = ass_indexTab.getRecordByPk(assName);
		String[] newRecord = new String[resultRecord.length-1];
		for(int i = 0,j=0; i < resultRecord.length; i++){
			if(!resultRecord[i].equals(stuName)){
				newRecord[j++] = resultRecord[i];
			}
		}
		int ass_indexPos = ass_indexTab.getDataPos(assName);
		ass_indexTab.updataRecord(ass_indexPos, newRecord);
		stu_assTab.deleteData(stuId, assId);
		System.out.println("ѧ���Ƴ�����");
		stu_assTab.showData();
		ass_indexTab.showData();
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
			System.out.println();
			System.out.println();
			resultPos = stu_assTab.getDataPos(stuId);
		}
		stuTab.deleteData(stuId);
		
		System.out.println("ɾ��ѧ��:");
		stuTab.showData();
		stu_assTab.showData();
		System.out.println();
		
	}
	//��ѯ����
	
	//��ѯѧ��
	String[][] getStuAss(String keyWord){
		int recordArrIndex = 0;
		String[][] recordArr = new String[5][2];
		int firstResultPos = stu_assTab.getDataPos(keyWord);
		
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
		while( lowerRecordIndex <= recordNum && data[lowerRecordIndex][0].equals(keyWord)){
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
		ass_indexTab.writeData();
		assTab.writeData();
	}
	
	
	
	
	public static void main(String[] args) {
		
		main controller = new main();
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
//		controller.studentQuitAssociation("201503764338", "1004");
		
		//��ȡ���Ż᳤
//		controller.stu_assTab.getSecreataries("1001");
		
		//��ȡ��������
//		controller.stu_assTab.getMasters("1001");
		

		//��ȡѧ��������Ϣ
//		String[][] s = controller.getStuAss("201503164338");
//		for(String[] str:s){
//			for(String ss:str)
//			System.out.println(ss);
//		}
		
		//�����޸�
//		controller.saveData();
		
		
	}

}




//controller.addAssociation("1009 ��ӰЭ�� 2011-12-16");
//controller.studentQuitAssociation("201503164124", "1001");
//controller.stu_assTab.deleteData("201503164124", "1001");
//controller.deleteStudent("201503164124");
//controller.stu_assTab.getSecreataries("1001");
//controller.stu_assTab.getMasters("1001");
//controller.stuTab.searchData("֣����");
//controller.assTab.searchData("1001");
//String[][] s = controller.getStuAss("201503164338");
//for(String[] str:s){
//	for(String ss:str)
//	System.out.println(ss);
//}







//stuTab.writeData();
//stu_assTab.writeData();
//ass_indexTab.writeData();
//assTab.writeData();



////stuTab.writeData();
//stuTab.readData();
//stuTab.showData();
//
//stuTab.setData("201503164338 abd f 566 ac ");
////stuTab.addData("201503164256 ccc f 1aaa432 dddds ");
////stuTab.deleteData("201503164256");
//System.out.println();
//stuTab.showData();
//
//System.out.println("sc:");
//stuTab.searchData("xxm");

//stuTab.writeData();




//assTab.setData("1001 ����1 2011-12-3");
//System.out.println("sc:");
//
//assTab.searchData("1001");
//
//assTab.writeData();


//stu_assTab.readData();
//
////stu_assTab.addData("201503164334 21 master");
////stu_assTab.addData("201503164351 23 secreatary");
////stu_assTab.addData("201503164364 26 secreatary");
//stu_assTab.showData();
//stu_assTab.writeData();




//
//ass_index.readData();
//ass_index.showData();
