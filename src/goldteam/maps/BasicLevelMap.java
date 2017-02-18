/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.maps;

import goldteam.domain.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

/**
 *
 * @author gordon
 */
public class BasicLevelMap extends GameObject implements Movable, Collidable {

    private GameObject go;
     public Image tileImage;
     public int [][] map;
     private BasicLevelMap layer;
    
    public BasicLevelMap(GameEngine gamedata, Point initialPoint, int [][] existingMap) throws IOException {
        super(gamedata, initialPoint);
    
        
        
        this.gamedata.getMapDimensions();
        this.image.getGraphics();
        String del = ":";
        Map<String, String> map = new HashMap<>();
   
        try(Stream<String> lines = Files.lines(Paths.get("/assets/map.txt"))){
            lines.filter(line -> line.contains(del)).forEach(
                    line ->map.putIfAbsent(line.split(del)[0], line.split(del)[1])
            );
        }
        System.out.println(map);
     /*   
    map = new int[existingMap.length][existingMap[0].length];

    for(int y = 0; y < map.length; y++)
    {
        for(int x = 0; x < map[y].length; x++)
        {
            map[y][x] = existingMap[y][x];
        }
    }*/
    tileImage = LoadTileSheet("block_brick.png");
    }
    
    public static BasicLevelMap FromFile (String fileName){
    
    BasicLevelMap layer = null;
    
    ArrayList<ArrayList<Integer>> tempLayout = new ArrayList<>();
    
    try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
    {
        String currentLine;
        
        while((currentLine = br.readLine()) !=null)
        {
           if(currentLine.isEmpty())
               continue;
            
           ArrayList<Integer> row = new ArrayList<>(); // for rows 
           
           String[] values = currentLine.trim().split(" "); //trim off all white spaces
           
           for(String string: values)
           {
               if(!string.isEmpty())
               {
                   int id = Integer.parseInt(string);
                   
                   row.add(id);
               }
           }
           tempLayout.add(row);
        }
    }
    catch(IOException e){
        
    }
    
    int width = tempLayout.get(0).size(); 
    int height = tempLayout.size();
    
    //layer = new TileLayer(width, height);
    
    for(int y = 0; y < height; y++)
    {
        for(int x = 0; x < width; x++){
            layer.map[y][x] = tempLayout.get(y).get(x);
        }
    }
    
    layer.tileImage = layer.LoadTileSheet("assets/block_brick.png");
    
    return layer;
}
  public BufferedImage LoadTileSheet(String fileName){
    
    BufferedImage img = null;

    try{
        img = ImageIO.read(new File(fileName));
    }
    catch(IOException e)
    {
        System.out.printf("Could not load image!");
    }
    return img;
}

public void DrawLayer(Graphics s){
    
    for(int y = 0; y < map.length; y++)
    {
        for(int x = 0; x < map[y].length; x++){
        int index = map[y][x];
        int yOffset = 0;
        
      //  if(index > (tileImage.getWidth()))
        {
            yOffset++;
            
     //    index = index - (tileImage.getWidth());
            
        }
    }
    }
}

/*public void DrawPanel(){
    
    layer = BasicLevelMap.FromFile("map.txt");
    
}*/
    @Override
    public void Update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DoubleVector getVelocityVector() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVelocityScalarDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Polygon getPolygon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCollider(Collidable obj, CollisionPlane direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCollider(Collidable obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Collidable, CollisionPlane> getColliders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void GraphicsUpdateHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void ClickHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void KeyHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void UpdateEffectHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void MapUpdateTimerHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
