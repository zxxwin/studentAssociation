package studentAssociation;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class studentTable extends tableFile{
	studentTable(){
		data = new String[maxStudentNum][];
		dataFileName = "studentTable.txt";
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