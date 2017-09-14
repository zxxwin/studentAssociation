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
		//添加新社团
		
		assTab.addData(record);
		
		
//		System.out.println("添加新社团:");
//		assTab.showData();
//		ass_indexTab.showData();
	}
	
	//修改社团信息	
	void setAssociationInfo(String record){
		//更新ass_index
//		System.out.println("修改社团信息:");
		assTab.setData(record);
	}
	//删除社团
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
	
	
	//添加一个学生
	void addStudent(String record){
		stuTab.addData(record);
//		System.out.println("添加一个学生:");
//		stuTab.showData();
	}
	
	
	//修改学生信息
	void setStudentInfo(String record){
		stuTab.setData(record);
//		System.out.println("修改学生信息:");
//		stuTab.showData();
	}

	
	//学生加入社团
	/*
	 * 添加stu_assTab 记录
	 * 添加ass_index 记录
	 * "201503164998 1009 会员 "
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
		
//		System.out.println("学生加入社团:");
//		stu_assTab.showData();
//		ass_indexTab.showData();
	}
	
	

	//修改学生所在社团信息
	//"201503764338 1004 会员"
	void setStudentJob(String record){

		String[] recordArr = record.split(" ");
		String stuId = recordArr[0];
		String assId = recordArr[1];
		String studentJob = recordArr[2];
		
		int resultPos = stu_assTab.getDataPos(stuId, assId);		
		String[] targetRecord = stu_assTab.getRecordByPk(stuId, assId);
		targetRecord[2] = studentJob;
		stu_assTab.updataRecord(resultPos, targetRecord);
//		System.out.println("修改学生所在社团信息");
//		stu_assTab.showData();
		
	}
	
	//学生推出社团
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
//		System.out.println("学生推出社团");
//		stu_assTab.showData();
//		ass_indexTab.showData();
	}
	
	//删除学生
	/*
	 * 从联系表stu_assTab获得参加的社团名字，
	 * 根据社团名字获得ass_indexTab中学生所在社团的记录，
	 * 先删除ass_indexTab记录中的学生名字
	 * 再删除stu_assTab的学生参加的社团记录
	 * 最后删除stuTab中的记录
	 * 
	 * */
//	String pk_stu = "201503164124", pk_ass = "1001";
//	String[] resultRecord = stu_assTab.getRecordByPk(pk_stu, pk_ass);
	void deleteStudent(String stuId){
		
		int resultPos = stu_assTab.getDataPos(stuId);
//		System.out.println(resultPos);
		
		//从社团中删除学生名单
		while(resultPos != -1){
			String[] recordArr = stu_assTab.getRecordByPpos(resultPos);
			String assId = recordArr[1];
			this.studentQuitAssociation(stuId, assId);
			resultPos = stu_assTab.getDataPos(stuId);
		}
		stuTab.deleteData(stuId);
		
//		System.out.println("删除学生:");
//		stuTab.showData();
//		stu_assTab.showData();
//		System.out.println();
		
	}
	//查询社团
	
	//查询学生
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
		//添加社团信息
//		controller.addAssociation("1001 领航工作室 2011-12-3 ");
//		controller.addAssociation("1002 足球俱乐部 2011-12-3 ");
//		controller.addAssociation("1003 java俱乐部 2011-12-3");
//		controller.addAssociation("1004 骑行社 2011-12-3");
//		controller.addAssociation("1005 acm俱乐部 2011-12-3 ");
//		controller.addAssociation("1006 学生会 2011-12-3 ");
//		controller.addAssociation("1008 3D协会 2011-12-16 ");
		

		//修改社团信息
//		controller.setAssociationInfo("1006 学生会 2001-12-3 ");
		
		//删除社团
//		controller.deleteAssociation("1006");

		//添加学生信息
//		controller.addStudent("201503161222 隔壁老王 男 土木工程 ");
//		controller.addStudent("201503164124 隔壁老吴 女 汽车工程  ");
//		controller.addStudent("201503164312 李孙张 男 会计  ");
//		controller.addStudent("201503164322 孙李长 男 护理  ");
//		controller.addStudent("201503164338 郑心享 男 网络工程 ");
//		controller.addStudent("201503164352 大牙 男 计算机科学与技术");
//		controller.addStudent("201503164362 大妹子 男 计算机科学与技术  ");
//		controller.addStudent("201503164998 小熊猫 女 临床医学 ");
//		controller.addStudent("201503764338 徐显明 女 机械自动化  ");

		//修改学生信息
//		controller.setStudentInfo("201503764338 徐显明 男 机械自动化  ");
		
		//删除学生
//		controller.deleteStudent("201503764338");
		
		//学生加入社团
//		controller.studentJoinAssociation("201503164124 1003 秘书 ");
//		controller.studentJoinAssociation("201503164352 1001 秘书 ");
//		controller.studentJoinAssociation("201503164362 1002 秘书 ");
//		controller.studentJoinAssociation("201503764338 1004 秘书 ");
		
		//修改学生在社团中的信息
//		controller.setStudentJob("201503764338 1004 会长");
		
		//学生退出社团
//		controller.studentQuitAssociation("201503164338", "1001");
		
		//获取社团会长
//		controller.stu_assTab.getMasters("1001");
		
		//获取社团秘书
//		controller.stu_assTab.getSecreataries("1001");
		

		//获取社团成员
//		controller.stu_assTab.getMembers("1001");

		//获取学生社团信息
//		String[][] s = controller.getStuAss("201503164338");
//		for(String[] str:s){
//			for(String ss:str)
//			System.out.print(ss + " ");
//		System.out.println();
//		}
		
		//保存修改
//		controller.saveData();
		
		
	}

}


