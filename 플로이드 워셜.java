/*
입력 예시
4
7
1 2 4
1 4 6
2 1 3
2 3 7
3 1 5
3 4 4
4 3 2
*/
public class FloydWarshallExam {
    public static void main(String args[]){
        int INF = (int) 1e9; //무한을 의미하는 값 10억(둘을 더하여도 21억 이하인 충분히 큰 숫자)
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //노드의 개수
        int m = sc.nextInt(); //간선의 개수
        
        int[][] graph = new int[n+1][m+1];// 2차원 최단 거리 테이블 만들기
        
        //최단 거리 테이블을 모두 무한으로 초기화, 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
        IntStream.rangeClosed(0,n).forEach(i->{
            Arrays.fill(graph[i],INF);
            graph[i][i] = 0;
        });
        
        //간선에 대한 정보를 입력 받아, 그 값으로 초기화
        IntStream.range(0, m).forEach(i->{
            //a에서 b로 가는 비용은 c
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph[a][b] = c;
        });
        
        //점화식에 따라 플로이드 워셜 알고리즘 수행
        for (int k=1; k<=n; k++)
            for(int a=1; a<=n; a++)
                for(int b=1; b<=n; b++)
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
        
        //수행된 결과를 출력
        for (int a=1; a<=n; a++){
            for(int b=1; b<=n; b++) {
                //도달할 수 없는 경우, 무한(INFINITY) 출력
                if (graph[a][b] == INF) System.out.print("INFINITY ");
                //도달할 수 있는 경우 거리 출력
                else System.out.print(graph[a][b] + " ");
            }
            System.out.println();
        }
    }
}