package org.sang.bean.basedata;

public class Material {
    private int id;
    private String name;
    private String number;
    private int baseUnitId;
    private String baseUnitName;
    private String materialTypeName;
    private int materialTypeId;
    private String materialModel;

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBaseUnitId() {
        return baseUnitId;
    }

    public void setBaseUnitId(int baseUnitId) {
        this.baseUnitId = baseUnitId;
    }

    public String getBaseUnitName() {
        return baseUnitName;
    }

    public void setBaseUnitName(String baseUnitName) {
        this.baseUnitName = baseUnitName;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public int getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(int materialType) {
        this.materialTypeId = materialType;
    }
}
