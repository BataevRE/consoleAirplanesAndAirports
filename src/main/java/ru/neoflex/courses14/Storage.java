package ru.neoflex.courses14;//To change this template use File | Settings | File Templates.

import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.entity.LocationOfAirplanes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Storage {
    private static Storage instance;
    private Boolean saveInFile;
    private HashMap<Long, Airport> airports;
    private HashMap<Long, Airplane> airplanes;
    private HashSet<LocationOfAirplanes> locationsOfAirplanes;

    private Storage() {
        airports = new HashMap<Long, Airport>();
        airplanes = new HashMap<Long, Airplane>();
        locationsOfAirplanes = new HashSet<LocationOfAirplanes>();
        saveInFile = false;
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void addAirport(Airport airport) throws IOException {
        if (saveInFile) {
            File file = new File("Storage/Airports/");
            if (!file.exists()) {
                file.mkdirs();
            }
            writeObject(new File(file.getAbsolutePath() + "/Airport" + airport.getAirportId() + ".bin"), airport);
        } else {
            this.airports.put(airport.getAirportId(), airport);
        }
    }

    public void addAirplane(Airplane airplane) {
        if (saveInFile) {
            File file = new File("Storage/Airplanes/");
            if (!file.exists()) {
                file.mkdirs();
            }
            marshal(airplane, new File(file + "/Airplane" + airplane.getAirplaneId() + ".xml"));
        } else {
            this.airplanes.put(airplane.getAirplaneId(), airplane);
        }
    }

    public void DBsave(Object obj) {
        String username = "study";
        String password = "study";
        String url = "jdbc:oracle:thin:@192.168.56.102:1521:orcl";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            statement.execute("create table airport (airport_id number primary key, name varchar2(20), city varchar2(20))");
            statement.execute("insert into airport values ('46465465', 'dme', 'moscow')");
            conn.commit();
            conn.close();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void marshal(Object obj, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, file);
        } catch (JAXBException e) {
            System.out.println(e);
        }
    }

    public void addLocationOfAirplanes(LocationOfAirplanes locationOfAirplanes) throws IOException, ClassNotFoundException {
        Set<LocationOfAirplanes> location = new HashSet<LocationOfAirplanes>();
        if (saveInFile) {
            File file = new File("Storage/Location.bin");
            if (file.canRead()) {
                location = (Set<LocationOfAirplanes>) readObject(file);
            }
            location.add(locationOfAirplanes);
            writeObject(file, location);
        } else {
            this.locationsOfAirplanes.add(locationOfAirplanes);
        }
    }

    public Map<Long, Airport> getAirports() throws IOException, ClassNotFoundException {
        if (saveInFile) {
            Map<Long, Airport> result = new HashMap<Long, Airport>();
            File dir = new File("Storage/Airports/");
            File[] list = dir.listFiles();
            if (list != null) {
                for (File file : list) {
                    if (file.getName().contains("Airport")) {
                        Airport airport = (Airport) readObject(file);
                        result.put(airport.getAirportId(), airport);
                    }
                }
            }
            return result;
        } else {
            return (Map<Long, Airport>) airports.clone();
        }
    }

    public Map<Long, Airplane> getAirplanes() {
        if (saveInFile) {
            Map<Long, Airplane> result = new HashMap<Long, Airplane>();
            File dir = new File("Storage/Airplanes/");
            File[] list = dir.listFiles();
            if (list != null) {
                for (File file : list) {
                    if (file.getName().contains("Airplane")) {
                        Airplane airplane = (Airplane) unmarshal(file, Airplane.class);
                        result.put(airplane.getAirplaneId(), airplane);
                    }
                }
            }
            return result;
        } else {
            return (Map<Long, Airplane>) airplanes.clone();
        }
    }

    public Object unmarshal(File file, Class clazz) {
        Object result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println(e);
        }
        return result;
    }

    public Set<LocationOfAirplanes> getLocationsOfAirplanes() throws IOException, ClassNotFoundException {
        if (saveInFile) {
            Set<LocationOfAirplanes> result;
            result = (HashSet<LocationOfAirplanes>) readObject(new File("Storage/Location.bin"));
            return result;
        } else {
            return (Set<LocationOfAirplanes>) locationsOfAirplanes.clone();
        }
    }

    public void removeAirport(Long id) {
        if (saveInFile) {
            File file = new File("Storage/Airports/Airport" + id + ".bin");
            if (!file.delete()) {
                System.out.println("Нет доступа к файлу Airport" + id + ".bin");
            }
        } else {
            airports.remove(id);
        }
    }

    public void removeAirplane(Long id) {
        if (saveInFile) {
            File file = new File("Storage/Airplanes/Airplane" + id + ".xml");
            if (!file.delete()) {
                System.out.println("Нет доступа к файлу Airplane" + id + ".xml");
            }
        } else {
            airplanes.remove(id);
        }
    }

    public void removeLocationOfAirplanes(LocationOfAirplanes location) throws IOException, ClassNotFoundException {
        File file = new File("Storage/Location.bin");
        if (saveInFile) {
            Set<LocationOfAirplanes> locations = (HashSet<LocationOfAirplanes>) readObject(file);
            Set<LocationOfAirplanes> temp = new HashSet<LocationOfAirplanes>();
            temp.addAll(locations);
            for (LocationOfAirplanes locationOfAirplanes : temp) {
                if (locationOfAirplanes.getAirportId().equals(location.getAirportId()) && locationOfAirplanes.getAirplaneId().equals(location.getAirplaneId())) {
                    locations.remove(locationOfAirplanes);
                }
            }
            writeObject(file, locations);
        } else {
            locationsOfAirplanes.remove(location);
        }
    }

    public void setSaveInFile(Boolean bool) {
        saveInFile = bool;
    }

    private void writeObject(File file, Object obj) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Throwable throwable = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
        } catch (FileNotFoundException e) {
            throwable = e;
            throw e;
        } catch (IOException e) {
            throwable = e;
            throw e;
        } finally {
            if (throwable == null) {
                oos.close();
                fos.close();
            } else {
                try {
                    oos.close();
                    fos.close();
                } catch (Throwable unused) {
                }
            }
        }
    }

    private Object readObject(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object obj = null;
        Throwable throwable = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            obj = ois.readObject();
        } catch (FileNotFoundException e) {
            throwable = e;
            throw e;
        } catch (IOException e) {
            throwable = e;
            throw e;
        } catch (ClassNotFoundException e) {
            throwable = e;
            throw e;
        } finally {
            if (throwable == null) {
                ois.close();
                fis.close();
            } else {
                try {
                    ois.close();
                    fis.close();
                } catch (Throwable unused) {
                }
            }
        }
        return obj;
    }
}
