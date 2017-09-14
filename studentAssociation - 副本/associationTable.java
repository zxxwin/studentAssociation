package studentAssociation;

import java.io.File;

public class associationTable extends tableFile{
	
	associationTable(){
		data = new String[maxStudentNum][];
		dataFileName = "associationTable.txt";
		dataFile = new File(dataFilePath,dataFileName);
	}
	String getNameByPk(String primaryKeyValue){
		int resultPos = this.getDataPos(primaryKeyValue);
		if(resultPos >= 0){
			String[] resultData = data[resultPos];
			return resultData[1];
		}else{
			System.out.println("get name failed");
			return null;
		}
	}
	
}
