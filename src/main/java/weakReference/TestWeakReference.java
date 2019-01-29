package weakReference;

import java.lang.ref.WeakReference;

public class TestWeakReference {
	public static void main(String[] args) {
		Car car = new Car(22200,"sliver");
		WeakReference<Car> wr = new WeakReference<Car>(car);
		int i=0;
		while(true) {
			if(wr.get()!=null) {
				i++;  
                System.out.println("Object is alive for "+i+" loops - "+wr); 
			}else {
				System.out.println(i);
				System.out.println("Object has been collected.");  
                break;  
			}
		}
	}
}
