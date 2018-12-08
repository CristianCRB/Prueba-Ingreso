import java.util.Scanner;

public class Non_Divisible {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        int cont = 0;
        int Array[] = new int[n];
        int k = sc.nextInt(); 
        for (int i = 0; i < n; i++) {
            Array[i] = sc.nextInt();
            
        }
        int R[]= new int[k];
        for(int i=0; i<n; i++){
            R[Array[i]%k]=R[Array[i]%k]+1;
            int residuo=R[Array[i]%k];
            System.out.print(residuo+" ");
            
        }
        for(int j=1; j<=k/2; j++){
            if(j == k-j){
                cont++;
            }
            
            else if(R[j]>=R[k-j]){
                cont = cont + R[j];
            }
            
            else{
                cont = cont + R[k-j];
            }
        }
        if(R[0]!=0){
            cont++;
            
        }
	System.out.println(cont);
    }
}