public class Message {
    private boolean[] tags;
    private String content;
    
    public Message(boolean a, boolean b, boolean c, String msg){
        this.tags[0] = a;
        this.tags[1] = b;
        this.tags[2] = c;
        this.content = msg;
    }
    
    public boolean[] getTags(){
        return this.tags;
    }
    
    public String getMensagem(){
        return this.content;
    }
    
    public void setTags(boolean[] t){
        this.tags = t;
    }
    
    public void setMensagem(String msg){
        this.content = msg;
    }
    
    public int saveInBD(){
    	return MySqlCon.executeInsert("INSERT INTO DB_MIDDLEWARE.message (a,b,c,content) VALUES ("+tags[0]+","+tags[1]+","+tags[2]+","+content+")");
    }
}
