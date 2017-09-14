package studentAssociation;

import java.io.File;

public class stu_assTable extends tableFile{
	String[] masters = new String[10];
	String[] secreataries = new String[20];
	String[] members = new String[50];

	stu_assTable(){
		data = new String[maxStudentNum][];
		dataFileName = "stu_assTable.txt";
		dataFile = new File(dataFilePath,dataFileName);
	}
	
	//"201503164124", "1001"
	int getDataPos(String pk_stu,String pk_ass){
		int firstResultPos = getDataPos(pk_stu);
		if(firstResultPos == -1)
			return -1;
//		System.out.println("firstResultPos  "+firstResultPos);
		
		int recordNum = getRecordNum();
		String[][] data = getData();
		

		if(data[firstResultPos][1].equals(pk_ass)){
			return firstResultPos;
		}

		int upperRecordIndex =  firstResultPos -1;
		
		while( upperRecordIndex >= 0 &&  data[upperRecordIndex][0].equals(pk_stu)){
			if(data[upperRecordIndex][1].equals(pk_ass)){
				return upperRecordIndex;
			}
			upperRecordIndex--;
		}

		int lowerRecordIndex =  firstResultPos +1;
		while( lowerRecordIndex < recordNum && data[lowerRecordIndex][0].equals(pk_stu)){
			if(data[lowerRecordIndex][1].equals(pk_ass)){
				return lowerRecordIndex;
			}
			lowerRecordIndex++;
		}

		return -1;

	}	

	
	String[] getRecordByPk(String pk_stu,String pk_ass){
		int resultPos = this.getDataPos(pk_stu, pk_ass);
		return data[resultPos];
	}

	boolean deleteData(String pk_stu,String pk_ass) {
		int resultPos = this.getDataPos(pk_stu, pk_ass);
		if(resultPos >= 0) {
//			data[recordPos] = newRecord;
			for(int row = resultPos; row < recordNum-1; row++) {
				data[row] = data[row+1];
			}
			recordNum--;
//			System.out.println(pk_stu +" " + pk_ass + " delete Success");
			return true;
		}else {
//			System.out.println(pk_stu +" " + pk_ass + " delete failed");
			return false;
		}
		
	}

	String[] getMasters(String assId){
		int masterNum = 0;
		for(int i = 0; i < this.recordNum; i++){
			if( data[i][1].equals(assId) && data[i][2].equals("»á³¤")){
				masters[masterNum++] = data[i][0];
			}
		}
//		for(String str:masters){
//			System.out.println(str);
//		}
		return masters;
	}
	String[] getSecreataries(String assId){
		int secreataryNum = 0;
		for(int i = 0; i < this.recordNum; i++){
			if( data[i][1].equals(assId) && data[i][2].equals("ÃØÊé")){
				secreataries[secreataryNum++] = data[i][0];
			}
		}
		return secreataries;
	}

	String[] getMembers(String assId){
		int memberNum = 0;
		for(int i = 0; i < this.recordNum; i++){
			if( data[i][1].equals(assId)){
				members[memberNum++] = data[i][0];
			}
		}
//		for(String s:members){
//			System.out.println(s);
//		}
		return members;
	}

}
