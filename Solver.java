package com.bullet.projects.mazesolver;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;
import javax.swing.JFrame;

public class Solver extends JFrame {
    int[][] maze;
    int x, y;
    static String ans = "";
    static List<Integer> path = new ArrayList<>();
    static boolean check = false;

    public Solver(int[][] maze, int x, int y) {
        this.maze = maze;
        this.x = x; // starting point
        this.y = y; // starting point
        setTitle("Maze Solver");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boolean[][] map = new boolean[maze.length][maze[0].length];

        dfs(maze, x, y, map);
        System.out.println(ans);
    }

    @Override
    public void paint(Graphics g) {
        g.translate(50, 50);
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                Color color;
                switch (maze[i][j]) {
                    case 1: color = Color.BLACK; break;
                    case 9: color = Color.RED; break;
                    default: color = Color.WHITE; break;
                }

                g.setColor(color);
                g.fillRect(15 * j, 15 * i, 15, 15);
                g.setColor(Color.RED);
                g.drawRect(15 * j, 15 * i, 15, 15);
            }
        }

        Color color = Color.GREEN;

        for(int i = 0; i < path.size(); i+=2) {
            g.setColor(color);
            g.fillRect(15 * path.get(i+1), 15 * path.get(i), 15, 15);
            g.setColor(Color.RED);
            g.drawRect(15 * path.get(i+1), 15 * path.get(i), 15, 15);
        }
    }

    static boolean dfs(int[][] maze, int i, int j, boolean[][] map) {
        if(i >= maze.length || j >= maze[0].length || i < 0 || j < 0 || maze[i][j] == 1 || map[i][j]) {
            return false;
        }

        if(maze[i][j] == 9) {
            path.add(i);
            path.add(j);
            return true;
        }

        map[i][j] = true;

        if(dfs(maze, i + 1, j, map)) {
            path.add(i);
            path.add(j);
            return true;
        }

        if(dfs(maze, i, j + 1, map)) {
            path.add(i);
            path.add(j);
            return true;
        }

        if(dfs(maze, i - 1, j, map)) {
            path.add(i);
            path.add(j);
            return true;
        }

        if(dfs(maze, i, j - 1, map)) {
            path.add(i);
            path.add(j);
            return true;
        }

        map[i][j] = false;
        return false;
    }
}
