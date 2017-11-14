package lib;

import java.io.File;
import java.lang.Exception;

public class RelativePath {

	public static String ROOT = null;
	public static String getPath(String f) throws Exception{
		if(ROOT == null){ throw new Exception("Defina o parâmetro ROOT"); }
		String result = "";
		String absoluteRoot = getAbsolutePath(ROOT);
		String absoluteF = getAbsolutePath(f);

		result = getDiffPath(absoluteF, absoluteRoot);

		return result;
	}

	private static String getAbsolutePath(String f){
		try{
			return (new File(f)).getCanonicalPath();
		}
		catch(Exception e) {
			return (new File(f)).getAbsolutePath();
		}
	}

	private static String getDiffPath(String chg, String org){
		String result = "";
		
		boolean isFileChg = new File(chg).exists() && !(new File(chg).isDirectory());
		boolean isFileOrg = new File(org).exists() && !(new File(org).isDirectory());

		// Portabilizar  o separador (Windows, Linux etc)
		String[] dirsChg = chg.split("/");
		String[] dirsOrg = org.split("/");

		int minimumSize = (dirsChg.length < dirsOrg.length ? 
			dirsChg.length : dirsOrg.length);

		int i;
		for (i = 0; i < minimumSize; i++){
			if(!dirsChg[i].trim().equals(dirsOrg[i])){
				result += dirsChg[i] + "/";
			}
		}

		// Completando
		for(;i < dirsChg.length; i++){
			result += dirsChg[i] + "/";
		}

		if (isFileChg) {
			result = result.substring(0, result.length()-1);
		}

		return result;
	}
}