import java.io.*;
import java.util.*;

public class swea5648 {

    static int n;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc<=T; tc++){
            
            n = Integer.parseInt(br.readLine());
            List<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());
                atoms.add(new Atom(x, y, dir, energy));
            }

            int totalEnergy = simulate(atoms);
            sb.append("#").append(tc).append(" ").append(totalEnergy).append("\n");

        }
        System.out.print(sb);
    }

    static int simulate(List<Atom> atoms){
        int totalEnergy = 0;

        while (!atoms.isEmpty()) {
            Map<String, List<Atom>> map = new HashMap<>();

            for (Atom atom : atoms) {
                atom.x += dx[atom.dir];
                atom.y += dy[atom.dir];
                String key = atom.x + "," + atom.y;
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(atom);
            }

            List<Atom> nextAtoms = new ArrayList<>();

            for (List<Atom> list : map.values()) {
                if (list.size() >= 2) {
                    for (Atom a : list) {
                        totalEnergy += a.energy;
                    }
                } else {
                    nextAtoms.add(list.get(0));
                }
            }

            atoms = nextAtoms;
            atoms.removeIf(a -> a.x < -2000 || a.x > 2000 || a.y < -2000 || a.y > 2000);
        }

        return totalEnergy;
    }

    static class Atom{
        int x;
        int y;
        int dir;
        int energy;
        boolean alive;

        Atom(int x, int y, int dir, int energy){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
            this.alive = true;
        }
    }
}
