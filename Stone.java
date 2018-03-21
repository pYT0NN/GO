public class Stone {

boolean white;
int freiheit = 0;
int con = 0;
boolean group = false;

public Stone(boolean white){
								this.white = white;
}

public boolean isWhite(){
								return this.white;
}

public int getFreiheit(){
								return this.freiheit;
}
public void addFreiheit(int x){
								this.freiheit += x;
}
public void setFreiheit(int x){
								this.freiheit = x;
}

public int getCon(){
								return this.con;
}

public void addCon(int x){
								this.con += x;
}
public void mark(){
								this.group = true;
}
public void unmark(){
								this.group = false;
}
}
