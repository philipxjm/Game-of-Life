package gameOfLife.core;

/**
 * Created by Philip Xu on 6/15/14.
 */
public class Cell {
    private boolean state;


    public Cell(){
        state = false;
    }


    public boolean getState() {
        return state;
    }

    public void setState(boolean state){
        this.state = state;
    }

    public void flipState(){
        this.state = !state;
    }


}
