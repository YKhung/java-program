import java.util.*;

public class Scorpion {
 static int[][] graph = new int[100][100];
 static int[] visited = new int[100];
 static int[] counts =new int[100];
 static int n = 19;
 static int predfn, postdfn;
 static ArrayList<Integer> Queue = new ArrayList<Integer>();;
 
 public static void main(String[] args) {
  graph[0][1] = graph[1][0] = 1;
  graph[0][6] = graph[6][0] = 1;
  graph[1][5] = graph[5][1] = 1;
  graph[5][6] = graph[6][5] = 1;
  graph[6][7] = graph[7][6] = 1;
  graph[7][8] = graph[8][7] = 1;
  graph[7][9] = graph[9][7] = 1;
  graph[8][9] = graph[9][8] = 1;
  graph[1][2] = graph[2][1] = 1;
  graph[2][3] = graph[3][2] = 1;
  graph[3][4] = graph[4][3] = 1;
  graph[2][4] = graph[4][2] = 1;
  
  graph[10][11] = graph[11][10] = 1;
  graph[11][13] = graph[13][11] = 1;
  graph[12][11] = graph[11][12] = 1;
  graph[11][14] = graph[14][11] = 1;
  
  graph[16][17] = graph[17][16] = 1;
  graph[17][18] = graph[18][17] = 1;
  //DFS();
  //System.out.println();
  for (int i = 0; i < n; i++) {
   visited[i] = 0;
  }
  BFS();
  
 }
 
 public static void DFS() {
  int flag = 0;
  for (int i = 0; i < n; i++) {
   if (visited[i] == 0) {
    flag++;
    dfs(i, flag);
   }
  }
 }
 
 private static void dfs(int i, int flag) {
  visited[i] = 1;
  System.out.println(flag + " " + i);
  for (int j = 0; j < n; j++) {
   if (graph[i][j] == 1 && visited[j] == 0) {
    dfs(j, flag);
   }
  }
 }
 
 public static void BFS() {
  int flag = 0;
  for (int i = 0; i < n; i++) {
   if (visited[i] == 0) {
    flag++;
    bfs(i, flag);
   }
  }
 }
 
 private static void bfs(int i, int flag) {
  visited[i] = 1;
  Queue.add(i);
  //System.out.println(flag + ":" + i);
  while (!Queue.isEmpty()) {
   int v = Queue.remove(Queue.size() - 1);
   int count=0;
   for (int j = 0; j < n; j++) {
    if (graph[v][j] == 1 && visited[j] == 0) {
    count=count+1;
     visited[j] = 1;
     Queue.add(j);
     //System.out.println(flag + ":" + j);
    }
   }
   if (count==0|| count==n-1){
	   System.out.println("not Scoripon");
   }
   else if(count==n-2){
	   
   }
  }
 }
}

