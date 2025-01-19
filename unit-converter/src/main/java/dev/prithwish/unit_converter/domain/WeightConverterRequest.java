package dev.prithwish.unit_converter.domain;

public class WeightConverterRequest {
    private double weight;
    private WeightUnit unitFromConvert;
    private WeightUnit unitToConvert;

    public WeightConverterRequest() {
    }

    public WeightConverterRequest(double weight, WeightUnit unitFromConvert, WeightUnit unitToConvert) {
        this.weight = weight;
        this.unitFromConvert = unitFromConvert;
        this.unitToConvert = unitToConvert;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public WeightUnit getUnitFromConvert() {
        return unitFromConvert;
    }

    public void setUnitFromConvert(WeightUnit unitFromConvert) {
        this.unitFromConvert = unitFromConvert;
    }

    public WeightUnit getUnitToConvert() {
        return unitToConvert;
    }

    public void setUnitToConvert(WeightUnit unitToConvert) {
        this.unitToConvert = unitToConvert;
    }
}
