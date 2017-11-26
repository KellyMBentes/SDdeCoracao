
public class Client {
    private int id;
    private int[] tags;
    private String endereco;
    
    
    public Client(int id, int a, int b, int c, String endereco){
        this.id = id;
        this.tags = new int[3];
        this.tags[0] = a;
        this.tags[1] = b;
        this.tags[2] = c;
        this.endereco = endereco;
    }
    
    public Client(int a, int b, int c, String endereco){
        this.id = 999; //caso seja inserido verificar id errada
        this.tags = new int[3];
        this.tags[0] = a;
        this.tags[1] = b;
        this.tags[2] = c;
        this.endereco = endereco;
    }
    
    public int getId(){
        return this.id;
    }
    
    public int[] getTags(){
        return this.tags;
    }
    
    public String getEndereco(){
        return this.endereco;
    }
    
    private void setId(int id){
        this.id = id;
    }
    
    public void setTags(int[] t){
        this.tags = t;
    }
    
    public void setEndereco(String end){
        this.endereco = end;
    }
    
    public void saveInBD() throws Exception{
    	int autoId = MySqlCon.executeInsert("INSERT INTO `DB_MIDDLEWARE`.`client` (tag_A,tag_B,tag_C,address) VALUES ("+tags[0]+","+tags[1]+","+tags[2]+",'"+endereco+"')");
    	
    	if(autoId < 0)
    		throw new Exception("Auto Key de ID foi retornado como "+autoId);
    	else
    		this.setId(autoId);
    }
    
    public void updateEnd(){
    	MySqlCon.excuteUpdate("UPDATE `DB_MIDDLEWARE`.`client` SET `ADDRESS` = "+endereco+" WHERE `ID` = "+id);
    }
    
    @Override
    public boolean equals(Object obj){
    	if (obj == null) {
            return false;
        }
    	
        if (!Client.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        
        final Client other = (Client) obj;
        
        if (this.getId() != other.getId()) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
    	return ("ID: "+this.id+", tipos : ["+this.tags[0]+", "+this.tags[1] + ", "+ this.tags[2] +"], End: "+this.endereco);
    }
}
