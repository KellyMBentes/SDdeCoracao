public class Message {
    private int id;
    private boolean[] tags;
    private String content;
    
    public Message(boolean a, boolean b, boolean c, String msg){
        this.id = 999;
        this.tags[0] = a;
        this.tags[1] = b;
        this.tags[2] = c;
        this.content = msg;
    }
    
    public int getId(){
        return this.id;
    }
    
    public boolean[] getTags(){
        return this.tags;
    }
    
    public String getMensagem(){
        return this.content;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setTags(boolean[] t){
        this.tags = t;
    }
    
    public void setMensagem(String msg){
        this.content = msg;
    }
    
    public void saveInBD(){
    	// TODO modificar para retornar id gerada
    	MySqlCon.excuteUpdate("INSERT INTO DB_MIDDLEWARE.message VALUES ("+id+","+tags[0]+","+tags[1]+","+tags[2]+","+content+")");
    }
}
