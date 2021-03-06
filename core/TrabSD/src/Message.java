public class Message {
    private int[] tags;
    private String content;
    
    public Message(int a, int b, int c, String msg){
        this.tags = new int[3];
    	this.tags[0] = a;
        this.tags[1] = b;
        this.tags[2] = c;
        this.content = msg;
    }
    
    public int[] getTags(){
        return this.tags;
    }
    
    public String getMensagem(){
        return this.content;
    }
    
    public void setTags(int[] t){
        this.tags = t;
    }
    
    public void setMensagem(String msg){
        this.content = msg;
    }
    
    public int saveInBD(){
    	return MySqlCon.executeInsert("insert into `db_middleware`.`message` (`tag_A`,`tag_B`,`tag_C`,`content`) values ("+tags[0]+","+tags[1]+","+tags[2]+",'"+content+"');");
    }
}
