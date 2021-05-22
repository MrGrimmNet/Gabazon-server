package net.mrgrimm.gabazon;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class DBConnection {
    private Statement stmt;
    private ResultSet result;
    private Connection con;
    private static DBConnection dbConn=null;
    private DBConnection(){
        try{
            List<File> jars = Arrays.asList(new File("jars/").listFiles());
            URL[] urls = new URL[jars.size()];
            for (int i = 0; i < jars.size(); i++) {
                try {
                    urls[i] = jars.get(i).toURI().toURL();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            URLClassLoader childClassLoader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
            Class.forName("com.mysql.cj.jdbc.Driver", true , childClassLoader);
            //Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){ System.out.println(e);}
    }
    public static DBConnection getConnection(){
        if(dbConn==null)
            dbConn=new DBConnection();
        return dbConn;
    }
    public ResultSet queryTheDatabase(String stm){
        try {
            if (con == null || con.isClosed())
                con = DriverManager.getConnection("jdbc:mysql://localhost/minecraftitem" + "user=gabazon&password=server");
            stmt=con.createStatement();
            result=stmt.executeQuery(stm);
        }catch(SQLException e){System.out.println(e); }
        return result;
    }


//    private static String functionToMap(String temp){
//        if(temp.charAt(0)==' ')
//            return temp.replaceFirst(" ","");
//        else
//            return temp;
//    }
//    public void initializeDatabase(String filePath) throws FileNotFoundException {
//        Scanner sc = new Scanner(new File(filePath));
//        int idMovie=1,idActor=1,idGenre=1,idDirector=1;
//        sc.useDelimiter("\n");
//        while(sc.hasNext() && idMovie<10){
//            String[] temp=sc.next().split(",");
//            Movie mov=new Movie(idMovie,temp[0],temp[1],Integer.parseInt(temp[3]),(int)(Float.parseFloat(temp[6])*10));
//            MovieDAO.insertMovie(this,mov);
//
//            if(temp[5]!=""){
//                String[] actors=Arrays.stream(temp[5].split(";"))
//                        .map(actor -> functionToMap(actor))
//                        .toArray(String[]::new);
//                for(String item : actors){
//                    if(ActorDAO.findByName(this, item)==null){
//                        Actor actorTemporar= new Actor(idActor,item);
//                        ActorDAO.insertActor(this,actorTemporar);
//                        Acting actingTemporar=new Acting(idActor,idMovie);
//                        ActingDAO.insertActing(this,actingTemporar);
//                        idActor++;
//                    }
//                    else{
//                        Acting actingTemporar=new Acting(ActorDAO.findByName(this, item).getId(),idMovie);
//                        ActingDAO.insertActing(this,actingTemporar);
//                    }
//                }
//            }
//
//            {
//                try {
//                    con.close();
//                }catch(SQLException e){System.out.println(e);}
//            }
//
//            if(temp[4]!=""){
//                String[] directors= Arrays.stream(temp[4].split(";"))
//                        .map(director -> functionToMap(director))
//                        .toArray(String[]::new);
//                for(String item : directors){
//                    if(DirectorDAO.findByName(this,item)==null){
//                        Director directorTemporar=new Director(idDirector,item);
//                        DirectorDAO.insertDirector(this,directorTemporar);
//                        Directing directingTemporar=new Directing(idDirector,idMovie);
//                        DirectingDAO.insertDirecting(this,directingTemporar);
//                        idDirector++;
//                    }
//                    else{
//                        Directing directingTemporar=new Directing(DirectorDAO.findByName(this,item).getId(),idMovie);
//                        DirectingDAO.insertDirecting(this,directingTemporar);
//                    }
//                }
//            }
//
//            {
//                try {
//                    con.close();
//                }catch(SQLException e){System.out.println(e);}
//            }
//
//            if(temp[2]!=""){
//                String[] genres=Arrays.stream(temp[2].split(";"))
//                        .map(genre -> functionToMap(genre))
//                        .toArray(String[]::new);
//                for(String item : genres){
//                    if(GenreDAO.findByName(this,item)==null){
//                        Genre genreTemporar=new Genre(idGenre,item);
//                        GenreDAO.insertGenre(this,genreTemporar);
//                        MovieGenres genresTemporar=new MovieGenres(idMovie, idGenre);
//                        MovieGenresDAO.insertMovieGenre(this,genresTemporar);
//                        idGenre++;
//                    }
//                    else{
//                        MovieGenres genresTemporar=new MovieGenres(idMovie, GenreDAO.findByName(this,item).getId());
//                        MovieGenresDAO.insertMovieGenre(this,genresTemporar);
//                    }
//                }
//            }
//
//            {
//                try {
//                    con.close();
//                }catch(SQLException e){System.out.println(e);}
//            }
//
//            idMovie++;
//        }
//
//        sc.close();
//    }


}
