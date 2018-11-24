package tec.ac.cr.carpoolingtec;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import tec.ac.cr.carpoolingtec.Data.Rider;
import tec.ac.cr.carpoolingtec.Logic.List;
import tec.ac.cr.carpoolingtec.Logic.MainBrain;
import tec.ac.cr.carpoolingtec.Logic.Node;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class RiderView extends AppCompatActivity {

    ArrayList clickedPoints = new ArrayList();
    ArrayList lineCount = new ArrayList();
    int userID = TemporalHolder.userID;
    TemporalHolder holder = MainBrain.preparation();
    Rider rider = new Rider(userID, -1, false, -1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lineCount.add(99);
        setContentView(R.layout.activity_rider_view);
        TextView mainLabel = findViewById(R.id.mainLabel);
        mainLabel.setText("Hello, " + userID);
        drawGraph(holder.getMatrixEnableRoads(), holder.getList());
    }

    public ArrayList getClickedPoints() {
        return clickedPoints;
    }

    /**
     * Registers button input and updates GUI with lines.
     * @param v View
     */
    public void modifyClickedPoints(View v) {
        int point = v.getId();
        System.out.println("Point with ID: " + point + " has been selected");
        clickedPoints.add(point);
        // If there are two clicked points
        if (clickedPoints.size() == 2) {
            System.out.println("Link between " + clickedPoints.get(0) + " and point " + clickedPoints.get(1));
            // Paint settings
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(6);
            paint.setAntiAlias(true);
            // Gets route between nodes
            int origin = fromViewIDtoPointID((int) clickedPoints.get(0));
            int destination = fromViewIDtoPointID((int) clickedPoints.get(1));
            ArrayList route = MainBrain.createRoute(origin, destination, holder.getRoadMatrix());
            // If there's no route between the nodes
            if ((int) route.get(0) == -1) {
                Context context = getApplicationContext();
                CharSequence text = "There's no route between points";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                clickedPoints.clear();
            } else {
                drawRoute(route, holder.getList());
                clickedPoints.clear();
                toggleButtons(false);
            }
        }
    }

    public void drawRoute(ArrayList route, List points) {
        int i = 0;
        while (i != route.size() - 1) {
            Node start = points.searchElement((int) route.get(i));
            Node end = points.searchElement((int) route.get(i + 1));
            float startx = start.getPosx();
            float starty = start.getPosy();
            float endx = end.getPosx();
            float endy = end.getPosy();
            // Sets paint data
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            // Draws line
            drawLine(startx - 55, starty - 195, endx - 55, endy - 195, paint);
            i++;
        }
    }

    /**
     * Draws points from point list data
     * @param list List
     */
    public void drawPoints(List list) {
        Node node = list.head;
        int i = 1;
        while (node != null) {
            moveTo(i, node.getPosx(), node.getPosy());
            i++;
            node = node.next;
        }
    }

    /**
     * Draws points and lines in graph.
     * @param enableRoads array matrix ([][]) with connections
     * @param points List with point data
     */
    public void drawGraph(int[][] enableRoads, List points) {
        drawPoints(points);
        for (int i=0; i < enableRoads.length; i++) {
            for (int j=0; j < enableRoads[i].length; j++) {
                // If there's a connection, draw a line
                if (enableRoads[i][j] == 1) {
                    // Gets start and end points's x and y positions
                    Node start = points.searchElement(i);
                    Node end = points.searchElement(j);
                    float startx = start.getPosx();
                    float starty = start.getPosy();
                    float endx = end.getPosx();
                    float endy = end.getPosy();
                    // Sets paint data
                    Paint paint = new Paint();
                    paint.setColor(Color.RED);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(6);
                    paint.setAntiAlias(true);
                    // Draws line
                    drawLine(startx - 55, starty - 195, endx - 55, endy - 195, paint);
                }
            }
        }
    }

    /**
     * Draws line in GUI.
     * @param startx X position of start point
     * @param starty Y postion of start point
     * @param endx X position of end point
     * @param endy Y position of end point
     */
    public void drawLine(float startx, float starty, float endx, float endy, Paint paint) {
        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.riderConstraint);
        ConstraintSet set = new ConstraintSet();
        lineView line = new lineView(this);
        line.paint = paint;
        PointF start = new PointF(startx, starty);
        PointF end = new PointF(endx, endy);
        line.isDrawing = true;
        line.startPoint = start;
        line.endPoint = end;
        line.setId((int) lineCount.get(0) + 1);
        lineCount.add((int) lineCount.get(0) + 1);
        lineCount.remove(0);
        ConstraintLayout.LayoutParams params =
                new ConstraintLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.addView(line, params);
        set.clone(layout);
        set.connect(line.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 60);
        set.applyTo(layout);
    }

    /**
     * Randomizes 30 points in GUI.
     * @param v View
     */
    public void generate(View v){
//        int i = 1;
//        while (i != 30) {
//            Button myBtn;
//            myBtn = (Button) findViewById(getNode(i));
//            myBtn.setTranslationX(randomNumberX());
//            myBtn.setTranslationY(randomNumberY());
//            i++;
//        }
        ArrayList route = MainBrain.createRoute(0, 9, holder.getRoadMatrix());
        drawRoute(route, holder.getList());
    }

    /**
     * Moves a button to different position
     * @param node int number of button to move
     * @param x X position of destination
     * @param y Y position of destination
     */
    public void moveTo(int node, int x, int y) {
        Button myBtn;
        myBtn = (Button) findViewById(getNode(node));
        myBtn.setTranslationX(x);
        myBtn.setTranslationY(y);
    }

    /**
     * Enables or disables buttons
     * @param booli boolean
     */
    public void toggleButtons(boolean booli) {
        int i = 1;
        while (i != 30) {
            Button button = (Button) findViewById(getNode(i));
            button.setEnabled(booli);
            i++;
        }
    }

    /**
     * Converts high value point numbers to button ID's.
     * @param num int high value point number
     * @return
     */
    public int getNode(int num) {
        int result = 0;
        if (num == 1) {
            result = R.id.node1;
        } else if (num == 2) {
            result = R.id.node2;
        } else if (num == 3) {
            result = R.id.node3;
        } else if (num == 4) {
            result = R.id.node4;
        } else if (num == 5) {
            result = R.id.node5;
        } else if (num == 6) {
            result = R.id.node6;
        } else if (num == 7) {
            result = R.id.node7;
        } else if (num == 8) {
            result = R.id.node8;
        } else if (num == 9) {
            result = R.id.node9;
        } else if (num == 10) {
            result = R.id.node10;
        } else if (num == 11) {
            result = R.id.node11;
        } else if (num == 12) {
            result = R.id.node12;
        } else if (num == 13) {
            result = R.id.node13;
        } else if (num == 14) {
            result = R.id.node14;
        } else if (num == 15) {
            result = R.id.node15;
        } else if (num == 16) {
            result = R.id.node16;
        } else if (num == 17) {
            result = R.id.node17;
        } else if (num == 18) {
            result = R.id.node18;
        } else if (num == 19) {
            result = R.id.node19;
        } else if (num == 20) {
            result = R.id.node20;
        } else if (num == 21) {
            result = R.id.node21;
        } else if (num == 22) {
            result = R.id.node22;
        } else if (num == 23) {
            result = R.id.node23;
        } else if (num == 24) {
            result = R.id.node24;
        } else if (num == 25) {
            result = R.id.node25;
        } else if (num == 26) {
            result = R.id.node26;
        } else if (num == 27) {
            result = R.id.node27;
        } else if (num == 28) {
            result = R.id.node28;
        } else if (num == 29) {
            result = R.id.node29;
        } else if (num == 30) {
            result = R.id.node30;
        }
        return result;
    }

    /**
     * Randomizes a number for X axis.
     * @return int random number between 100 and 1080
     */
    public int randomNumberX() {
        Random r = new Random();
        int low = 100;
        int high = 1080;
        int result = r.nextInt(high-low) + low;
        return result;
    }

    /**
     * Randomizes a number for Y axis.
     * @return int random number between 300 and 1600
     */
    public int randomNumberY() {
        Random r = new Random();
        int low = 400;
        int high = 1700;
        int result = r.nextInt(high-low) + low;
        return result;
    }

    public int fromViewIDtoPointID(int viewID) {
        Button button = findViewById(viewID);
        int result = -1;
        if (button == findViewById(R.id.node1)) {
            result = 0;
        } else if (button == findViewById(R.id.node2)) {
            result = 1;
        } else if (button == findViewById(R.id.node3)) {
            result = 2;
        } else if (button == findViewById(R.id.node4)) {
            result = 3;
        } else if (button == findViewById(R.id.node5)) {
            result = 4;
        } else if (button == findViewById(R.id.node6)) {
            result = 5;
        } else if (button == findViewById(R.id.node7)) {
            result = 6;
        } else if (button == findViewById(R.id.node8)) {
            result = 7;
        } else if (button == findViewById(R.id.node9)) {
            result = 8;
        } else if (button == findViewById(R.id.node10)) {
            result = 9;
        } else if (button == findViewById(R.id.node11)) {
            result = 10;
        } else if (button == findViewById(R.id.node12)) {
            result = 11;
        } else if (button == findViewById(R.id.node13)) {
            result = 12;
        } else if (button == findViewById(R.id.node14)) {
            result = 13;
        } else if (button == findViewById(R.id.node15)) {
            result = 14;
        } else if (button == findViewById(R.id.node16)) {
            result = 15;
        } else if (button == findViewById(R.id.node17)) {
            result = 16;
        } else if (button == findViewById(R.id.node18)) {
            result = 17;
        } else if (button == findViewById(R.id.node19)) {
            result = 18;
        } else if (button == findViewById(R.id.node20)) {
            result = 19;
        } else if (button == findViewById(R.id.node21)) {
            result = 20;
        } else if (button == findViewById(R.id.node22)) {
            result = 21;
        } else if (button == findViewById(R.id.node23)) {
            result = 22;
        } else if (button == findViewById(R.id.node24)) {
            result = 23;
        } else if (button == findViewById(R.id.node25)) {
            result = 24;
        } else if (button == findViewById(R.id.node26)) {
            result = 25;
        } else if (button == findViewById(R.id.node27)) {
            result = 26;
        } else if (button == findViewById(R.id.node28)) {
            result = 27;
        } else if (button == findViewById(R.id.node29)) {
            result = 28;
        } else if (button == findViewById(R.id.node30)) {
            result = 29;
        }
        return result;
    }

}
