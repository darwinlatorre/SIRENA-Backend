package co.edu.unicauca.SIRENABackend.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Classroom")
public class ClassroomModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cls_id", unique = true)
    private  Integer clsId;
    private  String clsName;
    private Integer clsCapacity;
    private  String clsState;
    private  String clsBuilding;
    private  String clsType;

    public Integer getClsId() {
        return clsId;
    }

    public void setClsId(Integer clsId) {
        this.clsId = clsId;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public Integer getClsCapacity() {
        return clsCapacity;
    }

    public void setClsCapacity(Integer clsCapacity) {
        this.clsCapacity = clsCapacity;
    }

    public String getClsState() {
        return clsState;
    }

    public void setClsState(String clsState) {
        this.clsState = clsState;
    }

    public String getClsBuilding() {
        return clsBuilding;
    }

    public void setClsBuilding(String clsBuilding) {
        this.clsBuilding = clsBuilding;
    }

    public String getClsType() {
        return clsType;
    }

    public void setClsType(String clsType) {
        this.clsType = clsType;
    }
}
