package mihnea.licenta.server.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class SensorsData {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private java.time.LocalDate date; // LocalDate.parse("2017-11-15")

    private double temp;
    private double earthHum;
    private double light;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getEarthHum() {
        return earthHum;
    }

    public void setEarthHum(double earthHum) {
        this.earthHum = earthHum;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
