
public class Client {
    private int id;
    private boolean[] tags;
    private String endereco;
    
    public Client(int id, boolean[] t, String endereco){
        this.id = id;
        this.tags = t;
        this.endereco = endereco;
    }
    
    public Client(int id, boolean a, boolean b, boolean c, String endereco){
        this.id = id;
        this.tags[0] = a;
        this.tags[1] = b;
        this.tags[2] = c;
        this.endereco = endereco;
    }
    
    public int getId(){
        return this.id;
    }
    
    public boolean[] getTags(){
        return this.tags;
    }
    
    public String getEndereco(){
        return this.endereco;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setTags(boolean[] t){
        this.tags = t;
    }
    
    public void setEndereco(String end){
        this.endereco = end;
    }
    
    public void saveInBD(){
    	MySqlCon.excuteUpdate("INSERT INTO DB_MIDDLEWARE.client VALUES ("+id+","+tags[0]+","+tags[1]+","+tags[2]+","+endereco+")");
    }
    
    public void updateEnd(){
    	MySqlCon.excuteUpdate("UPDATE DB_MIDDLEWARE.client SET ADDRESS = "+endereco+" WHERE ID = "+id);
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
