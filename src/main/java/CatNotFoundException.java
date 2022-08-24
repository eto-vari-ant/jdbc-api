public class CatNotFoundException extends Exception{
    CatNotFoundException(){
        super("There is no such cat");
    }
}
