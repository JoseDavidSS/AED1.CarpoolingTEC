package tec.ac.cr.carpoolingtec;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import tec.ac.cr.carpoolingtec.Data.Rider;
import tec.ac.cr.carpoolingtec.Data.SubRoute;
import tec.ac.cr.carpoolingtec.Logic.List;
import tec.ac.cr.carpoolingtec.Logic.MainBrain;
import tec.ac.cr.carpoolingtec.Logic.Node;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;
import tec.ac.cr.carpoolingtec.Server.Connect;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class DriverView extends AppCompatActivity {

    ArrayList<Integer> clickedPoints = new ArrayList<>();
    ArrayList lineCount = new ArrayList();
    ArrayList route = new ArrayList();
    TemporalHolder holder;
    int moveCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lineCount.add(99);
        setContentView(R.layout.activity_driver_view);
        try {
            holder = Connect.getMapData();
            drawGraph(holder.getMatrixEnableRoads(), holder.getList());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ArrayList getClickedPoints() {
        return clickedPoints;
    }

    /**
     * Registers button input and updates GUI with lines.
     * @param v View
     */
    public void modifyClickedPoints(View v) throws ExecutionException, InterruptedException {
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

            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(origin);
            arrayList.add(destination);
            SubRoute subRoute = new SubRoute(arrayList);
            subRoute = Connect.createRoute(subRoute);
            route = subRoute.getArrayList();

            // If there's no route between the nodes
            if ((int) route.get(0) == -1) {
                Context context = getApplicationContext();
                CharSequence text = "There's no route between points";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                clickedPoints.clear();
            } else {
                // Sends data to server
                MainMenu.driver.setDestination(destination);
                MainMenu.driver.setLocation(origin);
                MainMenu.driver.setCurrentRoute(route);
                MainMenu.driver = Connect.addDriver(MainMenu.driver);

                drawRoute(route, holder.getList());

                // Moves car to starting point
                ImageView carIcon = findViewById(R.id.car);
                int startPointID = (int) route.get(0);
                Node startPoint = holder.getList().searchElement(startPointID);
                int xpos = startPoint.getPosx();
                int ypos = startPoint.getPosy();
                carIcon.setTranslationX(xpos - 30);
                carIcon.setTranslationY(ypos - 45);

                // Resets clickedPoints and toggles buttons off
                clickedPoints.clear();
                toggleButtons(false);
            }
        }
    }

    /**
     * Draws route in GUI
     * @param route ArrayList
     * @param points List
     */
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
     * Redraws route if it changes
     * @param origin int origin point
     * @param destination int destination point
     */
    public void redrawRoute(int origin, int destination) {
        drawGraph(holder.getMatrixEnableRoads(), holder.getList());
        route = MainBrain.createRoute(origin, destination, holder.getRoadMatrix());
        drawRoute(route, holder.getList());

        // Moves car to starting point of redrawn route
        ImageView carIcon = findViewById(R.id.car);
        int startPointID = (int) route.get(0);
        Node startPoint = holder.getList().searchElement(startPointID);
        int xpos = startPoint.getPosx();
        int ypos = startPoint.getPosy();
        carIcon.setTranslationX(xpos - 30);
        carIcon.setTranslationY(ypos - 45);

        // Resets count data
        moveCount = 1;
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
                    drawLine(startx - 55, starty - 210, endx - 55, endy - 210, paint);
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
        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.driverConstraint);
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
     * Moves car to next node in route
     * @param v View
     */
    public void next(View v) throws ExecutionException, InterruptedException {
        if (moveCount < route.size()) {
            MainMenu.driver = Connect.updateDriver(MainMenu.driver);
            if (MainMenu.driver.isOnWay()) {
                route = MainMenu.driver.getCurrentRoute();
                redrawRoute((int) route.get(0), (int) route.get(route.size() - 1));
            }
            int destinationID = (int) route.get(moveCount);
            Node destination = holder.getList().searchElement(destinationID);
            int xpos = destination.getPosx();
            int ypos = destination.getPosy();
            ImageView carIcon = findViewById(R.id.car);
            carIcon.setTranslationX(xpos - 30);
            carIcon.setTranslationY(ypos - 45);
            MainMenu.driver.setLocation(destinationID);
            MainMenu.driver = Connect.updateDriver(MainMenu.driver);
            moveCount++;
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Already there, dude";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
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
     * @return int View ID
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

    /**
     * Converts Android View ID to internal point ID from 0 to 29
     * @param viewID int Android View ID
     * @return int ID from 0 to 29
     */
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
