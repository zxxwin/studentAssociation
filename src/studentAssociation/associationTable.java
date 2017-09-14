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
	String getPkByName(String name){
		for(int row = 0; row < recordNum; row++){
			if(data[row][1].equals(name)){
				return data[row][0];
			}
		}
		return null;
	}
	boolean isNameExist(String name){
		for(int row = 0; row < recordNum; row++){
			if(data[row][1].equals(name)){
				return true;
			}
		}
		return false;
	}

}
