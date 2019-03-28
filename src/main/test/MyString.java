import java.util.Objects;

public class MyString {
    int a;
    MyString(){}
    MyString(int a){
        this.a = a;
    }
    @Override
    public boolean equals(Object o) {
        if(!this.getClass().getClassLoader().equals(o.getClass().getClassLoader())){
            System.out.println("加载器不一样");
            return false;
        }
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyString myString = (MyString) o;
        return a == myString.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}
