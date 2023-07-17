/*
1. 각 노드에 대한 최단 거리를 담는 1차원 리스트 선언
2. 단계마다 방문하지 않은 노드 중에서 최단 거리가 가장 짧은 노드를 선택하기 위해, 1차원 리스트의 모든 원소를 확인(순차 탐색)
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
입력값
6 11
1
1 2 2
1 3 5
1 4 1
2 3 3
2 4 2
3 2 3
3 6 5
4 3 3
4 5 1
5 3 1
5 6 2
*/

class Node {
    private int index;
    private int distance;
    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
    public int getIndex() {
        return this.index;
    }
    public int getDistance() {
        return this.distance;
    }
}

public class DijkstraExam {
    private static int n;
    static int start;
    static ArrayList<ArrayList<Node>> graph;
        static boolean[] visited;
        static int[] distance;
        public static void main(String[] arsgs){
            Scanner scanner = new Scanner(System.in);
            //노드의 개수, 간선의 개수 입력
            n = scanner.nextInt();
            int m = scanner.nextInt();
            //시작 노드 번호 입력
            start = scanner.nextInt();
            //각 노드에 연결되어 있는 노드에 대한 정보를 담음
            graph = new ArrayList<>();
            IntStream.range(0,n+1).forEach(i->graph.add(new ArrayList<Node>()));
                
                //방문 여부를 체크하는 배열
                visited = new boolean[n+1];
                //IntStream.range(0, visited.length).forEach(i->visited[i]=false);
                
                //최단 거리 테이블을 모두 무한으로 초기화
                distance = new int[n+1];
                IntStream.range(0, distance.length).forEach(i->distance[i]=Integer.MAX_VALUE);
                
                //모든 간선 정보를 입력
                IntStream.range(0,m).forEach(i->{
                    //a번 노드에서 b번 노드로 가는 비용이 c임
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    int c = scanner.nextInt();
                    graph.get(a).add(new Node(b,c));
                });
                
                //다익스트라 알고리즘 수행
                dijkstra();
                
                //모든 노드로 가기 위한 최단 거리 출력
                IntStream.rangeClosed(1, n).forEach(i->{
                    if(distance[i] == Integer.MAX_VALUE){ //도달할 수 없는 경우 무한(INFINITY) 출력
                        System.out.println("INFINITY");
                    }else{
                        System.out.println(distance[i]); //도달할 수 있는 경우 거리를 출력
                    }
                });
                }
                
                // 방문하지 않은 노드 중 가장 최단 거리가 짧은 노드 번호 반환
                public static int getSmallestNode(){
                    int minValue = Integer.MAX_VALUE;
                    int index = 0; //가장 최단 거리가 짧은 노드(인덱스)
                    for(int i=0; i <= n; i++){
                        if(distance[i]<minValue && !visited[i]){
                            minValue = distance[i];
                            index = i;
                        }
                        }
                        return index;
                        }
                        
                        // 다익스트라 알고리즘
                        public static void dijkstra(){
                            //시작 노드에 대해서 초기화
                            distance[start] = 0;
                            visited[start] = true;
                            IntStream.range(0, graph.get(start).size()).forEach(i->
                                distance[graph.get(start).get(i).getIndex()] = graph.get(start).get(i).getDistance());
                            
                            // 시작 노드를 제외한 전체 n-1개의 노드에 대해 반복
                            for(int i=0;i<n-1; i++){
                                // 현재 최단 거리가 가장 짧은 노드를 꺼내서, 방문 처리
                                int now = getSmallestNode();
                                visited[now] = true;
                                // 현재 노드와 연결된 다른 노드 확인
                                for(int j =0; j < graph.get(now).size(); j++){
                                    int cost = distance[now] + graph.get(now).get(j).getDistance();
                                    // 현재 노드를 거쳐서 다른 노드를 이동하는 거리가 더 짧은 경우
                                    if(cost < distance[graph.get(now).get(j).getIndex()])
                                        distance[graph.get(now).get(j).getIndex()] = cost;
                                }
                            }
                        }
}