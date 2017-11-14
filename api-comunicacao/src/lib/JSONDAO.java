package lib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONDAO {
	private String srcFile;

	public JSONDAO(String f){
		this.srcFile = f;
	}

	public JSONObject read(){
		// Referência: http://www.devmedia.com.br/leitura-e-escrita-de-arquivos-json-em-java/27663
		JSONObject result = new JSONObject();

		//Cria o parse de tratamento
		JSONParser parser = new JSONParser();
		//Variaveis que irao armazenar os dados do arquivo JSON

		try {
			//Salva no objeto JSONObject o que o parse tratou do arquivo
			result = (JSONObject) parser.parse(new FileReader(this.srcFile));
		} 
		//Trata as exceptions que podem ser lançadas no decorrer do processo
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public void save(JSONObject val){
		// Referência: http://www.devmedia.com.br/leitura-e-escrita-de-arquivos-json-em-java/27663
		FileWriter writeFile = null;
		try{
			writeFile = new FileWriter(this.srcFile);
			//Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(val.toString());
			writeFile.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}